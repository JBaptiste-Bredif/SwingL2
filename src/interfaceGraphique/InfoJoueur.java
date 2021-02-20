
package interfaceGraphique;

/**
 *
 * @author Boulanger
 */
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JLabel;

public class InfoJoueur extends javax.swing.JFrame
{
  private JLabel forceLabel;
  private JLabel forceText;
  private JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea messages;
  private JLabel objetsLabel;
  private JLabel objetsListe;
  private JLabel vieLabel;
  private JLabel vieText;
  
  public InfoJoueur()
  {
    initComponents();
    messages.setText("P : Prendre objet au sol / D : Déposer un objet au sol / S : Utiliser médicament / M : Manger steak");
  }
  
  private void initComponents()
  {
    jLabel1 = new JLabel();
    forceLabel = new JLabel();
    vieLabel = new JLabel();
    forceText = new JLabel();
    vieText = new JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    messages = new javax.swing.JTextArea();
    objetsLabel = new JLabel();
    objetsListe = new JLabel();
    
    setDefaultCloseOperation(3);
    
    jLabel1.setHorizontalAlignment(0);
    jLabel1.setText("Information du joueur");
    
    forceLabel.setText("Force : ");
    
    vieLabel.setText("Point de vie : ");
    
    forceText.setText(null);
    
    vieText.setText(null);
    
    messages.setColumns(20);
    messages.setRows(5);
    jScrollPane1.setViewportView(messages);
    
    objetsLabel.setText("Objets :");
    
    objetsListe.setText("pas d'objets");
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
  
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1, -1, 407, 32767).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, -1, 387, 32767).addContainerGap()).addGroup(layout.createSequentialGroup().addGap(34, 34, 34).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(objetsLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(objetsListe, -1, 314, 32767)).addGroup(layout.createSequentialGroup().addComponent(vieLabel).addGap(6, 6, 6).addComponent(vieText)).addGroup(layout.createSequentialGroup().addComponent(forceLabel).addGap(18, 18, 18).addComponent(forceText))).addContainerGap()));
    
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(forceLabel).addComponent(forceText)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(vieLabel).addComponent(vieText)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(objetsLabel).addComponent(objetsListe, -1, -1, 32767)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, 32767).addComponent(jScrollPane1, -2, 217, -2).addContainerGap()));
   
    pack();
  }
  
  public void setForceText(int force)
  {
    forceText.setText("" + force);
  }
  
  public void setVieText(int vie) {
    vieText.setText("" + vie);
  }
  
  public void setObjetsText(String objetsListe) {
    this.objetsListe.setText("" + objetsListe);
  }
  
  public void setMessages(String message)
  {
    messages.setText(messages.getText() + "\n" + message);
  }
}
    

