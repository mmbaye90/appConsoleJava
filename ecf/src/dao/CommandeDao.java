package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Commande;
import entites.Db;

public class CommandeDao {

// ====================== RETURN FUNCTIONS ==================================
    public ArrayList <Commande> getAllCmde(){
        ArrayList <Commande> listeCmde = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM commande");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Commande cmde = new Commande();
                cmde.setId(resp.getInt("id"));
                cmde.setdateF(resp.getString("dateF"));
                cmde.setId_client(resp.getInt("id_client"));
                listeCmde.add(cmde);
            }
            return listeCmde;
        } catch (Exception e) {
            System.err.println("Liste cmde introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }
    public Commande getCmdeById(int id){
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM commande WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Commande cmde = new Commande();
                cmde.setId(resp.getInt("id"));
                cmde.setdateF(resp.getString("dateF"));
                cmde.setId_client(resp.getInt("id_client"));
                return cmde;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet cmde introuvable");
            e.printStackTrace();
            return null;
        }
    }

// ====================== VOID FUNCTIONS ==================================
    public void save(Commande cmde) {
            
        try {
            
            if(cmde.getId() != 0) {
                PreparedStatement ps  = Db.con.prepareStatement
                ("UPDATE commande SET dateF=?,id_client=? WHERE id=?");
                ps.setString(1,cmde.getdateF());
                ps.setInt(2, cmde.getId_client());
                ps.setInt(3, cmde.getId());
                ps.executeUpdate();
                System.out.println("Mise à jour effectuée".toUpperCase());
            }else {
                PreparedStatement ps  = Db.con.prepareStatement
                ("INSERT INTO client (dateF,id_client) VALUES(?,?)");
                ps.setString(1,cmde.getdateF());
                ps.setInt(2,cmde.getId_client());
                ps.executeUpdate();
                System.out.println("insertion Réussie".toUpperCase());
            }
            
        } catch (Exception e) {
            System.err.println("update ou insertion échouée".toUpperCase());
            e.printStackTrace();
        }

    }

    public void deleteCmdeById(int id) {
        try {
            
                PreparedStatement ps  = Db.con.prepareStatement
                ("DELETE FROM commande WHERE id=?");
                ps.setInt(1,id);
                ps.executeUpdate();
                System.out.println("Supprimé".toUpperCase());
            
        } catch (Exception e) {
            System.out.println("non suppriùé".toLowerCase());
            e.printStackTrace();
        }
    }


}
