package com.springboot.core.util;


import com.springboot.core.exception.SpringBootDemoException;
import com.springboot.core.exception.SpringBootDemoExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by admin on 2018/1/16.
 */
public class FileUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * nio way
     * @param filenname
     * @return
     */
    public static byte[] toByteArray(String filenname){
        File f = new File(filenname);
        if(!f.exists()){
            LOGGER.error("文件未找不到" + filenname);
            throw new SpringBootDemoException(SpringBootDemoExceptionEnum.FILE_NOT_FOUND);
        }
        FileChannel fileChannel = null;
        FileInputStream fs = null;
        try{
            fs = new FileInputStream(f);
            fileChannel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)fileChannel.size());
            while(fileChannel.read(byteBuffer) > 0){
                // do something
            }
            return byteBuffer.array();
        }catch (IOException e){
            throw new SpringBootDemoException(SpringBootDemoExceptionEnum.FILE_READING_ERROR);
        }finally {
            try{
                fileChannel.close();
            }catch (IOException e){
                throw new SpringBootDemoException(SpringBootDemoExceptionEnum.FILE_READING_ERROR);
            }

            try{
                fs.close();
            }catch (IOException e){
                throw new SpringBootDemoException(SpringBootDemoExceptionEnum.FILE_READING_ERROR);
            }
        }
    }

    /**
     * 删除目录
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir){
        if(dir.isDirectory()){
            String[] children = dir.list();
            for(int i = 0; i < children.length; i++){
                boolean success = deleteDir(new File(dir, children[i]));
                if(!success){
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
