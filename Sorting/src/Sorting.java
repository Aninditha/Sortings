import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sorting {

	static int small, large, median;

	private static int medianOfThree(int[] array, int first, int last) {

		int mid = (first + last) / 2;

		if (array[first] > array[mid]) {
			large = first;
			small = mid;
		} else if (array[first] <= array[mid]) {
			large = mid;
			small = first;
		}

		if (array[last] < array[small]) {
			median = small;
		} else if (array[last] > array[large]) {
			median = large;
		} else
			median = last;

		swap(array, median, last);
		return median;
	}

	// This method is used to partition the given array and returns the integer
	// which points to the sorted pivot index
	private static int partition(int[] array, int left, int right, int pivot) {
		int leftCursor = left - 1;
		int rightCursor = right;
		while (leftCursor < rightCursor) {
			while (array[++leftCursor] < pivot)
				;
			while (rightCursor > 0 && array[--rightCursor] > pivot)
				;
			if (leftCursor >= rightCursor) {
				break;
			} else {
				swap(array, leftCursor, rightCursor);
			}
		}
		swap(array, leftCursor, right);
		return leftCursor;
	}

	// This method is used to swap the values between the two given index
	public static void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}

	private static void quickSort(int[] array, int first, int last) {

		if (last - first <= 10)
			insertionSort(array, first, last);
		else if (first < last) {
			medianOfThree(array, first, last);
			int pivot = array[last];
			pivot = partition(array, first, last, pivot);

			quickSort(array, first, pivot - 1);
			quickSort(array, pivot + 1, last);
		}
	}

	public static void insertionSort(int array[], int first, int last) {

		for (int j = first; j <= last; j++) {
			int key = array[j];
			int i = j - 1;
			while ((i > -1) && (array[i] > key)) {
				array[i + 1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
	}

	public static void main(String[] args) throws IOException {

		int arraySize;
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

		// Reading the Input size from user
		System.out.print("Enter array size: ");
		String userCommand = bufferRead.readLine();
		arraySize = Integer.parseInt(userCommand);

		int[] array = new int[arraySize];

		// Reading the array elements
		System.out.print("\nDo you wish to randomly generate array numbers? [Y/N] ");
		userCommand = bufferRead.readLine();

		if (userCommand.equalsIgnoreCase("Y")) {
			// Randomly generate array numbers
			int Result;
			for (int i = 0; i < arraySize; i++) {
				Result = (int) (Math.random() * arraySize);
				array[i] = Result;
			}
			System.out.println("Generated Unsorted Array:");
			printArray(array, arraySize);
		} else if (userCommand.equalsIgnoreCase("N")) {
			// manually enter the array elements
			System.out.println("Enter " + arraySize + " elements:");
			for (int i = 0; i < arraySize; i++) {
				userCommand = bufferRead.readLine();
				array[i] = Integer.parseInt(userCommand);
			}
			System.out.println("Given Unsorted Array:");
			printArray(array, arraySize);
		} else {
			System.out.println("Invalid Choice.");
			System.exit(0);
		}

		quickSort(array, 0, arraySize - 1);

		System.out.println("Sorted Array:");
		printArray(array, arraySize);
	}

	private static void printArray(int[] array, int arraySize) {
		// TODO Auto-generated method stub
		System.out.print("[ ");
		for (int i = 0; i < arraySize; i++) {
			System.out.print("'" + array[i] + "' ");
		}
		System.out.print("]");
		System.out.println();
	}
}