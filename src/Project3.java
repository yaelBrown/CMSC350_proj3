import javax.swing.*;
import java.awt.event.ActionListener;

public class Project3 extends JFrame {
    public JFrame frame = new JFrame("Binary Tree Categorizer");

    public JTextField input = new JTextField("Enter Tree");
    public JTextField output = new JTextField("Output");

    public JButton makeTree = new JButton("Make Tree ");
    public JButton isBalanced = new JButton("Is Balanced?");
    public JButton isFull = new JButton("Is Full?");
    public JButton isProper = new JButton("Is Proper?");
    public JButton height = new JButton("Height");
    public JButton nodes = new JButton("Nodes");
    public JButton inOrder = new JButton("InOrder");

    public void ActionListeners (JButton[]buttons){
        for (JButton button : buttons) {
            button.addActionListener(treeActionListener);
        }
    }

    public ActionListener treeActionListener = event -> {
        BinaryTree treeInput = new BinaryTree(input.getText());
        switch (event.getActionCommand()) {
            case "Make Tree":
                output.setText(treeInput.toString());
                break;
            case "Is Balanced?":
                output.setText(String.valueOf(treeInput.isBalanced(null)));
                break;
            case "Is Full? ":
                output.setText(String.valueOf(treeInput.isFull()));
                break;
            case "Is Proper? ":
                output.setText(String.valueOf(treeInput.isProper()));
                break;
            case "Height":
                output.setText(String.valueOf(treeInput.height(null)));
                break;
            case "Nodes":
                output.setText(String.valueOf(""));
                break;
            case "Inorder":
                output.setText(String.valueOf(treeInput.inOrder()));
                break;
            default:
                break;
        }
    };

    public static void main(String args[]) {
        JFrame frame = new JFrame("Binary Tree Categorizer");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // center and make the elements show up. Window is empty
    }
}
