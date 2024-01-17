package entites;

public class Produit {
	private int id;
	private String titre;
	private float prix;
	private int id_categorie;
	private int stock;

	
	//Génération des Constructeurs
	public Produit() {}

	public Produit(int id, String titre, float prix, int id_categorie, int stock) {
		super();
		this.id = id;
		this.titre = titre;
		this.prix = prix;
		this.id_categorie = id_categorie;
		this.stock = stock;
	}

	public Produit(String titre, float prix, int id_categorie, int stock) {
		super();
		this.titre = titre;
		this.prix = prix;
		this.id_categorie = id_categorie;
		this.stock = stock;
	}


	
	
	//Génération des getters and setters
	
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
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "{id : " + id + ", titre : " + titre + ", prix : " + prix + ", id_categorie : " + id_categorie
				+ ", stock : " + stock + "}";
	};
	
	
	
}
