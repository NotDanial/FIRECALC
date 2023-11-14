/*
 * Класс FIRE калькулятор
 *
 * 1.0
 *
 * by NotDanial
 */

public class FreeCalc {

    private final int TARGET_YEAR = 2022;


    /**
     * ...Рассчет % роста индексации...
     */
    private double[] getIndexGrow(double[] MOEX_RATE) {

        double[ ] indexGrow = new double[MOEX_RATE.length - 1];

        for (int i = 0; i < MOEX_RATE.length - 1; i++) {
            indexGrow[i] = (MOEX_RATE[i + 1] - MOEX_RATE[i]) / MOEX_RATE[i] * 100;
        }
        return  indexGrow;
    }

    /**
     * ...Запуск цикла по годам с проверкой условия ,
     * ...что капитал > 0, при ежегодных вычетах
     */
    private boolean checkPercentage(double withdrawalPercentage , double[] indexGrow, int startYear) {

        double capital = 100;
        double annualPayment = capital * withdrawalPercentage / 100;

        for (int i = startYear - 2002; i < TARGET_YEAR - 2002; i++) {
            capital = capital - annualPayment;


            if (capital < 0) {
                return false;
            }

            capital = capital * (1 + indexGrow[i] / 100);
            annualPayment = annualPayment * (1 + Constants.INFLATION_RATE[i] / 100);
        }

        return true;

    }

    /**
     * ...Запуск работы калькулятора. Здесь же находиться цикл, необходимый
     * ...для поиска максимального возможного процента изьятия
     */
    public double calculate(int year) throws Exception {
        boolean first;
        double answer = 1;
        double[] indexGrow = getIndexGrow(Constants.MOEX_RATE);

        if ((year < 2002) || (year > TARGET_YEAR - 1)) {
            throw new Exception("throws Exception…");
        }

        for (double i = 2.0; i <= 100; i = i + 0.5) {

             first = this.checkPercentage(i, indexGrow, year);

            if ((first) && (i > answer)) {
                answer = i;
            }
        }

        return answer;
    }

}
