import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int principal;
        double annualInterestRate;
        int loanTerm;
        String monthlyPayments;

        Scanner scanner = new Scanner(System.in);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setCurrency(Currency.getInstance(Locale.US));


        while (true) {
            System.out.print("Principal ($1k-1M): ");
            principal = scanner.nextInt();

            if (principal >= 1000 && principal <= 1_000_000) {
                break;
            }
            System.out.println("Please enter a value between 1,000 and 1,000,000");
        }

        while (true) {
            System.out.print("Annual Interest Rate: ");
            annualInterestRate = scanner.nextDouble();
            if (annualInterestRate >=1 && annualInterestRate <= 30) {
                break;
            }
            System.out.println("Enter Correct Annual Interest Rate");
        }

        while (true) {
            System.out.print("Period (Years): ");
            loanTerm = scanner.nextInt();

            if (loanTerm >= 1 && loanTerm <= 30) {
                break;
            }
            System.out.println("Enter the correct years");

        }



        double mortgage = calculateMortgage(principal,annualInterestRate, loanTerm);
        monthlyPayments = numberFormat.format(mortgage);

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


}