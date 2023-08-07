package Inventory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Tools implements Serializable {

    private Tool tool;
    private ArrayList<Tool> tools = new ArrayList<>();
    public Tools() {
    }
    //constructor with persisting arrayList
    public Tools(String ToolCode) {
        if (ToolCode != null){
            addTool(ToolCode);
        }
    }
    public void addTool(String ToolCode) {
        Boolean HasWeekdayCharge = true;
        String ToolBrand = "";
        String ToolType = "";
        BigDecimal ToolPrice = new BigDecimal(0);
        boolean HasWeekendCharge = false, HasHolidayCharge = false;

        int day = 4;
        switch (ToolCode.toLowerCase().charAt(3)) {
            case 's' -> ToolBrand = "Stihl";
            case 'w' -> ToolBrand = "Werner";
            case 'd' -> ToolBrand = "DeWalt";
            case 'r' -> ToolBrand = "Ridgid";
        }

        if (ToolCode.toLowerCase().contains("chn")) {
            ToolType = "Chainsaw";
            ToolPrice = new BigDecimal("1.49");
            HasWeekendCharge = false;
            HasHolidayCharge = true;
        } else if (ToolCode.toLowerCase().contains("lad")) {
            ToolType = "Ladder";
            ToolPrice = new BigDecimal("1.99");
            HasWeekendCharge = true;
            HasHolidayCharge = false;
        } else if (ToolCode.toLowerCase().contains("jak")) {
            ToolType = "Jackhammer";
            ToolPrice = new BigDecimal("2.99");
            HasWeekendCharge = false;
            HasHolidayCharge = false;
        } else return;

        this.tools.add(new Tool(ToolCode, ToolBrand, ToolType, ToolPrice, HasWeekdayCharge, HasWeekendCharge, HasHolidayCharge));
    }
    public void resetTools() {
        if (this.tools.size() > 0) {
            this.tools.clear();
        }
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }
}

