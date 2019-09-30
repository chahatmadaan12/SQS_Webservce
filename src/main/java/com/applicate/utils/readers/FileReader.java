package com.applicate.utils.readers;

import org.springframework.stereotype.Component;

@Component
public interface FileReader<T> {
   T get(String filePath) throws Throwable;
}
