import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Set: menyimpan ID terdaftar secara unik (duplikat otomatis diabaikan)
        Set<String> registered = new HashSet<>();
        Scanner regScanner = new Scanner(new File("registrations.txt"));
        while (regScanner.hasNext()) {
            registered.add(regScanner.next());
        }
        regScanner.close();

        // Set: menyimpan mahasiswa yang sudah berhasil check-in
        Set<String> checkedIn = new HashSet<>();
        // List: menyimpan hasil setiap check-in sesuai urutan asli
        List<String> results = new ArrayList<>();
        int rejected = 0;

        Scanner checkScanner = new Scanner(new File("checkins.txt"));
        while (checkScanner.hasNext()) {
            String id = checkScanner.next();

            if (!registered.contains(id)) {
                results.add(id + ": Rejected (not registered)");
                rejected++;
            } else if (checkedIn.contains(id)) {
                results.add(id + ": Rejected (already checked in)");
                rejected++;
            } else {
                checkedIn.add(id);
                results.add(id + ": Checked in");
            }
        }
        checkScanner.close();

        System.out.println("===== Event Check-In Results =====");
        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + checkedIn.size());
        System.out.println("Absent students: " + (registered.size() - checkedIn.size()));
        System.out.println("Rejected attempts: " + rejected);
    }
}
