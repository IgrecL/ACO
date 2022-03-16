import java.awt.*;
import java.util.ArrayList;

public class Obstacle {
    private Color couleur;
    private ArrayList<Segment> murs = new ArrayList<Segment>();

    public Obstacle (Vecteur[] points) {
        for (int i = 0; i < points.length; i++) {
            murs.add(new Segment(points[i],points[(i+1)%points.length]));
        }
        couleur = Color.YELLOW;
    }

    public void dessine(Graphics g) {
        g.setColor(couleur);
        int[] X = new int[murs.size()];
        int[] Y = new int[murs.size()];
        int i = 0;
        for (Segment m : murs) {
            X[i] = (int)m.pointA.x;
            Y[i] = (int)m.pointA.y;
            i++;
        }
        g.drawPolygon(X, Y, X.length);
    }

}
