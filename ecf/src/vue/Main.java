package vue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import dao.CategorieDao;
import dao.ClientDao;
import dao.CommandeDao;
import dao.Entree_stockDao;
import dao.FournisseurDao;
import dao.PaiementDao;
import dao.ProduitDao;
import entites.Categorie;
import entites.Client;
import entites.Db;
import entites.Entree_stock;
import entites.Fournisseur;
import entites.Produit;

public class Main {
	static Scanner clavier = new Scanner(System.in);
	public static void main(String[] args) {
		// Connexion DB
		Db.connect();

		int choix=-1;
		while(choix!=0) {
			choix=menu();
			if(choix==1) {
				listeDesProduits();
			}else if(choix==2) {
				ajouterUnProduit();
			}else if(choix==3) {
				modifierUnProduit();
			}else if(choix==4) {
				// SupprimerUnProduit();
			}else if(choix==5) {
				// RechercherUnProduit();
			}else if(choix==6) {
				listeDesClients();
			}else if(choix==7) {
				ajouterUnClient();
			}else if(choix==8) {
				modifierUnClient();
			}else if(choix==9) {
				// SupprimerUnClient();
			}
			else if(choix==10) {
				// RechercherUnClient();
			}
			else if(choix==11) {
				listeDesCatégories();
			}
			else if(choix==12) {
				ajouterUneCatégorie();
			}
			else if(choix==13) {
				modifierUneCatégorie();
			}
			else if(choix==14) {
				// SupprimerUneCatégorie();
			}
			else if(choix==15) {
				listeDesCommandes();
			}
			else if(choix==16) {
				// PasserUneCommande();
			}
			else if(choix==17) {
				// SupprimerUneCommande();
			}
			else if(choix==18) {
				listeDesFournisseurs();
			}
			else if(choix==19) {
				ajouterUnFournisseur();
			}
			else if(choix==20) {
				modifierUnFournisseur();
			}
			else if(choix==21) {
				// SupprimerUnFournisseur();
			}
			else if(choix==22) {
				// RechercherUnFournisseur();
			}
			else if(choix==23) {
				listeDesEntréesEnStock();
			}
			else if(choix==24) {
				ajouterUneEntréeEnStock();
			}
			else if(choix==25) {
				// SupprimerUneEntréeEnStock();
			}
			else if(choix==26) {
				listeDesPaiements();
			}
			else if(choix==27) {
				// EffectuerUnPaiement();
			}
			else if(choix==28) {
				modifierUnPaiement();
			}
			else if(choix==29) {
				// SupprimerUnPaiement();
			}

		}
	}


	public static int menu() {
		System.out.println(
	"""
		############# MENU ###################################################
		#  1- ListeDesProduits     | 11- ListeDesCatégories     | 21- SupprimerUnFournisseur 
		#  2- AjouterUnProduit	   | 12- AjouterUneCatégorie    | 22- RechercherUnFournisseur
		#  3- ModifierUnProduit    | 13- ModifierUneCatégorie   | 23- ListeDesEntréesEnStock
		#  4- SupprimerUnProduit   | 14- SupprimerUneCatégorie  | 24- AjouterUneEntréeEnStock
		#  5- RechercherUnProduit  | 15- ListeDesCommandes      | 25- SupprimerUneEntréeEnStock
		#  6- ListeDesClients      | 16- PasserUneCommande      | 26- ListeDesPaiements
		#  7- AjouterUnClient 	   | 17- SupprimerUneCommande   | 27- EffectuerUnPaiement
		#  8- ModifierUnClient     | 18- ListeDesFournisseurs   | 28- ModifierUnPaiement
		#  9- SupprimerUnClient    | 19- AjouterUnFournisseur   | 29- SupprimerUnPaiement
		#  10- RechercherUnClient  | 20- ModifierUnFournisseur  | 0- Quitter
		############# Choix ###################################################
	""");
		int c=clavier.nextInt();
		return c;
	}

// =================================== LISTING  ============================
	public static void listeDesProduits(){
		System.out.println("####### ProductsList ###########");
		ProduitDao pdao = new ProduitDao();
		pdao.getAllProducts().forEach((p)->System.out.println(p));
	}


	public static void listeDesClients(){
		System.out.println("####### ClientList ###########");
		new ClientDao().getAllClient().forEach((cl)->System.out.println(cl));;
	}


	public static void listeDesCatégories(){
		System.out.println("####### CategList ###########");
		new CategorieDao().getAllCat().forEach((cat)->{
			System.out.println(" {id : "+cat.getId() + " ,titre :" +cat.getTitre()+" ,nbPrdt : "
			+new ProduitDao().getCountprdByIdCat(cat.getId())+" }");
		});
	}


	public static void listeDesCommandes(){
		System.out.println("####### ComdList ###########");
		new CommandeDao().getAllCmde().forEach((cmd)->System.out.println(cmd));
	}


	public static void listeDesFournisseurs(){
		System.out.println("####### FournisList ###########");
		new FournisseurDao().getAllFourni().forEach((fnr)-> System.out.println(fnr));
	}


	public static void listeDesEntréesEnStock(){
		System.out.println("####### EntStockList ###########");
		new Entree_stockDao().getAllEntStock().forEach((Es)-> System.out.println(Es));
	}


	public static void listeDesPaiements(){
		System.out.println("####### PymtList ###########");
		new PaiementDao().getAllPayment().forEach((pyt)->System.out.println(pyt));
	}


// =================================== ADDING  ============================
	public static void ajouterUnProduit(){
		System.out.println("####### Ajouter un produit #######");

		//Instanciation Objet Prod pour pouvoir le setter et l'env à la BD
		Produit prdt = new Produit();
		System.out.println("Titre du produit à ajouter");
		//flash
		clavier.nextLine();
		prdt.setTitre(clavier.nextLine());
		System.out.println("Prix du produit à ajouter");
		prdt.setPrix(clavier.nextFloat());

		//Afficher au user l'id_cat (clef_etr) pour qu'il choississe l'id corrspdt
		new CategorieDao().getAllCat().forEach((cat)->{
			System.out.println("{ id : " +cat.getId() + " ,titre : " +cat.getTitre() + " }");
		});

		//Demande au user l'ID du cat jusqu'à ce que l'id corresponde é l'un des ID affichés
		int idCat;
		do {
			System.out.println("Saisir un des ID des catégories ci dessus");
			idCat = clavier.nextInt();
		} while (new CategorieDao().getCatById(idCat)==null); //redmandé si ID retourne un objet null

		//On va pouvoir setter l'ID-cat de l'objet Prduit
		prdt.setId_categorie(idCat);

		//Instanciation et appel de la methode save de l'ojet Pdao pour inserer l'objt prdt setté à la BD
		new ProduitDao().save(prdt);

	}

	public static void ajouterUnClient(){
		System.out.println("####### Ajouter un produit #######");
		Client clt = new Client();
		System.out.println("Saisir nom du client à ajouter");
		//flash
		clavier.nextLine();		
		clt.setNom(clavier.nextLine());
		System.out.println("Ville du client à ajouter");
		clt.setVille(clavier.nextLine());
		System.out.println("Age du client à ajouter");
		clt.setAge(clavier.nextInt());
		//flash
		clavier.nextLine();
		System.out.println("Prenom du client à ajouter");
		clt.setPrenom(clavier.nextLine());
		//insertion
		new ClientDao().save(clt);
	}


	public static void ajouterUneCatégorie(){
		System.out.println("####### Ajouter une catégorie #######");
		Categorie cat = new Categorie();
		//flash
		clavier.nextLine();		
		System.out.println("Saisir titre du catégorie à ajouter");
		cat.setTitre(clavier.nextLine());
		new CategorieDao().save(cat);
	}


	public static void ajouterUnFournisseur(){
		System.out.println("####### Ajouter un Fournisseur #######");
		Fournisseur frsr = new Fournisseur();
		//flash
		clavier.nextLine();	
		System.out.println("Saisir nom du fournisseur Max(5) carateres");			
		String userPrt;
		do {
			userPrt = clavier.nextLine();
		} while (userPrt.length() >5); 

		frsr.setNom(userPrt);
		System.out.println("Saisir la ville du fournisseur");
		frsr.setVille(clavier.nextLine());
		new FournisseurDao().save(frsr);
	}


	public static void ajouterUneEntréeEnStock(){
		System.out.println("####### Ajouter une EntreeEnStock #######");

				Entree_stock Es = new Entree_stock();
				clavier.nextLine();
				//Afficher au user ID produits
				System.out.println("Liste des produits");
				new ProduitDao().getAllProducts().forEach((p)->{
					System.out.println("{ id : " +p.getId() + " ,titre : " +p.getTitre() + " }");
				});
				//Verif =>Remander l'ID tant qu'il ne corresponde pas à un ID affiché
				int idPdt;
				do {
					System.out.println("Saisir un des ID des pdts ci dessus");
					idPdt = clavier.nextInt();
				} while (new ProduitDao().getPrdtById(idPdt)==null);

				//setter l'ID du prdt
				Es.setId_produit(idPdt);
				//Afficher au user ID fournisseur
				System.out.println("Liste des fournisseurs");
				new FournisseurDao().getAllFourni().forEach((frsr)->{
					System.out.println("{ id :" +frsr.getId()+ " ,nom :" +frsr.getNom()+ " }");
				});

				//Verif =>Remander l'ID tant qu'il ne corresponde pas à un ID affiché
				int idF;
				do {
					System.out.println("Saisir un des ID des Fournisseurs ci dessus");
					idF = clavier.nextInt();
				} while (new FournisseurDao().getfrnsrById(idF)==null);

				//Setter l'ID fourn
				Es.setId_fournisseur(idF);
				System.out.println("Saisir la quantité");
				Es.setQuantite(clavier.nextInt());

				clavier.nextLine();
				String usrDate;
				String fmtValid = "yyyy-mm-dd";
				do {
					System.out.println("Saisir une date au format (YYYY-MM-DD)");
					usrDate = clavier.nextLine();				
				} while (isvalidDate(usrDate, fmtValid)!=true);

				Es.setdateE(usrDate);

				new Entree_stockDao().save(Es);
	}



//================================ UPDATING FUNTIONS  ==============
	public static void modifierUnProduit(){}


	public static void modifierUnClient(){}


	public static void modifierUneCatégorie(){}


	public static void modifierUnFournisseur(){}
	

	public static void modifierUnPaiement(){}


// =============================== FUUNCTION CONTROLLERS ============
	public static boolean isvalidDate(String d ,String formatDate){
		SimpleDateFormat df = new SimpleDateFormat(formatDate);
		try {
			Date dte = df.parse(d); 
			System.out.println(dte);
			return true; 
		} catch (ParseException pE) {
			System.err.println("Erreur Format");
            return false;
		}
	}
}

