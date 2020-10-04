#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

/*
Does a binary search on D to find the maximum valid D. For every D tested, it uses a greedy algorithm to test the validity.
*/

int N, M;
int intervalsStart[(int)1e5];
int intervalsEnd[(int)1e5];

// O(N+M)
bool isValidD(int D) {
	int currentInterval = 0, currentPos=intervalsStart[0], currentCow = 1;
	for (currentCow=1; currentCow < N; currentCow++) {
		if (currentPos + D > intervalsEnd[M-1]) return false;
		while (currentPos+D > intervalsEnd[currentInterval]) currentInterval++;
		currentPos = currentPos + D > intervalsStart[currentInterval]? currentPos + D : intervalsStart[currentInterval];
	}
	return true;
}

// O(log(intervalsEnd[M-1])) if isValidD is O(1)
int binSearchD(int start, int end) {
	int check = (start+end)/2;
	if (isValidD(check)) return isValidD(check+1)? binSearchD(check, end) : check;
	else				 return binSearchD(start, check);
}

int main() {
	ifstream fin("socdist.in");
	ofstream fout("socdist.out");
	fin >> N >> M;
	for (int i=0; i<M; i++) {
		fin >> intervalsStart[i];
		fin >> intervalsEnd[i];
	}
	// It isn't necessarily in order >:( we need it in order.
	sort(intervalsStart, intervalsStart + M);
	sort(intervalsEnd, intervalsEnd + M);
	fout << binSearchD(0, intervalsEnd[M-1]);
}
