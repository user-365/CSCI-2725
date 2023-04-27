public class Sorting {

    private int[] data;

    public Sorting(int[] data) {
        this.data = data;
    } // Sorting(int[])

    public boolean is_sorted() {

        int min_value = data[0];
        for (int i : data) {
            if (min_value > i) {
                return false;
            } // if
        } // for
        return true;

    } // is_sorted()

    void swap(int index1, int index2) {
        int temp_value = data[index1];
        data[index1] = data[index2];
        data[index2] = temp_value;
    } // swap(int,int)

    public long selection_sort() {

        long comparisons = 0;
        for (int current_index = 0; current_index < data.length; current_index++) {
            // Find minimum value in what's unsorted
            int min_index = current_index;
            for (int index = current_index; index < data.length; index++) {
                if (data[index] < data[min_index]) {
                    min_index = index;
                } // if
                comparisons++;
            } // for
              // Swap values at those indices
            swap(current_index, min_index);
        } // for
        return comparisons;

    } // selection_sort()

    long merge(int left_from, int left_through, int right_from, int right_through) {

        long comparisons = 0;
        int temp_arr[] = new int[data.length];
        int index = left_from;
        int copy_from = left_from;
        // Merge with comparisons
        while ((left_from <= left_through) && (right_from <= right_through)) {
            if (data[left_from] < data[right_from]) {
                temp_arr[index] = data[left_from];
                left_from++;
            } else {
                temp_arr[index] = data[right_from];
                right_from++;
            } // if-else
            comparisons++;
            index++;
        } // while
          // Merge rest of left half
        while (left_from <= left_through) {
            temp_arr[index] = data[left_from];
            left_from++;
            index++;
        } // while
          // Merge rest of right half
        while (right_from <= right_through) {
            temp_arr[index] = data[right_from];
            right_from++;
            index++;
        } // while
          // Copy `temp_arr` into `data` array
        java.lang.System.arraycopy(temp_arr, copy_from, data, copy_from, index - copy_from);
        return comparisons;

    } // merge(int,int,int,int)

    public long merge_sort(int from, int through) {

        long comparisons = 0;
        if (from < through) {
            int midpoint = (from + through) / 2;
            // Recurse on left half
            comparisons += merge_sort(from, midpoint);
            // Recurse on right half
            comparisons += merge_sort(midpoint + 1, through);
            // Merge
            comparisons += merge(from, midpoint, midpoint + 1, through);
        } // if
        return comparisons;

    } // merge_sort(int,int)

    long[] partition(int before, int after, int partition_point) {

        long comparisons = 0;
        int partition_value = data[partition_point];
        int first = before - 1;
        int last = after + 1;

        for (;;) {
            // Increment `first` until greater than `partition_value`
            do {
                first++;
                if (first >= data.length) {
                    break;
                } // if
                comparisons++;
            } while (data[first] <= partition_value); // do-while
            // Decrement `last` until leq than `partition_value`
            do {
                last--;
                if (last < 0) {
                    break;
                } // if
                comparisons++;
            } while (data[last] > partition_value); // do-while
            if (first > last) {
                // Swap `partition_point` and `last`
                swap(partition_point, last);
                long[] return_value = new long[2];
                return_value[0] = (long) last;
                return_value[1] = comparisons;
                return return_value;
            } // if
              // Swap `first` and `last`; move them toward each other
            swap(first, last);
        } // for (infinite)

    } // partition(int,int,int)

    long quick_sort_fp(int before, int after) {

        long comparisons = 0;
        if (before < after && before >= 0 && after >= 0) {

            int partition_point = before;
            long[] partition_results = partition(before + 1, after, partition_point);
            partition_point = (int) partition_results[0];
            comparisons += partition_results[1];
            comparisons += quick_sort_fp(before, partition_point - 1);
            comparisons += quick_sort_fp(partition_point + 1, after);
        } // if
        return comparisons;

    } // quick_sort_fp(int,int)

    long quick_sort_rp(int before, int after) {

        long comparisons = 0;
        if (before < after) {
            int partition_point = java.util.concurrent.ThreadLocalRandom
                    .current().nextInt(before, after);
            // before + 1 is stack overflow
            // after - 1 is stack overflow
            long[] partition_results = partition(before, after, partition_point);
            partition_point = (int) partition_results[0];
            comparisons += partition_results[1];
            comparisons += quick_sort_rp(before, partition_point - 1);
            comparisons += quick_sort_rp(partition_point + 1, after);
        } // if
        return comparisons;

    } // quick_sort_rp(int,int)

    long percolate_down(int unordered_root, int crown) {

        long comparisons = 0;
        int larger_child, left_child, right_child;
        left_child = (unordered_root << 1) + 1;
        right_child = (unordered_root << 1) + 2;
        if (left_child <= crown) {
            if (left_child == crown) {
                larger_child = left_child;
            } else {
                larger_child = (data[left_child] <= data[right_child]) ? right_child : left_child;
                comparisons++;
            } // if-else
            if (data[unordered_root] < data[larger_child]) {
                swap(unordered_root, larger_child);
                comparisons += percolate_down(larger_child, crown);
            } // if
            comparisons++;
        } // if
        return comparisons;

    } // percolate_down(int,int)

    long heap_sort() {

        long comparisons = 0;
        int index;
        // Heapify the array
        for (index = data.length / 2 - 1; index >= 0; index--) {
            comparisons += percolate_down(index, data.length - 1);
        } // for
          // Sort the array
        for (index = data.length - 1; index >= 1; index--) {
            swap(0, index);
            comparisons += percolate_down(0, index - 1);
        } // for
        return comparisons;

    } // heap_sort()

    public void set_data(int[] new_data) {
        this.data = new_data;
    } // set_data(int[])

    public int[] get_data() {
        return this.data;
    } // get_data()

} // Sorting

// TK TODO
// - fix quick_sort_fp and quick_sort_rp comparison count
