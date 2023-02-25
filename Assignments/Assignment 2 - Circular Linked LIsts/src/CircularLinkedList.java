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
		
		// TK: check this method
		// That which is to be inserted (or not)
		NodeType inserenda = new NodeType();
		inserenda.info = item;
		
		NodeType prevTemp = new NodeType();
		prevTemp.info = new ItemType(-1); // placeholder
		NodeType temp = this.head, prevTemp.next = temp;
		// Special case: empty list
		if (this.head == null) {
			this.head = inserenda;
			return;
		} // if
		// Novel case: singleton list TK fix whether insert before or after
		if (this.head.next == null) {
			this.head.next = inserenda;
			return;
		} // if

		for (;;) { // infinite if not explicitly returned
			
			if (item.compareTo(temp.info) > 0) {
				// Novel case: Inserenda is a maximum; insert now
				if (temp.next == this.head) {
					// Insert ahead of temp
					inserenda.next = this.head;
					temp.next = inserenda;
					return;
				} // if
				// Keep traversing
				prevTemp = prevTemp.next;
				temp = temp.next;
			} else if (item.compareTo(temp.info) == 0) { // duplicate item;
				return; // do not insert
			} // if-elif

			// if prevTemp has merged into list:
			if (prevTemp.info.getValue() != -1) {
				if (item.compareTo(temp.info) < 0) { // insert now
					// General case + Special case: inserting to front of list
					inserenda.next = temp;
					prevTemp.next = inserenda;
					// Special case: inserting to end of list
					if (prevTemp == this.head) { this.head = inserenda; }
					return;
				} // if
				// We have looped back around with no insertion; exit now
				if (temp == this.head) { return; } // TK print something?
			} else { // if prevTemp has not merged into list yet:
				// Iterate (once) to merge
				prevTemp = prevTemp.next;
				temp = temp.next;
			} // if-else
			
		} // for

	} // insertItem()
    
    /**
     * Pre-Condition: the list exists and item is initialized.
     * <p> Post-Condition: the node that contains the item is removed from the list.
     * If the item is not present in the list,
     * print the message that is shown in the example output.
     */
    void deleteItem(ItemType item) {
		
		// TK check this method
		// That which is to be deleted (or not)
		NodeType delenda = new NodeType();
		delenda.info = item;
		
		NodeType prevTemp = this.head;
		// Special case: empty list
		if (this.head == null) {
			System.out.println(""); // TK fill in quotes
			return;
		} // if

		NodeType temp = this.head.next;
		// Special case: deleting only item
		if (this.head.next == null) {
			this.head = null;
			return;
		} // if

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
				System.out.println(""); // TK fill in quotes
				return;
			} // if-elif-else
			// We have looped back around with no deletion; exit now
			if (temp == this.head) { return; } // TK print something?

		} // for

	} // deleteItem()
    
    /**
     * Pre-Condition: the list exists and item is initialized.
     * <p> Post-Condition: It return the index of the item if it is present in the list;
     * otherwise returns -1. Index starts from 1.
     */
    void SearchItem(ItemType item) {
		
		int returnIndex = 1;

		NodeType temp = this.head;
		// Novel case: empty list
		if (this.head == null) {
			System.out.println(""); // TK fill in quotes
			return -1;
 		} // if

		for (;;) { // infinite if not explicitly returned

			if (item.compareTo(temp.info) > 0) { // keep traversing
				temp = temp.next;
				returnIndex++;
		`	} else if (item.compareTo(temp.info == 0) {
				return returnIndex;
			} else {
				return -1;
			} // if-elif-else

			// We have looped back around w/o finding the item; exit now
			if (temp == this.head) { return -1; }
	
		} // for

	} // SearchItem()
    
    /**
     * Pre-Condition: the list exists.
     * <p> Post-Condition: returns the length of the list.
     */
    int length() {
		
		int returnLength = 0;
		NodeType temp = this.head;	
		for (;;) { // infinite if not explicitly returned
			temp = temp.next;
			returnLength++;
			// We have completed one loop around; stop counting
			if (temp == this.head) { break; }
		} // for
		return returnLength

	} // length()
    
    /**
     * Pre-Condition: the list exists.
     * <p> Post-Condition: items in the list are printed to standard output.
     */
    void print() {
		
		NodeType temp = this.head;	
		for (;;) { // infinite if not explicitly returned
			System.out.print(temp.info.getValue() + " ");
			temp = temp.next;
			// We have completed one loop around; stop printing
			if (temp == this.head) { return; }
		} // for

	} // print()
    
    // ----------------STATIC METHODS---------------- //
    
    /**
     * This function will take input from the user
     * for the lower and upper bound (both inclusive)
     * for a range of values that you will delete from the list.
     */
    static void deleteSubsection(int lower, int upper) {
		
		// TK should i allow deletion of entire list?
		//    or keep it as it is and leave one node at least?

		// Validate input (`lower`)
		if (lower < 1) {
			System.out.println(""); // TK fill in quotes
			return;
		} // if
		
		int counter = 1;
		NodeType prevTemp = this.head;
		NodeType temp = this.head.next;

		for (;;) { // infinite if not explicitly returned

			// We have not reached (one before) lower bound node yet
			if (counter < lower - 1) { prevTemp = prevTemp.next; }
			if (counter == upper + 1) {
				// Bridge over deleted nodes
				prevTemp.next = temp;
				return
			} // if

			// Keep traversing
			temp = temp.next;
			counter++;

			// We will imminently complete one loop around,
			// yet we still haven't found (user-inputted) upper bound
			if (temp.next == this.head && counter < upper) { return; }

		} // for

	} // deleteSubsection()
    
    /**
     * This function will return the reversed list.
     * Use the original list and change the “next” of nodes,
     * so that the list is reversed.
     * You are not allowed to create a new list that contains
     * the elements in reverse order.
     * You will get zero if you create a new list and copy
     * the elements in the reverse order in that list.
     */
    static CircularLinkedList reverseList() {
		
		NodeType prevTemp = this.head;
		NodeType temp = this.head.next;
		NodeType nextTemp = this.head.next.next;

		for (;prevTemp != this.head;) {
			// Reverse "next" direction
			temp.next = prevTemp;
			// Keep traversing
			prevTemp = temp;
			temp = nextTemp;
			nextTemp = nextTemp.next
		} // for
		
	} // reverseList()
    
    // ----------------HELPER METHODS---------------- //
    
    NodeType getHead() { return this.head; } // getHead()

	void setHead(NodeType head) { this.head = head; } // setHead(NodeType)
  
} // CircularLinkedList
