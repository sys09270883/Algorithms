#include "handler.h"

using namespace std;
const int len = 25;
int * new_arr = new int[len];

void merge(int * arr, int s, int e, int m) {
	int i = s, j = m + 1, k = s;

	while (i <= m && j <= e) {
		if (arr[i] < arr[j])
			new_arr[k++] = arr[i++];

		else
			new_arr[k++] = arr[j++];
	}

	while (i <= m) {
		new_arr[k++] = arr[i++];
	}

	while (j <= e) {
		new_arr[k++] = arr[j++];
	}

	for (int idx = s; idx <= e; idx++)
	{
		arr[idx] = new_arr[idx];
	}
}

void merge_sort(int * arr, int s, int e) {
	if (s < e) {
		int m = (s + e) / 2;
		// divide
		merge_sort(arr, s, m);
		merge_sort(arr, m + 1, e);
		// conquer
		merge(arr, s, e, m);
	}
}

int main() {
	srand((size_t)time(NULL));

	int * arr = new int[len];

	for (int i = 0; i < len; i++)
	{
		arr[i] = rand() % 100 + 1;
	}

	cout << " # before sort" << endl;
	print_array(arr);

	merge_sort(arr, 0, len - 1);
	cout << " # after sort" << endl;
	print_array(arr);

	return 0;
}