import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class day01 {
    public static ArrayList<Integer> readReport(String filename) {
        Scanner sc = null;
        ArrayList<Integer> input = new ArrayList<>();
        try {
            sc = new Scanner(new File(filename));
            while (sc.hasNextInt()) {
                input.add(sc.nextInt());
            }
        } catch (Exception e) {
            System.out.println("Something went horribly wrong: " + e);
        } finally {
            sc.close();
        }
        return input;
    }

    public static int CountSingleDescents() {
        ArrayList<Integer> report = readReport("data.txt");
        int counter = 0;
        int previousMeasurement = report.get(0);
        for (Integer currentMeasurement : report) {
            if (currentMeasurement > previousMeasurement) {
                counter++;
            }
            previousMeasurement = currentMeasurement;
        }
        return counter;
    }

    public static int CountTripleDescents() {
        ArrayList<Integer> report = readReport("data.txt");
        int counter = 0;
        int previousMeasurement = report.get(0);
        for (int i = 0; i < report.size() - 3; i++) {
            int currentMeasurement = report.get(i + 3);
            if (currentMeasurement > previousMeasurement) {
                counter++;
            }
            previousMeasurement = report.get(i + 1);
        }
        return counter;
    }

    public static void main(String[] args) {
        System.out.println("There are " + CountSingleDescents()
           + " measurements that are larger than the previous measurement.");
        System.out.println("There are " + CountTripleDescents()
           + " \"triple measurements\" that are larger than the previous one.");
    }
}