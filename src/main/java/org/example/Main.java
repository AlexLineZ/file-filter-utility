package org.example;

import org.example.filemanager.FileManager;
import org.example.options.LaunchOptions;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager(args);
        fileManager.process();
    }
}