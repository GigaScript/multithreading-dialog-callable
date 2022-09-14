package com.company;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    public final String name;
    public static Integer counter = 0;
    private static Integer maxIteration;

    MyCallable(String name, Integer maxIteration) {
        this.name = name;
        MyCallable.maxIteration = maxIteration;
    }

    @Override
    public String call() throws Exception {
        Thread.currentThread().setName(name);
        try {
            while (counter < maxIteration) {
                counter++;
                Thread.sleep(2000);
                System.out.println("Я поток " + Thread.currentThread().getName()
                        + ". Всем привет!");
            }
        } catch (InterruptedException err) {
            System.out.println(err.getMessage());
        }
        return "Поток " + Thread.currentThread().getName() + " - напечатал " + counter + " сообщений";
    }

}
