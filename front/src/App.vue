<template>
  <v-app>
    <!-- <v-navigation-drawer v-model="sidebar" app>
      <v-list>
        <v-list-tile
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">
          <v-list-tile-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>{{ item.title }}</v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer> -->
    <v-app-bar fixed app color="#fff" height="66">
      <!-- <span class="hidden-sm-and-up">
        <v-app-bar-nav-icon @click="sidebar = !sidebar">
        </v-app-bar-nav-icon>
      </span> -->
      <v-toolbar-title>
        <router-link to="/" tag="span" class="toHome" style="font-family: ''; cursor: pointer; font-size: 1.5rem; font-weight: 900;">{{ appTitle }}</router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items 
        class=""
        v-for="item in menuItems" 
          :key="item.title"
          @click="logout(item.title)"
        >

        <v-btn text color="#fff" 
          v-if="item.forIf === isLogin"
          :to="item.path">
          <v-icon 
            color="indigo darken-3">
            {{item.icon}}
          </v-icon>
        </v-btn>

        
      </v-toolbar-items>
    </v-app-bar>

    <v-content>
      <router-view :key="$route.fullPath"></router-view>
    </v-content>
  </v-app>
</template>

<script>
import baseURL from '@/base-url.js'
import cookie from './cookie.js'
export default {
  name: "App",
  computed: {
    isLogin() {
      return this.$store.state.isLogin
    },
  },
  data() {
    return {
      appTitle: "나를 찾아줘",
      sidebar: false,
      menuItems: [
        { title: "Statistics", path: "/statistics", icon: "mdi-chart-bar", forIf: true },
        { title: "User", path: "/account", icon: "mdi-face", forIf: true },
        { title: "SignIn", path: "/signmerge", icon: "mdi-lock-open-outline", forIf: false },
        { title: "SignOut", path: "/signmerge", icon: "mdi-lock-outline", forIf: true },
      ]
    }
  },
  methods: {
    logout(e) {
      if (e==='SignOut' && cookie.accessToken() != 'undefined') {
        baseURL.post('user/kakaologout?tmp='+cookie.accessToken())
        var date1 = new Date();
        this.$store.commit('isLogout')
        this.$store.commit('clearInfo')
        document.cookie = 'login_user' + "= " + "; expires=" + date1.toUTCString() + "; path=/";
        document.cookie = 'login_id' + "= " + "; expires=" + date1.toUTCString() + "; path=/";
        document.cookie = 'jwt-auth-token' + "= " + "; expires=" + date1.toUTCString() + "; path=/";
        document.cookie = 'access-token' + "= " + "; expires=" + date1.toUTCString() + "; path=/";
        document.cookie = 'login_name' + "= " + "; expires=" + date1.toUTCString() + "; path=/";
        this.$router.push('/signmerge')
      } else if (e==='SignOut') {
      var date2 = new Date();
      this.$store.commit('isLogout')
      this.$store.commit('clearInfo')
      document.cookie = 'login_user' + "= " + "; expires=" + date2.toUTCString() + "; path=/";
      document.cookie = 'login_id' + "= " + "; expires=" + date2.toUTCString() + "; path=/";
      document.cookie = 'jwt-auth-token' + "= " + "; expires=" + date2.toUTCString() + "; path=/";
      document.cookie = 'access-token' + "= " + "; expires=" + date1.toUTCString() + "; path=/";
      document.cookie = 'login_name' + "= " + "; expires=" + date2.toUTCString() + "; path=/";
      this.$router.push('/signmerge')
      }
      
    },
    
  }
}
</script>
<style lang="scss" scoped>
  .toHome {
    text-align: center;
    color: #fff;
		font-size: 92px;
		font-family: "ubuntu";
		text-transform: uppercase;
		font-weight: 700;
		font-family: "Josefin Sans", sans-serif;
		background: linear-gradient(to right, navy 10%, yellow 50%, navy 70%);
		background-size: auto auto;
		background-clip: border-box;
		background-size: 200% auto;
		background-clip: text;
		text-fill-color: transparent;
		-webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    -webkit-text-stroke: 1px rgba(0, 0, 0, 0.014);
		animation: textclip 3s linear infinite;
		display: inline-block;
  }
  @keyframes textclip {
	to {
		background-position: 200% center;
	}
}
</style>