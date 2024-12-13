package sia.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    private final File inputFile;
    private final char[][] board = new char[140][140];
    private final String word;
    //left, right, uo, down, up left, down left, up right, down right
    private final int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};

    public int found = 0;

    public Day4(File inputFile, String word) throws FileNotFoundException {
        this.inputFile = inputFile;
        this.word = word;
        readInput();
    }

    private void readInput() throws FileNotFoundException {
        Scanner sc = new Scanner(this.inputFile);
        int index = 0;
        while (sc.hasNextLine()) {
            char[] line = sc.nextLine().toCharArray();
            board[index] = line;
            index++;
        }
        sc.close();
    }

    //Search for 'X' linearly
    //return pos[rowNum,colIndex] if found or null if not
    public int[] findX(int rowNum, int startPos) {
        char[] row = board[rowNum];
        char searchChar = word.charAt(0);
        for (int i = startPos; i < row.length; i++) {
            if (row[i] == searchChar) {
                return new int[]{rowNum, i};
            }
        }
        return null;
    }

    //Search for 'M' in each direction
    //return array of directions of M or null if none
    public void findRemaining(int[]xPos){
        List<int[]> result = new ArrayList<>();

        for (int[] direction : directions) {
            //ensure word would not go out of bounds of board
            if (xPos[0] + direction[0] * (word.length() - 1) < 0 ||
                    xPos[0] + direction[0] * (word.length() - 1) >= board.length ||
                    xPos[1] + direction[1] * (word.length() - 1) < 0 ||
                    xPos[1] + direction[1] * (word.length() - 1) >= board[0].length) {
                System.out.println("Word would go out of bounds. Skipping direction: " + Arrays.toString(direction));
                continue;
            }
            //initial pos to check
            int[] mCheck = {xPos[0] + direction[0], xPos[1] + direction[1]};
            //keep checking in direction for remaining chars - add direction to mCheck after each found char
            for (int i = 1; i < word.length(); i++) {
                char searchChar = word.charAt(i);
                System.out.println("Char: " + searchChar);
                if (board[mCheck[0]][mCheck[1]] == searchChar) {
                    System.out.println(searchChar + " found at: " + Arrays.toString(direction));
                    mCheck[0] += direction[0];
                    mCheck[1] += direction[1];
                } else {
                    //break out of loop to start check on next direction
                    System.out.println("No " + searchChar + " at: " + Arrays.toString(direction));
                    break;
                }
                System.out.println("word loop i: " + i + " char " + searchChar);
                if (i == word.length() - 1) {
                    System.out.println("word found!");
                    found += 1;
                }
            }

        }
    }

}
