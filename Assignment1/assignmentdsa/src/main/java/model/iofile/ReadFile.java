package model.iofile;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import etc.Constants;

public class ReadFile {
	private static final Logger logger = LoggerFactory.getLogger(ReadFile.class);
    public static String read(String url){
        FileInputStream fileInputStream = null;
        String result = "";
        try {
        	fileInputStream = new FileInputStream(Constants.ROOT_PATH + url);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            result = dataInputStream.readUTF();
            dataInputStream.close();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException: ",e);
        } catch (IOException e) {
            logger.error("IOException: ",e);
        } finally {
            if (fileInputStream != null) {
                try {
                	fileInputStream.close();
                } catch (IOException e) {
                    logger.error("IOException: ",e);
                }
            }
        }
        if(StringUtils.isBlank(result)){
        	return Constants.DEFAULT_RESULT;
        }
        return result;
    }
}
