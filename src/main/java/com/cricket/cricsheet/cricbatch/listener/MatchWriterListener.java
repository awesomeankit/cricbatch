package com.cricket.cricsheet.cricbatch.listener;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cricket.cricsheet.cricbatch.model.Match;
import com.cricket.cricsheet.cricbatch.util.FileUtil;

@Component
public class MatchWriterListener implements ItemWriteListener<Match> {
	
	private static final Logger logger = LogManager.getLogger(MatchWriterListener.class);

	
	@Value("${match.data.filepath}")
	private String filepath;
	
	@Override
	public void beforeWrite(List<? extends Match> items) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterWrite(List<? extends Match> items) {
		for(Match match: items) {
			boolean deleted= FileUtil.deleteFile(filepath+match.getFrom_filename());
			logger.info(match.getFrom_filename()+": file delete status:"+deleted);
		}
	}

	@Override
	public void onWriteError(Exception exception, List<? extends Match> items) {
		// TODO Auto-generated method stub

	}

}
