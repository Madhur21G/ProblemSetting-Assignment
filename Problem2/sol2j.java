import java.io.*;
import java.util.*;

public class Main {
    static final int MAXN = 200005;
    static final int INF = 100000000;
    static final long INF_L = 1000000000000000000L;
    static final int MOD = 1000000001;
    static final int MOD2 = 1000000007;
    static final int BASE = 31;
    static final int BASE2 = 67;

    static int[] cnt1 = new int[26]; // Only 26 letters in English alphabet
    static int[] cnt2 = new int[26]; // Only 26 letters in English alphabet
    static long[] f = new long[MAXN];
    static long[] p = new long[MAXN];
    static long[] f2 = new long[MAXN];
    static long[] p2 = new long[MAXN];
    static Set<Pair> num = new HashSet<>();

    static class Pair {
        long first, second;

        Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    static long getHash(int l, int r) {
        return ((f[r] - p[r - l + 1] * f[l - 1] % MOD + MOD) % MOD + MOD) % MOD;
    }

    static long getHash2(int l, int r) {
        return ((f2[r] - p2[r - l + 1] * f2[l - 1] % MOD2 + MOD2) % MOD2 + MOD2) % MOD2;
    }

    public static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        
        Arrays.fill(f, 0);
        Arrays.fill(f2, 0);
        Arrays.fill(cnt1, 0);
        Arrays.fill(cnt2, 0);
        p[0] = p2[0] = 1;
        
        String s = br.readLine();
        String t = br.readLine();
        int n1 = s.length();
        int n2 = t.length();
        
        if (n1 > n2) {
            out.println(0);
            out.close();
            return;
        }
        
        // Add padding to make strings 1-indexed
        s = " " + s;
        t = " " + t;
        
        for (int i = 1; i < MAXN; i++) {
            p[i] = (p[i - 1] * BASE) % MOD;
            p2[i] = (p2[i - 1] * BASE2) % MOD2;
        }
        
        for (int i = 1; i <= n2; i++) {
            f[i] = (f[i - 1] * BASE + t.charAt(i) - 'a' + 1) % MOD;
            f2[i] = (f2[i - 1] * BASE2 + t.charAt(i) - 'a' + 1) % MOD2;
        }
        
        for (int i = 1; i <= n1; i++) {
            cnt1[s.charAt(i) - 'a']++;
        }
        
        for (int i = 1; i <= n2; i++) {
            if (i > n1) {
                cnt2[t.charAt(i - n1) - 'a']--;
            }
            cnt2[t.charAt(i) - 'a']++;
            
            if (i >= n1) {
                long tmp = getHash(i - n1 + 1, i);
                long tmp2 = getHash2(i - n1 + 1, i);
                
                boolean ck = false;
                for (char j = 'a'; j <= 'z'; j++) {
                    if (cnt1[j - 'a'] != cnt2[j - 'a']) {
                        ck = true;
                        break;
                    }
                }
                if (!ck) {
                    num.add(new Pair(tmp, tmp2));
                }
            }
        }
        
        out.println(num.size());
        out.close();
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}
