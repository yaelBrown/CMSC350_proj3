// CMSC 350 Data Structures and Analysis
// Week 5 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class contains the main method that creates the GUI for the
// program that tests the ArithmeticExpressionTree class.
// The GUI allows parenthesized infix expressions to be entered, the expression
// to be evaluated and displayed in both prefix and postfix.

package tree;

import java.awt.*;
import javax.swing.*;

class ExpressionGUI extends JFrame
{
	private final JButton make = new JButton("Make Tree"),
		evaluate = new JButton("Evaluate"), prefix = new JButton("Prefix"),		
		postfix = new JButton("Postfix");
	private final JTextField input = new JTextField(30),
		output = new JTextField(30);
	private ArithmeticExpressionTree tree;
	
	public ExpressionGUI()
	{
		super("Arithmetic Expression Tree");
		setSize(650, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 1, 20, 20));
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		JLabel inputLabel = new JLabel("Enter Tree: ");
		inputPanel.add(inputLabel);
		inputPanel.add(input);
		add(inputPanel);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(make);
		buttonPanel.add(evaluate);
		buttonPanel.add(prefix);
		buttonPanel.add(postfix);
		add(buttonPanel);
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new FlowLayout());
		JLabel outputLabel = new JLabel("Output: ");
		outputPanel.add(outputLabel);
		outputPanel.add(output);
		add(outputPanel);
		make.addActionListener(event -> makeTree());
		evaluate.addActionListener(event -> output.setText("" + tree.evaluate()));
		prefix.addActionListener(event -> output.setText(tree.prefix()));
		postfix.addActionListener(event -> output.setText(tree.postfix()));
	}
	
	private void makeTree()
	{
		try
		{
			tree = new ArithmeticExpressionTree(input.getText());
			JOptionPane.showMessageDialog(null, "Tree Constructed");
		}
		catch (InvalidTreeSyntax exception)
		{
			JOptionPane.showMessageDialog(null, "Invalid Tree Syntax: " +
				exception.getMessage());
		}
	}
	
	public static void main(String[] args)
	{
		ExpressionGUI window = new ExpressionGUI();
		window.setVisible(true);
	}
}
