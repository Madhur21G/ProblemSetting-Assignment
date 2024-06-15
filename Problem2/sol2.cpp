/* Author: BitWizz */

#include "bits/stdc++.h"
using namespace std;
#define int int64_t
#define sz(v) ((int) v.size())
#define all(v) v.begin(), v.end()
const int maxn = 2e5 + 5;
const int INF = 1e8;
const int inf = 1e18;
const int mod = 1e9 + 1;
const int mod2 = 1e9 + 7;
const int base = 31;
const int base2 = 67;

int cnt1[maxn], cnt2[maxn];
int f[maxn], p[maxn];
int f2[maxn], p2[maxn];
set<pair<int , int>> num;

int get_hash(int l, int r) {
	return ((f[r] - p[r - l + 1] * f[l - 1] + mod) % mod + mod) % mod;
}

int get_hash2(int l, int r) {
	return ((f2[r] - p2[r - l + 1] * f2[l - 1] + mod) % mod + mod) % mod;
}

void solve() {
	f[0] = f2[0] = 0;
	p[0] = p2[0] = 1;
	string s, t;
	cin >> s >> t;
	int n1 = (int)s.length();
	int n2 = (int)t.length();
	if (n1 > n2){
		cout << 0 << '\n';
		return;
	}
	s = ' ' + s;
	t = ' ' + t;
	for (int i = 1; i < maxn; i++)
		p[i] = (p[i - 1] * base) % mod;

	for (int i = 1; i <= n2; i++)
		f[i] = (f[i - 1] * base + t[i] - 'a' + 1) % mod;

	for (int i = 1; i < maxn; i++)
		p2[i] = (p2[i - 1] * base2) % mod;

	for (int i = 1; i <= n2; i++)
		f2[i] = (f2[i - 1] * base2 + t[i] - 'a' + 1) % mod;

	for (int i = 1; i <= n1; i++)
		cnt1[s[i]]++;

	for (int i = 1; i <= n2; i++){
		if (i > n1)
			cnt2[t[i - n1]]--;
		cnt2[t[i]]++;
		int tmp = get_hash(i - n1 + 1, i);
		int tmp2 = get_hash2(i - n1 + 1, i);
		int ck = 0;
		for (int j = 'a'; j <= 'z'; j++){
			if (cnt1[j] != cnt2[j]){
				ck = 1;
				break;
			}
		}
		if (ck)
			continue;
		num.insert({tmp, tmp2});
	}
	cout << num.size() << '\n';
}

signed main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
#endif
	solve();
	return 0;
}