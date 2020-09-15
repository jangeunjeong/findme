<template>
  <v-container fill-height class="justify-center">
    <v-card width="300px">
      <v-flex class="pa-4" id="test">
        <v-flex d-flex>
          <v-flex>강원도</v-flex>
        </v-flex>
        <v-flex d-flex>
          <v-flex>
            <v-list dense class="pl-0">
              <v-list-item dense d-flex class="pl-0 pr-6">
                <v-list-item-subtitle>자바</v-list-item-subtitle>
                <v-list-item-title class="text-right">28%</v-list-item-title>
              </v-list-item>
              <v-list-item dense d-flex class="pl-0 pr-6">
                <v-list-item-subtitle>파이썬</v-list-item-subtitle>
                <v-list-item-title class="text-right">24%</v-list-item-title>
              </v-list-item>
              <v-list-item dense d-flex class="pl-0 pr-6">
                <v-list-item-subtitle>C</v-list-item-subtitle>
                <v-list-item-title class="text-right">26%</v-list-item-title>
              </v-list-item>
              <v-list-item dense d-flex class="pl-0 pr-6">
                <v-list-item-subtitle>C++</v-list-item-subtitle>
                <v-list-item-title class="text-right">17%</v-list-item-title>
              </v-list-item>
              <v-list-item dense d-flex class="pl-0 pr-6">
                <v-list-item-subtitle>자바스크립트</v-list-item-subtitle>
                <v-list-item-title class="text-right">5%</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-flex>
          <div class="pa-0 pr-3 ma-0">
            <DonutChart />
          </div>
        </v-flex>
      </v-flex>
    </v-card>
  </v-container>
</template>

<script>
import DonutChart from "../../components/DonutChart";

export default {
  components: {
    DonutChart
  },
  data() {
    return {};
  },
  methods: {}
};
</script>
<style>
div.pl-0.v-list-item.v-list-item--dense.theme--light {
  min-height: 20px !important;
}
</style>