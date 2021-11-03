package com.creditsuisse.library.model.inventory;

import com.creditsuisse.library.repository.inventory.InventoryPK;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private InventoryPK inventoryPK;

    private boolean isReturned;

    public InventoryPK getInventoryPK() {
        return inventoryPK;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setInventoryPK(InventoryPK inventoryPK) {
        this.inventoryPK = inventoryPK;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryPK=" + inventoryPK +
                ", isReturned=" + isReturned +
                '}';
    }
}