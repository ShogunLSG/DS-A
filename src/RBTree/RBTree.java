package RBTree;

public class RBTree {
    public class Node {
        int key;
        Node left;
        Node right;
        Node parent;
        boolean color;

        public Node(int key, boolean isRed) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.color = isRed;
            this.parent = null;
        }
    }

    Node root;

    public void insert(int key) {
        Node newNode = new Node(key, true);
        //Insert node into tree
        Node parent = null;
        Node current = root;

        //locates where the node is going to be inserted
        while (current != null) {
            parent = current;
            if (key < current.key) {
                current = current.left;
            } else if (key > current.key) {
                current = current.right;
            } else {
                //key already exists in the tree
                return;
            }

        }
        //leaf node is marked as the parent on the new node
        newNode.parent = parent;
        if(parent == null) {
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        }else{
            parent.right = newNode;
        }
        //rebalance the tree by recoloring and/or rotating
        rebalance(newNode);
    }

    //method to rebalance the tree
    // *********THE HARD STUFF*********
    private void rebalance( Node node){
        while (node.parent != null && node.parent.color) {
            //checks if the parent is the left child of the grandparent
            if (node.parent == node.parent.parent.left) {
                //Uncle is set to right child because if the parent is the left child of the grandparent, then the uncle is the right child
                Node uncle = node.parent.parent.right;
                if (uncle != null && uncle.color) {
                    //case 1: uncle is red
                    //Because the parent is red, and the uncle is red, then the grandparent must be black
                    node.parent.color = false;

                    //Because the parent is red, and the grandparent is black, then the uncle must be red
                    uncle.color = false;

                    //Because the parent is red, and the uncle is black, then the grandparent must be red
                    node.parent.parent.color = true;

                    //sets the node to the grandparent to check if the grandparent is red
                    node = node.parent.parent;

                } else {
                    if (node == node.parent.right) {
                        //case 2: uncle is black and node is right child
                        node = node.parent;
                        rotateLeft(node);
                    }
                    //case 3: uncle is black and node is left child
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    rotateRight(node.parent.parent);
                }
            } else {
                //uncle is set to left child because if the parent is the right child of the grandparent, then the uncle is the left child
                Node uncle = node.parent.parent.left;
                if (uncle != null && uncle.color) {
                    //case 1: uncle is red
                    //Because the parent is red, and the uncle is red, then the grandparent must be black
                    node.parent.color = false;

                    //Because the parent is red, and the grandparent is black, then the uncle must be red
                    uncle.color = false;

                    //Because the parent is red, and the uncle is black, then the grandparent must be red
                    node.parent.parent.color = true;

                    //sets the node to the grandparent to check if the grandparent is red
                    node = node.parent.parent;

                } else {
                    if (node == node.parent.left) {
                        //case 2: uncle is black and node is left child
                        node = node.parent;
                        rotateRight(node);
                    }
                    //case 3: uncle is black and node is right child
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        //sets the root to black
        root.color = false;

    }
    //method to rotate the tree to the left
    private void rotateLeft(Node node){
        Node temp = node.right;
        node.right = temp.left;
        if(temp.left != null){
            temp.left.parent = node;
        }
        temp.parent = node.parent;
        if(node.parent == null){
            root = temp;
        }else if(node == node.parent.left){
            node.parent.left = temp;
        }else{
            node.parent.right = temp;
        }
        temp.left = node;
        node.parent = temp;
    }
    //method to rotate the tree to the right
    private void rotateRight(Node node){
        Node temp = node.left;
        node.left = temp.right;
        if(temp.right != null){
            temp.right.parent = node;
        }
        temp.parent = node.parent;
        if(node.parent == null){
            root = temp;
        }else if(node == node.parent.right){
            node.parent.right = temp;
        }else{
            node.parent.left = temp;
        }
        temp.right = node;
        node.parent = temp;
    }
}
