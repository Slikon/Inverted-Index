package kpi.iasa;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    static ArrayList<File> files = new Files().prepareFiles();
    public static int THREAD_NUM = 5;

    public static void main(String[] args) throws InterruptedException {

        HashMap<String, HashSet<File>> invertedIndex = new HashMap<>();

        InvertedIndex[] threadArray = new InvertedIndex[THREAD_NUM];

        //starting threads
        for (int numThread = 0; numThread < THREAD_NUM; numThread++){
            threadArray[numThread] = new InvertedIndex(files,
                    files.size()/THREAD_NUM * numThread,
                    numThread == (THREAD_NUM - 1)
                    ? files.size()
                    : files.size() / THREAD_NUM * (numThread + 1));
            threadArray[numThread].start();
        }

        //joining threads
        for (int numThread = 0; numThread < THREAD_NUM; numThread++){
            threadArray[numThread].join();
        }

        
    }
}
