import java.util.Scanner;

public class CurrencyConvertor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Show available currencies
        System.out.println("Available currencies: USD, INR, EUR");

        // Get base currency
        System.out.print("Enter base currency: ");
        String base = scanner.next().toUpperCase();

        // Get target currency
        System.out.print("Enter target currency: ");
        String target = scanner.next().toUpperCase();

        // Get amount
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        // Convert
        double rate = getRate(base, target);

        if (rate == 0) {
            System.out.println(" Conversion not available.");
        } else {
            double converted = amount * rate;
            System.out.println(" " + amount + " " + base + " = " + converted + " " + target);
        }

        scanner.close();
    }

    // Hardcoded rates (base to target)
    public static double getRate(String base, String target) {
        if (base.equals("USD") && target.equals("INR")) return 74.5;
        if (base.equals("INR") && target.equals("USD")) return 1 / 74.5;
        if (base.equals("USD") && target.equals("EUR")) return 0.85;
        if (base.equals("EUR") && target.equals("USD")) return 1 / 0.85;
        if (base.equals("INR") && target.equals("EUR")) return 0.85 / 74.5;
        if (base.equals("EUR") && target.equals("INR")) return 74.5 / 0.85;
        return 0;  // Not supported
    }
}
