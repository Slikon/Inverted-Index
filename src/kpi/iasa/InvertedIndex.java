package kpi.iasa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InvertedIndex extends Thread {

    public List<File> files;
    public HashMap<String, HashSet<File>> invertedIndex = new HashMap<>();
    public HashMap<String, HashSet<File>> getInvertedIndex() {
        return invertedIndex;
    }

    public InvertedIndex(List<File> files){
        this.files = files;
    }

    @Override
    public void run() {
        for (File eachFile : files){
            try {
                BufferedReader file = new BufferedReader(new FileReader(eachFile));
                ArrayList<String> content = new ArrayList<>();
                String str = file.readLine().replaceAll("[^a-zA-Z ]", "").toLowerCase().trim();
                Collections.addAll(content, str.split("\\s+"));

                for (String item : content){
                    if (!invertedIndex.containsKey(item)){
                        invertedIndex.put(item, new HashSet<File>());
                    }
                    invertedIndex.get(item).add(eachFile.getAbsoluteFile());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
