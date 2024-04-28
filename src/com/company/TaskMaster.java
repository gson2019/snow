package com.company;

import java.util.*;

public class TaskMaster {
    public static int maxTasks(int[] dependentTasks, int[] primaryTasks, int n) {
        // Create a graph where `graph.get(node)` is a list of tasks that depend on `node`.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < dependentTasks.length; i++) {
            graph.putIfAbsent(primaryTasks[i], new ArrayList<>());
            graph.get(primaryTasks[i]).add(dependentTasks[i]);
        }

        // Determine in-degree for each task
        //in-degree calculate nums of pre tasks of each task
        int[] inDegree = new int[n + 1];
        for (int dependent : dependentTasks) {
            inDegree[dependent]++;
        }

        // Find all tasks with zero in-degree to start the process.
        Queue<Integer> zeroInDegree = new LinkedList<>();
        for (int node = 1; node <= n; node++) {
            if (inDegree[node] == 0) {
                zeroInDegree.add(node);
            }
        }

        // Perform tasks while possible, those with zero in-degree.
        int performed = 0;
        while (!zeroInDegree.isEmpty()) {
            int task = zeroInDegree.poll();
            performed++;

            // Reduce in-degree for all tasks that depend on the current task.
            List<Integer> dependents = graph.getOrDefault(task, new ArrayList<>());
            for (int dependent : dependents) {
                inDegree[dependent]--;
                // If in-degree becomes zero, add to list of tasks that can be performed.
                if (inDegree[dependent] == 0) {
                    zeroInDegree.add(dependent);
                }
            }
        }

        // At this point, if there are still tasks with non-zero in-degree, this means they have mutual dependencies.
        // We can only perform one of each of these pairs.
        int mutualDependencies = 0;
        for (int degree : inDegree) {
            if (degree > 0) {
                mutualDependencies++;
            }
        }
        performed += mutualDependencies / 2; // assuming each mutual dependency reduces the number of tasks by 1

        return performed;
    }
}
