package com.applicate.service.contentuploader;

import java.io.InputStream;

public interface ContentUploader {
  
	Object upload(Long sourceFileSize,InputStream is,String fileName);
	
}
