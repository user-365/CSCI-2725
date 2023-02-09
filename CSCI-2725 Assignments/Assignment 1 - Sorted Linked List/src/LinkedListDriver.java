import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

// TK handle edge cases like empty list etc.
// TK replace with prefix printing
// TK remember to create Readme.txt on Odin

public class LinkedListDriver {
    
    public static void main(String[] args) {
        System.out.println("Commands:\n(i) - Insert value\n(d) - Delete value\n(s) - Search value\n(a) - Delete alternate nodes\n(m) - Merge lists\n(p) - Print list\n(l) - Print length\n(q) - Quit program");
        Path file = Paths.get(args[0]);
        try {
            SortedLinkedList list = new SortedLinkedList();
            Files.lines(file)
                .map(str -> str.split(" ")) // space-separated integers
                .flatMap(Arrays::stream)
                .mapToInt(Integer::parseInt) // String to int
                .distinct() // no duplicates!
                .sorted() // ascending order
                .forEach(i -> list.insertItem(new ItemType((byte) i))); // put in list
            Scanner s = new Scanner(System.in);
            ItemType item;
            for (;;) {
                System.out.println("Enter a command: ");
                no_prefix: // from default case
                switch (s.nextLine()) {
                    case "i":
                        System.out.print("Enter a number to insert: ");
                        item = new ItemType((byte) s.nextInt());
                        System.out.print("Original list: ");
                        list.printList();
                        list.insertItem(item);
                        System.out.print("New list: ");
                        list.printList();
                        break;
                    case "d":
                        System.out.print("Enter a number to delete: ");
                        item = new ItemType((byte) s.nextInt());
                        System.out.print("Original list: ");
                        list.printList();
                        list.deleteItem(item);
                        System.out.print("New list: ");
                        list.printList();
                        break;
                    case "s":
                        System.out.print("Enter a number to search: ");
                        item = new ItemType((byte) s.nextInt());
                        System.out.print("Original list: ");
                        list.printList();
                        int index = list.searchItem(item);
                        if (index == -1) {
                            System.out.println("Item is not present in the list");
                        } else {
                            System.out.println("The item is present at index " + index);
                        } // if-else
                        break;
                    case "a":
                        System.out.print("Original list: ");
                        list.printList();
                        SortedLinkedList.deleteAlternateNodes(list);
                        System.out.print("New list: ");
                        list.printList();
                        break;
                    case "m":
                        System.out.print("Enter the length of the new list: ");
                        int newLength = s.nextInt();
                        System.out.print("Enter the numbers: ");
                        System.out.print("The list 1: ");
                        list.printList();
                        SortedLinkedList newList;
                        // TK create newList
                        System.out.print("The list 2: ");
                        newList.printList();
                        SortedLinkedList.mergeList(list, newList);
                        System.out.print("Merged list: ");
                        list.printList();
                        break;
                    case "p":
                        System.out.print("The list is: ");
                        list.printList();
                        break;
                    case "l":
                        System.out.print("The length of the list is " + list.getLength());
                        break;
                    case "q": 
                        System.out.println("Exiting the program...");
                        s.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid command try again: ");
                        break no_prefix;
                } // switch
            } // for
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        } // try-catch
    } // main

    /**
     * TK write this
     * @param prefix
     * @param list
     */
    public static void printListWithPrefix(String prefix, SortedLinkedList list) {
        switch (prefix) {
            case "O":
                System.out.print("Original list: ");
                list.printList();
                break;
            case "N":
                System.out.print("New list: ");
                list.printList();
                break;
            default:
                System.out.print(prefix + ": ");
                list.printList();
                break;
        } // switch
    } // printListWithPrefix(String, SortedLinkedList)

} // LinkedListDriver