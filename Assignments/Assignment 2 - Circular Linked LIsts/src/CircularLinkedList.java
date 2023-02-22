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
		
		// TK: check this
		// That which is to be inserted (or not)
		NodeType inserenda = new NodeType();
		inserenda.info = item;
		
		NodeType prevTemp = new NodeType();
		prevTemp.info = new ItemType(-1); // (least) lower bound; header
		NodeType temp = this.head, prevTemp.next = temp;
		// Special case: empty list
		if (this.head == null) {
			this.head = inserenda;
			return;
		} // if
		// Novel case: singleton list
		if (this.head.next == null) {
			this.head.next = inserenda;
			return;
		} // if

		while (temp != null) { // infinite if not explicitly returned
			
			if (item.compareTo(temp.info) > 0) {
				// Novel case: Inserenda is an upper bound; insert now
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
				if (temp == this.head) { return; }
			} else { // if prevTemp has not merged into list yet:
				// Iterate (once) to merge
				prevTemp = prevTemp.next;
				temp = temp.next;
			} // if-else
			
		} // while

//		while (temp != null) { // infinite if not explicitly returned
//			if (item.compareTo(temp.info) > 0) { // keep traversing
//				// Inserenda is an upper bound; insert now
//				if (temp.next == this.head) {
//					inserenda.next = this.head;
//					temp.next = inserenda;
//					return;
//				} // if
//				temp = temp.next;
//				prevTemp = prevTemp.next;
//			} else if (item.compareTo(temp.info) < 0) { // insert now
//				// General case + Special case: inserting to front of list
//				inserenda.next = temp;
//				prevTemp.next = inserenda;
//				// Special case: inserting to end of list
//				if (prevTemp == this.head) { this.head = inserenda; }
//				return;
//			} else { // duplicate item;
//				return; // do not insert
//			} // if-elif-else
//			// We have looped back around with no insertion; exit now
//			if (temp == this.head) { return; }
//		} // while

	} // insertItem()
    
    /**
     * Pre-Condition: the list exists and item is initialized.
     * <p> Post-Condition: the node that contains the item is removed from the list.
     * If the item is not present in the list,
     * print the message that is shown in the example output.
     */
    void deleteItem(ItemType item) {
		
		// That which is to be deleted (or not)
		NodeType delenda = new NodeType();
		delenda.info = item;
		
		NodeType prevTemp = this.head;
		// Special case: empty list
		if (prevTemp == null) {
			System.out.println(""); // TK fill in quotes
			return;
		} // if
		NodeType temp = this.head.next;

		

	} // deleteItem()
    
    /**
     * Pre-Condition: the list exists and item is initialized.
     * <p> Post-Condition: It return the index of the item if it is present in the list;
     * otherwise returns -1. Index starts from 1.
     */
    void SearchItem(ItemType item) {
		
		

	} // SearchItem()
    
    /**
     * Pre-Condition: the list exists.
     * <p> Post-Condition: returns the length of the list.
     */
    int length() {
		
		

	} // length()
    
    /**
     * Pre-Condition: the list exists.
     * <p> Post-Condition: items in the list are printed to standard output.
     */
    void print() {
		
		

	} // print()
    
    // ----------------STATIC METHODS---------------- //
    
    /**
     * This function will take input from the user
     * for the lower and upper bound (both inclusive)
     * for a range of values that you will delete from the list.
     */
    static void deleteSubsection(int lower, int upper) {
		
		

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
		
		

	} // reverseList()
    
    // ----------------HELPER METHODS---------------- //
    
    NodeType getHead() { return this.head; } // getHead()
  
} // CircularLinkedList
