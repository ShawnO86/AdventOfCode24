package sia.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;


public class Day4Runner {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("D:\\adventOfCode24\\Advent_of_code_24\\src\\main\\java\\sia\\Day4\\input.txt");
        Day4 day4 = new Day4(inputFile, "XMAS");

        int row = 0;

        while (row < 140) {
           int col = 0;

           while (col < 140) {
               //linear search for 'X'
               int[] foundX = day4.findX(row, col);

               System.out.println("cur pos - row: " + row + ", col: " + col);
               if (foundX != null) {
                    System.out.println("X found at: " + Arrays.toString(foundX));
                    //update start position of column
                    col = foundX[1];
                    System.out.println("Looking for remaining chars...");
                    day4.findRemaining(foundX);

               }
               col++;
           }
           row++;
        }
        System.out.println(day4.found);

    }
}
