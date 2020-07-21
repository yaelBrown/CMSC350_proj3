import javax.swing.tree.TreeNode;
import java.util.*;

public class BinaryTree {
    public static void main(String[] args) {
        String test = "(A(G(j)(1))(z(5)))";
        String test2 = "AFDFASDASdf";

        System.out.println("test = " + test);
        System.out.println("test.length() = " + test.length());

        try {
            BinaryTree testBT = new BinaryTree(test);
        } catch (InvalidTreeSyntax invalidTreeSyntax) {
            invalidTreeSyntax.printStackTrace();
        }
    }

    public BinaryTree(String btStr) throws InvalidTreeSyntax {
        if (btStr.equals("") || !String.valueOf(btStr.charAt(0)).equals("(")) throw new InvalidTreeSyntax("Binary Tree Syntax must begin with open parentheses");
        nodeStack.add(createBinaryTree(btStr));
    }

    private Node createBinaryTree(String treenodeStr) throws InvalidTreeSyntax {
        if (start <= treenodeStr.length() - 2) {
            System.out.println("start = " + start);
            char c = treenodeStr.charAt(start);
            int l = treenodeStr.length();

            if (!isAlphaNumeric(c)) {
                carefulIncrement(l);
                return createBinaryTree(treenodeStr);
            }

            Node n = new Node(String.valueOf(c));

            int next = start + 1;

            if (next <= treenodeStr.length()) {
                if (String.valueOf(treenodeStr.charAt(next)).equals("(")) {
                    carefulIncrement(l);
                    n.addChild(createBinaryTree(treenodeStr));

                } else if (String.valueOf(treenodeStr.charAt(next)).equals(")")) {
                    carefulIncrement(l);
                    return n;
                }
            }

            if (n.getLeft() != null) {
                if (String.valueOf(treenodeStr.charAt(next)).equals("(")) {
                    carefulIncrement(l);
                    n.addChild(createBinaryTree(treenodeStr));
                }
            }

//            System.out.println("n = " + n);
            return n;
        } else {
            Node n = null;
            return n;
        }
    }

    private int carefulIncrement(int ml) {
        if (start + 1 < ml) {
            return start++;
        } else {
            return start;
        }
    }

    private boolean isAlphaNumeric(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9');
    }

    private boolean isAlphaNumeric(String cStr) {
        char c = cStr.charAt(0);
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9');
    }


    Stack<Node> nodeStack = new Stack<>();

    public static String treeInput;

    public static Node parent = null;
    public static Node child = null;
    public static Node root = null;

    private int start = 0;
    
    public boolean isFull() {
        return treeIsFull(parent, treeHeight(parent), 0);
    }

    protected boolean treeIsFull (Node root, int index, int height) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return(height == index +1);
        }

        if (root.left == null || root.right == null) {
            return false;
        }

        return treeIsFull(root.left,height,index+1) && treeIsFull(root.right, height, index+1);
    }

    public int height(Node left) {
        return treeHeight(parent)-1;
    }

    protected int treeHeight(Node root) {
        return (root == null) ? 0  : treeHeight(root.right);
    }


    public int findNodes() {
        return findTreeNodes(parent);
    }

    public int findTreeNodes(Node root) {
        return (root == null) ? 0 : findTreeNodes(root.right);
    }

    public boolean isProper() {
        return treeIsProper(parent);
    }

    public boolean treeIsProper(Node root){
        if (root == null) {
            return true;
        }
        return (
            (root.left != null || root.right == null)
            && (root.right == null || root.right != null)
            && (treeIsProper(root.left) || treeIsProper(root.left)));
        }

    public String inOrder() {
        return treeOrder(parent);
    }

    protected String treeOrder(Node root) {
        return (root == null) ? "" : "(" + treeOrder(root.left) + root.data +
                treeOrder(root.right) + ")";
    }

    //toString method for right node
    @Override
    public String toString() {
        return parent.toString();
    }

    //This method checks if the tree is balanced
    public boolean isBalanced(Node p) {
        return treeIsBalanced(null, p, p.left, p.right);
    }

    protected boolean treeIsBalanced(Node n, Node root, Node left, Node right) {
        //lh = left height, rh = right height
        int lh;
        int rh;

        //if tree is empty return true
        if (root == null) {
            return true;
        }

        //gets height of left and right sub tree
        lh = height(parent.left);
        rh = height(parent.right);

        return Math.abs(lh - rh) <= 1 && isBalanced(left) && isBalanced(right);
    }

    static class Node {

        protected String data;
        protected Node left;
        protected Node right;

        /**
         * Constructor for node class
         * @param data
         */
        public Node (String data) {
            this.data = data;
        }

        public Node () {}

        /**
         * Adds Child Node to another node
         * @param child
         * @throws InvalidTreeSyntax
         */
        protected void addChild(Node child) throws InvalidTreeSyntax {
            if (this.left == null) {
                this.setLeftChild(child);
            } else if (this.right == null) {
                this.setRightChild(child);
            } else {
                throw new InvalidTreeSyntax("Only two children nodes are allowed for each parent node");
            }
        }

        /**
         * Sets data of node.
         * @param d
         */
        protected void setData(String d) { this.data = d; }

        /**
         * Adds left node to another node
         * @param newLeft
         */
        protected void setLeftChild (Node newLeft) { left = newLeft; }

        /**
         * Adds right node to another node
         * @param newRight
         */
        protected void setRightChild (Node newRight) { right = newRight; }

        /**
         * Removes child from node
         * @param remChild
         */
        protected void removeChild (Node remChild) {
            if (this.getLeft().equals(remChild)) {
                setLeftChild(null);
            } else {
                setRightChild(null);
            }
        }

        public String getData() { return data; }
        public Node getLeft() { return left; }
        public Node getRight() { return right; }

        /**
         * Overwritten toString()
         * @return
         */
        @Override
        public String toString() {
            return "Node {" +
                    "data='" + data + '\'' +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}