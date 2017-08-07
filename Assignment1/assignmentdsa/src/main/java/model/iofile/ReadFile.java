package model.iofile;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import etc.Constants;

public class ReadFile {
    public static String read(String url){
        FileInputStream fis = null;
        String result = "";
        try {
            fis = new FileInputStream(Constants.ROOT_PATH + url);
            DataInputStream dis = new DataInputStream(fis);
            result = dis.readUTF();
            dis.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
