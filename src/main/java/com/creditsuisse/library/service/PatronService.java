package com.creditsuisse.library.service;


import com.creditsuisse.library.model.patron.Patron;

import java.util.List;
import java.util.Optional;

public interface PatronService {
    Patron createPatron(Patron patron);

    void deletePatron(int id);

    Patron updatePatron(Patron patron);

    List<Patron> getAllPatrons();

    Optional<Patron> getPatron(int id);

}
