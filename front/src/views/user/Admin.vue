<template>
  <div class="pl-1">
    <v-card style="max-height: 45vh;">
      <v-row height="100%">
        <v-col cols="12" sm="6">
          <v-card-title style="font-size: 1.1rem; font-weight: 900; border-bottom: 2px solid navy; padding: 10px 20px;">최근 활동</v-card-title>

          <v-timeline align-top :dense="$vuetify.breakpoint.smAndDown" style="margin: 5px;">
            <v-timeline-item
              v-for="(item, i) in items"
              :key="i"
              :color="item.color"
              fill-dot
              style="height: 50px;"
            >
              <v-card
                style="padding: 0; opacity: 0.85; margin-top: -10px;"
                :style="{'border': '1px solid '+item.color}"
              >
                <v-card-title 
                class="pa-0 ma-1"
                style="font-size: 16px;"><b>{{ item.subject }}</b>님 - {{ item.content }}</v-card-title>
                
              </v-card>
            </v-timeline-item>
          </v-timeline>
        </v-col>
        <v-col cols="12" sm="6" style="position: relative;">
          
          <v-card-title style="font-size: 1.1rem; font-weight: 900; border-bottom: 2px solid navy; padding: 10px 20px;">
              해야 할 일 
            <v-badge
              v-if="todoCount"
              class="ml-2"
              color="indigo darken-3"
              :content="todoCount"
            >
              <!-- <v-chip color="indigo darken-3" style="color: white;">{{todoCount}}</v-chip> -->
            </v-badge>
          </v-card-title>
          <div class="container scBar" id="vue-app" style="height: 200px; overflow-y:scroll;">
            <div class="row">
              <div class="col-sm-12">
                
                <ul class="list-group" v-if="todos.length > 0">
                  <li class="list-group-item" 
                      v-for="(todo, i) in todos"
                      :key="todo">
                    <v-icon :color="ballonColor[i%3]">mdi-balloon</v-icon>
                    <span
                      v-bind:class="{ 'completed' : todo.completed }"
                    >
                    {{todo.title}}
                    </span>
                    <button class="ml-2 btn-delete" v-on:click="deleteTodo(i)">삭제</button>
                    <button class="ml-2" 
                      v-bind:class="{'btn-success' : todo.completed, 'btn-danger' : !todo.completed}"
                      v-on:click="todoCompleted(todo)">{{todo.completed ? '진행' : '완료'}}</button>
                  </li>
                </ul>
                
                <div v-else><p>할 일을 모두 끝내셨습니다</p></div>
              </div>
            </div>
            
            <div class="todo-input" style="position: absolute; bottom: 0; width: 100%;">
                <form v-on:submit.prevent="addNewTodo(newTodo)">
                    <input 
                      type="text" 
                      v-model="newTodo.title"
                      class="ml-10 mt-2 mb-2"
                      style="width: 80%; border-bottom: 1px solid navy;" 
                      placeholder="할 일을 작성해주세요">
                    <button><v-icon color="indigo darken-3">mdi-card-plus-outline</v-icon></button>
                </form>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-card>
    <v-card class="mt-2">
    <v-tabs vertical>
      <v-tab>
        <v-icon left>mdi-account-box-outline</v-icon>
        회원 관리
      </v-tab>
      <v-tab>
        <v-icon left>mdi-newspaper</v-icon>
        채용공고 관리
      </v-tab>
      <v-tab>
        <v-icon left>mdi-xml</v-icon>
        언어 관리
      </v-tab>
      <v-tab>
        <v-icon left>mdi-message-draw</v-icon>
        리뷰 관리
      </v-tab>

      <v-tab-item>
        <v-card flat>
          <v-card-text>
            <admin-user class="mb-0"></admin-user>
          </v-card-text>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <v-card-text>
            <admin-job></admin-job>
          </v-card-text>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <v-card-text>
            <admin-lang></admin-lang>
          </v-card-text>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <v-card-text>
            <admin-review></admin-review>
          </v-card-text>
        </v-card>
      </v-tab-item>
    </v-tabs>
  </v-card>
  </div>
  
</template>

<script>
import AdminJob from '@/views/user/AdminJob.vue'
import AdminLang from '@/views/user/AdminLang.vue'
import AdminReview from '@/views/user/AdminReview.vue'
import AdminUser from '@/views/user/AdminUser.vue'
export default {
  components: {
    AdminJob,
    AdminLang,
    AdminReview,
    AdminUser
  },
  data: () => ({
      ballonColor: [
        'red darken-3', 'orange darken-2', 'green darken-2', 'yellow darken-2'
      ],
      items: [
        {
          color: '#ca3111',
          icon: 'mdi-book-variant',
          subject: 'qkrwjdwn',
          content: '리뷰'
        },
        {
          color: '#CCB63F',
          icon: 'mdi-account',
          subject: 'qkrwjdwn',
          content: '회원가입'
        },
        {
          color: '#990000',
          icon: 'mdi-exit-to-app',
          subject: 'whtnwl',
          content: '탈퇴'
        },
        {
          color: '#CCB63F',
          icon: 'mdi-account',
          subject: 'whtnwl',
          content: '회원가입'
        },
      ],
      todos: [
        {id: 1, title: '채용공고 관리 컴포넌트 생성', completed: true},
        {id: 2, title: '설문조사 모달', completed: false},
        {id: 3, title: 'rest api', completed: false}
        ],
      newTodo: {id: null, title: '', completed: false},
    }),
    computed: {
      todoCount() {
        return this.todos.length
    }
  },
  
  methods: {
    addNewTodo(newTodo) {
      this.todos.push(newTodo)
      this.newTodo = {id: null, title: '', completed: false}
    },
    
    deleteTodo(i) {
      this.todos.splice(i, 1)
    },
    
    todoCompleted(todo) {
      todo.completed = !todo.completed
    }
  },
}
</script>

<style lang="scss">
.v-timeline-item__dot, .v-timeline-item--fill-dot .v-timeline-item__inner-dot {
  height: 15px !important;
  width: 15px !important;
  margin: auto !important;
}
.v-slide-group__wrapper {
  width: 170px;
}
.v-tabs-slider-wrapper {
  width: 10px !important;
}
.v-tab {
  justify-content: start !important;
}
.v-item-group.theme--light.v-slide-group.v-tabs-bar.primary--text {
  color: navy !important;
  /* caret-color: #ff0000 !important; */
}
.v-tab--active {
  font-size: 0.97rem !important;
  font-weight: bolder !important;
  text-align: left !important;
  /* text-shadow: 0px 0px 1px navy; */
}

span.completed {
  text-decoration: line-through;
}
.margin-right-10 {
  margin-right: 10px;
}
.btn-success {
  color: green;
}
.btn-success:hover {
  background-color: rgba(0, 128, 0, 0.082);
}
.btn-danger {
  color: rgb(255, 94, 0);
}
.btn-danger:hover {
  background-color: rgba(255, 94, 0, 0.099);
}
.btn-delete {
  color: red;
}
.btn-delete:hover {
  background-color: rgba(255, 0, 0, 0.101);
}
.todo-input {
  border-top: 1px dotted navy;
  width: 100%;
}
.btn-add {
  color: blue;
  padding: 2px;
  font-weight: 900;
}
.btn-success:hover, .btn-danger:hover, .btn-delete:hover, .btn-add:hover {
  border-radius: 20px;
  text-shadow: 0px 0px 1px #999;
}
.btn-add:hover {
  text-decoration: underline;
}
.scBar::-webkit-scrollbar {
  display: none;
}
</style>