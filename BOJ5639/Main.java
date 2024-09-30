package BOJ5639;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int val;
        Node parent, leftChild, rightChild;

        Node(int val, Node parent) {
            this.val = val;
            this.parent = parent;
            leftChild = null;
            rightChild = null;
        }
    }

    static Node root;
    static StringBuilder ans = new StringBuilder();

    static void makeBST() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input = br.readLine()) != null && !input.isEmpty()) {
            int val = Integer.parseInt(input);
            if(root == null) {
                root = new Node(val, null);
            } else {
                Node curNode = root;
                while(true) {
                    if(val<curNode.val) {
                        if(curNode.leftChild == null) {
                            Node newNode = new Node(val, curNode);
                            curNode.leftChild = newNode;
                            break;
                        } else {
                            curNode = curNode.leftChild;
                        }
                    } else {
                        if(curNode.rightChild == null) {
                            Node newNode = new Node(val, curNode);
                            curNode.rightChild = newNode;
                            break;
                        } else {
                            curNode = curNode.rightChild;
                        }
                    }
                }
            }
        }
    }

    static void DFS(Node node) {
        if(node.leftChild != null) {
            DFS(node.leftChild);
        }
        if(node.rightChild != null) {
            DFS(node.rightChild);
        }
        ans.append(node.val + "\n");
    }

    public static void main(String[] args) throws IOException {
        makeBST();
        DFS(root);
        System.out.print(ans);
    }
}
