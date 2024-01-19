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
				passerUneCommande();
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
				effectuerUnPaiement();
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
		if (pdao.getAllProducts().isEmpty()) {
			System.out.println("Liste des produits vide".toUpperCase());
		}else pdao.getAllProducts().forEach((p)->System.out.println(p));
	}


	public static void listeDesClients(){
		System.out.println("####### ClientList ###########");
		if (new ClientDao().getAllClient().isEmpty()) {
			System.out.println("Liste des clients vide".toUpperCase());
		}else new ClientDao().getAllClient().forEach((cl)->System.out.println(cl));;
	}


	public static void listeDesCatégories(){
		System.out.println("####### CategList ###########");
		if (new CategorieDao().getAllCat().isEmpty()) {
			System.out.println("Liste des catégorie vide");
		}else{
			new CategorieDao().getAllCat().forEach((cat)->{
				System.out.println(" {id : "+cat.getId() + " ,titre :" +cat.getTitre()+" ,nbPrdt : "
				+new ProduitDao().getCountprdByIdCat(cat.getId())+" }");
			});	
		}
	}


	public static void listeDesCommandes(){
		System.out.println("####### ComdList ###########");
		if (new CommandeDao().getAllCmde().isEmpty()) {
			System.out.println("Liste des Commandes vide");
		}else new CommandeDao().getAllCmde().forEach((cmd)->System.out.println(cmd));
	}


	public static void listeDesFournisseurs(){
		System.out.println("####### FournisList ###########");
		if(new FournisseurDao().getAllFourni().isEmpty()){
			System.out.println("Liste des Fournisseurs vide");
		}else new FournisseurDao().getAllFourni().forEach((fnr)-> System.out.println(fnr));
	}


	public static void listeDesEntréesEnStock(){
		System.out.println("####### EntStockList ###########");
		if (new Entree_stockDao().getAllEntStock().isEmpty()) {
			System.out.println("liste des entrées en stock vide");
		}else new Entree_stockDao().getAllEntStock().forEach((Es)-> System.out.println(Es));
	}


	public static void listeDesPaiements(){
		System.out.println("####### PymtList ###########");
		if(new PaiementDao().getAllPayment().isEmpty()){
			System.out.println("Liste des paiements vide");
		}else new PaiementDao().getAllPayment().forEach((pyt)->System.out.println(pyt));
	}


// =================================== ADDING  ============================
	public static void ajouterUnProduit(){
		System.out.println("####### Ajouter un produit #######");

		Produit prdt = new Produit();
		System.out.println("Titre du produit à ajouter");
		//flash
		clavier.nextLine();
		prdt.setTitre(clavier.nextLine());
		System.out.println("Prix du produit à ajouter");
		prdt.setPrix(clavier.nextFloat());

		new CategorieDao().getAllCat().forEach((cat)->{
			System.out.println("{ id : " +cat.getId() + " ,titre : " +cat.getTitre() + " }");
		});

		int idCat;
		do {
			System.out.println("Saisir un des ID des catégories ci dessus");
			idCat = clavier.nextInt();
		} while (new CategorieDao().getCatById(idCat)==null); //redmandé si ID retourne un objet null

		prdt.setId_categorie(idCat);

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

		Commande cmd = new Commande();

		System.out.println("####### Passer une Commande #######");
		//flash
		clavier.nextLine();
		
		//Vérification de la date si elle correspond au bon format de la BD
		String usrDate;
		String fmtValid = "yyyy-mm-dd";
		do {
			System.out.println("Saisir la dateF au format (YYYY-MM-DD)");
			usrDate = clavier.nextLine();				
		} while (isvalidDate(usrDate, fmtValid)!=true);

		cmd.setDateF(usrDate);

		System.out.println("===== Liste des ID des clients =========");
		new ClientDao().getAllClient().forEach((cl)->{
			System.out.println("{id : " + cl.getId()+ ",prénom :" +cl.getPrenom()+ " }");
		});

		int idCl;
		do {
			System.out.println("Saisir impérativement un des ID ci-dessus".toUpperCase());
			idCl = clavier.nextInt();
		} while (new ClientDao().getClientById(idCl)==null);

		cmd.setId_client(idCl);
		new CommandeDao().save(cmd);
	} 
	
	public static void ajouterUneCatégorie(){
		System.out.println("####### Ajouter une catégorie #######");
		Categorie cat = new Categorie();

		//flash
		clavier.nextLine();		
		System.out.println("Saisir le titre de la catégorie à ajouter");
		cat.setTitre(clavier.nextLine());
		new CategorieDao().save(cat);
	}

	public static void ajouterUnFournisseur(){
		System.out.println("####### Ajouter un Fournisseur #######");
		Fournisseur frsr = new Fournisseur();
		//flash
		clavier.nextLine();	

		//Garantir la conformité de la longueur des chaines de caract de la BD
		System.out.println("Saisir le nom du fournisseur maximum(5) carateres");			
		String userPrt;
		do {
			userPrt = clavier.nextLine();
		} while (userPrt.length() >5); 

		frsr.setNom(userPrt);
		System.out.println("Saisir la ville du fournisseur");
		frsr.setVille(clavier.nextLine());
		new FournisseurDao().save(frsr);
	}

	public static void effectuerUnPaiement(){
		System.out.println("############# Effectuer un paiement ###############");

			listeDesCommandes();

			int idC;
			do {
				System.out.println("Choisir impérativement un des ID des commandes".toUpperCase());
				idC = clavier.nextInt();				
			} while (new CommandeDao().getCmdeById(idC) == null);

			Paiement p = new Paiement();
			p.setId_facture(idC);
			
			//Vérifier si le montant n'est pas négatif ou égal 0
			float mont;
			do {
				System.out.println("Saisir le montant");
				mont = clavier.nextFloat();
			} while (mont<0 ||mont==0);
			p.setMontant(mont);
			
			//flash
			clavier.nextLine();
			String usrDate;
			String fmtValid = "yyyy-mm-dd";
			do {
				System.out.println("Saisir la dateF au format (YYYY-MM-DD)");
				usrDate = clavier.nextLine();				
			} while (isvalidDate(usrDate, fmtValid)!=true);
	
			p.setDateP(usrDate);

			new PaiementDao().save(p);

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

				int idPdt;
				do {
					System.out.println("Saisir impérativement un des ID des produits ci dessus".toUpperCase());
					idPdt = clavier.nextInt();
				} while (new ProduitDao().getPrdtById(idPdt)==null);
				Es.setId_produit(idPdt);

				//Afficher au user ID des fournisseurs
				System.out.println("Liste des fournisseurs");
				new FournisseurDao().getAllFourni().forEach((frsr)->{
					System.out.println("{ id :" +frsr.getId()+ " ,nom :" +frsr.getNom()+ " }");
				});

				int idF;
				do {
					System.out.println("Saisir impérativement un des ID des Fournisseurs ci dessus".toUpperCase());
					idF = clavier.nextInt();
				} while (new FournisseurDao().getfrnsrById(idF)==null);
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
			System.out.println("Saisir impérativement un des ID du produit ci dessus".toUpperCase());
			idP = clavier.nextInt();
		} while (new ProduitDao().getPrdtById(idP)==null);
		Produit p = new ProduitDao().getPrdtById(idP);

		clavier.nextLine();
		System.out.println("Saisir titre à modifier");
		p.setTitre(clavier.nextLine());

		System.out.println("Saisir Prix à modifier");
		p.setPrix(clavier.nextFloat());

		System.out.println("Liste ID Catégorie à choisir impérativement pour updater".toUpperCase());
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
		
		int idUser;
		do {
			System.out.println("Saisir impérativement l'ID du titre à modifier".toUpperCase());
			idUser = clavier.nextInt();	
		} while (new CategorieDao().getCatById(idUser)==null);

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
		
		int idUser;
		do {
			System.out.println("Saisir impérativement l'ID du Fournisseur à modifier".toUpperCase());
			idUser = clavier.nextInt();	
		} while (new FournisseurDao().getfrnsrById(idUser)==null);

		Fournisseur fsr = new FournisseurDao().getfrnsrById(idUser);

		System.out.println("Saisir nom du fournisseur: Max(5) carateres");			
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
		listeDesPaiements();
		int idP;
		do {
			System.out.println("Saisir impérativement un des ID du paiement ci dessus".toUpperCase());
			idP = clavier.nextInt();
		} while (new PaiementDao().getPaymtById(idP)==null);
		Paiement p = new PaiementDao().getPaymtById(idP);

		clavier.nextLine();

		System.out.println("Liste ID Commande(id_fact) à choisir impérativement pour updater".toUpperCase());
		new CommandeDao().getAllCmde().forEach((c)->System.out.println("{id_facture :" +c.getId()
		+" ,dateF : "+ c.getDateF()+ " }"));
		
		int idC;
		do {
			System.out.println("Saisir ID Catégorie");
			idC = clavier.nextInt();
		} while (new CommandeDao().getCmdeById(idC)==null);

		p.setId_facture(idC);
		
		float mt;
		do {
			System.out.println("Saisir montant à modifier");
			mt =clavier.nextFloat();
		} while (mt ==0 || mt <0);

		p.setMontant(mt);

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
		//flash
		clavier.nextLine();
		System.out.println("Saisir le titre du Produit");
		new ProduitDao().rechPrdct(clavier.nextLine()).forEach((p)->{
			System.out.println("{ id : " +p.getId() + ",titre :" +p.getTitre() +" ,prix : "+p.getPrix()+
				" id_cat :" + new CategorieDao().getCatById(p.getId_categorie()).getId()+ " => "+
				new CategorieDao().getCatById(p.getId_categorie()).getTitre() + " }"
			);
		});
	}


	public static void rechercherUnClient(){
		System.out.println("####### Rechercher un Client #######");
		//flash
		clavier.nextLine();
		
		System.out.println("Saisir le nom du Client");

		new ClientDao().rechFrnsr(clavier.nextLine()).forEach((cl)->{
			System.out.println("{ id :" +cl.getId() +" ,nom :" +cl.getNom()+
			" ,ville :" +cl.getVille()+ " ,age :" +cl.getAge()+ " ,prénom : "+cl.getPrenom()+ " }"
			);
		});

	}


	public static void rechercherUnFournisseur(){
		System.out.println("####### Rechercher un fournisseur #######");

		//flash
		clavier.nextLine();
		System.out.println("Saisir la ville du fournisseur");
        
		new FournisseurDao().rechFrnsr(clavier.nextLine()).forEach((w)->{
			System.out.println("{ id : " + w.getId() + " , nom : " + w.getNom() + " ,ville : "+w.getVille()+ " }");
		});
	}

// ===============================  DELETING ========================
	public static void supprimerUnProduit(){
		if (new ProduitDao().getAllProducts().isEmpty()) {
			menu();
		}
		listeDesProduits();
		int idP;
		do {
			System.out.println("Saisir l'ID du produit à supp");
			idP = clavier.nextInt();
		} while (new ProduitDao().getPrdtById(idP)==null);

		Produit prdt = new ProduitDao().getPrdtById(idP);

		System.out.println("ATTENTION LES détails et les En_stck ASS SERt SUP 1(CONF)/0(ANN)");

		int resp = clavier.nextInt();
		if (resp ==1) new ProduitDao().deletePrdtById(prdt.getId());
		else if (resp ==0) {
			System.out.println("NON SUPP");
			menu();
		}		

	}

	public static void supprimerUnClient(){
		if (new ClientDao().getAllClient().isEmpty()) {
			menu();
		}
		listeDesClients();
		int idC;
		do {
			System.out.println("Saisir l'ID du client à sup");
			idC = clavier.nextInt();
		} while (new ClientDao().getClientById(idC)==null);

		Client c = new ClientDao().getClientById(idC);

		System.out.println("ATTENTION LES Commandes ASS SERt SUP 1(CONF)/0(ANN)".toUpperCase());

		int resp = clavier.nextInt();
		if (resp ==1) new ClientDao().deleteCltById(c.getId());
		else if (resp ==0) {
			System.out.println("NON SUPP");
			menu();
		}		

	}

	public static void supprimerUneCatégorie(){
		if (new CategorieDao().getAllCat().isEmpty()) {
			menu();
		}
		listeDesCatégories();
		int idCat;
		do {
			System.out.println("Saisir l'ID d'une des catégorie");
			idCat = clavier.nextInt();
		} while (new CategorieDao().getCatById(idCat)==null);

		Categorie c = new CategorieDao().getCatById(idCat);

		System.out.println("ATTENTION LES PRDTS ASS SERt SUP 1(CONF)/0(ANN)");

		int resp = clavier.nextInt();
		if (resp ==1) new CategorieDao().deleteCatById(c.getId());
		else if (resp ==0) {
			System.out.println("NON SUPP");
			menu();
		}		
	}

	public static void supprimerUneCommande(){
		if (new CommandeDao().getAllCmde().isEmpty()) {
			menu();
		}
		listeDesCommandes();
		int idC;
		do {
			System.out.println("Saisir l'ID de la commde à sup");
			idC = clavier.nextInt();
		} while (new CommandeDao().getCmdeById(idC)==null);

		Commande c = new CommandeDao().getCmdeById(idC);

		System.out.println("ATTENTION LES pymt et les details ASS SERt SUP 1(CONF)/0(ANN)".toUpperCase());

		int resp = clavier.nextInt();
		if (resp ==1) new CommandeDao().deleteCmdeById(c.getId());
		else if (resp ==0) {
			System.out.println("NON SUPP");
			menu();
		}		

	}

	public static void supprimerUnFournisseur(){
		if (new FournisseurDao().getAllFourni().isEmpty()) {
			menu();
		}
		listeDesFournisseurs();
		int idC;
		do {
			System.out.println("Saisir l'ID du fournisseur à sup");
			idC = clavier.nextInt();
		} while (new FournisseurDao().getfrnsrById(idC)==null);

		Fournisseur fsr = new FournisseurDao().getfrnsrById(idC);

		System.out.println("ATTENTION LES Ent_en_Stck et les prdts du frnsr ASS SERt SUP 1(CONF)/0(ANN)".toUpperCase());

		int resp = clavier.nextInt();
		if (resp ==1) new FournisseurDao().deleteFrnrsById(fsr.getId());
		else if (resp ==0) {
			System.out.println("NON SUPP");
			menu();
		}		

	}

	public static void supprimerUneEntréeEnStock(){
		if (new Entree_stockDao().getAllEntStock().isEmpty()) {
			menu();
		}
		listeDesEntréesEnStock();
		int idEs;
		do {
			System.out.println("Saisir l'ID de l'ent_stock à sup");
			idEs = clavier.nextInt();
		} while (new Entree_stockDao().getEntStckById(idEs)==null);

		Entree_stock Es = new Entree_stockDao().getEntStckById(idEs);

		System.out.println("ATTENTION L'Entree_Stock sera SUP 1(CONF)/0(ANN)".toUpperCase());

		int resp = clavier.nextInt();
		if (resp ==1) new Entree_stockDao().deleteEntStcktById(Es.getId());
		else if (resp ==0) {
			System.out.println("NON SUPP");
			menu();
		}		

	}

	public static void supprimerUnPaiement(){
		if (new PaiementDao().getAllPayment().isEmpty()) {
			menu();
		}
		else  listeDesPaiements();
		int idP;
		do {
			System.out.println("Saisir l'ID de Paiement à sup");
			idP = clavier.nextInt();
		} while (new PaiementDao().getPaymtById(idP)==null);

		Paiement p = new PaiementDao().getPaymtById(idP);

		System.out.println("ATTENTION Le Paiement sera SUP 1(CONF)/0(ANN)".toUpperCase());

		int resp = clavier.nextInt();
		if (resp ==1) new PaiementDao().deletePymtById(p.getId());
		else if (resp ==0) {
			System.out.println("NON SUPP");
			menu();
		}		

	}


// =============================== UTILS FUNCTIONS ============
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


