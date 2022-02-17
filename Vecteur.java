public class Vecteur {
    private double x;
    private double y;

    public Vecteur(double X, double Y) {
        x = X;
        y = Y;
    }

    public void set(double X, double Y) {
        double norme = Math.sqrt(Math.pow(X,2)+Math.pow(Y,2));
        x = X/norme;
        y = Y/norme;
    }

    public Vecteur somme(Vecteur v2, double coeff1, double coeff2) {
        double X = coeff1*x + coeff2*v2.x;
        double Y = coeff1*y + coeff2*v2.y;
        return new Vecteur(X,Y);
    }

    public void unitaire() {
        double norme = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        x = x/norme;
        y = y/norme;
    }

}