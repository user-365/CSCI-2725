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

} // CircularLinkedListDriver
