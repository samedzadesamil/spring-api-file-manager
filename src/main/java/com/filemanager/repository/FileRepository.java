package com.filemanager.repository;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileRepository {


    public void saveFile(String file, String targetDirectory) throws IOException {
        File targetFile = new File(targetDirectory + file);
        if (targetFile.exists()) {
            throw new IOException("File already exists in the target directory.");
        } else {
            targetFile.createNewFile();
        }
    }

    public List<File> listFiles(String directoryPath) {
        List<File> fileList = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }

    public void deleteFile(String fileToDelete,String baseDirectory) throws IOException {
        File file = new File(baseDirectory + fileToDelete);
        if (file.exists() && file.isFile()) {
            file.delete();
        } else {
            throw new IOException("File not found: " + fileToDelete);
        }
    }
}
