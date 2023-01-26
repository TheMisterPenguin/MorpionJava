import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener, TicTacInterface{

    private JPanel g_panel = new JPanel(); 
    private JPanel t_panel = new JPanel();
    private String joueur;
    private JButton[][] liste_boutons = new JButton[3][3];
    private TicTacComm autreJoueur;
    JLabel label;

    public TicTacToe(String joueur, String adresse){ 
        super("TicTacToe");
        try{
            this.autreJoueur =  new TicTacCommFactory((TicTacInterface) this, joueur)
                                .address(adresse)
                                .build();
        }catch (TicTacServerException e){
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ouverture de RMI", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(ABORT);
        }
        catch (TicTacClientException e){
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ouverture de RMI", "Erreur", JOptionPane.ERROR_MESSAGE);

        }
        
        this.setVisible(true);
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ferme la fenêtre
        this.setSize(700,700); //taille de la fenêtre
        this.setResizable(false); //redimensionnable

        /*Grille TicTacToe */
        g_panel.setLayout(new GridLayout(3,3,5,5)); //layout de la fenêtre
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                JButton but=new JButton();
                this.liste_boutons[i][j] = but;
                but.addActionListener(this);
                g_panel.add(this.liste_boutons[i][j]);
            }
        }

        /*Titre pour annoncer qui joue */
        joueur="J1";
        label= new JLabel("Au tour de J1");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        t_panel.add(label);
       


        this.add(t_panel,BorderLayout.SOUTH);
        this.add(g_panel);
    }

    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if (source == this.liste_boutons[i][j]){ // action a effectuer
                        if(this.joueur.equals("J1")){
                            setX(i,j);
                            autreJoueur.remoteObj().setX(i, j);
                            this.liste_boutons[i][j].setEnabled(false);
                            this.joueur="J2";
                        }else{
                            setO(i,j);
                            autreJoueur.remoteObj().setO(i, j);
                            this.liste_boutons[i][j].setEnabled(false);
                            this.joueur="J1";
                        }
                        label.setText("Au tour de: "+this.joueur);
                    }
                }
            }
            vainqueur(this.joueur.equals("J1")?"X":"O");
    }

    public boolean setX(int x, int y){
        this.liste_boutons[x][y].setText("X");
        this.liste_boutons[x][y].setFont(new Font("Arial", Font.BOLD, 50));
        return true;
    };

    public boolean setO(int x, int y){
        this.liste_boutons[x][y].setText("O");
        this.liste_boutons[x][y].setFont(new Font("Arial", Font.BOLD, 50));
        return true;
    };

    public void vainqueur(String element){
        if(this.liste_boutons[0][0].getText().equals(element) && this.liste_boutons[0][1].getText().equals(element) && this.liste_boutons[0][2].getText().equals(element)){
            label.setText("Le vainqueur est "+this.joueur);
            this.liste_boutons[0][0].setForeground(Color.GREEN);
            this.liste_boutons[0][1].setForeground(Color.GREEN);
            this.liste_boutons[0][2].setForeground(Color.GREEN);
        
        }else if(this.liste_boutons[0][0].getText().equals(element) && this.liste_boutons[1][1].getText().equals(element) && this.liste_boutons[2][2].getText().equals(element)){
            label.setText("Le vainqueur est "+this.joueur);

        }else if(this.liste_boutons[0][0].getText().equals(element) && this.liste_boutons[1][0].getText().equals(element) && this.liste_boutons[2][0].getText().equals(element)){
            label.setText("Le vainqueur est "+this.joueur);

        }else if(this.liste_boutons[2][0].getText().equals(element) && this.liste_boutons[2][1].getText().equals(element) && this.liste_boutons[2][2].getText().equals(element)){
            label.setText("Le vainqueur est "+this.joueur);

        }else if(this.liste_boutons[0][2].getText().equals(element) && this.liste_boutons[1][2].getText().equals(element) && this.liste_boutons[2][2].getText().equals(element)){
            label.setText("Le vainqueur est "+this.joueur);

        }else if(this.liste_boutons[0][2].getText().equals(element) && this.liste_boutons[1][1].getText().equals(element) && this.liste_boutons[2][0].getText().equals(element)){
            label.setText("Le vainqueur est "+this.joueur);
        }

    }
        
        
    

    public static void main(String[] toto){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new TicTacToe("X", "locahost");   
            }
        });
    }

}



/*
    ---> Créer interface Morpion
    --> Deux Images rond et croix
    -> S'envoyer l'image à tel position

*/