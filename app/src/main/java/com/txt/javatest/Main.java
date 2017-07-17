package com.txt.javatest;

import java.util.Stack;

/**
 * Created by tongxt on 2017/1/21.
 */
public class Main {
    public static void main(String[] args){
        BinaryTreeNode root = new BinaryTreeNode(10);
        BinaryTreeNode node1 = new BinaryTreeNode(5);
        BinaryTreeNode node2 = new BinaryTreeNode(12);
        BinaryTreeNode node3 = new BinaryTreeNode(4);
        BinaryTreeNode node4 = new BinaryTreeNode(7);
        root.mLeft = node1;
        root.mRight = node2;
        node1.mLeft = node3;
        node1.mRight = node4;
        FindPathInTree find = new FindPathInTree();
        find.findPath(root,22);
    }

    static class FindPathInTree{
        public void findPath(BinaryTreeNode root,int num){
            if(root == null)
                return;
            Stack<Integer> stack = new Stack<>();
            findPath(root,num,stack);
        }

        /**
         * 被递归调用
         * @param node
         * @param remain = 初始值-从父节点到当前节点路径和
         * @param path
         */
        public void findPath(BinaryTreeNode node, int remain, Stack<Integer> path){
            if(node == null)
                return;
            if(node.mLeft == null && node.mRight == null){//当前节点左右子节点为空，表示当前节点是叶节点
                if(node.mValue == remain){ // 剩余的值和当前节点的值相同，说明找到了路径
                    System.out.println("找到路径");
                    for(int i : path){
                        System.out.print(i+",");
                    }
                    System.out.println(node.mValue);
                }
            }else{//还有子节点
                path.push(node.mValue);//保存当前节点的值
                findPath(node.mLeft,remain - node.mValue , path);//继续递归子节点
                findPath(node.mRight,remain - node.mValue , path);
                path.pop();//把当前节点的值出栈
            }
        }
    }


    static class BinaryTreeNode{
        private int mValue;
        private BinaryTreeNode mLeft;
        private BinaryTreeNode mRight;

        public BinaryTreeNode(int value){
            mValue = value;
        }
    }
}
