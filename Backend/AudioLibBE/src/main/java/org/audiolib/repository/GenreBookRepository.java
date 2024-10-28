package org.audiolib.repository;

import org.audiolib.entity.GenreBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreBookRepository extends JpaRepository<GenreBooks, Integer> {}
