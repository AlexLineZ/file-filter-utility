package org.example.options;

import org.example.enums.StatisticType;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//java -jar util.jar -s -a -o "./outputput" -p sample- in1.txt in2.txt

public class LaunchOptions {
    private String[] args;
    private static String outputPath = "./";
    private static String prefix = "";
    private static boolean appendMode = false;
    private static StatisticType statisticType = StatisticType.NOTHING;
    private List<String> inputFiles = new ArrayList<>();

    public LaunchOptions(String[] args) {
        this.args = args;
        initial();
    }

    private void initial() {
        List<String> argsList = Arrays.asList(args);
        boolean expectValue = false;

        for (int i = 0; i < argsList.size(); i++) {
            String arg = argsList.get(i);

            if (expectValue) {
                expectValue = false;
                continue;
            }

            switch (arg) {
                case "-o":
                    setOutputPath(i);
                    expectValue = true;
                    break;
                case "-p":
                    setPrefix(i);
                    expectValue = true;
                    break;
                case "-a":
                    appendMode = true;
                    break;
                case "-s":
                    statisticType = StatisticType.SHORT;
                    break;
                case "-f":
                    statisticType = StatisticType.FULL;
                    break;
                default:
                    if (!arg.startsWith("-")) {
                        inputFiles.add(arg);
                    }
                    break;
            }
        }
    }

    private void setOutputPath(int index) {
        if (index + 1 < args.length) {
            String potentialPath = args[index + 1];
            try {
                Paths.get(potentialPath);
                outputPath = potentialPath;
            } catch (InvalidPathException e) {
                System.err.println("The specified output path is invalid: " + potentialPath);
            }
        } else {
            System.err.println("The output path is not specified correctly after -o");
        }
    }

    private void setPrefix(int index) {
        if (index + 1 < args.length) {
            prefix = args[index + 1];
        }
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public StatisticType getStatisticType() {
        return statisticType;
    }
}
