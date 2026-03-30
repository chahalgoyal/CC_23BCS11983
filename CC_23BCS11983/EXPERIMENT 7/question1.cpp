class Solution {
public:
    int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst,
                          int k) {
        vector<vector<pair<int, int>>> adj(n);
        for (auto& it : flights) {
            adj[it[0]].push_back({it[1], it[2]});
        }
        priority_queue<pair<int, pair<int, int>>,
                       vector<pair<int, pair<int, int>>>,
                       greater<pair<int, pair<int, int>>>>
            pq;
        // k, {dist, node}
        vector<int> dis(n, INT_MAX);
        dis[src] = 0;
        pq.push({0, {0, src}});
        while (!pq.empty()) {
            auto compK = pq.top().first;
            auto dist = pq.top().second.first;
            auto node = pq.top().second.second;
            pq.pop();
            if (compK > k)
                continue;
            for (auto& it : adj[node]) {
                int v = it.first;
                int wt = it.second;
                if (dist + wt < dis[v]) {
                    dis[v] = dist + wt;
                    pq.push({compK + 1, {dis[v], v}});
                }
            }
        }
        return dis[dst] == INT_MAX ? -1 : dis[dst];
    }
};
