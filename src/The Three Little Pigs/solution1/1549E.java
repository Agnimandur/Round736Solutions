//Written by Agnimandur (Runtime: 732ms)

import java.io.*;
import java.util.*;
 
public class solution {
    static final long MOD = 1000000007L;
    static FastScanner sc;
    static PrintWriter pw;
 
    public static void main(String[] args) {
        sc = new FastScanner();
        pw = new PrintWriter(System.out);
 
        int N = sc.ni();
        int Q = sc.ni();
        int[][] queries = new int[Q][2];
        for (int q = 0; q < Q; q++) {
            queries[q] = new int[]{sc.ni(),q};
        }
        sort(queries); //process the queries in order
        
        Combo c = new Combo(3*N,MOD);
        long DIV3 = c.power(3,MOD-2); //divide by 3 in modulo
 
        int[] dp = new int[3]; //this reduces the memory usage
        Arrays.fill(dp,N); //base case
        
        int INTMOD = 1000000007;
        int qIndex = 0;
        int[] ans = new int[Q];
        Arrays.fill(ans,1);
        
        for (int x = 1; x < 3*N; x++) {
            int[] newdp = new int[3];
            newdp[0] = (int)(((c.choose(3*N,x+1)-2*dp[0]-dp[1]+3*MOD)*DIV3)%MOD);
            newdp[1] = newdp[0]+dp[0];
            if (newdp[1]>=INTMOD) newdp[1] -= INTMOD;
            newdp[2] = dp[1]+newdp[1];
            if (newdp[2]>=INTMOD) newdp[2] -= INTMOD;
            
            while (qIndex < Q && queries[qIndex][0]==x) {
                ans[queries[qIndex][1]] = (int)((newdp[0]+c.choose(3*N,x))%MOD);
                qIndex++;
            }
            dp = newdp;
        }
        
        for (int a: ans)
            pw.println(a);
 
        pw.close(); 
    }
 
    static class Combo {
        //cache factorials
        long[] facs;
        long[] invfacs;
        long MOD;
        
        public Combo(int N, long M) {
          MOD = M;
          facs = new long[N+1];
          invfacs = new long[N+1];
          facs[0] = 1;
          for (int i = 1; i <= N; i++) {
            facs[i] = (facs[i-1]*i)%MOD;
          }
          invfacs[N] = power(facs[N],MOD-2);
          for (int i = N-1; i >= 0; i--)
            invfacs[i] = ((i+1)*invfacs[i+1])%MOD;
        }
        
        public long choose(int n, int k) {
          if (n<0||k<0||n<k) return 0;
          long denInv = (invfacs[k]*invfacs[n-k])%MOD;
          long ans = (facs[n]*denInv)%MOD;
          return ans;
        }
        
        public long factorial(int n) {
          return facs[n];
        }
        
        //binary exponentation
        public long power(long x, long y) {
          long ans = 1;
          x %= MOD;
          while (y > 0) {
            if((y&1)==1)
              ans = (ans * x) % MOD;
            y /= 2;
            x = (x * x) % MOD;
          }
          return ans;
        }
    }
    
    public static void sort(int[][] arr) {
        //Sort an array (immune to quicksort TLE)
    	Random rgen = new Random();
    	for (int i = 0; i < arr.length; i++) {
          int r = rgen.nextInt(arr.length);
          int[] temp = arr[i];
          arr[i] = arr[r];
          arr[r] = temp;
    	}
        Arrays.sort(arr, new Comparator<int[]>() {
          @Override
          public int compare(int[] a, int[] b) {
            return a[0]-b[0];
            //Ascending order.
          }
        });
    }
 
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in), 32768);
            st = null;
        }
 
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int ni() {
            return Integer.parseInt(next());
        }
    }
}