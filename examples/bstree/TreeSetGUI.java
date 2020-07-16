// CMSC 350 Data Structures and Analysis
// Week 5 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class contains the main method that creates the GUI for the
// program that tests the TreeSet class containing strings. The GUI allows
// strings to be inserted, deleted, a check made to determine whether a 
// string is in the set and the ability to display the entire set.

package bstree;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class TreeSetGUI extends JFrame implements ActionListener
{
	private TreeSet<String> names;
	private final String[] choices = {"Insert", "Delete", "Element Of",
		"Display"};
	private final JComboBox combo = new JComboBox(choices);
	private final JTextField nameText = new JTextField(10);
	private final JButton process = new JButton("Process Request");

	public TreeSetGUI()
	{
		super("Tree Set");
		setSize(350, 170);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 10, 10));
		panel.add(new JLabel(" Name: "));
		panel.add(nameText);
		panel.add(new JLabel(" Choose Selection: "));
		panel.add(combo);
		panel.add(new JLabel(""));
		panel.add(process);
		add(panel);
		process.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		String name = nameText.getText();
		int menuChoice = combo.getSelectedIndex();
		switch (choices[menuChoice])
		{
			case "Insert":
				try
				{	
					names.insert(name);
				}
				catch (DuplicateKey exception)
				{
					JOptionPane.showMessageDialog(null, "Duplicate Key");
				}
				break;
			case "Delete":
				try
				{
					names.delete(name);
				}
				catch (NotFound exception)
				{
					JOptionPane.showMessageDialog(null, "Key Not Found");
				}
				break;
			case "Element Of":
				boolean contains = names.elementOf(name);
				JOptionPane.showMessageDialog(null, " List does " + 
					(contains ? "" : " not ") + "contain " + name);
				break;
			case "Display":
				String list = "";
				Iterator<String> iterate = names.iterator();
				while (iterate.hasNext())
					list += iterate.next() + "\n ";
				JOptionPane.showMessageDialog(null, list);
				break;
		}
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		ArrayList<String> nameList = new ArrayList();
		JFileChooser choice = new JFileChooser(new File("."));
		int option = choice.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) 
		{
			Scanner input = new Scanner(choice.getSelectedFile());
			while (input.hasNext())
			{
				String name = input.nextLine();
				nameList.add(name);
			}
		}
		TreeSetGUI window = new TreeSetGUI();
		String[] namesArray = nameList.toArray(new String[0]);
		window.names = new TreeSet(namesArray);
		window.setVisible(true);
	}
}
