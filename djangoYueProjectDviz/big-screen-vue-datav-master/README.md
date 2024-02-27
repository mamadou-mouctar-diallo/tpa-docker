
## I. Description du projet

- Un " **Data Big Screen Project** " basé sur les frameworks Vue, Datav, Echart, avec un rafraîchissement dynamique des données via les composants Vue, et des graphiques internes librement remplaçables. Certains graphiques utilisent des composants propres à DataV, qui peuvent être modifiés, veuillez cliquer sur la documentation DataV ci-dessous pour plus de détails.
- Environnement du projet : Vue-cli-3.0, DataV-2.7.3, Echarts-4.6.0 (s'il y a un problème avec la version 5.x, veuillez passer à la version 4.x), Webpack-4.0, Npm-6.13, Node-v12.16.


Lien de référence :
1.  [Vue ](https://cn.vuejs.org/v2/guide/instance.html)
2.  [DataV ](http://datav.jiaminghi.com/guide/)
3.  [echarts](https://echarts.apache.org/examples/zh/index.html)，[echarts API Documentation](https://echarts.apache.org/en/api.html#echarts)

## II. Introduction aux principaux documents

| fichier               |           Rôle/Fonction                                                          |
| ------------------- | ----------------------------------------------------------------------------------- |
| main.js                | Fichiers du répertoire personnel, introduisant des fichiers tels que Echart/DataV                                   |
| utils                  | Fonctions outils, mixins, etc.                                               |
| views/ index.vue       | Structure principale du projet                                                             |
| views/others documents | Composants pour chaque zone de l'interface (nommés en fonction de l'emplacement)                                       |
| assets                 | Catalogue de ressources statiques pour les logos et les images d'arrière-plan                                       |
| assets / style.scss    | Fichier CSS générique, ajustement du style des raccourcis des éléments globaux                                      |
| assets / index.scss    | Fichiers CSS pour l'interface de l'index                                                  |
| components/echart      | Toutes les cartes echart (nommées par emplacement)                                      |
| common/...             | ECharts encapsulés globalement et code plugin flexible (adapté à la taille de l'écran, personnalisable pour modification)     |

##  III. introduction à l'utilisation

### Lancement de projets
Vous devez installer `nodejs` et `yarn` à l'avance, après avoir téléchargé le projet, lancez `yarn` dans le répertoire du projet pour extraire les dépendances. Après avoir installé les dépendances, utilisez `vue-cli` ou `npm run serve` pour démarrer le projet, puis vous devez exécuter manuellement le projet en plein écran (appuyez sur F11). Si vous n'avez pas les dépendances du framework DataV lors de la compilation du projet, tapez `npm install @jiaminghi/data-view` ou `yarn add @jiaminghi/data-view` pour les installer manuellement.

### Composants d'habillage pour le rendu des graphiques

Tous les graphiques ECharts sont créés sur la base du composant wrapper `common/echart/index.vue`, qui est déjà à l'écoute des données et des changements d'écran, et qui est capable de rendre dynamiquement les données et la taille du graphique. Dans le module qui écoute la taille de la fenêtre, une fonction anti-shudder est utilisée pour contrôler la fréquence de mise à jour et économiser les performances du navigateur.

Le projet est configuré avec le style de graphique ECharts par défaut, l'adresse du fichier : `common/echart/theme.json`.

Le composant graphique encapsulé permet de passer les paramètres suivants, qui peuvent être ajoutés/supprimés en fonction des besoins de l'entreprise.

Nom du paramètre      | Type      | Rôle/Fonction                      |
| -------------------| --------- | ------------------------------|
| id                 | String    | unique id, the node that renders the chart (not required, $el is used)
| className          | String    | nom du style de la classe (non requis)                 |
| options            | Object    | Configuration ECharts (obligatoire)                   |
| height             | String    | Hauteur du graphique (recommandé)                    |
| width              | String    | Largeur du graphique (recommandée)                    |

###  Diagrammes à rendu dynamique

Les exemples de graphiques à rendu dynamique sont les composants graphiques du répertoire `components`. Le fichier index est responsable de l'extraction et du traitement des données, et le fichier graphique est responsable de l'écoute et du rendu des données.

La logique principale du fichier graphique est la suivante :

```html
<template>
  <div>
    <Echart :options="options" id="id" height="height" width="width" ></Echart>
  </div>
</template>

<script>
  //  Présentation des composants d'habillag
import Echart from '@/common/echart'
export default {
  // Définition des données de configuration
  data(){ return { options: {}}},
  // Déclaration des composants
  components: { Echart},
  // recevoir des données
  props: {
    cdata: {
      type: Object,
      default: () => ({})
    },
  },
  // Pour ce faire, vous pouvez également utiliser la propriété de calcul
  watch: {
    cdata: {
      handler (newData) {
        this.options ={
          // Inscrire la configuration ECharts ici
        }
      },
      // Écouter maintenant
      immediate: true,
      // écoute approfondie
      deep: true
    }
  }
};
</script>
```

### Remplacement des bordures

La bordure utilise les propres composants de DataV, il vous suffit d'aller dans le répertoire des vues pour chercher l'emplacement correspondant et le remplacer, veuillez consulter le site officiel de DavaV pour voir les types spécifiques de bordures.
Veuillez consulter le site officiel de DavaV pour plus de détails :

```html
<dv-border-box-1></dv-border-box-1>
<dv-border-box-2></dv-border-box-2>
<dv-border-box-3></dv-border-box-3>
```

- Exemple de référence de configuration de axios main.js 

```js
import axios from 'axios';
Vue.prototype.$http = axios.create({
  timeout: 20000,
  baseURL: 'http://172.0.0.1:8080', 
});
```
