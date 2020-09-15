<template>
  <div class>
    <v-carousel cycle style="height: 40%;" hide-delimiter-background show-arrows-on-hover>
      <v-carousel-item v-for="(slide, i) in slides" :key="i" class="w90 mx-auto">
        <div id="leftside">
          <v-sheet height="100%" :style="{'background-image': slide.bgs}">
            <v-row class="fill-height">
              <div class="display-6 pa-4"></div>
            </v-row>
          </v-sheet>
        </div>

        <div id="rightside">
          <v-sheet color="white">
            <v-row class="fill-height" align="center" justify="end">
              <v-col cols="12">
                <div style="color: #000; font-size: 2rem; font-weight: bold;">{{ slide.title }}</div>
              </v-col>
              <v-col cols="12">
                <div style="color: #000;">{{ slide.position }}</div>
              </v-col>
              <v-col cols="12">
                <v-chip
                  class="ma-2 mr-0"
                  color="indigo"
                  outlined
                  style="font-weight: 900;"
                  v-for="(stack, i) in slide.stacks"
                  :key="i"
                >{{ stack }}</v-chip>
              </v-col>
              <v-col cols="12">
                <div style="color: #000;">~{{ slide.date }}</div>
              </v-col>
              <v-col cols="12">
                <div style="color: #000;">
                  <a :href="slide.page" style="color: #000;">>>> 채용공고 보러가기</a>
                </div>
              </v-col>
            </v-row>
          </v-sheet>
        </div>
      </v-carousel-item>
    </v-carousel>
    <v-container class="mx-auto text-center langContainer">
      <span v-for="(lang, i) in langs" :key="i" style="height: 200px;">
        <router-link :to="{name:'Lang', params:{langId:i}}">
          <div :style="'background: url('+lang.bgs+')'" class="toLang">
            <div class="dimmer"></div>
            <div class="go-corner">
              <div class="go-arrow">
                <v-icon color="white">mdi-arrow-top-right-thick</v-icon>
                <!-- <p>{{ lang.title }}</p> -->
              </div>
            </div>
          </div>
        </router-link>
      </span>
      <v-flex class="mx-auto">
        <ColumnChart :value="ranking" :category="columnChartCategory" />
      </v-flex>
    </v-container>

    <!-- <v-row>
    <v-col cols=6>
      <v-card>
        <h1>깃허브에서 사용도가 높은 언어</h1>
        <p>깃허브의 저장소에 사용된 언어 빈도를 분기별로 측정한 결과입니다 <br>
        </p>
      </v-card>
    </v-col>
    <v-col cols=6>
      <v-card
        class="ml-auto text-center">
        <v-sparkline
        :value="value"
        :gradient="gradient[2]"
        :smooth="radius || false"
        :padding="padding"
        :line-width="width"
        :stroke-linecap="lineCap"
        :gradient-direction="gradientDirection"
        :fill="fill"
        :type="type"
        :auto-line-width="autoLineWidth"
        auto-draw
        height="300"
        ></v-sparkline>
      </v-card>
    </v-col>
    </v-row>-->
  </div>
</template>

<script>
import baseURL from "@/base-url.js";
import cookie from "@/cookie.js";
import "@/assets/css/main.css";
import ColumnChart from "../../components/ColumnChart";

const gradients = [
  ["#222"],
  ["#42b3f4"],
  ["red", "orange", "yellow"],
  ["purple", "violet"],
  ["#00c6ff", "#F0F", "#FF0"],
  ["#f72047", "#ffd200", "#1feaea"]
];
export default {
  components: {
    ColumnChart
  },
  props: {},
  data() {
    return {
      newUser: false,
      userId: 0,
      slides: [
        // {
        //   bgs:
        //     "url(https://images.unsplash.com/photo-1542435503-956c469947f6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60)",
        //   title: "퀸즈코퍼레이션",
        //   page: "#",
        //   position: "개발팀장",
        //   stacks: ["보안", "웹개발"],
        //   date: "2020-04-17"
        // },
        // {
        //   bgs:
        //     "url(https://images.unsplash.com/photo-1486312338219-ce68d2c6f44d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60)",
        //   title: "트래포트",
        //   page: "#",
        //   position: "프론트",
        //   stacks: ["java", "oracle"],
        //   date: "2020-04-17"
        // },
        // {
        //   bgs:
        //     "url(https://images.unsplash.com/photo-1517694712202-14dd9538aa97?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60)",
        //   title: "윈즈시스템",
        //   page: "#",
        //   position: "보안 엔지니어",
        //   stacks: ["보안"],
        //   date: "2020-04-17"
        // },
        // {
        //   bgs:
        //     "url(https://images.unsplash.com/photo-1516542076529-1ea3854896f2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60)",
        //   title: "클라우드네트웍스",
        //   page: "#",
        //   position: "개발자",
        //   stacks: ["java", "javascript", "python"],
        //   date: "2020-04-17"
        // },
        // {
        //   bgs:
        //     "url(https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60)",
        //   title: "베스핀글로벌",
        //   page: "#",
        //   position: "프론트",
        //   stacks: ["react"],
        //   date: "2020-04-17"
        // }
      ],
      showLabels: true,
      width: 2,
      radius: 10,
      padding: 8,
      lineCap: "round",
      gradient: gradients,
      value: [0, 2, 5, 9, 5, 10, 3, 5, 0, 0, 1, 8, 2, 9, 0],
      gradientDirection: "left",
      gradients,
      fill: false,
      type: "trend",
      autoLineWidth: false,
      ranking: [],
      columnChartCategory: [
        "Assenbly",
        "C",
        "C#",
        "C++",
        "Delphi",
        "Go",
        "Java",
        "JavaScript",
        "MATLAB",
        "Objective-C",
        "Perl",
        "PHP",
        "PL/SQL",
        "Python",
        "R",
        "Ruby",
        "SQL",
        "Swift",
        "Visual Basic",
        "Visual Basic .NET"
      ],
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
      ]
    };
  },
  mounted() {
    baseURL
      .get("/user/carousel")
      .then(res => {
        for (let i = 0; i < res.data.carouselList.length; i++) {
          var string = JSON.stringify(res.data.carouselList[i].techStack);
          var array = string.replace(/"/gi, "").split(",");
          console.log(array);

          var date = this.getDate(res.data.carouselList[i].dueDate);

          this.slides.push({
            bgs:
              "url(https://images.unsplash.com/photo-1542435503-956c469947f6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60)",
            title: res.data.carouselList[i].compName,
            page: res.data.carouselList[i].url,
            position: res.data.carouselList[i].title,
            stacks: array,
            date: date
          });
        }
      })
      .catch(err => {
        console.log(err);
      });

    this.userId = cookie.cookieUser();
    
    if (this.$route.query.code) {
      baseURL("user/kakao_oauth?code=" + this.$route.query.code).then(res => {
        if (res.data.log == "회원가입이 필요합니다.") {
          alert(
            "처음 카카오 로그인을 하셨습니다. " +
              res.data.info.name +
              "님, 회원가입을 해 주세요."
          );
          this.$router.push({
            name: "SignMerge",
            params: {
              data: res.data
            }
          });
        } else if (res.data.status == true) {
          let loginData = {
            id: res.data.info.id,
            token: res.data["jwt-auth-token"],
            accessToken: res.data["access-token"],
            email: res.data.info.email,
            name: res.data.info.name,
            techStack: res.data.info.techStack,
            wishHope: res.data.info.wishHope,
            wishJob: res.data.info.wishJob
          };
          this.$store.commit("startLogin", loginData);
          this.$store.commit("isLogin");
          cookie.cookieCreate(loginData);
          this.$router.push("/");
        }
      });
    }
    baseURL
      .get("/user/ranking")
      .then(res => {
        // console.log(res.data);
        var tmpRanking = res.data.info;
        console.log(this.ranking);

        var adoptList = [];
        var salaryList = [];

        for (let i = 0; i < tmpRanking.length; i++) {
          adoptList.push(tmpRanking[i].adopted);
          salaryList.push(tmpRanking[i].salary);
        }
        this.ranking.push(
          {
            name: "사용빈도",
            data: adoptList
          },
          {
            name: "연봉 (만원)",
            data: salaryList
          }
        );
        console.log(this.ranking);
      })
      .catch(err => {
        console.log(err);
      });
  },
  methods: {
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
    }
  }
};
</script>

<style lang="scss" scoped>
* {
  transition: all 0.2s ease-out;
}
/* .v-carousel__controls .v-btn--round .v-btn__content .v-icon {
  color: red !important;
} */
.langContainer {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  position: relative;
}

.toLang {
  width: 200px;
  height: 200px;
  display: inline-block;
  background-color: white !important;
  background-size: contain !important;
  background-position: center !important;
  background-repeat: no-repeat !important;
  // top: 0px;
  position: relative;
  border-radius: 4px;
  // padding: 32px 24px;
  border: 1px solid #f2f8f9;
}

.go-corner {
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  width: 32px;
  height: 32px;
  overflow: hidden;
  top: 0;
  right: 0;
  background-color: #010a3d;
  border-radius: 0 4px 0 32px;
  opacity: 0.7;
}

.toLang:hover {
  border: 1px solid #000547;
  box-shadow: 0px 0px 300px 200px rgba(255, 255, 255, 0.562);
  z-index: 500;
  transform: scale(1.05);
  transition: transform 0.2s linear;
  .go-corner {
    opacity: 1;
    width: 64px;
    height: 64px;
    transition: height 0.2s linear;
  }

  .go-arrow p {
    display: inline;
  }
}

.go-arrow {
  margin-top: -4px;
  margin-right: -4px;
  color: white;
}

.go-arrow p {
  font-family: "Noto Sans KR";
  font-size: 1.5rem;
  font-weight: 900;
  display: none;
}
</style>