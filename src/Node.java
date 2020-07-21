///**
// * Filename: Node.java
// * Author: Yael Brown
// * Date: 7/19/2020
// * Brief Purpose of the Program:
// */
//
//public class Node {
//
//    protected String data;
//    protected Node left;
//    protected Node right;
//
//    /**
//     * Constructor for node class
//     * @param data
//     */
//    public Node (String data) {
//        this.data = data;
//    }
//
//    /**
//     * Adds Child Node to another node
//     * @param child
//     * @throws InvalidTreeSyntax
//     */
//    protected void addChild(Node child) throws InvalidTreeSyntax {
//        if (this.left == null) {
//            this.setLeftChild(child);
//        } else if (this.right == null) {
//            this.setRightChild(child);
//        } else {
//            throw new InvalidTreeSyntax("Only two children nodes are allowed for each parent node");
//        }
//    }
//
//    /**
//     * Adds left node to another node
//     * @param newLeft
//     */
//    protected void setLeftChild (Node newLeft) { left = newLeft; }
//
//    /**
//     * Adds right node to another node
//     * @param newRight
//     */
//    protected void setRightChild (Node newRight) { right = newRight; }
//
//    public String getData() { return data; }
//    public Node getLeft() { return left; }
//    public Node getRight() { return right; }
//
//    /**
//     * Overwritten toString()
//     * @return
//     */
//    @Override
//    public String toString() {
//        return "Node {" +
//                "data='" + data + '\'' +
//                ", left=" + left +
//                ", right=" + right +
//                '}';
//    }
//}