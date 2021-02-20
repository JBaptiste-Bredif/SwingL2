package modele;

import interfaceGraphique.MessageService;
import java.util.Scanner;


public class Piece {
	
	private Porte nord;
	private Porte sud;
	private Porte ouest;
	private Porte est;
	
	public void affiche()
	{
		if (nord!=null)
		{
			System.out.print("Au Nord : ");
			nord.affiche();
		}
		if (est!=null)
		{
			System.out.print("A l'Est : ");
			est.affiche();
		}	
		if (sud!=null)
		{
			System.out.print("Au Sud : ");
			sud.affiche();
		}	
		if (ouest!=null)
		{
			System.out.print("A l'Ouest : ");
			ouest.affiche();
		}	
	}
	
	public void ajoutPorte(Porte po, Piece pi, String s)
	{
		po.ajoutPiece(this, pi);
		if (s.equals("Nord"))
		{
			nord = po;
			pi.sud = po;
		}
		if (s.equals("Sud"))
		{
			sud = po;
			pi.nord = po;
		}
		if (s.equals("Est"))
		{
			est = po;
			pi.ouest = po;
		}
		if (s.equals("Ouest"))
		{
			ouest = po;
			pi.est = po;
		}
	}
	
	/*public Piece pieceVoisine()
	{
		// choix
		System.out.println("Tapez une lettre (N (Nord), E (Est), S (Sud), O (Ouest))");
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char r = s.charAt(0);
		// renvoie pi�ce 
		return pieceVoisine(r);
	}*/
        
	public Porte getPorte(char r)
	{
		switch (r)
		{
		case 'N' :
			return nord;
		case 'E' :
			return est;
		case 'S' :
			return sud;
		case 'O' :
			return ouest;
		default :
			System.out.println("Vous devez taper une des 4 lettres suivantes N, E, S, O");
			return null;
		}
	}
	
	public Piece pieceVoisine(char r)
	{
		Porte po = getPorte(r);
		if (po == null)
		{
			MessageService.message = "Croit apercevoir une passage secret... Non c'est un mur ! Ca fait mal !";
			return this; // on reste dans la m�me pi�ce
		}
		else
		{
			if (! po.getFerme()) // porte ouverte
			{
				return po.autrePiece(this);
			}
			else // porte ferm�e
			{
				MessageService.message = "Paf ! Ca fait mal !";
				return this; // on reste dans la m�me pi�ce
			}	
		}	
	} 

} 
