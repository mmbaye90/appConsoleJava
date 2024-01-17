package entites;

public class Client {
	private int id;
	private String nom;
	private String ville;
	private int age;
	private String prenom;
	
//Génération des Constructeurs
	public Client() {}

	public Client(int id, String nom, String ville, int age, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.ville = ville;
		this.age = age;
		this.prenom = prenom;
	}

	public Client(String nom, String ville, int age, String prenom) {
		super();
		this.nom = nom;
		this.ville = ville;
		this.age = age;
		this.prenom = prenom;
	}
//Génération getters and setters
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Client [ { id : " + id + ", nom : " + nom + ", ville : " + ville + ", age : " + age + ", prenom : " + prenom + " } ]";
	};
		
	
	
}
