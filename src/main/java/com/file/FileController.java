package com.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class FileController {

    @Value("${folderPath}")
    private String folderPath;

    @RequestMapping(value = "/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) throws IOException {
        File file =new File(folderPath + File.separator + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentLength(file.length())
                //Set application/octet-stream so that the browser can download the file
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
