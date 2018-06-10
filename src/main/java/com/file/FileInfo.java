package com.file;

import lombok.Data;

@Data
public class FileInfo {
// Below are the file information

    private String originalName;
    private String name;
    private String extension;
    private Long byteSize;
    private String mimeType;

}
