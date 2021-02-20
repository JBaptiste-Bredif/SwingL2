package interfaceGraphique;

import modele.Individu;
import modele.Joueur;
import modele.Monstre;
import modele.Objet;
import modele.PassageSecret;
import modele.Piece;
import modele.Porte;
import modele.Tresor;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;







public class GUI
  extends JFrame
{
  private int width = 100;
  private int height = 100;
  private Joueur joueur = Scenario.joueur;
  private Tresor tresor = Scenario.tresor;
  private JPanel panel;
  private InfoJoueur infoJoueur;
  public static JFrame programme;
  
  public GUI()
  {
    initComponents();
    programme = this;
    infoJoueur = new InfoJoueur();
    infoJoueur.setVisible(true);
    infoJoueur.setLocation(1050, 0);
    
    panel = new JPanel()
    {
      protected void paintComponent(Graphics g)
      {
        super.paintComponent(g);
        drawBackground(g);
      }
      
      private void drawBackground(Graphics g) {
        GUI.this.affichagePiece(g);
        GUI.this.affichagePorte(g);
        GUI.this.affichageIndividu(g);
        GUI.this.affichageObjet(g);
      }
      
    };
    GroupLayout jPanel1Layout = new GroupLayout(panel);
    panel.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 974, 32767));
    
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 514, 32767));
    

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panel, GroupLayout.Alignment.TRAILING, -1, -1, 32767));
    
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panel, -1, -1, 32767));
    

    setSize(width * 10 + 40, height * 10 + 40);
    
    panel.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
        super.mouseClicked(e);
        Piece piece = joueur.getPosition();
        int positionClick = numberPiece(e.getX(), width) + numberPiece(e.getY(), height) * 10;
        int positionJoueur = ((Integer)Scenario.pieces.get(piece)).intValue() - 1;
        
        if (positionClick == positionJoueur - 10) {
          if ((piece.getPorte('N') != null) && 
            (piece.getPorte('N').getFerme())) {
            joueur.ouvrirPorte('N');
          }
          

          joueur.deplacer('N');
          panel.updateUI();
        } else if (positionClick == positionJoueur - 1) {
          if ((piece.getPorte('O') != null) && 
            (piece.getPorte('O').getFerme())) {
            joueur.ouvrirPorte('O');
          }
          
          joueur.deplacer('O');
          panel.updateUI();
        } else if (positionClick == positionJoueur + 10) {
          if ((piece.getPorte('S') != null) && 
            (piece.getPorte('S').getFerme())) {
            joueur.ouvrirPorte('S');
          }
          
          joueur.deplacer('S');
          panel.updateUI();
        } else if (positionClick == positionJoueur + 1) {
          if ((piece.getPorte('E') != null) && 
            (piece.getPorte('E').getFerme())) {
            joueur.ouvrirPorte('E');
          }
          
          joueur.deplacer('E');
          panel.updateUI();
        } else if (positionClick == positionJoueur)
        {
          if (piece.getClass().getName().contains("PassageSecret")) {
            joueur.deplacer('s');
            panel.updateUI();
          }
        }
        



        for (Individu individu : Scenario.individus) {
          if ((individu.getClass() != joueur.getClass()) && 
            (individu.getPosition() == joueur.getPosition())) {
            if (individu.getClass().getName().contains("Monstre")&& individu!= null) {
              Monstre monstre = (Monstre)individu;
              joueur.combat(monstre);
            } else if (individu.getClass().getName().contains("Medecin")) {
              joueur.guerir();
            } else if (individu.getClass().getName().contains("Cuisinier")) {
              joueur.nourrir();
            }
          }
        }
        



        if (gagne()) {
          JOptionPane.showMessageDialog(panel, "Vous avez gagné !");
          GUI.programme.dispose();
          infoJoueur.dispose();
        }
        
        if (perdu()) {
          JOptionPane.showMessageDialog(panel, "Vous avez perdu...");
          GUI.programme.dispose();
          infoJoueur.dispose();
        }
      }
      
      private int numberPiece(int coord, int size) {
        for (int i = 1; i < 11; i++) {
          int x = i * size;
          if (coord < x) {
            return i - 1;
          }
        }
        return 0;
      }
      
    });
    panel.setFocusable(true);
    
    panel.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent e)
      {
        super.keyTyped(e);
        
        if (e.getKeyChar() == 'p') {
          for (Objet o : Scenario.objets) {
            if (joueur.getPosition() == o.getPosition()) {
              int position = Integer.valueOf(JOptionPane.showInputDialog(panel, "Où placer l'objet ?", "Prendre", 3)).intValue();
              joueur.prendreObjet(o, position);
              panel.updateUI();
              break;
            }
          }
        } else if (e.getKeyChar() == 'd') {
          int position = Integer.valueOf(JOptionPane.showInputDialog(panel, "Quel objet déposer ?", "Déposer", 3)).intValue();
          joueur.poserObjet(position);
          panel.updateUI();
        } else if (e.getKeyChar() == 'm') {
          joueur.manger();
          panel.updateUI();
        } else if (e.getKeyChar() == 's') {
          joueur.soigner();
          panel.updateUI();
        }
      }
    });
  }
  







  private void initComponents()
  {
    setDefaultCloseOperation(3);
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 562, 32767));
    


    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 525, 32767));
    



    pack();
  }
  


  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      Scenario s = new Scenario();
      
      public void run() {
        new GUI().setVisible(true);
      }
    });
  }
  

  private void affichagePiece(Graphics g)
  {
    BufferedImage vide = null;
    try
    {
      vide = ImageIO.read(new File("vide.png"));
    }
    catch (IOException e) {}
    
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        g.drawImage(vide, width * j, i * height, null);
      }
    }
    
    BufferedImage piece = null;
    try
    {
      piece = ImageIO.read(new File("piece.png"));
    }
    catch (IOException e) {}
    
    for (Piece p : Scenario.pieces.keySet()) {
      int[] coord = coordonnees(0, ((Integer)Scenario.pieces.get(p)).intValue() - 1);
      int y = coord[1];
      int x = coord[0];
      
      g.drawImage(piece, width * y, x * height, null);
    }
  }
  
  private void affichagePorte(Graphics g) {
    BufferedImage ouverture_verticale = null;
    try
    {
      ouverture_verticale = ImageIO.read(new File("ouverture_verticale.png"));
    }
    catch (IOException e) {}
    
    BufferedImage ouverture_horizontale = null;
    try
    {
      ouverture_horizontale = ImageIO.read(new File("ouverture_horizontale.png"));
    }
    catch (IOException e) {}
    
    BufferedImage cadenas = null;
    try
    {
      cadenas = ImageIO.read(new File("cadenas.png"));
    }
    catch (IOException e) {}
    
    BufferedImage secret = null;
    try
    {
      secret = ImageIO.read(new File("secret.png"));
    }
    catch (IOException e) {}
    
    for (Piece p : Scenario.pieces.keySet()) {
      int[] coord = coordonnees(0, ((Integer)Scenario.pieces.get(p)).intValue() - 1);
      int y = coord[1];
      int x = coord[0];
      
      if (!p.getClass().getName().contains("PassageSecret")) {
        if (p.getPorte('E') != null) {
          g.drawImage(ouverture_verticale, width * (y + 1) - 12, x * height + 20, null);
          if (p.getPorte('E').getFerme()) {
            g.drawImage(cadenas, width * (y + 1) - 12, x * height + 27, null);
          }
        }
        
        if (p.getPorte('S') != null) {
          g.drawImage(ouverture_horizontale, width * y + 20, (x + 1) * height - 10, null);
          if (p.getPorte('S').getFerme()) {
            g.drawImage(cadenas, width * y + 27, (x + 1) * height - 10, null);
          }
        }
      } else {
        PassageSecret ps = (PassageSecret)p;
        
        if (ps.getPorte('E') != null) {
          g.drawImage(ouverture_verticale, width * (y + 1) - 12, x * height + 20, null);
          if (p.getPorte('E').getFerme()) {
            g.drawImage(cadenas, width * (y + 1) - 12, x * height + 27, null);
          }
        }
        
        if (ps.getPorte('S') != null) {
          g.drawImage(ouverture_horizontale, width * y + 20, (x + 1) * height - 10, null);
          if (p.getPorte('S').getFerme()) {
            g.drawImage(cadenas, width * y + 27, (x + 1) * height - 10, null);
          }
        }
        
        if (ps.pieceVoisine('s') != null) {
          g.drawImage(secret, width * (y + 1) - 28, x + 4, null);
        }
      }
    }
  }
  
  private void affichageIndividu(Graphics g)
  {
    for (Individu individu : Scenario.individus) {
      int[] coord = coordonnees(0, ((Integer)Scenario.pieces.get(individu.getPosition())).intValue() - 1);
      int y = coord[1];
      int x = coord[0];
      
      BufferedImage individuImage = null;
      
      if (individu.getClass().getName().contains("Joueur")) {
        try {
          individuImage = ImageIO.read(new File("joueur.png"));
        }
        catch (IOException e) {}
      } else if (individu.getClass().getName().contains("Medecin")) {
        try {
          individuImage = ImageIO.read(new File("medecin.png"));
        }
        catch (IOException e) {}
      } else if (individu.getClass().getName().contains("Monstre") && individu!= null) {
        try {
          individuImage = ImageIO.read(new File("monstre.png"));
        }
        catch (IOException e) {}
      } else if (individu.getClass().getName().contains("Cuisinier")) {
        try {
          individuImage = ImageIO.read(new File("cuisinier.png"));
        }
        catch (IOException e) {}
      }
      
      if ((individu.getPosition() != joueur.getPosition()) || (individu == joueur)) {
        g.drawImage(individuImage, width * y + 20, x * height + 20, null);
      }
    }
    

    infoJoueur.setForceText(joueur.getForce());
    infoJoueur.setVieText(joueur.getVie());
    
    String objets = "";
    for (Objet o : joueur.getObjets()) {
      if (o != null) {
        objets = objets + ((Objet)o.getClass().cast(o)).toString() + "; ";
      } else {
        objets = objets + "vide; ";
      }
    }
    
    if (!objets.equals("")) {
      infoJoueur.setObjetsText(objets);
    }
    
    if (!MessageService.message.equals("")) {
      infoJoueur.setMessages(MessageService.message);
      MessageService.message = "";
    }
  }
  
  private void affichageObjet(Graphics g) {
    for (Objet objet : Scenario.objets)
    {
      if (objet.getPosition() != null) {
        int[] coord = coordonnees(0, ((Integer)Scenario.pieces.get(objet.getPosition())).intValue() - 1);
        int y = coord[1];
        int x = coord[0];
        
        BufferedImage objetImage = null;
        
        if (objet.getClass().getName().contains("Nourriture")) {
          try {
            objetImage = ImageIO.read(new File("steak.gif"));
          }
          catch (IOException e) {}
        } else if (objet.getClass().getName().contains("Cle")) {
          try {
            objetImage = ImageIO.read(new File("cle.png"));
          }
          catch (IOException e) {}
        } else if (objet.getClass().getName().contains("Medicament")) {
          try {
            objetImage = ImageIO.read(new File("medicament.jpg"));
          }
          catch (IOException e) {}
        } else if (objet.getClass().getName().contains("Tresor")) {
          try {
            objetImage = ImageIO.read(new File("tresor.jpg"));
          }
          catch (IOException e) {}
        }
        
        g.drawImage(objetImage, width * y + 10, x * height + 10, null);
      }
    }
  }
  


  public boolean perdu()
  {
    boolean test = false;
    if (joueur.getVie() == 0) {
      test = true;
    }
    if (joueur.getForce() == 0) {
      test = true;
    }
    return test;
  }
  


  public boolean gagne()
  {
    boolean test = false;
    if (joueur.getPosition() == tresor.getPosition()) {
      test = true;
    }
    return test;
  }
  
  private int[] coordonnees(int x, int y) {
    while (y >= 10) {
      y -= 10;
      x++;
    }
    
    return new int[] { x, y };
  }
}
