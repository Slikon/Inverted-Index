package kpi.iasa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class InvertedIndex extends Thread {

    public ArrayList<File> files;
    public HashMap<String, HashSet<File>> invertedIndex = new HashMap<>();
    public int startingFile;
    public int lastFile;

    public InvertedIndex(ArrayList<File> files, int startingFile, int lastFile){
        this.files = files;
        this.startingFile = startingFile;
        this.lastFile = lastFile;
    }
    public HashMap<String, HashSet<File>> getInvertedIndex() {
        return invertedIndex;
    }

    @Override
    public void run() {
        for (int index = startingFile; index < lastFile; index++){
            try {
                BufferedReader file = new BufferedReader(new FileReader(files.get(index)));

                ArrayList<String> content = new ArrayList<>();
                String str = file.readLine().replaceAll("[^a-zA-Z ]", "").toLowerCase().trim();
                Collections.addAll(content, str.split("\\s+"));

                for (String item : content){
                    if (!invertedIndex.containsKey(item)){
                        invertedIndex.put(item, new HashSet<File>());
                    }
                    invertedIndex.get(item).add(files.get(index).getAbsoluteFile());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            index++;
        }

    }


}
