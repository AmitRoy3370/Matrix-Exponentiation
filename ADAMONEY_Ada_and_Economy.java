import java.util.Scanner;

public class ADAMONEY_Ada_and_Economy {
	
	static Scanner in = new Scanner(System.in);
	
	static int testCases;
	
	static long n, mod = (long)(1e9 + 7L);
	
	static long T[];
	
	static void solve() {
		
		if(n < 5) {
			
			System.out.println(T[(int)n]);
			return;
			
		}
		
		long mat[][] = new long[6][6];
		
		// A[i] = A[i - 1] + 2 * A[i - 2] + 5 * A[i - 4]
		// + A[i - 5]
		
		mat[1][1] = 1L;
		mat[1][2] = 2L;
		mat[1][4] = 5L;
		mat[1][5] = 1L;
		
		for(int i = 2; i <= 5; ++i) {
			
			mat[i][i - 1] = 1L;
			
		}
		
		long unit[][] = new long[6][6];
		
		for(int i = 0; i < 6; ++i) {
			
			unit[i][i] = 1L;
			
		}
		
		long A[][] = new long[6][1];
		
		for(int i = 1; i <= 5; ++i) {
			
			A[i][0] = T[i - 1];
			
		}
		
		n -= 4L;
		
		while(n > 0L) {
			
			if(n % 2L == 1L) {
				
				unit = mul(unit, mat);
				
			}
			
			mat = mul(mat, mat);
			
			n /= 2L;
			
		}
		
		//long res[][] = mul(mat, A);
		
		long ans = 0L;
		
		for(int j = 4, i = 1; i <= 5 && j >= 0; --j, ++i) {
			
			ans += unit[1][i] * T[j];
			
			ans %= mod;
			
		}
		
		System.out.println(ans);
		
	}
	
	public static void main(String [] amit) {
		
		testCases = in.nextInt();
		
		for(int t = 0; t < testCases; ++t) {
			
			T = new long[5];
			
			for(int i = 0; i < 5; ++i) {
				
				T[i] = in.nextLong();
				
			}
			
			n = in.nextLong();
			
			solve();
			
		}
		
	}
	
    static long[][] mul(long a[][], long b[][]) {

        int len = a.length - 1;

        long ans[][] = new long[len + 1][len + 1];

        for (int i = 1; i <= len; ++i) {

            for (int j = 1; j <= len; ++j) {

                for (int k = 1; k <= len; ++k) {

                    ans[i][k] += a[i][j] * b[j][k];

                    ans[i][k] %= mod;

                }

            }

        }

        return ans;

    }

}