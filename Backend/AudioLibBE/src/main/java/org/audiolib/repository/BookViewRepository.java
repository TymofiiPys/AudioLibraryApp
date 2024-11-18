package org.audiolib.repository;

import org.audiolib.entity.BookView;
import org.audiolib.entity.SongView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookViewRepository extends JpaRepository<BookView, Long> {
}
