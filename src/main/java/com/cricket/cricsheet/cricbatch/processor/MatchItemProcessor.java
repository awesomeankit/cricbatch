package com.cricket.cricsheet.cricbatch.processor;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.cricket.cricsheet.cricbatch.model.Match;

@Component
public class MatchItemProcessor implements  ItemProcessor<Match, Match>{
	
	@Override
	public Match process(Match item) throws Exception {
		item.setFrom_filename(item.getResource().getFilename());
		item.setCreatedDate(new Date());
		return item;
	}
}
