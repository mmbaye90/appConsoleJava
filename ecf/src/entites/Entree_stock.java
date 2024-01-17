package entites;

public class Entree_stock {
	private int id;
	private int id_produit;
	private int id_fournisseur;
	private int quantite;
	private String dateE;

	
	//Génération des constructors
	public Entree_stock() {}
	public Entree_stock(int id, int id_produit, int id_fournisseur, int quantite, String dateE) {
		super();
		this.id = id;
		this.id_produit = id_produit;
		this.id_fournisseur = id_fournisseur;
		this.quantite = quantite;
		this.dateE = dateE;
	}
	public Entree_stock(int id_produit, int id_fournisseur, int quantite, String dateE) {
		super();
		this.id_produit = id_produit;
		this.id_fournisseur = id_fournisseur;
		this.quantite = quantite;
		this.dateE = dateE;
	}

	
	// Génération des getters and setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_produit() {
		return id_produit;
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	public int getId_fournisseur() {
		return id_fournisseur;
	}
	public void setId_fournisseur(int id_fournisseur) {
		this.id_fournisseur = id_fournisseur;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getdateE() {
		return dateE;
	}
	public void setdateE(String dateE) {
		this.dateE = dateE;
	}
	@Override
	public String toString() {
		return "Entree_stock [ { id : " + id + ", id_produit : " + id_produit + ", id_fournisseur : " + id_fournisseur
				+ ", quantite : " + quantite + ", dateE : " + dateE + " } ]";
	};
	
	
}
