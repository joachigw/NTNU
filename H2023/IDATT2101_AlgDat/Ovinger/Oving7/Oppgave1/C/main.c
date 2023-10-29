#include <stdio.h>
#include <string.h>

// Define the maximum number of nodes in the graph (and practically the maximum number of edges as well)
#define MAX_NODES 1000

// Initialize arrays for nodes' parents, the current path capacity, capacities and the passed flow as tables
int parents_list[MAX_NODES];
int current_path_capacity[MAX_NODES];
int capacities[MAX_NODES][MAX_NODES];
int flow_passed[MAX_NODES][MAX_NODES];

/**
 * Queue implementation.
 */
typedef struct {
    int items[MAX_NODES];
    int front, rear, size;
} Queue;

/**
 * Initialize the queue.
 * @param queue the queue to initialize
 */
void init_queue(Queue* queue) {
    queue->front = 0;
    queue->rear = -1;
    queue->size = 0;
}

/**
 * Add an item to the queue.
 * @param queue the queue to add to
 * @param item the item to add
 */
void enqueue(Queue* queue, int item) {
    queue->items[++queue->rear] = item;
    queue->size++;
}

/**
 * Remove an item from the queue.
 * @param queue the queue to remove from
 * @return the removed item
 */
int dequeue(Queue* queue) {
    int item = queue->items[queue->front++];
    queue->size--;
    return item;
}

/**
 * Find the minimum of two integers.
 * @param a integer a
 * @param b integer b
 * @return the minimum of a and b
 */
int min(int a, int b) {
    return (a < b) ? a : b;
}

/**
 * Breadth-first search algorithm for finding paths from the source node to the sink node.
 * @param source the source node
 * @param sink the sink node
 * @return the capacity of the path from source to sink
 */
int bfs(int source, int sink) {

    // Initialize the parents_list and current_path_capacity arrays to -1 and 0 respectively
    memset(parents_list, -1, sizeof(parents_list));
    memset(current_path_capacity, 0, sizeof(current_path_capacity));

    // Initialize the queue and add the source node to it
    Queue queue;
    init_queue(&queue);
    enqueue(&queue, source);

    // Set the source node's parent to -2 (to distinguish it from the other nodes)
    // and its current path capacity to 999 (because the source node has no parent and the path capacity is "infinite")
    parents_list[source] = -2;
    current_path_capacity[source] = 999;

    // While the queue is not empty, dequeue the first item and check if it is the sink node
    // Update the parents_list and current_path_capacity arrays accordingly
    // If the current node is the sink node, return the current path capacity
    while (queue.size > 0) {
        int current_node = dequeue(&queue);

        for (int to = 0; to < MAX_NODES; to++) {
            if (parents_list[to] == -1) {
                if (capacities[current_node][to] - flow_passed[current_node][to] > 0) {
                    parents_list[to] = current_node;
                    current_path_capacity[to] = min(current_path_capacity[current_node],
                                                  capacities[current_node][to] - flow_passed[current_node][to]);

                    if (to == sink) {
                        return current_path_capacity[sink];
                    }

                    enqueue(&queue, to);
                }
            }
        }
    }
    // If the sink node was not found, return 0
    return 0;
}

/**
 * Edmonds-Karp algorithm for finding the max flow from a source node to a sink node.
 * @param source the source node
 * @param sink the sink node
 * @return the max flow from source to sink
 */
int edmonds_karp(int source, int sink) {
    int max_flow = 0;

    // Infinite loop until there are no more paths from source to sink
    while (1) {
        int flow = bfs(source, sink);
        if (flow == 0) {
            break;
        }

        // Initialize the current node to the sink node and the path to an empty array
        int current_node = sink;
        int path[MAX_NODES];
        int path_length = 0;

        // Update the flow_passed array and the path array until the current node is the source node
        while (current_node != source) {
            int previous_node = parents_list[current_node];
            flow_passed[previous_node][current_node] += flow;
            flow_passed[current_node][previous_node] -= flow;
            path[path_length++] = current_node;
            current_node = previous_node;
        }
        path[path_length++] = source;

        printf("%d : ", flow);

        // Print the path in the "correct" order
        for (int i = path_length - 1; i >= 0; i--) {
            printf("%d ", path[i]);
        }
        printf("\n");

        max_flow += flow;
    }

    return max_flow;
}

/**
 * Main function.
 * @return 0 if the program ran successfully
 */
int main() {

    int source_node = 0;
    int sink_node = 1;

    // Open the file containing the graph
    FILE* f = fopen("flytgraf2.txt", "r");
    if (f == NULL) {
        printf("File not found.\n");
        return 1;
    }
    // Read the number of nodes and edges from the file
    int num_nodes, num_edges;
    fscanf(f, "%d %d\n", &num_nodes, &num_edges);

    // Initialize the capacities and flow_passed arrays to contain only zeroes
    memset(capacities, 0, sizeof(capacities));
    memset(flow_passed, 0, sizeof(flow_passed));

    // Read edges from file and save them in capacities array
    for (int i = 0; i < num_edges; i++) {
        int from, to, weight;
        fscanf(f, "%d %d %d\n", &from, &to, &weight);
        capacities[from][to] = weight;
    }
    fclose(f);

    // Check if the source and sink nodes are valid
    if (source_node < 0 || source_node >= num_nodes || sink_node < 0 || sink_node >= num_nodes) {
        printf("Invalid source or sink.\n");
        return 1;
    }

    // Print the max flow from source to sink using Edmonds-Karp algorithm
    printf("\nMax flow from node %d to node %d with Edmonds-Karp\n", source_node, sink_node);
    printf("Increase : Path\n");
    int max_flow = edmonds_karp(source_node, sink_node);
    printf("Max flow: %d\n", max_flow);

    return 0;
}
