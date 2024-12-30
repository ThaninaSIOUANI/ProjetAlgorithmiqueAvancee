package projetMonaLisa;
 

//classe qui represente un noeud pixel dans un graphe 
public class GraphPixel extends Pixel {
	GraphPixel pere; //pere du pixel dans le chemin trouve 
	double timeFromSource; //distance entre le pixel et le depart
	
	/**
	 * Constructeur 
	 * @param i
	 * @param j
	 * @param intensite
	 */
	public GraphPixel(int i,int j,double intensite) {
		super(i,j,intensite);
		pere=null;
		timeFromSource=Double.POSITIVE_INFINITY;
	}
}
