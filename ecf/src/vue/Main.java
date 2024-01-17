package vue;

import java.util.Scanner;

import dao.CategorieDao;
import dao.ClientDao;
import dao.CommandeDao;
import dao.Entree_stockDao;
import dao.FournisseurDao;
import dao.PaiementDao;
import dao.ProduitDao;
import entites.Db;

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
				// AjouterUnProduit();
			}else if(choix==3) {
				// ModifierUnProduit();
			}else if(choix==4) {
				// SupprimerUnProduit();
			}else if(choix==5) {
				// RechercherUnProduit();
			}else if(choix==6) {
				listeDesClients();
			}else if(choix==7) {
				// AjouterUnClient();
			}else if(choix==8) {
				// ModifierUnClient();
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
				// AjouterUneCatégorie();
			}
			else if(choix==13) {
				// ModifierUneCatégorie();
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
				// AjouterUnFournisseur();
			}
			else if(choix==20) {
				// ModifierUnFournisseur();
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
				// AjouterUneEntréeEnStock();
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
				// ModifierUnPaiement();
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


}

