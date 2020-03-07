package com.ibocon.cashflowbook;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Customer, Long> {

}
