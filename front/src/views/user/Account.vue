<template>
  <v-row class="fill-height" no-gutters>
    <v-navigation-drawer
      color="indigo darken-3"
      mini-variant
      mini-variant-width="100"
      permanent
      expand-on-hover
      absolute
      left
    >
      <v-list-item>
        <!-- <v-list-item-avatar height="70" width="70" class="ml-n1 pl-1">
          <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
          
        </v-list-item-avatar>-->
        <p class="accountName pa-4 mx-auto">
          {{name}}
          <br />
        </p>
        <p class="accountIcon text-right">
          <router-link :to="{ name: 'Setting', params:{id: id}}">
            <v-icon color="white">mdi-cog</v-icon>
          </router-link>
        </p>
      </v-list-item>

      <v-divider color="white"></v-divider>

      <v-list dense nav>
        <p class="accountTitle">기술 스택</p>
        <hr class="hr-divider" />

        <div>
          <p
            class="stacklist px-2 py-1"
            v-for="(stack,idx) in stacks"
            :key="idx"
            @click="clicked"
          >{{stack}}</p>
        </div>
        <p class="accountTitle">희망 직무</p>
        <hr class="hr-divider" />
        <div>
          <p
            class="stacklist px-2 py-1"
            v-for="(position,idx) in positions"
            :key="idx"
            @click="clicked"
          >{{position}}</p>
        </div>
        <p class="accountTitle">희망 기업</p>
        <hr class="hr-divider" />
        <div>
          <p
            class="stacklist px-2 py-1"
            v-for="(company,idx) in companies"
            :key="idx"
            @click="clicked"
          >{{company}}</p>
        </div>
        <p class="accountTitle" @click="recChk = !recChk" style="cursor: pointer">
          <span v-if="recChk">찜한 공고</span>
          <span v-else>맞춤 공고</span>
        </p>
        <hr class="hr-divider" />
      </v-list>
    </v-navigation-drawer>
    <span v-if="recChk">
      <v-card width="100%" style="margin-left: 110px;" class="mt-2" outlined v-if="!loading">
        <v-card-title>맞춤 공고</v-card-title>
        <account-job name="AccountJob" :cards="matchCards"></account-job>
      </v-card>

      <v-card width="100%" style="margin-left: 110px;" class="my-3" outlined v-if="!loading">
        <v-card-title>이런 공고는 어떠세요?</v-card-title>
        <account-job name="AccountJob" :cards="recommendCards"></account-job>
      </v-card>

      <v-card width="100%" height="400" style="margin-left: 110px;" outlined v-if="!loading">
        <v-flex v-if="slides.length > 0">
          <v-card-title>
            <span class="font-weight-bold">{{name}}</span> 님께 이런 기술스택을 추천합니다!
          </v-card-title>
          <account-stack name="AccountStack" :slides="slides"></account-stack>
        </v-flex>
        <v-flex v-if="slides.length == 0">
          <v-card-title>
            <span class="font-weight-bold">{{name}}</span> 님은 희망 직무에 필요한 기술스택을 모두 가지고 계시네요!!!
          </v-card-title>
        </v-flex>
      </v-card>
    </span>
    <span v-else>
      <v-card style="width: 100vw; margin-left: 110px;" class="mt-2" outlined v-if="!loading">
        <v-card-title>내가 찜한 공고</v-card-title>
        <account-rec></account-rec>
      </v-card>
    </span>
    <v-dialog v-model="loading" fullscreen>
      <v-container fluid fill-height style="background-color: rgba(255, 255, 255, 0.5);">
        <v-layout justify-center align-center>
          <v-progress-circular indeterminate color="primary"></v-progress-circular>
        </v-layout>
      </v-container>
    </v-dialog>
  </v-row>
</template>

<script>
import cookie from "@/cookie.js";
import baseURL from "@/base-url.js";
import AccountJob from "@/views/user/AccountJob.vue";
import AccountStack from "@/views/user/AccountStack.vue";
import AccountRec from "@/views/user/AccountRec.vue";

export default {
  components: {
    AccountJob,
    AccountStack,
    AccountRec
  },
  mounted() {
    this.id = cookie.cookieUser();
    this.name = cookie.cookieName();
    this.loading = true;
    this.profile();
    this.getRecruitData();
  },
  data: () => ({
    id: 0,
    name: "",
    stacks: [],
    companies: [],
    positions: [],
    links: [],
    mini: true,
    matchCards: [],
    recommendCards: [],
    slides: [],
    loading: false,
    langs: [
      {
        seq: 1,
        title: "Java",
        color: "#f72047",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270237-40c34200-86f1-11ea-869c-225d6b60f4db.png"
      },
      {
        seq: 2,
        title: "C",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270230-3ef97e80-86f1-11ea-9663-c456c0d9210c.png"
      },
      {
        seq: 3,
        title: "Python",
        color: "#ffd200",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270245-43259c00-86f1-11ea-802f-2a06e05f0b77.png"
      },
      {
        seq: 4,
        title: "C++",
        color: "#0000ff",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270231-3f921500-86f1-11ea-945e-a3a2a80ed97f.png"
      },
      {
        seq: 5,
        title: "C#",
        color: "#f72047",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270232-402aab80-86f1-11ea-8c62-2f5ff64ccff2.png"
      },
      {
        seq: 6,
        title: "VB.NET",
        color: "#999999",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270229-3ef97e80-86f1-11ea-930a-fe7546979521.png"
      },
      {
        seq: 7,
        title: "JavaScript",
        color: "#ffd200",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270238-415bd880-86f1-11ea-870c-687709473154.png"
      },
      {
        seq: 8,
        title: "PHP",
        color: "#0000ff",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270244-428d0580-86f1-11ea-933d-36a8f494e46b.png"
      },
      {
        seq: 9,
        title: "SQL",
        color: "#f72047",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270226-3dc85180-86f1-11ea-9233-5a91868ee495.png"
      },
      {
        seq: 10,
        title: "Go",
        color: "#999999",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270236-40c34200-86f1-11ea-8c07-132aca1616d5.png"
      },
      {
        seq: 11,
        title: "R",
        color: "#ffd200",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270224-3c972480-86f1-11ea-9f97-24301669fa9c.png"
      },
      {
        seq: 12,
        title: "Assembly",
        color: "#0000ff",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80269858-597e2880-86ee-11ea-9c38-ef32b9e0d193.png"
      },
      {
        seq: 13,
        title: "Swift",
        color: "#f72047",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80269028-3603af80-86e7-11ea-9f61-a6610fb28a5b.png"
      },
      {
        seq: 14,
        title: "Ruby",
        color: "#999999",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270225-3dc85180-86f1-11ea-9c75-50b3e2d51fe2.png"
      },
      {
        seq: 15,
        title: "MATLAB",
        color: "#ffd200",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270239-415bd880-86f1-11ea-9046-d3a6268b2a25.png"
      },
      {
        seq: 16,
        title: "PL/SQL",
        color: "#0000ff",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80269663-aa8d1d00-86ec-11ea-9f77-7e7afc117352.png"
      },
      {
        seq: 17,
        title: "Perl",
        color: "#f72047",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270243-428d0580-86f1-11ea-88eb-3d3e876b3edc.png"
      },
      {
        seq: 18,
        title: "Visual Basic",
        color: "#999999",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270228-3e60e800-86f1-11ea-98ab-4b1fe35fad4e.png"
      },
      {
        seq: 19,
        title: "Objective-C",
        color: "#ffd200",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270242-41f46f00-86f1-11ea-851b-9976976be9f6.png"
      },
      {
        seq: 20,
        title: "Delphi",
        color: "#0000ff",
        bgs:
          "https://user-images.githubusercontent.com/52478972/80270235-402aab80-86f1-11ea-80b1-b41576992e06.png"
      }
    ],
    recChk: true
  }),
  methods: {
    clicked() {},
    profile() {
      baseURL("user/" + this.id + "/profile").then(res => {
        this.stacks = res.data.techStack.split(",");
        this.companies = res.data.wishHope.split(",");
        if (res.data.wishJob != null)
          this.positions = res.data.wishJob.split(",");
        for (var i = 0; i < this.stacks.length; i++) {
          if (this.stacks[i] == "Cpp") {
            this.stacks[i] = "C++";
          } else if (this.stacks[i] == "Csharp") {
            this.stacks[i] = "C#";
          }
        }
      });
    },
    getDate(unixTimeStamp) {
      var date = "";

      if (unixTimeStamp != "1988118000" && unixTimeStamp != "2019567600") {
        var dueDate = new Date(unixTimeStamp * 1000);
        var year = dueDate.getFullYear();
        var month =
          dueDate.getMonth() / 10 >= 1
            ? dueDate.getMonth()
            : "0" + dueDate.getMonth();
        var day =
          dueDate.getDate() / 10 >= 1
            ? dueDate.getDate()
            : "0" + dueDate.getDate();
        var hours =
          dueDate.getHours() / 10 >= 1
            ? dueDate.getHours()
            : "0" + dueDate.getHours();
        var minutes =
          dueDate.getMinutes() / 10 >= 1
            ? dueDate.getMinutes()
            : "0" + dueDate.getMinutes();
        var seconds =
          dueDate.getSeconds() / 10 >= 1
            ? dueDate.getSeconds()
            : "0" + dueDate.getSeconds();

        date =
          year +
          "-" +
          month +
          "-" +
          day +
          " " +
          hours +
          ":" +
          minutes +
          ":" +
          seconds;
      } else {
        date = "채용시 마감";
      }
      return date;
    },
    getRecruitData() {
      baseURL("user/" + this.id + "/recommend")
        .then(res => {
          this.loading = false;
          let matchRecruits = res.data.matchRecruitList.slice(0, 6);
          let recommendRecruits = res.data.recommendRecruitList.slice(0, 6);
          var matchCard = {};
          var recommendCard = {};
          let pickRecruits = res.data.pickRecruitList.slice(
            0,
            res.data.pickRecruitList.length
          );
          console.log(pickRecruits);

          for (let i = 0; i < matchRecruits.length; i++) {
            var stack = matchRecruits[i].techStack.split(",").slice(0, 3);
            var stack_rcm = recommendRecruits[i].techStack
              .split(",")
              .slice(0, 3);

            if (matchRecruits[i].imgUrl == "") {
              matchRecruits[i].imgUrl =
                "https://images.unsplash.com/photo-1461749280684-dccba630e2f6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80";
            }

            if (recommendRecruits[i].imgUrl == "") {
              recommendRecruits[i].imgUrl =
                "https://images.unsplash.com/photo-1542435503-956c469947f6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60";
            }

            var date = this.getDate(matchRecruits[i].dueDate);
            var isPick = false;

            for (let j = 0; j < pickRecruits.length; j++) {
              if (pickRecruits[j].id == matchRecruits[i].id) {
                isPick = true;
                break;
              }
            }

            matchCard = {
              id: matchRecruits[i].id,
              company: matchRecruits[i].compName,
              position: matchRecruits[i].title,
              stacks: stack,
              url: matchRecruits[i].url,
              imgUrl: matchRecruits[i].imgUrl,
              date: date,
              isPick: isPick
            };

            date = this.getDate(recommendRecruits[i].dueDate);

            isPick = false;

            for (let j = 0; j < pickRecruits.length; j++) {
              if (pickRecruits[j].id == recommendRecruits[i].id) {
                isPick = true;
                break;
              }
            }

            recommendCard = {
              id: recommendRecruits[i].id,
              company: recommendRecruits[i].compName,
              position: recommendRecruits[i].title,
              stacks: stack_rcm,
              url: recommendRecruits[i].url,
              imgUrl: recommendRecruits[i].imgUrl,
              date: date,
              isPick: isPick
            };

            this.matchCards.push(matchCard);
            this.recommendCards.push(recommendCard);
          }

          let langDataList = res.data.recommendLanguageList;

          for (let i = 0; i < langDataList.length; i++) {
            for (let j = 0; j < this.langs.length; j++) {
              if (langDataList[i] == this.langs[j].title) {
                this.slides.push({
                  title: langDataList[i],
                  bgs: this.langs[j].bgs,
                  id: this.langs[j].seq - 1
                });
              }
            }
          }
          console.log(this.matchCards);
          console.log(this.recommendCards);
          // console.log(this.slides);
        })
        .catch(err => {
          this.loading = false;
          console.log(err);
        });
    }
  },
  computed: {
    cols() {
      const { lg, sm } = this.$vuetify.breakpoint;
      return lg ? ["30%", "70%"] : sm ? ["20%", "80%"] : ["40%", "60%"];
    }
  }
};
</script>

<style lang="scss">
.accountIcon .v-icon::before {
  color: white;
}

.accountName {
  color: white;
  display: inline;
  font-size: 1.3rem;
  font-weight: 900;
}

.accountTitle {
  color: white;
  display: inline;
  font-size: 0.9rem;
}

.accountBasic {
  font-size: 1.1rem;
  font-weight: 900;
}

.stacklist {
  color: white;
  display: inline-block;
  font-weight: 900;
}

.hr-divider {
  border: none;
  border-top: dashed 1px white;
}
</style>