package modele;


public class Porte {
	private Piece devant;
	private Piece derriere;
	private boolean ferme;
	int numero;
	
	public Porte()
	{
		ferme = false;
	}
	
	public Porte(int i)
	{

		numero = i;
		ferme = true;
	}
	
	public void affiche()
	{
		if (ferme)
			System.out.println("porte ferm�e Num�ro"+numero);
		else
			System.out.println("porte ouverte");
		
	}
	
	public void ajoutPiece(Piece p1, Piece p2)
	{
		devant = p1;
		derriere = p2;
	}
	
	
	public Piece autrePiece(Piece p)
	{
		if (devant == p)
			return derriere;
		else
			return devant;
	}
	public boolean getFerme()
	{
		return ferme;
	}
	
	public int getNumero()
	{
		return numero;
	}
	public void ouvrir()
	{
		ferme = false;
	}
}
