package sia.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {
    public List<Integer> leftArr = new ArrayList<>();
    public List<Integer> rightArr = new ArrayList<>();
    public File file;

    public Day1(File file) {
        this.file = file;
    }

    public void populateLists() throws FileNotFoundException {
        Scanner sc = new Scanner(this.file);
        while (sc.hasNextInt()) {
            leftArr.add(sc.nextInt());
            rightArr.add(sc.nextInt());
        }
        sc.close();
    }

    public int calcDistSums() {
        Collections.sort(leftArr);
        Collections.sort(rightArr);
        int distSums = 0;
        for (int i = 0; i < 1000; i++) {
            distSums += Math.max(leftArr.get(i), rightArr.get(i)) - Math.min(leftArr.get(i), rightArr.get(i));
        }
        return distSums;
    }

    public HashMap<Integer, Integer> getRightMap() {
        HashMap<Integer, Integer> rightMap = new HashMap<>();

        for (Integer integer : rightArr) {
            if (rightMap.containsKey(integer)) {
                rightMap.put(integer, rightMap.get(integer) + 1);
            } else {
                rightMap.put(integer, 1);
            }
        }
        return rightMap;
    }

    public int calcSimilarity() {
        int totalScore = 0;
        int currentScore;
        HashMap<Integer, Integer> rightMap = getRightMap();

        for (Integer integer : leftArr) {
            if (rightMap.containsKey(integer)) {
                currentScore = integer * rightMap.get(integer);
                totalScore += currentScore;
            }
        }

        return totalScore;
    }
}
