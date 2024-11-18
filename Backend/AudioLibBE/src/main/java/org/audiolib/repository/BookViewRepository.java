package org.audiolib.repository;

import org.audiolib.entity.BookView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookViewRepository extends JpaRepository<BookView, Long> {
    List<BookView> findByNameStartingWith(String name);
}
