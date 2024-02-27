// Valeur d'échelle par défaut
const scale = {
  width: '1',
  height: '1',
}

const baseWidth = 1920
const baseHeight = 1080

// * Ratio à maintenir (par défaut 1,77778)
const baseProportion = parseFloat((baseWidth / baseHeight).toFixed(5))

export default {
  data() {
    return {
      // * fonction de minuterie
      drawTiming: null
    }
  },
  mounted () {
    this.calcRate()
    window.addEventListener('resize', this.resize)
  },
  beforeDestroy () {
    window.removeEventListener('resize', this.resize)
  },
  methods: {
    calcRate () {
      const appRef = this.$refs["appRef"]
      if (!appRef) return 
      // Rapport hauteur/largeur actuel
      const currentRate = parseFloat((window.innerWidth / window.innerHeight).toFixed(5))
      if (appRef) {
        if (currentRate > baseProportion) {
          // dénotent plus large
          scale.width = ((window.innerHeight * baseProportion) / baseWidth).toFixed(5)
          scale.height = (window.innerHeight / baseHeight).toFixed(5)
          appRef.style.transform = `scale(${scale.width}, ${scale.height}) translate(-50%, -50%)`
        } else {
          // exprimer un degré plus élevé
          scale.height = ((window.innerWidth / baseProportion) / baseHeight).toFixed(5)
          scale.width = (window.innerWidth / baseWidth).toFixed(5)
          appRef.style.transform = `scale(${scale.width}, ${scale.height}) translate(-50%, -50%)`
        }
      }
    },
    resize () {
      clearTimeout(this.drawTiming)
      this.drawTiming = setTimeout(() => {
        this.calcRate()
      }, 200)
    }
  },
}