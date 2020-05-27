package kpi.iasa;

import java.io.File;
import java.util.ArrayList;

public class Files {

    ArrayList<File> files = new ArrayList<>();

    File[] directories = {new File("C:\\Study\\3course_2sem\\Parallel\\course\\files\\test_neg"),
                      new File("C:\\Study\\3course_2sem\\Parallel\\course\\files\\test_pos"),
                      new File("C:\\Study\\3course_2sem\\Parallel\\course\\files\\train_neg"),
                      new File("C:\\Study\\3course_2sem\\Parallel\\course\\files\\train_pos"),
                      new File("C:\\Study\\3course_2sem\\Parallel\\course\\files\\train_unsup")};

    public ArrayList<File> prepareFiles(){

        for (File folder : directories){
            for (File file : folder.listFiles()){
                files.add(file.getAbsoluteFile());
            }
        }
        return files;
    }
}
