import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Labwork 04 - Prelab : Rid's Time Hop
 *
 * Rid's algorithm, identified from his two notes, is INSERTION SORT (descending).
 * The program reads branches.txt, runs exactly 3 passes of insertion sort in
 * descending order, then performs a linear search for each target.
 *
 * Note 1: 3 7 1 9 5 8 2  --3 passes-->  9 7 3 1 5 8 2
 * Note 2: 2 5 8 1 10 6 3 --3 passes-->  8 5 2 1 10 6 3
 * Both match insertion sort, so insertion sort is implemented below.
 */
public class Prelab {

    // Number of passes Rid always runs before stopping.
    private static final int PASSES = 3;

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("branches.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Could not find branches.txt");
            return;
        }

        // Line 1: number of branches.
        int n = scanner.nextInt();

        // Line 2: the N stability values in their original order.
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = scanner.nextInt();
        }

        // Line 3: number of targets.
        int m = scanner.nextInt();

        // Line 4: the M target stability values.
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }

        scanner.close();

        // Apply Rid's algorithm for exactly 3 passes, descending.
        insertionSortDescending(branches, PASSES);

        // Display the array after the 3 passes.
        System.out.println(arrayToString(branches));

        // Search each target in input order and report the result.
        for (int i = 0; i < m; i++) {
            int target = targets[i];
            int index = linearSearch(branches, target);
            if (index != -1) {
                System.out.println(target + ": FOUND at index " + index);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }

    /**
     * Insertion sort in DESCENDING order, limited to a fixed number of passes.
     * Pass i (i = 1..passes) takes A[i] as the key and inserts it into the
     * sorted part to its left, shifting every smaller element one step right.
     * After the given number of passes the method stops, even if the array
     * is not fully sorted yet.
     */
    private static void insertionSortDescending(int[] a, int passes) {
        int n = a.length;
        for (int i = 1; i <= passes && i < n; i++) {
            int key = a[i];
            int j = i - 1;
            // For descending order, shift elements that are SMALLER than key.
            while (j >= 0 && a[j] < key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    /**
     * Linear search from index 0. Returns the index of the first occurrence
     * of target, or -1 if it is not present.
     */
    private static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /** Builds a space-separated representation of the array. */
    private static String arrayToString(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(a[i]);
        }
        return sb.toString();
    }
}
