#include "handler.h"

const int len = 12;

using namespace std;

void quick_sort(int * arr, int s, int e) {
	if (s < e) {
		int pivot = arr[(s + e) / 2];
		int l = s, r = e;

		while (l <= r) {
			while (arr[l] < pivot)
				l++;

			while (arr[r] > pivot)
				r--;

			if (l <= r) {
				swap(arr, l, r);
				l++; r--;
			}
		}

		quick_sort(arr, s, r);
		quick_sort(arr, l, e);
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