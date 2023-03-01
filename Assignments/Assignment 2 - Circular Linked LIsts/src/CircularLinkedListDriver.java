import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CircularLinkedListDriver {
    
    public static void main(String[] args) {

        System.out.println(
                "Commands:\n(i) - Insert value\n(d) - Delete value\n(p) - Print list\n(s) - Search list\n(l) - Length\n(r) - Reverse list\n(q) - Quit program");
        CircularLinkedList list = new CircularLinkedList();
        Scanner s = new Scanner(System.in);

        try {

            Path file = Paths.get(args[0]);
            Stream<String> listPrecursor = Files.lines(file)
                    .map(str -> str.split(" ")) // space-separated integers
                    .flatMap(Arrays::stream);
            makeCLLFromStream(listPrecursor, list);

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        } // try-catch

        for (;;) {
            System.out.print("\nEnter a command: ");
            no_prefix: // from default case
            switch (s.next()) {

                case "i":
                    insert(list, s);
                    break;

                case "d":
                    delete(list, s);
                    break;

                case "s":
                    search(list, s);
                    break;

                case "r":
                    reverse(list, s);
                    break;

                case "p":
                    printListWithLabel("The list is", list);
                    break;

                case "l":
                    System.out.println("The length of the list is " + list.length());
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
    private static void search(CircularLinkedList list, Scanner s) {
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
    } // search(CircularLinkedList, Scanner)

    /**
     * 
     * @param list
     * @param s
     */
    private static void delete(CircularLinkedList list, Scanner s) {
        ItemType item;
        System.out.print("Enter a number to delete: ");
        item = new ItemType(s.nextInt());
        printListWithLabel("Before delete", list);
        list.deleteItem(item);
        printListWithLabel("After delete", list);
    } // delete(CircularLinkedList, Scanner)

    /**
     * 
     * @param list
     * @param s
     */
    private static void insert(CircularLinkedList list, Scanner s) {
        ItemType item;
        System.out.print("Enter a number to insert: ");
        item = new ItemType(s.nextInt());
        printListWithLabel("Original list", list);
        list.insertItem(item);
        printListWithLabel("New list", list);
    } // insert(CircularLinkedList, Scanner)

    /**
     * 
     * @param list
     * @param s
     */
    private static void reverse(CircularLinkedList list, Scanner s) {
        printListWithLabel("Original list", list);
        CircularLinkedList.reverseList(list);
        printListWithLabel("Reversed list", list);
    } // insert(CircularLinkedList, Scanner)

    /**
     * TK write this
     * @param label
     * @param list
     */
    public static void printListWithLabel(String label, CircularLinkedList list) {

        System.out.print(label + ": ");
        list.print();

    } // printListWithPrefix(String, CircularLinkedList)

    /**
     * TK write this
     * Works.
     * @param stream
     * @param SLL
     */
    public static void makeCLLFromStream(Stream<String> stream, CircularLinkedList CLL) {
        
		// Avoids "IllegalStateException: stream has already been operated upon or closed"
        IntStream template = stream.mapToInt(Integer::parseInt) // String to int
                .distinct() // no duplicates!
                .sorted(); // ascending order
        Iterable<Integer> iterable = template::iterator;
        NodeType previous = new NodeType();
        previous.info = new ItemType(-1); // header
        //CLL.setHead(previous); // must reset head later

        NodeType least = new NodeType(); // smallest item
        int counter = 0;
        for (Integer i : iterable) {
            NodeType next = new NodeType();
            next.info = new ItemType(i);
            if (counter == 0) { // run only once
                least = next;
                CLL.setHead(least);
                counter++;
            } // if
            // Iterate once
            previous.next = next;
            previous = previous.next;
        } // for-each

        if (least.info == null) {
            System.out.println("List is empty.");
            CLL.setHead(null);
            return;
        } // if

        for (;;) {
            // Iterate `head` field
            CLL.setHead(CLL.getHead().next);
            // We are about to reach the end; loop back around
            if (CLL.getHead().next == null
            || CLL.getHead().info.compareTo(least.info) == 0) {
                CLL.getHead().next = least;
                return;
            } // if
        } // for

    } // makeCLLFromStream(Stream<String>, CircularLinkedList)


} // CircularLinkedListDriver
