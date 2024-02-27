<template>
  <div id="centerRight2">
    <div class="bg-color-black">
      <div class="d-flex pt-2 pl-2">
        <span>
          <icon name="align-left" class="text-icon"></icon>
        </span>
        <span class="fs-xl text mx-2" style="font-size: 10px;font-weight: bold;">Répartition des Catégories</span>
      </div>
      <div class="d-flex ai-center flex-column body-box">
        <dv-capsule-chart class="dv-cap-chart" :config="config" v-bind:key="this.config.data[1].value"/>
        <dv-active-ring-chart :config="configTwo" style="width:300px;height:250px" v-bind:key="this.configTwo.data[0].value"/>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      config: {
        data: [{
            name: 'aa',
            value: 167
          },
          {
            name: 'bb',
            value: 67
          },
          {
            name: 'cc',
            value: 123
          },
          {
            name: 'ss',
            value: 55
          },
          {
            name: 'cc',
            value: 98
          },
          {
            name: 'cc',
            value: 98
          }]
      },
      configTwo: {
        data: [],
        radius: '60%',
        activeRadius: '70%',
      }
    }
  },
  async mounted(){
        const res = await this.$http.get('myDviz/centerRight');
        this.$set(this.config,'data',res.data.countCategoriesList)
        this.$set(this.configTwo,'data',res.data.countCategoriesList)
  },
  components: {

  }
}
</script>

<style lang="scss" scoped>
#centerRight2 {
  $box-height: 410px;
  $box-width: 340px;
  padding: 5px;
  height: $box-height;
  width: $box-width;
  border-radius: 5px;
  .bg-color-black {
    padding: 5px;
    height: $box-height;
    width: $box-width;
    border-radius: 10px;
  }
  .text {
    color: #c3cbde;
  }
  .body-box {
    border-radius: 10px;
    overflow: hidden;
    .dv-cap-chart {
      width: 100%;
      height: 160px;
    }
  }
}
</style>
