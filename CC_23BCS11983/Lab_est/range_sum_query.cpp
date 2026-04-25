class SegmentTree{
    public:
    vector<int>seg;
    SegmentTree(int n){
        seg.resize(4*n);
    }
    void built(int i,int low,int high,vector<int>&nums){
        if(low==high){
            seg[i]=nums[low];
            return;
        }
        int mid=(low+high)>>1;
        built(2*i+1,low,mid,nums);
        built(2*i+2,mid+1,high,nums);
        seg[i]=seg[2*i+1]+seg[2*i+2];
        return;
    }
    int query(int i,int left,int right,int low,int high){
         if(right<low || high<left) return 0;
         else if(low>=left && high<=right) return seg[i];
         int mid=(low+high)>>1;
         return query(2*i+1,left,right,low,mid)+query(2*i+2,left,right,mid+1,high);
    }
    void update(int i,int low,int high,int idx,int val){
        if(low==high){
            seg[i]=val;
            return;
        }
        int mid=(low+high)>>1;
        if(idx<=mid) update(2*i+1,low,mid,idx,val);
        else update(2*i+2,mid+1,high,idx,val);
        seg[i]=seg[2*i+1]+seg[2*i+2];
        return; 
    }
};
class NumArray {
public:
    SegmentTree*seg;
    int n;
    NumArray(vector<int>& nums) {
        int n=nums.size();
        this->n=n;
        seg=new SegmentTree(n);
        seg->built(0,0,n-1,nums);
    }
    
    void update(int index, int val) {
        seg->update(0,0,n-1,index,val);
    }
    
    int sumRange(int left, int right) {
        return seg->query(0,left,right,0,n-1);
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */
