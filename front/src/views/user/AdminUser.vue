<template>
  <div class="">
    <v-card>
    <v-card-title>
      회원관리
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="desserts"
      :search="search"
      :single-expand="singleExpand"
      :expanded.sync="expanded"
      item-key="name"
      show-expand
      multi-sort
      class="elevation-1"
    >
    <template v-slot:item.calories="{ item }">
      <v-chip :color="getColor(item.calories)" dark>{{ item.calories }}</v-chip>
    </template>
    <!-- <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>Expandable Table</v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>
    </template> -->
    <template v-slot:expanded-item="{ headers, item }">
      <td :colspan="headers.length">More info about {{ item.name }}</td>
    </template>
    </v-data-table>
  </v-card>
  </div>
</template>

<script>
export default {
data () {
      return {
        search: '',
        headers: [
          {
            text: 'ID',
            align: 'start',
            sortable: false,
            value: 'name',
          },
          { text: 'Calories', value: 'calories' },
          { text: '언어', value: 'stacks' },
          { text: '리뷰', value: 'reviews' },
        ],
        desserts: [
          {
            name: 'qkrwjdwn',
            calories: 159,
            stacks: ['python'],
            reviews: 0,
          },
          {
            name: 'whtnwl',
            calories: 237,
            stacks: ['python', 'javascript'],
            reviews: 0,
          },
          {
            name: 'wkddmswjd',
            calories: 262,
            stacks: ['java'],
            reviews: 1,
          },
          {
            name: 'rlarldms',
            calories: 305,
            stacks: ['java', 'python'],
            reviews: 2,
          },
          {
            name: 'dlgodls',
            calories: 356,
            stacks: ['c++'],
            reviews: 1,
            jobs: 9,
          },
          {
            name: 'qkrghddms',
            calories: 375,
            stacks: ['c++', 'java', 'javascript'],
            reviews: 1,
          },
          {
            name: 'dlqudwn',
            calories: 392,
            stacks: ['java'],
            reviews: 1,
          },
          {
            name: 'rlarldms',
            calories: 408,
            stacks: ['javascript'],
            reviews: 0,
          },
          {
            name: 'qkrtkddnjs',
            calories: 452,
            stacks: [],
            reviews: 0,
          },
          {
            name: 'dhdbrud',
            calories: 518,
            stacks: [],
            reviews: 0,
          },
        ],
      }
    },
    methods: {
      getColor (calories) {
        if (calories > 400) return 'red'
        else if (calories > 200) return 'orange'
        else return 'green'
      },
    },
}
</script>

<style>

</style>