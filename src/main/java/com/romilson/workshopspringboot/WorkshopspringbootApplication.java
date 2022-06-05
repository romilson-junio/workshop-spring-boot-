package com.romilson.workshopspringboot;

import com.romilson.workshopspringboot.domain.*;
import com.romilson.workshopspringboot.domain.enums.StatePayment;
import com.romilson.workshopspringboot.domain.enums.TypeClient;
import com.romilson.workshopspringboot.repositories.*;
import com.romilson.workshopspringboot.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Properties;

@SpringBootApplication
public class WorkshopspringbootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopspringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
