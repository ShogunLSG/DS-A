package BST;
import BST.Node;


public class BST {
    Node root;

    public BST(int key){
        this.root = new Node(key);
    }

    public BST(){
        this.root = null;
    }

    void insert(int key){
        this.root = insertRec(this.root,key);
    }

    //create insertion function
    Node insertRec(Node root, int key)
    {
        // If the tree is empty,
        // return a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if(key <root.key){
            root.left = insertRec(root.left,key);
        }else if(key >root.key){
            root.right = insertRec(root.right,key);
        }
        return root;

    }
}
