package sia.Day2;

import java.io.File;
import java.io.FileNotFoundException;

public class Day2Runner {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("D:\\adventOfCode24\\Advent_of_code_24\\src\\main\\java\\sia\\Day2\\input.txt");
        Day2 day2 = new Day2(inputFile);
        day2.readReport();

        System.out.println(day2.safeReportCount);
    }
}
