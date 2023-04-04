import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Creates a main application that matches example output exactly.
 */
public class BinarySearchTreeDriver {
    
    public static void main(String[] args) {
        System.out.println(
                "Commands:\n(i) - Insert Item\n(d) - Delete Item\n(p) - Print Tree\n(r) - Retrieve Item\n(l) - Count Leaf Nodes\n(s) - Find Single Parents\n(c) - Find Cousins\n(q) - Quit program");
        BinarySearchTree bst = new BinarySearchTree();
        Scanner s = new Scanner(System.in);

        try {
            Path file = Paths.get(args[0]);
            Stream<String> BST_precursor = Files.lines(file)
                    .map(str -> str.split(" ")) // space-separated integers
                    .flatMap(Arrays::stream);
            make_BST_from_Stream(BST_precursor, bst);
            // TK comment out below
            //test(file, bst);
            //s.close();
            //System.exit(0);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        } // try-catch


        for (;;) {
            System.out.print("\nEnter a command: ");
            no_prefix: // from default case
            switch (s.next()) {

                case "i":
                    user_insert(bst, s);
                    break;

                case "d":
                    user_delete(bst, s);
                    break;

                case "p":
                    print_BST_in_order(bst);
                    break;

                case "r":
                    user_retrieve(bst, s);
                    break;

                case "l":
                    System.out.print("The number of leaf nodes are ");
                    bst.getNumLeafNodes();
                    break;

                case "s":
                    System.out.print("Single Parents: ");    
                    bst.getSingleParent();
                    System.out.println();
                    break;

                case "c":
                    NodeType subroot = new NodeType();
                    System.out.print("Enter a number: ");
                    int item = s.nextInt();
                    subroot.info = new ItemType(item);
                    System.out.print(item + " cousins: ");
                    bst.getCousins(subroot);
                    System.out.println();
                    break;

                case "q": // loop's exit condition
                    System.out.println("Exiting the program...");
                    s.close();
                    System.exit(0);
                    break;

                default:
                    System.out.print("Invalid command, try again: ");
                    break no_prefix; // goto label

            } // switch
        } // for

    } // main

    /**
     * 
     * @param bst
     * @param s
     */
    private static void user_insert(BinarySearchTree bst, Scanner s) {
        print_BST_in_order(bst);
        ItemType item;
        System.out.print("Enter a number to insert: ");
        item = new ItemType(s.nextInt());
        auto_insert(bst, item);
    } // user_insert(BinarySearchTree, Scanner)

    private static void auto_insert(BinarySearchTree bst, ItemType item) {
        bst.insert(item);
        print_BST_in_order(bst);
    }

    /**
     * 
     * @param bst
     * @param s
     */
    private static void user_delete(BinarySearchTree bst, Scanner s) {
        print_BST_in_order(bst);
        ItemType item;
        System.out.print("Enter a number to delete: ");
        item = new ItemType(s.nextInt());
        auto_delete(bst, item);
    } // user_delete(BinarySearchTree, Scanner)

    private static void auto_delete(BinarySearchTree bst, ItemType item) {
        bst.delete(item);
        print_BST_in_order(bst);
    }

    /**
     * TK write this
     * 
     * @param label
     * @param bst
     */
    public static void print_BST_in_order(BinarySearchTree bst) {

        System.out.print("In-order: ");
        bst.inOrder(bst.get_root());
        System.out.println();

    } // print_BST_in_order(String, BinarySearchTree)

    /**
     * 
     * @param bst
     * @param s
     */
    private static void user_retrieve(BinarySearchTree bst, Scanner s) {
        print_BST_in_order(bst);
        ItemType item;
        System.out.print("Enter a number to search: ");
        item = new ItemType(s.nextInt());
        auto_retrieve(bst, item);
    } // user_retrieve(BinarySearchTree, Scanner)

    private static void auto_retrieve(BinarySearchTree bst, ItemType item) {
        boolean found = bst.retrieve(item);
        if (found) {
            System.out.println("Item is present in the tree");
        } else {
            System.out.println("Item is not present in the tree");
        } // if-else
    }

    /**
     * TK write this
     * Works.
     * 
     * @param stream
     * @param bst
     */
    public static void make_BST_from_Stream(Stream<String> stream, BinarySearchTree bst) {

        // Avoids "IllegalStateException: stream has already been operated upon or
        // closed"
        IntStream template = stream.mapToInt(Integer::parseInt) // String to int
                .distinct(); // no duplicates!
        Iterable<Integer> iterable = template::iterator;
        int counter = 0;
        for (Integer i : iterable) {
            if (counter == 0) { // run only once
                NodeType root = new NodeType();
                root.info = new ItemType(i);
                bst.set_root(root);
                counter++;
                continue;
            } // if
            ItemType new_info = new ItemType(i);
            bst.insert(new_info);
        } // for-each

    } // make_BST_from_Stream(Stream<String>, BinarySearchTree)

    private static void test(Path file, BinarySearchTree bst) {
        
        System.out.println("// 1. Print Tree(p) will do the in-order traversal and print the nodes");
        print_BST_in_order(bst);
        System.out.println();
        System.out.println("// 2. Insert(i)\n// 2a. Insert an item that is not present in the tree");
        auto_insert(bst, new ItemType(20));
        System.out.println();
        System.out.println("// 2b. Insert an item that is already present in the tree");
        auto_insert(bst, new ItemType(20));
        System.out.println();
        System.out.println("// 3. Search/Retrieve(r)\n// 3a. Search an item that is already present in the tree");
        auto_retrieve(bst, new ItemType(20));
        System.out.println();
        System.out.println("// 3b. Search an item that is not present in the tree");
        auto_retrieve(bst, new ItemType(67));
        bst = reset_BST(file);
        System.out.println();
        
        System.out.println("// 4. delete\n// 4a. Delete a leaf node");
        print_BST_in_order(bst);
        auto_delete(bst, new ItemType(4));
        auto_delete(bst, new ItemType(24));
        bst = reset_BST(file);
        System.out.println();

        System.out.println("// 4b. Delete a node with one child");
        print_BST_in_order(bst);
        auto_delete(bst, new ItemType(50));
        auto_delete(bst, new ItemType(18));
        auto_delete(bst, new ItemType(22));
        bst = reset_BST(file);
        System.out.println();

        System.out.println("// 4c. Delete a node with two children");
        print_BST_in_order(bst);
        auto_delete(bst, new ItemType(22));
        auto_delete(bst, new ItemType(15));
        auto_delete(bst, new ItemType(25));
        bst = reset_BST(file);
        System.out.println();

        System.out.println("// 4d. Delete a node that is not present in the tree");
        print_BST_in_order(bst);
        auto_delete(bst, new ItemType(33));
        bst = reset_BST(file);
        System.out.println();

        System.out.println("// 5. Count leaf nodes (it is tested by deleting some of the nodes in between)");
        print_BST_in_order(bst);
        System.out.print("The number of leaf nodes are ");
        bst.getNumLeafNodes();
        auto_delete(bst, new ItemType(35));
        System.out.print("The number of leaf nodes are ");
        bst.getNumLeafNodes();
        auto_delete(bst, new ItemType(50));
        System.out.print("The number of leaf nodes are ");
        bst.getNumLeafNodes();
        auto_delete(bst, new ItemType(18));
        System.out.print("The number of leaf nodes are ");
        bst.getNumLeafNodes();
        bst = reset_BST(file);
        System.out.println();

        System.out.println("// 6. Find single parents (it is tested by deleting some of the nodes in between)");
        print_BST_in_order(bst);
        System.out.print("Single Parents: ");
        bst.getSingleParent();
        System.out.println();
        auto_delete(bst, new ItemType(18));
        System.out.print("Single Parents: ");
        bst.getSingleParent();
        System.out.println();
        auto_delete(bst, new ItemType(4));
        System.out.print("Single Parents: ");
        bst.getSingleParent();
        System.out.println();
        auto_delete(bst, new ItemType(12));
        System.out.print("Single Parents: ");
        bst.getSingleParent();
        System.out.println();
        auto_delete(bst, new ItemType(24));
        System.out.print("Single Parents: ");
        bst.getSingleParent();
        System.out.println();
        auto_delete(bst, new ItemType(35));
        System.out.print("Single Parents: ");
        bst.getSingleParent();
        System.out.println();
        bst = reset_BST(file);
        System.out.println();

        System.out.println("// 7. Find cousins");
        NodeType subroot = new NodeType();
        print_BST_in_order(bst);
        subroot.info = new ItemType(4);
        System.out.print(subroot.info.getValue() + " cousins: ");
        bst.getCousins(subroot);
        System.out.println();
        subroot.info = new ItemType(10);
        System.out.print(subroot.info.getValue() + " cousins: ");
        bst.getCousins(subroot);
        System.out.println();
        subroot.info = new ItemType(35);
        System.out.print(subroot.info.getValue() + " cousins: ");
        bst.getCousins(subroot);
        System.out.println();
        subroot.info = new ItemType(15);
        System.out.print(subroot.info.getValue() + " cousins: ");
        bst.getCousins(subroot);
        System.out.println();
        subroot.info = new ItemType(25);
        System.out.print(subroot.info.getValue() + " cousins: ");
        bst.getCousins(subroot);
        System.out.println();
        subroot.info = new ItemType(22);
        System.out.print(subroot.info.getValue() + " cousins: ");
        bst.getCousins(subroot);
        System.out.println();

    } // test(Path, BinarySearchTree)

    private static BinarySearchTree reset_BST(Path file) {
        BinarySearchTree bst;
        bst = new BinarySearchTree();
        Stream<String> BST_precursor;
        try {
            BST_precursor = Files.lines(file)
                    .map(str -> str.split(" ")) // space-separated integers
                    .flatMap(Arrays::stream);
            make_BST_from_Stream(BST_precursor, bst);
        } catch (IOException e) {
            e.printStackTrace();
        } // try-catch
        return bst;
    }

} // BinarySearchTreeDriver
