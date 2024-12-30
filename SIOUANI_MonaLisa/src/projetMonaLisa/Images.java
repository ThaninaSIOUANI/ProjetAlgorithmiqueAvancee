package projetMonaLisa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Images extends Application {

    private WritableImage wi;
    private Image image;
    private List<GraphPixel> pixelList = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        // l'utilisateur choisit son image
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));//les differnets formats acceptes
        //fichier choisit
        File file = fileChooser.showOpenDialog(stage);
        if (file == null) {
            System.out.println("Aucun fichier sélectionné. Fermeture de l'application.");
            return;
        }

        try {
        	//creation de l'image 
            FileInputStream fis = new FileInputStream(file);
            image = new Image(fis);
            System.out.println("Image chargée avec succès !");
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier introuvable !");
            e.printStackTrace();
            return;
        }
        
        // creation de l'image modifiable afin de pouvoir rajouter par dessus le chemin plutard
        wi = new WritableImage(image.getPixelReader(), (int) image.getWidth(), (int) image.getHeight());

        // Affichage de l'image
        ImageView imageView = new ImageView(wi);
        GraphImage graphImage = GraphImage.creerImage(wi);

        // Sélection des pixels de départ et d'arrivée grace aux clics 
        imageView.setOnMouseClicked(event -> {
            if (pixelList.size() < 2) {
                int i = (int) event.getX();
                int j = (int) event.getY();
                //trouver le pixel correspondant aux coordonnées du pixel selectionné
                GraphPixel pixel = graphImage.getPixel(i, j);
                pixelList.add(pixel);

                if (pixelList.size() == 2) {
                    GraphPixel start = pixelList.get(0);//pixel depart
                    GraphPixel end = pixelList.get(1);//pixel arrivée

                    System.out.println("Départ : " + start.getI() + " , " + start.getJ() );
                    System.out.println("Arrivée : " + end.getI() + " , " + end.getJ() );
                    System.out.println("Veuillez patienter quelques instants :) ");
                    LinkedList<GraphPixel> plusCourtChemin = graphImage.Dijkstra(start, end);

                    // Affichage du chemin le plus court trouvé
                    if (plusCourtChemin != null && !plusCourtChemin.isEmpty()) {
                    	System.out.println("Chemin trouvé avec succes grace a Dijkstra !");
                        PixelWriter pixelWriter = wi.getPixelWriter();
                        for (GraphPixel pixelChemin : plusCourtChemin) {
                        	//modifie la couleur des pixels du chemin trouvé en rouge 
                            pixelWriter.setColor(pixelChemin.getJ(), pixelChemin.getI(), Color.RED); 
                        }
                        imageView.setImage(wi);
                    } else {
                        System.out.println("Aucun chemin trouvé !");
                    }
                }
            }
        });

        // Configuration de la scène
        StackPane sp = new StackPane(imageView);
        Scene scene = new Scene(sp, image.getWidth(), image.getHeight());
        stage.setTitle("SIOUANI_MonaLisa");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

