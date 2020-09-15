<template>
  <v-container>
    <v-card-title style="border-top: 3px solid navy; border-bottom: 1px solid navy;">
      <v-row>
        <v-col
          xs="6"
          sm="9"
          style="font-family: 'Cafe24Dangdanghae'; font-size: 2rem; font-weight: 900;"
        >{{name}}</v-col>
        <v-col
          xs="6"
          sm="3"
          style="font-family: 'Roboto Mono'; font-size: 1.1rem; font-weight: 900;"
        >회원정보 수정</v-col>
      </v-row>
    </v-card-title>
    <v-simple-table>
      <template v-slot:default>
        <tbody>
          <tr>
            <td width="10%" class="forTd">01</td>
            <td class="forTd2">
              <section>비밀번호 변경</section>
              <v-row ref="form" lazy-validation>
                <v-col cols="10">
                  <v-text-field
                    v-model="newPassword"
                    :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                    :rules="newPasswordRules"
                    :type="show ? 'text' : 'password'"
                    label="비밀번호"
                    color="indigo darken-3"
                    @click:append="show = !show"
                    style
                  ></v-text-field>
                </v-col>
                <v-col cols="2" class="btnParent" style justify-center>
                  <v-btn
                    outlined
                    color="rgb(14, 22, 112)"
                    class="mr-4 text-center mt-3"
                    @click="updatePassword"
                  >변경</v-btn>
                </v-col>
              </v-row>
            </td>
          </tr>
          <tr>
            <td class="forTd">02</td>
            <td class="forTd2">
              <section class="mb-4">기술 스택 변경</section>
              <v-select
                v-model="langSelect"
                :items="items"
                :rules="[v => !!v || 'Item is required']"
                label="기술 스택 선택"
                color="indigo darken-3"
                attach
                chips
                multiple
                required
              ></v-select>
            </td>
          </tr>
          <tr>
            <td class="forTd">03</td>
            <td class="forTd2">
              <section class="mb-4">선호 직무 변경</section>
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
            </td>
          </tr>
          <tr>
            <td class="forTd">04</td>
            <td class="forTd2">
              <section class="mb-4">선호 기업 변경</section>
              <v-text-field v-model="firm" :rules="firmRules" label="선호 기업" required></v-text-field>
            </td>
          </tr>
          <tr>
            <td class="text-center" colspan="2">
              <v-layout justify-center class="btnParent">
                <v-btn
                  outlined
                  color="rgb(14, 22, 112)"
                  class="ma-4 text-center"
                  @click="updateProfile"
                >프로필 수정</v-btn>
                <v-btn
                  outlined
                  color="rgb(107, 0, 0)"
                  class="errBtn ma-4 text-center"
                  @click="secessionUser"
                >회원 탈퇴</v-btn>
              </v-layout>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import cookie from "@/cookie.js";
import baseURL from "@/base-url.js";

export default {
  data() {
    return {
      show: false,
      name: "",
      prePassword: "",
      newPassword: "",
      newPasswordRules: [
        v => !!v || "비밀번호를 입력해주세요",
        v => v.length >= 8 || "8자 이상의 비밀번호를 입력해주세요"
      ],
      langSelect: [],
      items: [
        "Java",
        "C",
        "Python",
        "C++",
        "C#",
        "VB.NET",
        "JavaScript",
        "PHP",
        "SQL",
        "Go",
        "R",
        "Assembly",
        "Swift",
        "Ruby",
        "MATLAB",
        "PL/SQL",
        "Perl",
        "Visual Basic",
        "Objective-C",
        "Delphi"
      ],
      firm: "",
      firmRules: [v => !!v || "선호 기업을 입력해주세요"],
      position: "",
      positionSelect: [],
      positions: [
        "웹",
        "응용프로그램",
        "QA",
        "테스터",
        "인공지능",
        "빅데이터",
        "블록체인",
        "보안",
        "데이터베이스",
        "네트워크",
        "PM",
        "ERP",
        "분석",
        "설계"
      ],
      positionRules: [v => !!v || "선호 직무를 입력해주세요"]
    };
  },
  methods: {
    basicProfile() {
      baseURL("user/" + cookie.cookieUser() + "/profile").then(res => {
        this.name = res.data.name;
        this.prePassword = res.data.password;
        this.langSelect = res.data.techStack.split(",");
        this.firm = res.data.wishHope.split(",");
        this.positionSelect = res.data.wishJob.split(",");
        for (var i = 0; i < this.langSelect.length; i++) {
          if (this.langSelect[i] == "Cpp") {
            this.langSelect[i] = "C++";
          } else if (this.langSelect[i] == "Csharp") {
            this.langSelect[i] = "C#";
          }
        }
      });
    },
    updatePassword() {
      if (cookie.accessToken() != "undefined") {
        alert("카카오 계정은 비밀번호 변경을 할 수 없습니다");
      } else {
        console.log(cookie.cookieUser());
        console.log(this.prePassword);
        console.log(this.newPassword);
        baseURL
          .put(
            "user/" +
              cookie.cookieUser() +
              "/updatepassword?pre_password=" +
              this.prePassword +
              "&new_password=" +
              this.newPassword
          )
          .then(() => {
            alert("비밀번호 변경이 완료되었습니다");
          });
      }
    },
    updateProfile() {
      for (var i = 0; i < this.langSelect.length; i++) {
        if (this.langSelect[i] == "C++") {
          this.langSelect[i] = "Cpp";
        } else if (this.langSelect[i] == "C#") {
          this.langSelect[i] = "Csharp";
        }
      }
      let data = {
        techStack: this.langSelect.join(),
        wishHope: this.firm,
        wishJob: this.positionSelect.join()
      };
      baseURL
        .put(
          "user/" +
            cookie.cookieUser() +
            "/updateprofile?techStack=" +
            data.techStack +
            "&wishHope=" +
            data.wishHope +
            "&wishJob=" +
            data.wishJob
        )
        .then(() => {
          alert("프로필 수정이 완료되었습니다");
        });
    },
    secessionUser() {
      if (confirm("정말 탈퇴하시겠습니까?")) {
        baseURL
          .put(
            "user/" +
              cookie.cookieUser() +
              "/secession?tmp=" +
              cookie.accessToken()
          )
          .then(() => {
            var date1 = new Date();
            this.$store.commit("isLogout");
            this.$store.commit("clearInfo");
            document.cookie =
              "login_user" +
              "= " +
              "; expires=" +
              date1.toUTCString() +
              "; path=/";
            document.cookie =
              "login_id" +
              "= " +
              "; expires=" +
              date1.toUTCString() +
              "; path=/";
            document.cookie =
              "jwt-auth-token" +
              "= " +
              "; expires=" +
              date1.toUTCString() +
              "; path=/";
            document.cookie =
              "access-token" +
              "= " +
              "; expires=" +
              date1.toUTCString() +
              "; path=/";
            document.cookie =
              "login_name" +
              "= " +
              "; expires=" +
              date1.toUTCString() +
              "; path=/";
            alert("탈퇴되셨습니다.");
            this.$router.push("/");
          });
      }
    }
  },
  mounted() {
    this.basicProfile();
  }
};
</script>

<style lang="scss">
.forTd {
  text-align: center;
  color: rgb(255, 236, 65);
  font-size: 2rem !important;
  text-shadow: 1px 1px 1px rgb(165, 165, 255);
}
.forTd2 > section {
  font-family: "Cafe24Dangdanghae";
  font-size: 1.1rem;
  padding-top: 20px;
}
</style>