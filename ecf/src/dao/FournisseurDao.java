package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.Fournisseur;

public class FournisseurDao {
// ====================== RETURN FUNCTIONS ==================================
     public ArrayList <Fournisseur> getAllFourni(){
        ArrayList <Fournisseur> lisFournis = new ArrayList<>();
        try {
            PreparedStatement prepStmnt = Db.con.prepareStatement
            ("SELECT * FROM fournisseur");

            ResultSet resp = prepStmnt.executeQuery();

            while (resp.next()) {
                Fournisseur fournsr = new Fournisseur();
                fournsr.setId(resp.getInt("id"));
                fournsr.setNom(resp.getString("nom"));
                fournsr.setVille(resp.getString("ville"));
                lisFournis.add(fournsr);
            }
            
            return lisFournis;

        } catch (Exception e) {
            System.err.println("Données indisponnibles !!".toUpperCase());
            e.printStackTrace();
            return null;
        }
     }

    public Fournisseur getfrnsrById(int id){
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM fournisseur WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Fournisseur frnsr = new Fournisseur();
                frnsr.setId(resp.getInt("id"));
                frnsr.setNom(resp.getString("nom"));
                frnsr.setVille(resp.getString("ville"));
                return frnsr;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet frnsr introuvable");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList <Fournisseur> rechFrnsr(String w){
        ArrayList <Fournisseur> listFrnsr = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * from fournisseur WHERE ville LIKE ? ORDER BY ville");
            ps.setString(1, "%"+w+"%");
            ResultSet resp = ps.executeQuery();
            while (resp.next()) {
                Fournisseur fsr = new Fournisseur();
                fsr.setId(resp.getInt("id"));
                fsr.setNom(resp.getString("nom"));
                fsr.setVille(resp.getString("ville"));
                listFrnsr.add(fsr);
            }
            return listFrnsr;
        } catch (Exception e) {
            System.err.println("Mot introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

// ====================== VOID FUNCTIONS ==================================
    public void save(Fournisseur frsr) {
            
        try {
            
            if(frsr.getId() != 0) {
                PreparedStatement ps  = Db.con.prepareStatement
                ("UPDATE fournisseur SET nom=?,ville=? WHERE id=?");
                ps.setString(1,frsr.getNom());
                ps.setString(2, frsr.getVille());
                ps.setInt(3, frsr.getId());
                ps.executeUpdate();
                System.out.println("Mise à jour effectuée".toUpperCase());
            }else {
                PreparedStatement ps  = Db.con.prepareStatement
                ("INSERT INTO fournisseur (nom,ville) VALUES(?,?)");
                ps.setString(1,frsr.getNom());
                ps.setString(2,frsr.getVille());
                ps.executeUpdate();
                System.out.println("insertion Réussie".toUpperCase());
            }
            
        } catch (Exception e) {
            System.err.println("update ou insertion échouée".toUpperCase());
            e.printStackTrace();
        }

    }
    public void deleteFrnrsById(int id) {
        try {
            
                PreparedStatement ps  = Db.con.prepareStatement
                ("DELETE FROM fournisseur WHERE id=?");
                ps.setInt(1,id);
                ps.executeUpdate();
                System.out.println("Supprimé".toUpperCase());
            
        } catch (Exception e) {
            System.out.println("non suppriùé".toLowerCase());
            e.printStackTrace();
        }
    }


}
