package Sales;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RentTest {
    @Test
    @DisplayName("initialize Tool Rental 1")
    void Rent() {
        Rent ProcessARental = new Rent("JAKR", "9/3/15", 101, 5);
        assertEquals("JAKR", ProcessARental.getToolCode());
        assertEquals("9/3/15", ProcessARental.getCheckoutDate());
        assertEquals(101, ProcessARental.getDiscountPercent());
        assertEquals(5, ProcessARental.getRentalDays());
    }

    @Test
    @DisplayName("initialize Tool Rental 2")
    void Rent2() {
        Rent ProcessARental = new Rent("LADW", "7/2/20", 10, 3);
        assertEquals("LADW", ProcessARental.getToolCode());
        assertEquals("7/2/20", ProcessARental.getCheckoutDate());
        assertEquals(10, ProcessARental.getDiscountPercent());
        assertEquals(3, ProcessARental.getRentalDays());
    }

    @Test
    @DisplayName("initialize Tool Rental 3")
    void Rent3() {
        Rent ProcessARental = new Rent("CHNS", "7/2/15", 25, 5);
        assertEquals("CHNS", ProcessARental.getToolCode());
        assertEquals("7/2/15", ProcessARental.getCheckoutDate());
        assertEquals(25, ProcessARental.getDiscountPercent());
        assertEquals(5, ProcessARental.getRentalDays());
    }

    @Test
    @DisplayName("initialize Tool Rental 4")
    void Rent4() {
        Rent ProcessARental = new Rent("JAKD", "9/3/15", 0, 6);
        assertEquals("JAKD", ProcessARental.getToolCode());
        assertEquals("9/3/15", ProcessARental.getCheckoutDate());
        assertEquals(0, ProcessARental.getDiscountPercent());
        assertEquals(6, ProcessARental.getRentalDays());
    }

    @Test
    @DisplayName("initialize Tool Rental 5")
    void Rent5() {
        Rent ProcessARental = new Rent("JAKR", "7/2/15", 0, 9);
        assertEquals("JAKR", ProcessARental.getToolCode());
        assertEquals("7/2/15", ProcessARental.getCheckoutDate());
        assertEquals(0, ProcessARental.getDiscountPercent());
        assertEquals(9, ProcessARental.getRentalDays());
    }

    @Test
    @DisplayName("initialize Tool Rental 6")
    void Rent6() {
        Rent ProcessARental = new Rent("JAKR", "7/2/20", 50, 4);
        assertEquals("JAKR", ProcessARental.getToolCode());
        assertEquals("7/2/20", ProcessARental.getCheckoutDate());
        assertEquals(50, ProcessARental.getDiscountPercent());
        assertEquals(4, ProcessARental.getRentalDays());
    }
}