import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setCurrency(Currency.getInstance(Locale.US));

        int principal = (int) readNumber("Principal ($1k-1M): ", 1000, 1000000);
        double annualInterestRate = readNumber("Annual Interest Rate: ", 1,30);
        int loanTerm = (int) readNumber("Period (Years): ", 1,30);

        double mortgage = calculateMortgage(principal,annualInterestRate, loanTerm);
        String monthlyPayments = numberFormat.format(mortgage);

        System.out.println("Mortgage: " + monthlyPayments);
    }

    public static double calculateMortgage(int principal, double annualInterestRate, int loanTerm) {
        final int MONTHS = 12;
        final int PERCENT = 100;
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
}