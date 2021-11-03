package com.creditsuisse.library.controller;

import com.creditsuisse.library.model.patron.Patron;
import com.creditsuisse.library.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping("/patrons")
    public ResponseEntity<List<Patron>> patronsList() {
        return ResponseEntity.ok(patronService.getAllPatrons());
    }

    @GetMapping("/patrons/{id}")
    public ResponseEntity<Patron> singlePatron(@PathVariable("id") int id) {
        return ResponseEntity.ok(patronService.getPatron(id).get());
    }

    @PostMapping("/patrons")
    public ResponseEntity<Patron> createPatron(@RequestBody Patron patron) {
        try {
            Patron patronResponse = patronService.createPatron(patron);
            return new ResponseEntity<>(patronResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/patrons/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable("id") int id, @RequestBody Patron patron) {
        Optional<Patron> patronResponse = patronService.getPatron(id);

        if (patronResponse.isPresent()) {
            Patron patronReturn = patronResponse.get();

            patronReturn.setDateOfBirth(patron.getDateOfBirth());
            patronReturn.setName(patron.getName());
            patronReturn.setPhoneNumber(patron.getPhoneNumber());
            return new ResponseEntity<>(patronService.updatePatron(patronReturn), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/patrons/{id}")
    public ResponseEntity<HttpStatus> deletePatronById(@PathVariable("id") int id) {
        try {
            patronService.deletePatron(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
