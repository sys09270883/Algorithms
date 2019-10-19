#pragma once
#include <iostream>
#include <vector>
#include <ctime>
#include <cstdlib>

void swap(int * arr, int i, int j) {
	int tmp = arr[i];
	arr[i] = arr[j];
	arr[j] = tmp;
}

void swap(std::vector<int> & arr, int i, int j) {
	int tmp = arr[i];
	arr[i] = arr[j];
	arr[j] = tmp;
}

void print_array(int * arr) {
	int len = _msize(arr) / sizeof(arr);

	for (size_t i = 0; i < len; i++)
	{
		std::cout << arr[i] << " ";
	}

	std::cout << "\n";
}

void print_array(std::vector<int> & arr) {
	for (size_t i = 0; i < arr.size(); i++)
	{
		std::cout << arr[i] << " ";
	}

	std::cout << "\n";
}