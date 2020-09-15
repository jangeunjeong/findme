<template>
  <v-card class="justify-end" width="100%" flat height="100%">
    <v-simple-table>
      <template v-slot:default>
        <tbody>
          <tr
            v-for="(interestPerState, index) in showInterestPerStateList"
            :key="interestPerState.state"
          >
            <td class="text-left">{{ (index + 1) + (nowPage - 1) * 6}}</td>
            <td class="text-left">{{ interestPerState.state }}</td>
            <td class="text-right">{{ interestPerState.interest }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <v-divider />
    <v-pagination v-model="nowPage" class="mt-5 mb-0 pb-0" :length="endPage"></v-pagination>
  </v-card>
</template>

<script>
export default {
  props: {
    value: {
      type: Array
    },
    title: {
      type: String
    }
  },
  data() {
    return {
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
      const nowStart = (e - 1) * 6;

      this.showInterestPerStateList = this.interestPerStateList.slice(
        nowStart,
        nowStart + 6
      );
    }
  },
  methods: {
    init() {
      for (let i = 1; i < this.value.length; i++) {
        if (this.value[i][0] === "서울") {
          this.interestPerStateList.push({
            state: this.value[i][0] + "특별시",
            interest: this.value[i][1]
          });
        } else if (this.value[i][0] === "제주도") {
          this.interestPerStateList.push({
            state: "제주특별자치도",
            interest: this.value[i][1]
          });
        } else {
          this.interestPerStateList.push({
            state: this.value[i][0],
            interest: this.value[i][1]
          });
        }
      }
      this.showInterestPerStateList = this.interestPerStateList.slice(
        (this.startPage - 1) * 6,
        5
      );
    },
    paginationInit() {
      this.startPage = 1;
      this.nowPage = 1;
      this.totalPage = Math.ceil(this.interestPerStateList.length / 6);
      this.endPage =
        this.totalPage >= 4 * this.startPage
          ? 4 * this.startPage
          : 3 * this.startPage;
    }
  },
  mounted() {
    this.paginationInit();
    this.init();
  }
};
</script>