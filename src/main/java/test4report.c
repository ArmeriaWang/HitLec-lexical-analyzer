function float sqrt(int x) {
    return 0.1;
}

function int getMin(int x, int y) {
    if (x < y) {
        return y;
    } else {
        return x;
    }
}

function int* getMinAddr(int a[], int len) {
    int i = 0, minVal = 0x7fffffff;
    int *ret = 0;
    while (i < len) {
        if (a[i] < minVal) {
            minVal = a[i];
            ret = &a[i];
        }
    }
    return ret;
}

function int printNode(struct Node *node, int times) {
    int time = 0;
    while (time < times) {
        printf("%d %d %f\n", from, to, weight);
        time = time + 1;
    }
    return 0;
}

struct Node {
    struct Node *from, *to;
    float weight;
};

int global_init = 123, global_non_init;

function int main() {
    struct Edge {
        int from, to;
        float weight;
        struct Node from_node[20], to_node;
    };
    struct Edge edge[100];
    int x = getMin(edge[0].from, edge[1].from);

    int arr[50];
    int minAddr = getMinAddr(arr, 50);

    struct Node node;
    printNode(&node, 0777);

    struct Edge *hd_arr[3][3 * 3][2 + 0x3fff % 077];
    hd_arr[0][1][2] = &edge[3];

    edge[99].from_node[1].weight = 1.123e7;

    return 0;
}