import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.util.LinkedList;

public class Parametres extends JPanel implements ActionListener {
    // Variables pour l'affichage
    public static final Color FOND_PARAM = new Color(214, 214, 214);
    private JCheckBox cocherPheromones, cocherSon;

    // Paramètres par défaut de la nouvelle carte
    private static int dt = 0;
    private static int nombreFourmis = 50;
    private String[] listeCartes = { "Carte par défaut", "Labyrinthe", "Double Pont", "Map 4", "Map 5" };
    private JComboBox<String> selectionCartes = new JComboBox<String>(listeCartes);

    // Élements du terrain
    private LinkedList<Obstacle> obstacles = (new LecteurCarte("assets/cartes/bordures.txt")).getObstacles();
    private Fourmiliere fourmiliere = (new LecteurCarte("assets/cartes/bordures.txt")).getFourmiliere();
    private LinkedList<Nourriture> nourritures = (new LecteurCarte("assets/cartes/bordures.txt")).getNourriture();

    private JButton editer;
    public static boolean modeEditeur = false;

    public static int getDt() {
        return dt;
    }

    public static int getNombreFourmis() {
        return nombreFourmis;
    }

    public Color getFond() {
        return FOND_PARAM;
    }

    public LinkedList<Obstacle> getObstacles() {
        return obstacles;
    }

    public LinkedList<Nourriture> getNourriture() {
        return nourritures;
    }

    public Fourmiliere getFourmiliere() {
        return fourmiliere;
    }

    public Parametres() {
        // Initialisation du JPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(new Insets(20, 20, 20, 0)));
        setBackground(FOND_PARAM);

        JPanel titrePanel = new JPanel();
        titrePanel.setLayout(new BoxLayout(titrePanel, BoxLayout.Y_AXIS));
        titrePanel.add(Box.createHorizontalGlue());
        titrePanel.setBackground(FOND_PARAM);
        titrePanel.setBorder(new EmptyBorder(new Insets(0, 0, 0, 20)));
        titrePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titrePanel);

        JLabel titre1 = new JLabel("ANT COLONY");
        JLabel titre2 = new JLabel("OPTIMIZATION");
        titre1.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre2.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre1.setFont(new Font(getFont().getFontName(), Font.BOLD, 18));
        titre2.setFont(new Font(getFont().getFontName(), Font.BOLD, 18));
        titrePanel.add(titre1);
        titrePanel.add(titre2);
        add(Box.createVerticalStrut(20));

        // Créations des champs permettant de modifier les paramètres de la carte
        JPanel champs = new JPanel();
        champs.setLayout(new BoxLayout(champs, BoxLayout.Y_AXIS));
        champs.add(Box.createHorizontalGlue());
        champs.setBackground(FOND_PARAM);
        champs.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(champs);

        JLabel texteChampDt = new JLabel("Période de rafraîchissement");
        texteChampDt.setAlignmentX(Component.LEFT_ALIGNMENT);
        champs.add(texteChampDt);
        champs.add(Box.createVerticalStrut(5));
        JSpinner champDt = new JSpinner(new SpinnerNumberModel(dt, 0, 100, 1));
        champDt.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                dt = (int) ((JSpinner) e.getSource()).getValue();
            }
        });
        champDt.setMaximumSize(new Dimension(100, 30));
        champDt.setAlignmentX(Component.LEFT_ALIGNMENT);
        champs.add(champDt);
        champs.add(Box.createVerticalStrut(10));

        JLabel texteChampNbFourmis = new JLabel("Nombre de fourmis");
        texteChampNbFourmis.setAlignmentX(Component.LEFT_ALIGNMENT);
        champs.add(texteChampNbFourmis);
        champs.add(Box.createVerticalStrut(5));
        JSpinner champNombreFourmis = new JSpinner(new SpinnerNumberModel(nombreFourmis, 0, 100, 5));
        champNombreFourmis.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                nombreFourmis = (int) ((JSpinner) e.getSource()).getValue();
            }
        });
        champNombreFourmis.setMaximumSize(new Dimension(100, 30));
        champNombreFourmis.setAlignmentX(Component.LEFT_ALIGNMENT);
        champs.add(champNombreFourmis);

        add(Box.createVerticalStrut(20));

        // Cases à cocher
        JPanel cocher = new JPanel();
        cocher.setLayout(new BoxLayout(cocher, BoxLayout.PAGE_AXIS));
        cocher.add(Box.createHorizontalGlue());
        cocher.setBackground(FOND_PARAM);
        cocher.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(cocher);

        JLabel texteCocher = new JLabel("Options");
        texteChampDt.setAlignmentX(Component.LEFT_ALIGNMENT);
        cocher.add(texteCocher);
        cocher.add(Box.createVerticalStrut(5));

        cocherPheromones = new JCheckBox("Affichage des phéromones", true);
        cocherPheromones.addActionListener(this);
        cocherPheromones.setAlignmentX(Component.LEFT_ALIGNMENT);
        cocher.add(cocherPheromones);
        cocherPheromones.setBackground(FOND_PARAM);

        cocherSon = new JCheckBox("Bruitages", false);
        cocherSon.addActionListener(this);
        cocherSon.setAlignmentX(Component.LEFT_ALIGNMENT);
        cocherSon.setSize(200, 20);
        cocher.add(cocherSon);
        cocher.add(Box.createVerticalStrut(20));
        cocherSon.setBackground(FOND_PARAM);

        // Sélecteur de cartes
        JPanel selecteurCartes = new JPanel();
        selecteurCartes.setLayout(new BoxLayout(selecteurCartes, BoxLayout.PAGE_AXIS));
        selecteurCartes.add(Box.createHorizontalGlue());
        selecteurCartes.setBackground(FOND_PARAM);
        selecteurCartes.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(selecteurCartes);

        selectionCartes.setMaximumSize(new Dimension(215,25));
        selectionCartes.setAlignmentX(Component.LEFT_ALIGNMENT);
        selectionCartes.setSelectedIndex(0);
        selectionCartes.addActionListener(this);

        editer = new JButton("Mode éditeur");
        editer.setMaximumSize(new Dimension(215,25));
        editer.setAlignmentX(Component.LEFT_ALIGNMENT);
        editer.addActionListener(this);
        editer.setBackground(new Color (234, 234, 234));
        selecteurCartes.add(selectionCartes);
        selecteurCartes.add(Box.createVerticalStrut(10));
        selecteurCartes.add(editer);

        add(Box.createVerticalGlue());
    }

    // Gestion des interactions avec l'utilisateurs
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cocherPheromones) {
            JCheckBox c = (JCheckBox) e.getSource();
            if (c.isSelected()) {
                Carte.affichagePheromones = true;
            } else {
                Carte.affichagePheromones = false;
            }
        }
        if (e.getSource() == cocherSon) {
            JCheckBox c = (JCheckBox) e.getSource();
            if (c.isSelected()) {
                Carte.bruitages = true;
            } else {
                Carte.bruitages = false;
            }
        }
        if (e.getSource() == selectionCartes) {
            LecteurCarte nouvelleCarte = new LecteurCarte();
            switch (selectionCartes.getSelectedIndex()) {
                case 0:
                    nouvelleCarte = new LecteurCarte("assets/cartes/bordures.txt");
                    break;
                case 1:
                    nouvelleCarte = new LecteurCarte("assets/cartes/labyrinthe.txt");
                    break;
                case 2:
                    nouvelleCarte = new LecteurCarte("assets/cartes/pont.txt");
                    break;
                default:
                    nouvelleCarte = new LecteurCarte("assets/cartes/bordures.txt");
                    break;
            }
            obstacles = nouvelleCarte.getObstacles();
            nourritures = nouvelleCarte.getNourriture();
            fourmiliere = nouvelleCarte.getFourmiliere();
        }

        if (e.getSource() == editer){
            if (!modeEditeur){
                modeEditeur = true;
                JOptionPane.showMessageDialog(null, "Le mode éditeur a été activé !", "Mode éditeur", JOptionPane.WARNING_MESSAGE);
                LecteurCarte nouvelleCarte = new LecteurCarte();
                obstacles = nouvelleCarte.getObstacles();
                nourritures = nouvelleCarte.getNourriture();
                fourmiliere = nouvelleCarte.getFourmiliere();
                MainWindow.modifierCarte();
            }
            else{
                modeEditeur = false;
                JOptionPane.showMessageDialog(null, "Le mode éditeur a été désactivé !", "Mode éditeur", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}