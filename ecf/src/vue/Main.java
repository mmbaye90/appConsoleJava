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
import entites.Commande;
import entites.Db;
import entites.Entree_stock;
import entites.Fournisseur;
import entites.Paiement;
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
				supprimerUnProduit();
			}else if(choix==5) {
				rechercherUnProduit();
			}else if(choix==6) {
				listeDesClients();
			}else if(choix==7) {
				ajouterUnClient();
			}else if(choix==8) {
				modifierUnClient();
			}else if(choix==9) {
				supprimerUnClient();
			}
			else if(choix==10) {
				rechercherUnClient();
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
				supprimerUneCatégorie();
			}
			else if(choix==15) {
				listeDesCommandes();
			}
			else if(choix==16) {
				// passerUneCommande();
			}
			else if(choix==17) {
				supprimerUneCommande();
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
				supprimerUnFournisseur();
			}
			else if(choix==22) {
				rechercherUnFournisseur();
			}
			else if(choix==23) {
				listeDesEntréesEnStock();
			}
			else if(choix==24) {
				ajouterUneEntréeEnStock();
			}
			else if(choix==25) {
				supprimerUneEntréeEnStock();
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
				supprimerUnPaiement();
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

    public static void passerUneCommande(){
		System.out.println("####### Passer une Commande #######");

		Commande cmde = new Commande();
		//flash
		clavier.nextLine();
		String usrDate;
		String fmtValid = "yyyy-mm-dd";
		do {
			System.out.println("Saisir la dateF au format (YYYY-MM-DD)");
			usrDate = clavier.nextLine();				
		} while (isvalidDate(usrDate, fmtValid)!=true);

		cmde.setdateF(usrDate);
		
		// new CommandeDao().getAllCmde().forEach((c)->System.out.println("{ id :" +c.getId()+
		// ", nom : " + c.getNom() + " }"
		// ));
		// int idclt;
		// do {
		// 	System.out.println("Saisir un des ID du client ci dessus");
		// 	idclt = clavier.nextInt();
		// } while (new ClientDao().getClientById(idclt)==null);
		System.out.println("saisir id_client");
		cmde.setId_client(clavier.nextInt());
		// System.out.println(cmde);
		new CommandeDao().save(cmde);

		// new CommandeDao().save(cmde);
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



//================================ UPDATING FUNCTIONS  ==============
	public static void modifierUnProduit(){
		System.out.println("####### Modifier un Produit #######");
		new ProduitDao().getAllProducts().forEach((p)->{
			System.out.println("{ id : " +p.getId() +" ,titre :"+p.getTitre()+ " }");
		});	
		int idP;
		do {
			System.out.println("Saisir un des ID du produit ci dessus");
			idP = clavier.nextInt();
		} while (new PaiementDao().getPaymtById(idP)==null);
		Produit p = new ProduitDao().getPrdtById(idP);
		clavier.nextLine();
		System.out.println("Saisir titre à modifier");
		p.setTitre(clavier.nextLine());
		System.out.println("Saisir Prix à modifier");
		p.setPrix(clavier.nextFloat());

		System.out.println("Liste ID Catégorie à choisir impérativement pour updater");
		new CategorieDao().getAllCat().forEach((cat)->System.out.println("{id :" +cat.getId()
		+" ,id_catégorie : "+ cat.getTitre()+ " }"));
		
		int idCat;
		do {
			System.out.println("Saisir ID Catégorie");
			idCat = clavier.nextInt();
		} while (new CategorieDao().getCatById(idCat)==null);

		p.setId_categorie(idCat);
		
		System.out.println("Saisir Stock à modifier");
		p.setStock(clavier.nextInt());

		new ProduitDao().save(p);
	}


	public static void modifierUnClient(){
		System.out.println("####### Modifier un Client #######");
		System.out.println("Liste des client à Modofier exple id/prenom");
		new ClientDao().getAllClient().forEach((cl)->{
			System.out.println("{ id : "+cl.getId()+" , prenom :" + cl.getPrenom()+" }");
		});
		
		//Verif ID saisi par user
		int idUser;
		do {
			System.out.println("Donnez l'ID du client à modifier");
			idUser = clavier.nextInt();	
		} while (new ClientDao().getClientById(idUser)==null);

		//Avec cet ID je crée un objet de type Client avec la methode getCltbyid de lobjDaoClt
		Client clt = new ClientDao().getClientById(idUser);
		
		System.out.println("Voici les détails du client demandé\n"+clt);
		System.out.println("Saisir Nom à modifier");
		clavier.nextLine();
		clt.setNom(clavier.nextLine());
		System.out.println("Saisir Ville à modifier");
		clt.setVille(clavier.nextLine());
		System.out.println("Saisir Age à modifier");
		clt.setAge(clavier.nextInt());
		System.out.println("Saisir Prenom à modifier");
		clavier.nextLine();
		clt.setPrenom(clavier.nextLine());
		new ClientDao().save(clt);
	}


	public static void modifierUneCatégorie(){
		System.out.println("####### Modifier une Catégorie #######");
		System.out.println("Liste des Categorie à Modofier");
		new CategorieDao().getAllCat().forEach((cat)->{
			System.out.println("{ id : "+cat.getId()+" , titre :" + cat.getTitre()+" }");
		});
		
		//Verif ID saisi par user
		int idUser;
		do {
			System.out.println("Donnez l'ID du client à modifier");
			idUser = clavier.nextInt();	
		} while (new CategorieDao().getCatById(idUser)==null);

		//Avec cet ID je crée un objet de type Catgrie avec la methode getCattbyid de lobjDao
		Categorie cat = new CategorieDao().getCatById(idUser);
		System.out.println("Saisir Titre à modifier");
		clavier.nextLine();
		cat.setTitre(clavier.nextLine());

		new CategorieDao().save(cat);
	}


	public static void modifierUnFournisseur(){
		System.out.println("####### Modifier un Fournisseur #######");
		System.out.println("Liste des Fournisseur à Modofier");
		new FournisseurDao().getAllFourni().forEach((fsr)->{
			System.out.println("{ id : "+fsr.getId()+" , nom :" + fsr.getNom()+" ,ville"+fsr.getVille()+" }");
		});
		
		//Verif ID saisi par user
		int idUser;
		do {
			System.out.println("Donnez l'ID du Fournisseur à modifier");
			idUser = clavier.nextInt();	
		} while (new FournisseurDao().getfrnsrById(idUser)==null);

		//Avec cet ID je crée un objet de type Frnsr avec la methode getFrtbyid de lobjDao
		Fournisseur fsr = new FournisseurDao().getfrnsrById(idUser);
		System.out.println("Saisir nom du fournisseur Max(5) carateres");			
		String userPrt;
		clavier.nextLine();
		do {
			userPrt = clavier.nextLine();
		} while (userPrt.length() >5); 

		fsr.setNom(userPrt);
		System.out.println("Saisir ville à modifier");
		fsr.setVille(clavier.nextLine());

		new FournisseurDao().save(fsr);
	}


	public static void modifierUnPaiement(){

		System.out.println("####### Modifier un Paiement #######");
		new PaiementDao().getAllPayment().forEach((p)->{
			System.out.println("{ id : " +p.getId() +" ,montant :"+p.getMontant()+ " }");
		});	
		int idP;
		do {
			System.out.println("Saisir un des ID du Paiement ci dessus");
			idP = clavier.nextInt();
		} while (new PaiementDao().getPaymtById(idP)==null);

		System.out.println("Liste ID Commande à choisir impérativement pour updater");
		new CommandeDao().getAllCmde().forEach((cmd)->System.out.println("{id :" +cmd.getId()
		+" ,id_client : "+ cmd.getId_client()+ " }"));
		
		int idCmd;
		do {
			System.out.println("Saisir ID commande => id_facture");
			idCmd = clavier.nextInt();
		} while (new CommandeDao().getCmdeById(idCmd)==null);

		Paiement p = new PaiementDao().getPaymtById(idP);

		p.setId_facture(idCmd);

		
		System.out.println("Saisir montant à modifier");
		p.setMontant(clavier.nextFloat());

		clavier.nextLine();
		String usrDate;
		String fmtValid = "yyyy-mm-dd";
		do {
			System.out.println("Saisir une date au format (YYYY-MM-DD)");
			usrDate = clavier.nextLine();				
		} while (isvalidDate(usrDate, fmtValid)!=true);

		p.setDateP(usrDate);

		new PaiementDao().save(p);
	}

// =============================== FUUNCTION SEARCHING ===============
	public static void rechercherUnProduit(){
		System.out.println("####### Rechercher un Produit #######");
		String wUser;
        boolean continuer=true;
		//flash
		clavier.nextLine();
        do{
			System.out.println("Saisir le titre du Produit");
			wUser =clavier.nextLine();

			//Sortir de la boucle si le wUser correspond au regex
            if(wUser.matches("[a-zA-Z]")) continuer=false;//Regex=> toutes les lettres  min et maj
        }while(continuer);

		new ProduitDao().rechPrdct(wUser).forEach((p)->{
			System.out.println("{ id : " +p.getId() + ",titre :" +p.getTitre() +" ,prix : "+p.getPrix()+
				" id_cat :" + new CategorieDao().getCatById(p.getId_categorie()).getId()+ " => "+
				new CategorieDao().getCatById(p.getId_categorie()).getTitre() + " }"
			);
		});
	}


	public static void rechercherUnClient(){
		System.out.println("####### Rechercher un Client #######");
		String wUser;
        boolean continuer=true;
		//flash
		clavier.nextLine();
        do{
			System.out.println("Saisir le nom du Client");
			wUser =clavier.nextLine();

			//Sortir de la boucle si le wUser correspond au regex
            if(wUser.matches("[a-zA-Z]")) continuer=false;//Regex=> toutes les lettres  min et maj
        }while(continuer);

		new ClientDao().rechFrnsr(wUser).forEach((cl)->{
			System.out.println("{ id :" +cl.getId() +" ,nom :" +cl.getNom()+
			" ,ville :" +cl.getVille()+ " ,age :" +cl.getAge()+ " ,prénom : "+cl.getPrenom()+ " }"
			);
		});

	}


	public static void rechercherUnFournisseur(){
		System.out.println("####### Rechercher un fournisseur #######");

		String wUser;
        boolean continuer=true;
		//flash
		clavier.nextLine();
        do{
			System.out.println("Saisir la ville du fournisseur");
			wUser =clavier.nextLine();

            if(wUser.matches("[a-zA-Z]")) continuer=false;//Regex=> toutes les lettres  min et maj
        }while(continuer);
        
		new FournisseurDao().rechFrnsr(wUser).forEach((w)->{
			System.out.println("{ id : " + w.getId() + " , nom : " + w.getNom() + " ,ville : "+w.getVille()+ " }");
		});
	}

// ===============================  DELETING ========================
	public static void supprimerUnProduit(){
		// System.out.println("####### Supprimer un produit #######");
		// ProduitDao pdao=new ProduitDao();
		// for(Produit p:pdao.getAllProducts()) {
		// 	System.out.println(p.getId()+" - "+p.getTitre());
		// }
		// System.out.println("Donnez l'id : ");
		// int id=clavier.nextInt();
		// Produit p=pdao.getPrdtById(id);
		// if(p==null) {
		// 	System.out.println("L'id n'existe pas ! ");
		// }else {
		// 	pdao.deletePrdtById(id);
		// }

	}

	public static void supprimerUnClient(){}

	public static void supprimerUneCatégorie(){}

	public static void supprimerUneCommande(){}

	public static void supprimerUnFournisseur(){}

	public static void supprimerUneEntréeEnStock(){}

	public static void supprimerUnPaiement(){}


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


