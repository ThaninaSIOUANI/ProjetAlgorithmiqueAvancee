package projetMonaLisa;

import java.util.*;


import javafx.scene.image.PixelReader; 
import javafx.scene.image.WritableImage; 
import javafx.scene.paint.Color;

//une image peut etre representée sous la forme d'un graphe ou les sommets 
//les pixels et le poids des arretes la difference d'intesité entre deux pixels voisins 
public class GraphImage {
	private List<GraphPixel> pixels;
	private static int largeur;
	private static int longueur;
	
	/**
	 * Constructeur qui initialise le graphe en graphe vide
	 */
	private GraphImage() {
		pixels=new ArrayList<>();
	}
	
	/**
	 * methode retourant le pixel correspondant aux coordonnees mises en parametres
	 * @param i numero de ligne
	 * @param j numero de colonne
	 * @return le pixel correspondant s'il fait partie de l'image sinon null
	 */
	public GraphPixel getPixel(int i,int j) {
		for(GraphPixel p: pixels) {
			if(p.getI()==i && p.getJ()==j) return p;
		}
		return null;
	}
	
	/**
	 * methode permettant de transformer tout les poids negatifs du graphe en poids positifs
	 */
	private void transformerPoidsPositifs() {
	    double minWeight = Double.POSITIVE_INFINITY;

	    // Trouver le poids minimal
	    for (GraphPixel pixel : pixels) {
	        for (Edge edge : pixel.getVoisins()) {
	            if (edge.getWeight() < minWeight) {
	                minWeight = edge.getWeight();
	            }
	        }
	    }

	    // Ajouter |minWeight| + ε à chaque poids
	    double constante = Math.abs(minWeight) + 0.01; // ε = 0.01
	    for (GraphPixel pixel : pixels) {
	        for (Edge edge : pixel.getVoisins()) {
	            edge.setWeight(edge.getWeight() + constante);
	        }
	    }
	}

	/**
	 * methode permettant de creer le graphe correspondant a l'image
	 * @param image une image modifiable
	 * @return un graphe
	 */
	
	public static GraphImage creerImage(WritableImage image) {
		GraphImage graph=new GraphImage();
		
		largeur=(int)image.getWidth();
		longueur=(int)image.getHeight();
		
		//recupere les informations des pixels de l'image
		PixelReader pr=image.getPixelReader();
		
		//ajout des pixels
		for(int i=0;i<largeur;i++) {
			for(int j=0;j<longueur;j++) {
				Color c=pr.getColor(i, j); //recupere la couleur du pixel
				//l'intensité d'un pixel en RGB est definie par la formule suivante 
				//car se rapproche le plus de la perseption humaine des couleurs RGB
				double intensite= (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
				graph.pixels.add(new GraphPixel(i,j,intensite));
			}
		}
		
		//ajout des voisins des pixels
		for(int i=0;i<largeur;i++) {
			for(int j=0;j<longueur;j++) {
				GraphPixel p=graph.getPixel(i, j);
				//verification si le pixel (i,j) n'est pas sur les bords
				 if (i > 0) {
	                    GraphPixel gauche = graph.getPixel(i-1, j);
	                    double intensite= p.getIntensite()-gauche.getIntensite();
	                    p.ajouterVoisin(new Edge(p,gauche,intensite));
	                }

	                // Ajouter le voisin du haut
	                if (j > 0) {
	                	GraphPixel haut = graph.getPixel(i, j-1);
	                    double intensite= p.getIntensite()-haut.getIntensite();
	                    p.ajouterVoisin(new Edge(p,haut,intensite));
	                    
	                }

	                // Ajouter le voisin de droite
	                if (i < largeur-1) {
	                	GraphPixel droite = graph.getPixel(i+1, j);
	                    double intensite= p.getIntensite()-droite.getIntensite();
	                    p.ajouterVoisin(new Edge(p,droite,intensite));
	                }

	                // Ajouter le voisin du bas
	                if (j < longueur - 1) {
	                    GraphPixel bas = graph.getPixel(i, j+1);
	                    double intensite= p.getIntensite()-bas.getIntensite();
	                    p.ajouterVoisin(new Edge(p,bas,intensite));
	                }
			}
		}
		return graph;
		
		
	}
	
	/**
	 * methode trouvant le plus court chemin entre deux pixels
	 * (Cette methode est une legere modification (adaptation) de la methode 
	 * implementee dans la partie B)
	 * @param start pixel de depart
	 * @param end pixel d'arrivé
	 * @return le plus court chemin trouvé dans une liste chainée
	 */
	public LinkedList<GraphPixel> Dijkstra(GraphPixel start,GraphPixel end){
		
		transformerPoidsPositifs();
		//initalisation de la distance finale du depart a 0 (si depart = arrivée)
		start.timeFromSource=0;
		
		//ensemble des pixels deja visités
		HashSet<GraphPixel> visited = new HashSet<GraphPixel>();
		
		while (!visited.contains(end)){
			// trouver le noeud minPixel parmis tous les noeuds  ayant la distance temporaire
			//   timeFromSource minimale.
			GraphPixel minPixel = null;
            double distanceMinimale = Double.POSITIVE_INFINITY;

            // Recherche du pixel avec la distance minimale non visité
            for (GraphPixel pixel : this.pixels) {
            	
                if (!visited.contains(pixel) && pixel.timeFromSource < distanceMinimale) {
                    minPixel = pixel;
                    distanceMinimale = pixel.timeFromSource;
                }
            }

            if (minPixel == null) {
                break; // Arrête si aucun chemin trouvé jusqu'à présent
            }

            visited.add(minPixel);
			
            for(int i=0;i<minPixel.getVoisins().size();i++) {
            	GraphPixel toTry = (GraphPixel) minPixel.getVoisins().get(i).getDestination();
				if(!visited.contains(toTry)) { //si le voisin est pas encore visité
					GraphPixel dernier_ajout=minPixel;
					GraphPixel voisin=toTry;
					if(dernier_ajout.timeFromSource + dernier_ajout.getVoisins().get(i).getWeight() < voisin.timeFromSource) {
						voisin.timeFromSource = dernier_ajout.timeFromSource + dernier_ajout.getVoisins().get(i).getWeight();
						voisin.pere= dernier_ajout;
					}
				}
            }
            
          
	    	
		}
		//constitution du chemin
		 LinkedList<GraphPixel> path=new LinkedList<>();
         path.addFirst(end);
         GraphPixel pere=(GraphPixel) end.pere;
         while(pere!=null) {
        	 path.addFirst(pere);
        	 pere=(GraphPixel) pere.pere;
         }
         
         return path;
	}
}

