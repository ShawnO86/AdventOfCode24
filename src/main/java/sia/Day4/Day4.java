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
    private String word;

    //left, right, uo, down, up left, down left, up right, down right
    private final List<int[]> directions_OLD = new ArrayList<>(List.of(
            new int[]{0, -1}, new int[]{0, 1}, new int[]{-1, 0}, new int[]{1, 0}, new int[]{-1, -1}, new int[]{1, -1}, new int[]{-1, 1}, new int[]{1, 1}
    ));

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
        char searchChar = 'X';
        for (int i = startPos; i < row.length; i++) {
            if (row[i] == searchChar) {
                return new int[]{rowNum, i};
            }
        }
        return null;
    }

    //Search for 'M' in each direction
    //return array of directions of M or null if none
    public List<int[]> findM(int[]xPos){
        List<int[]> result = new ArrayList<>();

        for (int[] direction : directions) {
            //ensure word would not go out of bounds of board
            if ((xPos[0] <=word.length()-1 && direction[0] == -1) ||
                    (xPos[0] >= board[0].length - word.length() && direction[0] == 1) ||
                    (xPos[1] <=word.length()-1 && direction[1] == -1) ||
                    (xPos[1] >= board.length - word.length() && direction[1] == 1)) {
                System.out.println("Check out of bounds... Skipping...");
                continue;
            }

            int[] mCheck = {xPos[0] + direction[0], xPos[1] + direction[1]};
            if (board[mCheck[0]][mCheck[1]] == 'M') {
                result.add(direction);
            } else {
                System.out.println("No M at: " + Arrays.toString(direction));
            }
        }

        if (result.isEmpty()) {
            return null;
        }

        return result;
    }

}
