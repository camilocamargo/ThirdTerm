package URIOnlineJudge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Camilo Camargo
 */
public class ChoosingAPlace {
    
    public static double calculateAverageAdjacent(int row, int column, int matrix[][]){
        double average = 0, count = 0;
        if (row - 1 >= 0 && column - 1 >= 0 && matrix[row - 1][column - 1] != 99
            && matrix[row - 1][column - 1] != 11 && matrix[row - 1][column - 1] != 88) {
            average += matrix[row - 1][column - 1];
            count++;
        }
        if(row - 1 >= 0 && matrix[row - 1][column] != 99
            && matrix[row - 1][column] != 11 && matrix[row - 1][column] != 88){
            average += matrix[row - 1][column];
            count++;
        }
        if(row - 1 >= 0 && column + 1 < 13 && matrix[row - 1][column + 1] != 99
            && matrix[row - 1][column + 1] != 11 && matrix[row - 1][column + 1] != 88){
            average += matrix[row - 1][column + 1];
            count++;
        }
        if(column + 1 < 13 && matrix[row][column + 1] != 99
            && matrix[row][column + 1] != 11 && matrix[row][column + 1] != 88){
            average += matrix[row][column + 1];
            count++;
        }
        if(row + 1 < 13 && column + 1 < 13 && matrix[row + 1][column + 1] != 99
            && matrix[row + 1][column + 1] != 11 && matrix[row + 1][column + 1] != 88){
            average += matrix[row + 1][column + 1];
            count++;
        }
        if(row + 1 < 13 && matrix[row + 1][column] != 99
            && matrix[row + 1][column] != 11 && matrix[row + 1][column] != 88){
            average += matrix[row + 1][column];
            count++;
        }
        if(row + 1 < 13 && column - 1 >= 0 && matrix[row + 1][column - 1] != 99
            && matrix[row + 1][column - 1] != 11 && matrix[row + 1][column - 1] != 88){
            average += matrix[row + 1][column - 1];
            count++;
        }
        if(column - 1 >= 0 && matrix[row][column - 1] != 99
            && matrix[row][column - 1] != 11 && matrix[row][column - 1] != 88){
            average += matrix[row][column - 1];
            count++;
        }
        return (average/count);
    }
    
    public static int calculateDistance(int row, int column){
        return (row + Math.abs(column - 7));
    }
    
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] cafeteria = new int[13][13];
        int i,j,k = 0;
        int WBJ, possiblePlaces = 0, lowerDistance;
        double lowerAverage;
        String[] averageAdjacent;
        String input = "";
        
        WBJ = Integer.parseInt(br.readLine());
        
        for (i = 0; i < 13; i++) {
            input = br.readLine();
            for (j = 0; j < 13; j++) {
                if(Integer.parseInt(input.split(" ")[j]) == 0){
                    possiblePlaces++;
                }
                cafeteria[i][j] = Integer.parseInt(input.split(" ")[j]);
            }
        }
        
        averageAdjacent = new String[possiblePlaces];
        
        for (i = 0; i < 13; i++) { //calculate the distance and the average for the possible places
            for (j = 0; j < 13; j++) {
                if(cafeteria[i][j] == 0){
                    averageAdjacent[k++] = calculateAverageAdjacent(i, j, cafeteria) + " " + calculateDistance(i, j) + " " + i + " " + j;
                }
            }
        }
        
        lowerAverage = 100;
        lowerDistance = 100;
        for (i = 0; i < possiblePlaces; i++) {
            if (Double.parseDouble(averageAdjacent[i].split(" ")[0]) < WBJ) {
                if (Double.parseDouble(averageAdjacent[i].split(" ")[0]) <= lowerAverage
                        && Integer.parseInt(averageAdjacent[i].split(" ")[1]) <= lowerDistance) {
                    lowerAverage = Double.parseDouble(averageAdjacent[i].split(" ")[0]);
                    lowerDistance = Integer.parseInt(averageAdjacent[i].split(" ")[1]);
                    input = averageAdjacent[i].split(" ")[2] + " " + averageAdjacent[i].split(" ")[3];
                }
            }
        }

        bw.write("linha > " + (input.split(" ")[0] + 1) + " coluna > " + (input.split(" ")[1] + 1) + "\n");
        bw.flush();
    }
}
