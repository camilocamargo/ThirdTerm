package AuxiliarClasses;

/**
 *
 * @author Camilo Camargo
 */
public class QueueNode {
    public char direction;
    public QueueNode next;
    
    public QueueNode(char direction){
        this.direction = direction;
        this.next = null;
    }
    
    public QueueNode clone(){
        QueueNode node = new QueueNode(this.direction);
        return node;
    }
}
