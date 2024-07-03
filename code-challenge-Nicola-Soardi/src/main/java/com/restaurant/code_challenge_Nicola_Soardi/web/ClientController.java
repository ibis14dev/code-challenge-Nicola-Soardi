package com.restaurant.code_challenge_Nicola_Soardi.web;

import com.restaurant.code_challenge_Nicola_Soardi.entity.Client;
import com.restaurant.code_challenge_Nicola_Soardi.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    ClientService clientService;

    @Operation(summary = "Retrieve user", description = "Provides the user given an id")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of user", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Client.class))))
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);
    }

    @Operation(summary = "Create User", description = "Creates a user from the provided payload")
    @ApiResponse(responseCode = "201", description = "Successful creation of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of user"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission")
    })
    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.saveClient(client), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete user", description = "Delete user given an id")
    @ApiResponse(responseCode = "204", description = "Successful deleted of user")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
