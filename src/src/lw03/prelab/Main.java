import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }

    // Looks for the input file in the working directory first,
    // then in the prelab folder (useful when running from the repo root in an IDE).
    private static File openInput(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            file = new File("src/lw03/prelab/" + fileName);
        }
        return file;
    }

    // ===== Problem 1: Playlist using List =====
    private static void problem1() throws FileNotFoundException {
        List<String> playlist = new ArrayList<>();
        Scanner sc = new Scanner(openInput("playlist.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ", 2);
            String operation = parts[0];

            switch (operation) {
                case "ADD":
                    playlist.add(parts[1]);
                    break;
                case "INSERT":
                    String[] insertParts = parts[1].split(" ", 2);
                    int index = Integer.parseInt(insertParts[0]);
                    playlist.add(index, insertParts[1]);
                    break;
                case "REMOVE":
                    playlist.remove(parts[1]); // removes first occurrence only, does nothing if absent
                    break;
            }
        }
        sc.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    // ===== Problem 2: Workshop participants using Set =====
    private static void problem2() throws FileNotFoundException {
        Set<String> participants = new LinkedHashSet<>(); // keeps first-appearance order
        int duplicates = 0;
        Scanner sc = new Scanner(openInput("participants.txt"));

        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();
            if (name.isEmpty()) continue;

            if (participants.contains(name)) {
                duplicates++;
            } else {
                participants.add(name);
            }
        }
        sc.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int number = 1;
        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }
        System.out.println("Duplicate registrations: " + duplicates);
    }

    // ===== Problem 3: Inventory using Map =====
    private static void problem3() throws FileNotFoundException {
        Map<String, Integer> inventory = new LinkedHashMap<>(); // keeps first-appearance order
        int failedSales = 0;
        Scanner sc = new Scanner(openInput("inventory.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");
            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    inventory.put(product, inventory.get(product) + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                    inventory.put(product, inventory.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        sc.close();

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}
