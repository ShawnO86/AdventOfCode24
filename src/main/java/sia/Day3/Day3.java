package sia.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    private final File inputFile;
    public int sum = 0;

    public Day3(File inputFile) throws FileNotFoundException {
        this.inputFile = inputFile;
        readInput();
    }

    private void readInput() throws FileNotFoundException {
        Scanner sc = new Scanner(this.inputFile);
        boolean mulDisabled = false;

        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("\\)");

            for (String s : line) {
                s = s.trim() + ')';

                if (s.contains("do()")) mulDisabled = false;
                if (s.contains("don't()")) mulDisabled = true;

                if (mulDisabled) continue;

                Pattern mulPattern = Pattern.compile("mul\\([1-9][0-9]?[0-9]?,[1-9][0-9]?[0-9]?\\)");
                Matcher mulMatcher = mulPattern.matcher(s);

                if (mulMatcher.find()) {
                    s = mulMatcher.group(0);
                    System.out.println("s: " + s);
                    runCmd(s);
                }
            }
        }
        sc.close();
    }

    private void runCmd(String cmd) {
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();
        boolean isLeftNum = true;
        for (int i = 0; i < cmd.length(); i++) {
            if (Character.isDigit(cmd.charAt(i))) {
                if (isLeftNum) {
                    num1.append(cmd.charAt(i));
                } else {
                    num2.append(cmd.charAt(i));
                }
            }
            if (cmd.charAt(i) == ',') {
                isLeftNum = false;
            }
        }
        sum += Integer.parseInt(String.valueOf(num1)) * Integer.parseInt(String.valueOf(num2));
    }
}
