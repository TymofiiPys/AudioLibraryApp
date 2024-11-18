package org.audiolib.repository;

import org.audiolib.entity.BookListens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookListensRepository extends JpaRepository<BookListens, Long> {
}
