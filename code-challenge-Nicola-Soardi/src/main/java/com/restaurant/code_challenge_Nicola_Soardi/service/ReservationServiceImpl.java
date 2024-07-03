package com.restaurant.code_challenge_Nicola_Soardi.service;

import com.restaurant.code_challenge_Nicola_Soardi.entity.Client;
import com.restaurant.code_challenge_Nicola_Soardi.entity.Reservation;
import com.restaurant.code_challenge_Nicola_Soardi.exception.ClientNotFoundException;
import com.restaurant.code_challenge_Nicola_Soardi.exception.InvalideReservationTimeException;
import com.restaurant.code_challenge_Nicola_Soardi.exception.ReservationConflictException;
import com.restaurant.code_challenge_Nicola_Soardi.exception.ReservationFullBookException;
import com.restaurant.code_challenge_Nicola_Soardi.repository.ClientRepository;
import com.restaurant.code_challenge_Nicola_Soardi.repository.ReservationRepository;
import com.restaurant.code_challenge_Nicola_Soardi.specification.ReservationSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{

    ReservationRepository reservationRepository;
    ClientRepository clientRepository;

    private static final LocalTime START_TIME = LocalTime.of(19, 0);
    private static final LocalTime END_TIME = LocalTime.of(23, 0);

    @Override
    public Reservation saveReservation(Reservation reservation,Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        reservation.setClient(client);

        if (!isReservationTimeValid(reservation)) {
            throw new InvalideReservationTimeException(reservation);
        }

        if (isReservationFullBooked(reservation)) {
            throw new ReservationFullBookException(reservation);
        }

        try {
            return reservationRepository.save(reservation);
        } catch (DataIntegrityViolationException e) {
            throw new ReservationConflictException(reservation);
        }
    }

    @Override
    public Page<Reservation> getReservationsByStartDateandEndDate(LocalDateTime startdate, LocalDateTime enddate, Pageable pageable) {
        return reservationRepository.findAll(ReservationSpecifications.overlappingReservations(startdate, enddate), pageable);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    private boolean isReservationTimeValid(Reservation reservation) {
        LocalDateTime reservationTime = reservation.getReservationDate();
        LocalTime reservationHour = reservationTime.toLocalTime();
        return reservationHour.isAfter(START_TIME) && reservationHour.isBefore(END_TIME);
    }

    private boolean isReservationFullBooked(Reservation reservation) {
        LocalDateTime reservationStart = reservation.getReservationDate();
        LocalDateTime reservationEnd = reservationStart.plusHours(1);
        List<Reservation> overlappingReservations = reservationRepository.findAll(ReservationSpecifications.overlappingReservations(reservationStart, reservationEnd));

        return overlappingReservations.size() >= 5;
    }
}
