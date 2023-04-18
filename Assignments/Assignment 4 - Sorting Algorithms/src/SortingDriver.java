import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SortingDriver {

    public static void main(String[] args) {
        FileReader fr = new FileReader("");
        BufferedReader br = new BufferedReader(fr);

        if (br.ready()) {
            int[] data = Arrays.stream(br.readLine()
            .split("\\s"))
            .mapToInt(Integer::parseInt)
            .toArray();
        } // if

} // SortingDriver