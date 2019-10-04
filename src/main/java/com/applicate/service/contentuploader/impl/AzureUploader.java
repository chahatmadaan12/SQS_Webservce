package com.applicate.service.contentuploader.impl;

import java.io.InputStream;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.applicate.service.contentuploader.ContentUploader;
import com.applicate.utils.Encryption;
import com.applicate.utils.configuration.Azure;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

@Component
public class AzureUploader implements ContentUploader {

	@Autowired
	private Azure azureProperties;

	@Autowired
	private Encryption encrypt;

	public void init(Azure azureProperties) {
		this.azureProperties = azureProperties;
	}

	@Override
	public Object upload(Long sourceFileSize, InputStream is, String fileName) {
		JSONObject returnObj = new JSONObject();
		try {
			CloudBlockBlob blob = getContainer().getBlockBlobReference(fileName);
			blob.deleteIfExists();
			blob.upload(is, sourceFileSize);
			String blobKey = encrypt.decrypt(azureProperties.getBlobUrl().toString()) + "" + fileName + "";
			returnObj.put("blobKey", blobKey)
			   .put("fileSize", sourceFileSize)
			   .put("fileName", fileName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObj;
	}

	private CloudBlobContainer getContainer() throws Exception {
		String storageConnectionString = "DefaultEndpointsProtocol=http;" + "AccountName="
				+ encrypt.decrypt(azureProperties.getAccountName().toString()) + ";" + "AccountKey="
				+ encrypt.decrypt(azureProperties.getAccountKey().toString()) + "";
		CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
		CloudBlobClient serviceClient = account.createCloudBlobClient();
		// Container name must be lower case.
		return serviceClient.getContainerReference(encrypt.decrypt(azureProperties.getContainer().toString()));
	}

}
