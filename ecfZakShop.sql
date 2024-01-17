CREATE DATABASE IF NOT EXISTS ecfShop;
USE ecfShop;

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


-- CREATION DES CONTRAINTES D'INTEGRIT3S
ALTER TABLE entree_stock
    ADD CONSTRAINT fk_produit
    FOREIGN KEY(id_produit) REFERENCES produit(id);

ALTER TABLE entree_stock
    ADD CONSTRAINT fk_fournisseur
    FOREIGN KEY(id_fournisseur) REFERENCES fournisseur(id);


ALTER TABLE produit
    ADD CONSTRAINT fk_categorie
    FOREIGN KEY(id_categorie) REFERENCES categorie(id);



ALTER TABLE details
    ADD CONSTRAINT fk_commande
    FOREIGN KEY(id_commande) REFERENCES commande(id);

ALTER TABLE details
    ADD CONSTRAINT fk_produitdet
    FOREIGN KEY(id_produit) REFERENCES produit(id);


ALTER TABLE commande
    ADD CONSTRAINT fk_commadeClient
    FOREIGN KEY(id_client) REFERENCES client(id);


ALTER TABLE paiement
    ADD CONSTRAINT fk_commadeP
    FOREIGN KEY(id_facture) REFERENCES commande(id);


-- ================= INSERTION DE QUELSES DONNEES DANS LES TABLES
INSERT INTO fournisseur(nom,ville)
VALUES
('ALIB','HONG KONG'),
('FNAC','Paris'),
('AMZ','New York');
-- SELECT * FROM fournisseur;
-- +----+------+-----------+
-- | ID | nom  | ville     |
-- +----+------+-----------+
-- |  1 | ALIB | HONG KONG |
-- |  2 | FNAC | Paris     |
-- |  3 | AMZ  | New York  |
-- +----+------+-----------+
-- 3 rows in set (0.001 sec)

INSERT INTO categorie(titre)
VALUES
('SMARTPHONE'),
('TV'),
('ORDINATEURS');
--  SELECT * FROM categorie;
-- +----+-------------+
-- | ID | titre       |
-- +----+-------------+
-- |  1 | SMARTPHONE  |
-- |  2 | TV          |
-- |  3 | ORDINATEURS |
-- +----+-------------+
-- 3 rows in set (0.001 sec


INSERT INTO produit(titre,prix,id_categorie,stock)
VALUES
('Iphone50',4000,1,20),
('Samsung Hd45',2000,2,15),
('Hp Envi',700,3,5),
('Zrnzus pro',1000,1,19),
('Brandt',800,2,55),
('MAc Book',900,3,60);
 SELECT * FROM produit;
-- +----+--------------+------+--------------+-------+
-- | ID | titre        | prix | id_categorie | stock |
-- +----+--------------+------+--------------+-------+
-- |  1 | Iphone50     | 4000 |            1 |    20 |
-- |  2 | Samsung Hd45 | 2000 |            2 |    15 |
-- |  3 | Hp Envi      |  700 |            3 |     5 |
-- |  4 | Zrnzus pro   | 1000 |            1 |    19 |
-- |  5 | Brandt       |  800 |            2 |    55 |
-- |  6 | MAc Book     |  900 |            3 |    60 |
-- +----+--------------+------+--------------+-------+
-- 6 rows in set (0.001 sec)


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

--  SELECT * FROM client;
-- +----+---------+------------+------+--------------+
-- | ID | nom     | ville      | age  | prenom       |
-- +----+---------+------------+------+--------------+
-- |  1 | MBAYE   | Dakar      |   33 | Mamadou      |
-- |  2 | MNDIACK | MAdagascar |   32 | Yves         |
-- |  3 | DUCOQ   | PAris      |   31 | Jean Mathieu |
-- |  4 | ALI     | MAroc      |   34 | Abdel        |
-- |  5 | BOUZIDI | Alger      |   33 | Mohammed     |
-- |  6 | GENS    | Paris      |   33 | Jonathan     |
-- |  7 | LEMAIRE | Paris      |   30 | Sophie       |
-- |  8 | REMOS   | Marseille  |   30 | Remy         |
-- |  9 | SINCE   | Normandie  |   32 | Manu         |
-- | 10 | LEBON   | Paris      |   33 | Ludovic      |
-- | 11 | LEBON   | Paris      |   33 | Cedric       |
-- | 12 | KASSIMI | MAroc      |   33 | Zak          |
-- | 13 | LEDUC   | PAris      |   31 | Claire       |
-- | 14 | MYETTE  | PAris      |   33 | MArichou     |
-- | 15 | STLOUIS | Lille      |   32 | Louis r      |
-- | 16 | FARID   | Toulouse   |   33 | Farid        |
-- +----+---------+------------+------+--------------+
-- 16 rows in set (0.001 sec)


INSERT INTO commande(dateF,id_client)
VALUES
('2023-10-27',3),
('2024-01-15',4),
('2022-10-27',6),
('2023-07-25',10);
--  SELECT * FROM commande;
-- +----+------------+-----------+
-- | ID | dateF      | id_client |
-- +----+------------+-----------+
-- |  1 | 2023-10-27 |         3 |
-- |  2 | 2024-01-15 |         4 |
-- |  3 | 2022-10-27 |         6 |
-- |  4 | 2023-07-25 |        10 |
-- +----+------------+-----------+
-- 4 rows in set (0.001 sec)


INSERT INTO paiement(id_facture,montant,dateP)
VALUES
(4,5000,'2023-10-28'),
(2,7000,'2024-01-15'),
(3,4000,'2022-10-29');
--  SELECT * FROM  paiement;
-- +----+------------+---------+------------+
-- | ID | id_facture | montant | dateP      |
-- +----+------------+---------+------------+
-- |  1 |          4 |    5000 | 2023-10-28 |
-- |  2 |          2 |    7000 | 2024-01-15 |
-- |  3 |          3 |    4000 | 2022-10-29 |
-- +----+------------+---------+------------+
-- 3 rows in set (0.001 sec)

INSERT INTO details(quantite,prixU,id_commande,id_produit)
VALUES
(1,4000,1,1),
(2,2000,3,2),
(5,900,4,6);
--  SELECT * FROM details;
-- +----+----------+-------+-------------+------------+
-- | ID | quantite | prixU | id_commande | id_produit |
-- +----+----------+-------+-------------+------------+
-- |  1 |        1 |  4000 |           1 |          1 |
-- |  2 |        2 |  2000 |           3 |          2 |
-- |  3 |        5 |   900 |           4 |          6 |
-- +----+----------+-------+-------------+------------+
-- 3 rows in set (0.001 sec)

INSERT INTO entree_stock(id_produit,id_fournisseur,quantite,dateE)
VALUES
(1,2,50,'2023-10-28'),
(3,3,150,'2023-01-15'),
(6,1,50,'2022-09-29');
-- SELECT * FROM entree_stock;
-- +----+------------+----------------+----------+------------+
-- | ID | id_produit | id_fournisseur | quantite | dateE      |
-- +----+------------+----------------+----------+------------+
-- |  1 |          1 |              2 |       50 | 2023-10-28 |
-- |  2 |          3 |              3 |      150 | 2023-01-15 |
-- |  3 |          6 |              1 |       50 | 2022-09-29 |
-- +----+------------+----------------+----------+------------+
-- 3 rows in set (0.001 sec)
