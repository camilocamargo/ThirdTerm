package HackerEarth;


/**
 *
 * @author Camilo Camargo
 */
//import classes.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class MancunianAndColoredTree {

    public static class Graph {

        public long[][] adjacencyMatrix;
        public long[] distance;
        public int[] colors;//only for Mancunian and Colored tree problem
        public int[] ancesterSameColor;//only for Mancunian and Colored Tree problem
        int[] visited; //for the graph traversal
        //Queue queue; //for BFS
        int numberOfVertices;
        boolean directed;

        public Graph(int numberOfVertices, boolean directed) {
            this.numberOfVertices = numberOfVertices;
            this.directed = directed;
            adjacencyMatrix = new long[numberOfVertices][numberOfVertices];
            visited = new int[numberOfVertices];
            distance = new long[numberOfVertices];
            Arrays.fill(distance, -1);
            //queue = new Queue();
        }

        public boolean hasConection(int from, int to) {
            return adjacencyMatrix[from][to] != 0;
        }

        public void createConection(int from, int to, int weight) {
            if (directed) {
                adjacencyMatrix[from][to] = weight;
            } else {
                adjacencyMatrix[from][to] = weight;
                adjacencyMatrix[to][from] = weight;
            }
        }

        public void ancesterSameColor(int color, int vertex) {
            for (int i = 0; i < numberOfVertices; i++) {
                if (hasConection(vertex, i)) {
                    if (colors[i] == colors[color]) {
                        ancesterSameColor[color] = i + 1;
                        return;
                    } else {
                        ancesterSameColor(color, i);
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Graph coloredTree;
        int N, C;
        String parents, colors;

        parents = br.readLine();
        N = Integer.parseInt(parents.split(" ")[0]);
        C = Integer.parseInt(parents.split(" ")[1]);
        coloredTree = new Graph(N, true);
        coloredTree.colors = new int[N];
        coloredTree.ancesterSameColor = new int[N];
        Arrays.fill(coloredTree.ancesterSameColor, -1);

        parents = br.readLine();
        colors = br.readLine();

        for (int i = 0; i < N; i++) {
            coloredTree.colors[i] = Integer.parseInt(colors.split(" ")[i]);
        }

        for (int i = 0; i < N - 1; i++) {
            coloredTree.createConection(i + 1, Integer.parseInt(parents.split(" ")[i]) - 1, 1);
        }

        for (int i = 0; i < N; i++) {
            coloredTree.ancesterSameColor(i, i);
        }

        for (int i = 0; i < N; i++) {
            bw.write(coloredTree.ancesterSameColor[i] + " ");
            bw.flush();
        }
    }
}
