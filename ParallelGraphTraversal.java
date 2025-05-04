//////////////////////////////////////////
/// PR 1
/// /////////////////////////////////////////

//user input

import java.util.*;

public class ParallelGraphTraversal {
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visitedDFS;
    static boolean[] visitedBFS;
    static int nodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of nodes: ");
        nodes = sc.nextInt();
        visitedDFS = new boolean[nodes];
        visitedBFS = new boolean[nodes];

        for (int i = 0; i < nodes; i++)
            graph.add(new ArrayList<>());

        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();
        System.out.println("Enter edges (u v):");
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u); // undirected graph
        }

        System.out.print("Enter starting node: ");
        int start = sc.nextInt();

        System.out.println("\n--- Parallel Traversals ---");

        Thread dfsThread = new Thread(() -> {
            System.out.print("DFS: ");
            dfs(start);
            System.out.println();
        });

        Thread bfsThread = new Thread(() -> {
            System.out.print("BFS: ");
            bfs(start);
            System.out.println();
        });

        dfsThread.start();
        bfsThread.start();
    }

    static void dfs(int node) {
        visitedDFS[node] = true;
        System.out.print(node + " ");
        for (int neighbor : graph.get(node)) {
            if (!visitedDFS[neighbor])
                dfs(neighbor);
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visitedBFS[start] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            for (int neighbor : graph.get(node)) {
                if (!visitedBFS[neighbor]) {
                    visitedBFS[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }
}

// Enter number of nodes: 5
// Enter number of edges: 4
// Enter edges (u v):
// 0 1
// 0 2
// 1 3
// 1 4
// Enter starting node: 0

// Enter number of nodes: 5
// Enter number of edges: 4
// Enter edges (u v):
// 0 1
// 0 2
// 1 3
// 1 4
// Enter starting node: 0
