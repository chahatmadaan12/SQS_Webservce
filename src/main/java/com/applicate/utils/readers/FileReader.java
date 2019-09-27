package com.applicate.utils.readers;

public interface FileReader<T> {
   T get(String filePath) throws Throwable;
}
