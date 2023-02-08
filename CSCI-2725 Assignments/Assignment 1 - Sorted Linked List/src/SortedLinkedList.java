// TK finish javadocs
// TK implement methods
// TK handle null head
// TK handle other edge cases
public class SortedLinkedList {
    
    private NodeType head;

    /**
     * Default no-argument constructor.
     */
    public SortedLinkedList() {
        super();
    } // SortedLinkedList()

    /**
     * Getter method. You need to do a loop over the list
     * and get the number of nodes in the list.
     * 
     * <p>
     * <strong>Side effects:</strong> none.
     * 
     * @return the length of the linked list
     */
    public int getLength() {
        int counter = 0;
        if (this.head != null) {
            NodeType temp = this.head;
            while (temp.next != null) {
                counter++;
                temp = temp.next;
            } // while
        } // ifnotnull
        return counter;
    } // getLength()

    /**
     * item should be inserted to the linked list maintaining
     * the ascending sorted order.
     * 
     * <ul>
     * <li>General Case: Insert at the middle or end.</li>
     * <li>Special Cases:
     * <ul>
     * <li>Insert the first element</li>
     * <li>Insert in an empty list</li>
     * <li>Print <em><strong>“Sorry. You cannot insert the duplicate
     * item”</em></strong> when the user tries to insert duplicate item.</li>
     * </ul>
     * </li>
     * </ul>
     * 
     * <p>
     * <strong>Side effects:</strong> modify linked-list or print message to stdout.
     * 
     * @param inserenda that which is to be inserted into the linked-list
     */
    public void insertItem(ItemType inserenda) {
        
    } // insertItem(ItemType)

    /**
     * The node in the list that contains an item equal to
     * the item parameter should be removed.
     * You should handle all cases of deleting an element.
     * 
     * <ul>
     * <li>General Case: Deleting the last element or an element in the middle.</li>
     * <li>Special Cases:
     * <ul>
     * <li>Deleting the first element.</li>
     * <li>Deleting the only element</li>
     * <li>Attempt to delete a non-existing item should
     * print <em><strong>“Item not found”</em></strong>.</li>
     * <li>Attempt to delete from an empty list should
     * print <em><strong>“You cannot delete from
     * an empty list”</em></strong>.</li>
     * </ul>
     * </li>
     * </ul>
     * 
     * <p>
     * <strong>Side effects:</strong> modify linked-list or print message to stdout.
     * 
     * @param delenda that which is to be deleted from the linked-list
     */
    public void deleteItem(ItemType delenda) {
        
    } // deleteItem(ItemType)

    /**
     * Search the linked list that contains an item equal to
     * the parameter item and return its index. Print <em><strong>“Item not
     * found”</em></strong> if the item is not present in the list.
     * 
     * <p>
     * <strong>Side effect:</strong> print message to stdout.
     * 
     * @param quaerenda that which is to be searched in the linked-list
     * @return the index of the item if present; otherwise, -1
     */
    public int searchItem(ItemType quaerenda) {
        
    } // serachitem(ItemType)

    /**
     * This function should merge two lists and not include any duplicate items in
     * the list. If there are duplicates in the two lists, the merge function should
     * keep only one of the duplicate instances in the resulting list.
     * 
     * <ul>
     * <li>Example:
     * <p>
     * List 1: 9 13 45 36 47 89
     * </p>
     * <p>
     * List 2: 3 45 89 96
     * </p>
     * <p>
     * Merged list: 3 9 13 36 45 47 89 96
     * </p>
     * </li>
     * </ul>
     * 
     * @param list1
     * @param list2
     */
    public static void mergeList(SortedLinkedList list, SortedLinkedList list2) {
        
    } // mergeList()

    /**
     * This function should delete alternate nodes from the list. It should
     * skip the first node, delete the second, skip the third, delete the fourth
     * and so on.
     * 
     * <ul>
     * <li>Example:
     * <p>
     * List before alternate delete: 3 7 14 26 74 78
     * </p>
     * <p>
     * List after alternate delete: 3 14 74
     * </p>
     * </li>
     * </ul>
     * 
     * @param list
     */
    public static void deleteAlternateNodes(SortedLinkedList list) {
        
    } // deleteAlternateNodes()

    /**
     * Prints the instance linked-list, in order, as space-separated integers.
     */
    public void printList() {
        if (this.head != null) {
            NodeType temp = this.head;
            for (int i = 0; i < this.getLength(); i++) {
                System.out.print(temp.info.getValue() + " ");
                temp = temp.next;
            } // for
        } // ifnotnull
        System.out.println();
    } // printList()

} // SortedLinkedList