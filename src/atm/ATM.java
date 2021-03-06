package atm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ATM {
    private int[] billValues;
    private Map<Integer, Long> valueToCount;
    private long currentSum;

    public ATM() {
        billValues = new int[]{1, 3, 5, 10, 25, 50, 100, 500, 1000, 5000}; // Not empty, sorted
        valueToCount = new HashMap<>();
        for (int i : billValues) {
            valueToCount.put(i, 0L);
        }
        currentSum = 0;
    }

    public long put(int value, int count) {
        if (!valueToCount.containsKey(value)) {
            throw new IllegalArgumentException("Invalid bill value: " + value);
        }
        if (count <= 0) {
            throw new IllegalArgumentException("Invalid count of values(<= 0): " + count);
        }
        if (currentSum + (long) value * count < 0) { // overflow
            throw new ArithmeticException("Long overflow");
        }
        valueToCount.put(value, valueToCount.get(value) + count);
        currentSum += (long) value * count;
        return currentSum;
    }

    public String dump() {
        String result = "ATM:";
        for (int i : billValues) {
            result += "\n" + i + " " + valueToCount.get(i);
        }
        return result;
    }

    public long state() {
        return currentSum;
    }

    public String get(int sumNeed) {
        if (sumNeed < 0) {
            throw new IllegalArgumentException("Invalid requested sum: (<= 0)" + sumNeed);
        }
        if (sumNeed > 40_000_000) { // 50_000_000 = OutOfMemoryError
            throw new IllegalArgumentException("Requested sum is too large (40_000_000 maximum)");
        }
        int[][] dp = new int[billValues.length][(int) Math.min(sumNeed, currentSum) + 1];   // dp[x][y] - minimal count of bills
        for (int[] row : dp) {                                                              // with value billValues[x] we need
            Arrays.fill(row, -1);                                                       // to get y as the sum and -1 if cant
        }

        for (int i = 0; i * billValues[0] < dp[0].length && i <= valueToCount.get(billValues[0]); ++i) {   // using only one value - billValues[0]
            dp[0][i * billValues[0]] = i;
        }

        for (int valInd = 1; valInd < billValues.length; ++valInd) {
            dp[valInd][0] = 0;
            int value = billValues[valInd];
            for (int sum = 1; sum < dp[0].length; ++sum) {
                if (dp[valInd - 1][sum] != -1) { // can reach sum without any value bills
                    dp[valInd][sum] = 0;
                } else if (sum - value >= 0 &&
                        dp[valInd][sum - value] != -1 &&
                        dp[valInd][sum - value] < valueToCount.get(value)) { // can reach (sum - value)
                    dp[valInd][sum] = dp[valInd][sum - value] + 1;           // with some value bills
                }
            }
        }

        int reachSum = (int) Math.min(sumNeed, currentSum);
        while (dp[billValues.length - 1][reachSum] == -1) { // find the greatest reachable sum
            --reachSum;
        }

        String result = "";
        int sumIndex = reachSum;
        for (int i = billValues.length - 1; i >= 0; --i) {
            int value = billValues[i];
            if (dp[i][sumIndex] != 0) {
                result += value + "=" + dp[i][sumIndex] + ", ";
            }
            valueToCount.put(value, valueToCount.get(value) - dp[i][sumIndex]);
            currentSum -= dp[i][sumIndex] * value;
            sumIndex -= dp[i][sumIndex] * billValues[i];
        }

        result += "summary: " + reachSum;

        if (reachSum < sumNeed) {
            result += "\nWithout " + (sumNeed - reachSum);
        }

        return result;
    }
}
