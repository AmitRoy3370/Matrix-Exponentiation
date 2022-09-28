
import java.util.Scanner;

public class C_Fibonacci {

    static Scanner in = new Scanner(System.in);

    static long n, mod = 1000000007L;

    static void solve() {

        long unit[][] = new long[2][2];

        for (int i = 0; i < 2; ++i) {

            unit[i][i] = 1L;

        }
		
		/*
		
		observe some thing for the basic matrix
		if our begining two value of the serise
		is 0 1. we can think that our nth fibo
		will store in the value of the b.
		so our next value of the a is a, and the
		next value of b is the summation of the
		a and previous value of the b. so, we can
		express new_a as 0 * a + 1 * b and the 
		new_b is 1 * a + 1 * b. So, if we create
		a 2 * 2 matrix, our o index mention the
		value of the a and index 1 is the value
		of the b. so if we want to go from the
		a to a that means index 0 to 0 we need
		the value 0. so there we can set the value
		of the 0. if we want to move from the
		value 0 to 1 we need the value 1.
		so, we set the index (0, 1) as 1
		again if we want to go from 1 to index 0
		we need the value 1. so set (1, 0) as 1
		and 1 to 1 is also 1, so the value will
		be 1 also.
		
		Now come that what index or position or cell
		will carry out the whole result or the final
		value of the b.
		
		mainly we detect the transiction of the value
		of the matrix so
		trns = (0, 0) * new_a + (1, 0) * new_b
		trns = 0 * new_a + (1, 0) * 1
		= (1, 0)
		
		so, our ans will be store in the (1, 0)
		index. 
			
		*/

        long mat[][] = new long[][]{{0L, 1L}, {1L, 1L}};

        while (n > 0L) {

            if (n % 2L == 1L) {

                unit = mul(unit, mat);

            }

            mat = mul(mat, mat);

            n /= 2L;

        }

        System.out.print(unit[1][0]);

    }

    public static void main(String[] priya) {

        n = in.nextLong();

        solve();

    }

    static long[][] mul(long a[][], long b[][]) {

        int len = a.length;

        long ans[][] = new long[len][len];

        for (int i = 0; i < len; ++i) {

            for (int j = 0; j < len; ++j) {

                for (int k = 0; k < len; ++k) {

                    ans[i][k] += a[i][j] * b[j][k];

                    ans[i][k] %= mod;

                }

            }

        }

        return ans;

    }

}
