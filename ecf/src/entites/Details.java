package entites;

public class Details {
	private int id;
	private int quantite;
	private float prixU;
	private int id_commande;
	private int id_produit;
	
	//Génération des Constructeurs
	public Details() {
		super();
	}

	public Details(int id, int quantite, float prixU, int id_commande, int id_produit) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.prixU = prixU;
		this.id_commande = id_commande;
		this.id_produit = id_produit;
	}

	public Details(int quantite, float prixU, int id_commande, int id_produit) {
		super();
		this.quantite = quantite;
		this.prixU = prixU;
		this.id_commande = id_commande;
		this.id_produit = id_produit;
	}

	//Génération des getters and setters;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public float getPrixU() {
		return prixU;
	}

	public void setPrixU(float prixU) {
		this.prixU = prixU;
	}

	public int getId_commande() {
		return id_commande;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}

	public int getId_produit() {
		return id_produit;
	}

	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}

	@Override
	public String toString() {
		return "Details [ { id : " + id + ", quantite : " + quantite + ", prixU : " + prixU + ", id_commande : " + id_commande
				+ ", id_produit : " + id_produit + " } ]";
	}
	
	
	
}
