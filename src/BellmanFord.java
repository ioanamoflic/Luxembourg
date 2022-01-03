import java.util.Vector;

public class BellmanFord {

    public static Vector<Nod> predecessor;

    public static String GetMinDistance(Nod sourceNode, Nod destinationNode, Vector<Nod> listaNoduri) {

        int noNodes = listaNoduri.size();
        int[] distance = new int[noNodes];
        predecessor = new Vector<>();

        for (int index = 0; index < noNodes; ++index) {
            distance[index] = Integer.MAX_VALUE;
            predecessor.add(new Nod(0));
        }

        distance[sourceNode.GetNumar()] = 0;

        boolean changed;
        do {
            changed = false;

            for (Nod currentNode : listaNoduri)
            {
                for (int index = 0; index < currentNode.listaLegaturi.size(); ++index)
                {
                    Nod leg = currentNode.listaLegaturi.elementAt(index);

                    if (distance[currentNode.GetNumar()]!= Integer.MAX_VALUE &&
                            distance[currentNode.GetNumar()] + currentNode.costuri.elementAt(index)
                            < distance[leg.GetNumar()])
                    {
                        distance[leg.GetNumar()] = distance[currentNode.GetNumar()]
                                + currentNode.costuri.elementAt(index);
                        changed = true;
                        predecessor.set(leg.GetNumar(), currentNode);
                    }
                }
            }
        } while (changed);


        return "Bellman-Ford: distanta minima de la nodul cu id-ul " + sourceNode.GetNumar()+ " la nodul cu id-ul "
                + destinationNode.GetNumar()+ " este " + distance[destinationNode.GetNumar()];
    }
}
