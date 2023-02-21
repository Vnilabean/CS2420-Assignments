import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'reverseArray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static List<Integer> reverseArray(List<Integer> a) {
        // Write your code here
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = a.size() - 1; i == 0; i--) {
            for (int n = 0; n < a.size(); n++) {
                temp.set(n, a.get(i));
            }
        }

        return temp;

    }

    /**
     * @param <T>
     * @return
     */
    public static <T> T getArrayitem() {


        return null;
    }


    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            List<Integer> res = Result.reverseArray(arr);

            bufferedWriter.write(
                    res.stream()
                            .map(Object::toString)
                            .collect(joining(" "))
                            + "\n"
            );

            bufferedReader.close();
            bufferedWriter.close();
        }
    }}
