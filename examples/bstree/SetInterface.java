// CMSC 350 Data Structures and Analysis
// Week 5 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This interface defines the operations that are required for a set data
// structure.

package bstree;

public interface SetInterface<T>
{
	void insert(T item) throws DuplicateKey;
	void delete(T item) throws NotFound;
	boolean elementOf(T item);
}
