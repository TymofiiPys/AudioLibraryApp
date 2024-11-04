package org.audiolib.repository;

import org.audiolib.entity.Transact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactRepository extends JpaRepository<Transact, Long> {
}
