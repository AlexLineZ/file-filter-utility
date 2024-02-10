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
    private static String outputPath = "./output";
    private static String prefix = "";
    private static boolean appendMode = false;
    private static StatisticType statisticType = StatisticType.FULL;
    private List<String> inputFiles = new ArrayList<>();

    public LaunchOptions(String[] args){
        this.args = args;
        initial();
    }

    private void initial() {
        setOutputPath();
        setPrefix();
        setAppendMode();
        setStatisticType();
        setInputFiles();
    }

    private void setOutputPath(){
        var argsList = Arrays.asList(args);
        int indexOfO = argsList.indexOf("-o");
        if (indexOfO != -1 && argsList.size() > indexOfO + 1) {
            String potentialPath = argsList.get(indexOfO + 1);
            if (!potentialPath.startsWith("-")) {
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
    }

    private void setAppendMode() {
        var argsList = Arrays.asList(args);
        appendMode = argsList.contains("-a");
    }

    private void setPrefix() {
        var argsList = Arrays.asList(args);
        int indexOfP = argsList.indexOf("-p");
        if (indexOfP != -1 && argsList.size() > indexOfP + 1) {
            prefix = argsList.get(indexOfP + 1);
        }
    }

    private void setStatisticType() {
        var argsList = Arrays.asList(args);
        if (argsList.contains("-s")) {
            statisticType = StatisticType.SHORT;
        } else if (argsList.contains("-f")) {
            statisticType = StatisticType.FULL;
        }
    }

    private void setInputFiles() {
        List<String> argsList = Arrays.asList(args);
        int lastKeyIndex = Math.max(argsList.lastIndexOf("-o"), argsList.lastIndexOf("-p"));
        lastKeyIndex = Math.max(lastKeyIndex, argsList.lastIndexOf("-a"));
        lastKeyIndex = Math.max(lastKeyIndex, argsList.lastIndexOf("-s"));
        lastKeyIndex = Math.max(lastKeyIndex, argsList.lastIndexOf("-f"));

        if (lastKeyIndex != -1 && lastKeyIndex + 1 < argsList.size()) {
            inputFiles = argsList.subList(lastKeyIndex + 2, argsList.size());
        }
    }

    public void printOptions() {
        System.out.println("Current launch settings:");
        System.out.println("Output path: " + outputPath);
        System.out.println("File name prefix: " + prefix);
        System.out.println("Append mode: " + (appendMode ? "enabled" : "disabled"));
        System.out.println("Statistic type: " + (statisticType == StatisticType.FULL ? "full" : "short"));
        System.out.println("Input files: " + inputFiles.toString());
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
