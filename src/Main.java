
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       try {
           Scanner console = new Scanner(System.in);
           FreeCalc.getfreecalc().Calculate( console.nextInt() );
       }
       catch (Exception ex){
           System.out.println(ex.getMessage());
       }
    }
}