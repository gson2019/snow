package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int[][] intervalList1 = new int[][] {
                {1, 5},
                {10, 14},
                {16, 18}
        };
        int[][] intervalList2 = new int[][] {
                {2, 6},
                {8, 10},
                {11, 20}
        };
        LC56 lc56 = new LC56();
        int[][] mergeTwoIntervalLists = lc56.merge2IntervalList(intervalList1, intervalList2);
    }
}
