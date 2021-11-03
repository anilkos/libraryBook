package com.creditsuisse.library.repository.inventory;

import lombok.Builder;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@Embeddable
public class InventoryPK implements Serializable {

    private int patronId;
    private int bookId;

    public InventoryPK() {
    }

    public InventoryPK(int patronId, int bookId) {
        this.patronId = patronId;
        this.bookId = bookId;
    }

    public int getPatronId() {
        return patronId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "InventoryPK{" +
                "patronId=" + patronId +
                ", bookId=" + bookId +
                '}';
    }
}
