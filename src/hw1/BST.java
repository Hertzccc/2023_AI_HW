package hw1;
import java.util.ArrayList;
import java.util.List;

// 不同的二叉搜索树II
public class BST {
    /*
    target:给定n个节点，生成任意二叉搜索树。
    区分划分为左右区间，分别表示左右子树，从下到上生成子树返回并以笛卡尔积的方式拼装，直到主根节点。
     */

    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);

    }
    private List<TreeNode> generateTrees(int begin, int end) {
        List<TreeNode> ans = new ArrayList<>();
        // recursive exit
        if (begin > end) {
            ans.add(null);
            return ans;
        }
        for (int i = begin; i <= end; i++) {
            List<TreeNode> leftNodes = generateTrees(begin, i - 1);
            List<TreeNode> rightNodes = generateTrees(i + 1, end);
            // choose 1 from left trees and 1 from right trees
            for (TreeNode left : leftNodes) {
                for (TreeNode right : rightNodes) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    ans.add(root);
                }
            }
        }
        // return all the possible trees
        return ans;
    }
}
