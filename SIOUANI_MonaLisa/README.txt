## Prérequis
1. Assurez-vous que **Java 17 ou supérieur** est installé sur votre système.
   - Vérifiez votre version de Java avec la commande : `java -version`.

2. Ce projet a des dépendances JavaFX qu’il faut installer 2 sur : https://gluonhq.com/products/javafx/  et cochez la case include older versions.

## Arborescence du projet 
SIOUANI_MonaLisa/
├── README.txt                    # Instructions pour exécuter le fichier .jar
├── SIOUANI_MonaLisa.jar          # Fichier JAR exécutable
├── src/                          # Code source du projet
│   └── projetMonaLisa/           # Package contenant les classes
│       ├── Edge.java
│       ├── GrapheImage.java
│       ├── GraphePixel.java
│       ├── Images.java
│       └── Pixel.java
└──  Mona_Lisa-677x1024.jpeg                   #Exemple d’image


## Exécution
### Sous Windows :
1. Ouvrez une invite de commande (cmd).
2. Exécutez la commande suivante depuis le dossier où vous avez décompressé le projet :
Sur Windows :
java --module-path ".\javafx-sdk-24\lib" --add-modules javafx.controls -jar SIOUANI_MiniProjet.jar
Sur Linus/Mac :
java --module-path "./javafx-sdk-21.0.5/lib" --add-modules javafx.controls -jar SIOUANI_MiniProjet.jar

