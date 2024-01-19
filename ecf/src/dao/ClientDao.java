package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Client;
import entites.Db;

public class ClientDao {

// ====================== RETURN FUNCTIONS ==================================
        public ArrayList <Client> getAllClient(){
        ArrayList <Client> listeClient = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM client");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Client client = new Client();
                client.setId(resp.getInt("id"));
                client.setNom(resp.getString("nom"));
                client.setVille(resp.getString("ville"));
                client.setAge(resp.getInt("age"));
                client.setPrenom(resp.getString("prenom"));
                listeClient.add(client);
            }
            return listeClient;
        } catch (Exception e) {
            System.err.println("Liste client introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    public Client getClientById(int id){
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM client WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Client clt = new Client();
                clt.setId(resp.getInt("id"));
                clt.setNom(resp.getString("nom"));
                clt.setVille(resp.getString("ville"));
                clt.setAge(resp.getInt("age"));
                clt.setPrenom(resp.getString("prenom"));
                return clt;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet client introuvable");
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList <Client> rechFrnsr(String w){
        ArrayList <Client> listClt = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * from client WHERE nom LIKE ? ORDER BY nom");
            ps.setString(1, "%"+w+"%");
            ResultSet resp = ps.executeQuery();
            while (resp.next()) {
                Client clt = new Client();
                clt.setId(resp.getInt("id"));
                clt.setNom(resp.getString("nom"));
                clt.setVille(resp.getString("ville"));
                clt.setAge(resp.getInt("age"));
                clt.setPrenom(resp.getString("prenom"));
                listClt.add(clt);
            }
            return listClt;
        } catch (Exception e) {
            System.err.println("Mot introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

// ====================== VOID FUNCTIONS ==================================
    public void save(Client clt) {
            
        try {
            
            if(clt.getId() != 0) {
                PreparedStatement ps  = Db.con.prepareStatement
                ("UPDATE client SET nom=?,ville=?,age=?,prenom=? WHERE id=?");
                ps.setString(1,clt.getNom());
                ps.setString(2, clt.getVille());
                ps.setInt(3, clt.getAge());
                ps.setString(4, clt.getPrenom());
                ps.setInt(5, clt.getId());
                ps.executeUpdate();
                System.out.println("Mise à jour effectuée".toUpperCase());
            }else {
                PreparedStatement ps  = Db.con.prepareStatement
                ("INSERT INTO client (nom,ville,age,prenom) VALUES(?,?,?,?)");
                ps.setString(1,clt.getNom());
                ps.setString(2,clt.getVille());
                ps.setInt(3,clt.getAge());
                ps.setString(4,clt.getPrenom());
                ps.executeUpdate();
                System.out.println("insertion Réussie".toUpperCase());
            }
            
        } catch (Exception e) {
            System.err.println("update ou insertion échouée".toUpperCase());
            e.printStackTrace();
        }

    }

    public void deleteCltById(int id) {
        try {
            
                PreparedStatement ps  = Db.con.prepareStatement
                ("DELETE FROM client WHERE id=?");
                ps.setInt(1,id);
                ps.executeUpdate();
                System.out.println("Supprimé".toUpperCase());
            
        } catch (Exception e) {
            System.out.println("non suppriùé".toLowerCase());
            e.printStackTrace();
        }
    }
    
}
