package projetMonaLisa;
//Classe represantant une arete
public class Edge {
	private GraphPixel source;
	private GraphPixel destination;
	private double weight;
	/**
	 * Constructeur initialisant une arete
	 * @param source
	 * @param destination
	 * @param weight le poids
	 */
	public Edge(GraphPixel source,GraphPixel destination,double weight) {
		this.destination=destination;
		this.source=source;
		this.weight=weight;
	}
	
	/**
	 * @return le pixel de depart de l'arete
	 */
	public GraphPixel getSource() {
		return source;
	}
	 
	/**
	 * @return le pixel de destination de l'arete
	 */
	public GraphPixel getDestination() {
		return destination;
	}
	
	/**
	 * @return le poids de l'arete
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * methode pour changer la valeur du poids de l'arete
	 * @param d poids de l'arete
	 */
	public void setWeight(double d) {
		weight=d;
		
	}

	
	
	
}
