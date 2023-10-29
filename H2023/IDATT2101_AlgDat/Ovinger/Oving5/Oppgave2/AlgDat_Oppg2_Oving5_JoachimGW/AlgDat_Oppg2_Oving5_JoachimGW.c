#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "hashtable.h"

#define TABLE_SIZE 10000019

/**
 * Shuffles the numbers array.
 * @param array the array to shuffle.
 * @param size the size of the array to shuffle.
 */
void shuffle(int* array, int size) {
    for (int i = size - 1; i > 0; i--) {
        int j = rand() % (i + 1);
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

int* create_numb_array(int floor, int ceiling) {
    int* numb_array = (int*) malloc(sizeof(int) * TABLE_SIZE);
    numb_array[0] = 1;
    for (int i = 1; i < TABLE_SIZE; i++) {
        numb_array[i] = numb_array[i-1] + (rand() % (ceiling - floor + 1)) + floor;
    }
    return numb_array;
}

double measure_linear_probing(HashTable* ht1, int* numb_array, double load_factor) {
    clock_t linear_start = clock();
    for (int i = 0; i < TABLE_SIZE*load_factor; i++) {
        if (ht1->count == ht1->size) {
            printf("The hash table is full!\n");
            break;
        }
        ht_insert_linear_probing(ht1, numb_array[i]);
    }
    clock_t linear_end = clock();
    return ((double) (linear_end - linear_start)) / CLOCKS_PER_SEC;
}

double measure_double_hashing(HashTable* ht2, int* numb_array, double load_factor) {
    clock_t double_hash_start = clock();
    for (int i = 0; i < TABLE_SIZE*load_factor; i++) {
        if (ht2->count == ht2->size) {
            printf("The hash table is full!\n");
            break;
        }
        ht_insert_double_hashing(ht2, numb_array[i]);
    }
    clock_t double_hash_end = clock();
    return ((double) (double_hash_end - double_hash_start)) / CLOCKS_PER_SEC;
}

int main(int argc, char *argv[]) {
    srand(1);
    int floor = 10;
    int ceiling = 100;
    double load_factor = 0.7;

    // Check for command line arguments when the program is run from the terminal
    if (argc > 1) {
        if (sscanf(argv[1], "%lf", &load_factor) != 1 || load_factor == 0.0 || load_factor < 0.0) {
            printf("Invalid input for load factor. Please enter a valid non-zero double.\n");
            return 1; // Exit with an error code
        }
    }

    // Initialize the hash tables for linear probing (ht1) and double hashing (ht2)
    HashTable* ht1 = create_table(TABLE_SIZE);
    HashTable* ht2 = create_table(TABLE_SIZE);

    // Create an array with numbers from 1 to TABLE_SIZE and shuffle it
    int* numb_array = create_numb_array(floor, ceiling);
    shuffle(numb_array, TABLE_SIZE);

    // Inserts from numb_array into hash table with linear probing and measures time spent
    double linear_time_measured = measure_linear_probing(ht1, numb_array, load_factor);

    // Inserts from numb_array into hash table with double hashing and measures time spent
    double double_hash_time_measured = measure_double_hashing(ht2, numb_array, load_factor);

    // Print the current load factor
    printf("LOAD FACTOR: %.2f\n\n", ((double)ht2->count / (double) TABLE_SIZE));

    // Print the results of the insertions
    printf("LINEAR PROBING\n----------------------------------------\n");
    printf("Time measured (in milliseconds): %.3f\n", linear_time_measured*1000);
    printf("Number of collisions: %lu\n----------------------------------------\n\n", ht1->collisions);

    // Print the amount of collisions that occurred during each insertion method
    printf("DOUBLE HASHING\n----------------------------------------\n");
    printf("Time measured (in milliseconds): %.3f\n", double_hash_time_measured*1000);
    printf("Number of collisions: %lu\n----------------------------------------\n", ht2->collisions);

    // Frees the dynamically allocated memory to prevent memory leaks
    free(ht1->keys);
    free(ht1);
    free(ht2->keys);
    free(ht2);
    free(numb_array);

    return 0;
}