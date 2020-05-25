package kpi.iasa;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    static ArrayList<File> files = new Files().prepareFiles();
    public static int THREAD_NUM = 4;


    public static void main(String[] args) throws InterruptedException {

        InvertedIndex[] threadArray = new InvertedIndex[THREAD_NUM];
        HashMap<String, HashSet<File>> fullInvertedIndex = new HashMap<>();

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

        //merging results of all threads into final 'fullInvertedIndex'
        for (int num = 0; num < THREAD_NUM; num++){
            threadArray[num].getInvertedIndex().forEach(
                    (key, value) -> fullInvertedIndex.merge(key, value,
                            (v1, v2) ->
                            {
                                HashSet<File> fileValueV1 = new HashSet<>(v1);
                                fileValueV1.addAll(v2);
                                return fileValueV1;
                            }
                            ));
        }

        long FINISH = System.currentTimeMillis();
        System.out.println("TIME = " + (FINISH-START));

        System.out.println(fullInvertedIndex.get("hollywood"));
    }
}
