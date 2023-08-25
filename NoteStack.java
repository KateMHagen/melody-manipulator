package PP3;//Purpose: Creates NoteStack to hold note objects.

public class NoteStack {

	//Creates the top node of stack.
	Node top;                

	//Initializes top of stack.
	public NoteStack()
	{
		top = null;
	}

	//Adds object to the top of the stack.
	public void push(Object item)
	{
		//If no object in stack then object placed in first slot.
		if (top == null)
		{
			top = new Node(item);
		}
		//Otherwise object is added to top of stack.
		else 
		{
			Node n = new Node(item); 

			n.setNext(top);           
			top = n;		
		}
	}

	//Removes and returns object from top of stack.
	public Object pop()
	{
		//Returns and removes object from top of stack.
		if(top != null) 
		{
			Node n = top;
			top = top.getNext();
			return n.getItem();
		}
		//Returns null if stack empty.
		return null;
	}

	//Returns top object in stack.
	public Object peek() 
	{
		if(top != null)
		{
			return top.getItem();
		}
		//Returns null if empty.
		return null;
	}

	//Checks whether or not stack is empty.
	public boolean isEmpty()
	{
		if(top != null)
		{
			return true;
		}
		return false;
	}


}