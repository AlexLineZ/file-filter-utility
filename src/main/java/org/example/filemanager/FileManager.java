package org.example.filemanager;

import org.example.options.LaunchOptions;

public class FileManager {
    private final LaunchOptions launchOptions;

    public FileManager(String[] args) {
        this.launchOptions = new LaunchOptions(args);
    }

    public void process() {
        DataSorter sorter = new DataSorter(launchOptions);
        sorter.processFiles();
    }
}
