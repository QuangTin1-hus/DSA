package week2.ex2;

public class SelectionSort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public void sort(T[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < data.length; j++) {

                if (data[j].compareTo(data[minIdx]) < 0) {
                    minIdx = j;
                }
                T temp = data[minIdx];
                data[minIdx] = data[i];
                data[i] = temp;

            }
        }
    }


}
