package entites;

public class Categorie {
	private int id;
	private String titre;
	
	
	//Génération des Constructeurs
	public Categorie() {};
	public Categorie(int id, String titre) {
		super();
		this.id = id;
		this.titre = titre;
	}
	public Categorie(String titre) {
		super();
		this.titre = titre;
	}

	//Génération des getters et Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	};

	
	@Override
	public String toString() {
		return "Categorie [ { id :" + id + ", titre : " + titre + " } ]";
	}
	
}
