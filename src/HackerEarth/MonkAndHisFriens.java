package HackerEarth;


//import classes.BinaryTree;
//import classes.Node;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * HackerEarth Monk and his friends
 */
/**
 *
 * @author Camilo Camargo
 */
public class MonkAndHisFriens {

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

        public Node binarySearch(int value) {
            Node temp = root;
            while (temp != null) {
                if (temp.value == value) {
                    break;
                }
                if (value < temp.value) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
            return temp;
        }

        public boolean isLeaf(Node node) {
            if (node != null) {
                return (node.left == null && node.right == null) ? true : false;
            }
            return false;
        }

        public boolean oneChild(Node node) {
            return (node.left != null && node.right != null) ? false : true;
        }

        public void insert(int value) {
            if (isEmpty()) {
                root = new Node(value);
            } else {
                Node temp = root; //only to find the space for insertion
                Node parent = root;
                while (temp != null) {
                    parent = temp;
                    if (temp.value == value) {
                        return;
                    }
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

        public void delete(int value) {
            Node toDelete = root;
            Node parentToDelete = toDelete;
            /**
             * Fisrt find the node*
             */
            while (toDelete != null) {
                if (toDelete.value == value) {
                    break;
                }
                parentToDelete = toDelete;
                if (value < toDelete.value) {
                    toDelete = toDelete.left;
                } else {
                    toDelete = toDelete.right;
                }
            }
            /**
             * The node was or wasn´t found at this point.*
             */
            if (toDelete == null) {
                System.out.println("No existe");
                /**
                 * If is a leaf.*
                 */
            } else if (isLeaf(toDelete)) {
                toDelete = null;
                if (value < parentToDelete.value) {
                    parentToDelete.left = null;
                } else {
                    parentToDelete.right = null;
                }
                /**
                 * If has one child*
                 */
            } else if (oneChild(toDelete)) {
                /**
                 * If has one child for the left*
                 */
                if (toDelete.left != null) {
                    if (value < parentToDelete.value) {
                        parentToDelete.left = toDelete.left;
                    } else {
                        parentToDelete.right = toDelete.left;
                    }
                    /**
                     * If has one child for the right.*
                     */
                } else if (value < parentToDelete.value) {
                    parentToDelete.left = toDelete.right;
                } else {
                    parentToDelete.right = toDelete.right;
                }
            } else {
                /**
                 * If have two children.*
                 */
                Node succesor = toDelete.right;
                Node parentSuccesor = succesor;
                if (succesor.left == null) {
                    succesor.left = toDelete.left;
                    if (value < parentToDelete.value) {
                        parentToDelete.left = succesor;
                    } else {
                        parentToDelete.right = succesor;
                    }
                } else {
                    while (succesor.left != null) {
                        parentSuccesor = succesor;
                        succesor = succesor.left;
                    }
                    /**
                     * At this point the succesor was found.* If is a leaf.*
                     */
                    if (isLeaf(succesor)) {
                        succesor.left = toDelete.left;
                        succesor.right = toDelete.right;
                    } else {
                        parentSuccesor.left = succesor.right;
                        succesor.left = toDelete.left;
                        succesor.right = toDelete.right;
                    }
                    if (value < parentToDelete.value) {
                        parentToDelete.left = succesor;
                    } else {
                        parentToDelete.right = succesor;
                    }
                    toDelete = null;
                    System.gc();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BinaryTree tree = new BinaryTree();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T, N, M, i = 0;
        String input;
        Node toFind;

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            i = 0;
            input = br.readLine();
            N = Integer.parseInt(input.split(" ")[0]);
            M = Integer.parseInt(input.split(" ")[1]);

            input = br.readLine();
            while (i < N) {
                tree.insert(Integer.parseInt(input.split(" ")[i++]));
            }

            while (i < (N + M)) {
                toFind = tree.binarySearch(Integer.parseInt(input.split(" ")[i++]));
                if (toFind != null) {
                    bw.write("YES\n");
                    tree.delete(Integer.parseInt(input.split(" ")[i-1]));
                } else {
                    bw.write("NO\n");
                    tree.insert(Integer.parseInt(input.split(" ")[i-1]));
                }
                bw.flush();
            }
        }
    }
}
