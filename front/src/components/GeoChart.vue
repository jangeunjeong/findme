<template>
  <v-card class="card mr-3" width="100%" height="100%" flat>
    <v-card-text class="pa-0">
      <v-flex>
        <GChart type="GeoChart" :data="chartData" :options="chartOptions" :key="value" />
      </v-flex>
    </v-card-text>
  </v-card>
</template>

<script>
import { GChart } from "vue-google-charts";

export default {
  name: "GeoChart",
  components: {
    GChart
  },
  props: {
    value: {
      type: Array
    },
    colors: {
      type: String
    }
  },
  computed: {},
  data() {
    return {
      chartData: [],
      chartOptions: {
        region: "KR",
        resolution: "provinces",
        colorAxis: {
          minValue: 0
        },
        colors: this.colors,
        height: "100px",
        legend: "none"
      }
    };
  },
  methods: {
    makeData() {
      this.chartData.push(this.value[0]);

      var minValue = 10000000;
      var maxValue = 0;

      for (let i = 1; i < this.value.length; i++) {
        if (minValue > this.value[i][1]) {
          minValue = this.value[i][1];
        }

        if (maxValue < this.value[i][1]) {
          maxValue = this.value[i][1];
        }

        this.chartData.push([
          {
            v:
              this.value[i][0] == "서울특별시"
                ? "서울"
                : this.value[i][0] == "제주특별자치도"
                ? "제주도"
                : this.value[i][0],
            f: this.value[i][0]
          },
          {
            v: this.value[i][1],
            f: this.value[i][1]
          }
        ]);
      }
      // minValue - Math.ceil(minValue / 5)
      // Math.ceil(maxValue / 2);
      // this.chartOptions.colorAxis.minValue = minValue;
      // this.chartOptions.colorAxis.maxValue = maxValue;
    }
  },
  mounted() {
    this.makeData();
  }
};
</script>