package com.bluestone.blueStoneCodeTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.bluestone.blueStoneCodeTest.util.Custom;
import com.bluestone.blueStoneCodeTest.util.ScanAnnotation;

@SpringBootApplication
@EnableScheduling
public class BlueStoneCodeTestApplication implements CommandLineRunner {
	@Autowired
	private ScanAnnotation scanAnnotation;

	public static void main(String[] args) {
		SpringApplication.run(BlueStoneCodeTestApplication.class, args);
	}

	/*
	 * This implemented methods of CLR will run when the application starts
	 */
	@Override
	public void run(String... args) throws Exception {
		scanAnnotation.findAnnotatedClasses(Custom.class, "com.bluestone.blueStoneCodeTest");
	}
}
