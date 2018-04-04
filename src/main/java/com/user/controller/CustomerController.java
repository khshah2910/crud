package com.user.controller;

import com.user.model.Customer;
import com.user.repository.CustomerMangoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerMangoRepositoryImpl customerMangoRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        System.out.println("Getting Customers!");
        return customerMangoRepository.findAll();
    }
//    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
//    public  Customer getCustomeById(@PathVariable("id") String id ){
//        return customerMangoRepository.findOne(id);
//    }

    @PostMapping("/customers/create")
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        customer.setActive(false);
        return customerMangoRepository.save(customer);
    }

    @PutMapping("/customers/find/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer){
        Customer customerData = customerMangoRepository.findOne(id);
        if(customer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerData.setName(customer.getName());
        customerData.setAge(customer.getAge());
        customerData.setActive(customer.isActive());

        Customer updateCustomer = customerMangoRepository.save(customerData);
        return  new ResponseEntity<>(updateCustomer,HttpStatus.OK);
    }

    @DeleteMapping("/customers/delete/{id}")
    public  ResponseEntity<String> deleteCustomer(@PathVariable("id") String id){
        if(customerMangoRepository.findOne(id) == null){
            new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        }

        customerMangoRepository.delete(id);
        return new ResponseEntity<> ("Deleted Successfully", HttpStatus.OK);
    }


    @DeleteMapping("/customers/delete")
    public ResponseEntity<String> deleteAllCustomers() {
        System.out.println("Delete All Customers...");

        customerMangoRepository.deleteAll();

        return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
    }


}
