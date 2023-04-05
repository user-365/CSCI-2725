import java.util.ArrayList;

/**
 * BinarySearchTree
 */
public class BinarySearchTree {

    private NodeType root;

    /**
     * Constructor.
     *
     * <ul>
     * <li>Pre-Condition: None.</li>
     * <li>Post-Condition: Tree is initialized.</li>
     * </ul>
     */
    public BinarySearchTree() {
        super();
    } // BinarySearchTree()

    /**
     * Inserts a specified info. <strong>No duplicates are allowed.</strong>
     *
     * <ul>
     * <li>Pre-Condition: Tree and {@code inserenda} initialized.</li>
     * <li>Post-Condition: Insert a node with the value of {@code inserenda} into
     * the tree.</li>
     * </ul>
     *
     * @param inserenda info to be inserted
     */
    public void insert(ItemType inserenda) {

        // Special Case: empty BST
        if (is_empty()) {
            this.root = new NodeType();
            // `- set instance's root to inserenda-node
            this.root.info = inserenda;
        } // if
        ArrayList<NodeType> survey_result = survey(inserenda);
        // "Base Case"
        // -- Subroot is null
        // `- Assign subroot to inserenda-node
        if (get_last(survey_result) == null) {
            NodeType parent = get_second_to_last(survey_result);
            NodeType inserted = new NodeType();
            inserted.info = inserenda;
            switch (parent.info.compareTo(inserenda)) {
                case -1:
                    parent.right = inserted;
                    break;
                case 1:
                    parent.left = inserted;
                    break;
            } // switch-case
        } else {
            // `- If inserenda is equal to subroot, then notify duplicate-ness
            System.out.println("The item already exists in the tree.");
        } // if-else

    } // insert(ItemType)

    /**
     * Deletes a specified info.
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
     * @param delenda info to be deleted from the tree
     */
    public void delete(ItemType delenda) {

        // Special Case: empty BST
        if (is_empty()) {
            System.out.println("The number is not present in the tree");
            return; 
        } // if
        ArrayList<NodeType> survey_result = survey(delenda);
        // Special Case
        // -- delenda-node not present in BST
        // `- notify non-presence (exit of course)
        if (get_last(survey_result) == null) {
            System.out.println("The number is not present in the tree");
        } else {
            // `- If delenda is equal to subroot, then call actual delete method
            NodeType deleted = get_last(survey_result);
            if (survey_result.size() <= 1) {
                // if `deleted` has no parent
                delete_biparous_node(deleted);
                return;
            } // if
            NodeType parent = get_second_to_last(survey_result);
            delete(parent, deleted);
        } // if-else

    } // delete(ItemType)

    /** Wrapped method in {@link #delete(ItemType) delete()}. */
    private void delete(NodeType parent, NodeType deleted) {

        // What kind of child is deleted?
        String side = "";
        if (parent.right == deleted) {
            side = "right";
        } else if (parent.left == deleted) {
            side = "left";
        } // if-elif

        // Actual deletion
        if (deleted.left == null
                && deleted.right == null) {
            // `- If delenda-node is leaf node, then remove parent's link to delenda-node
            switch (side) {
                case "right":
                    parent.right = null;
                    break;
                case "left":
                    parent.left = null;
                    break;
            } // switch-case
        } else if (is_uniparous(deleted)) {
            // `- If delenda-node is single-child/uniparous node,
            // then link child (across delenda-node) to parent
            NodeType grandchild = get_only_child(deleted);
            switch (side) {
                case "right":
                    parent.right = grandchild;
                    break;
                case "left":
                    parent.left = grandchild;
                    break;
            } // switch-case
        } else {
            delete_biparous_node(deleted);
        } // if-elif-else

    } // delete(NodeType)

    private void delete_biparous_node(NodeType deleted) {

        // `- If delenda-node is double-child/biparous node,
        ArrayList<NodeType> branch_to_predecessor;
        // `- find predecessor
        if ((branch_to_predecessor = get_branch_to_predecessor(deleted)).size() <= 1) {

            // `- if predecessor nonexistent
            ArrayList<NodeType> branch_to_successor = get_branch_to_successor(deleted);
            // `- replace delenda-node with successor (duplicating the latter)
            NodeType successor = get_last(branch_to_successor);
            NodeType parent_of_successor = get_second_to_last(branch_to_successor);
            deleted.info = successor.info;
            // `- actual-delete the original successor
            delete(parent_of_successor, successor);
            
        } else {

            // `- else, if predecessor existent
            // `- replace delenda-node with predecessor (duplicating the latter)
            NodeType predecessor = get_last(branch_to_predecessor);
            NodeType parent_of_predecessor = get_second_to_last(branch_to_predecessor);
            deleted.info = predecessor.info;
            // `- actual-delete the original successor
            delete(parent_of_predecessor, predecessor);

        } // if-else

    } // delete_biparous_node(NodeType)

    /**
     * Retrieves a specified info via object reference, and returns a success value.
     *
     * <ul>
     * <li>Pre-Condition: Tree, {@code recuperanda}, and found are all TK found?
     * initialized.</li>
     * <li>Post-Condition: {@code recuperanda} should refer to a key of a
     * {@code Node} <em>n</em>
     * in the tree where the value of <em>n</em>{@code .key} is equal to the value
     * of
     * {@code recuperanda}.</li>
     * </ul>
     *
     * @param recuperanda item to be retrieved from the tree
     * @return true if <em>n</em> exists; otherwise returns false.
     */
    public boolean retrieve(ItemType recuperanda) {

        // Special Case: empty BST
        if (is_empty()) {
            // TK notify
            return false;
        } // if
        ArrayList<NodeType> survey_result = survey(recuperanda);
        return get_last(survey_result) != null
            && get_last(survey_result).info.compareTo(recuperanda) == 0;

    } // retrieve(ItemType)

    /**
     * <ul>
     * <li>Pre-Condition: The tree has been initialized.</li>
     * <li>Post-Condition: Print out the tree in "in-order" order.</li>
     */
    public void inOrder(NodeType subroot) {

        // Base case: when current subroot is null
        if (subroot == null) { return; } // if
        // Recursive case:
        // inOrder on left child
        if (subroot.left != null) { inOrder(subroot.left); } // if
        // Print parent
        System.out.print(subroot.info.getValue() + " ");
        // inOrder on right child
        if (subroot.right != null) { inOrder(subroot.right); } // if

    } // inOrder()

    /**
     * <strong>Not</strong> a getter method.
     * <p>
     * Side Effect: This method should print the nodes that have one child.
     */
    public void getSingleParent() {

        // Special Case: empty BST
        if (is_empty()) {
            // TK notify
            return;
        } // if
        // Wrapped method: getSingleParent(NodeType subroot)
        get_uniparous_parents(this.root);

    } // getSingleParent()

    /**
     * Wrapped (recursive) method inside {@link #getSingleParent() getSingleParent()}.
     * @param subroot the node under which we are to print the nodes that have one child
     */
    private void get_uniparous_parents(NodeType subroot) {
        
        // Base case: if is null, then return
        if (subroot == null) { return; }
        // Recursive case: Pre-order traversing:
        if (subroot.left != null && subroot.right != null) {
            // `- does it have both children?
            // `- recurse with both children
            get_uniparous_parents(subroot.left);
            get_uniparous_parents(subroot.right);
        } else if (subroot.left != null && subroot.right == null) {
            // `- does it have only the left child?
            // `- print and recurse with left child
            System.out.print(subroot.info.getValue() + " ");
            get_uniparous_parents(subroot.left);
        } else if (subroot.left == null && subroot.right != null) {
            // `- else, does it have only the right child?
            // `- print and recurse with right child
            System.out.print(subroot.info.getValue() + " ");
            get_uniparous_parents(subroot.right);
        } // if-else
          // `- otherwise, it has zero children and implicitly return

    } // get_uniparous_parents(NodeType)

    /**
     * <strong>Not</strong> a getter method.
     * <p>
     * Side Effect: This method should count the number of leaf nodes in the BST
     * (Nodes with no children), and then output the count.
     */
    public void getNumLeafNodes() {

        // Special Case: empty BST
        if (is_empty()) {
            // TK notify
            return;
        } // if
        // Wrapped method: int getNumLeafNodes(NodeType subroot)
        // Wrapper method: print
        System.out.println(get_num_leaf_nodes(this.root));

    } // getNumLeafNodes()

    /**
     * Wrapped (recursive) method inside {@link #getNumLeafNodes()
     * getNumLeafNodes()}.
     * 
     * @param subroot the node under which we are to print the leaf nodes
     * @return the number of leaf nodes under subroot
     */
    private int get_num_leaf_nodes(NodeType subroot) {

        // Base case: if is null, then return 0
        if (subroot == null) { return 0; }
        // Recursive case: Post-order traversing:
        int sum = get_num_leaf_nodes(subroot.left) + get_num_leaf_nodes(subroot.right);
        // `- does it have either child?
        // `- if so, return sum of number of leaf nodes (below current subroot)
        // `- if not, return 1
        return sum > 0 ? sum : 1;

    } // get_num_leaf_nodes(NodeType)

    /**
     * <strong>Not</strong> a getter method.
     * <p>
     * Side Effect: This method should take in a node as input,
     * and prints the cousins of the given node.
     * <p>
     * Note: for every node in the BST, it can have a maximum of two (first)
     * cousins.
     */
    public void getCousins(NodeType node) {

        // Special Case: empty BST
        if (is_empty()) {
            // TK notify
            return;
        } // if
        // survey node
        ArrayList<NodeType> branch = survey(node.info);
        NodeType confirmed_target = get_last(branch);
        if (confirmed_target != null) {
            if (branch.size() <= 1) {
                // if `confirmed_target` has no parent
                return;
            } // if
            // get parent of node (from survey)
            NodeType parent = get_second_to_last(branch);
            if (branch.size() <= 2) {
                // if `confirmed_target` has no grandparent
                return;
            } // if
            // get to grandparent of node (from survey)
            NodeType grandparent = branch.get(branch.size() - 3);
            // avoid parent of node, going to pibling
            if (grandparent.left == null
                || grandparent.right == null) {
                // if pibling does not exist, return immediately
                return; // TK notify
            } else {
                // if pibling does exist, print its children
                NodeType pibling = grandparent.left.info.compareTo(parent.info) == 0 ? grandparent.right : grandparent.left;
                if (pibling.left != null) {
                    System.out.print(pibling.left.info.getValue() + " ");
                } // ifnotnull
                if (pibling.right != null) {
                    System.out.print(pibling.right.info.getValue() + " ");
                } // ifnotnull
            } // if-else
        } // ifnotnull

    } // getCousins(NodeType)

    /**
     * Helper method: Gets predecessor (and its branch from the root) of input node.
     *
     * @param node the node whose predecessor we want
     * @return the node's predecessor
     */
    private ArrayList<NodeType> get_branch_to_predecessor(NodeType node) {

        ArrayList<NodeType> branch = new ArrayList<NodeType>();
        branch.add(node);
        // Go left
        if (node.left != null) {
            branch.add(node.left);
        } else {
            return branch;
        } // if-else
        // Keep going right
        NodeType addendum;
        while ((addendum = get_last(branch).right) != null) {
            branch.add(addendum);
        } // while
        // Return the node branch
        return branch;

    } // get_branch_to_predecessor(NodeType)

    /**
     * Helper method: Gets successor (and its branch from the root) of input node.
     *
     * @param node the node whose successor we want
     * @return the node's successor
     */
    private ArrayList<NodeType> get_branch_to_successor(NodeType node) {

        ArrayList<NodeType> branch = new ArrayList<NodeType>();
        branch.add(node);
        // Go right
        if (node.right != null) {
            branch.add(node.right);
        } else {
            return branch;
        } // if-else
        // Keep going left
        NodeType addendum;
        while ((addendum = get_last(branch).left) != null) {
            branch.add(addendum);
        } // while
          // Return the node branch
        return branch;

    } // get_branch_to_successor(NodeType)

    /**
     * Self-explanatory.
     * 
     * @return true if the BST is empty (i.e. its root is null); false otherwise
     */
    private boolean is_empty() {
        return this.root == null;
    } // isEmpty()

    /**
     * Wrapped (recursive) method inside {@link #survey(ItemType) survey()}.
     * <p>
     * Side Effect: modifies branch parameter.
     */
    private void survey(ItemType quaerenda, NodeType subroot, ArrayList<NodeType> branch) {

        // Push to branch
        branch.add(subroot);
        // Base Case
        if (subroot == null) {
            return;
        } // if
        // Recursive Case
        switch (subroot.info.compareTo(quaerenda)) {
            case -1:
                survey(quaerenda, subroot.right, branch);
                break;
            case 0: // Other Base Case
                return;
            case 1:
                survey(quaerenda, subroot.left, branch);
                break;
        } // switch-case

    } // search(InfoType)

    /**
     * Returns the branch/path to a target node
     * (or, if the target node isn't in the BST, the branch to where it <em>would</em> be).
     * Wrapper method that is the cornerstone for many of this class's methods,
     * viz., {@link #insert(ItemType) insert()}, {@link #delete(ItemType) delete()},
     * {@link #retrieve(ItemType) retrieve()}, and {@link #getCousins(NodeType) getCousins()}.
     * 
     * @param quaerenda the info/item of the node that is the target of the survey
     * @return the list of nodes that were traversed
     */
    private ArrayList<NodeType> survey(ItemType quaerenda) {

        // Special Case: empty BST
        if (is_empty()) {
            // TK notify
            return null;
        } // if
        ArrayList<NodeType> branch = new ArrayList<NodeType>();
        survey(quaerenda, this.root, branch);
        return branch;

    } // survey(ItemType)

    /** Returns whether the given (parent) has only one child or not. */
    private boolean is_uniparous(NodeType parent) {
        return (parent.left == null && parent.right != null)
                || (parent.left != null && parent.right == null);
    } // is_single_child(NodeType)

    /** Assumes parent has only one child. */
    private NodeType get_only_child(NodeType parent) {
        return parent.right == null ? parent.left : parent.right;
    } // get_only_child(NodeType)

    /**
     * Shorthand method to get the last node in the branch list,
     * which represents the target node (of a survey).
     * @param branch the list of nodes that were traversed
     * @return the target node of the survey
     */
    private NodeType get_last(ArrayList<NodeType> branch) {
        return branch.get(branch.size() - 1);
    } // get_last(ArrayList<NodeType>)

    /**
     * Shorthand method to get the second-to-last node in the branch list,
     * which represents the parent of the target node (of a survey).
     * 
     * @param branch the list of nodes that were traversed
     * @return the parent of the target node of the survey
     */
    private NodeType get_second_to_last(ArrayList<NodeType> branch) {
        return branch.get(branch.size() - 2);
    } // get_last(ArrayList<NodeType>)

    /**
     * Mutates root instance field.
     * @param the instance's root-field-to-be
     */
    public void set_root(NodeType new_root) {
        this.root = new_root;
    } // set_root(NodeType)
    
    /**
     * Accesses root instance field
     * @return the instance's root field
     */
    public NodeType get_root() {
        return this.root;
    } // get_root()

} // BinarySearchTree

// TK TODO
// TK - write down side effects in javadocs
// TK - finish javadocs