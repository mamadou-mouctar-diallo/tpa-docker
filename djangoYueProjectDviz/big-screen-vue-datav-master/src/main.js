import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import dataV from '@jiaminghi/data-view';
// inport global css
import './assets/scss/style.scss';
// import icon of vue-awesome
import Icon from 'vue-awesome/components/Icon';
import 'vue-awesome/icons/chart-bar.js';
import 'vue-awesome/icons/chart-area.js';
import 'vue-awesome/icons/chart-pie.js';
import 'vue-awesome/icons/chart-line.js';
import 'vue-awesome/icons/align-left.js';


//import echart
//4.x
import echarts from 'echarts'
import $http from '@/api/index.js'

Vue.prototype.$echarts = echarts;
Vue.config.productionTip = false;
Vue.prototype.$http=$http;

// inscription global
Vue.component('icon', Icon);
Vue.use(dataV);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
