package com.restaurant.code_challenge_Nicola_Soardi.web;

import com.restaurant.code_challenge_Nicola_Soardi.entity.Client;
import com.restaurant.code_challenge_Nicola_Soardi.entity.Reservation;
import com.restaurant.code_challenge_Nicola_Soardi.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    ReservationService reservationService;

    @Operation(summary = "Retrieve reservations", description = "Provides the reservations given a start date and end date")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of reservations", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Reservation.class))))
    @GetMapping("/{startdate}/{enddate}")
    public ResponseEntity<Page<Reservation>> getReservationsByClient(
            @PathVariable LocalDateTime startdate,
            @PathVariable LocalDateTime enddate,
            @PageableDefault(sort = {"reservationDate", "id"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable) {

        Page<Reservation> reservations = reservationService.getReservationsByStartDateandEndDate(startdate, enddate, pageable);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @Operation(summary = "Create Reservation", description = "Creates a reservation from the provided payload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of reservation"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission")
    })
    @PostMapping("/client/{clientId}")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation, @PathVariable Long clientId) {
        return new ResponseEntity<>(reservationService.saveReservation(reservation,clientId), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete reservation", description = "Delete reservation given an id")
    @ApiResponse(responseCode = "204", description = "Successful deleted of reservation")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
