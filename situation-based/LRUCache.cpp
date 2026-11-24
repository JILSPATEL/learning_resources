#include <bits/stdc++.h>
using namespace std;

class LRUCache{
    private:
        int capacity;
        vector<pair<int,int>> cache;
        unordered_map<int,int> mp;
    public:
        LRUCache(int capacity){
            this->capacity=capacity;
        }
        int get(int key){
            if(mp.find(key)==mp.end()){
                return -1;
            }
            int val=mp[key];
            for(int i=0;i<cache.size();i++){
                if(cache[i].first==key){
                    cache.erase(cache.begin()+i);
                    cache.insert(cache.begin(),{key,val});
                    break;
                }
            }
            return val;
        }
        void put(int key,int val){
            if(mp.find(key)!=mp.end()){
                mp[key]=val;
                for(int i=0;i<cache.size();i++){
                    if(cache[i].first==key){
                        cache.erase(cache.begin()+i);
                        cache.insert(cache.begin(),{key,val});
                        break;
                    }
                }
                return;
            }
            if(cache.size()==capacity){
                int lruKey=cache.back().first;
                cache.pop_back();
                mp.erase(lruKey);
            }
            cache.insert(cache.begin(),{key,val});
            mp[key]=val;
        }
        void display() {
            for (auto p : cache) {
                cout << "(" << p.first << "," << p.second << ") ";
            }
            cout << endl;
        }
};

int main(){
    LRUCache cache(2);
    cache.put(1, 10);
    cache.put(2, 20);

    cache.display();

    cout << "Get 1: " << cache.get(1) << endl;

    cache.put(3, 30);

    cache.display();

    cout << "Get 2: " << cache.get(2) << endl;

    cache.put(4, 40);

    cache.display();

    cout << "Get 3: " << cache.get(3) << endl;

    return 0;
}