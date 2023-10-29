#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE 181

/**
 * Hashes the given string.
 * @param string the string to be hashed.
 * @return the string hashed to an int.
 */
int hash(char* string) {
    int hash = 0;
    for (int i = strlen(string); i-- > 0;)
        hash = ((7 * hash) + string[i]) % SIZE;

    return hash;
}

/**
 * Struct definition for a hash table item.
 */
typedef struct Ht_item {
    int key;
    char* value;
} Ht_item;

/**
 * Struct definition for a linked list.
 */
typedef struct LinkedList {
    Ht_item* item;
    struct LinkedList* next;
} LinkedList;

/**
 * Struct definition for a hash table.
 */
typedef struct HashTable {
    Ht_item** items;
    LinkedList** linked_lists;
    int size;
    int count;
    int collisions;
} HashTable;

/**
 * Allocates memory space for a linked list.
 * @return
 */
LinkedList* alloc_list() {
    LinkedList* list = (LinkedList*) malloc(sizeof(LinkedList));
    return list;
}

/**
 * Inserts a hash table item into a linked list.
 * @param list the list to insert into.
 * @param item the item to insert.
 * @return the new linked list with the new element.
 */
LinkedList* list_insert(LinkedList* list, Ht_item* item) {
    if (!list) {
        LinkedList* head = alloc_list();
        head->item = item;
        head->next = NULL;
        list = head;
        return list;
    } else if (list->next == NULL) {
        LinkedList* node = alloc_list();
        node->item = item;
        node->next = NULL;
        list->next = node;
        return list;
    }

    LinkedList* temp = list;

    while (temp->next->next)
        temp = temp->next;

    LinkedList* node = alloc_list();
    node->item = item;
    node->next = NULL;
    temp->next = node;
    return list;
}

/**
 * Frees the memory space used by the linked list.
 * @param list
 */
void list_free(LinkedList* list) {
    LinkedList* temp = list;

    while (list) {
        temp = list;
        list = list->next;
        free(temp);
    }
}

/**
 * Creates new linked lists in the given hash table's indexes.
 * @param table the table to receive the linked lists.
 * @return the linked lists.
 */
LinkedList** list_create(HashTable* table) {
    LinkedList** lists = (LinkedList**)calloc(table->size, sizeof(LinkedList*));

    for (int i = 0; i < table->size; i++)
        lists[i] = NULL;

    return lists;
}

/**
 * Frees all linked lists in the given hash table.
 * @param table the table.
 */
void list_free_overflow(HashTable* table) {
    LinkedList** lists = table->linked_lists;

    for (int i = 0; i < table->size; i++)
        list_free(lists[i]);

    free(lists);
}

/**
 * Creates a new hash table item.
 * @param key the int key.
 * @param value the value of the item.
 * @return the created hash table item.
 */
Ht_item* create_item(int key, char* value) {
    Ht_item* item = (Ht_item*) malloc(sizeof(Ht_item));
    item->key = key;
    item->value = strdup(value);
    return item;
}

/**
 * Creates a new hash table based on the set constant 'SIZE'.
 * @return the new hash table.
 */
HashTable* create_table() {
    HashTable* table = (HashTable*)malloc(sizeof(HashTable));
    table->size = SIZE;
    table->count = 0;
    table->collisions = 0;
    table->items = (Ht_item**)calloc(table->size, sizeof(Ht_item));

    for (int i = 0; i < table->size; i++)
        table->items[i] = NULL;

    table->linked_lists = list_create(table);

    return table;
}

/**
 * Frees the memory where the table was stored.
 * @param table the table to free the memory of.
 */
void free_table(HashTable* table) {
    for (int i = 0; i < table->size; i++) {
        Ht_item* item = table->items[i];
        if (item != NULL)
            free(item);
    }

    list_free_overflow(table);
    free(table->items);
    free(table);
}

/**
 * Handles index collisions (when the same key is generated more than once).
 * @param table the table.
 * @param index the index of where the collision happened.
 * @param item the new item that caused the collision.
 */
void handle_collision(HashTable* table, int index, Ht_item* item) {
    LinkedList* head = table->linked_lists[index];

    if (head == NULL) {
        printf("# Collision at index %d between names '%s' and '%s'\n",
               index, table->items[index]->value, item->value);
        head = alloc_list();
        head->item = item;
        table->linked_lists[index] = head;
    } else {
        printf("# Collision at index %d between names '%s' and '%s'\n",
               index, head->item->value, item->value);

        table->linked_lists[index] = list_insert(head, item);
    }
}

/**
 * Inserts a new hash table item into the hash table on a given index.
 * Calls handle_collision() if there occurs a collision on an index.
 * @param table the table to insert the item into.
 * @param key the item's key.
 * @param value the item's value.
 */
void ht_insert(HashTable* table, char* key, char* value) {
    int index = hash(key);

    Ht_item* current_item = table->items[index];

    if (current_item == NULL) {
        if (table->count == table->size) {
            printf("Error: The table is full!");
            return;
        }

        table->items[index] = create_item(index, value);
        table->count++;
    } else {
        if (current_item->key == index) {
            handle_collision(table, index, create_item(index, value));
            table->collisions++;
            table->count++;
            return;
        } else {
            return;
        }
    }
}

/**
 * Searches for a given string inside of a table, by key (and value if the index has established a linked list).
 * @param table the table to search in.
 * @param key the key to search by.
 * @param value the expected value
 *              (used as a second search argument when encountering a linked list on the same index/key).
 * @return the string if found, else NULL.
 */
char* ht_search(HashTable* table, char* key, char* value) {
    int index = hash(key);
    Ht_item* item = table->items[index];
    LinkedList* head = table->linked_lists[index];

    if ((item != NULL) && (item->key == index) && (strcmp(item->value, value) == 0)) {
        return item->value;
    }
    else if (head != NULL) {
        LinkedList* current = head;
        if (strcmp(item->value, value) != 0) {
            printf("# Collision between the names '%s' and '%s' during search at index %d!\n",
                   value, item->value, index);
        }
        while (current != NULL) {
            if ((current->item->key == index) && (strcmp(current->item->value, value) == 0)) {
                return current->item->value;
            }
            printf("# Collision between the names '%s' and '%s' during search!\n\n",
                   value, current->item->value);
            current = current->next;
        }
    }

    return NULL;
}

/**
 * Prints the results of the ht_search()-function.
 * @param table the table that was searched.
 * @param key the key that was used to search.
 * @param value the value that was used to search.
 */
void print_search(HashTable* table, char* key, char* value) {
    printf("\nSEARCHING FOR '%s'...\n------------------------------------------------\n", value);
    char* val = ht_search(table, key, value);
    int hashed_key = hash(key);

    if (val == NULL) {
        printf("Key: %s does not exist!\n", key);
    }
    else {
        printf("\xE2\x9C\x93 A match was found!\nKey: %d, Value: '%s'\n", hashed_key, val);
    }
    printf("------------------------------------------------\n\n\n");
}

/**
 * Prints all of the table's contents, line by lin.
 * @param table the table to print out.
 */
void print_table(HashTable* table) {
    printf("\n\nHASH TABLE\n------------------------------------------------\n");

    for (int i = 0; i < table->size; i++) {
        if (table->items[i] != NULL) {
            printf("Index: %d, Key: %d, Value: '%s'\n",
                   i, table->items[i]->key, table->items[i]->value);

            LinkedList* head = table->linked_lists[i];
            if (head != NULL) {
                printf("    Linked List:\n");
                LinkedList* current = head;
                while (current != NULL) {
                    printf("        Key: %d, Value: '%s'\n",
                           current->item->key, current->item->value);
                    current = current->next;
                }
            }
        }
    }

    printf("------------------------------------------------\n\n");
}

/**
 * Main function.
 * @return 0 if successfully run.
 */
int main() {
    HashTable* ht = create_table();
    FILE* file = fopen("navn.txt", "r");
    char line[256];

    if (file == NULL) {
        perror("Error opening the file");
        return 1;
    }

    printf("\nCOLLISIONS DURING INSERTION\n------------------------------------------------\n");
    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\n")] = '\0';
        ht_insert(ht, line, line);
    }
    fclose(file);
    printf("------------------------------------------------\n\n");

    print_table(ht);

    char* name_to_search = "Per Henrik Bergene Holm";
    print_search(ht, name_to_search, name_to_search);

    int number_of_names = ht->count;
    double collisions_per_name = ((double) ht->collisions / (double) number_of_names);

    printf("SUMMARY\n------------------------------------------------\n");
    printf("---> Number of collisions during insertions: %d\n", ht->collisions);
    printf("---> Load factor \u03B1: %f\n", ((double) number_of_names / (double) SIZE));
    printf("---> Collisions per name: %f\n", collisions_per_name);
    printf("------------------------------------------------\n");

    free_table(ht);
    return 0;
}