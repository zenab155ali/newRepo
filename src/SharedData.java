public class SharedData {
    private Integer[] array; // Changed int[] to Integer[]
    private boolean[] winArray;
    private boolean flag;
    private final int b;

    public SharedData(int[] array, int b) {
        // Convert int[] to Integer[] and initialize
        this.array = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i]; // Autoboxing
        }
        this.b = b;
    }

    public boolean[] getWinArray() {
        return winArray;
    }

    public void setWinArray(boolean[] winArray) {
        this.winArray = winArray;
    }

    public Integer[] getArray() { // Changed return type to Integer[]
        return array;
    }

    public int getB() {
        return b;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}