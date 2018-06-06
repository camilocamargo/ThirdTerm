package URIOnlineJudge;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * URI online judge maester´s map
 */
/**
 *
 * @author Camilo Camargo
 */
public class MaestersMap {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int x, y, i, j;
        char board[][];
        boolean chest = false;

        x = Integer.parseInt(br.readLine());
        y = Integer.parseInt(br.readLine());

        board = new char[y][x];

        for (i = 0; i < y; i++) {
            input = br.readLine();
            for (j = 0; j < x; j++) {
                if (input.charAt(j) == '*') {
                    chest = true;
                }
                board[i][j] = input.charAt(j);
            }
        }

        if (!chest) {//if isn´t a chest in the given map
            bw.write("!\n");
        } else {//travers the array to find the chest
            i = 0;
            j = 0;
            while (true) {
                if(board[i][j] == '+'){
                    System.out.println("-----"+board[i][j]);
                    bw.write("!\n");
                    break;
                }
                if(board[i][j] == '*'){
                    bw.write("*\n");
                    break;
                }
                if (board[i][j] == '>') {
                    board[i][j++] = '+';
                    while (board[i][j] != '<' && board[i][j] != 'v' && board[i][j] != '^' && j < x && board[i][j] != '+' && board[i][j] != '*') {
                        j++;
                    }
                } else if (board[i][j] == 'v') {
                    board[i++][j] = '+';
                    while (board[i][j] != '<' && board[i][j] != '>' && board[i][j] != '^' && i < y && board[i][j] != '+' && board[i][j] != '*') {
                        i++;
                    }
                } else if (board[i][j] == '<') {
                    board[i][j--] = '+';
                    while (board[i][j] != 'v' && board[i][j] != '>' && board[i][j] != '^' && j > 0 && board[i][j] != '+' && board[i][j] != '*') {
                        j--;
                    }
                } else if (board[i][j] == '^') {
                    board[i--][j] = '+';
                    while (board[i][j] != 'v' && board[i][j] != '>' && board[i][j] != '<' && i > 0 && board[i][j] != '+' && board[i][j] != '*') {
                        i--;
                    }
                }
            }
        }
        bw.flush();
    }
}
