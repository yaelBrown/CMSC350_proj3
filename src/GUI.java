import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JPanel {
    private JLabel treeLabel = new JLabel("Enter Tree: ");
    private JTextField treeInput = new JTextField(40);

    private JButton makeTree = new JButton("Make Tree");
    private JButton isBalanced = new JButton("Is Balanced?");
    private JButton isFull = new JButton("Is Full?");
    private JButton isProper = new JButton("Is Proper?");
    private JButton height = new JButton("Height");
    private JButton nodes = new JButton("Nodes");
    private JButton inOrder = new JButton("Inorder");

    private JLabel outputLabel = new JLabel("Output: ");
    private JTextField output = new JTextField(40);

    public void actionListeners (JButton[] btns) {
        for (JButton b : btns) {
            b.addActionListener(btnsActions);
        }
    }
    public ActionListener btnsActions = event -> {
        try {
            BinaryTree bt = new BinaryTree(treeInput.getText());
            switch (event.getActionCommand()) {
                case "Make Tree":
                    System.out.println("ASDFASDF");
                    output.setText(bt.parent.toString());
                    break;
                case "Is Balance?":
                    output.setText(String.valueOf(bt.isBalanced()));
                    break;
                case "Is Full?":
                    output.setText(String.valueOf(bt.isFull()));
                    break;
                case "Is Proper?":
                    output.setText(String.valueOf(bt.isProper()));
                    break;
                case "Height":
                    output.setText(String.valueOf(bt.height(null)));
                    break;
                case "Nodes":
                    output.setText(String.valueOf(bt.parent.toString()));
                    break;
                case "Inorder":
                    output.setText(String.valueOf(bt.inOrder()));
                    break;
                default:
                    output.setText("default?");
                    break;
            }

        } catch (InvalidTreeSyntax invalidTreeSyntax) {
            invalidTreeSyntax.printStackTrace();
            output.setText("Invalid Tree Syntax Error");
        }
    };

    public GUI() {
        setLayout(new GridLayout(3, 1, 2, 2));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel row1 = new JPanel();
        row1.add(treeLabel);
        row1.add(treeInput);

        JPanel row2 = new JPanel();
        row2.add(makeTree);
        row2.add(isBalanced);
        row2.add(isFull);
        row2.add(isProper);
        row2.add(height);
        row2.add(nodes);
        row2.add(inOrder);

        JPanel row3 = new JPanel();
        row3.add(outputLabel);
        row3.add(output);

        add(row1);
        add(row2);
        add(row3);
    }

}
