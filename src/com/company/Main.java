package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static final Integer maxCallableObject = 3;
    public static final Integer maxObjectIteration = 2;
    public static final List<Callable<String>> callableTaskList = new LinkedList<>();
    public static final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        createCallableTaskList();
        printAllTaskResult(executeAllTask());
        createCallableTaskList();
        printFirstResult(executeAnyTask());
    }

    private static void printFirstResult(String executeFirstTask) {
        System.out.println("Результат выполнения самой быстрой задачи :\n " + executeFirstTask);
    }

    private static String executeAnyTask() throws ExecutionException, InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        String anyTask = threadPool.invokeAny(callableTaskList);
        threadPool.shutdown();
        return anyTask;
    }

    private static List<Future<String>> executeAllTask() throws InterruptedException, ExecutionException {
        final List<Future<String>> taskList = threadPool.invokeAll(callableTaskList);
        threadPool.shutdown();
        return taskList;
    }

    private static void printAllTaskResult(List<Future<String>> taskList) throws ExecutionException, InterruptedException {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(taskList.get(i).get());
        }
    }


    private static void createCallableTaskList() {
        for (int i = 0; i < maxCallableObject; i++) {
            callableTaskList.add(i, new MyCallable(
                            String.valueOf(i + 1), maxObjectIteration
                    )
            );
        }
    }
}
