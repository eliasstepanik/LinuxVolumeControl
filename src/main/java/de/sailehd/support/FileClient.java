package de.sailehd.support;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileClient {
    public ArrayList<String> ls(String path){
        ArrayList<String> list = new ArrayList<String>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                list.add(file.getName());
            }
        }
        return list;
    }
}
