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
public class StackNode {
    char operation;
    StackNode next;
    
    StackNode(char operation){
        this.operation = operation;
    }
}
