#include <stdio.h>
#include <stdlib.h>
#include <time.h>

using namespace std;

int compare(const void *a, const void *b)
{
	return *(int*)a - *(int*)b;
}

int getMax(int array[], int n)
{
	int max = array[0];
	for (int i = 1; i < n; i++) {
		if (array[i] > max)
			max = array[i];
	}
	return max;
}

int countSort(int array[], int n, int exp)
{
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

void radixSort(int array[], int n)
{
	int max = getMax(array, n);

	for (int exp = 1; max/exp > 0; exp *= 256) {
		countSort(array, n, exp);
	}
}

int main()
{
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
	radixSort(array, p);
	t4 = clock();

	float diff ((float)t2-(float)t1);
	float miseconds = (diff / CLOCKS_PER_SEC) * 1000000;
	cout << "Microseconds: " << miseconds << endl;

	return 0;
}