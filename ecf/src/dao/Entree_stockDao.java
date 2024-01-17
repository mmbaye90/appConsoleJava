package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import entites.Db;
import entites.Entree_stock;

public class Entree_stockDao {
// ====================== RETURN FUNCTIONS ==================================
    public ArrayList <Entree_stock> getAllEntStock(){
        ArrayList<Entree_stock>  lisStock = new ArrayList<>();
        try {
                PreparedStatement ps = Db.con.prepareStatement
                ("SELECT * FROM entree_stock");

                ResultSet res = ps.executeQuery();

                while (res.next()) {
                    Entree_stock entreeStock = new Entree_stock();
                    entreeStock.setId(res.getInt("id"));
                    entreeStock.setId_produit(res.getInt("id_produit"));
                    entreeStock.setId_fournisseur(res.getInt("id_fournisseur"));
                    entreeStock.setQuantite(res.getInt("quantite"));
                    entreeStock.setdateE(res.getString("dateE"));
                    lisStock.add(entreeStock);
                }
                return lisStock;
        } catch (Exception e) {
            System.err.println("Liste des Stock indisponnible".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    public Entree_stock getEntStckById(int id){
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM entree_stock WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Entree_stock entStck = new Entree_stock();
                entStck.setId(resp.getInt("id"));
                entStck.setId_produit(resp.getInt("id_produit"));
                entStck.setId_fournisseur(resp.getInt("id_fournisseur"));
                entStck.setQuantite(resp.getInt("quantite"));
                entStck.setdateE(resp.getString("dateE"));
                return entStck;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet entStck introuvable");
            e.printStackTrace();
            return null;
        }
    }

// ====================== VOID FUNCTIONS ==================================
    public void save(Entree_stock entStck) {
            
        try {
            
            if(entStck.getId() != 0) {
                PreparedStatement ps  = Db.con.prepareStatement
                ("UPDATE entree_stock SET id_produit=?,id_fournisseur=?,quantite=?,dateE=? WHERE id=?");
                ps.setInt(1,entStck.getId_produit());
                ps.setInt(2, entStck.getId_fournisseur());
                ps.setInt(3, entStck.getQuantite());
                ps.setString(4, entStck.getdateE());
                ps.setInt(5, entStck.getId());
                ps.executeUpdate();
                System.out.println("Mise à jour effectuée".toUpperCase());
            }else {
                PreparedStatement ps  = Db.con.prepareStatement
                ("INSERT INTO entree_stock (id_produit,id_fournisseur,quantite,dateE) VALUES(?,?,?,?)");
                ps.setInt(1,entStck.getId_produit());
                ps.setInt(2,entStck.getId_fournisseur());
                ps.setInt(3,entStck.getQuantite());
                ps.setString(4,entStck.getdateE());
                ps.executeUpdate();
                System.out.println("insertion Réussie".toUpperCase());
            }
            
        } catch (Exception e) {
            System.err.println("update ou insertion échouée".toUpperCase());
            e.printStackTrace();
        }

    }

    public void deleteEntStcktById(int id) {
        try {
            
                PreparedStatement ps  = Db.con.prepareStatement
                ("DELETE FROM entree_stock WHERE id=?");
                ps.setInt(1,id);
                ps.executeUpdate();
                System.out.println("Supprimé".toUpperCase());
            
        } catch (Exception e) {
            System.out.println("non suppriùé".toLowerCase());
            e.printStackTrace();
        }
    }

}
