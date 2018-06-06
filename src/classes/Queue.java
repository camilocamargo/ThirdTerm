package classes;

/*
 * class queue

/**
 *
 * @author Camilo Camargo
 */

public class Queue {

    QueueNode head = null;

    public boolean isEmpty() {
        return head == null ? true : false;
    }

    public void enqueue(QueueNode newNode) {
        if(isEmpty()){
            head = newNode;
        } else {
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
    
    public int front(){
        return head.value;
    }

}
