package org.example;

import java.util.Map;
import java.util.Optional;

public class MyThread extends Thread {
    private final Map<Integer, Integer> map;

    public MyThread(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (map) {
                try {
                    map.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Optional<Map.Entry<Integer, Integer>> maxEntry = map.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue());
                int maxValueCount = maxEntry.get().getValue();
                int maxValue = maxEntry.get().getKey();
                System.out.println("MAXVALUE = " + maxValue + " MAXVALUECOUNT = " + maxValueCount);
            }
        }
    }
}
