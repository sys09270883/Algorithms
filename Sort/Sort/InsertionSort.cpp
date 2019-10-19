#include "handler.h"

using namespace std;

void insertion_sort(int * arr) {
	int len = _msize(arr) / sizeof(arr);

	for (int i = 1; i < len; i++)
	{
		int tmp = arr[i];
		int cpr = i - 1;

		while (cpr >= 0 && tmp < arr[cpr]) {
			arr[cpr + 1] = arr[cpr];
			cpr--;
		}

		arr[cpr + 1] = tmp;
	}
}

int main() {
	srand((size_t)time(NULL));

	int * arr = new int[10];

	for (int i = 0; i < 10; i++)
	{
		arr[i] = rand() % 99 + 1;
	}

	cout << " # 정렬 전" << endl;
	print_array(arr);

	cout << " # 정렬 후" << endl;
	insertion_sort(arr);
	print_array(arr);

	return 0;
}