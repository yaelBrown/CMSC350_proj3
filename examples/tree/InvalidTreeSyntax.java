// CMSC 350 Data Structures and Analysis
// Week 5 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class defines a checked exception that is thrown when a parenthesized
// infix expression is input that has an invalid syntax.

package tree;

class InvalidTreeSyntax extends Exception
{
	public InvalidTreeSyntax(String message)
	{
		super(message);
	}
}
