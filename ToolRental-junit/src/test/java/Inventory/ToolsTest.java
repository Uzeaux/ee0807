package Inventory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ToolsTest {

    @Test
    @DisplayName("initialize toolset from constructor")
    void Tools() {
        Tools toolset = new Tools("JAKR");
        assertEquals("JAKR", toolset.getTools().get(0).getToolCode());
    }

    @Test
    @DisplayName("add a tool")
    void addTool() {
        Tools toolset = new Tools("JAKR");
        assertEquals(1, toolset.getTools().size());
    }

    @Test
    @DisplayName("clear tools")
    void resetTools() {
        Tools toolset = new Tools("JAKR");
        toolset.resetTools();
        assertEquals(0, toolset.getTools().size());
    }

    @Test
    @DisplayName("get tools")
    void getTools() {
        Tools toolset = new Tools("JAKR");
        toolset.addTool("LADW");
        assertEquals(2, toolset.getTools().size());
    }
}