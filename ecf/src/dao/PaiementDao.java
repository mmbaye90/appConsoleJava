package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.Paiement;

public class PaiementDao {
// ====================== RETURN FUNCTIONS ==================================
        public ArrayList <Paiement> getAllPayment(){
        ArrayList <Paiement> listPaymnt = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM paiement");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Paiement paymnt = new Paiement();
                paymnt.setId(resp.getInt("id"));
                paymnt.setId_facture(resp.getInt("id_facture"));
                paymnt.setMontant(resp.getFloat("montant"));
                paymnt.setDateP(resp.getString("dateP"));
                listPaymnt.add(paymnt);
            }
            return listPaymnt;
        } catch (Exception e) {
            System.err.println("Liste des paiement non dispo".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }
    public Paiement getPaymtById(int id){
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM paiement WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Paiement pmt = new Paiement();
                pmt.setId(resp.getInt("id"));
                pmt.setId_facture(resp.getInt("id_facture"));
                pmt.setMontant(resp.getFloat("montant"));
                pmt.setDateP(resp.getString("dateP"));
                return pmt;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet pmt introuvable");
            e.printStackTrace();
            return null;
        }
    }
// ====================== VOID FUNCTIONS ==================================
    public void save(Paiement pymt) {
            
        try {
            
            if(pymt.getId() != 0) {
                PreparedStatement ps  = Db.con.prepareStatement
                ("UPDATE paiement SET id_facture=?,montant=?,dateP=? WHERE id=?");
                ps.setInt(1,pymt.getId_facture());
                ps.setFloat(2, pymt.getMontant());
                ps.setString(3, pymt.getDateP());
                ps.setInt(4, pymt.getId());
                ps.executeUpdate();
                System.out.println("Mise à jour effectuée".toUpperCase());
            }else {
                PreparedStatement ps  = Db.con.prepareStatement
                ("INSERT INTO client (id_facture,montant,dateP) VALUES(?,?,?)");
                ps.setInt(1,pymt.getId_facture());
                ps.setFloat(2,pymt.getMontant());
                ps.setString(3,pymt.getDateP());
                ps.executeUpdate();
                System.out.println("insertion Réussie".toUpperCase());
            }
            
        } catch (Exception e) {
            System.err.println("update ou insertion échouée".toUpperCase());
            e.printStackTrace();
        }

    }

    public void deletePymtById(int id) {
        try {
            
                PreparedStatement ps  = Db.con.prepareStatement
                ("DELETE FROM paiement WHERE id=?");
                ps.setInt(1,id);
                ps.executeUpdate();
                System.out.println("Supprimé".toUpperCase());
            
        } catch (Exception e) {
            System.out.println("non suppriùé".toLowerCase());
            e.printStackTrace();
        }
    }


}
