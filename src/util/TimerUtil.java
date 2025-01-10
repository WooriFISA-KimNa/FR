package util;

public class TimerUtil {

    // 실행 시간을 측정하고 출력
    public static void measureExecutionTime(String taskName, Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        System.out.println(taskName + " executed in " + (endTime - startTime) / 1_000_000 + " milliseconds");
    }

    // 실행 시간을 측정하고 반환
    public static long measureExecutionTimeReturn(String taskName, Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        return endTime - startTime; // 나노초 단위로 반환
    }
}
