package org.audiolib.dto;

import java.sql.Date;

public record GenreStatsDTO (Integer genreId, Boolean isBook, Long activeRents, Date date){
}
