package URIOnlineJudge;

import classes.Graph;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 *
 * @author Camilo Camargo
 */
public class CountriesAtWar {/*****/
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        int numberOfVertices = 0, numberOfEdges = 0, queries;
        int v[];
        
        while(!(input = br.readLine()).equals("0 0")){
            numberOfVertices = Integer.parseInt(input.split(" ")[0]);
            numberOfEdges = Integer.parseInt(input.split(" ")[1]);
            Graph cities = new Graph(numberOfVertices,true);
            v = new int[numberOfVertices];
            for (int i = 0; i < numberOfEdges; i++) {
                input = br.readLine();
                cities.createConection(Integer.parseInt(input.split(" ")[0]) - 1, 
                                       Integer.parseInt(input.split(" ")[1]) - 1, 
                                       Integer.parseInt(input.split(" ")[2]));
            }
            
            queries = Integer.parseInt(br.readLine());
            for (int i = 0; i < queries; i++) {
                Arrays.fill(cities.distance,-1);
                input = br.readLine();
                cities.modifiedShortestPath(Integer.parseInt(input.split(" ")[0]) - 1, Integer.parseInt(input.split(" ")[1]) - 1, v, 0);
                if((cities.distance[Integer.parseInt(input.split(" ")[1]) - 1]) == -1){
                    bw.write("Nao e possivel entregar a carta\n");
                }else{
                    bw.write(cities.distance[Integer.parseInt(input.split(" ")[1]) - 1] + "\n");
                }
                bw.flush();
            }
        }
    }
}
