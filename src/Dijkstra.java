import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

public class Dijkstra {


    private boolean[] visited;
    private int[] distance;
    public static Vector<Nod> predecessor;

    public String GetMinDistance(Nod sourceNode, Nod destinationNode, Vector<Nod> listaNoduri)
    {
        int noNodes = listaNoduri.size();
        visited = new boolean[noNodes];
        distance = new int[noNodes];
        predecessor = new Vector<>();

        for(int index = 0; index < noNodes; index++)
        {
            distance[index] = Integer.MAX_VALUE;
            predecessor.add(new Nod(0));
        }

        PriorityQueue<Pair<Nod, Integer>> pq = new PriorityQueue<>(noNodes, new Comparator<Pair<Nod, Integer>>() {
            @Override
            public int compare(Pair<Nod, Integer> o1, Pair<Nod, Integer> o2) {
                int key1 = o1.getValue();
                int key2 = o2.getValue();
                return key1-key2;
            }
        });

        distance[sourceNode.GetNumar()] = 0;

        Pair<Nod, Integer> p0 = new Pair<>(sourceNode, distance[sourceNode.GetNumar()]);

        pq.offer(p0);

        while(!pq.isEmpty())
        {
            Pair<Nod, Integer> extractedPair = pq.poll();

            Nod extractedVertex = extractedPair.getKey();

            if(!visited[extractedVertex.GetNumar()])
            {
                visited[extractedVertex.GetNumar()] = true;

                Vector<Nod> list = extractedVertex.listaLegaturi;

                for (int index = 0; index < list.size(); index++)
                {
                    Nod adjNode = list.get(index);
                    int cost = extractedVertex.costuri.elementAt(index);

                    if (!visited[adjNode.GetNumar()])
                    {

                        int newCost =  distance[extractedVertex.GetNumar()] + cost;
                        int currentCost = distance[adjNode.GetNumar()];

                        if(newCost < currentCost)
                        {
                            Pair<Nod, Integer> p = new Pair<>(adjNode, newCost);
                            pq.offer(p);
                            distance[adjNode.GetNumar()] = newCost;
                            predecessor.set(adjNode.GetNumar(), extractedVertex);
                        }
                    }
                }
            }
        }
        String result = "Dijkstra: distanta minima de la nodul cu id-ul " + sourceNode.GetNumar()
                + " la nodul cu id-ul "+  destinationNode.GetNumar() + " este: " + distance[destinationNode.GetNumar()];
        return result;
    }

}
