import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            FreeCalc freecalc = new FreeCalc();
            Scanner console   = new Scanner(System.in);

            double result = freecalc.calculate(console.nextInt());
            System.out.println(result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}