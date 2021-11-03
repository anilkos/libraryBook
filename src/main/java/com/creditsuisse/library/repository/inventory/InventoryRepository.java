package com.creditsuisse.library.repository.inventory;

import com.creditsuisse.library.model.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}