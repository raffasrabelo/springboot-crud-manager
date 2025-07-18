package br.edu.ifsuldeminas.mch.springbootcrud.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Address;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Product;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Supplier;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.AddressRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.ProductRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.SupplierRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.UserRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class AtSystemStartup implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Address aEmerson = new Address();
		aEmerson.setNumber(123);
		aEmerson.setPlace("Rua A");
		aEmerson.setZipCode("136000");
		addressRepository.save(aEmerson);
		
		Address aNoe = new Address();
		aNoe.setNumber(100);
		aNoe.setPlace("Rua B");
		aNoe.setZipCode("136888");
		addressRepository.save(aNoe);
		
		Address aLu = new Address();
		aLu.setNumber(101);
		aLu.setPlace("Rua L");
		aLu.setZipCode("000888");
		addressRepository.save(aLu);
		
		addressRepository.flush();
		
		User emerson = new User();
		emerson.setName("Emerson A. Carvalho");
		emerson.setGender(User.Gender.M);
		emerson.setEmail("emerson@mail.com");
		emerson.setAddress(aEmerson);
		
		User luiza = new User();
		luiza.setName("Luiza O. Carvalho");
		luiza.setGender(User.Gender.F);
		luiza.setEmail("lu@mail.com");
		luiza.setAddress(aLu);
		
		User noe = new User();
		noe.setName("Noe L. Carvalho");
		noe.setGender(User.Gender.M);
		noe.setEmail("noe@mail.com");
		noe.setAddress(aNoe);
		
		userRepository.save(emerson);
		userRepository.save(luiza);
		userRepository.save(noe);
		
		// --- SUPPLIERS ---
	    Supplier supplierCaloi = new Supplier();
	    supplierCaloi.setName("Caloi Bikes Ltda");
	    supplierCaloi.setCnpj("12345678000199");
	    supplierCaloi.setEmail("contato@caloi.com");
	    supplierCaloi.setPhone("11999999999");
	    supplierRepository.save(supplierCaloi);

	    Supplier supplierShimano = new Supplier();
	    supplierShimano.setName("Shimano Parts");
	    supplierShimano.setCnpj("98765432000177");
	    supplierShimano.setEmail("vendas@shimano.com");
	    supplierShimano.setPhone("11988888888");
	    supplierRepository.save(supplierShimano);

	    Supplier supplierSense = new Supplier();
	    supplierSense.setName("Sense Factory");
	    supplierSense.setCnpj("11223344000155");
	    supplierSense.setEmail("info@sensefactory.com");
	    supplierSense.setPhone("11977777777");
	    supplierRepository.save(supplierSense);

	    supplierRepository.flush();

	    // --- PRODUCTS ---
	    Product bikeCaloi = new Product();
	    bikeCaloi.setName("Caloi Elite Carbon");
	    bikeCaloi.setDescription("Mountain bike aro 29, quadro de carbono");
	    bikeCaloi.setPrice(new BigDecimal("8999.90"));
	    bikeCaloi.setSupplier(supplierCaloi);
	    bikeCaloi.setStockQuantity(3);
	    productRepository.save(bikeCaloi);

	    Product groupShimano = new Product();
	    groupShimano.setName("Grupo Shimano Deore");
	    groupShimano.setDescription("Kit de transmissão 12v para MTB");
	    groupShimano.setPrice(new BigDecimal("2499.00"));
	    groupShimano.setSupplier(supplierShimano);
	    groupShimano.setStockQuantity(7);
	    productRepository.save(groupShimano);

	    Product capaceteSense = new Product();
	    capaceteSense.setName("Capacete Sense Trail");
	    capaceteSense.setDescription("Capacete leve e seguro para trilhas");
	    capaceteSense.setPrice(new BigDecimal("399.90"));
	    capaceteSense.setSupplier(supplierSense);
	    capaceteSense.setStockQuantity(5);
	    productRepository.save(capaceteSense);

	    productRepository.flush();
	}
}
