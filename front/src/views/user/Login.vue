<template>
  <v-container fill-height class="justify-center">
    <svg id="stroke" xmlns="http://www.w3.org/2000/svg" width="0" height="0">
      <defs>
        <path id="line" d="M2 2c49.7 2.6 100 3.1 150 1.7-46.5 2-93 4.4-139.2 7.3 45.2-1.5 90.6-1.8 135.8-.6" fill="none"
          stroke-linecap="round" stroke-linejoin="round" vector-effect="non-scaling-stroke" />
      </defs>
    </svg>
    <v-row align="center" justify="center" style="background-color: white;">
      <v-col justify="center" sm="10">
        <v-form ref="form" v-model="valid" lazy-validation @submit.prevent>
          <p class="form-header">로그인</p>
          <hr class="form-hr">
          <br>
          <v-text-field color="indigo darken-3" v-model="id" :rules="idRules" label="Email" required></v-text-field>
          <v-text-field color="indigo darken-3" v-model="password" :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="passwordRules" :type="show1 ? 'text' : 'password'" label="비밀번호" @click:append="show1 = !show1">
          </v-text-field>
          <v-layout justify-center class="btnParent">
            <span class="box2">
              <a class="btn1"
                href="https://kauth.kakao.com/oauth/authorize?client_id=df3683c5354024c47b509ecad955f714&redirect_uri=http://localhost:8081&response_type=code&scope=talk_message,friends">
                <!-- <a class="btn1"
                href="https://kauth.kakao.com/oauth/authorize?client_id=df3683c5354024c47b509ecad955f714&redirect_uri=https://i02b204.p.ssafy.io&response_type=code&scope=talk_message,friends"> -->
                카카오 로그인
                <svg class="button-stroke" viewBox="0 0 154 13">
                  <use href="#line"></use>
                </svg>
                <svg class="button-stroke" viewBox="0 0 154 13">
                  <use href="#line"></use>
                </svg>
              </a>
              <button class="btn1 mr-2" @click="submit">
                로그인
                <svg class="button-stroke" viewBox="0 0 154 13">
                  <use href="#line"></use>
                </svg>
                <svg class="button-stroke" viewBox="0 0 154 13">
                  <use href="#line"></use>
                </svg>
              </button>
            </span>
          </v-layout>
          <v-dialog v-model="dialog" persistent max-width="600px">
            <template v-slot:activator="{ on }">
              <v-btn class="mx-auto diyBtn" style="display: block; color: #888;" text v-on="on">비밀번호를 찾아주세요</v-btn>
            </template>
            <v-card>
              <v-card-title class="ml-4 pt-8" style="font-size: 1rem; line-height: 150%;">
                가입하신 이메일 주소를 입력해주세요
                <br>
                임시 비밀번호를 보내드립니다
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-text-field v-model="email" :rules="emailRules" color="indigo darken-3" required>
                  </v-text-field>
                </v-container>
              </v-card-text>
              <v-card-actions class="box1">
                <v-spacer></v-spacer>

                <button class="btn1" @click="dialog = false; sendPassword();">
                  보내기
                  <svg class="button-stroke" viewBox="0 0 154 13">
                    <use href="#line" />
                  </svg>
                  <svg class="button-stroke" viewBox="0 0 154 13">
                    <use href="#line" />
                  </svg>
                </button>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
  import baseURL from "@/base-url.js";
  import * as EmailValidator from "email-validator";
  import cookie from '@/cookie.js'
  import '@/assets/css/user.css'

  export default {
    data: () => ({
      dialog: false,
      valid: true,
      id: '',
      idRules: [
        v => !!v || '이메일을 입력해주세요',
        v => (EmailValidator.validate(v)) || '이메일 형식이 아닙니다',
      ],
      show1: false,
      password: '',
      passwordRules: [
        v => !!v || '비밀번호를 입력해주세요',
        v => v.length >= 8 || '8자 이상의 비밀번호를 입력해주세요',
      ],
      checkbox: false,
      email: '',
      emailRules: [
        v => !!v || '이메일을 입력해주세요',
        v => (EmailValidator.validate(v)) || '이메일 형식이 아닙니다',
      ],
    }),

    methods: {
      submit() {
        baseURL.post('user/login?email=' + this.id + '&password=' + this.password)
          .then(res => {
            if (res.data.info.roleType == 'ADMIN') {
              this.$router.push({
                name: "Admin"
              })
            } else {
              let loginData = {
                id: res.data.info.id,
                token: res.headers["jwt-auth-token"],
                email: res.data.info.email,
                name: res.data.info.name,
                techStack: res.data.info.techStack,
                wishHope: res.data.info.wishHope,
                wishJob: res.data.info.wishJob,
              }
              this.$store.commit('startLogin', loginData)
              this.$store.commit('isLogin')
              cookie.cookieCreate(loginData)
              this.$router.push({
                name: "Main"
              })
            }
          })
          .catch(() => {
            alert('등록된 회원이 아닙니다')
            this.email = ''
            this.password = ''
          })

      },
      sendPassword() {
        baseURL.put('user/sendpassword?email=' + this.email)
          .then(() => {
            alert("입력하신 이메일 주소로 임시 비밀번호를 전송했습니다.")
          })
      }
    },
  }
</script>

<style lang="scss">
  .frame {
    display: flex;
    justify-content: center;
    align-items: center;
    // background: #FFA114;
  }

  .frame .BTN {
    display: flex;
    align-items: center;
    justify-content: center;

    cursor: pointer;
    border-radius: 10px;

    svg {
      height: 55px;
      width: 120px;
      fill: none;
      stroke-width: 5;

      .o1 {
        stroke: rgba(rgb(2, 0, 129), 0.7);
        fill: rgb(255, 255, 255);
        transition: all 1s ease-in-out;
      }

      .o2 {
        stroke: rgb(255, 255, 255);
        stroke-dasharray: 20 420;
        stroke-dashoffset: 20;
        transition: all 1s ease-in-out;
      }
    }

    span {
      position: absolute;
      margin: auto 0;
      text-transform: uppercase;
      letter-spacing: 3px;
    }

    &:hover {
      .o1 {
        // fill: rgba(rgb(9, 5, 255), 0.3);
      }

      .o2 {
        stroke-dashoffset: -420;
      }
    }
  }

  .box1 {
    // position:absolute;
    // top:50%;
    // left:50%;
    // transform : translate(-50% ,-50%);
    // border: 1px solid red;

    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
    top: -30px;
    left: -20px;
  }

  .box2 {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .btn1 {
    display: inline-block;
    // color: white;
    min-width: 120px;
    text-decoration: none;
    margin: auto 0;
    padding: 5px;
    position: relative;
    // top: -50px;
    text-align: center;

    &:hover {
      .button-stroke:nth-child(2) {
        stroke-dashoffset: 0;
      }
    }
  }

  .button-stroke {
    display: block;
    width: calc(100% - 40px);
    height: 20px;
    stroke: darken(#020da8, 10%);
    position: absolute;
    left: 20px;
    bottom: -10px;
    stroke-width: 4;

    &:nth-child(2) {
      stroke-dasharray: 650px;
      stroke-dashoffset: 650px;
      stroke: yellow;
      stroke-width: 5;
      transition: stroke-dashoffset 500ms ease-out;
    }
  }

  .diyBtn:hover {
    color: #000 !important;
  }

  .diyBtn:hover {
    background: none !important;
  }

  .diyBtn::before {
    background-color: #fff !important;
  }
</style>