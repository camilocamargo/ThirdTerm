
package URIOnlineJudge;

import classes.Graph;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Camilo Camargo
 */
public class AnalogimonTypes {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Graph analogimons;
        String input;
        int N, M, numberOfSpecies, spicie;
        
        while(!(input = br.readLine()).equals("0 0") ){
            numberOfSpecies = 1;
            N = Integer.parseInt(input.split(" ")[0]);
            M = Integer.parseInt(input.split(" ")[1]);
            analogimons = new Graph(N,false);
            
            /**Create the conections**/
            for (int i = 0; i < M; i++) {
                input = br.readLine();
                analogimons.createConection(Integer.parseInt(input.split(" ")[0]) - 1, Integer.parseInt(input.split(" ")[1]) - 1, 1);
            }
            
            numberOfSpecies = analogimons.countSpecies(numberOfSpecies, Integer.parseInt(br.readLine()) - 1);
            
            bw.write(numberOfSpecies + "\n");
            bw.flush();
        }
        
    }
}
