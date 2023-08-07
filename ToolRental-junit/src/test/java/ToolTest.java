import Inventory.Tool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ToolTest {
    @Test
    @DisplayName("get tool from constructor")
    void Tool() {
        Tool sample = new Tool("JAKR", "Ridgid", "Jackhammer", new BigDecimal("2.99"), true, false, false);
        assertEquals("JAKR", sample.getToolCode());
        assertEquals("Ridgid", sample.getToolBrand());
        assertEquals("Jackhammer", sample.getToolType());
        assertEquals(new BigDecimal("2.99"), sample.getToolPrice());
        assertEquals(true, sample.getHasWeekdayCharge());
        assertEquals(false, sample.getHasWeekendCharge());
        assertEquals(false, sample.getHasHolidayCharge());
    }

    @Test
    @DisplayName("get tool code")
    void getToolCode() {
        Tool sample = new Tool("JAKR", "Ridgid", "Jackhammer", new BigDecimal("2.99"), true, false, false);
        assertEquals("JAKR", sample.getToolCode());
    }

    @Test
    @DisplayName("set tool code")
    void setToolCode() {
        Tool sample = new Tool();
        sample.setToolCode("JAKR");
        assertEquals("JAKR", sample.getToolCode());
    }

    @Test
    @DisplayName("get tool brand")
    void getToolBrand() {
        Tool sample = new Tool("JAKR", "Ridgid", "Jackhammer", new BigDecimal("2.99"), true, false, false);
        assertEquals("Ridgid", sample.getToolBrand());
    }

    @Test
    @DisplayName("set tool brand")
    void setToolBrand() {
        Tool sample = new Tool();
        sample.setToolBrand("Ridgid");
        assertEquals("Ridgid", sample.getToolBrand());
    }

    @Test
    @DisplayName("get tool type")
    void getToolType() {
        Tool sample = new Tool("JAKR", "Ridgid", "Jackhammer", new BigDecimal("2.99"), true, false, false);
        assertEquals("Jackhammer", sample.getToolType());
    }

    @Test
    @DisplayName("set tool type")
    void setToolType() {
        Tool sample = new Tool();
        sample.setToolType("Jackhammer");
        assertEquals("Jackhammer", sample.getToolType());
    }

    @Test
    @DisplayName("get tool price")
    void getToolPrice() {
        Tool sample = new Tool("JAKR", "Ridgid", "Jackhammer", new BigDecimal("2.99"), true, false, false);
        assertEquals(new BigDecimal("2.99"), sample.getToolPrice());
    }

    @Test
    @DisplayName("set tool price")
    void setToolPrice() {
        Tool sample = new Tool();
        sample.setToolPrice(new BigDecimal("2.99"));
        assertEquals(new BigDecimal("2.99"), sample.getToolPrice());
    }

    @Test
    @DisplayName("get tool weekday charge")
    void getHasWeekdayCharge() {
        Tool sample = new Tool("JAKR", "Ridgid", "Jackhammer", new BigDecimal("2.99"), true, false, false);
        assertEquals(true, sample.getHasWeekdayCharge());
    }

    @Test
    @DisplayName("set tool weekday charge")
    void setHasWeekdayCharge() {
        Tool sample = new Tool();
        sample.setHasWeekdayCharge(true);
        assertEquals(true, sample.getHasWeekdayCharge());
    }

    @Test
    @DisplayName("get tool weekend charge")
    void getHasWeekendCharge() {
        Tool sample = new Tool("JAKR", "Ridgid", "Jackhammer", new BigDecimal("2.99"), true, false, false);
        assertEquals(false, sample.getHasWeekendCharge());
    }

    @Test
    @DisplayName("set tool weekend charge")
    void setHasWeekendCharge() {
        Tool sample = new Tool();
        sample.setHasWeekendCharge(false);
        assertEquals(false, sample.getHasWeekendCharge());
    }

    @Test
    @DisplayName("get tool holiday charge")
    void getHasHolidayCharge() {
        Tool sample = new Tool("JAKR", "Ridgid", "Jackhammer", new BigDecimal("2.99"), true, false, false);
        assertEquals(false, sample.getHasHolidayCharge());
    }

    @Test
    @DisplayName("set tool holiday charge")
    void setHasHolidayCharge() {
        Tool sample = new Tool();
        sample.setHasHolidayCharge(false);
        assertEquals(false, sample.getHasHolidayCharge());
    }
}