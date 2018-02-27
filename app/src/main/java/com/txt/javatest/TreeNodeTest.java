package com.txt.javatest;

/**
 * Created by tongxiaotao on 18-2-24.
 */

public class TreeNodeTest {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
    }

    public TreeNode createTree(int index){
        TreeNode node = new TreeNode();
        node.val = index + 1;
        if(node.val > 10){
            node.left = null;
            node.right = null;
            return null;
        }
        node.left = createTree(node.val);
        node.right = createTree(node.val);
        return node;
    }
}
