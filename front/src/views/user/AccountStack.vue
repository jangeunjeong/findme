<template>
  <div>
    <div class="example-3d">
      <swiper class="swiper" :options="swiperOption">
        <swiper-slide v-for="(slide,i) in slides" :key="i" class="toLang"
          style="font-size: 2rem; font-weight: 900; text-shadow: 1px 1px #000; box-shadow: 5px 5px 10px #ccc; border: 5px outset white;">
          <a :href="'https://i02b204.p.ssafy.io/lang/' + slide.id">
            <v-img :src="slide.bgs" />
          </a>
        </swiper-slide>
        <div class="swiper-pagination" slot="pagination"></div>
      </swiper>
    </div>
  </div>
</template>

<script>
  export default {
    name: "AccountStack",
    data() {
      return {
        swiperOption: {
          effect: "coverflow",
          grabCursor: true,
          centeredSlides: true,
          slidesPerView: "auto",
          coverflowEffect: {
            rotate: 50,
            stretch: 0,
            depth: 100,
            modifier: 1,
            slideShadows: true
          },
          pagination: {
            el: ".swiper-pagination"
          }
        },
      };
    },
    methods: {},
    props: {
      slides: Array,
    },
  };
</script>

<style lang="scss">
  .example-3d {
    width: 100%;
    height: 400px;
    // padding-top: 50px;
    padding-bottom: 60px;
  }

  .swiper {
    height: 100%;
    width: 100%;

    .swiper-slide {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 300px;
      height: 300px;
      text-align: center;
      font-weight: bold;
      background-position: center;
      background-size: cover;
    }

    // .swiper-pagination {
    //   /deep/ .swiper-pagination-bullet.swiper-pagination-bullet-active {
    //     background-color: white;
    //   }
    // }

    .toLang {
      width: 250px;
      height: 250px;
      display: inline-block;
      background-color: white !important;
      background-size: contain !important;
      background-position: center !important;
      background-repeat: no-repeat !important;
      // top: 0px;
      position: relative;
      border-radius: 4px;
      // padding: 32px 24px;
      border: 1px solid #f2f8f9;

    }
  }
</style>