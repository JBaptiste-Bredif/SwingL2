package modele;

public class Medicament extends Objet {

    private int vie;

    public Medicament(Piece p) {
        super(p);
        vie = (int) (Math.random() * 3 + 1);
    }

    public void affiche() {
        System.out.println("Medicament de " + vie + " point(s) ");
    }

    public int getVie() {
        return vie;
    }

    @Override
    public String toString() {
        return "Medic " + vie + " point(s)";
    }
}
