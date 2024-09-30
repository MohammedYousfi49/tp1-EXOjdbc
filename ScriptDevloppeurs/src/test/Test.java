/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import beans.Developpeur;
import connexion.Connexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class Test {

    public static void CreateDevData() {
        //Information d'accès à la base de données
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/scriptdeveloppeurs";
        Connection cn = null;
        Statement st = null;
        try {
            //Etape 1 : Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            //Etape 2 : Récupération de la connexion
            cn = DriverManager.getConnection(url, user, password);
            //Etape 3 : Création d'un statement
            st = cn.createStatement();
            String req = "CREATE TABLE DevData (Developpeur VARCHAR (32),Jour CHAR (11),NbScripts INTEGER)";
            //Etape 4 : Exécution de la requête
            st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }
    }

    public static void InsertDev(Developpeur dev) {
        //Information d'accès à la base de données
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/scriptdeveloppeurs";
        Connection cn = null;
        Statement st = null;
        try {
            //Etape 1 : Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            //Etape 2 : Récupération de la connexion
            cn = DriverManager.getConnection(url, user, password);
            //Etape 3 : Création d'un statement
            st = cn.createStatement();
            String req = "insert into devdata values('" + dev.getNomdev()
                    + "','" + dev.getJour() + "'," + dev.getNbrScript() + ")";
            //Etape 4 : Exécution de la requête
            st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }
    }

    public static void NbreScriptMax() {

       Statement st = null;
        Connection cn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            cn = Connexion.getConnexion();

            st = cn.createStatement();
            String req = "SELECT Developpeur, Jour, max(NbScripts)"
                    + " from devdata"
                    + " group by Jour";

            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println(" " + new Developpeur(rs.getString("Developpeur"), rs.getString("Jour"),
                        rs.getInt("max(NbScripts)")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }
    }
    
    
    
    public static void ScriptDecroissant() {

        Statement st = null;
        Connection cn = null;

        try {
            
            Class.forName("com.mysql.jdbc.Driver");           
            cn = Connexion.getConnexion();            
            st = cn.createStatement();
            String req = "SELECT Developpeur, sum(NbScripts) as c"
                    + " from devdata"
                    + " Group by Developpeur"
                    + " order by c desc";
            
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("Le développeur : " + rs.getString("Developpeur") +
                        " avec un nombre de script égal a : " + rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }
    }
    
    
    
    public static void TotalScriptSemaine() {

        Statement st = null;
        Connection cn = null;

        try {
            
            Class.forName("com.mysql.jdbc.Driver");            
            cn = Connexion.getConnexion();            
            st = cn.createStatement();
            String req = "SELECT SUM(NbScripts) FROM devdata";           
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("Le nombre des scripts réalisés pandant la semaine est " +
                        rs.getInt("SUM(NbScripts)") + " scripts");
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }
    }
    
    public static void TotalScriptSemaineDev(String nomdev) {

        Statement st = null;
        Connection cn = null;

        try {
            
            Class.forName("com.mysql.jdbc.Driver");           
            cn = Connexion.getConnexion();            
            st = cn.createStatement();
            String req = "SELECT SUM(NbScripts) FROM devdata WHERE Developpeur='" + nomdev + "'";           
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("Le nombre des scripts réalisés pandant la semaine par " + nomdev + " est " +
                        rs.getInt("SUM(NbScripts)") + " scripts");
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");}
    }
    
    
    
    public static void Requete() {

        Statement st = null;
        Connection cn = null;

        try {
            //Etape 1 : Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            //Etape 2 : Récupération de la connexion
            cn = Connexion.getConnexion();
            //Etape 3 : Création d'un statement
            st = cn.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Entrez votre requête SQL :");
            String req = scanner.nextLine();
            scanner.close();
            //Etape 4 : Exécution de la requête
            boolean bool = st.execute(req);
            if (bool) {
                ResultSet rs = st.executeQuery(req);
                ResultSetMetaData metaData = rs.getMetaData();
                System.out.println("Le nombre de colonnes du table est : " + metaData.getColumnCount());
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    System.out.println("Colonne numéro " + i + ": " + metaData.getColumnName(i) + " (Type: "
                            + metaData.getColumnTypeName(i) + ")");
                }
                System.out.println("Contenu du résultat :");
                while (rs.next()) {
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.print("\n");
                }
            }else{
                // Si la requête modifie des lignes, afficher le nombre de lignes modifiées
                int rowsAffected = st.getUpdateCount();
                System.out.println("Nombre de lignes modifiées : " + rowsAffected);
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        }

    }
    
   

    public static void main(String[] args) {
        //Test.TotalScriptSemaineDev("WAFI");
        Test.Requete();
        
    
     }
     }

/**
 * @param args the command line arguments
 */
