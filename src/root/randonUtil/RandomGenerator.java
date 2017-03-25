package root.randonUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by zhenia on 22.03.17.
 */
public class RandomGenerator {
    public static void main(String[] args) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("9.txt"));
            StringBuilder sb = new StringBuilder();

            double[] tmp = generate(42000);

            for (int i = 0; i < tmp.length; i++) {
                sb.append(tmp[i] + " ");
            }

            bw.write(sb.toString());

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double[] generate(int num){
        double[] arr = new double[num];

        for (int i = 0; i < num; i++) {
            arr[i] = ((int)(Math.random() * 1000000)) / 1000.0;
        }

        return arr;
    }
}
