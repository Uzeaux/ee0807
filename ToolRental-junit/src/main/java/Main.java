import Inventory.Tool;
import Inventory.Tools;
import Sales.Rent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String LeftAlignFormat = "| %-15s | %-11s | %-7s |%n";

        //online research on generating tables in console
        System.out.format("+-----------------+-------------+---------+%n");
        System.out.format("|  Tool Code      |   Type      |  Brand  |%n");
        System.out.format("+-----------------+-------------+---------+%n");
        System.out.format(LeftAlignFormat, "CHNS", "Chainsaw", "Stihl");
        System.out.format(LeftAlignFormat, "LADW", "Ladder", "Werner");
        System.out.format(LeftAlignFormat, "JAKD", "Jackhammer", "DeWalt");
        System.out.format(LeftAlignFormat, "JAKR", "Jackhammer", "Ridgid");
        System.out.format("+-----------------+-------------+---------+%n");

        Scanner scn = new Scanner(System.in);

        System.out.println("Enter Tool Code: ");
        String ToolCode = scn.nextLine();

        System.out.println("How many days would you like to rent for?: ");
        int RentalDays = scn.nextInt();

        //exception with message on fail case
        if (RentalDays < 1) {
            throw new Exception("Sorry, you have selected less than the minimum number of rental days (1). Please try again.");
        }

        LeftAlignFormat = "| %-15s | %-11s | %-11s | %-11s | %-11s |%n";

        System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
        System.out.format("|                     Daily     |  Weekday    |   Weekend   |   Holiday   |%n");
        System.out.format("|                     charge    |  charge     |   charge    |   charge    |%n");
        System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");
        System.out.format(LeftAlignFormat, "Ladder", "$1.99", "Yes", "Yes", "No");
        System.out.format(LeftAlignFormat, "Chainsaw", "$1.49", "Yes", "No", "Yes");
        System.out.format(LeftAlignFormat, "Jackhammer", "$2.99", "Yes", "No", "No");
        System.out.format("+-----------------+-------------+-------------+-------------+-------------+%n");

        System.out.println("What percentage discount is being offered? (Enter a whole number ex: 75, 100): ");
        int DiscountPercent = scn.nextInt();
        scn.nextLine();

        if (DiscountPercent < 0 || DiscountPercent > 100) {
            throw new Exception("Sorry this level discount is not currently supported, please choose a discount within range (1-100)");
        }

        String CheckoutDate;
        do{
            System.out.println("Please enter the return date in the format (mm/dd/yyyy): ");
            CheckoutDate = scn.nextLine();
        }while(!DateIsValid(CheckoutDate));

        Rent ProcessARental = new Rent(ToolCode, CheckoutDate, DiscountPercent, RentalDays);
        ProcessARental.ProcessRental();
    }

    private static final ThreadLocal<SimpleDateFormat> format = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat DateFormat = new SimpleDateFormat("M/d/y");
            DateFormat.setLenient(false);
//            System.out.println("created");
            return DateFormat;
        }
    };

    private static final ThreadLocal<SimpleDateFormat> format2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat DateFormat = new SimpleDateFormat("M-d-y");
            DateFormat.setLenient(false);
//            System.out.println("created");
            return DateFormat;
        }
    };

    public static boolean DateIsValid(String DateString) {
        if (DateString == null)
            return false;
        try {
            if (DateString.matches("\\d{1,2}/\\d{1,2}/\\d{2}(?:\\d{2})?")) {
                format.get().parse(DateString);
            } else if (DateString.matches("\\d{1,2}-\\d{1,2}-\\d{2}(?:\\d{2})?")) {
                format2.get().parse(DateString);
            } else return false;

            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
}
