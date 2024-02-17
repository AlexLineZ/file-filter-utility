package org.example.statistic;

public class NumberStatistic extends Statistic {
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private double sum = 0;

    @Override
    public void update(String line) {
        super.update(line);
        double value = Double.parseDouble(line);
        if (value < min) min = value;
        if (value > max) max = value;
        sum += value;
    }

    @Override
    public String summarize() {
        String baseSummary = super.summarize();
        if (count > 0) {
            double average = sum / count;
            return baseSummary + ", Min: " + min + ", Max: " + max + ", Sum: " + sum + ", Avg: " + average;
        }
        return baseSummary;
    }

    public String shortSummarize(){
        return super.summarize();
    }
}
