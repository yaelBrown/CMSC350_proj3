// CMSC 350 Data Structures and Analysis
// Week 5 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class defines a binary tree. It provides the ability to do inorder,
// preorder and postorder tree traversals.

package tree;

import java.util.*;

public class BinaryTree<T>
{
	protected static class Node<T>
	{
		protected Node<T> left;
		protected T data;
		protected Node<T> right;
	}
	protected Node<T> root;

	public BinaryTree()
	{
	}

	public BinaryTree(T item)
	{
		root = new Node<T>();
		root.data = item;
	}

	public BinaryTree(BinaryTree<T> left, T item, BinaryTree<T> right)
	{
		root = new Node();
		root.left = left.root;
		root.data = item;
		root.right = right.root;
	}

	public ArrayList<T> preorder()
	{
		ArrayList<T> list = new ArrayList();
		preorder(root, list);
		return list;
	}

	protected void preorder(Node<T> node, ArrayList<T> list)
	{
		if (node == null)
			return;
		list.add(node.data);
		preorder(node.left, list);
		preorder(node.right, list);
	}

	public ArrayList<T> inorder()
	{
		ArrayList<T> list = new ArrayList();
		inorder(root, list);
		return list;
	}

	protected void inorder(Node<T> node, ArrayList<T> list)
	{
		if (node == null)
			return;
		inorder(node.left, list);
		list.add(node.data);
		inorder(node.right, list);
	}

	public ArrayList<T> postorder()
	{
		ArrayList<T> list = new ArrayList();
		postorder(root, list);
		return list;
	}

	protected void postorder(Node<T> node, ArrayList<T> list)
	{
		if (node == null)
			return;
		postorder(node.left, list);
		postorder(node.right, list);
		list.add(node.data);
	}

	public void iterativePreorder()
	{
		Stack<Node<T>> rightChildren = new Stack();

		Node<T> node = root;
		rightChildren.push(null);
		while (node != null)
		{
			System.out.print(node.data + " ");
			if (node.right != null)
				rightChildren.push(node.right);
			node = node.left;
			if (node == null)
				node = rightChildren.pop();
		}
	}
}
