package model.iofile;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import etc.Constants;

public class WriteFile {
    public static boolean write(String url, String data){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(Constants.ROOT_PATH + url);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(data);
            dos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
