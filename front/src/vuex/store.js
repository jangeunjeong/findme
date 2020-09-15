import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
    isUser: false,
    isLogin: getters.isLogin(),
    email: getters.userInfo(),
    profile: '',
    id: getters.userSeq(),
    chk: false,
}

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions
})