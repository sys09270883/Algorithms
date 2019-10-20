#include "handler.h"

using namespace std;

const int len = 8;

vector<int> init() {
	vector<int> heap;
	heap.push_back(NULL);
	return heap;
}

void heap_insert(vector<int> & heap, int input) {
	heap.push_back(input);
	int idx = heap.size() - 1;

	while (idx != 1 && heap[idx] < heap[idx >> 1]) {
		swap(heap, idx, idx >> 1);
		idx >>= 1;
	}
}

int heap_delete(vector<int> & heap) {
	int min_val = heap[1], parent = 1, child = 2, heap_len = heap.size() - 1;
	swap(heap, 1, heap_len);
	heap.pop_back();
	heap_len--;

	if (child + 1 <= heap_len)
		child = (heap[child] < heap[child + 1]) ? child : child + 1;

	while (child <= heap_len && heap[parent] > heap[child]) {
		swap(heap, parent, child);

		parent = child;
		child <<= 1;

		if (child + 1 <= heap_len)
			child = (heap[child] < heap[child + 1]) ? child : child + 1;
	}

	return min_val;
}

void heap_sort(vector<int> & arr, int len) {
	vector<int> heap = init();

	for (int i = 0; i < len; i++)
	{
		heap_insert(heap, arr[i]);
	}

	for (int i = 0; i < len; i++)
	{
		arr[i] = heap_delete(heap);
	}
}

int main() {
	srand((size_t)time(NULL));

	vector<int> arr;

	for (int i = 0; i < len; i++)
	{
		arr.push_back(rand() % 99 + 1);
	}

	cout << " # before sort" << endl;
	print_array(arr);

	heap_sort(arr, len);
	cout << " # after sort" << endl;
	print_array(arr);

	return 0;
}