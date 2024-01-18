CREATE DATABASE IF NOT EXISTS ecfShop1;
USE ecfShop1;

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
    FOREIGN KEY(id_produit) REFERENCES produit(id)ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UN PRODUIT, JE SUPPRIME LES ENT_ST QUI LUI SONT ASSOCIEES

ALTER TABLE entree_stock
    ADD CONSTRAINT fk_fournisseur
    FOREIGN KEY(id_fournisseur) REFERENCES fournisseur(id)ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UN FOURNISSEUR, JE SUPPRIME LES ENT_ST QUI LUI SONT ASSOCIEES


ALTER TABLE produit
    ADD CONSTRAINT fk_categorie
    FOREIGN KEY(id_categorie) REFERENCES categorie(id) ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UNE CATEGORIE, JE SUPPRIME LES PRDTS QUI LUI SONT ASSOCIES



ALTER TABLE details
    ADD CONSTRAINT fk_commande
    FOREIGN KEY(id_commande) REFERENCES commande(id)ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UNE COMMANDE, JE SUPPRIME LES DETAILS QUI LUI SONT ASSOCIES

ALTER TABLE details
    ADD CONSTRAINT fk_produitdet
    FOREIGN KEY(id_produit) REFERENCES produit(id)ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UN PRODUIT, JE SUPPRIME LES DETAILS QUI LUI SONT ASSOCIES


ALTER TABLE commande
    ADD CONSTRAINT fk_commadeClient
    FOREIGN KEY(id_client) REFERENCES client(id)ON DELETE CASCADE;
    -- QUAND JE SUPPRIME UN CLIENT, JE SUPPRIME LES COMMANDES QUI LUI SONT ASSOCIEES


ALTER TABLE paiement
    ADD CONSTRAINT fk_commadeP
    FOREIGN KEY(id_facture) REFERENCES commande(id)ON DELETE CASCADE;
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
('ORDINATEURS');


INSERT INTO produit(titre,prix,id_categorie,stock)
VALUES
('Iphone50',4000,1,20),
('Samsung Hd45',2000,2,15),
('Hp Envi',700,3,5),
('Zrnzus pro',1000,1,19),
('Brandt',800,2,55),
('MAc Book',900,3,60);


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
('2023-10-27',3),
('2024-01-15',4),
('2022-10-27',6),
('2023-07-25',10);


INSERT INTO paiement(id_facture,montant,dateP)
VALUES
(4,5000,'2023-10-28'),
(2,7000,'2024-01-15'),
(3,4000,'2022-10-29');

INSERT INTO details(quantite,prixU,id_commande,id_produit)
VALUES
(1,4000,1,1),
(2,2000,3,2),
(5,900,4,6);

INSERT INTO entree_stock(id_produit,id_fournisseur,quantite,dateE)
VALUES
(1,2,50,'2023-10-28'),
(3,3,150,'2023-01-15'),
(6,1,50,'2022-09-29');

