import java.nio.charset.Charset;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class SortingDriver {

    private static int[] data;

    public static void print_array(int[] data) {
        for (int i : data) {
            System.out.print(i + " ");
        } // for
        System.out.println();
    } // print_array(int[])

    static void auto_run() {

        Charset charset = Charset.forName("US-ASCII");
        StringBuilder sb = new StringBuilder("Algorithm,Size,Comparisons\n");
        String[] algos = { "Selection", "Merge", "Heap", "QuickSort-fp", "QuickSort-rp" };
        int[] sizes = { 100, 200, 300, 500, 600, 800, 1000, 2000, 3000, 5000, 6000, 8000, 10000, 15000, 17500, 20000,
                21000, 24000, 25000, 26000, 28000, 30000 };
        Sorting sorter = new Sorting(new int[0]);
        System.out.println("Auto-running");
        for (String algo : algos) {
            for (int input_size : sizes) {
                long average_comparisons = 0;
                for (int i = 0; i < 5; i++) {
                    data = generate_input(input_size);
                    sorter.set_data(data);
                    switch (algo) {
                        case "Selection":
                            average_comparisons += sorter.selection_sort();
                            break;
                        case "Merge":
                            average_comparisons += sorter.merge_sort(0, data.length - 1);
                            break;
                        case "Heap":
                            average_comparisons += sorter.heap_sort();
                            break;
                        case "QuickSort-fp":
                            average_comparisons += sorter.quick_sort_fp(0, data.length - 1);
                            break;
                        case "QuickSort-rp":
                            average_comparisons += sorter.quick_sort_rp(0, data.length - 1);
                            break;
                    } // switch-case
                } // for (5)
                average_comparisons /= 5;
                sb.append(algo + "," + input_size + "," + average_comparisons + "\n");
            } // for (sizes)
        } // for (algos)

        String csv_string = sb.toString();
        // Write to the file
        Path file = Paths.get("./lib/dynamic.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writer.write(csv_string, 0, csv_string.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        } // try-catch
        System.out.println("File successfully written");

    } // auto_run()

    static int[] generate_input(int size) {

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        } // for
        Collections.shuffle(list);
        return list.stream().mapToInt(i -> i).toArray();

    } // generate_input(int)

    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);

        if (args.length == 0) {
            System.out.print("Enter the size of input: ");
            data = generate_input(s.nextInt());
        } else {
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);

            data = Arrays.stream(br.readLine()
                    .split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } // if-else

        Sorting sorter = new Sorting(data);
        long comparisons;

        System.out.print(
                "Commands: selection-sort (s)\tmerge-sort (m)\t"
                        + "heap-sort (h)\tquick-sort-fp (q)\tquick-sort-rp (r)");

        for (;;) {
            System.out.print("\nEnter the algorithm: ");
            no_prefix: // from default case
            switch (s.next()) {

                case "s":
                    comparisons = sorter.selection_sort();
                    print_array(data);
                    System.out.println("#Selection-sort comparisons: " + comparisons);
                    s.close();
                    System.exit(0);
                    break;

                case "m":
                    comparisons = sorter.merge_sort(0, data.length - 1);
                    print_array(data);
                    System.out.println("#Merge-sort comparisons: " + comparisons);
                    s.close();
                    System.exit(0);
                    break;

                case "h":
                    comparisons = sorter.heap_sort();
                    print_array(data);
                    System.out.println("#Heap-sort comparisons: " + comparisons);
                    s.close();
                    System.exit(0);
                    break;

                case "q":
                    comparisons = sorter.quick_sort_fp(0, data.length - 1);
                    print_array(data);
                    System.out.println("#Quick-sort-fp comparisons: " + comparisons);
                    s.close();
                    System.exit(0);
                    break;

                case "r":
                    comparisons = sorter.quick_sort_rp(0, data.length - 1);
                    print_array(data);
                    System.out.println("#Quick-sort-rp comparisons: " + comparisons);
                    s.close();
                    System.exit(0);
                    break;

                default:
                    System.out.print("Invalid command, try again: ");
                    break no_prefix; // goto label

            } // switch
        } // for

    } // main(String[])

} // SortingDriver