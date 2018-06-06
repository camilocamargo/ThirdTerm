package HackerEarth;


//import classes.BinaryTree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * HackerEarth Monk Watching Fight
 */
/**
 *
 * @author Camilo Camargo
 */
public class MonkWatchingFight {

    public static class Node {

        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }

    public static class BinaryTree {

        Node root = null;

        public boolean isEmpty() {
            return root == null ? true : false;
        }

        public boolean isLeaf(Node node) {
            if (node != null) {
                return (node.left == null && node.right == null) ? true : false;
            }
            return false;
        }

        public void insert(int value) {
            if (isEmpty()) {
                root = new Node(value);
            } else {
                Node temp = root; //only to find the space for insertion
                Node parent = root;
                while (temp != null) {
                    parent = temp;
                    if (temp.value > value) {
                        temp = temp.left;
                    } else {
                        temp = temp.right;
                    }
                }
                if (parent.value < value) {
                    parent.right = new Node(value);
                } else {
                    parent.left = new Node(value);
                }
            }
        }

        public int nodeHeight(Node node) {
            if (isLeaf(node) || node == null) {
                return 0;
            } else {
                return nodeHeight(node.left) > nodeHeight(node.right)
                        ? nodeHeight(node.left) + 1 : nodeHeight(node.right) + 1;
            }
        }
        
    }
    public static void main(String[] args) throws IOException {
        BinaryTree tree = new BinaryTree();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        String input;
        
        N = Integer.parseInt(br.readLine());
        input = br.readLine();
        
        for (int i = 0; i < N; i++) {
            tree.insert(Integer.parseInt(input.split(" ")[i]));
        }
        
        bw.write(tree.nodeHeight(tree.root)+1 +"\n");
        bw.flush();
    }
}
