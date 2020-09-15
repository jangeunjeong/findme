import cookie from '@/cookie.js'

export default {
  isLogin: () => {
    var token = cookie.token()
    
    if (token == null) {
      return false
    }
    if (token) {
      return true
    } else {
      return false
    }
  },

  userAuth: () => {
    return cookie.cookieAuth()
  },

  passwordChange: state => {
    return {
      user_email: state.email,
    }
  },

  userInfo: () => {
    return cookie.cookieId()
  },

  profileUserEmail: state => {
    return {
      profile: state.email
    }
  },

  userSeq: () => {
    return cookie.cookieUser()
  },
  Chk: state => {
    return state.chk
  }
}