package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.Details;

public class DetailsDao {
// ====================== RETURN FUNCTIONS ==================================
    public ArrayList<Details> getAllDetails(){
        ArrayList<Details> listDetails = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM details");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Details det = new Details();
                det.setId(resp.getInt("id"));
                det.setQuantite(resp.getInt("quantite"));
                det.setPrixU(resp.getFloat("prixU"));
                det.setId_commande(resp.getInt("id_commande"));
                det.setId_produit(resp.getInt("id_produit"));
                listDetails.add(det);
            }
            return listDetails;
        } catch (Exception e) {
            System.err.println("Liste details non Dsispo ".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    public Details getDetById(int id){
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM details WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Details dtl = new Details();
                dtl.setId(resp.getInt("id"));
                dtl.setQuantite(resp.getInt("quantite"));
                dtl.setPrixU(resp.getFloat("prixU"));
                dtl.setId_commande(resp.getInt("id_commande"));
                dtl.setId_produit(resp.getInt("id_produit"));
                return dtl;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet Dtl introuvable");
            e.printStackTrace();
            return null;
        }
    }

// ====================== VOID FUNCTIONS ==================================
    public void save(Details clt) {
            
        try {
            
            if(clt.getId() != 0) {
                PreparedStatement ps  = Db.con.prepareStatement
                ("UPDATE details SET quantite=?,prixU=?,id_commande=?,id_produit=? WHERE id=?");
                ps.setInt(1,clt.getQuantite());
                ps.setFloat(2, clt.getPrixU());
                ps.setInt(3, clt.getId_commande());
                ps.setInt(4, clt.getId_produit());
                ps.setInt(5, clt.getId());
                ps.executeUpdate();
                System.out.println("Mise à jour effectuée".toUpperCase());
            }else {
                PreparedStatement ps  = Db.con.prepareStatement
                ("INSERT INTO client (quantite,prixU,id_commande,id_produit) VALUES(?,?,?,?)");
                ps.setInt(1,clt.getQuantite());
                ps.setFloat(2,clt.getPrixU());
                ps.setInt(3,clt.getId_commande());
                ps.setInt(4,clt.getId_produit());
                ps.executeUpdate();
                System.out.println("insertion Réussie".toUpperCase());
            }
            
        } catch (Exception e) {
            System.err.println("update ou insertion échouée".toUpperCase());
            e.printStackTrace();
        }

    }

    public void deleteDetailsById(int id) {
        try {
            
                PreparedStatement ps  = Db.con.prepareStatement
                ("DELETE FROM details WHERE id=?");
                ps.setInt(1,id);
                ps.executeUpdate();
                System.out.println("Supprimé".toUpperCase());
            
        } catch (Exception e) {
            System.out.println("non suppriùé".toLowerCase());
            e.printStackTrace();
        }
    }

  
}
