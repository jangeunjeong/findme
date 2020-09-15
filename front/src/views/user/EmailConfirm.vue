<template>
<div class="backConfirm">
<div class="loading">
  <div class="dot"></div>
  <div class="dot"></div>
  <div class="dot"></div>
  <div class="dot"></div>
  <div class="dot"></div>
</div>
</div>
</template>

<script>
import baseURL from '@/base-url.js'

export default {
  data () {
    return {
    }
  },
  mounted() {
    var data = {
      confirm_code: this.$route.params.confirmcode,
      email: this.$route.params.email
    }
    baseURL.get('user/key_alter?email='+data.email+'&key='+data.confirm_code)
      .then(()=> {
        alert("인증되셨습니다 로그인 해주세요")
        this.$router.push({
          name: "SignMerge",
        });
      })
      .catch(() => {
        alert("잘못된 인증 정보입니다.")
        this.$router.push({
          name: "Main",
        });
      })
  }
}
</script>

<style lang="scss">

.backConfirm {
  position: absolute;
  top: 0;
  background-color: rgb(0, 0, 78);
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  animation: fade 5s ease infinite;
}
.loading {
  $colors: #ffffff, #89cff0, #4682b4, #0f52ba, #000080;
  $wSize: 4em, 2em, 3em, 5em, 4em;
  display: flex;
  justify-content: center;
  align-items: center;
  animation-delay: 1s;

  .dot {
    position: relative;
    margin: 1em;
    border-radius: 50%;

    &::before {
      position: absolute;
      content: "";
      width: 100%;
      height: 100%;
      background: inherit;
      border-radius: inherit;
      animation: wave 2s ease-out infinite;
    }

    @for $i from 1 through 5 {
      &:nth-child(#{$i}) {
        background: nth($colors, $i);
        width: nth($wSize, $i);
        height: nth($wSize, $i);
        &::before {
          animation-delay: $i * 0.2s;
        }
      }
    }
  }
}

@keyframes fade {
  50% {
    opacity: 0.85;
  }
  100% {
    opacity: 1;
  }
}

@keyframes wave {
  30% {
    transform: scale(0.7);
  } 
  75% {
    transform: scale(1.5);
  }
  100% {
    opacity: 0;
  }
}

</style>