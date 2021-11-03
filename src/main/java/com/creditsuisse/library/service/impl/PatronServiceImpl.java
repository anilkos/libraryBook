package com.creditsuisse.library.service.impl;

import com.creditsuisse.library.model.patron.Patron;
import com.creditsuisse.library.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.creditsuisse.library.repository.patron.PatronRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository PatronRepository;

    @Override
    public Patron createPatron(Patron patron) {
        return PatronRepository.save(patron);
    }

    @Override
    public void deletePatron(int id) {
        PatronRepository.deleteById(id);
    }

    @Override
    public Patron updatePatron(Patron patron) {
        return PatronRepository.save(patron);
    }

    @Override
    public List<Patron> getAllPatrons() {
        return PatronRepository.findAll();
    }

    @Override
    public Optional<Patron> getPatron(int id) {
        return PatronRepository.findById(id);
    }
}
