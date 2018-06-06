/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;


/**
 *
 * @author Camilo
 */
public class QueueNode {

    public int value;

    public QueueNode next;

    public QueueNode() {}
    
    public QueueNode(int value) {
        this.value = value;
    }
    
    public String toString(){
        return "Value: " + this.value + "\n"; 
    }

    public QueueNode clone() {
        QueueNode clone = new QueueNode(this.value);
        return clone;
    }
    
}