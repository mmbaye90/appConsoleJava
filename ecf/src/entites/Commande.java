package entites;

public class Commande {
	private int id;
	private String dateF;
	private int id_client;
	
	//Génération des Constructeurs
	public Commande() {}

	public Commande(int id, String dateF, int id_client) {
		super();
		this.id = id;
		this.dateF = dateF;
		this.id_client = id_client;
	}

	public Commande(String dateF, int id_client) {
		super();
		this.dateF = dateF;
		this.id_client = id_client;
	}
	
	
//Génération des getters an setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateF() {
		return dateF;
	}

	public void setDateF(String dateF) {
		this.dateF = dateF;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	@Override
	public String toString() {
		return "Commande [ { id : " + id + ", dateF : " + dateF + ", id_client : " + id_client + " } ]";
	}
	

	
}
