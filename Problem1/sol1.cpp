#include "bits/stdc++.h"
using namespace std;
#define int int64_t
#define sz(v) ((int) v.size())
#define all(v) v.begin(), v.end()
const int M1 = 69421;
const int M2 = 1e9 + 9;

void solve() {
	string s; cin >> s;
	int n = s.length();
	int ans = 0;
	int st = 0, fnd = 0;
	while (fnd != -1) {
		int hshl = 0;
		int hshr = 0;
		fnd = -1;
		for (int i = st, pw = 1; i < n / 2; i++, pw = (pw * M1 % M2)) {
			hshl = (hshl * M1 + s[i]) % M2;
			hshr = (hshr + pw * s[n - i - 1]) % M2;
			if (hshl == hshr) {
				fnd = i;
				break;
			}
		}
		if (fnd != -1) {
			st = fnd + 1;
			ans += 2;
		}
	}
	if (2 * st == n) cout << ans << '\n';
	else cout << ans + 1 << '\n';
}

signed main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
#endif
	int tc;
	cin >> tc;
	while (tc--) solve();
	return 0;
}


/*

Editorial:
We are going to employ a greedy approach to solve this question. We are going to initialise two strings
(in this case hash values) one built with the ith character and the other with its n - i - 1 th complement.
We will loop from left to right and start adding characters one at a time to their respective strings
until the left string is same as the right string, in which we can create two partitions.

To check the equality of these strings, we will use string hashing.

*/