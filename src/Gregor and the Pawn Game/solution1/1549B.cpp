//Written by arvindr9 (Runtime: 31ms)

#include <bits/stdc++.h>
using namespace std;
 
int t;
 
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> t;
    while (t--) {
        int n;
        cin >> n;
        string st, tt;
        cin >> st >> tt;
        int ans = 0;
        vector<bool> taken(n);
        for (int j = 0; j < n; j++) {
            if (tt[j] == '1') {
                for (int i = j - 1; i <= j + 1; i++) {
                    if (i >= 0 and i < n) {
                        if (!taken[i]) {
                            if ((st[i] == '1' and i != j) or (st[i] == '0' and i == j)) {
                                taken[i] = 1;
                                ans++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        cout << ans << "\n";
    }
} 