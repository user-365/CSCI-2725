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

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        } // try-catch

        for (;;) {
            System.out.print("\nEnter a command: ");
            no_prefix: // from default case
            switch (s.next()) {

                case "i":
                    insert(bst, s);
                    break;

                case "d":
                    delete(bst, s);
                    break;

                case "s":
                    search(bst, s);
                    break;

                case "r":
                    reverse(bst, s);
                    break;

                case "p":
                    printListWithLabel("The list is", bst);
                    break;

                case "l":
                    System.out.println("The length of the list is " + bst.length());
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
     * @param list
     * @param s
     */
    private static void search(BinarySearchTree list, Scanner s) {
        ItemType item;
        System.out.print("Enter a number to search: ");
        item = new ItemType(s.nextInt());
        printListWithLabel("Original list", list);
        int index = list.SearchItem(item);
        if (index != -1) {
            System.out.println("The item is present at index " + index);
        } else {
            System.out.println("Item is not present in the list");
        } // if-else
    } // search(BinarySearchTree, Scanner)

    /**
     * 
     * @param list
     * @param s
     */
    private static void delete(BinarySearchTree list, Scanner s) {
        ItemType item;
        System.out.print("Enter a number to delete: ");
        item = new ItemType(s.nextInt());
        printListWithLabel("Before delete", list);
        list.deleteItem(item);
        printListWithLabel("After delete", list);
    } // delete(BinarySearchTree, Scanner)

    /**
     * 
     * @param list
     * @param s
     */
    private static void insert(BinarySearchTree list, Scanner s) {
        ItemType item;
        System.out.print("Enter a number to insert: ");
        item = new ItemType(s.nextInt());
        printListWithLabel("Original list", list);
        list.insertItem(item);
        printListWithLabel("New list", list);
    } // insert(BinarySearchTree, Scanner)

    /**
     * 
     * @param list
     * @param s
     */
    private static void reverse(BinarySearchTree list, Scanner s) {
        printListWithLabel("Original list", list);
        BinarySearchTree.reverseList(list);
        printListWithLabel("Reversed list", list);
    } // insert(BinarySearchTree, Scanner)

    /**
     * TK write this
     * 
     * @param label
     * @param bst
     */
    public static void printListWithLabel(String label, BinarySearchTree bst) {

        System.out.print(label + ": ");
        bst.inOrder(bst.get_root());

    } // printListWithPrefix(String, BinarySearchTree)

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
            } // if
            ItemType new_info = new ItemType(i);
            bst.insert(new_info);
        } // for-each

    } // make_BST_from_Stream(Stream<String>, BinarySearchTree)

} // BinarySearchTreeDriver
