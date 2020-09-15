<template>
  <v-card class="card pt-3" width="100%" flat>
    <v-card-text class="pa-0">
      <v-flex id="chart-line2" class="pb-0 mb-0">
        <apexchart type="line" height="250" :options="chartOptions" :series="series"></apexchart>
      </v-flex>
      <v-flex id="chart-line" class="pt-0 mt-0">
        <apexchart type="area" height="130" :options="chartOptionsLine" :series="seriesLine"></apexchart>
      </v-flex>
    </v-card-text>
  </v-card>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "BrushChart",
  components: {
    apexchart: VueApexCharts
  },
  props: [
    "firstLang",
    "firstValue",
    "secondLang",
    "secondValue",
    "thirdLang",
    "thirdValue",
    "fourthLang",
    "fourthValue",
    "fifthLang",
    "fifthValue",
    "colors"
  ],
  computed: {},
  data() {
    return {
      series: [],
      chartOptions: {
        chart: {
          id: "chart2",
          type: "line",
          toolbar: {
            autoSelected: "pan",
            show: false
          },
          animations: {
            enabled: false
          },
          events: {
            // beforeMount: () => {
            //   this.makeSeries();
            // }
          }
        },
        noData: {
          text: "Loading..."
        },
        colors: this.colors,
        stroke: {
          width: 3
        },
        dataLabels: {
          enabled: false
        },
        fill: {
          opacity: 1
        },
        markers: {
          size: 4
        },
        xaxis: {
          type: "datetime"
        },
        yaxis: {},
        legend: {
          offsetY: top,
          itemMargin: {
            horizontal: 5,
            vertical: 0
          }
        }
      },
      seriesLine: [],
      chartOptionsLine: {
        chart: {
          id: "chart1",
          height: 130,
          type: "area",
          animations: {
            enabled: false
          },
          brush: {
            target: "chart2",
            enabled: true
          },
          selection: {
            enabled: true,
            xaxis: {
              min: new Date(this.firstValue[0][0]).getTime(),
              max: new Date(
                this.firstValue[this.firstValue.length - 1][0]
              ).getTime()
            }
          }
        },
        noData: {
          text: "Loading..."
        },
        colors: this.colors,
        fill: {
          type: "gradient",
          gradient: {
            opacityFrom: 0.91,
            opacityTo: 0.1
          }
        },
        xaxis: {
          type: "datetime",
          tooltip: {
            enabled: false
          }
        },
        yaxis: {
          tickAmount: 2
        },
        legend: {
          show: false
        }
      }
    };
  },
  methods: {
    makeSeries() {
      if (
        this.firstLang != "" &&
        this.firstLang != null &&
        this.firstLang != "undefined"
      ) {
        this.series.push({
          name: this.firstLang,
          data: this.firstValue
        });
      }

      if (
        this.secondLang != "" &&
        this.secondLang != null &&
        this.secondLang != "undefined"
      ) {
        this.series.push({
          name: this.secondLang,
          data: this.secondValue
        });
      }

      if (
        this.thirdLang != "" &&
        this.thirdLang != null &&
        this.thirdLang != "undefined"
      ) {
        this.series.push({
          name: this.thirdLang,
          data: this.thirdValue
        });
      }

      if (
        this.fourthLang != "" &&
        this.fourthLang != null &&
        this.fourthLang != "undefined"
      ) {
        this.series.push({
          name: this.fourthLang,
          data: this.fourthValue
        });
      }

      if (
        this.fifthLang != "" &&
        this.fifthLang != null &&
        this.fifthLang != "undefined"
      ) {
        this.series.push({
          name: this.fifthLang,
          data: this.fifthValue
        });
      }
      this.seriesLine = this.series;
    }
  },
  created() {
    // this.makeSeries();
    if (
      this.firstLang != "" &&
      this.firstLang != null &&
      this.firstLang != "undefined"
    ) {
      this.series.push({
        name: this.firstLang,
        data: this.firstValue
      });
    }
    if (
      this.secondLang != "" &&
      this.secondLang != null &&
      this.secondLang != "undefined"
    ) {
      this.series.push({
        name: this.secondLang,
        data: this.secondValue
      });
    }
    if (
      this.thirdLang != "" &&
      this.thirdLang != null &&
      this.thirdLang != "undefined"
    ) {
      this.series.push({
        name: this.thirdLang,
        data: this.thirdValue
      });
    }
    if (
      this.fourthLang != "" &&
      this.fourthLang != null &&
      this.fourthLang != "undefined"
    ) {
      this.series.push({
        name: this.fourthLang,
        data: this.fourthValue
      });
    }
    if (
      this.fifthLang != "" &&
      this.fifthLang != null &&
      this.fifthLang != "undefined"
    ) {
      this.series.push({
        name: this.fifthLang,
        data: this.fifthValue
      });
    }
    this.seriesLine = this.series;
    console.log("hello");
  }
};
</script>
<style scoped>
</style>