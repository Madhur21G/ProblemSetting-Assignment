import java.util.*;
import java.io.*;

public class Main {
    static final int M1 = 69421;
    static final int M2 = 1000000009;

    public static void main(String[] args) {
        FastReader sc = new FastReader();
        int tc = sc.nextInt();
        while (tc-- > 0) {
            solve(sc);
        }
    }

    static void solve(FastReader sc) {
        String s = sc.next();
        int n = s.length();
        int ans = 0;
        int st = 0, fnd = 0;

        while (fnd != -1) {
            int hshl = 0;
            int hshr = 0;
            fnd = -1;
            for (int i = st, pw = 1; i < n / 2; i++, pw = (int)((pw * (long)M1) % M2)) {
                hshl = (int)((hshl * (long)M1 + s.charAt(i)) % M2);
                hshr = (int)((hshr + pw * (long)s.charAt(n - i - 1)) % M2);
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

        if (2 * st == n) System.out.println(ans);
        else System.out.println(ans + 1);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
