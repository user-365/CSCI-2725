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
    void insertItem(ItemType item) {} // insertItem()
    
    /**
     * Pre-Condition: the list exists and item is initialized.
     * <p> Post-Condition: the node that contains the item is removed from the list.
     * If the item is not present in the list,
     * print the message that is shown in the example output.
     */
    void deleteItem(ItemType item) {} // deleteItem()
    
    /**
     * Pre-Condition: the list exists and item is initialized.
     * <p> Post-Condition: It return the index of the item if it is present in the list;
     * otherwise returns -1. Index starts from 1.
     */
    void SearchItem(ItemType item) {} // SearchItem()
    
    /**
     * Pre-Condition: the list exists.
     * <p> Post-Condition: returns the length of the list.
     */
    int length() {} // length()
    
    /**
     * Pre-Condition: the list exists.
     * <p> Post-Condition: items in the list are printed to standard output.
     */
    void print() {} // print()
    
    // ----------------STATIC METHODS---------------- //
    
    /**
     * This function will take input from the user
     * for the lower and upper bound (both inclusive)
     * for a range of values that you will delete from the list.
     */
    static void deleteSubsection(int lower, int upper) {} // deleteSubsection()
    
    /**
     * This function will return the reversed list.
     * Use the original list and change the “next” of nodes,
     * so that the list is reversed.
     * You are not allowed to create a new list that contains
     * the elements in reverse order.
     * You will get zero if you create a new list and copy
     * the elements in the reverse order in that list.
     */
    static CircularLinkedList reverseList() {} // reverseList()
    
    // ----------------HELPER METHODS---------------- //
    
    NodeType getHead() {} // getHead()
  
} // CircularLinkedList
