package root.sort;

import java.util.Collection;

/**
 * Created by zhenia on 22.03.17.
 */
public class BinaryInsertSorter<T extends Comparable> {
    public T[] sort(T[] unsorted){
        sort_bin_insert(unsorted);
        return unsorted;
    }
    private void sort_bin_insert (T []a) {
        int left, right, middle;
        T x;
        for (int i=1; i<a.length; i++) {
            if (a[i-1].compareTo(a[i]) > 0) {
                x=a[i]; // x – елемент для вставки
                left=0;// ліва границя відсортованої частини масиву
                right=i-1; // права границя відсортованої частини масиву

                do {
                    middle = (left+right)/2; // middle – нова "середина"послідовності
                    if (a[middle].compareTo(x) < 0) left= middle+1;
                    else right=middle-1;
                } while (left<=right); // пошук ведеться поки ліва границя не виявиться справа від правої границі

                for (int j=i-1; j>=left; j--) a[j+1]= a[j];

                a[left]= x;
            }
        }
    }
}
