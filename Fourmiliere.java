import java.awt.*;

public class Fourmiliere {
    
    protected Vecteur position;
    protected Color couleur;
    private int nourriture;

    private static final double RAYON = 20;

    public Fourmiliere(double x, double y) {
        position = new Vecteur(x,y);
        couleur = Color.BLACK;
    }

    public Vecteur getPosition() {
        return new Vecteur(position.x,position.y);
    }  

    public void setPosition(Vecteur newPos){
        position = newPos;
    }

    public double getRayon() {
        return RAYON;
    }
    
    public void dessine(Graphics g) {
        g.setColor(couleur);
        g.fillOval((int) (position.x-RAYON), (int) (position.y-RAYON), (int)(2*RAYON), (int)(2*RAYON));
    }

    public void depot() {
        nourriture += 1;
    }

}
