package kpi.iasa;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    static ArrayList<File> files = new Files().prepareFiles();
    public static int THREAD_NUM = 100;

    public static void main(String[] args) throws InterruptedException {

        HashMap<String, HashSet<File>> invertedIndex = new HashMap<>();

        InvertedIndex[] threadArray = new InvertedIndex[THREAD_NUM];

        long START = System.currentTimeMillis();
        //starting threads
        for (int num = 0; num < THREAD_NUM; num++){
            threadArray[num] = new InvertedIndex(files,
                    files.size()/THREAD_NUM * num,
                    num == (THREAD_NUM - 1)
                    ? files.size()
                    : files.size() / THREAD_NUM * (num + 1));
            threadArray[num].start();
        }

        //joining threads
        for (int numThread = 0; numThread < THREAD_NUM; numThread++){
            threadArray[numThread].join();
        }
        long FINISH = System.currentTimeMillis();
        System.out.println("TIME = " + (FINISH-START));
    }
}
