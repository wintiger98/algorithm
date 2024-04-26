#include <cstdio>
#include <queue>
#include <algorithm>
using namespace std;
bool visited[1001][1001][11][2];
int mat[1001][1001],dix[4]={1,0,-1,0},diy[4]={0,-1,0,1},n,m,k,cnt[1001][1001][11][2];
void bfs(){
	cnt[0][0][0][0]=1;
	queue<pair<pair<int,int>,pair<int,int> > > q;
	q.push(make_pair(make_pair(0,0),make_pair(0,0)));
	while(!q.empty()){
		int y=q.front().first.first,x=q.front().first.second;
		int broken=q.front().second.first,day=q.front().second.second;
		if(y==n-1&&x==m-1){
			printf("%d",cnt[y][x][broken][day]);
			return;
		}
		q.pop();
		for(int i=0;i<4;i++){
			int my=y+diy[i],mx=x+dix[i];
			if(0<=my&&my<n&&0<=mx&&mx<m&&!visited[my][mx][broken][!day]){
				if(mat[my][mx]==0&&(cnt[my][mx][broken][!day]>cnt[y][x][broken][day]+1||cnt[my][mx][broken][!day]==0)){
					visited[my][mx][broken][!day]=true;
					q.push(make_pair(make_pair(my,mx),make_pair(broken,!day)));
					cnt[my][mx][broken][!day]=cnt[y][x][broken][day]+1;
				}
				else if(broken<k){
					if(day==0&&(cnt[my][mx][broken+1][!day]>cnt[y][x][broken][day]+1||cnt[my][mx][broken+1][!day]==0)){
						visited[my][mx][broken+1][!day]=true;
						q.push(make_pair(make_pair(my,mx),make_pair(broken+1,!day)));
						cnt[my][mx][broken+1][!day]=cnt[y][x][broken][day]+1;
					}
					else if(cnt[y][x][broken][!day]>cnt[y][x][broken][day]+1||cnt[y][x][broken][!day]==0){
						q.push(make_pair(make_pair(y,x),make_pair(broken,!day)));
						cnt[y][x][broken][!day]=cnt[y][x][broken][day]+1;
					}
				}
			}
		}
	}
	printf("-1");
}
int main(){
	scanf("%d %d %d",&n,&m,&k);
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			scanf("%1d",&mat[i][j]);
		}
	}
	bfs();
}