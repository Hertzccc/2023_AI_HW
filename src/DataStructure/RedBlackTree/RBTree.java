package DataStructure.RedBlackTree;

/**
 * @program: 2023_AI_HW
 * @description: Red Black Tree Implementation
 * @create: 2023-10-28 17:34
 **/
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;


    public RBNode getRoot() {
        return root;
    }

    // root node
    private RBNode root;

    /**
     * Realize left rotation
     * <p>
     * p					pr
     * / \				   /\
     * pl  pr			  p rr
     *      /\   ==>	  /\
     *     rl rr         pr rl
     * <p>
     * steps:
     * #the relation of p-pl and pr-rr do not need to adjust
     * 1. change pr-rl to p-rl
     *      set rl(pr.left) as right child of p
     *      set p as parent of rl(pr.left)
     * 2. check if p has a parent
     *      yes:
     *          pr.parent = p.parent
     *          //pr becomes child of p.parent, left or right child?
     *          if p.parent.left == p
     *              p.parent.left = pr
     *          else p.parent.right = pr
     *       no:
     *          set pr as root
     * <p>
     * 3. replace p with pr
     * p.parent = pr
     * pr.left = p
     *
     * @param p
     */
    private void leftRotate(RBNode p) {
        if (p != null) {
            //get pr node
            RBNode pr = p.right;
            //step 1: pr-rl -> p-rl

            //set rl(pr.left) as right child of p
            p.right = pr.left;
            if (pr.left != null) {
                //set p as parent of rl(pr.left)
                pr.left.parent = p;
            }
            //step 2: check if p has a parent
            pr.parent = p.parent; // Whether or not p has a parent, set p.parent as pr.parent
            if (p.parent == null) {
                // p is root, so that it has no parent
                root = pr;
            }    // p has a parent
            else if (p.parent.left == p) {
                //p is left child of parent, set pr as left child too
                p.parent.left = pr;
            } else {
                p.parent.right = pr;
            }

            //step 3: replace p with pr
            pr.left = p;
            p.parent = pr;
        }

    }

    /**
     * Realize right rotation
     * <p>
     * p					pl
     * / \				   / \
     * pl  pr			  ll  p
     * /\   ==>	             / \
     * ll lr               lr  pr
     * <p>
     * steps:
     * #the relation of p-pr and pl-ll do not need to adjust
     * 1. change pl-lr to p-lr
     *      set lr(pl.right) as left child of p
     *      set p as parent of lr(pl.right)
     * 2. check if p has a parent
     *      yes:
     *          pl.parent = p.parent
     *          //pl becomes child of p.parent, left or right child?
     *          if p.parent.left == p
     *              p.parent.left = pl
     *          else p.parent.right = pl
     *       no:
     *          set pl as root
     * <p>
     * 3. replace p with pl
     * p.parent = pl
     * pl.right = p
     *
     * @param p
     */
    private void rightRotate(RBNode p) {
        if (p != null) {
            //get pl node
            RBNode pl = p.left;
            //step 1: pl-lr -> p-lr

            //set lr(pl.right) as left child of p
            p.left = pl.right;
            if (pl.right != null) {
                //set p as parent of rl(pr.left)
                pl.right.parent = p;
            }
            //step 2: check if p has a parent
            pl.parent = p.parent; // Whether or not p has a parent, set p.parent as pl.parent
            if (p.parent == null) {
                // p is root, so that it has no parent
                root = pl;
            }    // p has a parent
            else if (p.parent.left == p) {
                //p is left child of parent, set pr as left child too
                p.parent.left = pl;
            } else {
                p.parent.right = pl;
            }

            //step 3: replace p with pl, pl-p parent and right node
            pl.right = p;
            p.parent = pl;
        }

    }

    /**
     * Insert new nodes
     * @param key
     * @param value
     *
     * 1. Find the position to be inserted.(The parent node of the new node)
     * 2. Add new node to the children node of the parent(left or right).
     */
    public void insert(K key, V value){
        RBNode t = root;
        if(t == null){ // the first node
            root = new RBNode(key, value ==null ? key:value, null);
            return ;
        }
        //1. ...
        int cmp;
        RBNode parent;
        do {
            parent = t;

            cmp = key.compareTo((K) t.key);
            if(cmp < 0){
                //left
                t = t.left;
            }else if (cmp > 0){
                //right
                t = t.right;
            }else {
                // node exists, replace its value with the new value
                t.setValue(value==null?key:value);
            }

        }while(t != null);

        //2. ...
        //create a new node
        RBNode node = new RBNode(key, value==null? key: value, parent);
        if (cmp < 0){
            //add to the left,
            parent.left = node;
        }else{
            //add to the right
            parent.right = node;
        }
        //Rotate
        balance(node);

    }

    private RBNode parentOf(RBNode node){
        return node != null ? node.parent : null;
    }

    private RBNode leftOf(RBNode node){
        return node != null ? node.left : null;
    }

    private RBNode rightOf(RBNode node){
        return node != null ? node.right : null;
    }

    private boolean colorOf(RBNode node){
        return node == null ? BLACK : node.color;
    }

    private void setColor(RBNode node, boolean color){
        if(node != null){
            node.setColor(color);
        }
    }



    /**
     * Things to do after insertion
     *
     * Compare to 2-3-4 tree:
     *      2 nodes: Insert a new node, merge these 2 nodes together, no other adjustment
     *          RBT: Insert a red node under the black node
     *
     *      3 nodes: Insert a new node, 6 cases(2 of them no need to adjust, 4 cases to adjust)
     *          RBT:
     *
     *
     * @param x
     */
    private void balance(RBNode<K, Object> x){

        //set insert node to be red
        x.color = RED;
        // 2 nodes no need to balance, 3-4 nodes get balanced
        while(x != null && x != root && x.parent.color == RED){
            if(parentOf(x) == leftOf(parentOf(parentOf(x)))){
                // left only 4 cases
                //get uncle node of the inserted node x . Assume the structure is left-handed
                RBNode uncle = rightOf(parentOf(parentOf(x)));
                if (colorOf(uncle) == RED){
                    //there exists uncle
                    // change color and recursively upward red
                    // parent and uncle are set to black, grandpa is set to red
                    setColor(parentOf(x), BLACK);
                    setColor(uncle,BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    // throw red upward recursively
                    x = parentOf(parentOf(x));

                }else{
                    //there is no uncle
                    if (x == parentOf(x).right){
                        x = parentOf(x);
                        leftOf(x);
                    }
                    // father is set to black, grandpa is set to red
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);

                    rightRotate(parentOf(parentOf(x)));
                }
            }else{
                // also 4 cases, opposite to the cases above, the structure is right-handed

                //get uncle node of the inserted node x
                RBNode uncle = leftOf(parentOf(parentOf(x)));
                if (colorOf(uncle) == RED){
                    //there exists uncle
                    // change color and recursively upward red
                    // parent and uncle are set to black, grandpa is set to red
                    setColor(parentOf(x), BLACK);
                    setColor(uncle,BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    // throw red upward recursively
                    x = parentOf(parentOf(x));

                }else{
                    //there is no uncle
                    if (x == parentOf(x).right){
                        x = parentOf(x);
                        rightOf(x);
                    }
                    // father is set to black, grandpa is set to red
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);

                    leftRotate(parentOf(parentOf(x)));
                }
            }
        }
        // root is black
        root.color=BLACK;
    }


    static class RBNode<K extends Comparable<K>, V> {

        private RBNode parent;

        private RBNode left;

        private RBNode right;

        public void setColor(boolean color) {
            this.color = color;
        }

        private boolean color;

        private K key;

        private V value;


        public RBNode() {
        }


        public void setValue(V value) {
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public RBNode getRight() {
            return right;
        }

        public RBNode getLeft() {
            return left;
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V vaule) {
            super();
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = vaule;
        }

        public RBNode(K key, V value, RBNode parent) {
            this.parent = parent;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public boolean isColor() {
            return color;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

    }
}

