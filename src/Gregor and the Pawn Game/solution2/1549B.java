//Written by Agnimandur (Runtime: 280ms)

import java.io.*;
import java.util.*;
 
public class solution {
    static FastScanner sc;
    static PrintWriter pw;
    
    public static void main(String[] args) {
        sc = new FastScanner();
        pw = new PrintWriter(System.out);
      
        int Q = sc.ni();
        for (int q = 0; q < Q; q++) {
            int N = sc.ni();
            char[] enemy = sc.next().toCharArray();
            char[] gregor = sc.next().toCharArray();
            HopcroftKarp hk = new HopcroftKarp(N,N);
            for (int i = 0; i < N; i++) {
                if (gregor[i]=='1') {
                    if (i>0 && enemy[i-1]=='1')
                        hk.addEdge(i+1,N+i);
                    if (enemy[i]=='0')
                        hk.addEdge(i+1,N+i+1);
                    if (i<N-1 && enemy[i+1]=='1')
                        hk.addEdge(i+1,N+i+2);
                }
            }
            ArrayList<int[]> matching = hk.run();
            pw.println(matching.size());
        }
      
      pw.close(); 
    }
  
    static class HopcroftKarp {
        int[] match;
        int[] dist;
        ArrayList<Integer>[] graph;
        int N;
        int M;
        int T;
        int INF = 1000000000;
   
        HopcroftKarp(int N, int M) {
            this.N=N;
            this.M=M;
            T = N+M+1;
            match = new int[T];
            dist = new int[T];
            graph = new ArrayList[T];
            for (int i = 0; i < T; i++)
                graph[i] = new ArrayList<Integer>();
        }
   
        //1 indexed
        void addEdge(int u, int v) {
            graph[u].add(v);
            graph[v].add(u);
        }
   
        boolean BFS() {
            ArrayDeque<Integer> q = new ArrayDeque<Integer>();
            for (int u = 1; u <= N; u++) {
                if (match[u]==0) {
                    dist[u] = 0;
                    q.add(u);
                } else {
                    dist[u] = INF;
                }
            }
            dist[0] = INF;
            while (!q.isEmpty()) {
                int u = q.pollFirst();
                if (dist[u]<dist[0]) {
                    for (int v: graph[u]) {
                        if (dist[match[v]] == INF) {
                            dist[match[v]] = dist[u]+1;
                            q.push(match[v]);
                        }
                    }
                }
            }
            return dist[0]<INF;
        }
   
        boolean DFS(int u) {
            if (u > 0) {
                for (int v: graph[u]) {
                    if (dist[match[v]]==dist[u]+1 && DFS(match[v])) {
                        match[v] = u;
                        match[u] = v;
                        return true;
                    }
                }
                dist[u] = INF;
                return false;
            }
            return true;
        }
   
        ArrayList<int[]> run() {
            int m = 0;
            while (BFS()) {
                for (int u = 1; u <= N; u++) {
                    if (match[u]==0 && DFS(u)) m++;
                }
            }
            ArrayList<int[]> ans = new ArrayList<>();
            for (int v = N+1; v <= N+M; v++) {
                if (match[v]>0)
                    ans.add(new int[]{match[v],v});
            }
            return ans;
        }
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