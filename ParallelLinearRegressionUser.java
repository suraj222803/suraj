//////////////////////////////////////////
/// PR 4
/// /////////////////////////////////////////

//user input

import java.util.*;

public class ParallelLinearRegressionUser {
    static class Result {
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of data points: ");
        int n = sc.nextInt();
        double[] x = new double[n], y = new double[n];
        System.out.println("Enter X values:");
        for (int i = 0; i < n; i++)
            x[i] = sc.nextDouble();
        System.out.println("Enter Y values:");
        for (int i = 0; i < n; i++)
            y[i] = sc.nextDouble();

        Result res = new Result();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                res.sumX += x[i];
                res.sumY += y[i];
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                res.sumXY += x[i] * y[i];
                res.sumX2 += x[i] * x[i];
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        double m = (n * res.sumXY - res.sumX * res.sumY) / (n * res.sumX2 - res.sumX * res.sumX);
        double b = (res.sumY - m * res.sumX) / n;

        System.out.printf("Equation: Y = %.2fX + %.2f\n", m, b);
    }
}


// Enter number of data points: 5
// Enter X values:
// 1 2 3 4 5
// Enter Y values:
// 2 4 5 4 5
// Equation: Y = 0.60X + 2.20
