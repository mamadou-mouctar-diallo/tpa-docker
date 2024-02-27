<template>
    <div ref="chart" style="width: 95%;height:480px;place-items: center;" v-bind:key="cdata.marques"></div>
</template>

<script>
export default {
  data() {
    return {
      cdata: {
        marques: [
          {
            name: 'Marque 1',
            noms: [
              {
                nom: 'Nom 1',
                couleurs: [
                  {
                    couleur: 'Couleur 1',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },

                  {
                    name: 'Couleur 2',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                  {
                    name: 'Couleur 3',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                ]
              },
              {
                nom: 'Nom 2',
                couleurs: [
                  {
                    name: 'Couleur 1',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },

                  {
                    name: 'Couleur 2',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                  {
                    name: 'Couleur 3',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                ]
              },
            ]
          },
                      {
            name: 'Marque 2',
            noms: [
              {
                nom: 'Nom 1',
                couleurs: [
                  {
                    name: 'Couleur 1',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },

                  {
                    name: 'Couleur 2',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                  {
                    name: 'Couleur 3',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                ]
              },
              {
                nom: 'Nom 2',
                couleurs: [
                  {
                    name: 'Couleur 1',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },

                  {
                    name: 'Couleur 2',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                  {
                    name: 'Couleur 3',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                ]
              },
            ]
          },
                      {
            name: 'Marque 3',
            noms: [
              {
                nom: 'Nom 1',
                couleurs: [
                  {
                    name: 'Couleur 1',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },

                  {
                    name: 'Couleur 2',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                  {
                    name: 'Couleur 3',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                ]
              },
              {
                nom: 'Nom 2',
                couleurs: [
                  {
                    name: 'Couleur 1',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },

                  {
                    name: 'Couleur 2',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                  {
                    name: 'Couleur 3',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                ]
              },
            ]
          },
                      {
            name: 'Marque 4',
            noms: [
              {
                nom: 'Nom 1',
                couleurs: [
                  {
                    name: 'Couleur 1',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },

                  {
                    name: 'Couleur 2',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                  {
                    name: 'Couleur 3',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                ]
              },
              {
                nom: 'Nom 2',
                couleurs: [
                  {
                    name: 'Couleur 1',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },

                  {
                    name: 'Couleur 2',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                  {
                    name: 'Couleur 3',
                    occasions: [
                      {occasion:'Occasion 1',
                          prix:1000,
                        },
                      {occasion:'Occasion 2',
                          prixs:15000
                        }]
                  },
                ]
              },
            ]
          },
        ]
      }
    };
  },

  components: {
  },

  async mounted () {
    const res = await this.$http.get('myDviz/bottomRight');
    // console.log(res)
    this.$set(this.cdata,'marques',res.data)
  },
  updated() {
    this.initChart()
  },
  beforeDestroy () {
    clearInterval(this.drawTiming);
  },
  methods: {
    initChart(){
      this.myChart = this.$echarts.init(this.$refs.chart)
      const option = {
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove'
        },
        series: [
                {
                  type: 'tree',
                  data: [{
                      name: 'voitures', // root voitures
                      children:
                      this.cdata.marques.map((marque) => ({
                          name: marque.name, // marque
                          children: marque.noms.map((nom) => ({
                            name: nom.nom, // nom
                            children: nom.couleurs.map((couleur) => ({
                              name: couleur.couleur, // couleur
                              children: couleur.occasions.map((occasion) => ({
                                name: `Occasion:${occasion.occasion}: ${occasion.prix}€` // occasion prix
                              }))
                            }))
                          }))
                        })),
                  }],
                  top: '18%',
                  bottom: '14%',
                  layout: 'radial',
                  symbol: 'emptyCircle',
                  symbolSize: 7,
                  initialTreeDepth: 3,
                  animationDurationUpdate: 750,
                  emphasis: {
                    focus: 'descendant'
                  }
                }
        ]
      };
      this.myChart.setOption(option);
    },
  }
};
</script>

<style lang="scss" scoped>
</style>