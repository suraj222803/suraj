
//////////////////////////////////////////
/// PR 3
/// /////////////////////////////////////////

//user input

import java.util.*;

public class ParallelReductionUser {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number of elements : ");
        int n = sc.nextInt(), a[] = new int[n];
        System.out.println("Enter the Number  :");
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();

        Result res = new Result();
        Thread t1 = new Thread(() -> res.sum = Arrays.stream(a).sum());
        Thread t2 = new Thread(() -> res.min = Arrays.stream(a).min().getAsInt());
        Thread t3 = new Thread(() -> res.max = Arrays.stream(a).max().getAsInt());

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println("Min: " + res.min);
        System.out.println("Max: " + res.max);
        System.out.println("Sum: " + res.sum);
        System.out.println("Average: " + (res.sum / (double) n));
    }

    static class Result {
        int min, max, sum;
    }
}

/*
 * 5
 * 10 5 20 7 2
 * 
 */

/*
 * Min: 2
 * Max: 20
 * Sum: 44
 * Average: 8.8
 * 
 */