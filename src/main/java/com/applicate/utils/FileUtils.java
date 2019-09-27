package com.applicate.utils;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class FileUtils {

    public static String getFileExtention(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public static String getFileNameWithoutExtension(String fileName) {
        return FilenameUtils.getBaseName(fileName);
    }

    public static String getFileNameWithoutExtension(File file) {
        return FilenameUtils.getBaseName(file.toString());
    }

    public static boolean isXmlFile(File file) {
        if (file.toString().endsWith(".xml")) {
            return true;
        }
        return false;
    }

    public static String concat(String baseFile, String destinationFile) {
        if (!baseFile.endsWith("/") && !baseFile.endsWith("\\")) {
            baseFile = baseFile + "/";
        }
        if (destinationFile.startsWith("/") || destinationFile.startsWith("\\")) {
            destinationFile = destinationFile.substring(1);
        }
        return baseFile + destinationFile;
    }

}
