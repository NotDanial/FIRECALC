/*
 * Класс FREE калькулятор
 *
 * 1.0
 *
 * by NotDanial
 */

public class FreeCalc {
    public int startYear;
    private double[] indexGrow;
    private static FreeCalc freecalc;
    private final int TARGET_YEAR;

    public static FreeCalc getFreeCalc() {

        if (freecalc == null) {
            freecalc = new FreeCalc();
        }

        return freecalc;

    }

    private FreeCalc() {

        TARGET_YEAR = 2022;

    }

    /**
     * ...Рассчет % роста индексации...
     */
    private void indexGrow(double[] MOEX_RATE) {

        indexGrow = new double[MOEX_RATE.length - 1];

        for (int i = 0; i < MOEX_RATE.length - 1; i++) {
            indexGrow[i] = (MOEX_RATE[i + 1] - MOEX_RATE[i]) / MOEX_RATE[i] * 100;
        }

    }

    /**
     * ...Проверка условия , что капитал > 0, при ежегодных вычетах...
     */
    private boolean firstCondition(double withdrawalPercantage) {
        double capital = 100;
        double annualPayment = capital * withdrawalPercantage / 100;

        for (int i = startYear - 2002; i < TARGET_YEAR - 2002; i++) {
            capital = capital - annualPayment;

            if (capital < 0) {
                return false;
            }

            capital = capital * (1 + indexGrow[i] / 100);
            annualPayment = annualPayment * (1 + Mosindex_infl.INFLATION_RATE[i] / 100);
        }

        return true;

    }

    /**
     * ...Основной метод класса...
     */
    public void calculate(int year) throws Exception {
        double answer = 1;
        boolean first;

        startYear = year;

        if ((year < 2002) || (year > 2021)) {
            throw new Exception("throws Exception…");
        }

        this.indexGrow(Mosindex_infl.MOEX_RATE);

        for (double i = 2.0; i <= 100; i = i + 0.5) {

             first = this.firstCondition(i);

            if ((first) || (i > answer)) {
                answer = i;
            }
        }

        this.showAnswer(answer);
    }

    /**
     * ...Вывод результата калькулятора...
     */
    private void showAnswer(double answer) {
        System.out.println(answer);
    }
}
