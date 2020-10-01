#include <iostream>
#include <fstream>

using namespace std;

int main() {
	ifstream fin("lostcow.in");
	ofstream fout("lostcow.out");
	int x, y;
	fin >> x >> y;
	int dist = 0;
	for (int i=1; ; i*=-2) {
		cout << x + i << endl;
		if ((x+i <= y && y <= x) || (x+i >= y && y >= x)) {
			fout << dist + abs(y - (x-i/2));
			break;
		}
		else {
			dist += abs(i) + abs(i/2);
		}
	}
}