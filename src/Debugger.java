import java.util.*;

public class Debugger {
    private static int depth = 0;
    private static Map<String, Long> timers = new HashMap<>();

    // Вход в метод
    public static void enter(String method, Object... args) {
        printIndent();
        System.out.println("→ " + method + " " + Arrays.toString(args));
        depth++;
        timers.put(method, System.nanoTime());
    }

    // Выход из метода
    public static void exit(String method, Object result) {
        Long start = timers.get(method);
        if (start != null) {
            double ms = (System.nanoTime() - start) / 1_000_000.0;
            depth--;
            printIndent();
            System.out.println("← " + method + " = " + result + " (" + ms + " мс)");
        }
    }

    // Слежение за переменной
    public static void watch(String name, Object value) {
        printIndent();
        System.out.println("  " + name + " = " + value);
    }

    // Проверка условия
    public static void check(boolean cond, String msg) {
        if (!cond) {
            printIndent();
            System.out.println("  ⚠️ " + msg);
        }
    }

    // Отступ для читаемости
    private static void printIndent() {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
    }

    // Сброс статистики
    public static void reset() {
        depth = 0;
        timers.clear();
    }
}
