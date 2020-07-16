// CMSC 350 Data Structures and Analysis
// Week 5 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class defines a binary search tree as an extension of a binary tree
// that provides all the operations of a collection plus the ability to add,
// remove and check whether an element is in the tree. It also provides an
// iterator.

package tree;

import collection.*;
import java.util.*;

public class BinarySearchTree<T extends Comparable> extends BinaryTree<T> 
	implements CollectionInterface<T>, Iterable<T>
{
	private Node<T> parent, child;

	public BinarySearchTree()
	{
	}
	
	public BinarySearchTree(T[] values)
	{
		Arrays.sort(values);
		int middle = values.length / 2;
		root = new Node();
		root.data = values[middle];
		root.left = middleSplit(values, 0, middle - 1);
		root.right = middleSplit(values, middle + 1, values.length - 1);
	}
	
	private Node<T> middleSplit(T[] elements, int left, int right)
	{
		if (left > right)
			return null;
		int middle = (left + right) / 2;
		Node<T> node = new Node();
		node.data = elements[middle];
		node.left = middleSplit(elements, left, middle - 1);
		node.right = middleSplit(elements, middle + 1, right);
		return node;
	}
	
	@Override
	public boolean add(T element)
	{
		if (contains(element))
			return false;
		Node<T> newNode = new Node();
		newNode.data = element;
		if (parent == null)
			root = newNode;
		else if (element.compareTo(parent.data) < 0)
			parent.left = newNode;
		else
			parent.right = newNode;
		return true;
	}
	
	@Override
	public boolean remove(T element)
	{
		if (!contains(element))
			return false;
		if (child == root)
			root = deleteNode(root);
		else if (parent.left == child)
			parent.left = deleteNode(parent.left);
		else
			parent.right = deleteNode(parent.right);
		return true;
	}
	
	private Node<T> deleteNode(Node<T> node)
	{
		Node<T> inorderSuccessor, successorParent;

		if (node.right == null)
			return node.left;
		else if (node.left == null)
			return node.right;
		successorParent = node.left;
		inorderSuccessor = successorParent.right;
		if (inorderSuccessor == null)
		{
			node.left = successorParent.left;
			node.data = successorParent.data;
		}
		else
		{
			while (inorderSuccessor.right != null)
			{
				successorParent = inorderSuccessor;
				inorderSuccessor = inorderSuccessor.right;
			}
			successorParent.right = inorderSuccessor.left;
			node.data = inorderSuccessor.data;
		}
		return node;
	}
	
	public boolean contains(T element)
	{
		parent = null;
		child = root;
		while (child != null)
		{
			if (element.compareTo(child.data) == 0)
				return true;
			parent = child;
			if (element.compareTo(child.data) < 0)
				child = child.left;
			else
				child = child.right;
		}
		return false;
	}
	
	@Override
	public Iterator<T> iterator()
	{
		return inorder().iterator();
	}
}