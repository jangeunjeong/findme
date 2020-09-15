<template>
  <v-card class="justify-end" width="100%" flat>
    <v-simple-table>
      <template v-slot:default>
        <tbody>
          <tr
            v-for="(interestPerState, index) in showInterestPerStateList"
            :key="interestPerState.state"
          >
            <td class="text-left pt-0">{{ (index + 1) + (nowPage - 1) * 4}}</td>
            <td class="text-left pt-0 pr-12">{{ interestPerState.state }}</td>
            <td
              justify-end
              class="text-right align-center pa-0 pl-12 pr-5 justify-end"
              style="height: 40px !important; width: 250px !important"
            >
              <div style="height: 48px !important;">
                <BarChart :interest="interestPerState.interest" :selectedCnt="selectedCnt" :state="interestPerState.state" :colors="colors"/>
              </div>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <v-divider />
    <v-pagination v-model="nowPage" class="pt-9 mb-0 pb-0" :length="endPage"></v-pagination>
  </v-card>
</template>

<script>
import BarChart from "../components/BarChart";

export default {
  props: [
    "firstLang",
    "secondLang",
    "thirdLang",
    "fourthLang",
    "fifthLang",
    "colors",
    "sortValue",
    "totalGeoValue",
    "selectedCnt"
  ],
  components: { BarChart },
  data() {
    return {
      state: [],
      firstStackedValue: [],
      secondStackedValue: [],
      thirdStackedValue: [],
      fourthStackedValue: [],
      fifthStackedValue: [],
      interestPerStateList: [],
      showInterestPerStateList: [],
      startPage: 0,
      nowPage: 0,
      totalPage: 0,
      endPage: 0
    };
  },
  watch: {
    nowPage(e) {
      const nowStart = (e - 1) * 4;

      this.showInterestPerStateList = this.interestPerStateList.slice(
        nowStart,
        nowStart + 4
      );
    }
  },
  methods: {
    init() {
      if (this.sortValue == this.firstLang) {
        this.totalGeoValue.sort((a, b) => {
          for (let i = 0; i < this.totalGeoValue.length; i++) {
            return a[1] < b[1] ? 1 : a[1] > b[1] ? -1 : 0;
          }
        });
      } else if (this.sortValue == this.secondLang) {
        this.totalGeoValue.sort((a, b) => {
          for (let i = 0; i < this.totalGeoValue.length; i++) {
            return a[2] < b[2] ? 1 : a[2] > b[2] ? -1 : 0;
          }
        });
      } else if (this.sortValue == this.thirdLang) {
        this.totalGeoValue.sort((a, b) => {
          for (let i = 0; i < this.totalGeoValue.length; i++) {
            return a[3] < b[3] ? 1 : a[3] > b[3] ? -1 : 0;
          }
        });
      } else if (this.sortValue == this.fourthLang) {
        this.totalGeoValue.sort((a, b) => {
          for (let i = 0; i < this.totalGeoValue.length; i++) {
            return a[4] < b[4] ? 1 : a[4] > b[4] ? -1 : 0;
          }
        });
      } else if (this.sortValue == this.fifthLang) {
        this.totalGeoValue.sort((a, b) => {
          for (let i = 0; i < this.totalGeoValue.length; i++) {
            return a[5] < b[5] ? 1 : a[5] > b[5] ? -1 : 0;
          }
        });
      }

      if (this.selectedCnt == 2) {
        for (let i = 0; i < this.totalGeoValue.length; i++) {
          this.interestPerStateList.push({
            state: this.totalGeoValue[i][0],
            interest: [
              {
                name: this.firstLang,
                data: this.totalGeoValue[i][1]
              },
              {
                name: this.secondLang,
                data: this.totalGeoValue[i][2]
              }
            ]
          });
        }
      } else if (this.selectedCnt == 3) {
        for (let i = 0; i < this.totalGeoValue.length; i++) {
          this.interestPerStateList.push({
            state: this.totalGeoValue[i][0],
            interest: [
              {
                name: this.firstLang,
                data: this.totalGeoValue[i][1]
              },
              {
                name: this.secondLang,
                data: this.totalGeoValue[i][2]
              },
              {
                name: this.thirdLang,
                data: this.totalGeoValue[i][3]
              }
            ]
          });
        }
      } else if (this.selectedCnt == 4) {
        for (let i = 0; i < this.totalGeoValue.length; i++) {
          this.interestPerStateList.push({
            state: this.totalGeoValue[i][0],
            interest: [
              {
                name: this.firstLang,
                data: this.totalGeoValue[i][1]
              },
              {
                name: this.secondLang,
                data: this.totalGeoValue[i][2]
              },
              {
                name: this.thirdLang,
                data: this.totalGeoValue[i][3]
              },
              {
                name: this.thirdLang,
                data: this.totalGeoValue[i][4]
              }
            ]
          });
        }
      } else if (this.selectedCnt == 5) {
        for (let i = 0; i < this.totalGeoValue.length; i++) {
          this.interestPerStateList.push({
            state: this.totalGeoValue[i][0],
            interest: [
              {
                name: this.firstLang,
                data: this.totalGeoValue[i][1]
              },
              {
                name: this.secondLang,
                data: this.totalGeoValue[i][2]
              },
              {
                name: this.thirdLang,
                data: this.totalGeoValue[i][3]
              },
              {
                name: this.thirdLang,
                data: this.totalGeoValue[i][4]
              },
              {
                name: this.thirdLang,
                data: this.totalGeoValue[i][5]
              }
            ]
          });
        }
      }

      this.showInterestPerStateList = this.interestPerStateList.slice(
        (this.startPage - 1) * 4,
        4
      );
    },
    paginationInit() {
      this.startPage = 1;
      this.nowPage = 1;
      this.totalPage = Math.ceil(this.interestPerStateList.length / 4);
      this.endPage =
        this.totalPage >= 5 * this.startPage
          ? 5 * this.startPage
          : 4 * this.startPage;
    }
  },
  mounted() {
    this.paginationInit();
    this.init();
  }
};
</script>
<style scoped>
.apexcharts-inner.apexcharts-graphical {
  transform: translate(27, 7) !important;
}
</style>