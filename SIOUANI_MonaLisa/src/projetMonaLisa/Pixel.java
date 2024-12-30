package projetMonaLisa;

import java.util.ArrayList;
import java.util.List;
 // chaque image est representée par un pixel 
public abstract class Pixel {
	private int i;
	private int j;
	private double intensite;
	private List<Edge> voisins; //liste des aretes adjacentes
	
	/**
	 * Constructeur qui permet d'initaliser un pixel
	 * @param i numero ligne
	 * @param j numero colonne
	 * @param intensite du pixel
	 */
	public Pixel(int i,int j,double intensite) {
		this.i=i;
		this.j=j;
		this.intensite=intensite;
		this.voisins=new ArrayList<Edge>();
	}

	/**
	 * @return la coordonnée i
	 */
	public int getI() {
		return i;
	}
	
	/**
	 * @return la coordonnée j
	 */
	public int getJ() {
		return j;
	}

	/**
	 * @return l'intesité du pixel
	 */
	public double getIntensite() {
		return intensite;
	}
	
	/**
	 * methode qui joute un voisin au pixel
	 * @param edge l'arete adjacente
	 */
	public void ajouterVoisin(Edge edge) {
		voisins.add(edge);
	}
	
	/**
	 * @return la liste de voisins du pixel
	 */
	public List<Edge> getVoisins(){
		return voisins;
	}
}
