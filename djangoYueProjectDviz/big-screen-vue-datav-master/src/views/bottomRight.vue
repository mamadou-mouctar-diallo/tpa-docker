<template>
  <div id="bottomRight">
    <div class="bg-color-black">
      <div class="d-flex pt-2 pl-2">
        <span>
          <icon name="chart-area" class="text-icon"></icon>
        </span>
        <div class="d-flex">
          <span class="fs-xl text mx-2" style="font-size: 20px;font-weight: bold;">Prix des marques de Voitures</span>
          <div class="decoration2">
            <dv-decoration-2 :reverse="true" style="width:5px;height:6rem;" />
          </div>
        </div>
      </div>
      <div class="row_list">
<!--          <ul class="car_rank" style="width: 100%;height: 420px;overflow:auto;">-->
<!--            <li style="font-size: 20px">-->
<!--              <div>Marque</div>-->
<!--              <div>Nom</div>-->
<!--              <div>Prix</div>-->
<!--            </li>-->
<!--            <li style="">-->
<!--              <div>Marque</div>-->
<!--              <div>Nom</div>-->
<!--              <div>Prix</div>-->
<!--            </li>-->
<!--          </ul>-->
        <div>
        <BottomRightChart />
      </div>
      </div>
    </div>
  </div>
</template>

<script>
import BottomRightChart from '@/components/echart/bottom/bottomRightChart'

export default {
  components: {
      BottomRightChart,
  }
};
</script>

<style lang="scss" class>
$box-height: 520px;
$box-width: 100%;
#bottomRight {
  padding: 14px 16px;
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
  //underline
  .decoration2 {
    position: absolute;
    right: 0.125rem;
  }
  .chart-box {
    margin-top: 16px;
    width: 170px;
    height: 170px;
    .active-ring-name {
      padding-top: 10px;
    }
  }
  .row_list{
    list-style: none;
    .car_rank::-webkit-scrollbar{
      display: none;
    }
    .car_rank li{
      display: grid;
      -ms-grid-columns: 100px 150px 180px 120px 110px 100px;
      grid-template-columns: 100px 150px 180px 120px 110px 100px;
      cursor: pointer;
      margin-left: 23px;
      text-align: center;
      line-height: 30px;
    }
  }
}
</style>