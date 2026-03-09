class Solution {

    public int countBits(int N, int[] A) {

        long MOD = 1000000007L;
        long answer = 0;

        // Iterate through all 31 bit positions (0 to 30)
        for (int bit = 0; bit < 31; bit++) {

            long count1 = 0;

            // Count numbers having current bit set
            for (int i = 0; i < N; i++) {
                if ((A[i] & (1 << bit)) != 0) {
                    count1++;
                }
            }

            long count0 = N - count1;

            // Contribution of this bit (ordered pairs)
            long contribution = (2 * count1 * count0) % MOD;

            answer = (answer + contribution) % MOD;
        }

        return (int) answer;
    }
}
