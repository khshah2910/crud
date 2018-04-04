package com.user.repository;

import com.user.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CustomerMangoRepositoryImpl extends MongoRepository<Customer, String> {
}
