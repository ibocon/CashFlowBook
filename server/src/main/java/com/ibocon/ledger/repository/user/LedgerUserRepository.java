package com.ibocon.ledger.repository.user;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface LedgerUserRepository extends CrudRepository<LedgerUser, Long> {
    Optional<LedgerUser> findByEmail(String email);
    Boolean existsByEmail(String email);
}