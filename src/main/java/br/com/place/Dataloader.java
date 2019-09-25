package br.com.place;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class Dataloader {
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	@PostConstruct
    private void initDatabase() {
		
	}
}
