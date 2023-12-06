public class Main {
    public static void main(String[] args) {
        // Copy paste manual dari dataset yang berkesesuaian 16,18,20 & hamilton/non-hamilton
        int[][] adjTest = {{0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0},
                {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0},
                {0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0},
                {1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0},
                {0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0},
                {0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0},
        };


        int vertexNum = adjTest.length;  // Ganti sesuai dataset yang berkesesuaian 16,18,20
        Hamilton hamilton = new Hamilton(vertexNum);


        Runtime runtime = Runtime.getRuntime();

        // Dynamic Programming Approach
        double startTime = System.nanoTime() / 1_000_000.0;
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        if (hamilton.hamiltonDynProg(adjTest, vertexNum))
            System.out.println("YES");
        else
            System.out.println("NO");
        double endTime = System.nanoTime() / 1_000_000.0;
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        double timeDP = endTime - startTime;
        long memoryDP = memoryAfter - memoryBefore;

        // Backtracking Approach
        startTime = System.nanoTime() / 1_000_000.0;
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        if (hamilton.hamiltonBacktracking(adjTest))
            System.out.println("YES");
        else
            System.out.println("NO");
        endTime = System.nanoTime() / 1_000_000.0;
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        double timeBt = endTime - startTime;
        long memoryBt = memoryAfter - memoryBefore;

        // Ganti string sesuai dengan dataset
        System.out.println("========== RESULT HAMILTON " + vertexNum + " VERTICES ==========");
        System.out.println("Dynamic Prog: " + timeDP + "ms; " + memoryDP + " memory used");
        System.out.println("Backtracking: " + timeBt + "ms; " + memoryBt + " memory used");
    }
}