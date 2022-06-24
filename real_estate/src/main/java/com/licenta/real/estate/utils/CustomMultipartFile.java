package com.licenta.real.estate.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class CustomMultipartFile implements MultipartFile {

    private final byte[] fileContent;

    public CustomMultipartFile(byte[] fileData, String name) {
        this.fileContent = fileData;

    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        FileOutputStream fileOutputStream = new FileOutputStream(dest);
        fileOutputStream.write(fileContent);
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public byte[] getBytes() {
        return fileContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileContent);
    }
}
