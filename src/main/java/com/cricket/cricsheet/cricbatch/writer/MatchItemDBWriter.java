package com.cricket.cricsheet.cricbatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cricket.cricsheet.cricbatch.model.Match;

@Component
public class MatchItemDBWriter implements ItemWriter<Match> {
	
	@Autowired
	private MongoItemWriter<Match> matchItemWriter;
	
	@Override
	public void write(List<? extends Match> items) throws Exception {
		matchItemWriter.write(items);
	}
	
}
