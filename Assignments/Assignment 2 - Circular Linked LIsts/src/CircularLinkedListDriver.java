import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class CircularLinkedListDriver {
    
    public static void main(String[] args) {

        System.out.println(
                "Commands:\n(i) - Insert value\n(d) - Delete value\n(s) - Search value\n(a) - Delete alternate nodes\n(m) - Merge lists\n(p) - Print list\n(l) - Print length\n(q) - Quit program");
        CircularLinkedList list = new CircularLinkedList();
        Scanner s = new Scanner(System.in);

        try {

            Path file = Paths.get(args[0]);
            Stream<String> listPrecursor = Files.lines(file)
                    .map(str -> str.split(" ")) // space-separated integers
                    .flatMap(Arrays::stream);
            makeSLLFromStream(listPrecursor, list);

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        } // try-catch

        for (;;) {
            System.out.println("Enter a command: ");
            no_prefix: // from default case
            switch (s.nextLine()) {

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
                    System.out.print("The length of the list is " + list.getLength());
                    break;

                case "q": // loop's exit condition
                    System.out.println("Exiting the program...");
                    s.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid command, try again: ");
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
        list.searchItem(item);
    }

    /**
     * 
     * @param list
     * @param s
     */
    private static void delete(CircularLinkedList list, Scanner s) {
        ItemType item;
        System.out.print("Enter a number to delete: ");
        item = new ItemType(s.nextInt());
        printListWithLabel("Original list", list);
        list.deleteItem(item);
        printListWithLabel("New list", list);
    }

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
    }

    /**
     * TK write this
     * @param label
     * @param list
     */
    public static void printListWithLabel(String label, CircularLinkedList list) {

        System.out.print(label + ": ");
        list.printList();

    } // printListWithPrefix(String, CircularLinkedList)

    /**
     * TK write this
     * Works.
     * @param stream
     * @param SLL
     */
    public static void makeCLLFromStream(Stream<String> stream, CircularLinkedList CLL) {
        
		IntStream template = stream.mapToInt(Integer::parseInt) // String to int
                .distinct() // no duplicates!
                .sorted(); // ascending order
        Iterable<Integer> iterable = template::iterator;
        NodeType previous = new NodeType();
        previous.info = new ItemType(-1); // header
        CLL.setHead(previous);
        for (Integer i : iterable) {
            NodeType next = new NodeType();
            next.info = new ItemType(Math.abs( i ));
            previous.next = next;
            previous = next;
        } // for-each
        CLL.setHead(CLL.getHead().next);

    } // makeSLLFromStream


} // CircularLinkedListDriver
