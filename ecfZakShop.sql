CREATE DATABASE IF NOT EXISTS ecfShop2;
USE ecfShop2;

-- CREAATION DES TABLES

CREATE TABLE fournisseur(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(5),
    ville VARCHAR(50)
);

CREATE TABLE entree_stock(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    id_produit INT,
    id_fournisseur INT,
    quantite INT,
    dateE date
);

CREATE TABLE produit(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(50),
    prix FLOAT,
    id_categorie INT,
    stock INT
);

CREATE TABLE categorie(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(50)
);


CREATE TABLE details(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    quantite INT,
    prixU FLOAT,
    id_commande INT,
    id_produit INT
);

CREATE TABLE commande(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    dateF date,
    id_client INT
);


CREATE TABLE client(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(40),
    ville VARCHAR(40),
    age INT,
    prenom VARCHAR(40)
);

CREATE TABLE paiement(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    id_facture INT,
    montant FLOAT,
    dateP date
);


-- ============================= CREATION DES CONTRAINTES D'INTEGRIT3S =====================
ALTER TABLE entree_stock
    ADD CONSTRAINT fk_produit
    FOREIGN KEY(id_produit) REFERENCES produit(id) ON UPDATE CASCADE ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UN PRODUIT, JE SUPPRIME LES ENT_ST QUI LUI SONT ASSOCIEES

ALTER TABLE entree_stock
    ADD CONSTRAINT fk_fournisseur
    FOREIGN KEY(id_fournisseur) REFERENCES fournisseur(id) ON UPDATE CASCADE ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UN FOURNISSEUR, JE SUPPRIME LES ENT_ST QUI LUI SONT ASSOCIEES


ALTER TABLE produit
    ADD CONSTRAINT fk_categorie
    FOREIGN KEY(id_categorie) REFERENCES categorie(id)  ON UPDATE CASCADE ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UNE CATEGORIE, JE SUPPRIME LES PRDTS QUI LUI SONT ASSOCIES



ALTER TABLE details
    ADD CONSTRAINT fk_commande
    FOREIGN KEY(id_commande) REFERENCES commande(id) ON UPDATE CASCADE ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UNE COMMANDE, JE SUPPRIME LES DETAILS QUI LUI SONT ASSOCIES

ALTER TABLE details
    ADD CONSTRAINT fk_produitdet
    FOREIGN KEY(id_produit) REFERENCES produit(id) ON UPDATE CASCADE ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UN PRODUIT, JE SUPPRIME LES DETAILS QUI LUI SONT ASSOCIES


ALTER TABLE commande
    ADD CONSTRAINT fk_commadeClient
    FOREIGN KEY(id_client) REFERENCES client(id) ON UPDATE CASCADE ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UN CLIENT, JE SUPPRIME LES COMMANDES QUI LUI SONT ASSOCIEES


ALTER TABLE paiement
    ADD CONSTRAINT fk_commadeP
    FOREIGN KEY(id_facture) REFERENCES commande(id) ON UPDATE CASCADE ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UNE FACTURE, JE SUPPRIME LES PMTS QUI LUI SONT ASSOCIES


-- ================= INSERTION DE QUELSES DONNEES DANS LES TABLES
INSERT INTO fournisseur(nom,ville)
VALUES
('ALIB','HONG KONG'),
('FNAC','Paris'),
('AMZ','New York');


INSERT INTO categorie(titre)
VALUES
('SMARTPHONE'),
('TV'),
('Jeux vid'),
('Electro'),
('Brico'),
('ORDINATEURS');


INSERT INTO produit(titre,prix,id_categorie,stock)
VALUES
('Iphone50',4000,1,20),     
('Samsung Hd45',2000,1,25), 
('Galaxy S3',1400,2,5),     
('Sams Led 5k',1700,2,5),  
('Zrnzus pro',500,2,19),
('PS5',800,3,5),           
('ps4',200,3,15),           
('asp',500,4,19),          
('cet vap',200,4,29),    
('pur air',300,4,20),    
('robot tond',400,5,39),       
('tronç',100,5,29), 
('Hp pro',1000,6,49),          
('Hp pro',1000,6,49),            
('MAc book',1200,6,19),           
('Lenovo',1100,6,59),           
('Del lat',299,6,50);



INSERT INTO client(nom,ville,age,prenom)
VALUES
('MBAYE','Dakar',33,'Mamadou'),
('MNDIACK','MAdagascar',32,'Yves'),
('DUCOQ','PAris',31,'Jean Mathieu'),
('ALI','MAroc',34,'Abdel'),
('BOUZIDI','Alger',33,'Mohammed'),
('GENS','Paris',33,'Jonathan'),
('LEMAIRE','Paris',30,'Sophie'),
('REMOS','Marseille',30,'Remy'),
('SINCE','Normandie',32,'Manu'),
('LEBON','Paris',33,'Ludovic'),
('LEBON','Paris',33,'Cedric'),
('KASSIMI','MAroc',33,'Zak'),
('LEDUC','PAris',31,'Claire'),
('MYETTE','PAris',33,'MArichou'),
('STLOUIS','Lille',32,'Louis r'),
('FARID','Toulouse',33,'Farid');



INSERT INTO commande(dateF,id_client)
VALUES
('2023-10-27',1),
('2022-10-27',1),
('2020-10-27',1),
('2023-01-15',2),
('2021-01-15',2),
('2019-10-27',3),
('2022-11-17',4),
('2022-09-26',5),
('2022-02-28',6),
('2022-03-13',7),
('2023-12-15',8);


INSERT INTO paiement(id_facture,montant,dateP)
VALUES
(1,4000,'2024-01-18'),
(2,700,'2024-01-15'),
(3,300,'2024-01-20'),
(4,1000,'2024-01-18'),
(5,3800,'2024-10-19');

INSERT INTO details(quantite,prixU,id_commande,id_produit)
VALUES
(171,4500,1,1),
(62,2000,2,2),
(42,2300,2,5),
(52,1000,2,3),
(58,1900,4,6);

INSERT INTO entree_stock(id_produit,id_fournisseur,quantite,dateE)
VALUES
(1,1,50,'2023-10-28'),
(3,3,150,'2023-01-15'),
(4,2,150,'2023-01-15'),
(5,3,50,'2022-01-16'),
(6,1,50,'2022-09-29');

