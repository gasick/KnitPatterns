package com.gcorp.knitshceme;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileWork {
    //Метод октрытия файла
    //Вызывается из pattern чтобы можно было создать сразу паттерн из этого класса.
    public static ArrayList<String> openFile(String fileName){
        ArrayList<String> inputFile = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    inputFile.add(line);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputFile;
    }
    //Метод сохранения файла
    public void saveFile(Pattern patt, String fileName) {
        for (int i=0;i<patt.getRows();i++)
        {
            String data = "";
            for (int j =0; j<patt.getColumns(); j++){
                data +=  patt.pattern[i][j].ordinal() + "||";
            }
            data += "\n";
            BufferedWriter bw = null;
            FileWriter fw = null;
            try {

                File file = new File(fileName);
                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                // true = append file
                fw = new FileWriter(file.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                bw.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null)
                        bw.close();
                    if (fw != null)
                        fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }
    //Метод экспорта файла
    public void exportFile(Pattern patt){};

}
