package Tests;

import java.util.Arrays;

public class Vaja {
    public static void main(String[] args) {
        A a = new A();
        A b = new A();

        a.a = new int[]{1, 2, 3};
        b.a =a.a;

        System.out.println(Arrays.toString(a.a)+"   "+Arrays.toString(b.a));

        b.a[1] = 0;

        System.out.println(Arrays.toString(a.a)+"   "+Arrays.toString(b.a));


    }
}
class A{
    int[] a;
    int[] b;


}
