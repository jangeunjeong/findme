<template>
  <v-container fill-height class="pa-0 ma-0" style="width: 50px !important">
    <v-flex id="test">
      <apexchart type="donut" width="50" height="50" :options="data.chartOptions" :series="data.series"></apexchart>
    </v-flex>
  </v-container>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "DonutChart",
  components: {
    apexchart: VueApexCharts
  },
  data() {
    return {
      data: {
        series: [44, 55, 41, 17, 15],
        chartOptions: {
          chart: {
            type: "donut",
            animations: {
              enabled: false
            },
            sparkline: {
              enabled: true
            }
          },
          dataLabels: {
            enabled: false
          },
          tooltip: {
            enabled: false
          },
          states: {
            normal: {
              filter: {
                type: "none"
              }
            },
            hover: {
              filter: {
                type: "none"
              }
            },
            active: {
              allowMultipleDataPointsSelection: false,
              filter: {
                type: "none"
              }
            }
          },
          legend: {
            show: false
          },
          colors: this.colors
        }
      }
    };
  },
  methods: {}
};
</script>