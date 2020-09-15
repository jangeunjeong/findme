<template>
  <!-- <div class="signup"> -->
    <v-container fill-height class="justify-center">
      <svg id="stroke" xmlns="http://www.w3.org/2000/svg" width="0" height="0">
        <defs>
          <path id="line" d="M2 2c49.7 2.6 100 3.1 150 1.7-46.5 2-93 4.4-139.2 7.3 45.2-1.5 90.6-1.8 135.8-.6" fill="none" stroke-linecap="round" stroke-linejoin="round" vector-effect="non-scaling-stroke"/>
        </defs>
      </svg>
      <v-row align="center" justify="center" style="background-color: white;">
        <v-col 
          justify="center"
          sm="10"
        >
          <v-form
            ref="form"
            v-model="valid"
            lazy-validation
            class="survey"
          >
            <p class="form-header">회원가입</p>
            <hr class="form-hr">
            <br>
            <span v-if="!this.$route.params.data">
              <v-text-field
                v-model="id"
                :rules="idRules"
                label="Email"
                required
                style="width: 80%; display: inline-block;"
              >
              </v-text-field>
              <span class="btnParent">
                <v-btn @click="emailCheck()"
                  class="ml-2"
                  style="width: 18%; display: inline-block;"
                  outlined
                  small
                  color="indigo darken-3"
                >
                  중복 확인
                </v-btn>
              </span>
              
              <v-text-field
                v-model="name"
                label="이름"
                required
              >
                
              </v-text-field>
            
              <v-text-field
                v-model="password"
                :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="passwordRules"
                :type="show1 ? 'text' : 'password'"
                label="비밀번호"
                hint="비밀번호는 8자 이상 입력해주세요"
                counter
                @click:append="show1 = !show1"
              ></v-text-field>
              <v-text-field
                v-model="passwordConfirm"
                :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="passwordConfirmRules"
                :type="show2 ? 'text' : 'password'"
                label="비밀번호 확인"
                counter
                @click:append="show2 = !show2"
              ></v-text-field>
            </span>

            <v-select
              v-model="langSelect"
              :items="items"
              :rules="[v => !!v || 'Item is required']"
              label="기술 스택 선택"
              attach
              chips
              multiple
              required
            ></v-select>
            <v-select
              v-model="positionSelect"
              :items="positions"
              :rules="positionRules"
              label="선호 직무"
              color="indigo darken-3"
              attach
              chips
              multiple
              required
            ></v-select>
            <v-text-field
              v-model="firm"
              label="선호 기업"
            ></v-text-field>
            <v-layout justify-center class="box2">
              <button class="btn1 mr-2" @click="reset">
                재작성
                <svg class="button-stroke" viewBox="0 0 154 13">
                  <use href="#line"></use>
                </svg>
                <svg class="button-stroke" viewBox="0 0 154 13">
                  <use href="#line"></use>
                </svg>
              </button>
              <button class="btn1 mr-2" @click="signUp">
                회원가입
                <svg class="button-stroke" viewBox="0 0 154 13">
                  <use href="#line"></use>
                </svg>
                <svg class="button-stroke" viewBox="0 0 154 13">
                  <use href="#line"></use>
                </svg>
              </button>
            </v-layout>
          </v-form>
        </v-col>
      </v-row>
    </v-container>
  <!-- </div> -->
</template>

<script>
import baseURL from '@/base-url.js'
import * as EmailValidator from 'email-validator'
import '@/assets/css/user.css'
export default {
  data: () => ({
      valid: true,
      id: '',
      idRules: [
        v => !!v || 'ID를 입력해주세요',
        v => (EmailValidator.validate(v)) || '이메일 형식이 아닙니다',
      ],
      name: '',
      show1: false,
      show2: false,
      password: '',
      passwordRules: [
        v => !!v || '비밀번호를 입력해주세요',
        v => v.length >= 8 || '8자 이상의 비밀번호를 입력해주세요',
      ],
      passwordConfirm: '',
      passwordConfirmRules: [
        v => !!v || '비밀번호를 입력해주세요',
        v => v.length >= 8 || '8자 이상의 비밀번호를 입력해주세요'
      ],
      langSelect: [],
      items: [
        'Java',
        'C',
        'Python',
        'C++',
        'C#',
        'VB.NET',
        'JavaScript',
        'PHP',
        'SQL',
        'Go',
        'R',
        'Assembly',
        'Swift',
        'Ruby',
        'MATLAB',
        'PL/SQL',
        'Perl',
        'Visual Basic',
        'Objective-C',
        'Delphi'
      ],
      firm: '',
      position: '',
      positionSelect: [],
      positions: [
        '웹',
        '응용프로그램',
        'QA',
        '테스터',
        '인공지능',
        '빅데이터',
        '블록체인',
        '보안',
        '데이터베이스',
        '네트워크',
        'PM',
        'ERP',
        '분석',
        '설계'
      ],
      positionRules: [
        v => !!v || '선호 직무를 입력해주세요',
      ],
      enableSwitch: true,
    }),
    watch: {
      passwordConfirm: function(e) {
        // console.log(e)
        if (e != this.password) {
          this.passwordConfirmRules[2] = '비밀번호가 일치하지 않습니다'
        } else {
          this.passwordConfirmRules.pop()
        }
        // this.formCheck(e);
      },
    },
    methods: {
      reset () {
        this.id = '';
        this.name = '';
        this.password = '';
        this.passwordConfirm = '';
        this.firm = '';
        this.langSelect = [];
        this.positionSelect = [];
      },
      signUp () {
        if (this.enableSwitch == true) {
          alert("이메일 중복 확인을 해 주세요.")
          } else if (this.$route.params.data && this.langSelect.length > 0 && this.positionSelect.length > 0 && this.valid) {
            for (var i=0; i < this.langSelect.length; i++) {
              if (this.langSelect[i] == "C++") {
                this.langSelect[i] = 'Cpp'
              } else if (this.langSelect[i] == 'C#') {
                this.langSelect[i] = 'Csharp'
              }
            }
            let Stacks = this.langSelect.join()
            let wishPositions = this.positionSelect.join()
            let data = {
              email: this.id,
              name: this.name,
              password: this.password,
              techStack: Stacks,
              wishHope: this.firm,
              wishJob: wishPositions
            }
            baseURL.post('user/kakao_signup', data)
              .then(() => {
                this.$router.push({
                  name: "/",
                });
              })
              .catch(() => {
                alert("잘못된 시도입니다.")
              })
          
        } else if (this.id && this.name && this.password && this.passwordConfirm && this.langSelect && this.positionSelect && this.valid) {
            for (var j=0; j < this.langSelect.length; j++) {
              if (this.langSelect[j] == "C++") {
                this.langSelect[j] = 'Cpp'
              } else if (this.langSelect[j] == 'C#') {
                this.langSelect[j] = 'Csharp'
              }
            }
            let Stacks = this.langSelect.join()
            let wishPositions = this.positionSelect.join()
            let data = {
              email: this.id,
              name: this.name,
              password: this.password,
              techStack: Stacks,
              wishHope: this.firm,
              wishJob: wishPositions
            }
            baseURL.post('user/signup', data)
              .then(() => {
                alert("이메일로 인증 코드를 보냈습니다.")
                this.$router.push({
                  name: "Main",
                });
              })
              .catch(()=>{
                alert("잘못된 시도입니다.")
                this.reset()
              })
        }
      },
      emailCheck() {
        baseURL('user/'+this.id+'/email-duplicate')
          .then(res => {
            if (res.data) {
              alert("등록되지 않은 이메일입니다. 계속 회원가입을 진행해주세요.")
              this.enableSwitch = false;
            }
            else {
              alert("이미 등록된 이메일입니다.")
              this.id = '';
            }
          })
          .catch(() => {
            alert("비정상적인 접근입니다.")
          })
      },
      
    },
    mounted() {
      if (this.$route.params.data) {
        let data = this.$route.params.data;
        this.id = data.info.email;
        this.name = data.info.name;
        this.password = data.info.password;
        this.passwordConfirm = data.info.password;
        this.enableSwitch = false;
      }
    }
}
</script>

<style>
.theme--light.v-chip:not(.v-chip--active) {
  /* background: linear-gradient(180deg, #002ae4, transparent) !important; */
            /* radial-gradient(ellipse at bottom, #002ae4, transparent) !important; */
  /* color: white; */
  border: 1px solid navy;
  background-color: white !important;
}
</style>