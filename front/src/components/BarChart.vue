<template>
  <apexchart type="bar" height="21" :options="chartOptions" :series="series"></apexchart>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "BarChart",
  components: {
    apexchart: VueApexCharts
  },
  props: ["interest", "selectedCnt", "state", "colors"],
  data() {
    return {
      nowState: this.state,
      series: [],
      chartOptions: {
        chart: {
          type: "bar",
          stacked: true,
          stackType: "100%",
          toolbar: {
            autoSelected: "pan",
            show: false
          },
          title: {
            show: false
          },
          animations: {
            enabled: false
          },
          offsetX: 0,
          offsetY: 13,
          sparkline: {
            enabled: true
          }
        },
        tooltip: {
          // custom: function({ series, seriesIndex, dataPointIndex, w }) {
          //   return (
          //     "<v-flex d-flex class='justify-start text-left align-start pa-3'>" +
          //     "<v-flex d-flex>" +
          //     w.config +
          //     ": " +
          //     series[seriesIndex][dataPointIndex] +
          //     "<br />" +
          //     series[seriesIndex][dataPointIndex] +
          //     "<br />" +
          //     series[seriesIndex][dataPointIndex] +
          //     "<br />" +
          //     "</v-flex>" +
          //     "</v-flex>"
          //   );
          // },
          x: {
            show: false
          },
          y: {
            title: {
              formatter: seriesName => seriesName
            }
          },
          z: {
            formatter: undefined,
            title: "Size: 100"
          },
          fixed: {
            enabled: true,
            offsetX: -400,
            offsetY: 0
          }
        },
        colors: this.colors,
        stroke: {
          width: 1,
          colors: ["#fff"]
        },
        dataLabels: {
          enabled: false
        },
        states: {
          hover: {
            filter: {
              type: "none"
            }
          }
        },
        // tooltip: {
        //   fixed: {
        //     enabled: false
        //   },
        //   x: {
        //     show: false
        //   },
        //   marker: {
        //     show: false
        //   }
        // },
        grid: {
          enabled: false,
          show: false
        },
        plotOptions: {
          bar: {
            horizontal: true
          }
        },
        xaxis: {
          enabled: false,
          show: false,
          labels: {
            show: false
          },
          axisBorder: {
            show: false
          },
          axisTicks: {
            show: false
          }
        },
        yaxis: {
          labels: {
            show: false
          }
        },
        fill: {
          opacity: 1
        },
        legend: {
          show: false
        }
      }
    };
  },
  methods: {},
  mounted() {
    if (this.selectedCnt == 2) {
      this.series = [
        {
          name: this.interest[0].name,
          data: [this.interest[0].data]
        },
        {
          name: this.interest[1].name,
          data: [this.interest[1].data]
        }
      ];
    } else if (this.selectedCnt == 3) {
      this.series = [
        {
          state: this.state,
          name: this.interest[0].name,
          data: [this.interest[0].data]
        },
        {
          name: this.interest[1].name,
          data: [this.interest[1].data]
        },
        {
          name: this.interest[2].name,
          data: [this.interest[2].data]
        }
      ];
    } else if (this.selectedCnt == 4) {
      this.series = [
        {
          name: this.interest[0].name,
          data: [this.interest[0].data]
        },
        {
          name: this.interest[1].name,
          data: [this.interest[1].data]
        },
        {
          name: this.interest[2].name,
          data: [this.interest[2].data]
        },
        {
          name: this.interest[3].name,
          data: [this.interest[3].data]
        }
      ];
    } else if (this.selectedCnt == 5) {
      this.series = [
        {
          name: this.interest[0].name,
          data: [this.interest[0].data]
        },
        {
          name: this.interest[1].name,
          data: [this.interest[1].data]
        },
        {
          name: this.interest[2].name,
          data: [this.interest[2].data]
        },
        {
          name: this.interest[3].name,
          data: [this.interest[3].data]
        },
        {
          name: this.interest[4].name,
          data: [this.interest[4].data]
        }
      ];
    }
    console.log(this.series)
  }
};
</script>
<style sccped>
</style>