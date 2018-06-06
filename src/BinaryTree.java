/*
 * class binary tree
 */

/**
 *
 * @author Camilo Camargo
 */

public class BinaryTree {

    Node root = null;

    public boolean isEmpty() {
        return (root == null);
    }

    public Node search(int value) {
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

    public Node succesor(Node node){
        Node succesor = root;
        while(succesor != node && succesor != null){
            if(node.value < succesor.value){
                succesor = succesor.left;
            }else{
                succesor = succesor.right;
            }
        }
        /**At this point que have or not the node that we search*/
        if(node == null){
            System.out.println("The node wasn't found");
        }else{
            if(isLeaf(succesor)){//if is a leaf
                return succesor = null;
            }else if(oneChild(succesor)){
                if(succesor.right != null){//have the right child
                    return succesor.right;
                }else{
                    return succesor.left;
                }
            }else{//have the two children
                succesor = succesor.right;
                if(succesor.left == null){
                    return succesor;
                }else{
                    while(succesor.left != null){//found the node more to the left
                        succesor = succesor.left;
                    }
                }
            }
        }
        return succesor;
    }
    
    public boolean isLeaf(Node node) {
        return ((node.left == null) && (node.right == null));
    }

    public boolean oneChild(Node node) {
        return (node.left != null && node.right != null) ? false : true;
    }
    
    public boolean twoChildren(Node node){
        return (node.left != null && node.right != null);
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
        Node parentToDelete;
        /** Fisrt find the node**/
        while (toDelete != null) {
            if (toDelete.value == value) {
                break;
            }
            if (value < toDelete.value) {
                toDelete = toDelete.left;
            } else {
                toDelete = toDelete.right;
            }
        }
        /** The node was or wasn´t found at this point.**/
        parentToDelete = parent(toDelete);
        if (toDelete == null) {
            System.out.println("The node wasn´t found");
            /*** If is a leaf.**/
        }
        Node succesor = succesor(toDelete);
        Node parentSuccesor = parent(succesor);
        if (twoChildren(toDelete)) {
            succesor.right = toDelete.right;
            succesor.left = toDelete.left;
            if (succesor.value < parentSuccesor.value) {
                parentSuccesor.left = null;
            } else {
                parentSuccesor.right = null;
            }
        }
        if (succesor.value < parentToDelete.value) {
            parentToDelete.left = succesor;
        } else {
            parentToDelete.right = succesor;
        }
        toDelete = null;
        System.gc();
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
        tree.insert(20);
        tree.insert(10);
        tree.insert(25);
        tree.insert(5);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(15);
        tree.insert(12);
        tree.insert(17);
        tree.insert(22);
        tree.insert(30);
        tree.insert(28);
//        tree.insert(20);
//        tree.insert(7);
//        tree.insert(4);
//        tree.delete(8);
//        tree.insert(10);
//        tree.insert(15);
//        tree.insert(20);
//        tree.insert(16);
        //tree.leftDoubleRotation(tree.binarySearch(11));
        tree.delete(25);
        //System.out.println("sucesor: "+ tree.succesor(tree.binarySearch(25)).value);;
        System.out.println("<<<-----Preorder------>>>");
        tree.traversalPreOrder(tree.root);
        System.out.println("<<<------Inorder------>>>");
        tree.traversalInOrder(tree.root);
        System.out.println("<<<-----Postorder---->>>");
        tree.traversalPostOrder(tree.root);
    }
}
