import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements ActionListener{
    private final int LARGEUR = 1280;
    private final int HAUTEUR = 720;
    private Carte carte;
    private JSpinner champDt;
    private JSpinner champNbFourmis;
    private JButton reset;
    private JButton valider;
    private JCheckBox cocherPhero;
    private int dt;
    private int nbFourmis;
    private boolean afficherPhero;

    public MainWindow() {
        // Création de l'interface graphique
        this.setSize(LARGEUR, HAUTEUR);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Insets insets = getInsets();
        
        JPanel conteneur = (JPanel)this.getContentPane();
        // Création de l'objet Carte
        carte = new Carte();
        carte.setPreferredSize(new Dimension((int)(0.8*LARGEUR), HAUTEUR-insets.top));

        // PARAMETRES
        Parametres parametres = new Parametres();
        parametres.setPreferredSize(new Dimension((int)(0.2*LARGEUR), HAUTEUR-insets.top));

        JLabel txtChampDt = new JLabel("Période de rafraichissement (en ms)");
        parametres.add(txtChampDt);
        champDt = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
		champDt.addChangeListener(new ChangeListener() {      
            public void stateChanged(ChangeEvent e) {
                dt = (int)((JSpinner)e.getSource()).getValue();
            }
        });
        champDt.setMaximumSize( new Dimension(100,20) );
        champDt.setAlignmentX(Component.CENTER_ALIGNMENT);
		parametres.add(champDt);

        JLabel txtChampNbFourmis = new JLabel("Nombre de fourmis");
        parametres.add(txtChampNbFourmis);
        champNbFourmis = new JSpinner(new SpinnerNumberModel(30, 0, 100, 5));
		champNbFourmis.addChangeListener(new ChangeListener() {      
            public void stateChanged(ChangeEvent e) {
                System.out.println(((JSpinner)e.getSource()).getValue());
                nbFourmis = (int)((JSpinner)e.getSource()).getValue();
            }
        });
        champNbFourmis.setMaximumSize( new Dimension(100,20) );
        champNbFourmis.setAlignmentX(Component.CENTER_ALIGNMENT);
		parametres.add(champNbFourmis);

        cocherPhero = new JCheckBox("Affichage des phéromones", true);
        cocherPhero.addActionListener(this);
        parametres.add(cocherPhero);

        parametres.add(Box.createVerticalGlue());

        reset = new JButton("Reset");
        reset.addActionListener(this);
		parametres.add(reset);

        valider = new JButton("Valider");
        valider.addActionListener(this);
        parametres.add(valider);

        conteneur.add(parametres, BorderLayout.WEST);
        conteneur.add(carte, BorderLayout.EAST);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==reset) {
            carte.reset();
        }
        if (e.getSource()==valider) {
            carte.valider(dt, nbFourmis, afficherPhero);
        }
        if(e.getSource() == cocherPhero){
            JCheckBox c = (JCheckBox)e.getSource();
            if (c.isSelected()){
                afficherPhero = true;
            } else{
                afficherPhero = false;
            }
        }
        /*
        if(e.getSource()== champNbFourmis){
            int k = Integer.parseInt(champNbFourmis.getValue());
            carte.changechampNbFourmis(k);
        } */     
    }


}