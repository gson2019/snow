package com.company;

import apple.laf.JRSUIUtils;

import java.util.*;

public class ThreadCount {
    public static int[] calculateMaxThreads(int[][] edges, int[][] knownThreads, int n) {
        // Initialize threads array with minimum value
        int[] threads = new int[n];
        Arrays.fill(threads, -1);

        // Priority Queue for the multi-source Dijkstra
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(Comparator.comparingInt(a -> -a[0]));

        // Add known threads to both threads array and maxHeap
        for (int[] threadPair : knownThreads) {
            int nodeIndex = threadPair[0] - 1; // assuming 1-indexed nodes
            threads[nodeIndex] = threadPair[1];
            maxHeap.add(threadPair);
        }

        // Construct the tree adjacency list
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0] - 1).add(edge[1] - 1); // assuming 1-indexed nodes
            adjacencyList.get(edge[1] - 1).add(edge[0] - 1);
        }

        // Dijkstra-like propagation from known thread counts
        while (!maxHeap.isEmpty()) {
            int[] current = maxHeap.poll();
            int threadCount = current[1];
            int nodeIndex = current[0] - 1;

            for (int neighbor : adjacencyList.get(nodeIndex)) {
                if (threads[neighbor] < threadCount - 1) {
                    threads[neighbor] = threadCount - 1;
                    maxHeap.add(new int[] {neighbor + 1, threadCount - 1});
                }
            }
        }

        return threads;
    }

    public static void main(String[] args) {
            int serviceNodes = 8;
            int[][] edges = {{1, 2}, {2, 3}, {1, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 8}};
            int[][] currentValues = {{2,4}, {4, 3}};
            int[] result = calculateMaxThreads(edges, currentValues, serviceNodes);

            for (int threadCount : result) {
                System.out.print(threadCount + " ");
            }
        }
}
