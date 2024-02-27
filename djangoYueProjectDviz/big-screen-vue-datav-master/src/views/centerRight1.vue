<template>
  <div id="centerRight1">
    <div class="bg-color-black">
      <div class="d-flex pt-2 pl-2">
        <span>
          <icon name="chart-line" class="text-icon"></icon>
        </span>
        <div class="d-flex">
          <span class="fs-xl text mx-2" style="font-size: 10px;font-weight: bold;">Classement si deuxième voiture.</span>
          <a v-on:click="oneCarClick" href="#"><span style="color: deepskyblue">OneCar</span></a>
          <a v-on:click="oneCarClick" href="#"><span style="color: #33cea0">TwoCar</span></a>
        </div>
      </div>
      <div class="d-flex jc-center body-box">
        <dv-scroll-board class="dv-scr-board" style="width: 98%;font-size: 5px" :config="config" v-bind:key="config.data[0][1]" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      config: {
        header: ['Taux','Age','Sexe','Categorie'],
        data: [
          ['27','827','M', "<span  class='colorGrass'>citadine%</span>"],
          ['28','827','F',"<span  class='colorRed'>citadine</span>"],
        ],
        rowNum: 7, //nb row
        headerHeight: 35,
        headerBGC: '#0f1325', //headwe table
        oddRowBGC: '#0f1325', // row impair
        evenRowBGC: '#171c33', //row pair
        index: true,
        cellStyle: {
          paddingLeft: '0px',
          paddingRight: '0px',
        },
        columnWidth: [5,60,52,60,110],
        align: ['left'],
        textalign:'left'
      }
    }
  },

  methods:{
    async oneCarClick(){
      const res = await this.$http.get('myDviz/centerRightChange/0');
      this.$set(this.config,'data',res.data.realData)
    },
    async twoCarClick(){
      const res = await this.$http.get('myDviz/centerRightChange/1');
      this.$set(this.config,'data',res.data.realData)
    }
  },
  async mounted(){
        const res = await this.$http.get('myDviz/centerRightChange/1');
        // console.log(res)
        this.$set(this.config,'data',res.data.realData)
  },
}
</script>

<style lang="scss" scoped>
$box-height: 410px;
$box-width: 300px;
#centerRight1 {
  padding: 16px;
  padding-top: 20px;
  height: $box-height;
  width: $box-width;
  border-radius: 5px;
  .bg-color-black {
    height: $box-height - 30px;
    border-radius: 10px;
  }
  .text {
    color: #c3cbde;
  }
  .body-box {
    border-radius: 10px;
    overflow: hidden;
    .dv-scr-board {
      width: 300px;
      height: 340px;
    }
  }
}
</style>
