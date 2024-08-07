package com.restaurant.code_challenge_Nicola_Soardi.repository;

import com.restaurant.code_challenge_Nicola_Soardi.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {
}

