//Written by Agnimandur (Runtime: 499ms)

import java.io.*;
import java.util.*;
 
public class Main {
    static final int INF = 50000000;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        
        int Q = sc.ni();
        int P = 18;
        for (int q = 0; q < Q; q++) {
            int N = sc.ni();
            long[] a = new long[N];
            for (int i = 0; i < N; i++)
                a[i] = sc.nl();
            
            long[][] table = new long[P][N-1];
            for (int i = 0; i < N-1; i++)
                table[0][i] = Math.abs(a[i+1]-a[i]);
            for (int i = 1; i < P; i++) {
                for (int j = 0; j < N-(1<<i); j++) {
                    table[i][j] = gcd(table[i-1][j],table[i-1][j+(1<<(i-1))]);
                }
            }
            int ans = 1;
            for (int j = 0; j < N-1; j++) {
                if (table[0][j]==1) continue;
                int cur = j;
                long g = INF;
                for (int i = P-1; i >= 0; i--) {
                    if (cur==N-1) break;
                    if (table[i][cur] >= 2 && (g==INF||gcd(g,table[i][cur])>=2)) {
                        if (g==INF)
                            g = table[i][cur];
                        else
                            g = gcd(g,table[i][cur]);
                        cur += (1<<i);
                    }
                }
                ans = Math.max(ans,cur-j+1);
            }
            pw.println(ans);
        }
        pw.close();
    }
    
    //Find the GCD of two numbers
    public static long gcd(long a, long b) {
        if (a < b)
            return gcd(b,a);
        if (b == 0)
            return a;
        else
            return gcd(b,a%b);
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
 
        long nl() {
            return Long.parseLong(next());
        }
    }
}