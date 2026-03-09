public class zAlgorithm {

    // Build Z array
    public static int[] computeZ(String s) {
        int n = s.length();
        int[] Z = new int[n];

        int left = 0, right = 0;

        for (int i = 1; i < n; i++) {

            if (i <= right) {
                Z[i] = Math.min(right - i + 1, Z[i - left]);
            }

            while (i + Z[i] < n && s.charAt(Z[i]) == s.charAt(i + Z[i])) {
                Z[i]++;
            }

            if (i + Z[i] - 1 > right) {
                left = i;
                right = i + Z[i] - 1;
            }
        }

        return Z;
    }

    // Pattern Matching using Z
    public static void search(String pattern, String text) {

        String combined = pattern + "$" + text;
        int[] Z = computeZ(combined);

        int pLen = pattern.length();

        for (int i = 0; i < Z.length; i++) {
            if (Z[i] == pLen) {
                System.out.println("Pattern found at index: " + (i - pLen - 1));
            }
        }
    }

    public static void main(String[] args) {

        String text = "ababcababc";
        String pattern = "ababc";

        search(pattern, text);
    }
}