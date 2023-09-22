package org.example;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) {

        Runnable logic = () -> {
            String text = generateRoute("RLRFR", 100);
            int count = 0;
            for (int i = 0; i < text.length(); ++i) {
                if ('R' == text.charAt(i)) {
                    ++count;
                }
            }
            synchronized (sizeToFreq) {
                System.out.println(Thread.currentThread().getName() + " ZAHVATIL");
                if (sizeToFreq.containsKey(count)) {
                    sizeToFreq.put(count, sizeToFreq.get(count) + 1);
                } else {
                    sizeToFreq.put(count, 1);
                }
                sizeToFreq.notify();
            }
        };

        Thread freqlog = new MyThread(sizeToFreq);
        freqlog.start();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(logic);
            thread.start();
        }

        //Thread.sleep(3000);

        //System.out.println(generateString(sizeToFreq));
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    public static String generateString(Map<Integer, Integer> map) {
        StringBuilder builder = new StringBuilder();
        Optional<Map.Entry<Integer, Integer>> maxEntry = map.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        int maxValueCount = maxEntry.get().getValue();
        int maxValue = maxEntry.get().getKey();
        builder.append("Maximum povoterniy - ").append(maxValueCount).append(" U elementa ").append(maxValue).append("\n");
        map.remove(maxValue);
        builder.append("Drugie povtoreniya - ");
        builder.append(map.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).collect(Collectors.toList()));
        return builder.toString();
    }
}