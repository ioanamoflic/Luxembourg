import java.awt.*;
import java.util.Vector;

public class Nod {

    private int numarNod;
    private Double coordonataX;
    private Double coordonataY;
    public Vector<Nod> listaLegaturi;
    public Vector<Integer> costuri;
    public Vector<Color> culori;

    public Nod(int numarNod)
    {
        this.numarNod = numarNod;
    }

    public Nod(Double coordonataX, Double coordonataY, int numarNod) {
        listaLegaturi = new Vector<>();
        costuri = new Vector<>();
        culori = new Vector<>();
        this.coordonataX = coordonataX;
        this.coordonataY = coordonataY;
        this.numarNod = numarNod;
    }

    public double GetCoordX() {
        return coordonataX;
    }

    public double GetCoordY() {
        return coordonataY;
    }
    public void SetCoordX(double x) {
       coordonataX = x;
    }

    public void SetCoordY(double y) {
       coordonataY = y;
    }
    public int GetNumar() {
        return numarNod;
    }

    public void addLegatura (Nod n, int cost) {
        listaLegaturi.add(n);
        costuri.add(cost);
        culori.add(Color.BLACK);
    }
    public boolean isNear(Point p)
    {
        if (Math.sqrt(Math.pow(p.getX() - (int)this.GetCoordX(), 2) + Math.pow(p.getY() - (int)this.GetCoordY(), 2)) < 30)
            return true;
        return false;
    }
}
