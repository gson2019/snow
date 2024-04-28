package com.company;

import java.util.Collections;
import java.util.PriorityQueue;

public class JobExecution {
    public static int getMinimumOperations(int[] executionTime, int x, int y) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int counter = 0;
        for (int execution : executionTime) {
            pq.add(execution);
        }

        while (!pq.isEmpty()) {
            int topElement = pq.poll();
            topElement -= x;
            Integer[] remainingNums = new Integer[(pq.size())];
            Integer[] remains = pq.toArray(remainingNums);
            pq.clear();
            for (int i = 0; i < remains.length; i++) {
                remains[i] -= y;
            }
            for (int i = 0; i < remains.length; i++) {
                if (remains[i] > 0) {
                    pq.add(remains[i]);
                }
            }
            if (topElement > 0) {
                pq.add(topElement);
            }
            counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        int[] executionTime = {3, 4, 10, 8, 12};
        int x = 5, y = 2;
        int result = getMinimumOperations(executionTime, x, y);

        System.out.println(result);
    }
}
