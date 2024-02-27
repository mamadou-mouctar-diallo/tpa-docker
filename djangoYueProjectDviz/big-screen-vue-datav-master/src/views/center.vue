<template>
  <div id="center">
    <div class="up">
      <div class="bg-color-black item">
        <p class="ml-3 colorBlue fw-b fs-xl">Taux les plus Quantité</p>
        <div style="text-align: center;line-height: 2">
            {{this.result.hightaux}}
        </div>
      </div>
      <div class="bg-color-black item">
        <p class="ml-3 colorBlue fw-b fs-xl">Marque Véhicules les plus élevés</p>
        <div style="text-align: center;line-height: 2">
            {{this.result.HighVololum}}
        </div>
      </div>
      <div class="bg-color-black item">
        <p class="ml-3 colorBlue fw-b fs-xl">coutEnergie moindre</p>
        <div style="text-align: center;line-height: 2">
            {{this.result.lowCoutEnergie}}
        </div>
      </div>
      <div class="bg-color-black item">
        <p class="ml-3 colorBlue fw-b fs-xl">Nombre les plus Quantité de Taux</p>
        <div style="text-align: center;line-height: 2">
            {{this.result.countHighTaux}}
        </div>
      </div>
      <div class="bg-color-black item">
        <p class="ml-3 colorBlue fw-b fs-xl">Nombre Marque Véhicules le plus élevé</p>
        <div style="text-align: center;line-height: 2">
            {{this.result.countHighVololum}}
        </div>
      </div>
      <div class="bg-color-black item">
        <p class="ml-3 colorBlue fw-b fs-xl">RejetsCO2 moindre coutEnergie</p>
        <div style="text-align: center;line-height: 2">
            {{this.result.lowCoutEnergierejetsCO2}}
        </div>
      </div>

    </div>
    <div class="down">
      <div class="ranking bg-color-black">
        <span>
          <icon name="chart-pie" class="text-icon"></icon>
        </span>
        <span class="fs-xl text mx-2 mb-1 pl-3" style="font-size: 10px;font-weight: bold;">Top 10 Tableau des quantités de CO2 coutEnergie</span><br>
<!--        <span class="fs-xl text mx-2 mb-1 pl-3" style="font-size: 10px;">(All:{{this.nbClassement}} classements)</span>-->
        <dv-scroll-ranking-board class="dv-scr-rank-board mt-1" :config="ranking" v-bind:key="'coutEnergie of CO2: '+this.ranking.data[0].value"/>
      </div>
      <div class="percent">
        <div class="item bg-color-black">
          <span>Pourcentage (%) de clients possédant la deuxième voiture</span>
          <CenterChart
            :id="rate[0].id"
            :tips="rate[0].tips"
            :colorObj="rate[0].colorData"
          />
        </div>
        <div class="item bg-color-black">
          <span>Pourcentage (%) de clients ne possédant pas la deuxième voiture</span>
          <CenterChart
            :id="rate[1].id"
            :tips="rate[1].tips"
            :colorObj="rate[1].colorData"
          />
        </div>
<!--        <div class="water">-->
<!--          <dv-water-level-pond class="dv-wa-le-po" :config="water" v-bind:key="water.data[0]"/>-->
<!--        </div>-->
      </div>
    </div>
  </div>
</template>

<script>
import CenterChart from '@/components/echart/center/centerChartRate'

export default {
  data() {
    return {
      result:'',
      nbClassement:0,
      ranking: {
        data: [
          {
            name:'defaultBonusMalus',
            value:'1'
          }
        ],
        carousel: 'single',
        unit: ' modeles'
      },
      water: {
        data: [24, 45],
        shape: 'roundRect',
        formatter: '{value}%',
        waveNum: 3
      },

      rate: [
        {
          id: 'centerRate1',
          tips: 60,
          colorData: {
            textStyle: '#3fc0fb',
            series: {
              color: ['#00bcd44a', 'transparent'],
              dataColor: {
                normal: '#03a9f4',
                shadowColor: '#97e2f5'
              }
            }
          }
        },
        {
          id: 'centerRate2',
          tips: 40,
          colorData: {
            textStyle: '#67e0e3',
            series: {
              color: ['#faf3a378', 'transparent'],
              dataColor: {
                normal: '#ff9800',
                shadowColor: '#fcebad'
              }
            }
          }
        }
      ]
    }
  },
  components: {
    CenterChart
  },
  async mounted(){
        const res = await this.$http.get('myDviz/center');
        this.result = res.data;
        // this.nbClassement=res.data.lastsortDict.length
        this.$set(this.ranking,'data',res.data.lastsortDict.slice(0,10))
        // console.log(this.ranking.data)
        this.$set(this.rate[0],'tips',res.data.trueRate)
        this.$set(this.rate[1],'tips',res.data.falseRate)
        // this.$set(this.water,'data',[res.data.other])


  }
};
</script>

<style lang="scss" scoped>
#center {
  display: flex;
  flex-direction: column;
  .up {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    .item {
      border-radius: 6px;
      padding-top: 8px;
      margin-top: 8px;
      width: 32%;
      height: 70px;
      .dv-dig-flop {
        width: 150px;
        height: 30px;
      }
    }
    .fs-xl {
        font-size: 9px;
    }
  }
  .down {
    padding: 6px 4px;
    padding-bottom: 0;
    width: 100%;
    display: flex;
    height: 255px;
    justify-content: space-between;
    .bg-color-black {
      border-radius: 5px;
    }
    .ranking {
      padding: 10px;
      width: 59%;
      .dv-scr-rank-board {
        height: 225px;
      }
    }
    .percent {
      width: 40%;
      display: flex;
      flex-wrap: wrap;
      .item {
        width: 50%;
        height: 120px;
        span {
          margin-top: 8px;
          font-size: 14px;
          display: flex;
          justify-content: center;
        }
      }
      .water {
        width: 100%;
        .dv-wa-le-po {
          height: 120px;
        }
      }
    }
  }
}
</style>
