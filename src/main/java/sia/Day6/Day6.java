package sia.Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6 {
    private final File inputFile;
    private final char[][] board = new char[130][130];
    //up, right, down, left
    private final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[] currDirection = directions[0];

    public int visited = 0;

    public Day6(File inputFile) throws FileNotFoundException {
        this.inputFile = inputFile;
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

    private void setNextDirection() {
        if (currDirection == directions[directions.length - 1]) {
            currDirection = directions[0];
        } else {
            for (int i = 0; i < directions.length; i++) {
                if (currDirection == directions[i]) {
                    currDirection = directions[i + 1];
                }
            }
        }
    }


}
