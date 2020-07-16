public class Node {

    protected String data;
    protected Node left;
    protected Node right;

    public Node (String data) {
        this.data = data;
    }

    protected void addChild(Node child) throws InvalidTreeSyntax {

        if (this.left == null) {
            this.setLeft(child);
        } else if (this.right == null) {
            this.setRight(child);
        } else {
            throw new InvalidTreeSyntax("Only two children nodes are allowed for each parent node");
        }
    }

    protected void setLeft (Node newLeft) {
        left = newLeft;
    }
    protected void setRight (Node newRight) {
        right = newRight;
    }

    protected static String toString(Node root){
        return (root == null) ? "" :
                "(" + root.data + toString(root.left)
                        + toString(root.right) + ")";
    }
}