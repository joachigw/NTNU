package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EdmondsKarp {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("src/main/resources/flytgraf5.txt"));

		int nodes = scan.nextInt();
		int edges = scan.nextInt();
		int source = 0;
		int sink = 7;

		Node[] graph = new Node[nodes];

		// Initialize each node
		for (int i = 0; i < nodes; i++)
			graph[i] = new Node();

		// Initialize each edge
		for (int i = 0; i < edges; i++) {
			int u = scan.nextInt();
			int v = scan.nextInt();
			int c = scan.nextInt();

			// Note edge "b" is not actually in the input graph
			// It is a construct that allows us to solve the problem
			Edge a = new Edge(u , v , 0 , c);
			Edge b = new Edge(v , u , 0 , 0);

			// Set pointer from each edge "a" to
			// its reverse edge "b" and vice versa
			a.setReverse(b);
			b.setReverse(a);

			graph[u].edges.add(a);
			graph[v].edges.add(b);
		}

		int maxFlow = 0;

		while (true) {
			// Parent array used for storing path
			// (parent[i] stores edge used to get to node i)
			Edge[] parent = new Edge[nodes];

			ArrayList<Node> q = new ArrayList<>();
			q.add(graph[source]);

			// BFS finding the shortest augmenting path
			while (!q.isEmpty()) {
				Node curr = q.remove(0);

				// Checks that edge has not yet been visited, and it doesn't
				// point to the source, and it is possible to send flow through it.
				for (Edge e : curr.edges)
					if (parent[e.v] == null && e.v != source && e.capacity > e.flow) {
						parent[e.v] = e;
						q.add(graph[e.v]);
					}
			}

			// If sink was NOT reached, no augmenting path was found.
			// Algorithm terminates and prints out max flow.
			if (parent[sink] == null)
				break;

			// If sink WAS reached, we will push more flow through the path
			int pushFlow = Integer.MAX_VALUE;

			// Finds maximum flow that can be pushed through given path
			// by finding the minimum residual flow of every edge in the path
			for (Edge e = parent[sink]; e != null; e = parent[e.u])
				pushFlow = Math.min(pushFlow , e.capacity - e.flow);

			// Adds to flow values and subtracts from reverse flow values in path
			for (Edge e = parent[sink]; e != null; e = parent[e.u]) {
				e.flow += pushFlow;
				e.reverse.flow -= pushFlow;
			}

			maxFlow += pushFlow;
		}

		System.out.println("Max Flow: " + maxFlow);

		scan.close();
	}
}

// No explicit constructor is necessary :P

class Node {

	// List of edges also includes reverse edges that
	// are not in original given graph (for push-back flow)
	ArrayList<Edge> edges = new ArrayList<>();
}

class Edge {

	int u , v , flow , capacity;
	Edge reverse;

	public Edge(int u , int v , int flow , int capacity) {
		this.u = u;
		this.v = v;
		this.flow = flow;
		this.capacity = capacity;
	}

	public void setReverse(Edge e) {
		reverse = e;
	}
}