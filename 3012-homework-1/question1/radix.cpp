#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <iostream>

using namespace std;

void rsor(int array[], int n) {
	int big = bigval(array, n);
	for (int exp = 1; big/exp > 0; exp *= 256) {
		csor(array, n, exp);
	}
}

int csor(int array[], int n, int exp) {
	int i;
	int output[n];
	int count[256] = {0};
	for (i = 0; i < n; i++) {
		count[ array[i] / exp % 256 ]++;
	}
	for (i = 1; i < 256; i++) {
		count[i] += count[i - 1];
	}
	for (i = n - 1; i >= 0; i--) {
		output[count[array[i] / exp % 256] - 1] = array[i];
		count[array[i] / exp % 256]--;
	}
	for (i = 0; i < n; i++) {
		array[i] = output[i];
	}
}

int bigval(int array[], int n) {
	int big = array[0];
	for (int i = 1; i < n; i++) {
		if (array[i] > big)
			big = array[i];
	}
	return big;
}

int compare(const void *a, const void *b) {
	return *(int*)a - *(int*)b;
}

int main() {
	short int x = 65535;
	int k = 100;
	int values[k];
	int array[k];
	clock_t t1, t2;
	clock_t t3, t4;
	for (int i = 0; i < k; i++) {
		values[i] = rand() % x;
	}
	t1 = clock();
	qsort(values, k, sizeof(int), compare);
	t2 = clock();
	for (int i = 0; i < k; i++) {
		values[i] = rand() % x;
	}
	int p = sizeof(array) / sizeof(array[0]);
	t3 = clock();
	rsor(array, p);
	t4 = clock();
	float diff ((float)t2-(float)t1);
	float miseconds = (diff / CLOCKS_PER_SEC) * 1000000;
    float diff2 ((float)t3-(float)t4);
	float miseconds2 = (diff2 / CLOCKS_PER_SEC) * 1000000;
	cout << "Microseconds for qsort: " << miseconds << endl;
    cout << "Microseconds for rsort: " << miseconds2 << endl;
	return 0;
}