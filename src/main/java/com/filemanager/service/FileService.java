package com.filemanager.service;

import com.filemanager.dto.FileDto;
import com.filemanager.repository.FileRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private static final String BASE_DIRECTORY = "C:/Users/samed/Desktop/file-manager";
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public String addFile(@NotNull FileDto fileDto) {
        reserveDirectory();
        String file = fileDto.getFileName() + "." + fileDto.getFileExtension();
        String targetDirectory = BASE_DIRECTORY;
        try {
            fileRepository.saveFile(file, targetDirectory);
            return "File saved successfully.";
        } catch (IOException e) {
            return "An error occurred while saving the file: " + e.getMessage();
        }
    }

    public List<String> getFileList() {
        List<String> list = new ArrayList<>();
        List<File> fileList = fileRepository.listFiles(BASE_DIRECTORY);
        for (File file : fileList) {
            list.add(file.getName());
        }
        return list;
    }

    public String deleteFile(@NotNull FileDto fileDto) {
        String fileToDelete = fileDto.getFileName() + "." + fileDto.getFileExtension();
        try {
            fileRepository.deleteFile(fileToDelete, BASE_DIRECTORY);
            return "File deleted successfully.";
        } catch (IOException e) {
            return "An error occurred while deleting the file: " + e.getMessage();
        }
    }


    private static void reserveDirectory() {
        File baseDirectory = new File(BASE_DIRECTORY);
        if (!baseDirectory.exists()) {
            baseDirectory.mkdirs();
            System.out.println("Base directory reserved: " + BASE_DIRECTORY);
        }
    }
}
