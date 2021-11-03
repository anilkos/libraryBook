package com.creditsuisse.library.controller;

import com.creditsuisse.library.model.inventory.Inventory;
import com.creditsuisse.library.repository.inventory.InventoryPK;
import com.creditsuisse.library.service.InventoryService;
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
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventories")
    public ResponseEntity<List<Inventory>> inventoryList() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @PostMapping("/inventory")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        try {
            Inventory inventoryResponse = inventoryService.createInventory(inventory);
            return new ResponseEntity<>(inventoryResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/inventory/return")
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory) {

        List<Inventory> inventories = inventoryService.getAllInventory();
        Optional<Inventory> inventoryResponse = inventories.stream().
                filter(inv -> inv.getInventoryPK().getBookId() == inventory.getInventoryPK().getBookId() &&
                inv.getInventoryPK().getPatronId() == inventory.getInventoryPK().getPatronId()).findAny();

        if (inventoryResponse.isPresent()) {
            Inventory inventoryReturn = inventoryResponse.get();

            inventoryReturn.setInventoryPK(InventoryPK.builder().bookId(inventory.getInventoryPK().getBookId()).patronId(inventory.getInventoryPK().getBookId()).build());
            inventoryReturn.setReturned(!inventoryResponse.get().isReturned());
            return new ResponseEntity<>(inventoryService.updateInventory(inventoryReturn), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/inventory/borrow")
    public ResponseEntity<Inventory> borrowInventory(@RequestBody Inventory inventory) {

        try {
            inventory.setReturned(false);
            Inventory inventoryResponse = inventoryService.createInventory(inventory);
            return new ResponseEntity<>(inventoryResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/inventories/borrowed")
    public ResponseEntity<Long> borrowedBooks() {
        List<Inventory> inventories = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventories.stream().filter(Inventory::isReturned).count());
    }
}
