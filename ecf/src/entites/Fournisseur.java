package entites;

public class Fournisseur {
	private int id;
	private String nom;
	private String ville;
	
	//Génération des Constructeurs
	
	public Fournisseur() {}

	public Fournisseur(int id, String nom, String ville) {
		super();
		this.id = id;
		this.nom = nom;
		this.ville = ville;
	}

	public Fournisseur(String nom, String ville) {
		super();
		this.nom = nom;
		this.ville = ville;
	}
	
	//Génération des  Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Fournisseur [ { id : " + id + ", nom : " + nom + ", ville : " + ville + " } ]";
	};
	
	
}