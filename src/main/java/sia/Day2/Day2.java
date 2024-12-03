package sia.Day2;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
    private final File file;
    public int safeReportCount = 0;

    public Day2(File file) {
        this.file = file;
    }

    public void readReport() throws FileNotFoundException {
        Scanner sc = new Scanner(this.file);

        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");
            ArrayList<Integer> lineArr = new ArrayList<>();

            for (String s : line) {
                lineArr.add(Integer.parseInt(s));
            }

            if (!isSafe(lineArr)) {
                for (int i = 0; i < lineArr.size(); i++) {
                    List<Integer> tempList = new ArrayList<>(lineArr);
                    tempList.remove(i);
                    if (isSafe(tempList)) {
                        safeReportCount++;
                        break;
                    }
                }
            } else {
                safeReportCount++;
            }
        }
        sc.close();
    }

    public boolean isSafe(List<Integer> lineArr) {
        boolean isDec = lineArr.getFirst() < lineArr.get(1);

        for (int i = 0; i < lineArr.size()-1; i++) {
            int diff = Math.abs(lineArr.get(i + 1) - lineArr.get(i));
            if (diff < 1 || diff > 3) return false;
            if ( (isDec && lineArr.get(i) > lineArr.get(i+1)) || (!isDec && lineArr.get(i) < lineArr.get(i+1)) ) return false;
        }

        return true;
    }
}