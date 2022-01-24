package com.cricket.cricsheet.cricbatch.util;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtil {
	
	private static final Logger logger = LogManager.getLogger(FileUtil.class);
	
	public static boolean deleteFile(String filepath) {
		try {
			logger.info("Deleting file:"+filepath);
			File fileToDelete = new File(filepath);
			if(fileToDelete.exists()) {
				return fileToDelete.delete();
			}
			logger.info("File not found:"+filepath);
		} catch (Exception e) {
			logger.error("Exception:",e);
		}
		return false;
	}

}
