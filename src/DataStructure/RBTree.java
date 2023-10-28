package DataStructure;

/**
 * @program: 2023_AI_HW
 * @description: Red Black Tree Implementation
 * @create: 2023-10-28 17:34
 **/
public class RBTree {

    private static final boolean RED = false;
    private static final boolean BLACK = true;


    // root node
    private RBNode root;

    /**
     * Realize left rotation
     * <p>
     * p					pr
     * / \				   /\
     * pl  pr			  p rr
     * /\   ==>		  /\
     * rl rr          pr rl
     * <p>
     * steps:
     * #the relation of p-pl and pr-rr do not need to adjust
     * 1. change pr-rl to p-rl
     * set rl(pr.left) as right child of p
     * set p as parent of rl(pr.left)
     * 2. check if p has a parent
     * yes:
     * pr.parent = p.parent
     * pr becomes child of p.parent, left or right child?
     * if p.parent.left == p
     * p.parent.left = pr
     * else p.parent.right = pr
     * no:
     * set pr as root
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

    static class RBNode<K extends Comparable<K>, V> {

        private RBNode parent;

        private RBNode left;

        private RBNode right;

        private boolean color;

        private K key;

        private V value;


        public RBNode() {
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


        public void setParent(RBNode parent) {
            this.parent = parent;
        }

    }
}

