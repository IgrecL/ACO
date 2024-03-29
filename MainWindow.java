import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class MainWindow extends JFrame {
    private static final int LARGEUR = 1280;
    private static final int HAUTEUR = 720;
    private static JPanel conteneur;
    private static Insets insets;
    private static Parametres param;
    public static Carte carte;

    // Images et tailles
    public static BufferedImage imageFourmiA, imageFourmiB, imageFourmiliere, imageNourriture, imageFond, imageTexture, imageTuto;
    public static ImageIcon icone;
    public static final int TAILLE_FOURMI = 20;
    public static final int TAILLE_FOURMILIERE = 40;
    public static final int TAILLE_NOURRITURE = 30;

    public MainWindow() {
        importerImages();
        // La fenêtre utilise par défaut le style de l'OS de l'utilisateur
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception err) {
            System.out.println(err);
        }
        // Création de l'interface graphique
        this.setSize(LARGEUR, HAUTEUR);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Ant Colony Optimization");
        insets = getInsets();
        conteneur = new JPanel();
        conteneur.setLayout(null);
        add(conteneur);

        // Création du JPanel Parametres
        param = new Parametres();
        param.setSize(new Dimension((int) (0.2 * LARGEUR) - insets.left, HAUTEUR - insets.top - insets.bottom));

        // Création du premier objet Carte
        carte = new Carte(param.getObstacles(), param.getNourriture(), param.getFourmiliere());
        carte.setSize(new Dimension((int) (0.8 * LARGEUR), HAUTEUR - insets.top - insets.bottom));

        // Ajouts des JPanel
        param.setLocation(0, 0);
        carte.setLocation((int) (0.2 * LARGEUR) - insets.left, 0);
        conteneur.add(param);
        conteneur.add(carte);

        // Icone de la fenêtre ne fonctionne que sur Windows
        URL iconeURL = getClass().getResource("assets/images/icon.png");
        icone = new ImageIcon(iconeURL);
        this.setIconImage(icone.getImage());

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repaint();
    }

    public static void modifierCarte() {
        conteneur.remove(carte);
        carte.getTimer().stop();
        carte = new Carte(param.getObstacles(), param.getNourriture(), param.getFourmiliere());
        carte.setSize(new Dimension((int) (0.8 * LARGEUR), HAUTEUR - insets.top - insets.bottom));
        carte.setLocation((int) (0.2 * LARGEUR) - insets.left, 0);
        conteneur.add(carte);
        conteneur.revalidate();
        conteneur.repaint();
    }

    // Importation et redimensionnement des images qu'on importe en tant que BufferedImage
    private void importerImages() {
        try {
            // On importe les images
            imageFourmiA = ImageIO.read(new File("assets/images/FourmiA.png"));
            imageFourmiB = ImageIO.read(new File("assets/images/FourmiB.png"));
            imageFourmiliere = ImageIO.read(new File("assets/images/Fourmiliere.png"));
            imageNourriture = ImageIO.read(new File("assets/images/Nourriture.png"));
            imageFond = ImageIO.read(new File("assets/images/Fond.png"));
            imageTexture = ImageIO.read(new File("assets/images/texture.png"));
            imageTuto = ImageIO.read(new File("assets/images/tuto.png"));

            // On leur donne la taille désirée
            imageFourmiA = redimensionner(imageFourmiA, TAILLE_FOURMI);
            imageFourmiB = redimensionner(imageFourmiB, TAILLE_FOURMI);
            imageFourmiliere = redimensionner(imageFourmiliere, TAILLE_FOURMILIERE);
            imageNourriture = redimensionner(imageNourriture, TAILLE_NOURRITURE);
            imageFond = redimensionner(imageFond, 1025);

        } catch (IOException e) {
            throw new RuntimeException("Impossible de lire les fichiers images.");
        }
    }

    // Redimensionne l'image de fourmi à la taille désirée
    private static BufferedImage redimensionner(BufferedImage img, int largeurVoulue) {
        int largeur = img.getWidth();
        int hauteur = img.getHeight();
        int hauteurVoulue = (largeurVoulue * hauteur) / largeur; // Simple produit en croix

        // On crée une nouvelle image vide la taille désirée
        BufferedImage nouvelleImage = new BufferedImage(largeurVoulue, hauteurVoulue, img.getType());
        Graphics2D g = nouvelleImage.createGraphics();

        // On place l'image dans cette nouvelle image de manière à ce qu'elle la remplisse, par interpolation
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, largeurVoulue, hauteurVoulue, 0, 0, largeur, hauteur, null);
        g.dispose();

        return nouvelleImage;
    }

}