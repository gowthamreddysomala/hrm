package com.example.hrm.utils;

import com.example.hrm.exceptions.NullValueException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private FileInputStream fileInputStream;
    private Properties properties;
    private String path = "src/main/resources/object.properties";

    // constructor for initialization of FileInputStream and Properties
    // create a new properties file and use the file input steam in it.
    public PropertiesReader() {
        try {
            fileInputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException exception) {
            System.err.println("IO Exception");
        }
    }

    // returning the String by find the required key in object.properties file
    // if the result is null it throws custom exception
    public String getProperty(String key) throws NullValueException {
        String result = properties.getProperty(key);
        if (result.isEmpty()) {
            throw new NullValueException(key);
        }
        return result;
    }

    // closing the FileInputStream after using
    public void killFileInputStream() throws IOException {
        fileInputStream.close();
    }
}
