import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Utils
{

    public static Nod GetNearNode(Point p, Vector<Nod> listaNoduri)
    {
            for(Nod n: listaNoduri)
            {

                if(n.isNear(p)==true)
                {
                    return n;
                }
            }
        return new Nod(0);
    }

    public static Vector<Nod> ConvertAllPoints(Vector<Nod> listaNoduri)
    {
        for(Nod nod:listaNoduri)
        {
            int x = MapScale.lon2x(nod.GetCoordX());
            int y = MapScale.lat2y(nod.GetCoordY());
            nod.SetCoordX(x);
            nod.SetCoordY(y);
        }
        return listaNoduri;
    }

    public static void Output(String str)
    {
        JFrame f = new JFrame("Message!");
        Label label = new Label(str);
        label.setVisible(true);
        label.setSize(10, 10);
        f.add(label);
        f.setSize(200, 100);
        f.setLocation(150, 150);
        f.setVisible(true);
    }

}
