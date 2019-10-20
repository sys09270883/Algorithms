#include "handler.h"

const int len = 12;

using namespace std;

int partition(int * arr, int s, int e) {
	int l = s, r = e + 1;
	int pivot = arr[l];

	do
	{
		do
		{
			l++;
		} while (l <= e && arr[l] < pivot);

		do
		{
			r--;
		} while (r >= s && arr[r] > pivot);

		if (l < r)
			swap(arr, l, r);
	} while (l < r);

	swap(arr, s, r);

	return r;
}

void quick_sort(int * arr, int s, int e) {
	if (s < e) {
		int p = partition(arr, s, e);
		quick_sort(arr, s, p - 1);
		quick_sort(arr, p + 1, e);
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

	quick_sort(arr, 0, len - 1);
	cout << " # after sort" << endl;
	print_array(arr);

	return 0;