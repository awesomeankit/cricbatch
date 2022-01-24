package com.cricket.cricsheet.cricbatch.tasklet;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cricket.cricsheet.cricbatch.util.ZipUtil;

@Component
public class UnzipMatchFileTasklet implements Tasklet {
	
	private static final Logger logger = LogManager.getLogger(UnzipMatchFileTasklet.class);
	
	@Value("${match.data.filepath}")
	private String filepath;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try {
			File directoryPath = new File(filepath);
			String contents[] = directoryPath.list();
			for (int i = 0; i < contents.length; i++) {
				if(!contents[i].endsWith(".zip")) {
					logger.info("Skipping file:"+contents[i]);
					continue;
				}
				ZipUtil.unzip(filepath+contents[i], filepath);
			}
		} catch (Exception e) {
			logger.error("Exception ",e);
		}
		return RepeatStatus.FINISHED;
	}

}
