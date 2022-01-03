import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class ShortestPathFaster
{
    public static Vector<Nod> predecessor;

    public static String GetMinDistance(Nod sourceNode, Nod destinationNode, Vector<Nod> listaNoduri)
    {

        int noNodes = listaNoduri.size();
        int[] distance = new int[noNodes];
        predecessor = new Vector<>();

        boolean[] queued = new boolean[noNodes];

        for (int index = 0; index < noNodes; ++index) {
            distance[index] = Integer.MAX_VALUE;
            queued[index] = false;
            predecessor.add(new Nod(0));
        }

        distance[sourceNode.GetNumar()] = 0;

        Queue<Nod> queue= new LinkedList<>();
        queue.add(sourceNode);
        queued[sourceNode.GetNumar()] = true;

        while(!queue.isEmpty())
        {
            Nod currentNode = queue.poll();

            for(int index = 0; index < currentNode.listaLegaturi.size(); ++index)
            {
                Nod leg = currentNode.listaLegaturi.elementAt(index);
                if(leg.GetNumar() == currentNode.GetNumar())
                    continue;
                if(distance[leg.GetNumar()] > distance[currentNode.GetNumar()]+ currentNode.costuri.elementAt(index))
                {
                    distance[leg.GetNumar()] = distance[currentNode.GetNumar()] + currentNode.costuri.elementAt(index);

                    if(!queued[leg.GetNumar()])
                    {
                        queue.add(leg);
                        queued[leg.GetNumar()] = true;
                        predecessor.set(leg.GetNumar(), currentNode);
                    }
                }
            }
        }

        return "SPF: distanta minima de la nodul cu id-ul " + sourceNode.GetNumar()+ " la nodul cu id-ul "
                + destinationNode.GetNumar()+ " este " + distance[destinationNode.GetNumar()];
    }
}
