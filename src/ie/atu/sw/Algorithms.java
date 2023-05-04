package ie.atu.sw;

import java.util.Arrays;
import java.util.Random;

public class Algorithms {
	private static Random rand = new Random(); // create Random object

	public static void main(String[] args) {
		int[] sizes = { 100, 250, 500, 750, 1000, 1250, 1500, 2500, 3750, 5000, 6250, 7500, 8750, 10000 }; //
		String results = "";

		// Bubble Sort
		for (int size : sizes) { // creating loop
			int[] arr = generateRandomArray(size); //invoke method generateRandomArray
			int[] arrCopy = arr.clone(); // creating new array which is a copy of previous
			double totalTime = 0;
			for (int i = 0; i < 10; i++) {
				long start = System.nanoTime(); // current time when algorithm start to sort array
				bubbleSort(arrCopy); // invoke Bubble sort method
				long end = System.nanoTime(); // current time when algorithm end to sort array
				totalTime += (end - start) / 1000000.0; // time which needs to sort array, when loop finish we'll get total time of 10 loops
				arrCopy = arr.clone(); // creating new array which is a copy of previous
			}
			double avgTime = totalTime / 10; // finding average time  
			results += String.format(" (%d): %.3f ms\t", size, avgTime); //
		}
		System.out.println("Bubble sort" + results);

		results = "";

		// Selection Sort
		for (int size : sizes) {
			int[] arr = generateRandomArray(size);
			int[] arrCopy = arr.clone();
			double totalTime = 0;
			for (int i = 0; i < 10; i++) {
				long start = System.nanoTime();
				selectionSort(arrCopy);
				long end = System.nanoTime();
				totalTime += (end - start) / 1000000.0;
				arrCopy = arr.clone();
			}
			double avgTime = totalTime / 10;
			results += String.format(" (%d): %.3f ms\t", size, avgTime);
		}
		System.out.println("Selection sort" + results);

		results = "";

		// Insertion Sort
		for (int size : sizes) {
			int[] arr = generateRandomArray(size);
			int[] arrCopy = arr.clone();
			double totalTime = 0;
			for (int i = 0; i < 10; i++) {
				long start = System.nanoTime();
				insertionSort(arrCopy);
				long end = System.nanoTime();
				totalTime += (end - start) / 1000000.0;
				arrCopy = arr.clone();
			}
			double avgTime = totalTime / 10;
			results += String.format(" (%d): %.3f ms\t", size, avgTime);
		}
		System.out.println("Insertion sort" + results);

		results = "";

		// Merge Sort
		for (int size : sizes) {
			int[] arr = generateRandomArray(size);
			int[] arrCopy = arr.clone();
			double totalTime = 0;
			for (int i = 0; i < 10; i++) {
				long start = System.nanoTime();
				mergeSort(arrCopy);
				long end = System.nanoTime();
				totalTime += (end - start) / 1000000.0;
				arrCopy = arr.clone();
			}
			double avgTime = totalTime / 10;
			results += String.format("  (%d): %.3f ms\t", size, avgTime);
		}
		System.out.println("Merge sort" + results);

		results = "";
		
		// Counting Sort
		for (int size : sizes) {
			int[] arr = generateRandomArray(size);
			int[] arrCopy = arr.clone();
			double totalTime = 0;
			for (int i = 0; i < 10; i++) {
				long start = System.nanoTime();
				countingSort(arrCopy);
				long end = System.nanoTime();
				totalTime += (end - start) / 1000000.0;
				arrCopy = arr.clone();
			}
			double avgTime = totalTime / 10;
			results += String.format("  (%d): %.3f ms\t", size, avgTime);
		}
		System.out.println("Counting sort" + results);

		results = "";
	}

	//https://www.tutorialspoint.com/generate-a-random-array-of-integers-in-java 
	private static int[] generateRandomArray(int size) {
		int[] arr = new int[size]; // create new object
		for (int i = 0; i < size; i++) { // use loop for from 0 till size (100-10000) to create random array
			arr[i] = rand.nextInt(1000); // storing random integers in an array
		}
		return arr; // return new random array
	}

	private static void bubbleSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	private static void selectionSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIdx]) {
					minIdx = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[minIdx];
			arr[minIdx] = temp;
		}
	}

	private static void insertionSort(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	private static void mergeSort(int[] arr) {
		int n = arr.length;
		if (n > 1) {
			int mid = n / 2;
			int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
			int[] rightArr = Arrays.copyOfRange(arr, mid, n);
			mergeSort(leftArr);
			mergeSort(rightArr);
			merge(arr, leftArr, rightArr);
		}
	}

	private static void merge(int[] arr, int[] leftArr, int[] rightArr) {
		int leftLen = leftArr.length;
		int rightLen = rightArr.length;
		int i = 0, j = 0, k = 0;
		while (i < leftLen && j < rightLen) {
			if (leftArr[i] <= rightArr[j]) {
				arr[k++] = leftArr[i++];
			} else {
				arr[k++] = rightArr[j++];
			}
		}
		while (i < leftLen) {
			arr[k++] = leftArr[i++];
		}
		while (j < rightLen) {
			arr[k++] = rightArr[j++];
		}
	}

	private static void countingSort(int[] arr) {
		int n = arr.length;
		int[] output = new int[n];

		// Find the maximum element in the array
		int max = arr[0];
		for (int i = 1; i < n; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}

		// Create a count array to store the number of occurrences of each element
		int[] count = new int[max + 1];
		for (int i = 0; i < n; i++) {
			count[arr[i]]++;
		}

		// Modify the count array to store the actual position of each element
		for (int i = 1; i <= max; i++) {
			count[i] += count[i - 1];
		}

		// Build the output array
		for (int i = n - 1; i >= 0; i--) {
			output[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--;
		}

		// Copy the output array to the input array
		for (int i = 0; i < n; i++) {
			arr[i] = output[i];
		}
	}

}
