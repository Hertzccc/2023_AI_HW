package DataStructure.RedBlackTree;

import java.util.Scanner;

/**
 * @program: 2023_AI_HW
 * @description:
 * @create: 2023-10-28 21:49
 **/
public class RBTreeTest {
    public static void main(String[] args) {
        insertOpt();
    }

    public static void insertOpt(){
        Scanner scanner = new Scanner(System.in);
        RBTree<String, Object> rbt = new RBTree<>();
        while (true){
            System.out.println("Type the node you want to insert in: ");
            String key = scanner.next();
            System.out.println();
            if(key.length()==1){
                key="00" + key;
            }else if (key.length() ==2){
                key = "0" + key;
            }
            rbt.insert(key, null);
            TreeOperation.show(rbt.getRoot());
        }
    }
}

