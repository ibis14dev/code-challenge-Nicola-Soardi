package com.restaurant.code_challenge_Nicola_Soardi.service;

import com.restaurant.code_challenge_Nicola_Soardi.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;


public interface ReservationService {

    Reservation saveReservation(Reservation reservation,Long clientId);
    Page<Reservation> getReservationsByStartDateandEndDate(LocalDateTime startdate, LocalDateTime enddate, Pageable pageable);
    void deleteReservation(Long id);
}
