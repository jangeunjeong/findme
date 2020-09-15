<template>
  <section class="forms-section">
    <div class="firefly" v-for="n in 30" :key="n"></div>
    <div class="forms">
      <div class="form-wrapper is-active">
        <button type="button" class="switcher switcher-login" @click="loginSwitch()">
          로그인
          <span class="underline"></span>
        </button>
        <form class="form form-login">
          <login name="Login"></login>
        </form>
      </div>
      <div class="form-wrapper">
        <button type="button" class="switcher switcher-signup" @click="signupSwitch()">
          회원가입
          <span class="underline"></span>
        </button>
        <form class="form form-signup">
          <signup name="Signup"></signup>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
import Signup from "@/views/user/Signup.vue";
import Login from "@/views/user/Login.vue";

export default {
  components: {
    Signup,
    Login
  },
  mounted() {
    if (this.$route.params.data) {
      this.signupSwitch();
    }
  },
  methods: {
    signupSwitch() {
      const signupSwitch = document.querySelector(".switcher-signup");
      const loginSwitch = document.querySelector(".switcher-login");
      loginSwitch.parentElement.classList.remove("is-active");
      signupSwitch.parentElement.classList.add("is-active");
    },
    loginSwitch() {
      const signupSwitch = document.querySelector(".switcher-signup");
      const loginSwitch = document.querySelector(".switcher-login");
      signupSwitch.parentElement.classList.remove("is-active");
      loginSwitch.parentElement.classList.add("is-active");
    }
  }
};
</script>

<style lang="scss">
.forms-section {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  // background-color: rgb(4, 12, 102);
  background: url("../../assets/images/sky3.jpg") no-repeat;
  background-position: center;
  background-size: cover;
  overflow-x: scroll;
  overflow-y: hidden;
}
.firefly {
  position: fixed;
  left: 50%;
  top: 50%;
  width: 0.4vw;
  height: 0.4vw;
  margin: -0.2vw 0 0 9.8vw;
  animation: ease 100s alternate infinite;
  pointer-events: none;

  &::before,
  &::after {
    content: "";
    position: absolute;
    width: 100%;
    height: 100%;
    border-radius: 50%;
    transform-origin: -10vw;
  }
  &::before {
    background: rgb(4, 12, 102);
    opacity: 0.4;
    animation: drift ease alternate infinite;
  }
  &::after {
    background: rgb(247, 243, 7);
    opacity: 0;
    box-shadow: 0 0 0vw 0vw yellow;
    animation: drift ease alternate infinite, flash ease infinite;
  }
}

@for $i from 1 through 30 {
  $steps: random(12) + 16;
  $rotationSpeed: random(10) + 8s;

  .firefly:nth-child(#{$i}) {
    animation-name: move#{$i};

    &::before {
      animation-duration: #{$rotationSpeed};
    }

    &::after {
      animation-duration: #{$rotationSpeed}, random(6000) + 5000ms;
      animation-delay: 0ms, random(8000) + 500ms;
    }
  }

  @keyframes move#{$i} {
    @for $step from 0 through $steps {
      #{$step * (100 / $steps)}% {
        transform: translateX(random(100) - 50vw)
          translateY(random(100) - 50vh)
          scale(random(75) / 100 + 0.25);
      }
    }
  }
}
@keyframes drift {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
@keyframes flash {
  0%,
  30%,
  100% {
    opacity: 0;
    box-shadow: 0 0 0vw 0vw yellow;
  }
  5% {
    opacity: 1;
    box-shadow: 0 0 2vw 0.4vw yellow;
  }
}

.section-title {
  font-size: 32px;
  letter-spacing: 1px;
  color: #fff;
}

.forms {
  display: flex;
  align-items: flex-start;
  margin-top: 30px;
}

.form-wrapper {
  width: 600px;
  animation: hideLayer 0.3s ease-out forwards;
}

.form-wrapper.is-active {
  animation: showLayer 0.3s ease-in forwards;
}

@keyframes showLayer {
  50% {
    z-index: 1;
  }
  100% {
    z-index: 1;
  }
}

@keyframes hideLayer {
  0% {
    z-index: 1;
  }
  49.999% {
    z-index: 1;
  }
}

.switcher {
  position: relative;
  cursor: pointer;
  display: block;
  margin-right: auto;
  margin-left: auto;
  padding: 0;
  text-transform: uppercase;
  font-family: inherit;
  font-size: 16px;
  letter-spacing: 0.5px;
  color: #999;
  background-color: transparent;
  border: none;
  outline: none;
  transform: translateX(0);
  transition: all 0.3s ease-out;
}
.switcher:hover {
  color: rgb(255, 255, 255);
}

.switcher-login {
  right: -200px;
}
.switcher-signup {
  left: -200px;
}
.form-wrapper.is-active .switcher-login {
  color: #fff;
  text-shadow: 1px 1px 5px #fff;
  transform: translateX(50px);
}

.form-wrapper.is-active .switcher-signup {
  color: #fff;
  transform: translateX(-50px);
}

.underline {
  position: absolute;
  bottom: -5px;
  left: 0;
  overflow: hidden;
  pointer-events: none;
  width: 100%;
  height: 2px;
}

.underline::before {
  content: "";
  position: absolute;
  top: 0;
  left: inherit;
  display: block;
  width: inherit;
  height: inherit;
  background-color: currentColor;
  transition: transform 0.2s ease-out;
}

.switcher-login .underline::before {
  transform: translateX(101%);
}

.switcher-signup .underline::before {
  transform: translateX(-101%);
}

.form-wrapper.is-active .underline::before {
  transform: translateX(0);
}

.form {
  overflow: hidden;
  margin-top: 50px;
  padding: 30px 25px;
  border-radius: 5px;
  transform-origin: top;
}

.form-login {
  width: 100%;

  animation: hideLogin 0.3s ease-out forwards;
}

.form-wrapper.is-active .form-login {
  animation: showLogin 0.3s ease-in forwards;
}

.form-wrapper:not(.is-active) .container {
  // background:rgb(4, 12, 102);
  opacity: 0.2;
}
@keyframes showLogin {
  0% {
    /* background: #d7e7f1; */
    transform: translate(40%, 10px);
  }
  50% {
    transform: translate(0, 0);
  }
  100% {
    /* background-color: #fff; */
    transform: translate(35%, -20px);
  }
}

@keyframes hideLogin {
  0% {
    /* background-color: #fff; */
    transform: translate(35%, -20px);
  }
  50% {
    transform: translate(0, 0);
  }
  100% {
    /* background: #d7e7f1; */
    transform: translate(40%, 10px);
  }
}

.form-signup {
  width: 100%;
  animation: hideSignup 0.3s ease-out forwards;
}

.form-wrapper.is-active .form-signup {
  animation: showSignup 0.3s ease-in forwards;
}

@keyframes showSignup {
  0% {
    /* background: #d7e7f1; */
    transform: translate(-40%, 10px);
  }
  50% {
    transform: translate(0, 0);
  }
  100% {
    /* background-color: #fff; */
    transform: translate(-35%, -20px);
  }
}

@keyframes hideSignup {
  0% {
    /* background-color: #fff; */
    transform: translate(-35%, -20px);
  }
  50% {
    transform: translate(0, 0);
  }
  100% {
    /* background: #d7e7f1; */
    transform: translate(-40%, 10px);
  }
}

.form fieldset {
  position: relative;
  opacity: 0;
  margin: 0;
  padding: 0;
  border: 0;
  transition: all 0.3s ease-out;
}

.form-login fieldset {
  transform: translateX(-50%);
}

.form-signup fieldset {
  transform: translateX(50%);
}

.form-wrapper.is-active fieldset {
  opacity: 1;
  transform: translateX(0);
  transition: opacity 0.4s ease-in, transform 0.35s ease-in;
}

.form legend {
  position: absolute;
  overflow: hidden;
  width: 1px;
  height: 1px;
  clip: rect(0 0 0 0);
}

.input-block {
  margin-bottom: 20px;
}

.input-block label {
  font-size: 14px;
  color: #a1b4b4;
}

.input-block input {
  display: block;
  width: 100%;
  margin-top: 8px;
  padding-right: 15px;
  padding-left: 15px;
  font-size: 16px;
  line-height: 40px;
  color: #3b4465;
  background: #eef9fe;
  border: 1px solid #cddbef;
  border-radius: 2px;
}

.form [type="submit"] {
  opacity: 0;
  display: block;
  min-width: 120px;
  margin: 30px auto 10px;
  font-size: 18px;
  line-height: 40px;
  border-radius: 25px;
  border: none;
  transition: all 0.3s ease-out;
}

.form-wrapper.is-active .form [type="submit"] {
  opacity: 1;
  transform: translateX(0);
  transition: all 0.4s ease-in;
}

.btn-login {
  color: #fbfdff;
  background: #a7e245;
  transform: translateX(-30%);
}

.btn-signup {
  color: #a7e245;
  background: #fbfdff;
  box-shadow: inset 0 0 0 2px #a7e245;
  transform: translateX(30%);
}
.v-form {
  width: 100%;
}

@media only screen and (max-width: 600px) {
  .form-wrapper {
    width: 300px;
  }
  .form-login.form-signup {
    display: none;
  }
  .switcher-login {
    right: -80px;
  }
  .switcher-signup {
    left: -40px;
  }
}
</style>