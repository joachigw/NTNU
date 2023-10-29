#include <stdio.h>
#include <stdlib.h>

/**
 * Modulo hashing.
 * @param key the key to hash.
 * @param ht_size the size of the hash table.
 * @return the hashed int.
 */
int hash1(int key, int ht_size) {
    return key % ht_size;
}

/**
 * Modulo hashing.
 * @param key the key to hash.
 * @param ht_size the size of the hash table.
 * @return the hashed int.
 */
int hash2(int key, int ht_size) {
    return (key % (ht_size-1)) + 1;
}

/**
 * Struct definition for a hash table.
 */
typedef struct HashTable {
    int* keys;
    int size;
    int count;
    long collisions;
} HashTable;

/**
 * Creates a hash table of a given size.
 * @param size the size.
 * @return the new hash table.
 */
HashTable* create_table(int size) {
    HashTable* ht = (HashTable*) malloc(sizeof(HashTable));
    ht->size = size;
    ht->keys = (int*)malloc(sizeof(int) * size);
    ht->count = 0;
    ht->collisions = 0;

    // Initializes all indexes to hold the value 0
    for (int i = 0; i < size; i++) {
        ht->keys[i] = 0;
    }

    return ht;
}

/**
 * Inserts a key with a value into the hash table using linear probing.
 * (In this case, the key is equal to the value).
 * @param ht the hash table to receive the key (and value).
 * @param key the key.
 * @param ht_size the binary exponent of the hash table size.
 */
void ht_insert_linear_probing(HashTable* ht, int key) {
    // Calculates a start index for the key
    int index = hash1(key, ht->size);

    // Moves one by one index, and wraps around if the end of the table is reached
    while (ht->keys[index] != 0) {
        index = (index + 1) % ht->size;
        ht->collisions++;
    }

    ht->keys[index] = key;
    ht->count++;
}

/**
 * Inserts a key with a value into the hash table using double hashing.
 * (In this case, the key is equal to the value).
 * @param ht the hash table to receive the key (and value).
 * @param key the key.
 * @param ht_size the
 */
void ht_insert_double_hashing(HashTable* ht, int key) {
    // Calculates a start index for the key
    int index = hash1(key, ht->size);
    if (ht->keys[index] == 0) {
        ht->keys[index] = key;
        ht->count++;
        return;
    } else ht->collisions++;

    // Calculates a distance to the new index
    int index_jump_size = hash2(key, ht->size);

    // Moves the given index_jump_size until a free space is found
    for (;;) {
        index = (index + index_jump_size) % ht->size;
        if (ht->keys[index] == 0) {
            ht->keys[index] = key;
            ht->count++;
            return;
        } else ht->collisions++;
    }
}