package classes;

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
    static Stack position = new Stack();
    Node left = null;
    Node right = null; 
    
    Node(){}
    
    Node(int value){
        this.value = value;
    }
    Node(char operation){
        this.operation = operation;
    }
    
    public static void addPosition(char direction){
        position.push(new StackNode(direction));
    }
    
    public static Stack getPosition(){
        return position;
    }
    
    public static void clearPosition(){
        position.head = null;
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
