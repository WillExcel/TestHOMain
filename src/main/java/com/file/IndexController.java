package com.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    @Value("${folderPath}")
    private String folderPath;

    @RequestMapping("/")
    public String index(Model model) throws IOException {
        List<FileInfo> fileInfos = Files.list(Paths.get(folderPath))
                .filter(Files::isRegularFile)
                .map(path -> fileInfo(path.toFile(), path)).collect(Collectors.toList());

        //Add the list of files to the model so the thymeleaf engine can iterate over then and display them in html
        model.addAttribute("files", fileInfos);
        return "index"; //Return index.html

    }

    private FileInfo fileInfo(File file, Path filePath) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginalName(file.getName());
        fileInfo.setName(fileName(file.getName()));
        fileInfo.setExtension(extension(file.getName()));
        fileInfo.setByteSize(file.length());
        fileInfo.setMimeType(mimeType(filePath));

        return fileInfo;
    }

    private String fileName(String fileNameWithExtension) {
        int index = fileNameWithExtension.lastIndexOf('.');
        if (index > 0) {
            return fileNameWithExtension.substring(0, index);
        } else {
            return fileNameWithExtension;
        }
    }

    private String extension(String fileNameWithExtension) {
        int index = fileNameWithExtension.lastIndexOf('.');
        if (index > 0) {
            return fileNameWithExtension.substring(index + 1, fileNameWithExtension.length());
        } else {
            return "No extension";
        }
    }

    private String mimeType(Path filePath){
        String mimeType = null;
        try {
            mimeType = Files.probeContentType(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(mimeType == null){
            return "unknown";
        }
        return mimeType;
    }
}
