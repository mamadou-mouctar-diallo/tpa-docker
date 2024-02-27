// Mélangez le code resize-mixins.js
// Après le passage à Scale, ce code n'est pas utilisé, mais il est conservé.
import { debounce } from '@/utils';
const resizeChartMethod = '$__resizeChartMethod';

export default {
  data() {
    // Permet de faire correspondre une référence à l'init du graphique à la propriété du graphique à l'intérieur du composant.
    return {
      chart: null,
    };
  },
  created() {
    window.addEventListener('resize', this[resizeChartMethod], false);
  },
  activated() {
    // Empêche la distorsion des graphiques après le keep-alive
    if (this.chart) {
      this.chart.resize()
    }
  },
  beforeDestroy() {
    window.removeEventListener('reisze', this[resizeChartMethod]);
  },
  methods: {
    // Fonction anti-shake pour contrôler la fréquence de redimensionnement
    [resizeChartMethod]: debounce(function() {
      if (this.chart) {
        this.chart.resize();
      }
    }, 300),
  },
};
