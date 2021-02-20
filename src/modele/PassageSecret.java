package modele;

import java.util.Scanner;


public class PassageSecret extends Piece{
	
	private Piece passageSecret;
	
	public void affiche()
	{
		super.affiche();
		System.out.println("Passage secret");
	}
	
	public void ajoutPassageSecret(Piece p)
	{
		passageSecret = p;
	}
	
	/*public Piece pieceVoisine()
	{
		// choix
		System.out.println("Tapez une lettre (N (Nord), E (Est), S (Sud), O (Ouest), s (secret)");
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char r = s.charAt(0);
		// renvoie pi�ce 
		return pieceVoisine(r);
	}*/
	
	public Piece pieceVoisine(char r)
	{
		if (r=='s')
			return passageSecret;
		else
			return super.pieceVoisine(r); // pi�ce voisine de la classe Piece
		
	}
	

}
