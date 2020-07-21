import javax.swing.*;

public class Project3 extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Binary Tree Categorizer");

        frame.add(new GUI());

        frame.setSize(800, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }

}
