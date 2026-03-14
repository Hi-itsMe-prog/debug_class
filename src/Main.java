import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();

        Debugger.reset();
        Debugger.enter("main", a, b);

        System.out.println(mult1(a, b));
        System.out.println(mult2(a, b));
        System.out.println(mult3(a, b));
        System.out.println(mult4(a, b));
        System.out.println(mult5(a, b));
        System.out.println(mult6(a, b));
        System.out.println(mult7(a, b));

        Debugger.exit("main", "готово");
    }

    public static int mult1(int a, int b) {
        Debugger.enter("mult1", a, b);
        if (a == 0 || b == 0) {
            Debugger.exit("mult1", 0);
            return 0;
        }

        int result = 0;
        int absB = Math.abs(b);
        Debugger.watch("absB", absB);

        for (int i = 0; i < absB; i++) {
            result += a;
            Debugger.watch("i=" + i + " result", result);
        }

        Debugger.check(result == a * b, "Ожидалось " + (a * b) + ", получили " + result);
        Debugger.exit("mult1", result);
        return result;
    }

    public static int mult2(int a, int b) {
        Debugger.enter("mult2", a, b);

        int result;
        if (b == 0) result = 0;
        else if (b == 1) result = a;
        else if (b == -1) result = -a;
        else if (b > 0) result = a + mult2(a, b - 1);
        else result = -mult2(a, -b);

        Debugger.check(result == a * b, "Ожидалось " + (a * b));
        Debugger.exit("mult2", result);
        return result;
    }

    public static int mult3(int a, int b) {
        Debugger.enter("mult3", a, b);

        int result = 0;
        boolean negative = (a < 0) ^ (b < 0);
        int absA = Math.abs(a);
        int absB = Math.abs(b);

        Debugger.watch("negative", negative);
        Debugger.watch("absA", absA);
        Debugger.watch("absB", absB);

        while (absB > 0) {
            if ((absB & 1) == 1) {
                result += absA;
                Debugger.watch("result (+=absA)", result);
            }
            absA <<= 1;
            absB >>= 1;
            Debugger.watch("absA<<", absA);
            Debugger.watch("absB>>", absB);
        }

        result = negative ? -result : result;
        Debugger.check(result == a * b, "Ожидалось " + (a * b));
        Debugger.exit("mult3", result);
        return result;
    }

    public static int mult4(int a, int b) {
        Debugger.enter("mult4", a, b);

        if (a == 0 || b == 0) {
            Debugger.exit("mult4", 0);
            return 0;
        }

        boolean negative = (a < 0) ^ (b < 0);
        double logA = Math.log(Math.abs(a));
        double logB = Math.log(Math.abs(b));
        double logSum = logA + logB;
        double expVal = Math.exp(logSum);
        int result = (int) Math.round(expVal);

        Debugger.watch("logA", logA);
        Debugger.watch("logB", logB);
        Debugger.watch("expVal", expVal);

        result = negative ? -result : result;
        Debugger.check(Math.abs(result - a * b) < 0.1, "Точность нарушена");
        Debugger.exit("mult4", result);
        return result;
    }

    public static int mult5(int a, int b) {
        Debugger.enter("mult5", a, b);

        if (a == 0 || b == 0) {
            Debugger.exit("mult5", 0);
            return 0;
        }

        boolean negative = (a < 0) ^ (b < 0);
        int absA = Math.abs(a);
        int absB = Math.abs(b);
        int result = 0;

        Debugger.watch("absA", absA);
        Debugger.watch("absB", absB);

        while (absB > 0) {
            if (absB % 2 == 1) {
                result += absA;
                Debugger.watch("result (+)", result);
            }
            absA <<= 1;
            absB >>= 1;
            Debugger.watch("absA", absA);
            Debugger.watch("absB", absB);
        }

        result = negative ? -result : result;
        Debugger.check(result == a * b, "Ожидалось " + (a * b));
        Debugger.exit("mult5", result);
        return result;
    }

    public static int mult6(int a, int b) {
        Debugger.enter("mult6", a, b);

        int sum = a + b;
        int diff = a - b;
        double sum2 = Math.pow(sum, 2);
        double diff2 = Math.pow(diff, 2);
        int result = (int)((sum2 - diff2) / 4);

        Debugger.watch("sum", sum);
        Debugger.watch("diff", diff);
        Debugger.watch("sum^2", sum2);
        Debugger.watch("diff^2", diff2);

        Debugger.check(result == a * b, "Ожидалось " + (a * b));
        Debugger.exit("mult6", result);
        return result;
    }

    public static int mult7(int a, int b) {
        Debugger.enter("mult7", a, b);

        if (a == 0 || b == 0) {
            Debugger.exit("mult7", 0);
            return 0;
        }
        if (a == 1) {
            Debugger.exit("mult7", b);
            return b;
        }
        if (b == 1) {
            Debugger.exit("mult7", a);
            return a;
        }
        if (a == -1) {
            Debugger.exit("mult7", -b);
            return -b;
        }
        if (b == -1) {
            Debugger.exit("mult7", -a);
            return -a;
        }

        int smaller = Math.abs(a) < Math.abs(b) ? Math.abs(a) : Math.abs(b);
        int larger = Math.abs(a) < Math.abs(b) ? Math.abs(b) : Math.abs(a);
        int result = 0;

        Debugger.watch("smaller", smaller);
        Debugger.watch("larger", larger);

        for (int i = 0; i < smaller; i++) {
            result += larger;
            Debugger.watch("i=" + i + " result", result);
        }

        boolean negative = (a < 0) ^ (b < 0);
        result = negative ? -result : result;

        Debugger.check(result == a * b, "Ожидалось " + (a * b));
        Debugger.exit("mult7", result);
        return result;
    }
}