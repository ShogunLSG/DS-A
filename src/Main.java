import RBTree.*;

public class Main {


        public static void main(String[] args) {
            //Question 4
            //insert the numbers: 1,2,5,7,8,11,14,15
            RBTree tree = new RBTree();
            tree.insert(1);
            tree.insert(2);
            tree.insert(5);
            tree.insert(7);
            tree.insert(8);
            tree.insert(11);
            tree.insert(14);
            tree.insert(15);

            //assignement question
            tree.insert(3);
        }
}
