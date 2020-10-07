#include <iostream>
#include <fstream>
#include <vector>
#include <cstring>

using namespace std;
int *leftIndices;
vector<int> left1sIndices;
int left1sCnt=0;
long long leftInversions=0;
int *rightIndices;
vector<int> right1sIndices;
int right1sCnt=0;
long long rightInversions=0;
int N; // FUIDSABVKSFHIUWEAHSDUFVHN DIABFUIDASB IFBS IFADIUSA BZDIFISDUB ABDISABF IUSDABF IUSDNFJNGCXKNV KJSNEFUSDNZUSNF isdN we WIS HW YOU A ME RR ycHRISTMAS WE WIH  YOU A MERRY chRISTMAS WE WISHT OYU W AMA RRY

/*
4 rules:
 1. 1 <-> 0 on same half decreases inversions by 1
 2. 0 <-> 1 on same half increases inversions by 1
 3. 1 <-> 0 on center increases inversions on left by # of 1's on left, increases on right by # of 0's on right.
 4. 0 <-> 1 on center decreases inversions on left by # of 1's on left, decreases on right by # of 0's on right.
Assume we don't make any operations of 3 and 4. Then the minimum number of swaps is |leftInversions - rightInversions|
This means we just iterate on how many operations of 3 and 4 you make.
It is also safe to assume that, in the minimal-swapping sequence, we will not be using both 3 and 4 in the same sequence, and that we will not be switching two zeroes or two ones.
It is also much easier to think of it as moving around 1's rather than swapping 1's and 0's.
*/

long long solveRightIncrement() {
	int *lefts = new int[N];
	int *rights = new int[N];
	memmove(lefts, leftIndices, N*sizeof(int));
	memmove(rights, rightIndices, N*sizeof(int));
	long long rightIncrementIndex=0,  left1s=left1sCnt, right1s=right1sCnt;
	long long rightInv=rightInversions, leftInv=leftInversions, minimum=9223372036854775807;
	long long swapsMade=0;
	for (vector<int>::reverse_iterator left1Switching=left1sIndices.rbegin();left1Switching!=left1sIndices.rend();++left1Switching) {
		while (rightIncrementIndex < N && rights[rightIncrementIndex]==1) rightIncrementIndex++;
		if (rightIncrementIndex==N) break;
		// Perform preliminary incremental switches
		rightInv -= rightIncrementIndex;
		swapsMade += rightIncrementIndex;
		leftInv -= N-1-*left1Switching;
		swapsMade += N-1-*left1Switching;
		// Perform center switch
		leftInv += left1s-1;
		rightInv += N-right1s-1;
		lefts[*left1Switching] = 0;
		rights[rightIncrementIndex] = 1;
		swapsMade++;
		// Set minimum
		minimum = min(minimum, swapsMade+abs(leftInv-rightInv));
		// Increment
		left1s--;
		right1s++;
	}
	return minimum;
}

long long solveLeftIncrement() {
	int *lefts = new int[N];
	int *rights = new int[N];
	memmove(lefts, leftIndices, N*sizeof(int));
	memmove(rights, rightIndices, N*sizeof(int));
	long long leftIncrementIndex = N-1, minimum=9223372036854775807, rightInv=rightInversions, leftInv=leftInversions, left1s=left1sCnt, right1s=right1sCnt;
	long long swapsMade=0;
	for (vector<int>::iterator right1Switching=right1sIndices.begin();right1Switching!=right1sIndices.end();++right1Switching) {
		while (leftIncrementIndex >= 0 && lefts[leftIncrementIndex]==1) leftIncrementIndex--;
		if (leftIncrementIndex==-1) break;
		// Perform preliminary incremental switches.
		leftInv += N-1-leftIncrementIndex;
		swapsMade += N-1-leftIncrementIndex;
		rightInv += *right1Switching;
		swapsMade += *right1Switching;
		// Perform center switch
		leftInv -= left1s;
		rightInv -= N-right1s;
		lefts[leftIncrementIndex] = 1;
		rights[*right1Switching] = 0;
		swapsMade++;
		// Set minimum
		minimum = min(minimum, swapsMade+abs(leftInv-rightInv));
		// Increment
		left1s++;
		right1s--;
	}
	return minimum;
}

int main() {
	ifstream fin("balance.in");
	ofstream fout("balance.out");
	fin >> N;
	leftIndices = new int[N];
	rightIndices = new int[N];
	int count1s = 0;
	for (int i=0; i<N; i++) {
		fin >> leftIndices[i];
		count1s += leftIndices[i];
		if (leftIndices[i] == 0) leftInversions += count1s;
		else					 left1sIndices.push_back(i);
	}
	left1sCnt = count1s;
	count1s = 0;
	for (int i=0; i<N; i++) {
		fin >> rightIndices[i];
		count1s += rightIndices[i];
		if (rightIndices[i] == 0) rightInversions += count1s;
		else					  right1sIndices.push_back(i);
	}
	right1sCnt = count1s;
	fout << min(min(solveRightIncrement(), solveLeftIncrement()), abs(rightInversions-leftInversions));
	return 0;
}