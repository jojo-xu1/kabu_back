package com.kabu.dev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.kabu.dev.util.DailyBatch;
import com.kabu.dev.util.HistoryBatch;


@SpringBootApplication
@EnableScheduling
@MapperScan("com.kabu.dev.dao")
public class KaBuApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(KaBuApplication.class, args);
	}

}
//public class KaBuApplication {
//	public static void main(String[] args) throws Exception {
//		  SpringApplication springApplication = 
//	                new SpringApplicationBuilder()
//	                .sources(KaBuApplication.class)
//	                .web(WebApplicationType.NONE)
//	                .build();
//	        springApplication.run(args);
//	    	ApplicationContext context = DailyBatch.getApplicationContext();
//	    	HistoryBatch userServiceI = context.getBean(HistoryBatch.class);//
//	    	userServiceI.Dailybatch();
//		//SpringApplication.run(KaBuApplication.class, args);
//	}
//
//}
