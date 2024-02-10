package org.example;

import org.example.options.LaunchOptions;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LaunchOptions launchOptions = new LaunchOptions(args);
        launchOptions.printOptions();
    }
}