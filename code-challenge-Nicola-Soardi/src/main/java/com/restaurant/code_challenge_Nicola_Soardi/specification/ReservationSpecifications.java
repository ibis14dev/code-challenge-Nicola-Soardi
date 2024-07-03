package com.restaurant.code_challenge_Nicola_Soardi.specification;
import com.restaurant.code_challenge_Nicola_Soardi.entity.Reservation;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;


public class ReservationSpecifications {

    public static Specification<Reservation> overlappingReservations(LocalDateTime start, LocalDateTime end) {
        LocalDateTime adjustedStart = start.minusHours(1);
        return (root, query, criteriaBuilder) -> {
            Predicate startsBeforeOrAtEnd = criteriaBuilder.lessThanOrEqualTo(root.get("reservationDate"), end);
            Predicate endsAfterOrAtStart = criteriaBuilder.greaterThanOrEqualTo(root.get("reservationDate"), adjustedStart);
            return criteriaBuilder.and(startsBeforeOrAtEnd, endsAfterOrAtStart);
        };
    }

}





