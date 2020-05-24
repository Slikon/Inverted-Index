package kpi.iasa;

import java.io.File;
import java.util.ArrayList;

public class Files {

    ArrayList<File> files = new ArrayList<>();
    ArrayList<File> folders = new ArrayList<>();

    public ArrayList<File> prepareFiles(){

        folders.add(new File("C:\\Study\\3course_2sem\\Parallel\\course\\files\\test_neg"));
        folders.add(new File("C:\\Study\\3course_2sem\\Parallel\\course\\files\\test_pos"));
        folders.add(new File("C:\\Study\\3course_2sem\\Parallel\\course\\files\\train_neg"));
        folders.add(new File("C:\\Study\\3course_2sem\\Parallel\\course\\files\\train_pos"));
        folders.add(new File("C:\\Study\\3course_2sem\\Parallel\\course\\files\\train_unsup"));

        for (File folder : folders){
            for (File file : folder.listFiles()){
                files.add(file.getAbsoluteFile());
            }
        }
        return files;
    }
}
