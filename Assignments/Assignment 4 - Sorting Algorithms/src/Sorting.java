import java.util.concurrent.ThreadLocalRandom;

public class Sorting {

    private int[] data;

    void swap(int index1, int index2) {
        int temp_value = data[index1];
        data[index1] = data[index2];
        data[index2] = temp_value;
    } // swap(int,int)

    void selection_sort() {

        for (int current_index = 0; current_index < data.length; current_index++) {
            // Find minimum value in what's unsorted
            int min_index = current_index;
            for (int index = current_index; index < data.length; index++) {
                if (data[index] < data[min_index]) {
                    min_index = index;
                } // if
            } // for
              // Swap values at those indices
            swap(current_index, min_index);
        } // for

    } // selection_sort()

    void merge(int left_from, int left_to, int right_from, int right_to) {

        int temp_arr[] = new int[data.length];
        int index = left_from;
        int copy_from = left_from;
        // Merge with comparisons
        while ((left_from < left_to) && (right_from < right_to)) {
            if (data[left_from] < data[right_from]) {
                temp_arr[index] = data[left_from];
                left_from++;
            } else {
                temp_arr[index] = data[right_from];
                right_from++;
            } // if-else
            index++;
        } // while
          // Merge rest of left half
        while (left_from < left_to) {
            temp_arr[index] = data[left_from];
            left_from++;
            index++;
        } // while
          // Merge rest of right half
        while (right_from < right_to) {
            temp_arr[index] = data[right_from];
            right_from++;
            index++;
        } // while
          // Copy `temp_arr` into `data` array
        System.arraycopy(temp_arr, copy_from, data, copy_from, index + ~copy_from);
    } // merge(int,int,int,int)

    void merge_sort(int from, int to) {

        if (from < to) {
            int midpoint = (from + to) / 2;
            // Recurse on left half
            merge_sort(from, midpoint);
            // Recurse on right half
            merge_sort(midpoint, to);
            // Merge
            merge(from, midpoint, midpoint, to);
        } // if

    } // merge_sort(int,int)

    int split(int before, int after, int split_point) {

        int split_value = data[split_point];
        while (!(before > after)) {
            // Increment `before` until greater than `split_value`
            while (!(data[before] > split_value)) {
                before++;
            } // while
              // Decrement `after` until leq than `split_value`
            while (!(data[after] <= split_value)) {
                after--;
            } // while
              // Swap `before` and `after`; move them toward each other
            swap(before, after);
            before++;
            after--;
        } // while
          // Swap `split_point` and `after`
        swap(split_point, after);
        return after;

    } // split(int,int,int)

    void quick_sort_fp(int before, int after) {

        if (before < after) {
            int split_point = before + 1;
            split_point = split(before, after, split_point);
            quick_sort_fp(before, split_point - 1);
            quick_sort_fp(split_point + 1, after);
        } // if

    } // quick_sort_fp(int,int)

    void quick_sort_rp(int before, int after) {

        if (before < after) {
            int split_point = ThreadLocalRandom.current().nextInt(before, after + 1);
            split_point = split(before, after, split_point);
            quick_sort_fp(before, split_point - 1);
            quick_sort_fp(split_point + 1, after);
        } // if

    } // quick_sort_rp(int,int)

    void percolate_down(int unordered_root, int crown) {

        int larger_child = 0, left_child, right_child;
        left_child = (unordered_root << 1) + 1;
        right_child = (unordered_root << 1) + 2;
        if (left_child == crown) {
            larger_child = left_child;
        } else if (left_child < crown) {
            larger_child = data[left_child] <= data[right_child] ? right_child : left_child;
        } // if-elif
        if (data[unordered_root] < data[larger_child]) {
            swap(unordered_root, larger_child);
            percolate_down(larger_child, crown);
        } // if

    } // percolate_down(int,int)

    void heap_sort() {

        int index;
        // Heapify the array
        for (index = data.length / 2 - 1; index >= 0; index--) {
            percolate_down(index, data.length - 1);
        } // for
          // Sort the array
        for (index = data.length - 1; index >= 1; index--) {
            swap(0, index);
            percolate_down(0, index - 1);
        } // for

    } // heap_sort()

    public void set_data(int[] new_data) {
        this.data = new_data;
    } // set_data(int[])

} // Sorting