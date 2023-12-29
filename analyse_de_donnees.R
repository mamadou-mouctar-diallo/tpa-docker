##########################
#ANALYSE DE DONNEES EN R#
#########################

library(shiny)
library(dplyr)
library(ggplot2)
library(DT)
library(factoextra)
library(rpart)
library(cluster)
library(tidyr)


ui <- fluidPage(
  titlePanel("Analyse exploratoire des données"),
  tabsetPanel(
    #Création d'un onglet "Matrices" pour afficher les données communes entre les clients et les voitures choisies par les clients
    tabPanel("Matrices", DTOutput("clientMatrix")),
    tabPanel("Clusters",
             DTOutput("clusterTable")
    ),
    tabPanel("Graphiques",
             sliderInput("numClusters", "Nombre de clusters:", min = 2, max = 9, value = 3),
             plotOutput("clusterPlotPuissance"),
             plotOutput("histogram")
             ),
    tabPanel("Catégories",
             fluidRow(
               div(style = "text-align: center; margin-bottom: 20px; margin-top: 20px",
              actionButton("btn_citadine", "Citadines", style = "background-color: #FFC0CB; color: white;"),
              actionButton("btn_familiale", "Familiales", style = "background-color: #D0C1E5; color: white;"),
              actionButton("btn_sportive", "Sportives", style = "background-color: #F3D9CE; color: white;"),
              actionButton("btn_luxe", "Luxe", style = "background-color: #40E0D0; color: white;"),
              actionButton("btn_berline", "Berline", style = "background-color: #B6E1D0; color: white;"),
              actionButton("btn_suv", "SUV et Monospaces", style = "background-color: #DDD5E9; color: white"),
              actionButton("btn_berline_compacte", "Berline compacte", style = "background-color:#A7E3F2; color:white")
               
               )
             ),
             DTOutput("tableauVoitures")
             ),
    tabPanel("Résultats",
             div(style = "display: flex; margin-top:20px",
                 downloadButton("downloadCluster", class = "square-button", style = "background-color: #A7E3F2;", "Télécharger Clusters"),
                 downloadButton("downloadCategories", class="square-button", style = "background-color: #FFC0CB;", "Télécharger Catégories")
             )
    ),
    tags$head(
      tags$style(HTML("
            .square-button {
                width: 250px;
                height: 250px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
        ")
      )
    )
  )
)

server <- function(input, output) {
  
  ########################################
  #Mise en place de la matrice de données#
  ########################################
  
  # Récupération des données des clients dans une variable clientData

  clientData <- reactive({
    #Spécification de l'encodage des fichiers car le "é" apparaissait en "<e9>"
    first_file <- read.csv("./Clients_13.csv", fileEncoding = "Windows-1252")
    second_file <- read.csv("./Clients_19.csv", fileEncoding = "Windows-1252")
    # Deux fichiers clients ont été donnés, il faut donc les extraire et les fusionner avec la fonction rbind
    both_files <- rbind(first_file, second_file)
    return(both_files)
  })
  
  # Récupération des données des véhicules dans une variable vehicleData
  vehicleData <- reactive({
    raw_data <- read.csv("./Immatriculations.csv", stringsAsFactors = FALSE)
    # Correction des erreurs d'encodage de la colonne "longueur"
    raw_data$longueur <- gsub("tr<e8>s", "très", raw_data$longueur)
    
    return(raw_data)
  })
  
  #Combinaison des données "clientData" et des données "vehicleData" en mettant en commun la colonne d'immatriculation
  combinedData <- reactive({
    data <- merge(clientData(), vehicleData(), by = "immatriculation")
    #Transformation de la colonne "nbEnfantsAcharge" en integer
    data$nbEnfantsAcharge <- as.numeric(as.character(data$nbEnfantsAcharge))
    data$nbEnfantsAcharge[is.na(data$nbEnfantsAcharge)] <- 0
    #Remplacement des données impropres par la valeur 0
    data[is.na(data)] <- 0
    return (data)
  })
  
  # Création de la matrice sur notre app
  output$clientMatrix <- renderDT({
    datatable(combinedData())
  })
  
  ###########################
  #Réalisation du clustering#
  ###########################
  
  clusters <- reactive({
    data_for_clustering <- combinedData()[, c("age", "nbEnfantsAcharge", "puissance", "nbPlaces", "nbPortes", "prix")]
    data_for_clustering <- data_for_clustering[sapply(data_for_clustering, is.numeric)]
    data_for_clustering <- data_for_clustering[sapply(data_for_clustering, function(col) length(unique(col)) > 1)]
    if(ncol(data_for_clustering) > 0){
      kmeans(data_for_clustering, centers = input$numClusters)
    }
  })
  
  
  # Création d'un graphique sous forme de nuages de points permettant de déterminer les clusters selon le K que l'on définit
  output$clusterTable <- renderDT({
    # Obtenir les données combinées et les clusters actuels
    data <- combinedData()
    
    # Récupération en temps réel des données Kmeans
    set.seed(123) # Pour la reproductibilité
    clust <- clusters()
    
    # Ajout de la colonne de clusters aux données
    data$cluster <- as.factor(clust$cluster)
    
    datatable(data)
  })
  
  my_colors <- c("#FFC0CB", "#184D68", "#F70B0C", "#40E0D0", "#F4BB94", "#DDD5E9", "#A7E3F2", "#BDCDA3", "#B0554A")
  
  
  output$clusterPlotPuissance <- renderPlot({
    # Utilisation des données combinées directement pour le graphique
    data <- combinedData()
    set.seed(123) # Pour la reproductibilité
    clust <- clusters()
    
    # Ajout de la colonne de clusters aux données pour le graphique
    data$cluster <- as.factor(clust$cluster)
    
    # Construction du nuage de points avec ggplot
    ggplot(data, aes(x = puissance, y = prix, color = cluster)) +
      geom_point() +
      theme_minimal() +
      labs(title = "Clusters selon le prix et la puissance d'une voiture",
           x = "Puissance",
           y = "Prix",
           color = "Cluster") +
      scale_color_manual(values = my_colors)
  })
  
  #Remarque : avec un K=5, on identifie des clusters distincts dont la séparation est évocatrice
  
  data_for_histogram <- reactive({
    data <- combinedData()
    clust <- clusters()
    
    if (!is.null(clust)) {
      data$cluster <- as.factor(clust$cluster)
      
      # Calcul des quartiles de la colonne des prix car sinon l'affichage est presque illisible
      quartiles <- quantile(data$prix, probs = c(0, 0.25, 0.5, 0.75, 1), na.rm = TRUE)
      
      # Création des étiquettes de gamme de prix basées sur les quartiles
      labels <- c(
        paste("[", quartiles[1], "-", quartiles[2], "]"),
        paste("[", quartiles[2], "-", quartiles[3], "]"),
        paste("[", quartiles[3], "-", quartiles[4], "]"),
        paste("[", quartiles[4], "-", quartiles[5], "]")
      )
      
      # Catégorisation des prix en utilisant les quartiles et les étiquettes
      data$prix_cat <- cut(data$prix, breaks = quartiles, labels = labels, include.lowest = TRUE)
      data$prix_cat <- as.factor(data$prix_cat)
      
      #Sélection des colonnes de notre futur histogramme
      features <- data[, c("nbEnfantsAcharge", "nbPlaces", "nbPortes", "prix_cat", "cluster")]
      features$nbEnfantsAcharge <- as.factor(features$nbEnfantsAcharge)
      features$nbEnfantsAcharge[features$nbEnfantsAcharge == -1] <- NA
      features$nbPlaces <- as.factor(features$nbPlaces)
      features$nbPortes <- as.factor(features$nbPortes)
      
      # Transformation en format long pour l'insérer ensuite dans ggplot'
      long_data <- pivot_longer(features, cols = -cluster, names_to = "caractéristique", values_to = "valeur")
      long_data <- long_data %>% drop_na(valeur)
      return(long_data)
    } else {
      return(NULL)
    }
  })
  
  # Création de l'histogramme empilé
  output$histogram <- renderPlot({
    long_data <- data_for_histogram()
    
    if (!is.null(long_data)) {
      ggplot(long_data, aes(x = valeur, fill = cluster)) +
        geom_bar(position = "dodge") +
        facet_wrap(~ caractéristique, scales = "free_x") +
        scale_fill_manual(values = my_colors) +
        theme_minimal() +
        theme(axis.text.x = element_text(angle = 45, hjust = 1)) +
        labs(fill = "Cluster", x = "Caractéristique", y = "Compte") +
        guides(fill = guide_legend(title = "Cluster"))
    }
  })
  
  ##########################
  #Création des catégories#
  #########################
  
  current_filter <- reactiveVal()
  #Initialisation d'une variable permettant de montrer les données seulement quand on appuie sur un bouton
  selected_category <- reactiveVal(NULL)

  categoriser_voitures <- function(data) {
    data <- data %>%
      mutate(categorie = case_when(
        puissance > 400 ~ "sportive",
        prix > 50000 ~ "luxe",
        longueur == "moyenne" & nbPlaces > 4 ~ "berline compacte",
        puissance <= 100 & longueur == "courte" & prix <= 18000 & occasion == "false" ~ "citadine",
        longueur == "courte" & prix > 18000 & prix < 50000 ~ "SUV",
        nbPlaces > 4 & longueur == "longue" & marque != "8Jaguar" ~ "familiale",
        nbPlaces > 4 & marque == "Mercedes" ~ "luxe",
        longueur == "courte" & prix <= 10000 & occasion == "true" ~ "citadine",
        marque == "Jaguar" ~ "luxe",
        longueur == "très longue" & prix < 50000 ~ "berline",
        TRUE ~ "SUV"
      ))
    return(data)
  }
  
  output$tableauVoitures <- renderDT({
    data <- combinedData()
    categoriser_voitures(data)
  })
  
  # Observe les clics sur les boutons et met à jour le filtre
  observeEvent(input$btn_citadine, { selected_category("citadine") }, ignoreInit = TRUE)
  observeEvent(input$btn_familiale, { selected_category("familiale") }, ignoreInit = TRUE)
  observeEvent(input$btn_sportive, { selected_category("sportive") }, ignoreInit = TRUE)
  observeEvent(input$btn_luxe, { selected_category("luxe") }, ignoreInit = TRUE)
  observeEvent(input$btn_berline, { selected_category("berline") }, ignoreInit = TRUE)
  observeEvent(input$btn_suv, { selected_category("SUV") }, ignoreInit = TRUE)
  observeEvent(input$btn_berline_compacte, { selected_category("berline compacte") }, ignoreInit = TRUE)
  

  output$imageOutput <- renderUI({
    req(image_path())  # Require that the image path is set
    tags$img(src = image_path(), style = "max-width: 100%; height: auto;")
  })
  
  
  # Utilise le filtre actuel pour afficher les données
  output$tableauVoitures <- renderDT({
    req(selected_category())  # Nécessite une catégorie sélectionnée pour afficher le tableau
    data <- combinedData()
    # Application de la fonction de catégorisation
    categorised_data <- categoriser_voitures(data)
    
    # Filtrer les données selon la catégorie sélectionnée
    filtered_data <- categorised_data %>%
      filter(categorie == selected_category())
    datatable(filtered_data)
  })
  
  output$downloadCluster <- downloadHandler(
    filename = function() { "cluster.csv" },
    content = function(file) {
      data <- combinedData()  # Ou la source des données de clusters
      write.csv(data, file)
    }
  )
  
  output$downloadCategories <- downloadHandler(
    filename = function() { "categories.csv" },
    content = function(file) {
      data <- categoriser_voitures(combinedData())  # Appliquez votre fonction de catégorisation ici
      write.csv(data, file)
    }
  )
  
  
}

shinyApp(ui = ui, server = server)
