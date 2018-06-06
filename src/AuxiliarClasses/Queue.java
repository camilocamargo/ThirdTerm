package AuxiliarClasses;

/*
 * class queue
 */
/**
 *
 * @author Camilo Camargo
 */

public class Queue {

    public QueueNode head = null;//Empty List

    public boolean isEmpty() {
        // operador ternario.
        return head == null ? true : false;
    }

    public void enqueue(QueueNode newNode) {
        if(isEmpty()){
            head = newNode;
        }else{
            QueueNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public QueueNode dequeue() {
        QueueNode temp = head;
        head = head.next;
        temp.next = null;
        return temp;
    }
    
}
