#include <bits/stdc++.h>
using namespace std;
int main(){
	ios::sync_with_stdio(false);
	cin.tie(0);
	int n,time[501],in[501]={},ans[501]={};
	vector<int> build[501];
	cin >> n;
	for(int i = 1; i <= n; i++){
		cin >> time[i];
		while(1){
			int x;
			cin >> x;
			if(x == -1)break;
			in[i]++;
			build[x].push_back(i);
		}
	}
	queue<int> q;
	for(int i = 1; i <= n; i++){
		if(in[i] == 0){
			q.push(i);
			ans[i] = time[i];
		}
	}
	while(!q.empty()){
		int select = q.front();
		q.pop();
		for(int i = 0; i < build[select].size(); i++){
			in[build[select][i]]--;
			ans[build[select][i]] = max(ans[build[select][i]], ans[select]);
			if(in[build[select][i]] == 0){
				ans[build[select][i]] += time[build[select][i]];
				q.push(build[select][i]);
			}	
		}
	}
	for(int i = 1; i <= n; i++){
		cout<<ans[i]<<'\n';
	}
}