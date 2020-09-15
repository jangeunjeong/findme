<template>
  <v-container fill-height class="justify-center">
    <v-card width="300px">
      <div id="app">
        <div id="chart-container">
          <fusioncharts
            :type="type"
            :width="width"
            :height="height"
            :dataformat="dataFormat"
            :dataSource="dataSource"
          ></fusioncharts>
        </div>
      </div>
    </v-card>
  </v-container>
</template>

<script>
import Vue from "vue";
import VueFusionCharts from "vue-fusioncharts";
import FusionCharts from "fusioncharts";
import Column2D from "fusioncharts/fusioncharts.charts";
import FusionTheme from "fusioncharts/themes/fusioncharts.theme.fusion";

Vue.use(VueFusionCharts, FusionCharts, Column2D, FusionTheme);

export default {
  components: {},
  data() {
    return {
      type: "column2d",
      renderAt: "chart-container",
      width: "600",
      height: "400",
      dataFormat: "json",
      dataSource: {
        chart: {
          caption: "Global Population",
          theme: "fusion",
          formatNumberScale: "0",
          numberSuffix: "M",
          showLabels: "1",
          useSNameInToolTip: "1",
          useSNameInLabels: "0"
        },
        colorrange: {
          color: [
            {
              minvalue: "0",
              maxvalue: "100",
              code: "#D0DFA3",
              displayValue: "< 100M"
            },
            {
              minvalue: "100",
              maxvalue: "500",
              code: "#B0BF92",
              displayValue: "100-500M"
            },
            {
              minvalue: "500",
              maxvalue: "1000",
              code: "#91AF64",
              displayValue: "500M-1B"
            },
            {
              minvalue: "1000",
              maxvalue: "5000",
              code: "#A9FF8D",
              displayValue: "> 1B"
            }
          ]
        },
        data: [
          {
            id: "NA",
            value: "515"
          },
          {
            id: "SA",
            value: "373"
          },
          {
            id: "AS",
            value: "3875"
          },
          {
            id: "EU",
            value: "727"
          },
          {
            id: "AF",
            value: "885"
          },
          {
            id: "AU",
            value: "32"
          }
        ]
      }
    };
  },
  methods: {}
};
</script>
<style>
div.pl-0.v-list-item.v-list-item--dense.theme--light {
  min-height: 20px !important;
}
</style>