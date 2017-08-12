package model.iofile;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import etc.Constants;

/**
 *
 * @author : Pham Tuan Ngoc
 *
 * this class will write file
 */
public class WriteFile {
	private static final Logger logger = Logger.getLogger(WriteFile.class);

    /**
     * method to write file
     * @param url url to file
     * @param data String data write
     * @return true if done
     */
    public static boolean write(String url, String data){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(Constants.ROOT_PATH + url);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(data);
            dos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException: ",e);
            return false;
        } catch (IOException e) {
            logger.error("IOException: ",e);
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error("IOException: ",e);                    
                }
            }
        }
        return true;
    }
}
