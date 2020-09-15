<template>
  <v-card class="card mr-3" width="100%" height="100%" flat>
    <v-card-text class="pa-0">
      <v-flex>
        <GChart type="GeoChart" :data="chartData" :options="chartOptions" />
      </v-flex>
    </v-card-text>
  </v-card>
</template>

<script>
import { GChart } from "vue-google-charts";

export default {
  name: "TotalGeoChart",
  components: {
    GChart
  },
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
  data() {
    return {
      tmpData: [],
      data: [],
      chartData: [],
      chartOptions: {
        region: "KR",
        resolution: "provinces",
        displayMode: "auto",
        colorAxis: {
          minValue: 1,
          maxValue: 5,
          colors: this.colors
        },
        tooltip: {
          // isHtml: true,
          // allowHtml: true
        },
        legend: "none",
        // backgroundColor: { fill: "#FFFFFF", stroke: "#FFFFFF", strokeWidth: 0 },
        datalessRegionColor: "#f5f5f5"
      }
    };
  },
  methods: {},
  mounted() {
    this.chartData.push(["state", "관심도"]);

    for (let i = 0; i < this.totalGeoValue.length; i++) {
      var maxNum = 0;
      var vState = this.totalGeoValue[i][0];

      for (let j = 1; j < this.totalGeoValue[i].length; j++) {
        if (this.totalGeoValue[i][j] > maxNum) {
          maxNum = this.totalGeoValue[i][j];

          if (this.totalGeoValue[i][0] == "서울특별시") {
            vState = "서울";
          } else if (this.totalGeoValue[i][0] == "제주특별자치도") {
            vState = "제주도";
          }

          this.chartData.push([
            {
              v: vState,
              f: this.totalGeoValue[i][0]
            },
            {
              v: j,
              f: maxNum
            }
          ]);
        }
      }
    }
  }
};
</script>
<style scoped>
.apexcharts-inner.apexcharts-graphical {
  transform: translate(10px, 7px) !important;
}
</style>