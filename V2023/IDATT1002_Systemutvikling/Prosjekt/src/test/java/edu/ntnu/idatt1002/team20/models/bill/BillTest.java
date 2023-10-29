package edu.ntnu.idatt1002.team20.models.bill;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BillTest {

  @Test
  void testGetDueDate() {
    LocalDate dueDate = LocalDate.of(2023, 5, 1);
    Bill bill = new Bill(dueDate, "Electricity", 1, 100.0);
    assertEquals(dueDate, bill.getDueDate());
  }

  @Test
  void testGetCategory() {
    Bill bill = new Bill(LocalDate.of(2023, 5, 1), "Electricity", 1, 100.0);
    assertEquals("Electricity", bill.getCategory());
  }

  @Test
  void testGetBillId() {
    Bill bill = new Bill(LocalDate.of(2023, 5, 1), "Electricity", 1, 100.0);
    assertEquals(1, bill.getBillId());
  }

  @Test
  void testGetAmount() {
    Bill bill = new Bill(LocalDate.of(2023, 5, 1), "Electricity", 1, 100.0);
    assertEquals(100.0, bill.getAmount());
  }

  @Test
  void testSetAmount() {
    Bill bill = new Bill(LocalDate.of(2023, 5, 1), "Electricity", 1, 100.0);
    bill.setAmount(200.0);
    assertEquals(200.0, bill.getAmount());
  }

  @Test
  void testIsPaid() {
    Bill bill = new Bill(LocalDate.of(2023, 5, 1), "Electricity", 1, 100.0);
    assertEquals(false, bill.isPaid());
  }

  @Test
  void testSetPaid() {
    Bill bill = new Bill(LocalDate.of(2023, 5, 1), "Electricity", 1, 100.0);
    bill.setPaid(true);
    assertEquals(true, bill.isPaid());
  }

  @Test
  void testToString() {
    Bill bill = new Bill(LocalDate.of(2023, 5, 1), "Electricity", 1, 100.0);
    assertEquals("Bill{dueDate=2023-05-01, category='Electricity', amount=100.0}", bill.toString());
  }
}
