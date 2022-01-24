package com.cricket.cricsheet.cricbatch.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.cricket.cricsheet.cricbatch.listener.MatchWriterListener;
import com.cricket.cricsheet.cricbatch.model.Match;
import com.cricket.cricsheet.cricbatch.processor.MatchItemProcessor;
import com.cricket.cricsheet.cricbatch.tasklet.UnzipMatchFileTasklet;
import com.cricket.cricsheet.cricbatch.writer.MatchItemDBWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@Configuration
public class MatchFileReaderConfig {
	
	private static final Logger logger = LogManager.getLogger(MatchFileReaderConfig.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Value("${match.data.filepath}")
	private String filepath;
	
	@Value("${match.data.filepattern}")
	private String filepattern;
	
	@Value("${match.mongo.collection.name}")
	private String mongoCollectionName;
	
	@Value("${match.read.chunkSize}")
	private int chunkSize;
	
	@Value("${match.read.threadcount}")
	private int matchReadThreadCount;
	
	@Autowired
	private MatchItemDBWriter matchItemDbWriter;
	
	@Autowired
	private MatchItemProcessor matchItemProcessor;
	
	@Autowired
	private MatchWriterListener matchWriterListener;
	
	@Autowired
	private UnzipMatchFileTasklet unzipMatchFileTasklet;
	
	@Bean
	public Step unzipMatchFiles() {
		return stepBuilderFactory.get("unzipMatchFiles")
				.tasklet(unzipMatchFileTasklet)
				.build();
	}

	@Bean(destroyMethod="")
	@StepScope
	public SynchronizedItemStreamReader<Match> multiResourceItemReader(){
		try {
			MultiResourceItemReader<Match> reader = new MultiResourceItemReader<>();
			ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
			Resource[] inputFiles = patternResolver.getResources("file:" + filepath + filepattern);
			reader.setDelegate(matchDataReader());
			reader.setResources(inputFiles);
			SynchronizedItemStreamReader<Match> syncItemReader= new SynchronizedItemStreamReader<Match>();
			syncItemReader.setDelegate(reader);
			return syncItemReader;
		} catch (Exception e) {
			logger.error("Exception ",e);
			return null;
		}
	}

	@Bean(destroyMethod="")
	public JsonItemReader<Match> matchDataReader() {
		final ObjectMapper mapper = new ObjectMapper();
		final GenericJsonObjectReader<Match> jsonObjectReader = new 
				GenericJsonObjectReader<>(Match.class);
		jsonObjectReader.setMapper(mapper);

		return new JsonItemReaderBuilder<Match>().jsonObjectReader(jsonObjectReader).name("matchDataReader").build();

	}
	
	@Bean
	public MongoItemWriter<Match> matchItemWriter() {
		MongoItemWriter<Match> writer = new MongoItemWriter<Match>();
	    writer.setTemplate(mongoTemplate);
	    writer.setCollection(mongoCollectionName);
	    return writer;
	}
	
	@Bean
	public TaskExecutor taskExecutor(){
	    SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("match_executor");
	    asyncTaskExecutor.setConcurrencyLimit(matchReadThreadCount);
	    return asyncTaskExecutor;
	}

	@Bean
	public Step jsonMatchFileReadStep() {
		return stepBuilderFactory.get("jsonMatchFileReadStep")
				.<Match, Match>chunk(chunkSize)
				.reader(multiResourceItemReader())
				.processor(matchItemProcessor)
				.writer(matchItemDbWriter)
				.faultTolerant()
				.skip(UnrecognizedPropertyException.class)
				.skipLimit(15)
				.listener(matchWriterListener)
				.taskExecutor(taskExecutor())
				.build();
	}

	@Bean
	public Job matchFileReaderjob() {
		return jobBuilderFactory.get("matchFileReaderjob")
				.start(unzipMatchFiles())
				.next(jsonMatchFileReadStep())
				.build();
	}

}
