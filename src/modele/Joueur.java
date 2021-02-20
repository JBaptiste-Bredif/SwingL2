package modele;

import interfaceGraphique.MessageService;
import interfaceGraphique.Scenario;
import java.util.Scanner;

public class Joueur extends Individu {

    private final int DEBUT = 10;
    private int force;
    private int vie;
    private Objet[] tabObjet;

    public Joueur(Piece p) {
        super(p);
        force = DEBUT;
        vie = DEBUT;
        tabObjet = new Objet[4];
    }

    public void deplacer(char r) {
        super.deplacer(r);
        force--;
        // action en foction des personnages pr�sent dans la pi�ce
    }

    public void affiche() {
        System.out.println();
        System.out.println("JOUEUR force : " + force + " et de point de vie : " + vie);
        int nbobj = 0;
        // objet du joueur
        for (int i = 0; i < tabObjet.length; i++) {
            if (tabObjet[i] != null) {
                System.out.print("Objet en " + i + " : ");
                tabObjet[i].affiche();
                nbobj++;
            }
        }
        if (nbobj == 0) {
            System.out.println("pas d'objet sur le joueur");
        }
        // pi�ce
        System.out.println("Dans la pi�ce du joueur");
        super.affiche();
    }

    // prendre objet et le mettre � l'indice i
    public void prendreObjet(Objet o, int place) {

        if (o != null) {
            if (o.getPosition() == getPosition()) {
                if (tabObjet[place] != null) {
                    MessageService.message = "depose du précédent objet en " + place + " dans cette pi�ce";
                    poserObjet(place);
                }
                tabObjet[place] = o;
                o.pris(); // la position de l'objet devient null
                MessageService.message = "objet pris!";
            } else {
                MessageService.message = "Erreur l'objet et le joueur ne sont pas dans la m�me pi�ce";
            }
        } else {
            MessageService.message = "pas d'objet";
        }
    }

    // poser l'objet � l'indice i
    public void poserObjet(int i) {
        if (tabObjet[i] != null) {
            Objet o = tabObjet[i];
            o.setPosition(getPosition()); // donne � l'objet la position du joueur
            tabObjet[i] = null;
            //System.out.print("depose de ");
            //o.affiche();
            MessageService.message = "objet posé!";
        } else {
            MessageService.message = "pas d'objet";
        }
    }

    // poser l'objet
   /* public void poserObjet() {
    Scanner sc = new Scanner(System.in);
    System.out.println("quel est l'indice de l'objet � poser ? (de 0 � 3)");
    int i = sc.nextInt();
    while (i > 3 || i < 0) {
    System.out.println("quel est l'indice de l'objet � poser ? (de 0 � 3)");
    i = sc.nextInt();
    }
    poserObjet(i);
    }*/
    public void ouvrirPorte(char r) {
        Porte po = getPosition().getPorte(r);
        if (po == null) {
            MessageService.message = "Pas de porte";
        } else {
            if (po.getFerme()) // porte ferm�e
            {
                int numPorte = po.getNumero();
                // recherche si le joueur � une cl� avec le m�me num�ro dans tabObjet
                boolean memeNum = false;
                for (int i = 0; i < 4; i++) {
                    if (tabObjet[i] != null && tabObjet[i].getClass().getName().contains("Cle")) {
                        Cle c = (Cle) tabObjet[i];
                        if (c.getNumero() == numPorte) {
                            memeNum = true;
                        }
                    }
                }
                // si le joueur a la cl�
                if (memeNum) {
                    po.ouvrir();
                    MessageService.message = "la porte est maintenant ouverte";
                } else {
                    MessageService.message = "le joueur n'a pas la cl�";
                }
            } else {
                MessageService.message = "la porte est d�j� ouverte";
            }
        }
    }

    // renvoie l'objet en i
    public Objet objetI(Class<?> c) {
        for (Objet o : tabObjet) {
            if (o != null) {
                if (o.getClass() == c) {
                    return o;
                }
            }
        }

        return null;
    }

    public Objet[] getObjets() {
        return this.tabObjet;
    }

    private void removeObjet(Objet o)
    {
        for(int i=0;i<tabObjet.length;i++)
            if(tabObjet[i] == o)
                tabObjet[i] = null;
    }

    public void manger() {
        Objet o = objetI(Nourriture.class);
        // Si cet objet est de la nourriture
        if (o != null) {
            Nourriture n = (Nourriture) o;
            force = Math.min(force + n.getForce(), DEBUT); // calcule de la force du joueur
            MessageService.message = "la force est maintenant de " + force;
            removeObjet(o); // suppression de la nourriture
        } else {
            MessageService.message = "Aucune nourriture trouvée";
        }
    }

    public void soigner() {
        Objet o = objetI(Medicament.class);
        // Si cet objet est un m�dicament
        if (o != null) {
            Medicament m = (Medicament) o;
            vie = Math.min(vie + m.getVie(), DEBUT); // calcule des points de vie du joueur
            MessageService.message = "Les points de vie sont maintenant de " + vie;
            removeObjet(o); // suppression du m�dicament
        } else {
            MessageService.message += "Aucun médicament trouvé";
        }
    }

    public void combat(Monstre m) {
        if (getPosition() == m.getPosition() && m != null) {
            MessageService.message = "Combat!!\n";
            int sommeForce = force + m.getForce();
            int tirage = (int) (Math.random() * sommeForce);
            MessageService.message += "tirage : " + tirage+"\n";
            if (tirage < force) // si tirage compris entre 0 et force le joueur gagne
            {
                MessageService.message += "Joueur gagne";
                //MessageService.message += "Le joueur tue le monstre et gagne le combat";
                //Scenario.mortMonstre(m); 
            } else // si tirage compris entre force et (force + force du monstre) le monstre gagne
            {
                MessageService.message += "Monstre gagne";
                vie--;
            }
        }
    }

    public void guerir() {
        MessageService.message = "Guerir";
        vie = DEBUT;
    }

    public void nourrir() {
        MessageService.message = "Nourrir";
        force = DEBUT;
    }

    public int getForce() {
        return force;
    }

    public int getVie() {
        return vie;
    }

}
