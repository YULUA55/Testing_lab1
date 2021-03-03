package functions;

import java.util.HashMap;

import static java.lang.Double.NaN;

public class Function {
    private static HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
    public static final double PRECISION = 0.1;

    private static Integer fact(Integer num) {
        Integer cached = cache.get(num);
        if (cached != null)
            return cached;
        Integer result = 1;
        if (num > 1) {
            result = fact(num - 1) * num;
        }
        cache.put(num, result);
        return result;

    }

    private static double sinTailor(double x, int num) {
        int sign = ((num + 1) % 2 == 0) ? 1 : -1;
        return (sign * ((Math.pow(x, 2 * num - 1)) / fact(Integer.valueOf(2 * num - 1))));
    }

    public static double sin(double x) {
        if (x > Math.PI || x < -Math.PI) {
            return NaN;
        }
        double result = 0;
        double currentMember = 0.0;
        double prevMember = 10.0;
        int n = 1;
        while (Math.abs(prevMember - currentMember) > PRECISION) {
            prevMember = currentMember;
            currentMember = sinTailor(x, n);
            result += currentMember;
            n++;
        }
        return result;
    }


}
