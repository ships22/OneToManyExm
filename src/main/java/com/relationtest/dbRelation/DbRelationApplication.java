package com.relationtest.dbRelation;

import com.relationtest.dbRelation.service.generalService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbRelationApplication {


	public static void main(String[] args) {

		//generalService service = new generalService();

		SpringApplication.run(DbRelationApplication.class, args);

		//service.saveTest();
		System.out.println("it works!");
	}

}
