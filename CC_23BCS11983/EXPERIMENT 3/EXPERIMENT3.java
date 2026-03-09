class Solution {
    public long gcd(long a, long b){
        if(b == 0) return a;
        else return gcd(b,a%b);
    }

    public long lcm(long a , long b) {
        return (a/gcd(a,b)) * b;
    }

    public int nthMagicalNumber(int n, int a, int b) {
        long low = Math.min(a,b), high = n * low, count = 0, mid = (high + low)/2, l = lcm(a,b);
        int MOD = 1_000_000_007;
        while(low <= high){
            mid = (high + low) / 2;

            count = mid/a + mid/b - mid/l;

            if(count>=n){
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return (int) (low % MOD);
    }
}