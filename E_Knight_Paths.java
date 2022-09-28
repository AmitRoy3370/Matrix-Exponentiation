import java.util.Scanner;

public class E_Knight_Paths {
	
	static Scanner in = new Scanner(System.in);
	
	static long n, mod = (long)(Math.pow(2, 32));
	
	static void solve() {
		
		long dp[][] = new long[65][65];
		
		for(int i = 0; i < 8; ++i) {
			
			for(int j = 0; j < 8; ++j) {
				
				for(int dr : new int[]{-2, -1, 1, 2}) {
					
					for(int dc : new int[]{-2, -1, 1, 2}) {
						
						int new_raw = i + dr;
						int new_col = j + dc;
						
						if(inside(new_raw, new_col) && Math.abs(dr) != Math.abs(dc)) {
							
							dp[8 * i + j][8 * new_raw + new_col] = 1L;
							
						}
						
					}
					
				}
				
			}
			
		}
		
		for(int i = 0; i <= 64; ++i) {
			
			dp[i][64] = 1L;
			
		}
		
		long unit[][] = new long[65][65];
		
		for(int i = 0; i < unit.length; ++i) {
			
			unit[i][i] = 1L;
			
		}
		
		while(n > 0L) {
			
			if(n % 2L == 1L) {
				
				unit = mul(unit, dp);
				
			}
			
			dp = mul(dp, dp);
			
			n /= 2L;
			
		}
		
		System.out.print(unit[0][64]);
		
	}
	
	public static void main(String [] priya) {
		
		n = in.nextLong() + 1L;
		
		solve();
		
	}
	
	static boolean inside(int raw, int col) {
		
		return 0 <= Math.min(raw, col) & Math.max(raw, col) < 8;
		
	}
		
    static boolean isSmaller(String str1, String str2) {
 
        int n1 = str1.length(), n2 = str2.length();
 
        if (n1 < n2) {
            return true;
        }
        if (n2 < n1) {
            return false;
        }
 
        for (int i = 0; i < n1; i++) {
            if (str1.charAt(i) < str2.charAt(i)) {
                return true;
            } else if (str1.charAt(i) > str2.charAt(i)) {
                return false;
            }
        }
        return false;
    }
 		
    static long[][] mul(long a[][], long b[][]) {

        int len = a.length;

        long ans[][] = new long[len][len];

        for (int i = 0; i < len; ++i) {

            for (int j = 0; j < len; ++j) {

                for (int k = 0; k < len; ++k) {

                    ans[i][k] = Long.parseLong(sum(ans[i][k] + "", mul((a[i][j] % mod) + "" , (b[j][k] % mod) + "") ));

                    ans[i][k] %= mod;

                }

            }

        }

        return ans;

    }
	
    static String sub(String str1, String str2) {
 
        if (isSmaller(str1, str2)) {
            String t = str1;
            str1 = str2;
            str2 = t;
        }
 
        String str = "";
 
        int n1 = str1.length(), n2 = str2.length();
        int diff = n1 - n2;
 
        int carry = 0;
 
        for (int i = n2 - 1; i >= 0; i--) {
 
            int sub
                    = (((int) str1.charAt(i + diff) - (int) '0')
                    - ((int) str2.charAt(i) - (int) '0')
                    - carry);
            if (sub < 0) {
                sub = sub + 10;
                carry = 1;
            } else {
                carry = 0;
            }
 
            str += String.valueOf(sub);
        }
 
        for (int i = n1 - n2 - 1; i >= 0; i--) {
            if (str1.charAt(i) == '0' && carry > 0) {
                str += "9";
                continue;
            }
            int sub = (((int) str1.charAt(i) - (int) '0')
                    - carry);
            if (i > 0 || sub > 0) {
                str += String.valueOf(sub);
            }
            carry = 0;
        }
 
        return new StringBuilder(str).reverse().toString();
    }
 
    static String sum(String str1, String str2) {
 
        if (str1.length() > str2.length()) {
            String t = str1;
            str1 = str2;
            str2 = t;
        }
 
        String str = "";
 
        int n1 = str1.length(), n2 = str2.length();
        int diff = n2 - n1;
 
        int carry = 0;
 
        for (int i = n1 - 1; i >= 0; i--) {
 
            int sum = ((int) (str1.charAt(i) - '0')
                    + (int) (str2.charAt(i + diff) - '0') + carry);
            str += (char) (sum % 10 + '0');
            carry = sum / 10;
        }
 
        for (int i = n2 - n1 - 1; i >= 0; i--) {
            int sum = ((int) (str2.charAt(i) - '0') + carry);
            str += (char) (sum % 10 + '0');
            carry = sum / 10;
        }
 
        if (carry > 0) {
            str += (char) (carry + '0');
        }
 
        return new StringBuilder(str).reverse().toString();
    }
 
    static long detect_sum(int i, long a[], long sum) {
 
        if (i >= a.length) {
 
            return sum;
 
        }
 
        return detect_sum(i + 1, a, sum + a[i]);
 
    }
 
    static String mul(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 == 0 || len2 == 0) {
            return "0";
        }
 
        int result[] = new int[len1 + len2];
 
        int i_n1 = 0;
        int i_n2 = 0;
 
        for (int i = len1 - 1; i >= 0; i--) {
            int carry = 0;
            int n1 = num1.charAt(i) - '0';
 
            i_n2 = 0;
 
            for (int j = len2 - 1; j >= 0; j--) {
 
                int n2 = num2.charAt(j) - '0';
 
                int sum = n1 * n2 + result[i_n1 + i_n2] + carry;
 
                carry = sum / 10;
 
                result[i_n1 + i_n2] = sum % 10;
 
                i_n2++;
            }
 
            if (carry > 0) {
                result[i_n1 + i_n2] += carry;
            }
 
            i_n1++;
        }
 
        int i = result.length - 1;
        while (i >= 0 && result[i] == 0) {
            i--;
        }
 
        if (i == -1) {
            return "0";
        }
 
        String s = "";
 
        while (i >= 0) {
            s += (result[i--]);
        }
 
        return s;
 
    }
 	
}