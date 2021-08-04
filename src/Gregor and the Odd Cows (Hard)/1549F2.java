//Written by Agnimandur (Runtime: 4211 ms)

import java.io.*;
import java.util.*;
 
public class solution {
    static FastScanner sc;
    static PrintWriter pw;
    
    static long[][] gcds;
    static final int D = 1000;
 
    public static void main(String[] args) {
        sc = new FastScanner();
        pw = new PrintWriter(System.out);
 
        int N = sc.ni();

        gcds = new long[D][D];
        for (int i = 0; i < D; i++) {
            for (int j = 0; j <= i; j++)
                gcds[i][j] = calcGCD(i,j);
        }
 
        long[][] nums = new long[N][2];
        int[][] mods = new int[N][2];
        for (int i = 0; i < N; i++) {
            nums[i] = new long[]{sc.nl(),sc.nl()};
            mods[i][0] = (int)nums[i][0]%4;
            mods[i][1] = (int)nums[i][1]%4;
        }
 
        int[][][][] cnt = new int[N][4][4][4]; //number of other points with modularity (j,k) such that the segment from i has modularity l boundary points.
        for (int i = 0; i < N; i++) {
            for (int other = i+1; other < N; other++) {
                int b = boundary(nums[i][0],nums[i][1],nums[other][0],nums[other][1]);
                cnt[i][mods[other][0]][mods[other][1]][b] += 1;
                cnt[other][mods[i][0]][mods[i][1]][b] += 1;
            }
        }
 
        //a little manipulation by pick's theorem: a fence is interesting IFF A (2*area) is congruent to B (total boundary points) modulo 4.
        //since A is even, b1,b2,b3 (where B=b1+b2+b3) are either all even or one is even.
 
        long eee = 0;
        long ooe = 0;
 
        //fix one vertex
        for (int i = 0; i < N; i++) {
            //fix the modularities of b1 and b2
            for (int b1 = 0; b1 < 4; b1++) {
                for (int b2 = b1; b2 < 4; b2 += 2) {
                    //fix the modularities of the other points
                    for (int x1 = 0; x1 < 4; x1++) {
                        for (int y1 = 0; y1 < 4; y1++) {
                            int startx2 = b1<b2 ? x1%2 : x1;
                            for (int x2 = startx2; x2 < 4; x2 += 2) {
                                int starty2 = x1!=x2||b1<b2 ? y1%2 : y1;
                                for (int y2 = starty2; y2 < 4; y2 += 2) {
                                    int b3;
                                    int triangles;
                                    if (x1==x2 && y1==y2) {
                                        b3 = 0;
                                        if (b1==b2)
                                            triangles = cnt[i][x1][y1][b1]*(cnt[i][x1][y1][b2]-1)/2;
                                        else
                                            triangles = cnt[i][x1][y1][b1]*cnt[i][x1][y1][b2];
                                    } else {
                                        b3 = 2;
                                        triangles = cnt[i][x1][y1][b1]*cnt[i][x2][y2][b2];
                                    }
                                    int B = (b1+b2+b3)%4;
                                    if (area(nums[i][0],nums[i][1],x1,y1,x2,y2) == B) {
                                        if (b1%2==0&&b2%2==0)
                                            eee += triangles;
                                        else
                                            ooe += triangles;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        long ans = eee/3+ooe;
        pw.println(ans);
 
        pw.close(); 
    }
 
    //shoelace formula for A (2*area)
    public static int area(long x1, long y1, long x2, long y2, long x3, long y3) {
        return (int)(Math.abs(x1*y2+x2*y3+x3*y1-x2*y1-x3*y2-x1*y3)&3);
    }
 
    //number of boundary points between two points
    public static int boundary(long x1, long y1, long x2, long y2) {
        return (int)(gcd(Math.abs(x1-x2),Math.abs(y1-y2))&3);
    }
 
    public static long calcGCD(long a, long b) {
        if (a < b)
          return gcd(b,a);
        if (b == 0)
          return a;
        else
          return gcd(b,a%b);
    }
    
    public static long gcd(long a, long b) {
        if (a < b)
          return gcd(b,a);
        if (a < D)
            return gcds[(int)a][(int)b];
        else if (b == 0)
          return a;
        else
          return gcd(b,a%b);
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
 
        long nl() {
            return Long.parseLong(next());
        }
    }
}