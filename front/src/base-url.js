import axios from "axios";
import cookie from '@/cookie.js'
export default axios.create({
  baseURL: "http://localhost:8888/api",
  // baseURL: "https://i02b204.p.ssafy.io/api",
  // baseURL: "/api",
  headers: {
    "jwt-auth-token": cookie.token(),
    "access-token": cookie.accessToken(),
  }
});