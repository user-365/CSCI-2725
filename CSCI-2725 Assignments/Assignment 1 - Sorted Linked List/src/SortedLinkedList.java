// TK finish javadocs

// Note: always assuming NodeType can never be empty (of ItemType)
// Note: iterate using and compare with temp, while prevTemp is following
public class SortedLinkedList {
    
    private NodeType head;

    /**
     * Default no-argument constructor.
     */
    public SortedLinkedList() {
        super();
        // empty is having no node, not having empty node
    } // SortedLinkedList()

    /**
     * Getter method. You need to do a loop over the list
     * and get the number of nodes in the list.
     * 
     * <p>
     * <strong>Side effects:</strong> none.
     * 
     * TK handle edge cases that may come up
     * 
     * @return the length of the linked list
     */
    public int getLength() {
        
        int counter = 0;
        NodeType temp = this.head;
        while (temp != null) {
            counter++;
            temp = temp.next;
        } // while
        return counter;
        
    } // getLength()

    /**
     * {@code item} should be inserted to the linked list maintaining
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
        
        // superfluous but did it anyway for early exit
        if (this.head == null) { // insert in an empty list
            (this.head = new NodeType()).info = inserenda;
        } else { // begin traversing

            // TK check this
            NodeType prevTemp = new NodeType(), temp = this.head;
            prevTemp.info = new ItemType((byte) -1); // header item; nodes can't be empty
            prevTemp.next = temp;
            while (temp != null) {
                // if the inserenda's number is < the temp's number, ...
                if (inserenda.compareTo(temp.info) < 0) { // keep traversing.
                    prevTemp = temp; // shift down
                    temp = temp.next; // shift down
                } else if (inserenda.compareTo(temp.info) > 0) {
                    // insert inserenda after prevTemp
                    (prevTemp.next = new NodeType()).info = inserenda;
                    // and before temp
                    prevTemp.next.next = temp;
                    return;
                } else {
                    // otherwise (==), don't insert duplicate item
                    System.out.println("Item already exists");
                    return;
                } // if-elif-else
            } // while

        } // if-else
        
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
        
        if (this.head == null) { // delete from an empty list
            System.out.println("You cannot delete from an empty list");
            return;
        } else { // begin traversing

            // TK check this
            NodeType prevTemp = new NodeType(), temp = this.head;
            prevTemp.info = new ItemType((byte) -1); // header item; nodes can't be empty
            prevTemp.next = temp;
            while (temp != null) {
                // if the delenda's number is != the temp's number, ...
                if (delenda.compareTo(temp.info) != 0) {
                    // keep traversing.
                    prevTemp = temp; // shift down
                    temp = temp.next; // shift down
                } else { // otherwise (==),
                    // bridge the nodes around delenda
                    prevTemp.next = temp.next;
                    // we are done deleting.
                    return;
                } // if-else
            } // while
            System.out.println("The item is not present in the list");

        } // if-else
        
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
        
        // TK check this
        int index = 0;
        NodeType temp = this.head;
        while (temp != null) {
            // if the quarenda's number is != the temp's number, ...
            if (quaerenda.compareTo(temp.info) != 0) {
                // keep traversing.
                index++;
                temp = temp.next; // shift down
            } else {
                return index;
            } // if-else
        } // while
        System.out.println("Item is not present in the list");
        return -1;
        
    } // serachitem(ItemType)

    /**
     * This function should merge two lists and not include any duplicate items in
     * the list. If there are duplicates in the two lists, the merge function should
     * keep only one of the duplicate instances in the resulting list.
     * 
     * <p>Assuming, of course, that the two list arguments are sorted
     * <p><strong><em>Side effect:</em></strong> modifies list1 argument
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
    public static void mergeList(SortedLinkedList list1, SortedLinkedList list2) {
        
        // --------Early Exit:--------
        if (list1 == null) {
            if (list2 == null) { return; } // if
            list1 = list2;
            return;
        } // if
        
        // --------Setup:--------
        // pointers for the two list arguments (will be compared)
        NodeType temp1 = list1.head, temp2 = list2.head;
        // pointer for the final merged list (will not be compared)
        NodeType mergeTemp = new NodeType();
        NodeType mergedListHead = mergeTemp; // head node for the final list
        boolean temp1IsNOTNull, temp2IsNOTNull; // don't want to calculate this everytime
        
        // --------Actual Merging:--------
        // while at least one of temp1,2 is NOT null, there are still items to merge
        while ((temp1IsNOTNull = (temp1 != null))
               | (temp2IsNOTNull = (temp2 != null))) { // bitwise OR to avoid short-circuiting the boolean assignment
            // if at least one of temp1,2 IS null, we need to switch to single-track merging (in the next loop)
            if (!(temp1IsNOTNull && temp2IsNOTNull)) { break; } // if
            // compare each item.
            if (temp1.info.compareTo(temp2.info) == -1) {
                // then put in list1's item
                mergeTemp = temp1;
                temp1 = temp1.next;
            } else if (temp1.info.compareTo(temp2.info) == 1) {
                // else, put in list2's item
                mergeTemp = temp2;
                temp2 = temp2.next;
            } else { // duplicate item (==)
                // put only one item in
                mergeTemp = temp1;
                // then, iterate on BOTH lists
                temp1 = temp1.next;
                temp2 = temp2.next;
            } // if-elif-else
            // iterate (once) on merged list, to prepare for next item
            mergeTemp = mergeTemp.next;
        } // while
        
        // --------Loose Ends:--------
        // it is likely that we run out of traversing one list faster than the other.
        // for the rest of the items, simply append them to the merged list
        if (!temp1IsNOTNull && temp2IsNOTNull) { // if temp1 is null and temp2 is NOT, ...
            // then switch to temp2 to continue merging
            temp1 = temp2;
        } // if
        // only need to attach/append temp1 to the merged list because temp1 holds the chain
        // before we entered this loop, we had already prepared the next spot for an item
        mergeTemp = temp1;
        // we are now done merging.
        // do...something? do we just assign one of them to the merged list?
        list1.head = mergedListHead;
        
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
        
        // TK check (end behavior)
        NodeType temp = list.head;
        if (temp == null) {
            System.out.println("The list is empty");
            return;
        } // if
        // while neither temp nor its twice-subsequent is null
        // (if temp.next is null, then surely temp.next.next is also null)
        while (!(temp == null || temp.next == null)) {
            // if we've reached the end of the list...
            if (temp.next.next == null) {
                temp.next = null;
                return;
            } // if
            temp.next = temp.next.next; // bridge across delenda
            temp = temp.next; // iterate
        } // while
        
    } // deleteAlternateNodes()

    /**
     * Prints the instance linked-list, in ascending order, as space-separated integers.
     */
    public void printList() {

        NodeType temp = this.head;
        while (temp != null) {
            System.out.print(temp.info.getValue() + " ");
            temp = temp.next;
        } // while
        System.out.println();

    } // printList()

} // SortedLinkedList
