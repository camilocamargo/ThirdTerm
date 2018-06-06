package HackerEarth;

/*
 * HackerEarth problem MirrorImage
 */

/**
 *
 * @author Camilo Camargo
 */
import classes.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MirrorImage {
    public static void main(String[] args) throws IOException {
        BinaryTree tree = new BinaryTree();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int N, Q , parent, value;
        char side;
        Node temp;
        
        input = br.readLine();
        N = Integer.parseInt(input.split(" ")[0]);
        Q = Integer.parseInt(input.split(" ")[1]);
        tree.insert(1);//the tree is always rooted at 1.
        while(N-- > 1){
            input = br.readLine();
            parent = Integer.parseInt(input.split(" ")[0]);
            value = Integer.parseInt(input.split(" ")[1]);
            side = input.split(" ")[2].charAt(0);
            tree.insertAtNode(parent, value, side);
        }
        
        while(Q-- > 0){
            tree.mirror(Integer.parseInt(br.readLine()));
        }
    }
}
