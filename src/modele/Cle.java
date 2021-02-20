package modele;

public class Cle extends Objet {

    private int numero;

    public Cle(Piece p, int n) {
        super(p);
        numero = n;
    }

    public void affiche() {
        System.out.println("cle num�rot� : " + numero);
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Clé numéro " + numero;
    }
}
