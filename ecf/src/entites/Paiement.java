package entites;

public class Paiement {
	private int id;
	private int id_facture;
	private float montant;
	private String dateP;

//Génération des Constructeurs
	public Paiement() {}

	public Paiement(int id, int id_facture, float montant, String dateP) {
		super();
		this.id = id;
		this.id_facture = id_facture;
		this.montant = montant;
		this.dateP = dateP;
	}

	public Paiement(int id_facture, float montant, String dateP) {
		super();
		this.id_facture = id_facture;
		this.montant = montant;
		this.dateP = dateP;
	}
	
	
//Génération des getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_facture() {
		return id_facture;
	}

	public void setId_facture(int id_facture) {
		this.id_facture = id_facture;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public String getDateP() {
		return dateP;
	}

	public void setDateP(String dateP) {
		this.dateP = dateP;
	}

	@Override
	public String toString() {
		return "Paiement [ { id : " + id + ", id_facture : " + id_facture + ", montant : " + montant + ", dateP :" + dateP + " } ]";
	}
	

}
