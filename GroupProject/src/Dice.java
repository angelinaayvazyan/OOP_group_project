import java.util.Random;

public class Dice {
    public static int[] roll(){
        Random m = new Random();
        int a = m.nextInt(6) + 1;
        int b = m.nextInt(6) + 1;
        int[] arr = new int[2];
        arr[0] = a;
        arr[1] = b;
        return arr;
    }
    public static void printRoll(int[] arr){
        System.out.println(arr[0] + " " + arr[1]);
    }
    public static void main(String[] args){
        printRoll(roll());
        roll();
    }

}
