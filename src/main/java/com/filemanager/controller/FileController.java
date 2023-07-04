package com.filemanager.controller;

import com.filemanager.dto.FileDto;
import com.filemanager.service.FileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @DeleteMapping()
    public String deleteFile(@RequestBody FileDto fileDto) {
    return fileService.deleteFile(fileDto);
    }

    @PostMapping()
    public String addFile(@RequestBody FileDto fileDto){
        System.out.println(fileDto.getFileName()+fileDto.getFileExtension());
        return fileService.addFile(fileDto);
    }

    @GetMapping("/files")
    public List<String> getFileList(){
        return fileService.getFileList();
    }


}
