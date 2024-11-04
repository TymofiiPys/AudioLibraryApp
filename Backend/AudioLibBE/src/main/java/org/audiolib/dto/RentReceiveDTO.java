package org.audiolib.dto;


import java.sql.Date;

public record RentReceiveDTO (Long id, String userId, Long audioCarrierId, Date dateRented, Date dateEndOfRent){
}
