
import AuxiliarClasses.QueueNode;
import AuxiliarClasses.Queue;

/*
 * Class AVL Tree
 */

/**
 *
 * @author Camilo Camargo
 */
public class AVLTree {

    Node root = null;
    Node lastInserted;
    public static Queue path = new Queue();

    public boolean isEmpty() {
        return root == null ? true : false;
    }

    public boolean isLeaf(Node node) {
        if (node != null) {
            return (node.left == null && node.right == null) ? true : false;
        }
        return false;
    }

    public boolean oneChild(Node node) {
        return (node.left != null && node.right != null) ? false : true;
    }

    public boolean twoChildren(Node node) {
        return (node.left != null && node.right != null);
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

    public int nodeHeight(Node node) {
        if (isLeaf(node) || node == null) {
            return 0;
        } else {
            return nodeHeight(node.left) > nodeHeight(node.right)
                    ? nodeHeight(node.left) + 1 : nodeHeight(node.right) + 1;
        }
    }

    public void traceRoute(Node from, Node to) {
        path.head = null;//clear the queue
        Node temp = from;
        while (temp != to) {
            if (to.value < temp.value) {
                temp = temp.left;
                path.enqueue(new QueueNode('L'));
            } else {
                temp = temp.right;
                path.enqueue(new QueueNode('R'));
            }
        }
    }

    public int unbalaceFactor(Node node) {
        if (node.left == null) {
            return -(nodeHeight(node.right) + 1);
        } else if (node.right == null) {
            return nodeHeight(node.left) + 1;
        } else {
            return (nodeHeight(node.left) + 1) - (nodeHeight(node.right) + 1);
        }
    }

    public void balance(Node node) {
        if (node != null) {
            balance(node.left);
            balance(node.right);
            if (unbalaceFactor(node) > 1) {//more nodes on the left
                traceRoute(node, lastInserted);
                path.dequeue();//discard the fist
                if (path.dequeue().direction == 'L') {
                    right_rotation(node);
                } else {
                    left_double_rotation(node);
                }
            } else if (unbalaceFactor(node) < -1) {
                traceRoute(node, lastInserted);
                path.dequeue();//discard the first
                if (path.dequeue().direction == 'R') {
                    left_rotation(node);
                } else {
                    right_double_rotation(node);
                }
            }
        }
    }

    public Node parent(Node node) {
        if (node == root) {
            return root;
        } else {
            Node temp = root;
            Node parent = null;

            while (temp != null) {
                if (node.value == temp.value) {
                    break;
                } else {
                    parent = temp;
                    if (node.value < temp.value) {
                        temp = temp.left;
                    } else {
                        temp = temp.right;
                    }
                }
            }

            if (temp == null) {
                parent = null;
            }
            return parent;
        }
    }

    public void right_rotation(Node node) {
        Node parent = null;
        if (node != root) {
            parent = parent(node);
        }
        Node middle = node.left;
        if (middle.right != null) {
            node.left = middle.right;
        } else {
            node.left = null;
        }
        middle.right = node;
        if (parent != null) {
            if (middle.value < parent.value) {
                parent.left = middle;
            } else {
                parent.right = middle;
            }
        } else {
            root = middle;
        }
    }

    public void left_rotation(Node node) {
        Node parent = null;
        if (node != root) {
            parent = parent(node);
        }
        Node middle = node.right;
        if (middle.left != null) {
            node.right = middle.left;
        } else {
            node.right = null;
        }
        middle.left = node;
        if (parent != null) {
            if (middle.value < parent.value) {
                parent.left = middle;
            } else {
                parent.right = middle;
            }
        } else {
            root = middle;
        }
    }

    public void left_double_rotation(Node node) {
        Node node2 = node.left;
        Node node3 = node2.right;
        left_rotation(node2);
        right_rotation(node);
    }

    public void right_double_rotation(Node node) {
        Node node2 = node.right;
        Node node3 = node2.left;
        right_rotation(node2);
        left_rotation(node);
    }

    public void insert(int value) {
        Node insert = new Node(value);
        if (isEmpty()) {
            root = insert;
            lastInserted = insert;
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
                parent.right = insert;
            } else {
                parent.left = insert;
            }
            lastInserted = insert;
            balance(root);
        }
    }

    public void delete(int value) {
        Node toDelete = root;
        Node parentToDelete;
        /*** Fisrt find the node**/
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
        /*** The node was or wasn´t found at this point.**/
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

    public Node succesor(Node node) {
        Node succesor = root;
        while (succesor != node && succesor != null) {
            if (node.value < succesor.value) {
                succesor = succesor.left;
            } else {
                succesor = succesor.right;
            }
        }
        /** At this point que have or not the node that we search*/
        if (node == null) {
            System.out.println("The node wasn't found");
        } else {
            if (isLeaf(succesor)) {//if is a leaf
                return succesor = null;
            } else if (oneChild(succesor)) {
                if (succesor.right != null) {//have the right child
                    return succesor.right;
                } else {
                    return succesor.left;
                }
            } else {//have the two children
                succesor = succesor.right;
                if (succesor.left == null) {
                    return succesor;
                } else {
                    while (succesor.left != null) {//found the node more to the left
                        succesor = succesor.left;
                    }
                }
            }
        }
        return succesor;
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
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(30);
        tree.insert(15);
        tree.insert(20);
        tree.insert(10);
        tree.insert(5);
        tree.insert(18);
        tree.insert(22);
        tree.insert(40);
        tree.insert(17);
        tree.insert(19);
        tree.insert(16);
        
        tree.traversalPreOrder(tree.root);
//        System.out.println(" "+tree.unbalance_factor(tree.root) );
//        System.out.println(" "+tree.nodeHeight(tree.root));
    }
}
