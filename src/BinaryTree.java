import java.util.EmptyStackException;
import java.util.Stack;

public class BinaryTree {
    Stack<Node> nodeStack = new Stack<>();

    public static String treeInput;

    public static Node parent;
    public static Node child;
    public static Node root;

    final String [] arrayInput = treeInput.substring(1, treeInput.length()-1)
        .split("(?<=\\\\()|(?=\\\\()|(?<=\\\\))|(?=\\\\))");

    public BinaryTree(String text) {
        parent = new Node(arrayInput[0]);

        for (int i = 1; i < arrayInput.length - 1; i++) {

            if ((arrayInput[i].equals("()"))) {
                nodeStack.push(parent);
            }

            if (child != null) {
                parent = child;
            } else if (arrayInput[i].equals("()")) {
                try {
                    child = parent;
                    parent = nodeStack.pop();
                } catch (EmptyStackException exception) {
                    child = new Node(arrayInput[i]);
                }
            }

            if (parent != null) {
                try {
                    parent.addChild(child);
                } catch (InvalidTreeSyntax invalidTreeSyntax) {
                    invalidTreeSyntax.printStackTrace();
                }
            }
        }
    }

    public boolean isFull() {
        return treeIsFull(this.parent, treeHeight(this.parent), 0);
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
        return treeHeight(this.parent)-1;
    }

    protected int treeHeight(Node root) {
        return (root == null) ? 0  : treeHeight(root.right);
    }


    public int findNodes() {
        return findTreeNodes(this.parent);
    }

    public int findTreeNodes(Node root) {
        return (root == null) ? 0 : findTreeNodes(root.right);
    }

    public boolean isProper() {
        return treeIsProper(this.parent);
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
        return treeOrder(this.parent);
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

        if (Math.abs(lh-rh) <= 1 && isBalanced(left) && isBalanced(right)) {
            return true;
        }

        return false;
    }
}