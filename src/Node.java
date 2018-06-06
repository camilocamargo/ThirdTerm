/*
 * Node class for binary tree.
 */

/**
 *
 * @author Camilo Camargo Convers
 */
public class Node {
    int value;
    char operation;
    Node left = null;
    Node right = null; 
    
    Node(){}
    
    Node(int value){
        this.value = value;
    }
    Node(char operation){
        this.operation = operation;
    }
    
    public String toString(){
        return "Valor: " + this.value;
    }
    
    public Node clone(){
        Node newNode = new Node(this.value);
//        newNode.left = this.left;
//        newNode.right = this.right;
        return newNode;
    }
}
