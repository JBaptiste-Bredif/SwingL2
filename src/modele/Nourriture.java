package modele;

public class Nourriture extends Objet {

    int force;

    public Nourriture(Piece p) {
        super(p);
        force = (int) (Math.random() * 3 + 1);
    }

    public void affiche() {
        System.out.println("Nourriture de " + force + " point(s) de force ");
    }

    public int getForce() {
        return force;
    }

    public String toString() {
        return "Nourriture de " + force + " point(s) de force";
    }
}
