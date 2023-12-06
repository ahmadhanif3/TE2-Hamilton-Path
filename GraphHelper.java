import java.util.Arrays;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GraphHelper {
    public static int[][] graphGenerator(int vertex) {
        Random rand = new Random();
        int[][] adj = new int[vertex][vertex];

        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                adj[i][j] = -1;
            }
        }

        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                if (i != 0) {
                    for (int k = 0; k < i; k++) {
                        adj[j][k] = adj[k][j];
                    }
                }
                if (adj[i][j] == -1) {
                    adj[i][j] = rand.nextInt(2);
                }
            }
        }

        return adj;
    }

    public static void main(String[] args) {
        int vertexNum = 20;  // Ganti untuk masing-masing dataset yang dibutuhkan
        int[][] adjTest = graphGenerator(vertexNum);
        int[] matrix = new int[vertexNum];
        Arrays.fill(matrix, 0);
        int[][] adjCopy = new int[vertexNum][];
        for (int i = 0; i < vertexNum; i++) {
            if (i == vertexNum-1)
                adjCopy[i] = matrix;
            else
                adjCopy[i] = Arrays.copyOf(adjTest[i], adjTest[i].length);
            adjCopy[i][vertexNum-1] = 0;
        }

        String graph1 = graphToString(vertexNum, adjTest);
        String filepath1 = "hamilton_" + vertexNum;
        String graph2 = graphToString(vertexNum, adjCopy);
        String filepath2 = "non-hamilton_" + vertexNum;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath1))) {
            writer.write(graph1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath2))) {
            writer.write(graph2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String graphToString(int vertexNum, int[][] adjCopy) {
        String out = "{";
        for (int i = 0; i < vertexNum; i++) {
            out = out.concat("{");
            for (int j = 0; j < vertexNum; j++) {
                out = out.concat(adjCopy[i][j] + ((j == vertexNum-1) ? "},":", "));
            }
            out = out.concat("\n");
        }
        out = out.concat("};\n");
        return out;
    }
}
