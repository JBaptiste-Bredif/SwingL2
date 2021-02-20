package interfaceGraphique;


import modele.Cle;
import modele.Cuisinier;
import modele.Individu;
import modele.Joueur;
import modele.Medecin;
import modele.Medicament;
import modele.Monstre;
import modele.Nourriture;
import modele.Objet;
import modele.PassageSecret;
import modele.Piece;
import modele.Porte;
import modele.Tresor;
import java.util.HashMap;


public class Scenario {

    static public Individu individus[];
    static public Objet objets[];
    static public HashMap<Piece, Integer> pieces;
    static public Joueur joueur;
    static public Tresor tresor;
    
   
   /**
    * Mise en place du scénario 
    * Création Piece / Porte / Cadenas / Clef / Monstre / Items
    */
    public Scenario() {
        pieces = new HashMap<Piece, Integer>();

        // les pi�ces
        Piece p1 = new Piece();
        Piece p2 = new Piece();
        Piece p3 = new Piece();
        Piece p4 = new Piece();
        Piece p5 = new Piece();
        Piece p6 = new Piece();
        Piece p7 = new Piece();
        PassageSecret p10 = new PassageSecret();
        Piece p9 = new Piece();
        Piece p11 = new Piece();
        Piece p8 = new Piece();
        
        
        //par rapport au cadrage de l'interface graphique
        pieces.put(p8, 2);
        pieces.put(p5, 11);
        pieces.put(p6, 12);
        pieces.put(p7, 13);
        pieces.put(p1, 21);
        pieces.put(p2, 22);
        pieces.put(p3, 23);
        pieces.put(p4, 24);
        pieces.put(p9, 25);
        pieces.put(p10, 32);
        pieces.put(p11, 33);
        // les portes
        Porte po1 = new Porte();
        Porte po2 = new Porte(2);
        Porte po3 = new Porte();
        Porte po4 = new Porte(2);
        Porte po5 = new Porte();
        Porte po6 = new Porte();
        Porte po7 = new Porte(4);
        Porte po8 = new Porte(3);
        Porte po9 = new Porte(1);
        Porte po10 = new Porte(3);
        Porte po11 = new Porte(1);
        // ajout de portes aux pi�ces
        p1.ajoutPorte(po1, p2, "Est");
        p1.ajoutPorte(po2, p5, "Nord");
        p2.ajoutPorte(po3, p3, "Est");
        p3.ajoutPorte(po4, p4, "Est");
        p5.ajoutPorte(po5, p6, "Est");
        p6.ajoutPorte(po6, p7, "Est");
        p6.ajoutPorte(po7, p8, "Nord");
        p7.ajoutPorte(po8, p3, "Sud");
        p10.ajoutPassageSecret(p9);
        p9.ajoutPorte(po9, p4 , "Ouest");
        p10.ajoutPorte(po10, p2 , "Nord");
        p11.ajoutPorte(po11, p3 , "Nord");
        // individus
        Joueur j = new Joueur(p6);
        joueur = j;
        Monstre m1 = new Monstre(p1);
        Monstre m2 = new Monstre(p5);
        Monstre m3 = new Monstre(p11);
        Monstre m4 = new Monstre(p4);
        Cuisinier c1 = new Cuisinier(p7);
        Medecin m = new Medecin(p9);
        individus = new Individu[]{j,m1, m2, m3, m4, c1, m};

        // objets
        Cle cl1 = new Cle(p1, 1);
        Cle cl2 = new Cle(p6, 2);
        Cle cl3 = new Cle(p11, 3);
        Cle cl4 = new Cle(p10,4);
        Nourriture n1 = new Nourriture(p2);
        Medicament me1 = new Medicament(p3);
        Tresor t = new Tresor(p8);
        tresor = t;
        objets = new Objet[]{cl1, cl2, cl3, cl4, n1, me1, t};
        
        
    }
    /**
     * Sert a tuer le monstre 
     * Pas fonctionnel
     * @param m 
     */
    public static void mortMonstre(Monstre m){
        for(int i=0;i<individus.length;i++)
            if(individus[i]!=null &&  individus[i]== m){
                individus[i]= null;
            }
    }
    
}

