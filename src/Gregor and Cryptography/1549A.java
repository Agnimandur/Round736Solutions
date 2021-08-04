Written by Agnimandur (Runtime: 109ms)

import java.io.*;
import java.util.*;
 
public class solution {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        
        int T = sc.ni();
        for (int t = 0; t < T; t++) {
        	int P = sc.ni();
        	int a = 2;
        	int b = P-1;
        	pw.println(a + " " + b);
        }
        
        pw.close();
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
    }
}