package edu.ntnu.idatt1002.team20.models.bill;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BillRegisterTest {

  private BillRegister billRegister;
  private Bill bill1;
  private Bill bill2;

  @BeforeEach
  void setUp() {
    billRegister = new BillRegister();
    bill1 = new Bill(LocalDate.of(2025, 6, 27), "Electricity", 1, 100.0);
    bill2 = new Bill(LocalDate.of(2025, 4, 28), "Water", 2, 50.0);
  }

  @Test
  void testAddBill() {
    billRegister.addBill(bill1);
    List<Bill> expectedBills = List.of(bill1);
    assertEquals(expectedBills, billRegister.getBills());
  }

  @Test
  void testAddBills() {
    List<Bill> bills = new ArrayList<>();
    bills.add(bill1);
    bills.add(bill2);
    billRegister.addBills(bills);
    List<Bill> expectedBills = List.of(bill1, bill2);
    assertEquals(expectedBills, billRegister.getBills());
  }

  @Test
  void testRemoveBill() {
    billRegister.addBill(bill1);
    billRegister.addBill(bill2);
    billRegister.removeBill(bill1);
    List<Bill> expectedBills = List.of(bill2);
    assertEquals(expectedBills, billRegister.getBills());
  }

  @Test
  void testGetBills() {
    List<Bill> expectedBills = new ArrayList<>();
    assertEquals(expectedBills, billRegister.getBills());
  }

  @Test
  void testGetUpcomingBills() {
    billRegister.addBill(bill1);
    billRegister.addBill(bill2);
    List<Bill> expectedBills = List.of(bill1, bill2);
    assertEquals(expectedBills, billRegister.getUpcomingBills());
  }

  @Test
  void testGetNextDueDateWithNoBills() {
    assertEquals("", billRegister.getNextDueDate());
  }

  @Test
  void testGetTotalAmount() {
    billRegister.addBill(bill1);
    billRegister.addBill(bill2);
    assertEquals(150.0, billRegister.getTotalAmount());
  }

  @Test
  void testClear() {
    billRegister.addBill(bill1);
    billRegister.addBill(bill2);
    billRegister.clear();
    List<Bill> expectedBills = new ArrayList<>();
    assertEquals(expectedBills, billRegister.getBills());
    assertEquals(0.0, billRegister.getTotalAmount());
  }

}
