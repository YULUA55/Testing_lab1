package bfs;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    private ArrayList adj[];
    private boolean used[];
    private Queue queue;
    private ArrayList sequence;

    public Graph(int numberOfEdges) {
        adj = new ArrayList[numberOfEdges];
        for (int i = 0; i < numberOfEdges; i++) {
            adj[i] = new ArrayList();
        }
        used = new boolean[numberOfEdges];
        queue = new LinkedList();
        sequence = new ArrayList();
    }

    public void addEdge(int src, int dest) {
        adj[src].add(dest);

    }

    public ArrayList getEdges(int edge) {
        return adj[edge];
    }

    public ArrayList getSequence() {
        return sequence;
    }

    public void bfs(int v) {
        sequence.clear();
        for (int i = 0; i < used.length; i++) {
            used[i] = false;
        }

        queue.add(v);
        sequence.add(v);
        used[v] = true;
        while (!queue.isEmpty()) {
            v = (int) queue.poll();
            for (int i = 0; i < adj[v].size(); ++i) {
                int w = (int) adj[v].get(i);
                if (used[w]) {
                    continue;
                }
                queue.add(w);
                used[w] = true;
                sequence.add(w);
            }
        }

    }
}

