//Written by Agnimandur (Runtime: 124ms)

import java.io.*;
import java.util.*;
import java.math.*;

public class solution {
    static FastScanner sc;
    static PrintWriter pw;

    public static void main(String[] args) {
        sc = new FastScanner();
        pw = new PrintWriter(System.out);
        
        int Q = sc.ni();
        for (int q = 0; q < Q; q++) {
            int N = sc.ni();
            String enemy = sc.next();
            boolean[] free = new boolean[N]; //enemy pawns available for capture
            for (int i = 0; i < N; i++) {
                if (enemy.charAt(i)=='1')
                    free[i] = true;
            }
            String gregor = sc.next();
        	
            int ans = 0;
            for (int i = 0; i < N; i++) {
                if (gregor.charAt(i)=='0')
                    continue;
        		
        	if (i>0 && free[i-1]) {
        	    free[i-1] = false;
        	    ans++;
        	} else if (enemy.charAt(i)=='0') {
                    //no enemy pawn in our path
        	    ans++;
        	} else if (i < N-1 && free[i+1]) {
        	    free[i+1] = false;
        	    ans++;
        	}
            }
            pw.println(ans);
        }

        pw.close();
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