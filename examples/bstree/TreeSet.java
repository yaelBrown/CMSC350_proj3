// CMSC 350 Data Structures and Analysis
// Week 5 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class defines a sorted set implemented with a binary search tree.

package bstree;

import java.util.*;
import tree.*;

public class TreeSet<T extends Comparable> implements SortedSetInterface<T>
{
	private BinarySearchTree<T> set;
	
	public TreeSet()
	{
		set = new BinarySearchTree();
	}

	public TreeSet(T[] values)
	{
		set = new BinarySearchTree(values);
	}
	
	@Override
	public void insert(T item) throws DuplicateKey
	{
		if (set.contains(item))
			throw new DuplicateKey();
		set.add(item);
	}

	@Override 
	public void delete(T item) throws NotFound
	{
		if (!set.remove(item))
			throw new NotFound();
	}

	@Override
	public boolean elementOf(T item)
	{
		return set.contains(item);
	}
	
	public Iterator<T> iterator()
	{
		return set.iterator();
	}
}
