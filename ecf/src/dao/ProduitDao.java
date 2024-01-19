package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.Produit;

public class ProduitDao {
// ====================== RETURN FUNCTIONS ==================================
    public ArrayList <Produit> getAllProducts(){
        ArrayList<Produit> listPrducts = new ArrayList<>();

        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM produit ");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Produit prdt = new Produit();
                prdt.setId(resp.getInt("id"));
                prdt.setTitre(resp.getString("titre"));
                prdt.setPrix(resp.getFloat("prix"));
                prdt.setId_categorie(resp.getInt("id_categorie"));
                prdt.setStock(resp.getInt("stock"));
                listPrducts.add(prdt);
            }
            return listPrducts;
        } catch (Exception e) {
            System.err.println("Liste produit non dispo".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    public Produit getPrdtById(int id){
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM produit WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Produit prd = new Produit();
                prd.setId(resp.getInt("id"));
                prd.setTitre(resp.getString("titre"));
                prd.setPrix(resp.getFloat("prix"));
                prd.setId_categorie(resp.getInt("id_categorie"));
                prd.setStock(resp.getInt("stock"));
                return prd;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet client introuvable");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList <Produit> rechPrdct(String w){
        ArrayList <Produit> listPrdt = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * from produit WHERE titre LIKE ? ORDER BY titre");
            ps.setString(1, "%"+w+"%");
            ResultSet resp = ps.executeQuery();
            while (resp.next()) {
                Produit pdt = new Produit();
                pdt.setId(resp.getInt("id"));
                pdt.setTitre(resp.getString("titre"));
                pdt.setPrix(resp.getFloat("prix"));
                pdt.setId_categorie(resp.getInt("id_categorie"));
                pdt.setStock(resp.getInt("stock"));
                listPrdt.add(pdt);
            }
            return listPrdt;
        } catch (Exception e) {
            System.err.println("Mot introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    public int getCountprdByIdCat(int id){
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT COUNT(*) AS nbrPrdts from produit WHERE id_categorie =?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            resp.next();
            int nbr = resp.getInt("nbrPrdts");
            return nbr;
        } catch (Exception e) {
            System.err.println("nbPrduit non dispo".toUpperCase());
            e.printStackTrace();
            return 0;
        }
    }
// ====================== VOID FUNCTIONS ==================================
    public void save(Produit prdt) {
            
        try {
            
            if(prdt.getId() != 0) {
                PreparedStatement ps  = Db.con.prepareStatement
                ("UPDATE produit SET titre=?,prix=?,id_categorie=?,stock=? WHERE id=?");
                ps.setString(1,prdt.getTitre());
                ps.setFloat(2, prdt.getPrix());
                ps.setInt(3, prdt.getId_categorie());
                ps.setInt(4, prdt.getStock());
                ps.setInt(5, prdt.getId());
                ps.executeUpdate();
                System.out.println("Mise à jour effectuée".toUpperCase());
            }else {
                PreparedStatement ps  = Db.con.prepareStatement
                ("INSERT INTO produit (titre,prix,id_categorie,stock) VALUES(?,?,?,?)");
                ps.setString(1,prdt.getTitre());
                ps.setFloat(2,prdt.getPrix());
                ps.setInt(3,prdt.getId_categorie());
                ps.setInt(4,prdt.getStock());
                ps.executeUpdate();
                System.out.println("insertion Réussie".toUpperCase());
            }
            
        } catch (Exception e) {
            System.err.println("update ou insertion échouée".toUpperCase());
            e.printStackTrace();
        }

    }

    public void deletePrdtById(int id) {
        try {
            
                PreparedStatement ps  = Db.con.prepareStatement
                ("DELETE FROM produit WHERE id=?");
                ps.setInt(1,id);
                ps.executeUpdate();
                System.out.println("Supprimé".toUpperCase());
            
        } catch (Exception e) {
            System.out.println("non suppriùé".toLowerCase());
            e.printStackTrace();
        }
    }

}
