/**
 * Note: All the functions should not crash when called on an empty list.
 */
public class CircularLinkedList {

    private NodeType head;
    
    /**
     * Default constructor.
     * <p> Post-condition: the list is created.
     */
    CircularLinkedList() {
        super();
    } // CircularLinkedList()
    
    // ----------------INSTANCE METHODS---------------- //
    
    /**
     * Pre-Condition: the list exists and item is initialized.
     * <p> Post-Condition: the item is inserted into the list, maintaining sorted order.
     */
    void insertItem(ItemType item) {
		
		// That which is to be inserted (or not)
		NodeType inserenda = new NodeType();
		inserenda.info = item;
		
		// Special case: empty list
		if (this.head == null) {
			this.head = inserenda;
			inserenda.next = this.head;
			return;
		} // if

		// Novel case: singleton list   
		if (this.head.next == this.head) {
			this.head.next = inserenda;
			inserenda.next = this.head;
			if (item.compareTo(this.head.info) > 0) { this.head = inserenda; }
			return;
		} // if
		
		// Preparation
		NodeType prevTemp = this.head;
		NodeType temp = this.head.next;

		for (;;) { // infinite if not explicitly returned
			
			if (item.compareTo(temp.info) > 0) {
				// Special case: inserting greatest item
				if (temp == this.head) {
					// Insert ahead of temp
					inserenda.next = temp.next;
					temp.next = inserenda;
					this.head = inserenda;
					return;
				} // if
				// Keep traversing
				prevTemp = temp;
				temp = temp.next;
			} else if (item.compareTo(temp.info) < 0) { // insert now
				// General case + Special case: inserting least item
				inserenda.next = temp;
				prevTemp.next = inserenda;
				return;
			} else { // duplicate item;
				System.out.println("Item already exists.");
				return; // do not insert
			} // if-elif-else

			// We have looped back around with no insertion; exit now
			if (prevTemp == this.head) { return; } // TK print something?
			
		} // for

	} // insertItem(ItemType)
    
    /**
     * Pre-Condition: the list exists and item is initialized.
     * <p> Post-Condition: the node that contains the item is removed from the list.
     * If the item is not present in the list,
     * print the message that is shown in the example output.
     */
    void deleteItem(ItemType item) {
		
		// TK check this method
		// Special case: empty list
		if (this.head == null) {
			System.out.println("You cannot delete from an empty list.");
			return;
		} // if
		
		// Special case: deleting only item
		if (this.head.next == this.head
			&& item.compareTo(this.head.info) == 0) {
			this.head = null;
			return;
		} // if
		
		// That which is to be deleted (or not)
		NodeType delenda = new NodeType();
		delenda.info = item;
		NodeType prevTemp = this.head;
		NodeType temp = this.head.next;
		
		for (;;) { // infinite if not explicitly returned
			
			if (item.compareTo(temp.info) > 0) { // keep traversing
				prevTemp = prevTemp.next;
				temp = temp.next;
			} else if (item.compareTo(temp.info) == 0) {
				// General case + Special case: deleting least item
				prevTemp.next = temp.next;
				if (temp == this.head) {
					// Special case: deleting greatest item
					this.head = prevTemp;
				} // if
				return;
			} else { // item not present
				System.out.println("The item is not present in the list.");
				return;
			} // if-elif-else
			// We have looped back around with no deletion; exit now
			if (temp == this.head.next) {
				System.out.println("The item is not present in the list.");
				return;
			} // if

		} // for

	} // deleteItem(ItemType)
    
    /**
     * Pre-Condition: the list exists and item is initialized.
     * <p> Post-Condition: It return the index of the item if it is present in the list;
     * otherwise returns -1. Index starts from 1.
	 * 
	 * <p> No side-effects like printing to stdout
     */
    int SearchItem(ItemType item) {
		
		int returnIndex = 0;

		NodeType temp = this.head;
		// Novel case: empty list
		if (this.head == null) {
			return -1;
 		} // if

		for (;;) { // infinite if not explicitly returned

			if (item.compareTo(temp.info) > 0
				|| (returnIndex == 0
					&& temp == this.head)) { // keep traversing
				// We have looped back around w/o finding the item; exit now
				if (returnIndex > 0 && temp == this.head) { return -1; }
				temp = temp.next;
				returnIndex++;
			} else if (item.compareTo(temp.info) == 0) {
				return returnIndex;
			} else {
				return -1;
			} // if-elif-else

	
		} // for

	} // SearchItem(ItemType)
    
    /**
     * Pre-Condition: the list exists.
     * <p> Post-Condition: returns the length of the list.
     */
    int length() {
		
		int returnLength = 0;
		if (this.head == null) { return returnLength; }
		NodeType temp = this.head;	
		for (;;) { // infinite if not explicitly returned
			temp = temp.next;
			returnLength++;
			// We have completed one loop around; stop counting
			if (temp == this.head) { break; }
		} // for
		return returnLength;

	} // length()
    
    /**
     * Pre-Condition: the list exists.
     * <p> Post-Condition: items in the list are printed to standard output.
     */
    void print() {
		
		NodeType temp = this.head;	
		for (; temp != null;) {
			temp = temp.next;
			System.out.print(temp.info.getValue() + " ");
			// We have completed one loop around; stop printing
			if (temp.next == this.head.next) { break; }
			// Singleton list; print item and exit
			if (temp.next == temp) {
				System.out.print(this.head.info.getValue());
				break;
			} // if
		} // for
		System.out.println();

	} // print()
    
    // ----------------STATIC METHODS---------------- //
    
//    /**
//     * This function will take input from the user
//     * for the lower and upper bound (both inclusive)
//     * for a range of values that you will delete from the list.
//     */
//    static void deleteSubsection(int lower, int upper) {
//		
//		// TK should i allow deletion of entire list?
//		// or keep it as it is and leave one node at least?
//
//		// Validate input (`lower`)
//		if (lower < 1) {
//			System.out.println(""); // TK fill in quotes
//			return;
//		} // if
//		
//		int counter = 1;
//		NodeType prevTemp = this.head;
//		NodeType temp = this.head.next;
//
//		for (;;) { // infinite if not explicitly returned
//
//			// We have not reached (one before) lower bound node yet
//			if (counter < lower - 1) { prevTemp = prevTemp.next; }
//			if (counter == upper + 1) {
//				// Bridge over deleted nodes
//				prevTemp.next = temp;
//				return;
//			} // if
//
//			// Keep traversing
//			temp = temp.next;
//			counter++;
//
//			// We will imminently complete one loop around,
//			// yet we still haven't found (user-inputted) upper bound
//			if (temp.next == this.head && counter < upper) { return; }
//
//		} // for
//
//	} // deleteSubsection()
    
    /**
	 * This function will return the reversed list.
	 * Use the original list and change the “next” of nodes,
	 * so that the list is reversed.
	 * You are not allowed to create a new list that contains
	 * the elements in reverse order.
	 * You will get zero if you create a new list and copy
	 * the elements in the reverse order in that list.
	 * 
	 * <p>
	 * <strong>Side effects:</strong> modifies argument, not a new list
	 */
    static CircularLinkedList reverseList(CircularLinkedList CLL) {
		
		if (CLL.head == null) {
			System.out.println("The list is empty.");
			return CLL;
		} // if

		NodeType prevTemp = CLL.head;
		NodeType least = CLL.head.next;
		NodeType temp = CLL.head.next;
		NodeType nextTemp = CLL.head.next.next;

		for (; prevTemp != temp;) {
			// Reverse "next" direction
			temp.next = prevTemp;
			// Keep traversing
			prevTemp = temp;
			temp = nextTemp;
			nextTemp = nextTemp.next;
			// We have looped back around; exit now
			if (prevTemp == CLL.head) {
				CLL.head = least;
				break;
			} // if
		} // for

		return CLL;
		
	} // reverseList()
    
    // ----------------HELPER METHODS---------------- //
    
    NodeType getHead() { return this.head; } // getHead()

	void setHead(NodeType head) { this.head = head; } // setHead(NodeType)
  
} // CircularLinkedList
