import javax.swing.tree.TreeNode;
import java.util.*;

public class BinaryTree {
//    public static void main(String[] args) {
//        String test = "(A(G(j)(1))(z(5)))";
//        String test2 = "AFDFASDASdf";
//
//        System.out.println("test = " + test);
//        System.out.println("test.length() = " + test.length());
//
//        try {
//            BinaryTree testBT = new BinaryTree(test);
//
//            System.out.println("testBT.parent.toString() = " + testBT.parent.toString());
//
//
//        } catch (InvalidTreeSyntax invalidTreeSyntax) {
//            invalidTreeSyntax.printStackTrace();
//        }
//    }

    /**
     * Constructor that creates binary tree and save it in parent
     * @param btStr
     * @throws InvalidTreeSyntax
     */
    public BinaryTree(String btStr) throws InvalidTreeSyntax {
        if (btStr.equals("") || !String.valueOf(btStr.charAt(0)).equals("(")) throw new InvalidTreeSyntax("Binary Tree Syntax must begin with open parentheses");
        parent = createBinaryTree(btStr);
        System.out.println("parent.toString() = " + parent.toString());
    }

    /**
     * Helper method that creates binary tree.
     * @param treenodeStr
     * @return
     * @throws InvalidTreeSyntax
     */
    private Node createBinaryTree(String treenodeStr) throws InvalidTreeSyntax {
        if (start <= treenodeStr.length() - 2) {
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

    /**
     * Carefully increment with out generating indexOutOfBounds in helper method
     * @param ml
     * @return
     */
    private int carefulIncrement(int ml) {
        if (start + 1 < ml) {
            return start++;
        } else {
            return start;
        }
    }

    /**
     * Checks if character is either a letter or number with a char as input
     * @param c
     * @return
     */
    private boolean isAlphaNumeric(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9');
    }

    /**
     * Checks if character is either a letter or number with a string as input
     * @param cStr
     * @return
     */
    private boolean isAlphaNumeric(String cStr) {
        char c = cStr.charAt(0);
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9');
    }

    Stack<Node> nodeStack = new Stack<>();

    public static Node parent = null;
    public static Node child = null;
    public static Node root = null;

    private int start = 0;

    /**
     * Checks if parent node is full
     * @return
     */
    public boolean isFull() {
        return treeIsFull(parent, treeHeight(parent), 0);
    }

    /**
     * Checks if tree is full
     * @param root
     * @param index
     * @param height
     * @return
     */
    protected boolean treeIsFull (Node root, int index, int height) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return(height == index + 1);
        if (root.left == null || root.right == null) return false;
        return treeIsFull(root.left,height,index+1) && treeIsFull(root.right, height, index+1);
    }

    /**
     * Checks hight of nodes
     * @param left
     * @return
     */
    public int height(Node left) {
        return treeHeight(parent) - 1;
    }

    /**
     * Return height of tree
     * @param root
     * @return
     */
    protected int treeHeight(Node root) {
        return (root == null) ? 0  : treeHeight(root.right);
    }

    /**
     * Checks if tree is proper
     * @return
     */
    public boolean isProper() {
        return treeIsProper(parent);
    }

    /**
     * Checks if tree is proper
     * @param n
     * @return
     */
    public boolean treeIsProper(Node n) {
        if (n == null) { return true; }
        return (
            (n.left != null || n.right == null)
            && (n.right == null || n.right != null)
            && (treeIsProper(n.left) || treeIsProper(n.left))
        );
    }

    /**
     * Checks if nodes are in order
     * @return
     */
    public String inOrder() {
        return treeOrder(parent);
    }

    /**
     * Helper method for inOrder
     * @param n
     * @return
     */
    protected String treeOrder(Node n) {
        return (root == null) ? "" : "(" + treeOrder(n.left) + n.data +
                treeOrder(root.right) + ")";
    }

    /**
     * Returns parent node as string.
     * @return
     */
    @Override
    public String toString() {
        return parent.toString();
    }

    /**
     * Checks if tree is balanced
     * @return
     */
    public boolean isBalanced() {
        return treeIsBalanced(null, this.parent, this.parent.left, this.parent.right);
    }

    /**
     * overloaded method
     * @param n
     * @return boolean
     */
    public boolean isBalanced(Node n) {
        return treeIsBalanced(null, this.parent, n.left, n.right);
    }

    /**
     * Checks if tree is balanced
     * @param n
     * @param root
     * @param left
     * @param right
     * @return
     */
    protected boolean treeIsBalanced(Node n, Node root, Node left, Node right) {
        if (root == null) return true;
        return Math.abs(height(parent.left) - height(parent.right)) <= 1 && isBalanced(left) && isBalanced(right);
    }

    /**
     * Nested Node class
     */
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