package Sales;

import Inventory.Tool;
import Inventory.Tools;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class Rent {
    private String ToolCode;
    private String CheckoutDate;
    protected String DueDate;
    private int DiscountPercent;
    private int RentalDays;

    public Rent() {
    }
    //constructor and getters/setters
    public Rent(
            String ToolCode,
            String CheckoutDate,
            int DiscountPercent,
            int RentalDays
    ) {
        setToolCode(ToolCode);
        setCheckoutDate(CheckoutDate);
        setDiscountPercent(DiscountPercent);
        setRentalDays(RentalDays);
    }
    public String getToolCode() {
        return ToolCode;
    }

    public void setToolCode(String ToolCode) {
        this.ToolCode = ToolCode;
    }
    public String getCheckoutDate() {
        return CheckoutDate;
    }

    public void setCheckoutDate(String CheckoutDate) {
        if ((CheckoutDate == null) ||
                (!(CheckoutDate.matches("\\d{1,2}-\\d{1,2}-\\d{2}(?:\\d{2})?")) && !(CheckoutDate.matches("\\d{1,2}/\\d{1,2}/\\d{2}(?:\\d{2})?")))
        ) {
            return;
        }

        this.CheckoutDate = CheckoutDate;
    }
    public int getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(int DiscountPercent) {
        if (DiscountPercent < 1 || DiscountPercent > 100) {
            this.DiscountPercent = -1;
            return;
        }
        this.DiscountPercent = DiscountPercent;
    }

    public int getRentalDays() {
        return RentalDays;
    }

    public void setRentalDays(int RentalDays) {
        if (RentalDays < 1) {
            this.RentalDays = -1;
            return;
        }
        this.RentalDays = RentalDays;
    }
    public void ProcessRental() throws Exception {

        try {
            Tools RentTools = new Tools(ToolCode);
            ArrayList<Tool> RentToolList = RentTools.getTools();

            DisplayRentalCertificate(RentToolList);
        } catch (Exception e){
            System.out.println("We could not find that tool");
        }
    }

    public Long CalculateChargeableDaysOfRental(Tool RentTool) throws Exception {
        List<LocalDate> Holidays = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/y");
        if (this.CheckoutDate.matches("\\d{1,2}-\\d{1,2}-\\d{2}(?:\\d{2})?")) {
            formatter = DateTimeFormatter.ofPattern("M-d-y");
        }
        LocalDate CheckoutDate = LocalDate.parse(this.CheckoutDate, formatter);
        LocalDate now = LocalDate.now();

        LocalDate DueDate = CheckoutDate.plusDays(RentalDays);
        this.DueDate = DueDate.format(formatter);

        //online research on use of predicates for special time intervals
        Holidays.add(LocalDate.of(CheckoutDate.getYear(), Month.JULY, 4));
        Holidays.add(LocalDate.of(DueDate.getYear(), Month.JULY, 4));
        Holidays.add(LocalDate.of(CheckoutDate.getYear(), Month.SEPTEMBER, CheckoutDate.with(firstInMonth(DayOfWeek.MONDAY)).getDayOfMonth()));
        Holidays.add(LocalDate.of(DueDate.getYear(), Month.SEPTEMBER, now.with(firstInMonth(DayOfWeek.MONDAY)).getDayOfMonth()));

        Predicate<LocalDate> IsHoliday = Holidays::contains;
        Predicate<LocalDate> IsWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long AllDaysBetween = ChronoUnit.DAYS.between(CheckoutDate, DueDate);
        List<LocalDate> RentalDays = Stream.iterate(CheckoutDate, date -> date.plusDays(1))
                .limit(AllDaysBetween)
                .toList();

        //utilized filters to toggle switch cases
        if (!RentTool.getHasWeekdayCharge()) {
            RentalDays = RentalDays.stream().filter(IsWeekend).toList();
        }
        if (!RentTool.getHasHolidayCharge()) {
            RentalDays = RentalDays.stream().filter(IsHoliday.negate()).toList();
        }
        if (!RentTool.getHasWeekendCharge()) {
            RentalDays = RentalDays.stream().filter(IsWeekend.negate()).toList();
        }

        return (long) RentalDays.size();
    }
    public ArrayList<String> CalculateCostOfRental(long RentalDays, int Discount, BigDecimal Price) throws Exception {
        BigDecimal TotalDays = new BigDecimal(RentalDays);

        double DiscountPercent = (double) Discount/100;
        BigDecimal DiscountDecimal = new BigDecimal(DiscountPercent);

        BigDecimal Total = Price.multiply(TotalDays);
        BigDecimal DiscountAmount = Total.multiply(DiscountDecimal);


        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        ArrayList<String> Costs = new ArrayList<>();

        Costs.add(n.format(DiscountAmount.doubleValue()));
        Costs.add(n.format(Total.doubleValue()));
        Costs.add(n.format(Total.subtract(DiscountAmount).doubleValue()));

        return Costs;
    }

    public void DisplayRentalCertificate (ArrayList<Tool> RentToolList) throws Exception {
        for (Tool RentTool : RentToolList) {
            Long ChargeableDaysOfRental = CalculateChargeableDaysOfRental(RentTool);
            ArrayList<String> CostsOfRental = CalculateCostOfRental(ChargeableDaysOfRental, DiscountPercent, RentTool.getToolPrice());

            String LeftAlignFormat = "| %-15s | %-11s | %-11s | %-11s | %-11s |%n";

            System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
            System.out.format("|        ***   AMAZING  TOOL  RENTAL  SERVICES  EXPENSE  SHEET   ***      |%n");
            System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
            System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
            System.out.format("|  Tool                         |  Tool       |   Tool      |Daily  Rental|%n");
            System.out.format("|  type                         |  code       |   brand     |   charge    |%n");
            System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
            System.out.format(LeftAlignFormat, RentTool.getToolType(), "", RentTool.getToolCode(), RentTool.getToolBrand(), RentTool.getToolPrice());
            System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
            System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
            System.out.format("|                 | Rental days |Checkout date| Charge days |  Due date   |%n");
            System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
            System.out.format(LeftAlignFormat, "", RentalDays, this.CheckoutDate, ChargeableDaysOfRental, this.DueDate);
            System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
            System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
            System.out.format(LeftAlignFormat, "", "Pre", "", "Discount", "");
            System.out.format(LeftAlignFormat, "", "discount", CostsOfRental.get(1), "amount", CostsOfRental.get(0));
            System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
            System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
            System.out.format("|                                                           ______________|%n");
            LeftAlignFormat = "| %-56s | %-12s |%n";
            System.out.format(LeftAlignFormat, "WooHoo! You saved " +  DiscountPercent + "% on your rental!", "Final Charge");
            LeftAlignFormat = "| %-15s   %-11s   %-11s   %-10s | %-12s |%n";
            System.out.format(LeftAlignFormat, "", "", "", "", CostsOfRental.get(2));
            System.out.format("+-----------------+-------------+-------------+------------+--------------+%n");
        }
    }
}