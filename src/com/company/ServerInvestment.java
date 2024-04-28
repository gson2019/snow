package com.company;

public class ServerInvestment {
    public static int[] maximumUpgrades(int[] num_servers, int[] money, int[] sell, int[] upgrade) {
        int[] maxUpgrades = new int[num_servers.length];

        for (int i = 0; i < num_servers.length; i++) {
            int totalFunds = money[i];
            int servers = num_servers[i];

            // Calculate the maximum number of upgrades by selling and upgrading servers
            while (servers > 0 && (totalFunds >= upgrade[i] || (servers > 1 && totalFunds + sell[i] >= upgrade[i]))) {
                if (totalFunds >= upgrade[i]) {
                    totalFunds -= upgrade[i];
                    maxUpgrades[i]++;
                } else {
                    totalFunds += (sell[i] - upgrade[i]);
                    servers--; // sold one server to add funds
                }
            }
        }

        return maxUpgrades;
    }

    public static void main(String[] args) {
        int[] num_servers = {4, 3, 5};
        int[] money = {8, 9, 10};
        int[] sell = {4, 2, 3};
        int[] upgrade = {4, 5, 6};

        int[] upgrades = maximumUpgrades(num_servers, money, sell, upgrade);

        for (int upgradeCount : upgrades) {
            System.out.println(upgradeCount);
        }
    }
}
