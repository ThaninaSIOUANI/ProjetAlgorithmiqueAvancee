## Prérequis
1. Assurez-vous que **Java 17 ou supérieur** est installé sur votre système.
   - Vérifiez votre version de Java avec la commande : `java -version`.

2. Ce projet a des dépendances JavaFX qu’il faut installer 2 sur : https://gluonhq.com/products/javafx/  et cochez la case include older versions.

## Arborescence du projet 
AlgoPartieB/
├── README.txt                    # Instructions pour exécuter le fichier .jar
├── SIOUANI_AlgoPartieB.jar          # Fichier JAR exécutable
├── src/                       
│   └── MainApp/           
│       ├── App.java
│       ├── WeigthedGraph.java
│       └── graph.txt
└──  SIOUANI_RapportAlgorithmiquePartieB.pdf


## Exécution
Pour utiliser Dijsktra; sinon remplacer Dijsktra dans les arguements par Astar.
### Sous Windows :
1. Ouvrez une invite de commande (cmd).
2. Exécutez la commande suivante depuis le dossier où vous avez décompressé le projet :
Sur Windows :
java --module-path ".\javafx-sdk-24\lib" --add-modules javafx.controls -jar SIOUANI_AlgoPartieB.jar .\graph.txt dijkstra

Sur Linus/Mac :
java --module-path "./javafx-sdk-21.0.5/lib" --add-modules javafx.controls -jar SIOUANI_AlgoPartieB.jar ./graph.txt dijkstra

