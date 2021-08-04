//Written by Agnimandur (Runtime: 218ms)

#include <bits/stdc++.h>
 
#define ll long long
#define sz(x) ((int) (x).size())
#define all(x) (x).begin(), (x).end()
#define vi vector<int>
#define vl vector<long long>
#define REP(i,a) for (int i = 0; i < (a); i++)
#define add push_back
using namespace std;
 
const ll MOD = 1000000007LL;
 
 
 
int ni() {
    int x; cin >> x;
    return x;
}
 
struct Combo {
    vl facs;
    vl invfacs;
    int N;
 
    Combo(int N) {
        this->N=N;
        facs.assign(N+1,0);
        invfacs.assign(N+1,0);
        facs[0] = 1;
        for (int i = 1; i <= N; i++) {
            facs[i] = (facs[i-1]*i)%MOD;
        }
        invfacs[N] = power(facs[N],MOD-2);
        for (int i = N-1; i >= 0; i--) {
            invfacs[i] = (invfacs[i+1]*(i+1))%MOD;
        }
    }
 
    ll choose(int n, int k) {
        if (n<0||k<0||n<k) return 0LL;
        ll denInv = (invfacs[k]*invfacs[n-k])%MOD;
        ll ans = (facs[n]*denInv)%MOD;
        return ans;
    }
 
    ll power(ll x, ll y) {
        ll ans = 1;
        x %= MOD;
        while (y > 0) {
            if (y%2==1) ans = (ans*x)%MOD;
            y /= 2;
            x = (x*x)%MOD;
        }
        return ans;
    }
};
 
int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
 
    int N = ni();
    Combo c(3*N);
    ll DIV3 = c.power(3,MOD-2);
 
    const int M = 1000000;
    ll dp[3*M+1][3];
    REP(i,3) dp[0][i] = N;
 
    for (int x = 1; x < 3*N; x++) {
        //solve the system of equations
        dp[x][0] = ((c.choose(3*N,x+1)-2*dp[x-1][0]-dp[x-1][1]+3*MOD)*DIV3)%MOD;
        dp[x][1] = (dp[x][0]+dp[x-1][0])%MOD;
        dp[x][2] = (dp[x][1]+dp[x-1][1])%MOD;
    }
    
    int Q = ni();
    REP(q,Q) {
        int x = ni();
        if (x==3*N)
            cout << "1\n";
        else
            cout << (dp[x][0]+c.choose(3*N,x))%MOD << '\n';
    }
}