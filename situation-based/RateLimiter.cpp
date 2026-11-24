#include <bits/stdc++.h>
using namespace std;

class RateLimiter{
    private:
        queue<int> requests;
        int limit;
        int windowSize;
    public:
        RateLimiter(int limit,int windowSize){
            this->limit=limit;
            this->windowSize=windowSize;
        }

        bool allowRequest(int currTime){
            while(!requests.empty() && currTime-requests.front()>=windowSize){
                requests.pop();
            }
            if(requests.size()<limit){
                requests.push(currTime);
                cout<<"Request allowed at "<<currTime<<endl;
                return true;
            }
            cout<<"Request Rejected at "<<currTime<<endl;
            return false;
        }
};

int main(){
    RateLimiter limiter(3,5);
    limiter.allowRequest(1);
    limiter.allowRequest(2);
    limiter.allowRequest(3);
    limiter.allowRequest(4);

    limiter.allowRequest(6);
    limiter.allowRequest(7);

    return 0;
}