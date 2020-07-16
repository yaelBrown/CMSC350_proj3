import javax.swing.*;
import java.awt.event.ActionListener;

public class Project3 extends JFrame {
    public static BinaryTree;
    public static treeInput;

    //Main method
    public static void main(String args[]) {

        //creates new instance of Project 3 Gui
        Project3 frame = new Project3();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisable(true);
    }



    //Creates and titles a new JFrame frame
    public JFrame frame = new JFrame("Binary Tree Categorizer");

    //Text Field for GUI
    public JTextField input = new JTextField("Enter Tree");
    public JTextField output = new JTextField("Output");

    //Buttons
    public JButton makeTree = new JButton("Make Tree ");
    public JButton isBalanced = new JButton("Is Balanced?");
    public JButton isFull = new JButton("Is Full?");
    public JButton isProper = new JButton("Is Proper?");
    public JButton height = new JButton("Height");
    public JButton nodes = new JButton("Nodes");
    public JButton inOrder = new JButton("InOrder");




    //action listeners for Jbuttons
    public void ActionListeners (JButton[] buttons) {
        for (JButton button: buttons) {
            button.addActionListener(treeActionListener);
        }
    }

    public ActionListener treeActionListener = event -> {

        try {
            //Switch statment to pick action
            switch ((event.getActionCommand())) {
                case "Make Tree":
                    treeInput = new Project3.BinaryTree(input.getText());
                    output.setText(treeInput.toString());
                    break;
                case "Is Balanced?":
                    output.setText(String.valueOf(treeInput.isBalanced()));
                    break;
                case "Is Full? ":
                    output.setText(String.valueOf(treeInput.isFull()));
                    break;
                case "Is Proper? ":
                    output.setText(String.valueOf(treeInput.isProper()));
                    break;
                case "Height":
                    output.setText(String.valueOf(treeInput.height()));
                    break;
                case "Nodes":
                    output.setText(String.valueOf(treeInput.node()));
                    break;
                case "Inorder":
                    output.setText(String.valueOf(treeInput.inOrder()));
                    break;

            }

        }catch (Project3.InvalidSyntax except){
            JOptionPane.showMessageDialog(null,"Invalid Syntax error " );
        }

    };

}