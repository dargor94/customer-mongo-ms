package org.dargor.customer.core.repository;

import org.dargor.customer.core.entity.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, UUID> {
}
