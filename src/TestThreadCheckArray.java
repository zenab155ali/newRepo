import java.util.Scanner;

public class TestThreadCheckArray {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            Thread thread1, thread2;

            // Prompt user to enter array size
            System.out.println("Enter array size");
            int num = input.nextInt();

            // Initialize array with user-input size
            int[] array = new int[num];
            
            // Prompt user to enter numbers for the array
            System.out.println("Enter numbers for array");
            for (int index = 0; index < num; index++)
                array[index] = input.nextInt();

            // Prompt user to enter a number
            System.out.println("Enter number");
            num = input.nextInt();

            // Create SharedData object with the array and the entered number
            SharedData sd = new SharedData(array, num);

            // Create two threads, each running the ThreadCheckArray Runnable
            thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
            thread2 = new Thread(new ThreadCheckArray(sd), "thread2");

            // Start the threads
            thread1.start();
            thread2.start();
            
            try {
                // Wait for both threads to finish execution
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // If the flag in SharedData is not set, print "Sorry" and exit
            if (!sd.getFlag()) {
                System.out.println("Sorry");
                return;
            }

            // Print solution details including 'b' and array length
            System.out.println("Solution for b: " + sd.getB() + ", n = " + sd.getArray().length);

            // Print indices of the array
            System.out.print("I:    ");
            for (int index = 0; index < sd.getArray().length; index++)
                System.out.print(index + "    ");
            System.out.println();

            // Print array elements with padding
            System.out.print("A:    ");
            for (int index : sd.getArray()) {
                System.out.print(index);
                int counter = 5;
                while (true) {
                    index = index / 10;
                    counter--;
                    if (index == 0)
                        break;
                }
                for (int i = 0; i < counter; i++)
                    System.out.print(" ");
            }

            System.out.println();

            // Print winArray values as 1 or 0
            System.out.print("C:    ");
            for (boolean index : sd.getWinArray()) {
                if (index)
                    System.out.print("1    ");
                else
                    System.out.print("0    ");
            }
        }
    }
}