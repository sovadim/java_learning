import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSortFJP {
    public static void sort(int[] array) {
        ForkJoinPool.commonPool().invoke(new MergeSortTask(array, 0, array.length));
    }
}

class MergeSortTask extends RecursiveAction {
    private final int[] array;
    private final int start;
    private final int end;

    public MergeSortTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= 1) {
            return;
        }

        int mid = (start + end) / 2;

        MergeSortTask left = new MergeSortTask(array, start, mid);
        MergeSortTask right = new MergeSortTask(array, mid, end);

        invokeAll(left, right);
        merge(mid);
    }

    private void merge(int mid) {
        int[] temp = new int[end - start];
        int i = start;
        int j = mid;
        int k = 0;

        while (i < mid && j < end) {
            temp[k++] = (array[i] <= array[j]) ? array[i++] : array[j++];
        }

        while (i < mid) temp[k++] = array[i++];
        while (j < end) temp[k++] = array[j++];

        System.arraycopy(temp, 0, array, start, temp.length);
    }
}
