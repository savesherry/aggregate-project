package com.example.aggregate_methods.tools.storage;

import com.example.aggregate_methods.tools.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CREATE BY LiYang
 * ON 2021-03-05
 * SUPPLY : Thanks for watching
 */
public class FileStorage<T extends Serializable & Comparable<T>> {

    private String path;

    public FileStorage(String path) {
        this.path = path;
    }

    /**
     * 将list数据写入缓存路径的文件
     *
     * @param list
     */
    public synchronized void write(List<T> list) {
        try {
            // 打开文件
            FileOutputStream fos = new FileOutputStream(path);
            // 将数据写入文件
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);

            // 释放资源
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取缓存里面的数据
     *
     * @return
     */
    public synchronized List<T> read() {
        List<T> list = new ArrayList<T>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                return list;
            }

            // 打开文件
            FileInputStream fis = new FileInputStream(path);

            // 读取文件
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();

            // 释放资源
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
