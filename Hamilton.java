// Diadaptasi dari program yang disediakan pada referensi soal, dengan catatan
// dilakukan beberapa modifiikasi yang sesuai agar bisa memenuhi kebutuhan soal

public class Hamilton {

    public Hamilton(int vertexNumb) {
        this.vertex = vertexNumb;
    }
    boolean hamiltonDynProg(int[][]adj, int n) {
        boolean [][]dp = new boolean[n][(1 << n)];
        // Set all dp[i][(1 << i)] to
        // true
        for(int i = 0; i < n; i++)
            dp[i][(1 << i)] = true;
        // Iterate over each subset
        // of nodes
        for(int i = 0; i < (1 << n); i++)
        {
            for(int j = 0; j < n; j++)
            {
                // If the jth nodes is included
                // in the current subset
                if ((i & (1 << j)) != 0)
                {
                    // Find K, neighbour of j
                    // also present in the
                    // current subset
                    for(int k = 0; k < n; k++)
                    {
                        if ((i & (1 << k)) != 0 &&
                                adj[k][j] == 1 && j != k &&
                                dp[k][i ^ (1 << j)])
                        {
                            // Update dp[j][i]
                            // to true
                            dp[j][i] = true;
                            break;
                        }
                    }
                }
            }
        }
        // Traverse the vertices
        for(int i = 0; i < n; i++)
        {
            // Hamiltonian Path exists
            if (dp[i][(1 << n) - 1])
                return true;
        }
        // Otherwise, return false
        return false;
    }


    /* Java program for solution of Hamiltonian Cycle problem
   using backtracking, modified for Hamiltonian Path */
    private final int vertex;
    int[] path;

    /* A utility function to check if the vertex v can be
       added at index 'pos'in the Hamiltonian Cycle
       constructed so far (stored in 'path[]') */
    boolean isSafe(int v, int[][] graph, int[] path, int pos) {
        /* Check if this vertex is an adjacent vertex of
           the previously added vertex. */
        if (graph[path[pos - 1]][v] == 0)
            return false;
        /* Check if the vertex has already been included.
           This step can be optimized by creating an array
           of size V */
        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;
        return true;
    }

    /* A recursive utility function to solve hamiltonian
       path problem */
    boolean hamPathUtil(int[][] graph, int[] path, int pos) {
        /* base case: If all vertices are included in
           Hamiltonian Path */
        if (pos == vertex) {
            return true;
        }
        // Try different vertices as a next candidate in
        // Hamiltonian Path. We don't try for 0 as we
        // included 0 as starting point in hamiltonBacktracking()
        for (int v = 1; v < vertex; v++) {
            /* Check if this vertex can be added to Hamiltonian
               Cycle */
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;
                /* recur to construct rest of the path */
                if (hamPathUtil(graph, path, pos + 1))
                    return true;
                /* If adding vertex v doesn't lead to a solution,
                   then remove it */
                path[pos] = -1;
            }
        }
        /* If no vertex can be added to Hamiltonian Cycle
           constructed so far, then return false */
        return false;
    }

    /* This function solves the Hamiltonian Path problem using
       Backtracking. It mainly uses hamPathUtil() to solve the
       problem. It returns false if there is no Hamiltonian Path
       possible, otherwise return true and prints the path.
       Please note that there may be more than one solution,
       this function prints one of the feasible solutions. */
    boolean hamiltonBacktracking(int[][] graph) {
        path = new int[vertex];
        for (int i = 0; i < vertex; i++)
            path[i] = -1;
        /* Akan dicoba kemungkinan start dari seluruh vertex. Jika ditemukan, return true */
        for (int i = 0; i <vertex; i ++) {
            path[0] = i;
            if (hamPathUtil(graph, path, 1))
                return true;
        }
        return false;
    }
}
