#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

// Implements the MST algorithm suggested by the given solution, which works 4 times faster on the last test case compared to my solution.
/*
The solution comes from a minimal spanning tree. To create K groups, you greedily remove the K-1 highest weighted edges in the tree.
    The maximal M is the highest weighted edge remaining, so you just get the (k-1)th highest weighted edge.
*/
using namespace std;

int N, K;

/*
Creates and returns an MST using Prim's algorithm
*/
vector<pair<int, int> > *createPrimsMST() {
    vector<pair<int, int> > *mst = new vector<pair<int, int> >[N]; // vector<other vertex, edge weight>
    bool keysVisited[N]; // Whether or not this key has been counted for the graph
    int keys[N]; // Key values
    int candidateEdges[N]; // Parent edges
    for (int i=0; i<N; i++) {
        keysVisited[i] = false;
        keys[i] = 2147483647;
        candidateEdges[i] = -1;
    }
    keys[0] = 0;
    for (int keysCount=0; keysCount < N; keysCount++) {
        int minKey = -1;
        // Find minimum key
        for (int i=0; i<N; i++) 
            if (!keysVisited[i] && (minKey == -1 || keys[minKey] > keys[i])) minKey = i;
        keysVisited[minKey] = true;
        // Set up keys
        for (int i=0; i<N; i++) {
            if (i!=minKey && !keysVisited[i]) {
                int buf = keys[i];
                keys[i] = min(keys[i],2019201997-84*min(i+1,minKey+1)-48*max(i+1, minKey+1));
                // Track edges
                if (keys[i] < buf) candidateEdges[i] = minKey;
            }
        }
    }
    // Set up mst
    for (int i=0; i<N; i++) {
        int other = candidateEdges[i];
        pair<int, int> newPair = make_pair(i, 2019201997-84*min(i+1, other+1)-48*max(i+1, other+1));
        if (other != -1) mst[other].push_back(newPair);
    }
    return mst;
}

int main() {
    ifstream fin("walk.in");
    ofstream fout("walk.out");
    fin >> N >> K;
    vector<pair<int, int> > *MST = createPrimsMST();
    int edges[N];
    int nextI = 0;
    for (int i=0; i<N; i++) {
        for (vector<pair<int, int> >::iterator edge=MST[i].begin(); edge!=MST[i].end(); ++edge) {
            edges[nextI] = get<1>(*edge);
            cout << nextI << endl;
            nextI++;
        }
    }
    sort(edges, edges+N);
    fout << edges[N-K+1];
}