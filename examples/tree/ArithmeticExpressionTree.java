// CMSC 350 Data Structures and Analysis
// Week 5 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class defines an arithmetic expression tree that is an extension of a
// binary tree. It provides the ability to construct a tree from a parenthesized
// infix expression, the ability to evaluate the expression and the ability to
// convert the expression to prefix and postfix.

package tree;

import java.util.*;

class ArithmeticExpressionTree extends BinaryTree<String>
{
	public ArithmeticExpressionTree(String treeString) throws InvalidTreeSyntax
	{
		StringTokenizer tokenizer = new StringTokenizer(treeString, 
			"()+-*/ ", true);
		root = makeNode(tokenizer);
	}
	private Node<String> makeNode(StringTokenizer tokenizer)
		throws InvalidTreeSyntax
	{
		Node<String> node;
		String token = nextNonBlank(tokenizer);
		if (isDouble(token))
		{
			node = new Node();
			node.data = token;
			return node;
		}
		if (!token.equals("("))
			throw new InvalidTreeSyntax("Missing Left Paremthesis");
		Node<String> leftChild = makeNode(tokenizer);
		token = nextNonBlank(tokenizer);
		if (!token.equals("+") && !token.equals("-") &&
			!token.equals("*") && !token.equals("/"))
			throw new InvalidTreeSyntax("Invalid Operator");
		Node<String> rightChild = makeNode(tokenizer);
		if (!nextNonBlank(tokenizer).equals(")"))
			throw new InvalidTreeSyntax("Missing Right Paremthesis");
		node = new Node();
		node.data = token;
		node.left = leftChild;
		node.right = rightChild;
		return node;
	}
	private String nextNonBlank(StringTokenizer tokenizer)
		throws InvalidTreeSyntax
	{
		String token;
		while (tokenizer.hasMoreTokens())
			if (!(token = tokenizer.nextToken()).equals(" "))
				return token;
		throw new InvalidTreeSyntax("Incomplete Expression");
	}
	public double evaluate()
	{
		if (root == null)
			return 0;
		return evaluate(root);
	}
	private double evaluate(Node<String> node)
	{
		if (isDouble(node.data))
			return Double.parseDouble(node.data);
		switch (node.data)
		{
			case "+":
				return evaluate(node.left) + evaluate(node.right);
			case "-":
				return evaluate(node.left) - evaluate(node.right);
			case "*":
				return evaluate(node.left) * evaluate(node.right);
			case "/":
				return evaluate(node.left) / evaluate(node.right);
		}
		return 0;
	}
	public String prefix()
	{
		ArrayList<String> list = new ArrayList();
		preorder(root, list);
		return makeString(list);
	}
	public String postfix()
	{
		ArrayList<String> list = new ArrayList();
		postorder(root, list);
		return makeString(list);
	}
	private boolean isDouble(String string)
	{
		try
		{
			Double.parseDouble(string);
			return true;
		}
		catch (NumberFormatException exception)
		{
			return false;
		}
	}
	private String makeString(ArrayList<String> list)
	{
		String result = "";
		for (String string: list)
			result += string + " ";
		return result;
	}
}
