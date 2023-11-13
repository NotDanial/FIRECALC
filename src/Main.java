import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner console = new Scanner(System.in);
            double result = FreeCalc.getFreeCalc().calculate(console.nextInt());
            System.out.println(result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}