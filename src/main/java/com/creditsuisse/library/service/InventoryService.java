package com.creditsuisse.library.service;

import com.creditsuisse.library.model.inventory.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory createInventory(Inventory inventory);

    Inventory updateInventory(Inventory inventory);

    List<Inventory> getAllInventory();

    long count();


}
