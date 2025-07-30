import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

public class IntList {
    private int[] arr;
    private int curSize;
    private int capacity;
    public IntList(){
        arr = new int[1];
        curSize = 0;
        capacity = 1;
    }

    private void extension() {
        capacity *= 2;
        arr = Arrays.copyOf(arr, capacity);
    }

    public void add(int x) {
        if (curSize == capacity) {
            extension();
        }
        arr[curSize++] = x;
    }

    public void fit() {
        arr = Arrays.copyOf(arr, curSize);
    }

    public int getInd(int ind) {
        if (ind >= curSize) {
            throw new RuntimeException();
        }
        return arr[ind];
    }

    public int getLen() {
        return curSize;
    }

    public void printr(){
        for (int i = curSize - 1; i >= 0; i--) {
            System.out.print(arr[i]);
            if (i != 0) {
                System.out.print(" ");
            }
        }
    }

    public void printAll(BufferedWriter out){
        try {
            out.write(curSize + " ");
            for (int i = 0; i < curSize; i++) {
                out.write(String.valueOf(arr[i]));
                if (i != curSize - 1) {
                    out.write(" ");
                }
            }
            out.write('\n');
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}