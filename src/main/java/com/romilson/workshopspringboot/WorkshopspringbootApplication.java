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

	@Autowired
	public CategoryService categoryService;

	@Autowired
	public ProductRepository productRepository;

	@Autowired
	public StateRepository stateRepository;

	@Autowired
	public CityRepository cityRepository;

	@Autowired
	public ClientRepository clientRepository;

	@Autowired
	public AddressRepository addressRepository;

	@Autowired
	public OrderedRepository orderedRepository;

	@Autowired
	public PaymentRepository paymentRepository;

	@Autowired
	public OrderedItemRepository orderedItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(WorkshopspringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		 * Product and Category
		 */
		Product p1 = new Product(null, "Computador", 2000D);
		Product p2 = new Product(null, "Impressora", 800D);
		Product p3 = new Product(null, "Mouse", 80D);

		Category c1 = new Category(null, "Categoria 1", Arrays.asList(p1, p2, p3));
		Category c2 = new Category(null, "Categoria 2", Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(c1));
		p2.getCategories().addAll(Arrays.asList(c1,c2));
		p3.getCategories().addAll(Arrays.asList(c1));

		categoryService.save(c1);
		categoryService.save(c2);
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		/*
		 * State and City
		 */
		State e1 = new State(null, "Minas Gerais", "MG");
		State e2 = new State(null, "Distrito Federal", "DF");

		City city1 = new City(null, "Uberlândia", e1);
		City city2 = new City(null, "Brasília", e2);
		City city3 = new City(null, "Taguatinga", e2);

		e1.getCities().addAll(Arrays.asList(city1));
		e2.getCities().addAll(Arrays.asList(city2, city3));

		stateRepository.saveAll(Arrays.asList(e1, e2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));

		/*
		* Client and Address
		*/
		Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "06489777110", TypeClient.PESSOA_FISICA);
		client1.getPhones().addAll(Arrays.asList("61999999999", "61888888888"));
		Address address1 = new Address(null, "Rua B", "26", "Quadra E", "Esplanda", "99999999", client1, city1);
		Address address2 = new Address(null, "Rua F", "36", "Quadra G", "Esplanda", "88888888", client1, city2);
		client1.getAddress().addAll(Arrays.asList(address1, address2));

		clientRepository.saveAll(Arrays.asList(client1));
		addressRepository.saveAll(Arrays.asList(address1, address2));

		/*
		 * Ordered and Payment
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Ordered ordered1 = new Ordered(null, sdf.parse("30/09/2021 10:32"), client1, address1);
		Ordered ordered2 = new Ordered(null, sdf.parse("10/10/2021 19:35"), client1, address2);

		Payment payment1 = new CardPayment(null, StatePayment.PAID_OFF, ordered1, 6);
		ordered1.setPayment(payment1);

		Payment payment2 = new TicketPayment(null, StatePayment.PENDING, ordered2, sdf.parse("20/10/2022 00:00"), null);
		ordered2.setPayment(payment2);

		client1.getOrdereds().addAll(Arrays.asList(ordered1, ordered2));

		orderedRepository.saveAll(Arrays.asList(ordered1, ordered2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));

		/**
		 * Items Ordered and Items Products
		 */
		OrderedItem orderedItem1 = new OrderedItem(ordered1, p1, 0D, 1, 2000D);
		OrderedItem orderedItem2 = new OrderedItem(ordered1, p3, 0D, 2, 80D);
		OrderedItem orderedItem3 = new OrderedItem(ordered2, p2, 100D, 1, 800D);

		ordered1.getItems().addAll(Arrays.asList(orderedItem1, orderedItem2));
		ordered2.getItems().addAll(Arrays.asList(orderedItem3));

		p1.getItems().addAll(Arrays.asList(orderedItem1));
		p2.getItems().addAll(Arrays.asList(orderedItem3));
		p3.getItems().addAll(Arrays.asList(orderedItem2));

		orderedItemRepository.saveAll(Arrays.asList(orderedItem1, orderedItem2, orderedItem3));

	}
}
