<template>
  <div>
    <div ref="chart" style="width: 95%;height:480px;place-items: center;" v-bind:key="cdata.lineData[0]"
        @mouseenter="startActon" @mouseleave="cancelAction"></div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      isHover:true,
      cdata: {
        category: [
          "s",
          "sc",
          "z",
          "x",
          "x",
          "x",
          "x",
          "s",
          "x",
          "x",
          "x",
        ],
        lineData: [
            641855000,
            115397700,
            1049653160,
            711399920,
            2519038240,
            263892696,
            557977430,
            182543420,
            294413780,
            196735854,
            22996420,
            947536522,
            178832850,
            135744125,
            44821120,
            11437500,
            28568540,
            21673650,
            16379550
        ],
        barData: [
            13772,
            3630,
            16546,
            21019,
            32551,
            17414,
            32677,
            8329,
            9355,
            8677,
            2558,
            27917,
            7544,
            10592,
            2374,
            1525,
            1807,
            2449,
            1396
        ],
        rateData: []
      }
    };
  },
  components: {
  },
  async mounted() {
    const res = await this.$http.get('myDviz/bottomLeft',{ timeout: 60000 });
    // console.log(res.data)
    this.$set(this.cdata,'category',res.data.marquesDict)
    this.$set(this.cdata,'barData',res.data.prixsVolumesDict)
    this.$set(this.cdata,'lineData',res.data.carsVolumesDict)
  },
  updated() {
    this.initChart()
    this.startDataUpdateInterval()
  },
  methods: {
    initChart(){
      this.myChart = this.$echarts.init(this.$refs.chart)
      const option = {
          tooltip: {
            trigger: "axis",
            backgroundColor: "rgba(255,255,255,0.1)",
            axisPointer: {
              type: "shadow",
              label: {
                show: true,
                backgroundColor: "#7B7DDC"
              }
            }
          },
        dataZoom:[
          {
            type:"slider",
            start:0,
            end:80,
            show:false
          }
        ],
          legend: {
            data: ["Valeur totale", "Nombre total", "yyy"],
            textStyle: {
              color: "#B4B4B4"
            },
            top: "0%"
          },
          grid: {
            x: "8%",
            width: "84%",
            y: "4%",
            left: '10%',
            right: '6%',

          },
          xAxis: {
              data: this.cdata.category,
              axisLine: {
                lineStyle: {
                  color: "#B4B4B4"
                }
              },
              axisLabel:{
                show:true,
                interval:0,
                rotate: 45
              },
              axisTick: {
                show: false
              }
          },
          yAxis: [
            {
              type: 'value',
              splitLine: { show: false },
              axisLine: {
                lineStyle: {
                  color: "#B4B4B4"
                }
              },
              axisLabel: {
                formatter: function (value) {
                  //  Millions pour Y
                  return (value / 1000000).toFixed(1) + 'M';
                }
              }
            },
            {
              splitLine: { show: false },
              axisLine: {
                lineStyle: {
                  color: "#B4B4B4"
                }
              },
              axisLabel: {
                formatter: "{value} "
              }
            }
          ],
          series: [
            {
              name: "Nombre Total",
              type: "line",
              smooth: true,
              showAllSymbol: true,
              symbol: "emptyCircle",
              symbolSize: 8,
              yAxisIndex: 1,
              itemStyle: {
                normal: {
                  color: "#F02FC2"
                }
              },
              // data: this.cdata.lineData
              data: this.cdata.lineData.map(value => {
                        const max = Math.max(...this.cdata.lineData);
                        const min = Math.min(...this.cdata.lineData);
                          if (value === max) {
                            return {
                              value: value,
                              itemStyle: {
                                color: '#FFCC00' // max
                              }
                            };
                          } else if (value === min) {
                            return {
                              value: value,
                              itemStyle: {
                                color: '#FF3333' // min
                              }
                            };
                          } else {
                            return value;
                          }
                        })
            },
            {

              name: "Valeur Totale",
              type: "bar",
              visualMap: {
                  min: Math.min(this.cdata.barData),
                  max: Math.max(this.cdata.barData),
                  calculable: true,
                  inRange: {
                    color: ['#FF3333', '#FFCC00']
                  }
                },
              barWidth: 8,
              itemStyle: {
                normal: {
                  barBorderRadius: 5,
                  color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    { offset: 0, color: "#956FD4" },
                    { offset: 1, color: "#3EACE5" }
                  ])
                }
              },
              data: this.cdata.barData
            },
            // {
            //   name: "yyy",
            //   type: "bar",
            //   barGap: "-100%",
            //   barWidth: 10,
            //   itemStyle: {
            //     normal: {
            //       barBorderRadius: 5,
            //       color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
            //         { offset: 0, color: "rgba(156,107,211,0.8)" },
            //         { offset: 0.2, color: "rgba(156,107,211,0.5)" },
            //         { offset: 1, color: "rgba(156,107,211,0.2)" }
            //       ])
            //     }
            //   },
            //   z: -12,
            //   data: this.cdata.lineData
            // }
          ]
        }
      this.myChart.setOption(option);
    },
    // Animation
    changeData(x){
      var st = x[0]
      for (var i= 0;i<x.length-1;i++){
        x[i]=x[i+1]
      }
      x[x.length-1]=st
    },
    updateBarChart(){
      if(this.isHover==true){
        this.changeData(this.cdata.category)
        this.changeData(this.cdata.lineData)
        this.changeData(this.cdata.barData)
        this.myChart.setOption({
          xAxis:{
            data:this.cdata.category
          },
          series :[
            {
              data:this.cdata.lineData
            },
            {
              data: this.cdata.barData
            }
          ]
        })
      }else{
        clearInterval(this.timer)
      }
    },
    startDataUpdateInterval(){
      if(this.isHover==true){
        const interval=2000
        clearInterval(this.timer)
        setInterval(this.updateBarChart,interval)
      }
    },
    //Animation si la souris n'est pas là, immobilité si la souris est là.
    startActon(){
      this.isHover=false
    },
    cancelAction(){
      this.isHover=true
    },
  }
};
</script>

<style lang="scss" scoped>
</style>