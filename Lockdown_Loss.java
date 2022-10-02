
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Lockdown_Loss {
	
    static Scanner in = new Scanner();

    static PrintWriter out = new PrintWriter(System.out);

    static StringBuilder ans = new StringBuilder();

	static int testCases;
	
	static long a[];
	
	static long n, mod = (power(10L, 9L) + 7L), p, q;
	
	/*
	
	important info:
	
	i)
	fib serise : 0 1 2 3 4 5 6 7  8  9  10 -> index
	             0 1 1 2 3 5 8 13 21 34 55
	
	ii)
	f(a) + f(a - 1) + f(a) * f(a - 1) + 1 = 
    (f(a) + 1) * (f(a - 1) + 1)                  	
	
	solve: 
	
	f(n) = f(n - 1) + f(n - 2) + f(n - 1) * f(n - 2)
	
	f(n) = f(n - 1) + f(n - 2) + f(n - 1) * f(n - 2) + 1 - 1
	
	f(n) + 1 = (f(n - 1) + 1) * (f(n - 2) + 1)...ii
	
	assume that g(n) = f(n) + 1
	
	g(0) + 1 = a + 1 (f(0) = a)
	
	g(1) + 1 = b + 1 (f(1) = b)
	
	g(2) + 1 = (a + 1)^1 * (b + 1)^1
	
	g(3) + 1 = G(2) * G(1)
	         = (a + 1) * (b + 1) * (b + 1)
		     = (a + 1)^1 * (b + 1)^2
	
	g(4) + 1 = g(2) * g(3)
	         = (a + 1) * (b + 1) * 
			   (a + 1)^2 * (b + 1)
		     = (a + 1)^2 * (b + 1)^3
	
	g(5) + 1 = g(4) * g(3)
	         = (a + 1)^3 * (b + 1)^5
	
	g(6) + 1 = g(5) * g(4)
	         = (a + 1)^3 * (b + 1)^5 * 
			   (a + 1)^2 * (b + 1)^3
		     = (a + 1)^5 * (b + 1)^8
	 
	g(7) + 1 = g(6) * g(5)
	         = (a + 1)^8 * (b + 1)^13
	
	g(8) + 1 = g(7) * g(6)
	         = (a + 1)^13 * (b + 1)^21
	
	g(9) + 1 = g(8) * g(7)
	         = (a + 1)^21 * (b + 1)^34
	 
	g(10) + 1 = g(9) * g(8)
	          = (a + 1)^34 * (b + 1)^55
	
    On observation, we can see that,
    
    g(x) + 1 = (a + 1)^(kth - 1)th fibo * 
           (b + 1)^(kth) fibo     
	
	g(x) = (a + 1)^(kth - 1) fibo *
	       (b + 1)^(kth) fibo - 1
	
	*/
	
	static void solve() {
		
		if(n == 0L) {
			
			System.out.println(p);
			return;
			
		}
		
		if(n == 1L) {
			
			System.out.println(q);
			return;
			
		}
		
		long fib_n = fib(n) % mod;
		long fib_n_1 = fib(n - 1) % mod;
		
		long f_n = (pow(p + 1L, fib_n_1) % mod) * (pow(q + 1L, fib_n) % mod);
		
		f_n %= mod;
		
		--f_n;
		
		f_n %= mod;
		
		f_n = (f_n + mod) % mod;
		
		System.out.println(f_n);
		
	}
	
	public static void main(String [] amit) throws IOException {
		
		testCases = in.nextInt();
		
		for(int t = 0; t < testCases; ++t) {
			
			p = in.nextLong();
			q = in.nextLong();
			n = in.nextLong();
			
			solve();
			
		}
		
	}
	
	static long pow(long value, long n) {
		
		long ans = 1L;
		
		while(n > 0L) {
			
			if(n % 2L == 1L) {
				
				ans *= value;
				
				ans %= mod;
				
			}
			
			value = value * value;
			
			value %= mod;
			
			n /= 2L;
			
		}
		
		return ans;
		
	}
	
	static long fib(long n) {
		
		if(n == 0L || n == 1L || n == 5L) {
			
			return n;
			
		}
		
		if(n == 2L) {
			
			return 1L;
			
		}
		
		/*
		
		primary_a = 0
		primary_b = 1
		
		        i               ii 
		new_a = 0 * primary_a + 1 * primary_b
		new_b = 1 * primary_a + 1 * primary_b
		
		i -> i = 0
		i -> ii = 1
		ii -> i = 1
		ii -> ii = 1
		
		trns = (0, 0) * new_a + (1, 0) * new_b
		trns = 0 * new_a + (1, 0) * 1
		     = (1, 0)
		
		so, our ans will be store in the (1, 0)
		index. 
			
		*/
		
		//--n;
		
        long mat[][] = new long[][]{{0L, 1L}, {1L, 1L}};
		long unit[][] = new long[2][2];
		
		for(int i = 0; i < 2; ++i) {
			
			unit[i][i] = 1L;
			
		}
		
		//--n;

        while (n > 0L) {

            if (n % 2L == 1L) {

                unit = mul(unit, mat);

            }

            mat = mul(mat, mat);

            n /= 2L;

        }

        return unit[1][0] % mod;
		
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
    static class Tree {

        long sum[];

        public Tree(long[] sum) {
            this.sum = sum;
        }

        void build_tree(int index, int l, int r) {

            if (l == r) {

                sum[index] = a[l];
                return;

            }

            int mid = (l + r) / 2;

            build_tree(2 * index, l, mid);
            build_tree(2 * index + 1, mid + 1, r);

            sum[index] = sum[index * 2] + sum[2 * index + 1];

        }

        void update(int index, int l, int r, int pos, long value) {

            if (l > pos || r < pos) {

                return;

            }

            if (l == r && l == pos) {

                sum[index] += value;
                return;

            }

            int mid = (l + r) / 2;

            if (mid >= pos) {

                update(2 * index, l, mid, pos, value);

            } else {

                update(2 * index + 1, mid + 1, r, pos, value);

            }

            sum[index] = sum[2 * index] + sum[2 * index + 1];

        }

        long query(int index, int L, int R, int l, int r) {

            if (L > r || R < l) {
                return 0;
            }
            if (L >= l && R <= r) {
                return sum[index];
            }

            int mid = (L + R) / 2;

            long x = query(2 * index, L, mid, l, r);

            long y = query(2 * index + 1, mid + 1, R, l, r);

            return x + y;

        }

    }

    static long power(long value, long n) {

        long result = 1L;

        while (n > 0L) {

            if (n % 2L == 1L) {

                result *= value;

            }

            value *= value;

            n /= 2L;

        }

        return result;

    }

    static long gcd(long n1, long n2) {
        if (n2 != 0) {
            return gcd(n2, n1 % n2);
        } else {
            return n1;
        }
    }

    static int search(long a[], long x, int last) {

        int i = 0, j = last;

        while (i <= j) {
            int mid = i + (j - i) / 2;

            if (a[mid] == x) {
                return mid;
            }

            if (a[mid] < x) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        return -1;

    }

    static void swap(long a[], int i, int j) {

        long temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }

    static void reverse(long a[]) {

        int n = a.length;

        for (int i = 0; i < n / 2; ++i) {

            swap(a, i, n - i - 1);

        }

    }

    static long max(long a[], int i, int n, long max) {

        if (i > n) {

            return max;

        }

        max = Math.max(a[i], max);

        return max(a, i + 1, n, max);

    }

    static long min(long a[], int i, int n, long max) {

        if (i > n) {

            return max;

        }

        max = Math.min(a[i], max);

        return max(a, i + 1, n, max);

    }

    static void printArray(long a[]) {

        for (long i : a) {

            System.out.print(i + " ");

        }

        System.out.println();

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

    static class Node<T> {

        T data;

        Node<T> next;

        public Node() {

            this.next = null;

        }

        public Node(T data) {

            this.data = data;

            this.next = null;

        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {

            return this.getData().toString() + "  ";

        }

    }

    static class ArrayList1<T> {

        Node<T> head, tail;

        int len;

        public ArrayList1() {

            this.head = null;

            this.tail = null;

            this.len = 0;

        }

        int size() {

            return len;

        }

        boolean isEmpty() {

            return len == 0;

        }

        int indexOf(T data) {

            if (isEmpty()) {

                throw new ArrayIndexOutOfBoundsException();

            }

            Node<T> temp = head;

            int index = -1, i = 0;

            while (temp != null) {

                if (temp.getData() == data) {

                    index = i;

                }

                i++;

                temp = temp.getNext();

            }

            return index;

        }

        void add(T data) {

            Node<T> newNode = new Node<>(data);

            if (isEmpty()) {

                head = newNode;

                tail = newNode;

                len++;

            } else {

                tail.setNext(newNode);

                tail = newNode;

                len++;

            }

        }

        void see() {

            if (isEmpty()) {

                throw new ArrayIndexOutOfBoundsException();

            }

            Node<T> temp = head;

            while (temp != null) {

                System.out.print(temp.getData().toString() + " ");
                //out.flush();

                temp = temp.getNext();

            }

            System.out.println();
            //out.flush();

        }

        void inserFirst(T data) {

            Node<T> newNode = new Node<>(data);

            Node<T> temp = head;

            if (isEmpty()) {

                head = newNode;
                tail = newNode;

                len++;

            } else {

                newNode.setNext(temp);

                head = newNode;

                len++;

            }

        }

        T get(int index) {

            if (isEmpty() || index >= len) {

                throw new ArrayIndexOutOfBoundsException();

            }

            if (index == 0) {

                return head.getData();

            }

            Node<T> temp = head;

            int i = 0;

            T data = null;

            while (temp != null) {

                if (i == index) {

                    data = temp.getData();

                }

                i++;

                temp = temp.getNext();

            }

            return data;

        }

        void addAt(T data, int index) {

            if (index >= len) {

                throw new ArrayIndexOutOfBoundsException();

            }

            Node<T> newNode = new Node<>(data);

            int i = 0;

            Node<T> temp = head;

            while (temp.next != null) {

                if (i == index) {

                    newNode.setNext(temp.next);

                    temp.next = newNode;

                }

                i++;

                temp = temp.getNext();

            }

            //    temp.setNext(temp);
            len++;

        }

        void popFront() {

            if (isEmpty()) {

                //return;
                throw new ArrayIndexOutOfBoundsException();

            }

            if (head == tail) {

                head = null;
                tail = null;

            } else {

                head = head.getNext();

            }

            len--;

        }

        void removeAt(int index) {

            if (index >= len) {

                throw new ArrayIndexOutOfBoundsException();

            }

            if (index == 0) {

                this.popFront();

                return;

            }

            Node<T> temp = head;

            int i = 0;

            Node<T> n = new Node<>();

            while (temp != null) {

                if (i == index) {

                    n.next = temp.next;

                    temp.next = n;

                    break;

                }

                i++;

                n = temp;

                temp = temp.getNext();

            }

            tail = n;

            --len;

        }

        void clearAll() {

            this.head = null;
            this.tail = null;

        }

    }

    static void merge(long a[], int left, int right, int mid) {

        int n1 = mid - left + 1, n2 = right - mid;

        long L[] = new long[n1];

        long R[] = new long[n2];

        for (int i = 0; i < n1; i++) {

            L[i] = a[left + i];

        }

        for (int i = 0; i < n2; i++) {

            R[i] = a[mid + 1 + i];

        }

        int i = 0, j = 0, k1 = left;

        while (i < n1 && j < n2) {

            if (L[i] <= R[j]) {

                a[k1] = L[i];

                i++;

            } else {

                a[k1] = R[j];

                j++;

            }

            k1++;

        }

        while (i < n1) {

            a[k1] = L[i];

            i++;

            k1++;

        }

        while (j < n2) {

            a[k1] = R[j];

            j++;
            k1++;

        }

    }

    static void sort(long a[], int left, int right) {

        if (left >= right) {

            return;

        }

        int mid = (left + right) / 2;

        sort(a, left, mid);

        sort(a, mid + 1, right);

        merge(a, left, right, mid);

    }

    static class Scanner {

        BufferedReader in;
        StringTokenizer st;

        public Scanner() {

            in = new BufferedReader(new InputStreamReader(System.in));

        }

        String next() throws IOException {

            while (st == null || !st.hasMoreElements()) {

                st = new StringTokenizer(in.readLine());

            }

            return st.nextToken();

        }

        String nextLine() throws IOException {

            return in.readLine();

        }

        int nextInt() throws IOException {

            return Integer.parseInt(next());

        }

        double nextDouble() throws IOException {

            return Double.parseDouble(next());

        }

        long nextLong() throws IOException {

            return Long.parseLong(next());

        }

        void close() throws IOException {

            in.close();

        }

    }

}