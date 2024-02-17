package org.example.statistic;

public class StringStatistic extends Statistic {
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = Integer.MIN_VALUE;

    @Override
    public void update(String line) {
        super.update(line);
        int length = line.length();
        if (length < minLength) minLength = length;
        if (length > maxLength) maxLength = length;
    }

    @Override
    public String summarize() {
        String baseSummary = super.summarize();
        if (count > 0) {
            return baseSummary + ", Min Length: " + minLength + ", Max Length: " + maxLength;
        }
        return baseSummary;
    }

    public String shortSummarize(){
        return super.summarize();
    }
}
