package Práticas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class SpreadingTheNew{


    class Graph {
        int nodes; // number of nodes
        List<Integer>[] adjacents; // adjacency lists

        @SuppressWarnings("unchecked") // due to new List[]
            public Graph(int nodes) {
                this.nodes = nodes;
                adjacents = new List[nodes];
                for (int i = 0; i < nodes; ++i)
                adjacents[i] = new LinkedList<>();
            }
            /* Adds the (directed) edge (U,V) to the graph. */
            public void addEdge(int u, int v)
            {
            adjacents[u].add(v);
            }
            ...
            }

    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int numeroAmigos = Integer.parseInt(input.readLine()); 



        



    }
}
