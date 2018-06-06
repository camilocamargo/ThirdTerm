package classes;

/*
 * Class stack
 */

/**
 *
 * @author Camilo Camargo
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Stack {

    StackNode head = null;//Empty List

    public boolean isEmpty() {
        // operador ternario.
        return head == null ? true : false;
    }

    public void push(StackNode newNode) {
        newNode.next = head;
        head = newNode;
    }

    public StackNode pop() {
        StackNode temp = head;
        head = head.next;
        temp.next = null;
        return temp;
    }
    
    public void printStack(){
        StackNode temp = head;
        while(temp != null){
            System.out.println(temp.operation);
            temp = temp.next;
        }
    }
}
