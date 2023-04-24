package AVLTree;
//Question 3
public class AVLTree {
    //node class
    public class Node{
        int key;
        Node left;
        Node right;
        int height;

        public Node(int key){
            this.key = key;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    Node root;
    //insert node
    public void insert(int key){
        root=insertNode(key);
    }
    //recursion and what nots
    public Node insertNode(int key){
        Node node = new Node(key);
        if(this.root == null){
            return node;
        }

        if(key < node.key){
            node.left = insertNode(key);
        }else if (key > node.key){
            node.right = insertNode(key);
        }else{
            return node;
        }
//update height
        node.height = 1 + Math.max(height(node.left), height(node.right));
        //check if node is balanced
        int balance = balanceFactor(node);
        //left left
        if(balance > 1 && key < node.left.key){
            return rotateRight(node);
        }

        //right right
        if(balance < -1 && key > node.right.key){
            return rotateLeft(node);
        }

        //left right
        if(balance > 1 && key > node.left.key){
            return doubleRotateLR(node);
        }

        //right left
        if(balance < -1 && key < node.right.key){
            return doubleRotateRL(node);
        }
        return node;
    }
    //calculate balance factor
    //A)
    public int balanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    //calculate height
    public int height(Node node){
        if(node == null){
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    //update the height of all nodes in the tree
    //B)
    public void updateHeight(Node node){
        if(node == null){
            return;
        }
        node.height = height(node);
        updateHeight(node.left);
        updateHeight(node.right);
    }
    //C)
    //performs a right-left and left-right rotation
    public Node doubleRotateLR(Node node){
        node.left = rotateRight(node.left);
        return rotateLeft(node);
    }

    public Node doubleRotateRL(Node node){
        node.right = rotateLeft(node.right);

        return rotateRight(node);
    }

    //performs a left-left and right-right rotation
    public Node rotateLeft(Node node){
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        updateHeight(node);
        updateHeight(temp);
        return temp;
    }

    public Node rotateRight(Node node){
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        updateHeight(node);
        updateHeight(temp);
        return temp;
    }





}
