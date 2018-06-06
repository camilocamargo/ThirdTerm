/*
 * URI online judge colony 
 */
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
public class AntsColony {
    
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = " ", output;
        int numberOfVertices = 0, queries;
        int v[];
        while (!(input = br.readLine()).equals("0")) {
            numberOfVertices = Integer.parseInt(input);
            Graph antHill = new Graph(numberOfVertices, false);
            for (int i = 1; i < numberOfVertices; i++) {
                input = br.readLine();
                antHill.createConection(i, Integer.parseInt(input.split(" ")[0]), Integer.parseInt(input.split(" ")[1]));
            }
            queries = Integer.parseInt(br.readLine());
            output = "";
            while (queries-- > 0) {
                v = new int[numberOfVertices];
                Arrays.fill(antHill.distance, -1);
                input = br.readLine();
                antHill.shortestPath(Integer.parseInt(input.split(" ")[0]), Integer.parseInt(input.split(" ")[1]), v, 0);
                output += antHill.distance[Integer.parseInt(input.split(" ")[1])] + " ";
            }
            bw.write(output + "\n");
            bw.flush();

        }        
    }
}

