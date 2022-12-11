import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    final static int MONTHS = 12;
    final static int PERCENT = 100;
    public static void main(String[] args) {
        int principal = (int) readNumber("Principal ($1k-1M): ", 1000, 1000000);
        double annualInterestRate = readNumber("Annual Interest Rate: ", 1,30);
        int loanTerm = (int) readNumber("Period (Years): ", 1,30);

        printMonthlyPayment(principal, annualInterestRate, loanTerm);
        printPaymentSchedule(principal, annualInterestRate, loanTerm);

    }

    private static void printMonthlyPayment(int principal, double annualInterestRate, int loanTerm) {
        double mortgage = calculateMortgage(principal, annualInterestRate, loanTerm);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        String monthlyPayments = numberFormat.format(mortgage);
        numberFormat.setCurrency(Currency.getInstance(Locale.US));
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + monthlyPayments);
    }

    private static void printPaymentSchedule(int principal, double annualInterestRate, int loanTerm) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("-----------------");
        for (int month = 1; month <= loanTerm * MONTHS; month++) {

            double balance = calculateBalance(principal, annualInterestRate, loanTerm, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double calculateMortgage(int principal, double annualInterestRate, int loanTerm) {
        double monthlyInterestRate = annualInterestRate/PERCENT/MONTHS;
        loanTerm = loanTerm * MONTHS;
        return principal * (monthlyInterestRate * Math.pow((1 + monthlyInterestRate), loanTerm))
                /(Math.pow((1 + monthlyInterestRate), loanTerm) - 1);
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();

            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Please enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateBalance(int principal, double annualInterestRate, int loanTerm, int numOfPaymentsMade) {
        double monthlyInterestRate = annualInterestRate/PERCENT/MONTHS;
        int numberOfPayments = loanTerm * MONTHS;

        return principal * (Math.pow(1 + monthlyInterestRate, numberOfPayments)
                - Math.pow(1 + monthlyInterestRate, numOfPaymentsMade))
                /(Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
        
    }
}