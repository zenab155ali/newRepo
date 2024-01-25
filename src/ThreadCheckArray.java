public class ThreadCheckArray implements Runnable {
    private boolean flag;
    private boolean[] winArray;
    SharedData sd;
    Integer[] array; // Changed int[] to Integer[]
    int b;

    // Constructor to initialize ThreadCheckArray with SharedData object
    public ThreadCheckArray(SharedData sd) {
        this.sd = sd;
        // Synchronize to safely access and initialize array and b
        synchronized (sd) {
            array = sd.getArray();
            b = sd.getB();
        }
        winArray = new boolean[array.length];
    }

    // Recursive method to check for a solution
    void rec(int n, int b) {
        // Synchronize to check the shared flag
        synchronized (sd) {
            if (sd.getFlag())
                return;
        }
        if (n == 1) {
            // Check if the solution is found
            if (b == 0 || b == array[n - 1]) {
                flag = true;
                // Synchronize to safely set the flag in SharedData
                synchronized (sd) {
                    sd.setFlag(true);
                }
            }
            if (b == array[n - 1])
                winArray[n - 1] = true;
            return;
        }

        // Recursive calls for the solution
        rec(n - 1, b - array[n - 1]);
        if (flag)
            winArray[n - 1] = true;
        // Synchronize to check the shared flag before making another recursive call
        synchronized (sd) {
            if (sd.getFlag())
                return;
        }
        rec(n - 1, b);
    }

    // Entry point for the Runnable
    public void run() {
        // Check if the array has more than one element
        if (array.length != 1)
            // Choose the appropriate recursive call based on the thread name
            if (Thread.currentThread().getName().equals("thread1"))
                rec(array.length - 1, b - array[array.length - 1]);
            else
                rec(array.length - 1, b);
        // Check if the array has only one element
        if (array.length == 1)
            if (b == array[0] && !flag) {
                winArray[0] = true;
                flag = true;
                // Synchronize to safely set the flag in SharedData
                synchronized (sd) {
                    sd.setFlag(true);
                }
            }
        // Check if the solution is found and update winArray in SharedData
        if (flag) {
            if (Thread.currentThread().getName().equals("thread1"))
                winArray[array.length - 1] = true;
            // Synchronize to safely update winArray in SharedData
            synchronized (sd) {
                sd.setWinArray(winArray);
            }
        }
    }
}
