// TK finish javadocs
// TK implement methods
// TK handle null head
// TK handle other edge cases
// Note: always assuming NodeType can never be empty (of ItemType)
// Note: iterate over and compare with tempNode, while preTempNode is following
// TK change pre to prev lol
public class SortedLinkedList {
    
    private NodeType head;

    /**
     * Default no-argument constructor.
     */
    public SortedLinkedList() {
        super();
        // empty is no Node, not empty Node
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
        
        if (this.head == null) { // insert in an empty list
            (this.head = new NodeType()).info = inserenda;
        } else { // begin traversing
            NodeType preTempNode = this.head, tempNode = preTempNode.next;
            // insert before first item
            if (inserenda.compareTo(preTempNode.info) <= 0) { // if < first item
                NodeType prePreTempNode = new NodeType();
                prePreTempNode.info = inserenda;
                prePreTempNode.next = preTempNode;
                this.head = prePreTempNode;
            } // if
            // as long as the inserenda's number is <= the temp's number, ...
            while (tempNode != null
                && inserenda.compareTo(tempNode.info) <= 0) { // keep traversing.
                preTempNode = tempNode; // shift down
                tempNode = tempNode.next; // shift down
            } // while
            // otherwise (>), insert inserenda after preTempNode (& before tempNode)
            (preTempNode.next = new NodeType()).info = inserenda;
            preTempNode.next.next = tempNode;
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
        } else { // begin traversing
            NodeType preTempNode = this.head, tempNode = preTempNode.next;
            // delete first item
            if (delenda.compareTo(preTempNode.info) == 0) { // if == first item
                this.head = tempNode;
            } // if
              // as long as the delenda's number is != the temp's number, ...
            while (tempNode != null
                    && delenda.compareTo(tempNode.info) != 0) { // keep traversing.
                preTempNode = tempNode; // shift down
                tempNode = tempNode.next; // shift down
            } // while
            // otherwise (==), link before and after Nodes around delenda
            preTempNode.next = tempNode.next;
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
        
        int index = -1;
        if (this.head == null) { // search from an empty list
            System.out.println("Item not found");
            // intentional fall-through
        } else { // begin traversing
            NodeType temp = this.head;
            // as long as the quarenda's number is != the temp's number, ...
            while (temp != null
                    && quaerenda.compareTo(temp.info) != 0) { // keep traversing.
                index++;
                temp = temp.next; // shift down
            } // while
            // intentional fall-through
        } // if-else
        return index;
        
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
        
        // we are going to traverse down both lists (always traverse lists thoroughly).
        
        // Setup:
        NodeType temp1 = list1.head, temp2 = list2.head;
        // header to avoid handling edge cases
        NodeType mergeTemp = new NodeType();
        mergeTemp.info = new ItemType(-1);
        NodeType mergedListHead = mergeTemp;
        boolean temp1IsNOTNull, temp2IsNOTNull;
        
        // while at least one of temp1/2 is NOT null, there are still items to merge
        while ((temp1IsNOTNull = (temp1 != null))
               | (temp2IsNOTNull = (temp2 != null))) { // bitwise OR to avoid short-circuiting
            // iterate on merged list, to prepare for next item
            mergeTemp = mergeTemp.next;
            // if at least one of temp1/2 IS null, we need to switch to single-track merging
            // thus, we must break out of this loop
            if (!(temp1IsNOTNull && temp2IsNOTNull)) { break; }
            // compare each item.
            if (temp1.compareTo(temp2) == -1) {
                // then put in first item
                mergeTemp = temp1;
                temp1 = temp1.next;
                // intentional fall-through
            } else if (temp1.compareTo(temp2) == 1) {
                // else put in second item
                mergeTemp = temp2;
                temp2 = temp2.next;
                // intentional fall-through
            } else {
                // if item == other item, then put only one item in
                mergeTemp = temp1;
                // then, iterate on BOTH lists
                temp1 = temp1.next;
                temp2 = temp2.next;
                // intentional fall-through
            } // if-elif-else
        } // while
        
        // it is likely that we run out of traversing one list faster than the other.
        // for the rest of the items, simply append them to the merged list
        if (!temp1IsNOTNull && temp2IsNOTNull) { // if temp1 is null and temp2 is not, ...
            // then switch to temp2 to continue merging
            temp1 = temp2;
        } // if
        // only need to attach/append temp1 to the merged list because temp1 holds the chain
        // before we entered this loop, we had already prepared the next spot for an item
        mergeTemp = temp1;
        
        // we are now done merging.
        // shift the merged list head off of the -1 header node
        mergedListHead = mergedListHead.next;
        // do...something? do we return the list (void tho) or just assign one of them to the merged list?
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
