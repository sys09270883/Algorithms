#include "handler.h"

using namespace std;

const int len = 10;

void selection_sort(int * arr) {
	for (int i = 0; i < len - 1; i++)
	{
		int tmp = i;

		for (int j = i + 1; j < len; j++)
		{
			if (arr[tmp] > arr[j])
				tmp = j;
		}

		swap(arr, tmp, i);
	}
}

void selection_sort2(int * arr) {
	for (int i = len - 1; i >= 1; i--)
	{
		int tmp = i;

		for (auto j = i - 1; j >= 0; j--)
		{
			if (arr[tmp] < arr[j])
				tmp = j;
		}

		swap(arr, tmp, i);
	}
}

int main() {
	srand((size_t)time(NULL));

	int * arr = new int[len];

	for (int i = 0; i < len; i++)
	{
		arr[i] = rand() % 99 + 1;
	}

	cout << " # 정렬 전" << endl;
	print_array(arr);

	//selection_sort(arr);
	selection_sort2(arr);
	cout << " # 정렬 후 " << endl;
	print_array(arr);

	return 0;
}
