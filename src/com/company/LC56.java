package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LC56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> list = new ArrayList<>();

        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (prev[1] >= intervals[i][0]) {
                prev[1] = Math.max(prev[1], intervals[i][1]);
            } else {
                list.add(prev);
                prev = intervals[i];
            }
        }
        list.add(prev);

        return list.toArray(new int[list.size()][2]);
    }

    public int[][] merge2IntervalList(int[][] intervals1, int[][] intervals2) {
        if (intervals1.length == 0) {
            return intervals2;
        } else if (intervals2.length == 0) {
            return intervals1;
        }

        Arrays.sort(intervals1, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(intervals2, Comparator.comparingInt(a -> a[0]));

        int i = 0, j = 0;
        int[] toAdd;
        if (intervals1[0][0] <= intervals2[0][0]) {
            i = 1;
            toAdd = intervals1[0];
        } else {
            j = 1;
            toAdd = intervals2[0];
        }

        List<int[]> result = new ArrayList<>();

        while (i < intervals1.length || j < intervals2.length) {
            int[] current = null;
            if (i < intervals1.length && j < intervals2.length) {
                if (j < i) {
                    current = intervals2[j++];
                } else if (i < j) {
                    current = intervals1[i++];
                } else {
                    if (intervals1[i][0] < intervals2[j][0]) {
                        current = intervals1[i++];
                    } else {
                        current = intervals2[j++];
                    }
                }
            } else if (i < intervals1.length) {
                current = intervals1[i++];
            } else {
                current = intervals2[j++];
            }

            if (toAdd[1] < current[0]) {
                result.add(toAdd);
                toAdd = current;
            } else if (current[1] < toAdd[0]) {
                result.add(current);
            } else {
                toAdd = new int[] {
                        Math.min(toAdd[0], current[0]),
                        Math.max(toAdd[1], current[1])
                };
            }
        }

        result.add(toAdd);
        return result.toArray(new int[result.size()][2]);
    }
}
