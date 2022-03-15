import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final int largeur = 1280;
    private final int hauteur = 720;

    public MainWindow() {
        this.setSize(largeur,hauteur);
        this.setLocationRelativeTo(null);
        Insets insets = getInsets();
        //Conteneur principal
        JPanel conteneur = (JPanel)this.getContentPane();

        Carte carte = new Carte();
        carte.setPreferredSize(new Dimension((int)(0.8*largeur),hauteur-insets.top));

        JPanel parametres = new JPanel();
        parametres.setBackground(Color.red);
        parametres.setPreferredSize(new Dimension((int)(0.2*largeur),hauteur-insets.top));

        conteneur.add(parametres, BorderLayout.WEST);
        conteneur.add(carte, BorderLayout.EAST);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repaint();
    }

}