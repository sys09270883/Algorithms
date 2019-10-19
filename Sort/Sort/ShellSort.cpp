#include "handler.h"

using namespace std;

const int len = 11;

void insertion_sort(int * arr, int start, int end, int gap) {
	for (size_t i = start + gap; i <= end; i += gap)
	{
		int tmp = arr[i];
		int cpr = i - gap;

		while (cpr >= 0 && tmp < arr[cpr]) {
			arr[cpr + gap] = arr[cpr];
			cpr -= gap;
		}

		arr[cpr + gap] = tmp;
	}
}

void shell_sort(int * arr) {
	int gap;

	for (int gap = len / 2; gap > 0; gap >>= 1)
	{
		if (gap % 2 == 0)
			gap++;

		for (int i = 0; i < gap; i++)
		{
			insertion_sort(arr, i, len - 1, gap);
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

	cout << " # 정렬 전" << endl;
	print_array(arr);

	shell_sort(arr);
	cout << " # 정렬 후" << endl;
	print_array(arr);

	return 0;
}