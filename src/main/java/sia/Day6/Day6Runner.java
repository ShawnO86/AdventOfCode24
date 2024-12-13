package sia.Day6;

import java.io.File;
import java.io.FileNotFoundException;

public class Day6Runner {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("D:\\adventOfCode24\\Advent_of_code_24\\src\\main\\java\\sia\\Day6\\input.txt");
        Day6 day6 = new Day6(inputFile);
        day6.simulatePatrol();
    }
}
