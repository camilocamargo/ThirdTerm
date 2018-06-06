package classes;

/*
 * class binary tree
 */

/**
 *
 * @author Camilo Camargo
 */

public class BinaryTree {

    public Node root = null;

    public boolean isEmpty() {
        return root == null ? true : false;
    }
    
    public int nodeHeight(Node node){
        if (isLeaf(node) || node == null){
            return 0;
        }else{
            return nodeHeight(node.left) > nodeHeight(node.right) ? 
                    nodeHeight(node.left) + 1 : nodeHeight(node.right) + 1;
        }
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
    
    public Node search(int value,Node node){//only for the problem mirror image
        if (node != null) {
            Node temp = null;
            if (node.value == value) {
                return node;
            } else {
                temp = search(value, node.left);
                Node.addPosition('L');
                if(temp == null){
                    Node.position.pop();
                    temp = search(value, node.right);
                    Node.addPosition('R');
                }
                if(temp == null){
                   Node.position.pop(); 
                }
            }
            return temp;
        }
        return null;
    }
    
    public void insertAtNode(int parent,int newNode, char side){
        Node temp = search(parent, root);
        if(side == 'R' || side == 'r'){
            temp.right = new Node(newNode);
        }else{
            temp.left = new Node(newNode);
        }
    }
    
    public void mirror(int value){
        Stack stack = new Stack();
        Node temp = root;
        Node.clearPosition();
        search(value,root);
        stack = Node.getPosition();
        while(!stack.isEmpty()){
            if(stack.pop().operation == 'L'){
                temp = temp.right;
            }else{
                temp = temp.left;
            }
            if(temp==null){
                System.out.println("-1");
                return;
            }
        }
        System.out.println(temp.value);
    }
    
    public Node parent(Node node) {
        Node temp = root;
        Node parent = null;
        if(node == root){
            return parent;
        }
        if (!isEmpty()) {
            /**
             * While doesn't found the node, and temp diferent of null*
             */
            while (temp != null && temp.value != node.value) {
                parent = temp;
                if (node.value < temp.value) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
            /**
             * The node wasn't found.*
             */
            if (temp == null) {
                parent.value = -1;
            }
        }
        return parent;
    }

    public int balanceFactor(Node node) { //falta terminar
        int balanceFactor = 0;
        Node temp = binarySearch(node.value);
        Node left = temp, right = temp;
        while (left != null) {

        }
        return balanceFactor;
    }

    public void rightSimpleRotation(Node node1) {
        Node parent = parent(node1);
        Node node2 = node1.left;
        node2.right = node1;
        node1.left = null;
        if (parent != null) {
            if (node2.value < parent.value) {
                parent.left = node2;
            } else {
                parent.right = node2;
            }
        } else {
            root = node2;
        }
    }

    public void rightDoubleRotation(Node node1){
        Node node2 = node1.right;
        Node node3 = node2.left;
        rightSimpleRotation(node2);
        leftSimpleRotation(node1);
    }
    
    public void leftSimpleRotation(Node node1) {
        Node parent = parent(node1);
        Node node2 = node1.right;
        node2.left = node1;
        node1.right = null;
        if (parent != null) {
            if (node2.value < parent.value) {
                parent.left = node2;
            } else {
                parent.right = node2;
            }
        } else {
            root = node2;
        }
    }
    
    public void leftDoubleRotation(Node node1){
        Node node2 = node1.left;
        Node node3 = node2.right;
        leftSimpleRotation(node2);
        rightSimpleRotation(node1);
    }

    public boolean isLeaf(Node node) {
        if(node != null){
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

    public void remove(int value) {
        //first find the value
        if (!isEmpty()) {
            Node toDelete = root;
            Node preToDelete = toDelete;
            boolean direction = false; //false for left true for right
            while (toDelete != null) {
                if (value < toDelete.value) {
                    direction = false;
                    preToDelete = toDelete;
                    toDelete = toDelete.left;
                } else {
                    direction = true;
                    preToDelete = toDelete;
                    toDelete = toDelete.right;
                }
                if (toDelete.value == value) {
                    break;
                }
            }
            //at this point the node with the value was found.
            if (toDelete != null) { //if was found 
                if (toDelete.right == null && toDelete.left == null) {//if the Node is a leaf
                    if (!direction)//for the left
                    {
                        preToDelete.left = null;
                    } else {
                        preToDelete.right = null;
                    }
                    toDelete = null;
                    System.gc();
                } else if (toDelete.left != null && toDelete.right == null) { //if only have child for the left
                    if (!direction) {
                        preToDelete.left = toDelete.left; // point the left of preToDelete to the left of toDelete
                    } else {
                        preToDelete.right = toDelete.left;// point the right of preToDelete to the left of toDelete
                    }
                    toDelete = null;
                    System.gc();
                } else if (toDelete.right != null && toDelete.left == null) { //if only have child for the right
                    if (!direction) {
                        preToDelete.left = toDelete.right; // point the left of preToDelete to the right of toDelete
                    } else {
                        preToDelete.right = toDelete.right;// point the right of preToDelete to the right of toDelete
                    }
                    toDelete = null;
                    System.gc();
                } else {
                    Node succesor = toDelete;
                    Node preSuccesor = succesor;

                    succesor = toDelete.right; //find the smaller value among the biggers
                    while (succesor.left != null) {
                        preSuccesor = succesor;
                        succesor = succesor.left;
                    }

                    if (!direction) {//for the left
                        if (succesor.right != null) {
                            preSuccesor.left = succesor.right;
                        }
                        succesor.left = toDelete.left;
                        succesor.right = toDelete.right;
                        preToDelete.left = succesor;
                    } else {
                        if (succesor.right != null) {
                            preSuccesor.left = succesor.right;
                        }
                        succesor.left = toDelete.left;
                        succesor.right = toDelete.right;
                        preToDelete.right = succesor;
                    }
                    toDelete = null;
                    System.gc();
                }
            }
        }
    }

    public void traversalPreOrder(Node node) {
        if (node != null) {
            System.out.println(node.toString());
            traversalPreOrder(node.left);
            traversalPreOrder(node.right);
        }
    }

    public void traversalInOrder(Node node) {
        if (node != null) {
            traversalInOrder(node.left);
            System.out.println(node.toString());
            traversalInOrder(node.right);
        }
    }

    public void traversalPostOrder(Node node) {
        if (node != null) {
            traversalPostOrder(node.left);
            traversalPostOrder(node.right);
            System.out.println(node.toString());
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
//        tree.insert(20);
//        tree.insert(8);
//        tree.insert(4);
//        tree.insert(5);
//        tree.insert(15);
//        tree.insert(10);
//        tree.insert(12);
//        tree.insert(13);
//        tree.insert(14);
//        tree.insert(25);
//        tree.insert(21);
//        tree.insert(50);
//        tree.insert(40);
//        tree.insert(30);
//        tree.insert(20);
//        tree.insert(7);
//        tree.insert(4);
//        tree.delete(8);
        tree.insert(10);
        tree.insert(15);
        tree.insert(20);
        tree.insert(16);
        //tree.leftDoubleRotation(tree.binarySearch(11));
        System.out.println("<<<-----Preorder------>>>");
        tree.traversalPreOrder(tree.root);
        System.out.println("<<<------Inorder------>>>");
        tree.traversalInOrder(tree.root);
        System.out.println("<<<-----Postorder---->>>");
        tree.traversalPostOrder(tree.root);
    }
}
