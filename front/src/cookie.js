function token() {
  var value = document.cookie.match('(^|;) ?' + 'jwt-auth-token' + '=([^;]*)(;|$)');
  return value = value? value[2] : null
}

function accessToken() {
  var value = document.cookie.match('(^|;) ?' + 'access-token' + '=([^;]*)(;|$)');
  return value = value? value[2] : null
}

function cookieId() {
  var value = document.cookie.match('(^|;) ?' + 'login_id' + '=([^;]*)(;|$)');
  return value = value? value[2] : null
}

function cookieUser() {
  var value = document.cookie.match('(^|;) ?' + 'login_user' + '=([^;]*)(;|$)');
  return value = value? value[2] : null
}

function cookieName() {
  var value = document.cookie.match('(^|;) ?' + 'login_name' + '=([^;]*)(;|$)');
  return value = value? value[2] : null
}



function updateCookie() {
  var value = token()
  if (value !== null) {
      var day = new Date();
      // ms단위기에 1초로 변환->60초->60분->24시간->최종적으로 day
      day.setTime(day.getTime() + (60*60*1000));
      var expires = "expires=" + day.toUTCString();
      document.cookie = 'jwt-auth-token' + "=" + value + ";" + expires + ";path=/" + ";";
      document.cookie = 'access-token' + "=" + accessToken() + ";" + expires + ";path=/" + ";";
      document.cookie = 'login_user' + "=" + cookieUser() + ";" + expires + ";path=/" + ";";
      document.cookie = 'login_id' + "=" + cookieId() + ";" + expires + ";path=/" + ";";
      document.cookie = 'login_name' + "=" + cookieName() + ";" + expires + ";path=/" + ";";
      return true
  } else {
    return false
  }
}

function cookieCreate(data) {
  let d = new Date();                
  d.setTime(d.getTime() + (60*60*1000));
  let expires = "expires="+ d.toUTCString();
  document.cookie = "login_user=" + data.id + ";" + expires + ";path=/";
  document.cookie = "jwt-auth-token=" + data.token + ";" + expires + ";path=/";
  document.cookie = "access-token=" + data.accessToken + ";" + expires + ";path=/";
  document.cookie = "login_id=" + data.email + ";" + expires + ";path=/";
  document.cookie = 'login_name' + "=" + data.name + ";" + expires + ";path=/" + ";";
}

const cookie = {
  token:()=>token(),
  accessToken:()=>accessToken(),
  updateCookie:()=>updateCookie(),
  cookieId:()=>cookieId(),
  cookieUser:()=>cookieUser(),
  cookieName:()=>cookieName(),
  cookieCreate:(email, headers)=>cookieCreate(email, headers),
}
export default cookie