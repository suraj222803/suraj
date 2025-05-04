//////////////////////////////////////////
/// PR 2
/// /////////////////////////////////////////


//user input

import java.util.*;

public class ShortParallelSortUser {
    public static void main(String[] args) throws Exception {
        System.out.print("Number of elements in the array :");
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), a[] = new int[n], b[] = new int[n];
        System.out.print("Enter Array elements :");
        for (int i = 0; i < n; i++)
            a[i] = b[i] = sc.nextInt();

        long t1 = System.nanoTime();
        bubbleSort(a);
        System.out.println("Bubble Time: " + (System.nanoTime() - t1));

        long t2 = System.nanoTime();
        new Thread(() -> mergeSort(b, 0, n - 1)).start();
        Thread.sleep(50); // simple wait
        System.out.println("Merge Time: " + (System.nanoTime() - t2));
    }

    static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++)
            for (int j = 0; j < a.length - i - 1; j++)
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
    }

    static void mergeSort(int[] a, int l, int r) {
        if (l >= r)
            return;
        int m = (l + r) / 2;
        Thread t1 = new Thread(() -> mergeSort(a, l, m));
        Thread t2 = new Thread(() -> mergeSort(a, m + 1, r));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
        }
        merge(a, l, m, r);
    }

    static void merge(int[] a, int l, int m, int r) {
        int[] t = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r)
            t[k++] = (a[i] < a[j]) ? a[i++] : a[j++];
        while (i <= m)
            t[k++] = a[i++];
        while (j <= r)
            t[k++] = a[j++];
        System.arraycopy(t, 0, a, l, t.length);
    }
}

/*
 * 5
 * 5 2 9 1 6
 * 
 */

// Bubble Time: 7600
// Merge Time: 54453300