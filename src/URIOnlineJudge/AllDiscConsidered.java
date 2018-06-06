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
public class AllDiscConsidered {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N1, N2, D, i, j, installed[], changes, packages = 0;
        boolean dependence;
        Graph disc;
        String input;
        
        while (!(input = br.readLine()).equals("0 0 0")) {
            N1 = Integer.parseInt(input.split(" ")[0]);
            N2 = Integer.parseInt(input.split(" ")[1]);
            D = Integer.parseInt(input.split(" ")[2]);
            disc = new Graph((N1 + N2), true);
            installed = new int[N1 + N2];
            
            for (i = 0; i < D; i++) {
                input = br.readLine();
                disc.createConection(Integer.parseInt(input.split(" ")[1]) - 1, Integer.parseInt(input.split(" ")[0]) - 1, i);
            }
            changes = 2;
            packages = 0;
            while (packages < (N1 + N2)) {
                for (i = 0; i < N1; i++) {
                    dependence = false;
                    for (j = N1; j < (N1+N2); j++) {
                        if(disc.adjacencyMatrix[i][j] != 0){//the package have dependences
                            if(installed[j] == 0){//the package isn´t installed yet.
                                dependence = true;
                                break;
                            }
                        }
                    }
                    if(!dependence){
                        packages++;
                        installed[i] = 1;
                    }
                }
                changes++;
                for (i = N1; i < (N1+N2); i++) {
                    dependence = false;
                    for (j = 0; j < N1; j++) {
                        if(disc.adjacencyMatrix[i][j] != 0){//the package have dependences
                            if(installed[j] == 0){//the package isn´t installed yet.
                                dependence = true;
                                break;
                            }
                        }
                    }
                    if(!dependence){
                        packages++;
                        installed[i] = 1;
                    }
                }
            }
            bw.write(changes +"\n");
            bw.flush();
        }
    }
}
