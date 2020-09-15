<template>
  <div>
    <v-container fluid>
      <v-layout wrap justify-center>
        <v-flex v-for="(card,i) in cards" :key="i" class="ma-2 pa-0" xs12 sm4 md4 lg3>
          <v-card class="job-card">
            <a :href="card.url" target="blank">
              <v-img
                :src="card.imgUrl"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.8)"
                height="200px"
              >
                <v-card-title
                  class="font-weight-bold"
                  style="text-shadow: 1px 1px 1px #000;"
                >{{ card.position }}</v-card-title>
                <v-card-subtitle
                  class="white--text font-weight-black text-right mb-n6"
                >{{ card.company }}</v-card-subtitle>
                <v-card-subtitle class="white--text font-weight-medium text-right">~{{ card.date }}</v-card-subtitle>
              </v-img>
            </a>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn icon @click="pick(card)">
                <v-icon :class="{picked:card.isPick}">mdi-bookmark</v-icon>
                <!--:class="{picked: card.picked}"-->
              </v-btn>
              <span v-if="kakaoChk">
                <v-btn icon @click="showDialog(card)">
                  <v-icon>mdi-share-variant</v-icon>
                </v-btn>
              </span>
            </v-card-actions>
          </v-card>
        </v-flex>
        <v-dialog v-model="shareDialog" width="500">
          <v-card>
            <v-card-title class="indigo darken-3" dark primary-title>
              <span style="color: white;">카카오톡으로 공유하기</span>
            </v-card-title>
            <v-data-table
              hide-default-header
              hide-default-footer
              :headers="headers"
              :items="friendsList"
              :items-per-page="5"
              class="elevation-1 ma-4"
            >
              <template v-slot:item.profile_nickname="{item}">
                <v-layout justify-center>
                  <button
                    class="btn-flip ml-n12"
                    :data-front="item.profile_nickname"
                    data-back="보내기"
                    @click="shareFriend(Data.id, item)"
                  ></button>
                </v-layout>
              </template>
              <template v-slot:item.profile_thumbnail_image="{item}">
                <v-layout justify-center>
                  <div class="pa-2 photo mr-n12">
                    <v-img :src="item.profile_thumbnail_image" width="100px"></v-img>
                    <div class="glow-wrap">
                      <i class="glow"></i>
                    </div>
                  </div>
                </v-layout>
              </template>
            </v-data-table>

            <v-divider></v-divider>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="indigo darken-3" text @click="shareDialog = false">I 닫기 I</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="loading" fullscreen>
          <v-container fluid fill-height style="background-color: rgba(255, 255, 255, 0.5);">
            <v-layout justify-center align-center>
              <v-progress-circular indeterminate color="primary"></v-progress-circular>
            </v-layout>
          </v-container>
        </v-dialog>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import baseURL from "@/base-url.js";
import cookie from "@/cookie.js";
export default {
  data: () => ({
    Data: {
      id: 0,
      company: "",
      position: ""
    },
    pickList: [],
    shareDialog: false,
    loading: false,
    headers: [
      {
        text: "프로필",
        align: "start",
        sortable: false,
        value: "profile_thumbnail_image"
      },
      {
        text: "이름",
        value: "profile_nickname"
      }
    ],
    friendsList: [],
    kakaoChk: false,
    pickColor: "black"
  }),
  props: {
    cards: Array
  },
  methods: {
    showDialog(card) {
      console.log("card: " + JSON.stringify(card));
      this.Data = card;
      this.shareDialog = true;
    },
    getFriends() {
      baseURL(
        "user/" +
          cookie.cookieUser() +
          "/kakaofriends?tmp=" +
          cookie.accessToken()
      )
        .then(res => {
          console.log("kakaofriedns");
          console.log(res);
          this.friendsList = res.data.info;
        })
        .catch(err => {
          console.log(err);
        });
    },
    shareFriend(card, v) {
      let data = {
        uuids: [v.uuid]
      };
      baseURL
        .post(
          "user/" +
            cookie.cookieUser() +
            "/" +
            card +
            "/kakao_sendToFriends?tmp=" +
            cookie.accessToken(),
          data
        )
        .then(() => {
          alert(v.profile_nickname + "님에게 공유하셨습니다.");
        })
        .catch(err => {
          console.log(err);
        });
    },
    getPickList() {
      baseURL("pick/findAll/" + cookie.cookieUser()).then(res => {
        this.pickList = res.data.recruit;
        for (var i = 0; i < this.pickList.length; i++) {
          for (var j = 0; j < this.cards.length; j++) {
            if (this.pickList[i].recruit.id == this.cards[j].id) {
              this.cards[j].picked = this.pickList[i].chekcPick;
            }
          }
        }
      });
    },
    pick(card) {
      location.reload()
      if (card.isPick) {
        baseURL
          .delete(
            "pick/delete?user_id=" +
              cookie.cookieUser() +
              "&recruit_id=" +
              card.id
          )
          .then(() => {
            var icon = document.querySelector(".card" + card.id);
            icon.classList.remove("picked");
            card.picked = !card.picked;
            this.$router.push("/account");
          });
      } else {
        baseURL
          .post("pick/save/" + cookie.cookieUser() + "?recruit_id=" + card.id)
          .then(() => {
            var icon = document.querySelector(".card" + card.id);
            icon.classList.add("picked");
            card.picked = !card.picked;
            this.$router.push("/account");
          });
      }
    }
  },
  mounted() {
    if (cookie.accessToken() != "undefined") {
      this.kakaoChk = true;
    }
    this.getPickList();
    this.getFriends();
  }
};
</script>

<style lang="scss">
.theme--light.job-card {
  box-shadow: 3px 3px 5px #ccc;
}
.picked {
  color: #c0392b !important;
}
$speed: 0.5s;

.btn-flip {
  opacity: 1;
  outline: 0;
  color: #fff;
  line-height: 40px;
  position: relative;
  text-align: center;
  letter-spacing: 1px;
  display: inline-block;
  text-decoration: none;
  font-family: "Open Sans";
  text-transform: uppercase;

  &:hover {
    &:after {
      opacity: 1;
      transform: translateY(0) rotateX(0);
    }

    &:before {
      opacity: 0;
      transform: translateY(50%) rotateX(90deg);
    }
  }

  &:after {
    top: 0;
    left: 0;
    opacity: 0;
    width: 100%;
    color: #eeeeee;
    display: block;
    transition: $speed;
    position: absolute;
    background: #010152;
    content: attr(data-back);
    transform: translateY(-50%) rotateX(90deg);
  }

  &:before {
    top: 0;
    left: 0;
    opacity: 1;
    color: #020253;
    display: block;
    padding: 0 30px;
    line-height: 40px;
    transition: $speed;
    position: relative;
    background: inherit;
    border: 2px solid #020253;
    border-right: 0;
    border-left: 0;
    content: attr(data-front);
    transform: translateY(0) rotateX(0);
  }
}

.photo {
  position: relative;
  display: block;
}

.photo .v-image {
  border-radius: 50px;
  width: 100px;
  height: 100px;
  object-fit: cover;
  // filter: grayscale(100%) contrast(120%);
  box-shadow: 10px 15px 25px 0 rgba(0, 0, 0, 0.2);
  display: block;
  transition: all 0.5s cubic-bezier(0.645, 0.045, 0.355, 1);
}

.photo:hover .v-image {
  box-shadow: 1px 1px 10px 0 rgba(0, 0, 0, 0.1);
}

.photo .glow-wrap {
  overflow: hidden;
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
}

.photo .glow {
  display: block;
  position: absolute;
  width: 40%;
  height: 200%;
  background: rgba(255, 255, 255, 0.2);
  top: 0;
  filter: blur(5px);
  transform: rotate(45deg) translate(-450%, 0);
  transition: all 0.5s cubic-bezier(0.645, 0.045, 0.355, 1);
}

.photo:hover .glow {
  transform: rotate(45deg) translate(450%, 0);
  transition: all 1s cubic-bezier(0.645, 0.045, 0.355, 1);
}
</style>