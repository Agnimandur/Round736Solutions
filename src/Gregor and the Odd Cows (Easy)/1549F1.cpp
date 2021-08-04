//Written by Agnimandur (Runtime: 31ms)

#include <bits/stdc++.h>
#define ll long long
#define REP(i,a) for (int i = 0; i < (a); i++)
using namespace std;

int ni() {
    int x; cin >> x;
    return x;
}
 
ll choose(ll n, ll k) {
    if (n<k) return 0LL;
    
    if (k==2)
        return n*(n-1)/2;
    else
        return n*(n-1)*(n-2)/6;
}
 
// number of boundary points between two points, mod 4
int boundary(int x1, int y1, int x2, int y2) {
    if (x1==x2&&y1==y2)
        return 0;
    else
        return 2;
}
 
int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
 
    int N = ni();
 
    ll cnt[2][2] = {{0,0},{0,0}};

    REP(i,N) {
        int x = ni();
        int y = ni();
        cnt[(x%4)/2][(y%4)/2] += 1;
    }
 
    ll ans = 0;
    for (int s1 = 0; s1 < 4; s1++) {
        for (int s2 = s1; s2 < 4; s2++) {
            for (int s3 = s2; s3 < 4; s3++) {
                int x1 = s1/2;
                int y1 = s1%2;
                int x2 = s2/2;
                int y2 = s2%2;
                int x3 = s3/2;
                int y3 = s3%2;
                int b1 = boundary(x1,y1,x2,y2);
                int b2 = boundary(x1,y1,x3,y3);
                int b3 = boundary(x2,y2,x3,y3);
                int B = (b1+b2+b3)%4;
 
                if (B==0) {
                    if (s1==s2&&s2==s3) {
                        ans += choose(cnt[x1][y1],3);
                    } else if (s1==s2) {
                        ans += choose(cnt[x1][y1],2)*cnt[x3][y3];
                    } else if (s2==s3) {
                        ans += choose(cnt[x2][y2],2)*cnt[x1][y1];
                    } else {
                        ans += cnt[x1][y1]*cnt[x2][y2]*cnt[x3][y3];
                    }
                }
            }
        }
    }
    cout << ans << '\n';
}