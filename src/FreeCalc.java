import com.sun.source.tree.WhileLoopTree;

public class FreeCalc {
    public int start_year;
    private double[] index_grow;
    private double[] withdrawal;
    private double[] inflation_grow;
    private static FreeCalc freecalc;
    private static int target_year;

    public static FreeCalc getfreecalc()
    {
        if (freecalc == null){
            freecalc = new FreeCalc( );
        }
        return freecalc;
    }

    private FreeCalc() {
        target_year = 2022;
    }

    private void inflation_grow(double[] inflation ){
        inflation_grow = new double[inflation.length-1];
        for(int i = 0; i < inflation.length-1; i++ ){
            inflation_grow[i] =  (inflation[i+1] - inflation[i]) / inflation[i]*100;
        }
    }
    private void index_grow(double[] MOEX_RATE){
        index_grow = new double[MOEX_RATE.length-1];
        for(int i = 0; i < MOEX_RATE.length-1; i++ ){
            index_grow[i] =  (MOEX_RATE[i+1] - MOEX_RATE[i]) / MOEX_RATE[i]*100;
        }
    }
    private void Get_withdrawal(double[] index_grow, double[] inflation_grow){
        withdrawal = new double[index_grow.length];
        for (int i=0; i < withdrawal.length; i++){
            withdrawal[i] = index_grow[i] - inflation_grow[i];
        }
    }
    private boolean first_condition (double perc_izyat){
        double y = 100;
        double x = y * perc_izyat / 100;
        for( int i = start_year-2002; i<target_year-2002; i++){

            y = y - x ; // отнимаю от капитала проценты.

            if ( y < 0 ){          // проверяю что капитал еще есть.
                return false;
            }
            y = y * (1 + index_grow [i]/100 );
            x = x * (1 + Mosindex_infl.INFLATION_RATE[i]/100); // умножаю капитал на процент роста.
        }
        return true;
    }
    public void Calculate(int year) throws Exception{
        if (year < 2002 || year > 2021) throw new Exception("throws Exception…");
        start_year = year;
        double answer = 1;
        this.index_grow(Mosindex_infl.MOEX_RATE);
        this.inflation_grow(Mosindex_infl.INFLATION_RATE);
        this.Get_withdrawal(index_grow, inflation_grow);

        for (double i = 0.5; i<=100; i=i+0.5) {
                boolean first = this.first_condition(i);
                if (first)  {
                    if ( i > answer){
                        answer = i;
                    }
                }
            }
        this.show_answer(  answer  );
        }
    private void show_answer(double answer ){
        System.out.println( answer );
    }
}
