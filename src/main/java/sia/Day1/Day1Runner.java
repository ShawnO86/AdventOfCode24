package sia.Day1;

import java.io.File;
import java.io.FileNotFoundException;

public class Day1Runner {
    public static void main(String[] args) throws FileNotFoundException {
        String dir = "D:\\adventOfCode24\\Advent_of_code_24\\src\\main\\java\\sia\\Day1";
        File inputFile = new File(dir + File.separator + "input.txt");
        Day1 day1 = new Day1(inputFile);
        day1.populateLists();

        System.out.println(day1.calcDistSums());
        System.out.println(day1.calcSimilarity());
    }
}
