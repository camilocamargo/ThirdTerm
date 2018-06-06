package classes;


import java.util.Arrays;

/**
 * Graph
 */
/**
 *
 * @author Camilo Camargo
 */
public class Graph {
    public long[][] adjacencyMatrix;
    public long[] distance;
    public int[] colors;//only for Mancunian and Colored tree problem
    public int[] ancesterSameColor;//only for Mancunian and Colored Tree problem
    
    int[] visited; //for the graph traversal
    Queue queue; //for BFS
    int numberOfVertices;
    boolean directed;
    
    public Graph(int numberOfVertices, boolean directed){
        this.numberOfVertices = numberOfVertices;
        this.directed = directed;
        adjacencyMatrix = new long[numberOfVertices][numberOfVertices];
        visited = new int[numberOfVertices];
        distance = new long[numberOfVertices];
        Arrays.fill(distance, -1);
        queue = new Queue();
    }
    
    public boolean isBidirectional(int from, int to){
        return (adjacencyMatrix[from][to] != 0 && adjacencyMatrix[to][from] != 0);
    }
    
    public boolean hasConection(int from, int to){
        return adjacencyMatrix[from][to] != 0;
    }
    
    public void createConection(int from, int to, int weight){
        if(directed){
            adjacencyMatrix[from][to] = weight;
        }else{
            adjacencyMatrix[from][to] = weight;
            adjacencyMatrix[to][from] = weight;
        }
    }
    
    public String DFS(int start, String output){
        output += start + " ";
        visited[start] = 1;
        for (int i = 0; i < numberOfVertices; i++) {
                if(adjacencyMatrix[start][i] != 0 && visited[i] == 0){
                    output = DFS(i,output);
                }
        }
        return output;
    }
    
    public String BFS(int start, String output){
        if(visited[start] == 0){
            output += start + " ";
            visited[start] = 1;
        }
        for (int i = 0; i < numberOfVertices; i++) {
            if(adjacencyMatrix[start][i] != 0 && visited[i] == 0){
                output += i + " ";
                visited[i] = 1;
                queue.enqueue(new QueueNode(i));
            }
        }
        while(!queue.isEmpty()){
            output = BFS(queue.dequeue().value,output);
        }
        return output;
    }
    
    public int countSpecies(int count, int vertice){
        visited[vertice] = 1;
        for (int i = 0; i < numberOfVertices; i++) {
            if(hasConection(vertice, i) && visited[i] == 0){
                count = countSpecies(++count,i);
            }
        }
        return count;
    }
    
    public long distance(int from, int to){
        return adjacencyMatrix[from][to];
    }
    
    public void shortestPath(int from, int to, int visited[], long distance) {
        visited[from] = 1;
        if(adjacencyMatrix[from][to] != 0) {//founded
            if(this.distance[to] == -1){
                this.distance[to] = distance + distance(from,to);
            }else if(distance + distance(from,to) < this.distance[to]){
                this.distance[to] = distance + distance(from,to);
            }
        }
        for (int i = 0; i < numberOfVertices; i++) {
            if(adjacencyMatrix[from][i] != 0 && visited[i] == 0 && i != to){
                shortestPath(i, to, visited.clone(), distance + distance(from,i));
            }
        }
    }
    
    public void modifiedShortestPath(int from, int to, int visited[], long distance) {
        visited[from] = 1;
        if(hasConection(from, to)) {//founded
            if (isBidirectional(from, to)) {
                if (this.distance[to] == -1) {
                    this.distance[to] = distance;
                } else if (distance < this.distance[to]) {
                    this.distance[to] = distance;
                }
            } else {
                if (this.distance[to] == -1) {
                    this.distance[to] = distance + distance(from, to);
                } else if (distance + distance(from, to) < this.distance[to]) {
                    this.distance[to] = distance + distance(from, to);
                }
            }
        }
        for (int i = 0; i < numberOfVertices; i++) {
            if(adjacencyMatrix[from][i] != 0 && visited[i] == 0 && i != to){
                if(isBidirectional(from, i)){
                    modifiedShortestPath(i, to, visited.clone(), distance);
                }else{
                    modifiedShortestPath(i, to, visited.clone(), distance + distance(from,i));
                }
            }
        }
    }
    
    public void ancesterSameColor(int color, int vertex) {
        for (int i = 0; i < numberOfVertices; i++) {
            if(hasConection(vertex, i)){
                if(colors[i] == colors[color]){
                    ancesterSameColor[color] = i + 1;
                    return;
                }else{
                    ancesterSameColor(color,i);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Graph g = new Graph((4), true);
        g.createConection(0, 1, 5);
        g.createConection(1, 0, 10);
        g.createConection(2, 3, 8);
        g.createConection(3, 2, 7);
        g.createConection(1, 2, 6);
//        g.createConection(2, 3, 10);
//        g.createConection(5, 3, 7);
//        g.createConection(4, 3, 20);
        int v[] = new int [7];
        g.modifiedShortestPath(0, 1, v, 0);
        String output = "";
        //System.out.println(g.BFS(0, output));
        for (int i = 0; i < g.numberOfVertices; i++) {
            System.out.print(g.distance[i] + " ");
        }
    }
}

/**
 public int shortestPath(int from, int to, int visited[], int distance) {
        System.out.println("------");
        System.out.println("from " +from+ " to "+to +" "+ Arrays.toString(visited)+" " +"distance"+ distance);
        visited[from] = 1;
        if(adjacencyMatrix[from][to] != 0) {//founded
            if(this.distance[to] == -1){
                this.distance[to] = distance + distance(from,to);
            }else if(distance + distance(from,to) < this.distance[to]){
                this.distance[to] = distance + distance(from,to);
            }
        }
        for (int i = 0; i < numberOfVertices; i++) {
            if(adjacencyMatrix[from][i] != 0 && visited[i] == 0 && i != to){
                System.out.println("llamar recursion y "+distance + distance(from,i)+ " i "+ i);
                shortestPath(i, to, visited.clone(), distance + distance(from,i));
            }
        }
        return distance;
    }
 
 **/