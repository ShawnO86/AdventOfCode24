package sia.Day3;

import sia.Day2.Day2;

import java.io.File;
import java.io.FileNotFoundException;

public class Day3Runner {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("D:\\adventOfCode24\\Advent_of_code_24\\src\\main\\java\\sia\\Day3\\input.txt");
        Day3 day3 = new Day3(inputFile);
        System.out.println(day3.sum);
    }
}
