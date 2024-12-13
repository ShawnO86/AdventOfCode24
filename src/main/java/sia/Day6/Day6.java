package sia.Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    private final File inputFile;
    //up, right, down, left (x, y)
    private final List<int[]> directions = new ArrayList<>(List.of(new int[]{-1, 0}, new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1}));
    private int[] currDirection = directions.getFirst();
    private final int[] currPos;
    private boolean isInArea = true;

    public char[][] board = new char[130][130];
    public Set<List<Integer>> visitedPositions = new HashSet<>();

    public Day6(File inputFile) throws FileNotFoundException {
        this.inputFile = inputFile;
        readInput();
        this.currPos = getStartingPoint();
    }

    private void readInput() throws FileNotFoundException {
        Scanner sc = new Scanner(inputFile);
        int index = 0;
        while (sc.hasNextLine()) {
            char[] line = sc.nextLine().toCharArray();
            board[index] = line;
            index++;
        }
        sc.close();
    }

    private int[] getStartingPoint() {
        int[] startingPoint = {-1, -1};
        for (int i = 0; i < 130; i++) {
            for (int j = 0; j < 130; j++) {
                if (board[i][j] == '^') {
                    startingPoint[0] = i;
                    startingPoint[1] = j;
                }
            }
        }
        visitedPositions.add(List.of(startingPoint[0], startingPoint[1]));
        return startingPoint;
    }

    private void moveForward() {
        int newX = currPos[0] + currDirection[0];
        int newY = currPos[1] + currDirection[1];
        System.out.println("Current Pos: " + currPos[0] + ", " + currPos[1] + " next pos: " + newX + ", " + newY);
        //in area bounds check
        if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[currPos[0]].length) {
            //guard is leaving area
            isInArea = false;
            return;
        }
        //check for '#' to signify an obstruction then setNextDirection
        if (board[newX][newY] == '#') {
            System.out.println("Turning . . . ");
            setNextDirection();
            return;
        }
        //If no direction change - move to new position and add visited position
        //Visited positions are not counted twice - because using Set.
        visitedPositions.add(List.of(newX, newY));
        currPos[0] = newX;
        currPos[1] = newY;
    }

    //guard turns right 90 degrees
    private void setNextDirection() {
        System.out.println("Current direction: " + Arrays.toString(currDirection));
        if (currDirection[0] == directions.getLast()[0] && currDirection[1] == directions.getLast()[1]) {
            currDirection = directions.getFirst();
        } else {
            int currDirectionIndex = directions.indexOf(currDirection) + 1;
            currDirection = directions.get(currDirectionIndex);
        }
        System.out.println("Next direction: " + Arrays.toString(currDirection));
    }

    public void simulatePatrol() {
        while (isInArea) {
            moveForward();
        }
        System.out.println("Patrol finished");
        System.out.println("Visited " + visitedPositions.size() + " unique positions");
    }
}