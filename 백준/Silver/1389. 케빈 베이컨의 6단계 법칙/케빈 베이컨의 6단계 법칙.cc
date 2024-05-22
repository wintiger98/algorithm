#include <cstdio>
#include <queue>
#include <cstring>
using namespace std;
vector<int> v[101];
bool visited[101];
int n;
int bfs(int start){
	queue<pair<int,int> >q;
	q.push(make_pair(start,0));
	int res=0;
	visited[start]=true;
	while(!q.empty()){
		int go=q.front().first,cnt=q.front().second;q.pop();
		for(int i=0;i<v[go].size();i++){
			if(!visited[v[go][i]]){
				visited[v[go][i]]=true;
				res+=cnt+1;
				q.push(make_pair(v[go][i],cnt+1));
			}
		}
	}
	return res;
}
int main(){
	int m,min_index=0,min_value=2100000000;
	scanf("%d %d",&n,&m);
	for(int i=0;i<m;i++){
		int a,b;
		scanf("%d %d",&a,&b);
		v[a].push_back(b);
		v[b].push_back(a);
	}
	for(int i=1;i<=n;i++){
		memset(visited,false,sizeof(visited));
		int cmp=bfs(i);
		if(cmp<min_value){
			min_value=cmp;
			min_index=i;
		}
	}
	printf("%d",min_index);
}