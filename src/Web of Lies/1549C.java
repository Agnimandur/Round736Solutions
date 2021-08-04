//Written by Agnimandur (Runtime: 249ms)

import java.io.*;
import java.util.*;
 
public class solution {
    static FastScanner sc;
    static PrintWriter pw;

    static int[] weaker;
    static int[] edges;
    static int ans;
 
    public static void main(String[] args) {
        sc = new FastScanner();
        pw = new PrintWriter(System.out);

        int N = sc.ni();
        int M = sc.ni();
        weaker = new int[N];
        edges = new int[N];
        for (int i = 0; i < M; i++) {
            int a = sc.ni()-1;
            int b = sc.ni()-1;
            change(a,b,1);
        }
        ans = 0;
        for (int i = 0; i < N; i++) {
            ans += survive(i);
        }

        int Q = sc.ni();
        for (int q = 0; q < Q; q++) {
            int type = sc.ni();
            if (type == 1 || type == 2) {
                int a = sc.ni()-1;
                int b = sc.ni()-1;
                update(a,b,type);
            } else {
                pw.println(ans);
            }
        }
        pw.close();
    }

    //process an update
    public static void update(int a, int b, int type) {
        int prev = survive(a)+survive(b);
        if (type==1) {
            change(a,b,1);
        } else {
            change(a,b,-1);
        }
        int next = survive(a)+survive(b);

        ans += (next-prev);
    }

    public static void change(int a, int b, int x) {
        edges[a] += x;
        edges[b] += x;
        if (a > b)
            weaker[a] += x;
        else
            weaker[b] += x;
    }

    //does noble a survive?
    public static int survive(int a) {
        if (weaker[a]==edges[a])
            return 1;
        else
            return 0;
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