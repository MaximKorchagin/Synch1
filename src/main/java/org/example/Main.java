package org.example;

import java.util.*;

import org.apache.commons.lang3.StringUtils;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) {

        Runnable logic = () -> {
            String text = generateRoute("RLRFR", 100);
            int count = 0;
            for(int i = 0; i < text.length(); ++i) {
                if ('R' == text.charAt(i)) {
                    ++count;
                }
            }
            if (sizeToFreq.containsKey(count)) {
                sizeToFreq.put(count, sizeToFreq.get(count) + 1);
            } else {
                sizeToFreq.put(count, 1);
            }
            System.out.println(count);
        };

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(logic);
            thread.start();
        }

    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}