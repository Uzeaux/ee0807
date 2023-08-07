package Inventory;

import java.io.Serializable;
import java.math.BigDecimal;

public class Tool implements Serializable {

    private String ToolCode;
    private String ToolBrand;
    private String ToolType;
    private BigDecimal ToolPrice;
    private Boolean HasWeekdayCharge;
    private Boolean HasWeekendCharge;
    private Boolean HasHolidayCharge;

    public Tool() {
    }
    //constructor and getters/setters
    public Tool(
            String ToolCode,
            String ToolBrand,
            String ToolType,
            BigDecimal ToolPrice,
            Boolean HasWeekdayCharge,
            Boolean HasWeekendCharge,
            Boolean HasHolidayCharge
    ) {
        this.ToolCode = ToolCode;
        this.ToolBrand = ToolBrand;
        this.ToolType = ToolType;
        this.ToolPrice = ToolPrice;
        this.HasWeekdayCharge = HasWeekdayCharge;
        this.HasWeekendCharge = HasWeekendCharge;
        this.HasHolidayCharge = HasHolidayCharge;
    }

    public String getToolCode() {
        return ToolCode;
    }

    public void setToolCode(String ToolCode) {
        this.ToolCode = ToolCode;
    }
    public String getToolBrand() {
        return ToolBrand;
    }

    public void setToolBrand(String ToolBrand) {
        this.ToolBrand = ToolBrand;
    }
    public String getToolType() {
        return ToolType;
    }

    public void setToolType(String ToolType) {
        this.ToolType = ToolType;
    }
    public BigDecimal getToolPrice() {
        return ToolPrice;
    }

    public void setToolPrice(BigDecimal ToolPrice) {
        this.ToolPrice = ToolPrice;
    }
    public Boolean getHasWeekdayCharge() {
        return HasWeekdayCharge;
    }

    public void setHasWeekdayCharge(Boolean HasWeekdayCharge) {
        this.HasWeekdayCharge = HasWeekdayCharge;
    }
    public Boolean getHasWeekendCharge() {
        return HasWeekendCharge;
    }

    public void setHasWeekendCharge(Boolean HasWeekendCharge) {
        this.HasWeekendCharge = HasWeekendCharge;
    }
    public Boolean getHasHolidayCharge() {
        return HasHolidayCharge;
    }

    public void setHasHolidayCharge(Boolean HasHolidayCharge) {
        this.HasHolidayCharge = HasHolidayCharge;
    }

}