import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TicTacToe extends JFrame implements ActionListener{

    private JPanel g_panel = new JPanel(); 
    private JPanel t_panel = new JPanel();
    private String joueur = "J1";
    private ArrayList<ArrayList<JButton>> liste_boutons = new ArrayList<>();
    JLabel label= new JLabel("Au tour de: "+this.joueur);

    public TicTacToe(){ 
        super("TicTacToe");
        this.setVisible(true);
        this.centerOnScreen();
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ferme la fenêtre
        this.setSize(700,700); //taille de la fenêtre
        this.setResizable(false); //redimensionnable

        /*Grille TicTacToe */
        g_panel.setLayout(new GridLayout(3,3,5,5)); //layout de la fenêtre
        for(int i=0;i<9;i++){
            JButton but=new JButton();
            this.liste_boutons.add(but);
            but.addActionListener(this);
            g_panel.add(this.liste_boutons.get(i));
        }

        /*Titre pour annoncer qui joue */
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        t_panel.add(label);
       

        this.add(t_panel,BorderLayout.SOUTH);
        this.add(g_panel);
    }

    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
            for(int i=0;i<9;i++){
                if (source == this.liste_boutons.get(i)){ // action a effectuer
                    if(this.joueur.equals("J1")){
                      
                       this.joueur="J2";
                       
                    }else{
                        this.liste_boutons.get(i).setText("O");
                        this.liste_boutons.get(i).setFont(new Font("Arial", Font.BOLD, 50));
                        this.joueur="J1";
                    }
                    label.setText("Au tour de: "+this.joueur);
                }
            }
    }

    public boolean setX(){
        this.liste_boutons.get(i).setText("X");
        this.liste_boutons.get(i).setFont(new Font("Arial", Font.BOLD, 50));
    };

    public static void main(String[] toto){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new TicTacToe();   
            }
        });
    }

}



/*
    ---> Créer interface Morpion
    --> Deux Images rond et croix
    -> S'envoyer l'image à tel position

*/