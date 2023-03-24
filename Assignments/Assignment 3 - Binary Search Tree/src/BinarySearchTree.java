/**
 * BinarySearchTree
 */
public class BinarySearchTree {

    private NodeType root;
    
    /**
     * Constructor.
     * 
     * <ul>
     * <li>
     * Pre-Condition: None.
     * </li>
     * <li>
     * Post-Condition: Tree is initialized.
     * </li>
     * </ul>
     */
    public BinarySearchTree() {
        super();
    } // BinarySearchTree()

    /**
     * Inserts a specified item. <strong>No duplicates are allowed.</strong>
     * 
     * <ul>
     * <li>Pre-Condition: Tree and {@code inserenda} initialized.</li>
     * <li>Post-Condition: Insert a node with the value of {@code inserenda} into
     * the tree.</li>
     * </ul>
     * 
     * @param inserenda item to be inserted
     */
    public void insert(ItemType inserenda) {
        
    } // insert(ItemType)

    /**
     * Deletes a specified item.
     * 
     * <ul>
     * <li>Pre-Condition: Tree and parameter key initialized.</li>
     * <li>Post-Condition: Remove a node with a key value equal to
     * {@code delenda}'s value, otherwise leave the tree unchanged
     * (if {@code delenda} is not present).
     * In situations, where {@code delenda} has two children,
     * replace {@code delenda} with its immediate predecessor or successor.</li>
     * </ul>
     * 
     * @param delenda item to be deleted from the tree
     */
    public void delete(ItemType delenda) {
        
    } // delete(ItemType)

    /**
     * Retrieves a specified item via object reference, and returns a success value.
     * 
     * <ul>
     * <li>Pre-Condition: Tree, {@code recuperanda}, and found are all TK found?
     * initialized.</li>
     * <li>Post-Condition: {@code recuperanda} should refer to a key of a {@code Node} <em>n</em>
     * in the tree where the value of <em>n</em>{@code .key} is equal to the value of
     * {@code recuperanda}.</li>
     * </ul>
     * 
     * @param recuperanda item to be retrieved from the tree
     * @return true if <em>n</em> exists; otherwise returns false.
     */
    public boolean retrieve(ItemType recuperanda) {
        
    } // retrieve(ItemType)

    /**
     * <ul>
     * <li>Pre-Condition: The tree has been initialized.</li>
     * <li>Post-Condition: Print out the tree in "in-order" order.</li>
     */
    public void inOrder() {
        
    } // inOrder()

    /**
     * <strong>Not</strong> a getter method.
     * <p> Side Effect: This function should print the nodes that have one child.
     */
    public void getSingleParent() {
        
    } // getSingleParent()

    /**
     * <strong>Not</strong> a getter method.
     * <p>
     * Side Effect: This function should count the number of leaf nodes in the BST
     * (Nodes
     * with no children), and then output the count.
     */
    public void getNumLeafNodes() {
        
    } // getNumLeafNodes()

    /**
     * <strong>Not</strong> a getter method.
     * <p>
     * Side Effect: This function should take in a node as input,
     * and prints the cousins of the given node.
     */
    public void getCousins(NodeType node) {
        
    } // getCousins(NodeType)

    /**
     * Getter method for the instance's {@code root}.
     * 
     * @return the instance's {@code root}
     */
    public NodeType getRoot() {
        return this.root;
    } // getRoot()
    
} // BinarySearchTree