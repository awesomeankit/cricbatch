package com.cricket.cricsheet.cricbatch.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ZipUtil {
	
	private static final Logger logger = LogManager.getLogger(ZipUtil.class);

	
	public static void unzip(String filepath, String outputFilePath) throws ZipException {
		logger.info("Unzipping file:"+filepath);
		ZipFile zipFile = new ZipFile(filepath);
		if (zipFile.isEncrypted()) {
			logger.info("File is password protected, cannot be unzipped. filename:"+filepath);
			return;
		}
		zipFile.extractAll(outputFilePath);
	}

}
