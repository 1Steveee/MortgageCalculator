import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int MONTHS = 12;
        final int PERCENT = 100;
        Scanner scanner = new Scanner(System.in);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setCurrency(Currency.getInstance(Locale.US));

        System.out.print("Principal: ");
        int principal = scanner.nextInt();

        System.out.print("Annual Interest Rate: ");
        double annualInterestRate = scanner.nextDouble();
        double monthlyInterestRate = annualInterestRate/PERCENT/MONTHS;

        System.out.print("Period (Years): ");
        int loanTerm = scanner.nextInt()*MONTHS;


//        double Mortgage = Math.pow((1 + monthlyInterestRate), loanTerm);
        double mortgage = principal * (monthlyInterestRate * Math.pow((1 + monthlyInterestRate), loanTerm))
                /(Math.pow((1 + monthlyInterestRate), loanTerm) - 1);

        String monthlyPayments = numberFormat.format(mortgage);

        System.out.println("Mortgage: " + monthlyPayments);

    }

}