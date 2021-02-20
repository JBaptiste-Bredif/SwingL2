package modele;

public abstract class Individu {

    private Piece position;

    public Individu(Piece p) {
        position = p;
    }

    public void affiche() {
        position.affiche();
    }

    public void deplacer(char r) {
        if (position.getClass().getName().contains("PassageSecret")) {
            PassageSecret secret = (PassageSecret) position;
            position = secret.pieceVoisine(r);
        } else {
            position = position.pieceVoisine(r);
        }
    }

    public Piece getPosition() {
        return position;
    }
}
