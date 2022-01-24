package com.cricket.cricsheet.cricbatch.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.json.JsonObjectReader;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * This class follows the structure and functions similar to JacksonJsonObjectReader, with 
 * the difference that it expects a object as root node, instead of an array.
 */
public class GenericJsonObjectReader<T> implements JsonObjectReader<T> {

	private final Class<? extends T> itemType;

	private JsonParser jsonParser;

	private ObjectMapper mapper;

	private InputStream inputStream;

	
	public GenericJsonObjectReader(Class<? extends T> itemType) {
		this(new ObjectMapper(), itemType);
	}

	public GenericJsonObjectReader(ObjectMapper mapper, Class<? extends T> itemType) {
		this.mapper = mapper;
		this.itemType = itemType;
	}

	
	public void setMapper(ObjectMapper mapper) {
		Assert.notNull(mapper, "The mapper must not be null");
		this.mapper = mapper;
	}

	@Override
	public void open(Resource resource) throws Exception {
		Assert.notNull(resource, "The resource must not be null");
		this.inputStream = resource.getInputStream();
		this.jsonParser = this.mapper.getFactory().createParser(this.inputStream);
	}

	@Nullable
	@Override
	public T read() throws Exception {
		try {
			if (this.jsonParser.nextToken() == JsonToken.START_OBJECT) {
				return this.mapper.readValue(this.jsonParser, this.itemType);
			}
		} catch (IOException e) {
			throw new ParseException("Unable to read next JSON object", e);
		}
		return null;
	}

	@Override
	public void close() throws Exception {
		this.inputStream.close();
		this.jsonParser.close();
	}

}
