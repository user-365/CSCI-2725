import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

// TK handle edge cases like empty list etc.
// TK remember to create Readme.txt on Odin

public class LinkedListDriver {
    
    public static void main(String[] args) {
        
        System.out.println("Commands:\n(i) - Insert value\n(d) - Delete value\n(s) - Search value\n(a) - Delete alternate nodes\n(m) - Merge lists\n(p) - Print list\n(l) - Print length\n(q) - Quit program");
        SortedLinkedList list = new SortedLinkedList();
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

                case "a":
                    printListWithLabel("Original list", list);
                    SortedLinkedList.deleteAlternateNodes(list);
                    printListWithLabel("New list", list);
                    break;

                case "m":
                    merge(list, s);
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
    private static void search(SortedLinkedList list, Scanner s) {
        ItemType item;
        System.out.print("Enter a number to search: ");
        item = new ItemType(s.nextInt());
        printListWithLabel("Original list", list);
        int index = list.searchItem(item);
        if (index == -1) {
            System.out.println("Item is not present in the list");
        } else {
            System.out.println("The item is present at index " + index);
        } // if-else
    }

    /**
     * 
     * @param list
     * @param s
     */
    private static void delete(SortedLinkedList list, Scanner s) {
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
    private static void insert(SortedLinkedList list, Scanner s) {
        ItemType item;
        System.out.print("Enter a number to insert: ");
        item = new ItemType(s.nextInt());
        printListWithLabel("Original list", list);
        list.insertItem(item);
        printListWithLabel("New list", list);
    }

    /**
     * 
     * @param list
     * @param s
     */
    private static void merge(SortedLinkedList list, Scanner s) {
        System.out.print("Enter the length of the new list: ");
        int newLength = s.nextInt(); // TK what
        if (newLength <= 0) { return; } // if
        // make newList
        System.out.print("Enter the numbers: ");
        SortedLinkedList newList = new SortedLinkedList();
        Stream<String> listPrecursor = Pattern.compile(" ")
                .splitAsStream(s.nextLine()) // apparently faster than Stream.of()
                .limit(newLength); // the only use i see for this field
        makeSLLFromStream(listPrecursor, newList);
        printListWithLabel("The list 1", list);
        // TK create newList based on arguments
        printListWithLabel("The list 2", newList);
        SortedLinkedList.mergeList(list, newList);
        printListWithLabel("Merged list", list);
    } // merge

    /**
     * TK write this
     * @param label
     * @param list
     */
    public static void printListWithLabel(String label, SortedLinkedList list) {

        System.out.print(label + ": ");
        list.printList();

    } // printListWithPrefix(String, SortedLinkedList)

    /**
     * TK write this
     * @param stream
     * @param SLL
     */
    public static void makeSLLFromStream(Stream<String> stream, SortedLinkedList SLL) {
        stream.mapToInt(Integer::parseInt) // String to int
                .distinct() // no duplicates!
                .sorted() // ascending order
                .forEach(i -> SLL.insertItem(new ItemType(Math.abs( i )))); // put in list
    } // makeSLLFromStream

} // LinkedListDriver
