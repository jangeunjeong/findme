<template>
  <apexchart type="bar" height="350" :options="chartOptions" :series="value"></apexchart>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "ColumnChart",
  components: {
    apexchart: VueApexCharts
  },
  props: ["value", "category"],
  data() {
    return {
      series: [],
      chartOptions: {
        chart: {
          type: "bar",
          height: 350
        },
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: "55%",
            endingShape: "rounded"
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          show: true,
          width: 2,
          colors: ["transparent"]
        },
        fill: {
          opacity: 1
        },
        xaxis: {
          categories: this.category
        }
      }
    };
  },
  methods: {},
  mounted() {}
};
</script>
<style sccped>
</style>