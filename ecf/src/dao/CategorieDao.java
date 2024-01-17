package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Categorie;
import entites.Db;

public class CategorieDao {

// ====================== RETURN FUNCTIONS ==================================
    public ArrayList <Categorie> getAllCat(){
        ArrayList <Categorie> listCat = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM categorie");
            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Categorie cat = new Categorie();
                cat.setId(resp.getInt("id"));
                cat.setTitre(resp.getString("titre"));
                listCat.add(cat);
            }
            return listCat;
        } catch (Exception e) {
            System.err.println("Liste catégorie vide ".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    
    public Categorie getCatById(int id){
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM categorie where id = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Categorie cat = new Categorie();
                cat.setId(resp.getInt("id"));
                cat.setTitre(resp.getString("titre"));
                return cat;                
            }else return null; 
        } catch (Exception e) {
            System.err.println("catégorie non dispo".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

// ====================== VOID FUNCTIONS ==================================
public void save(Categorie cat) {
		
    try {
        
        if(cat.getId() != 0) {
            PreparedStatement ps  = Db.con.prepareStatement
            ("UPDATE categorie set titre=? WHERE id=?");
            ps.setString(1,cat.getTitre());
            ps.setInt(2,cat.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée".toUpperCase());
        }else {
            PreparedStatement preparedStatement  = Db.con.prepareStatement
            ("INSERT INTO categorie (titre) VALUES(?)");
            preparedStatement.setString(1,cat.getTitre());
            preparedStatement.executeUpdate();
            System.out.println("insertion Réussie".toUpperCase());
        }
        
    } catch (Exception e) {
        System.err.println("update ou insertion échouée".toUpperCase());
        e.printStackTrace();
    }

}

public void deleteCatById(int id) {
	try {
			PreparedStatement ps  = Db.con.prepareStatement
            ("DELETE FROM produit WHERE id_categorie=?");
			ps.setInt(1,id);
			ps.executeUpdate();

            PreparedStatement ps2 = Db.con.prepareStatement
            ("DELETE FROM categorie WHERE id=?");
            ps2.setInt(1, id);
            ps2.executeUpdate();

            System.out.println("Catégorie et prdt supprimés".toUpperCase());
	} catch (Exception e) {
        System.out.println("non suppriùé".toLowerCase());
    	e.printStackTrace();
    }
}

}

