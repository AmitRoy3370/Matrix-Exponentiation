
import java.util.Scanner;
import java.math.BigInteger;

public class CLASSICSEQ_Classic_Sequence_Sum {

    static Scanner in = new Scanner(System.in);

    static long n, mod = 1000000007L;

    static int testCases;

    /*
    
	we know that,
	
	for all the real number of n,
	sum(j = 1 to n) f(j^2) = f(n) * f(n + 1) 
    
	basis for the introduction:
	...........................
	
	we know that f(1)^2 = 1 = f(3) - 1
	
	from(j = 1 to 1) f(j)^2 = f(1)^2 = 1 * 1 = 1
							= f(1) * f(2)
	
	from the rules of the induction hypothesis:
	...........................................
	
	sum(i = 1 to k) f(i)^2 = f(k) * f(k + 1)
	
	so we need to chow that the 
	sum(i = 1 to k + 1) f(i)^2 = f(k + 1) * f(k + 2) 
	
	induction step:
	...............
	
	sum(i = 1 to k + 1) f(i)^2 = sum(i = 1 to k)f(i)^2 + f(k + 1)^2
	= f(k) * f(k + 1) + f(k + 1)^2
	= f(k + 1) * (f(k) + f(k + 1))
	= f(k + 1) * f(k + 2)
	
	so can say that p(k) => p(k + 1)
	
	so all number from 1 to n, 
	f(n)^2 = f(n) * f(n + 1)
	
    */
    static void solve(int t) {

        long fibo_n = fib(n);
		long fibo_n_plus_1 = fib(n + 1L);
		
		//long square_sum = (new BigInteger(fibo_n + "").multiply(new BigInteger(fibo_n_plus_1 + ""))).longValue();
		
		//square_sum = new BigInteger(square_sum + "").mod(new BigInteger(mod + "")).longValue();
		
		long square_sum = fibo_n * fibo_n_plus_1;
		
		square_sum %= mod;
		
		System.out.println("Case " + t + ": " + square_sum);
		
    }

    public static void main(String[] args) {

        testCases = in.nextInt();

        for (int i = 0; i < testCases; ++i) {

            n = in.nextLong();

            //++n;
            solve(i + 1);

        }

    }
	
	static long fib(long n) {
		
		long unit[][] = new long[2][2];

        for (int i = 0; i < 2; ++i) {

            unit[i][i] = 1L;

        }

        long mat[][] = new long[2][2];

        mat[0][0] = 0L;
        mat[0][1] = 1L;
        mat[1][0] = 1L;
        mat[1][1] = 1L;

        //long sum = 0L;
        while (n > 0L) {

            if (n % 2L == 1L) {

                unit = mul(unit, mat);

                //sum += unit[1][0] * unit[1][0];
                //sum %= mod;
            }

            mat = mul(mat, mat);

            //sum += mat[1][0];
            //sum %= mod;
            //n = new BigInteger(n + "").divide(new BigInteger(2 + "")).longValue();
			
			n /= 2L;

        }

        //System.out.println(unit[1][0]);
        //System.out.println(sum);

        //print(unit);
		
		return unit[1][0];
		
	}

    static long[][] mul(long a[][], long b[][]) {

        int len = a.length;

        long ans[][] = new long[len][len];

        for (int i = 0; i < len; ++i) {

            for (int j = 0; j < len; ++j) {

                for (int k = 0; k < len; ++k) {

                    //ans[i][k] = new BigInteger(ans[i][k] + "").add(new BigInteger(a[i][j] + "").multiply(new BigInteger(b[j][k] + "") )).longValue();
					
					ans[i][k] += a[i][j] * b[j][k];
					
					ans[i][k] %= mod;
					
					/*if(ans[i][k] > mod) {
						
						ans[i][k] -= mod;
						
					}*/

                    //ans[i][k] = new BigInteger(ans[i][k] + "").mod(new BigInteger(mod + "") ).longValue();

                }

            }

        }

        return ans;

    }

    static void print(long a[][]) {

        for (long i[] : a) {

            for (long j : i) {

                System.out.print(j + " ");

            }

            System.out.println();

        }

    }

}
