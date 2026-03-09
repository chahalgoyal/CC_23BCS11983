import java.io.*;
import java.util.*;

public class Main {

    static final long BASE = 31;
    static final long MOD = 1000000007;
    static long[] power;

    // Precompute powers
    static void precomputePowers(int maxLen) {
        power = new long[maxLen + 1];
        power[0] = 1;
        for (int i = 1; i <= maxLen; i++) {
            power[i] = (power[i - 1] * BASE) % MOD;
        }
    }

    // Compute hash of string
    static long computeHash(String s) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            long val = (s.charAt(i) - 'a' + 1);
            hash = (hash + val * power[i]) % MOD;
        }
        return hash;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        String[] dict = new String[n];
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            dict[i] = sc.next();
            maxLen = Math.max(maxLen, dict[i].length());
        }

        precomputePowers(maxLen);

        HashSet<Long> set = new HashSet<>();

        // Store dictionary hashes
        for (int i = 0; i < n; i++) {
            set.add(computeHash(dict[i]));
        }

        // Process queries
        for (int i = 0; i < m; i++) {
            String query = sc.next();
            long originalHash = computeHash(query);
            boolean found = false;

            for (int j = 0; j < query.length(); j++) {
                char originalChar = query.charAt(j);
                long originalVal = (originalChar - 'a' + 1);

                // Remove original character contribution
                long baseHash = (originalHash
                        - (originalVal * power[j]) % MOD
                        + MOD) % MOD;

                // Try other characters
                for (char ch : new char[]{'a', 'b', 'c'}) {
                    if (ch == originalChar) continue;

                    long newVal = (ch - 'a' + 1);
                    long newHash = (baseHash + newVal * power[j]) % MOD;

                    if (set.contains(newHash)) {
                        System.out.println("YES");
                        found = true;
                        break;
                    }
                }

                if (found) break;
            }

            if (!found) {
                System.out.println("NO");
            }
        }

        sc.close();
    }
}