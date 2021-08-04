//Written by Agnimandur (Runtime: 202ms)

#include <bits/stdc++.h>
#define ll long long
#define vi vector<int>
#define vl vector<long long>
#define REP(i,a) for (int i = 0; i < (a); i++)
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
    int M = 3*N+3;
    Combo c(M);

    //cancel k from numerator and denominator of P(k)
    vl num(M,0);
    REP(i,M) {
        num[i] = c.choose(M,i+1);
        if (i <= 2) {
            num[i] -= c.choose(3,i+1);
            num[i] = (num[i]+MOD)%MOD;
        }
    }
    //denominator is k^2+3k+3
    //P(k) is num/denominator

    vl P(3*N+1,0);
    for (int i = 3*N; i >= 0; i--) {
        P[i] = num[i+2];
        num[i+1] = (num[i+1]-3*P[i]+3*MOD)%MOD;
        num[i] = (num[i]-3*P[i]+3*MOD)%MOD;
    }

    int Q = ni();
    REP(i,Q) {
        int x = ni();
        cout << P[x] << '\n';
    }
}