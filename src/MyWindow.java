import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class MyWindow extends JPanel {

    public static Vector<Nod> listaNoduri;
    private Point startPoint = null;
    private Point endPoint = null;
    public static Nod startNode, endNode;
    Dijkstra dij = new Dijkstra();
    BellmanFord bf = new BellmanFord();
    ShortestPathFaster spf = new ShortestPathFaster();

    public MyWindow() {

        listaNoduri = ReadXML.citireNoduri("/Users/ioanamoflic/map2.xml");
        ReadXML.citireLegaturi("/Users/ioanamoflic/map2.xml", listaNoduri);
        Utils.ConvertAllPoints(listaNoduri);

        DijkstraButton();
        BellmanFordButton();
        SPFButton();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (startPoint == null || endPoint == null) {
                    if (startPoint == null) {
                        startPoint = e.getPoint();
                        startNode = Utils.GetNearNode(startPoint, listaNoduri);
                    } else {
                        endPoint = e.getPoint();
                        endNode = Utils.GetNearNode(endPoint, listaNoduri);
                    }
                }
                super.mouseClicked(e);
            }
        });
        repaint();
    }

    public void DijkstraButton() {
        JButton dijkstraButton = new JButton("Dijkstra");
        dijkstraButton.setBounds(50, 100, 100, 30);
        this.add(dijkstraButton);
        dijkstraButton.setSize(400, 400);
        dijkstraButton.setVisible(true);

        dijkstraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (endNode != null) {
                    System.out.println(MyWindow.startNode.GetNumar() + " si " + MyWindow.endNode.GetNumar());
                    String result = dij.GetMinDistance(startNode, endNode, MyWindow.listaNoduri);
                    Utils.Output(result);

                    if (dij.predecessor!=null) {

                        Nod currentNode = endNode;
                        Nod nextNode;
                        while(currentNode != startNode)
                        {
                                nextNode = dij.predecessor.elementAt(currentNode.GetNumar());
                                int index = listaNoduri.indexOf(currentNode);
                                    Vector<Nod> noduri = listaNoduri.elementAt(index).listaLegaturi;
                                    int indexCul = noduri.indexOf(nextNode);
                                    listaNoduri.elementAt(index).culori.set(indexCul, Color.RED);

                                currentNode = nextNode;
                        }
                        repaint();

                        startPoint = null;
                        endPoint = null;
                        startNode = null;
                        endNode = null;
                    }
                }else
                {
                    Utils.Output("Va rugam sa alegeti doua noduri!");
                }
            }
        });
    }

    public void BellmanFordButton() {
        JButton BellmanFordButton = new JButton("Bellman-Ford");
        BellmanFordButton.setBounds(50, 100, 100, 30);
        this.add(BellmanFordButton);
        BellmanFordButton.setSize(400, 400);
        BellmanFordButton.setVisible(true);

        BellmanFordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (endNode != null) {
                    System.out.println(MyWindow.startNode.GetNumar() + " si " + MyWindow.endNode.GetNumar());
                    String result = bf.GetMinDistance(startNode, endNode, MyWindow.listaNoduri);
                    Utils.Output(result);

                    if (bf.predecessor!=null) {

                        Nod currentNode = endNode;
                        Nod nextNode;
                        while(currentNode != startNode)
                        {
                            nextNode = bf.predecessor.elementAt(currentNode.GetNumar());
                            int index = listaNoduri.indexOf(currentNode);
                            Vector<Nod> noduri = listaNoduri.elementAt(index).listaLegaturi;
                            int indexCul = noduri.indexOf(nextNode);
                            listaNoduri.elementAt(index).culori.set(indexCul, Color.BLUE);

                            currentNode = nextNode;
                        }
                        repaint();

                        startPoint = null;
                        endPoint = null;
                        startNode = null;
                        endNode = null;
                    }
                }else
                {
                    Utils.Output("Va rugam sa alegeti doua noduri!");
                }
            }
        });
    }

    public void SPFButton() {
        JButton SPFButton = new JButton("SPF");
        SPFButton.setBounds(50, 100, 100, 30);
        this.add(SPFButton);
        SPFButton.setSize(400, 400);
        SPFButton.setVisible(true);

        SPFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (endNode != null) {
                    System.out.println(MyWindow.startNode.GetNumar() + " si " + MyWindow.endNode.GetNumar());
                    String result = spf.GetMinDistance(startNode, endNode, MyWindow.listaNoduri);
                    Utils.Output(result);

                    if (spf.predecessor!=null) {

                        Nod currentNode = endNode;
                        Nod nextNode;
                        while(currentNode != startNode)
                        {
                            nextNode = spf.predecessor.elementAt(currentNode.GetNumar());
                            int index = listaNoduri.indexOf(currentNode);
                            Vector<Nod> noduri = listaNoduri.elementAt(index).listaLegaturi;
                            int indexCul = noduri.indexOf(nextNode);
                            if(indexCul!=-1)
                            listaNoduri.elementAt(index).culori.set(indexCul, Color.GREEN);
                            else break;

                            currentNode = nextNode;
                        }
                        repaint();

                        startPoint = null;
                        endPoint = null;
                        startNode = null;
                        endNode = null;
                    }
                }else
                {
                    Utils.Output("Va rugam sa alegeti doua noduri!");
                }
            }
        });
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Nod node : listaNoduri) {
            for (int index = 0; index < node.listaLegaturi.size(); index++) {
                Nod leg = node.listaLegaturi.elementAt(index);
                g2.setColor(node.culori.elementAt(index));
                if(node.culori.elementAt(index)!=Color.BLACK)
                        g2.setStroke(new BasicStroke(3));
                else
                    g2.setStroke(new BasicStroke(1));
                g2.drawLine((int) node.GetCoordX(), (int) node.GetCoordY(), (int) leg.GetCoordX(), (int) leg.GetCoordY());
            }
        }
    }
}