#include "handler.h"

using namespace std;

const int len = 8;

void bubble_sort(int * arr) {
	int len = _msize(arr) / sizeof(int);

	for (int i = 0; i < len - 1; i++)
	{
		for (int j = 0; j < len - 1 - i; j++)
		{
			if (arr[j] > arr[j + 1])
				swap(arr, j, j + 1);
		}
	}
}

void bubble_sort2(int * arr) {
	int len = _msize(arr) / sizeof(int);

	bool flag = true;
	for (int i = 0; i < len - 1 && flag; i++)
	{
		flag = false;
		for (int j = 0; j < len - 1 - i; j++)
		{
			if (arr[j] > arr[j + 1]) {
				swap(arr, j, j + 1);
				flag = true;
			}
		}
	}
}

int main() {
	srand((size_t)time(NULL));

	int * arr = new int[len];

	for (int i = 0; i < len; i++)
	{
		arr[i] = rand() % 99 + 1;
	}

	cout << " # before sort" << endl;
	print_array(arr);

	//bubble_sort(arr);
	bubble_sort2(arr);
	cout << " # after sort" << endl;
	print_array(arr);

	return 0;
}
