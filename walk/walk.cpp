#include <iostream>
#include <fstream>
#include <stack>
#include <time.h>

/*
First, constructs an edge list with each edge having the label representing (2019201913x + 2019201949y) mod 2019201907, or 7x + 4y for 
    simplicity. This takes quadratic time.
Then, does a binary search for M, using the M as an upper bound for the groups
For each iteration of the search, it checks if there exist at least K different groups.
I believe this is O(N^2 log(N)). The k is simply used as an end condition.

There are a few optimizations since it kept timing out on just the last test case. Now it makes it with some milliseconds to spare and only 
    makes it most of the time, so there is definitely a better way to do this.
*/

using namespace std;

int N, K;
int **walkTimes;
int *validityResults;

bool isValidM(int m) {
    cout << m << endl;
    if (validityResults[m] != -1) return validityResults[m]%2 == 0? true:false;
    bool visited[N];
    int groups = 0;
    for (int i=0; i<N; i++) visited[i] = false;
    // Count groups
    for (int i=0; i<N; i++) {
        bool isNewGroup = true;
        visited[i] = true;
        for (int k=0; k<N; k++) {
            if (!visited[k] && walkTimes[i][k] > m && i != k) visited[k] = true;
            else if (visited[k] && walkTimes[i][k] > m && i != k) isNewGroup = false;
        }
        if (isNewGroup) groups++;
        if (groups >= K) {
            validityResults[m] = 0;
            return true;
        }
    }
    validityResults[m] = 1;
    return false;
}

int solveM(int start, int end) {
    int check = (start+end)/2;
    //cout << start << " " << check << " " << end << endl;
    if (start==check || end==check) return check+1;
    if (isValidM(check)) return solveM(start, check);
    else                 return solveM(check, end);
}

int main() {
    time_t seconds = time(NULL);
    ifstream fin("walk.in");
    ofstream fout("walk.out");
    fin >> N >> K;
    walkTimes = new int*[N];
    validityResults = new int[11*N+1];
    for (int i=0; i<11*N+1; i++) validityResults[i] = -1;
    for (int x=0; x<N; x++) {
        walkTimes[x] = new int[N];
        for (int y=0; y<N;y++) walkTimes[x][y] = 7*min(x+1, y+1) + 4*max(x+1, y+1);
    }
    fout << 2019201997 - 12*solveM(0, 11*N);
    cout << time(NULL) - seconds << endl;
}