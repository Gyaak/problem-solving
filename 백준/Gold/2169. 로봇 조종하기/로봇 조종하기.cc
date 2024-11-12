#include<iostream>

using namespace std;

#define INF -2000000001

int N,M;
int arr[1001][1001], cost[3][1001][1001];

void setInput()
{
    cin >> N >> M;
    for(int i = 0; i<N; i++)
        for(int j = 0; j<M; j++) {
            cin >> arr[i][j];
            cost[0][i][j] = INF;
            cost[1][i][j] = INF;
            cost[2][i][j] = INF;
        }
    cost[0][0][0] = arr[0][0];
    cost[1][0][0] = arr[0][0];
    cost[1][0][0] = arr[0][0];
}

int findMAX(int a, int b, int c = INF)
{
    return max(a,max(b,c));
}

bool isValid(int r, int c)
{
    if(r<0 || c<0 || r>=N || c>=M)
        return false;
    return true;
}

// 0 : 위 , 1 : 왼쪽 , 2 오른쪽
int Cost(int d, int r, int c)
{
    if(!isValid(r,c))
        return INF;
    if(cost[d][r][c]==INF) {
        cost[d][r][c] = arr[r][c];
        if(d==0)
            cost[d][r][c] += findMAX(Cost(0,r-1,c),Cost(1,r-1,c),Cost(2,r-1,c));
        if(d==1)
            cost[d][r][c] += findMAX(Cost(0,r,c-1),Cost(1,r,c-1));
        if(d==2)
            cost[d][r][c] += findMAX(Cost(0,r,c+1),Cost(2,r,c+1));
    }
    return cost[d][r][c];
}

int main()
{
    setInput();
    cout << findMAX(Cost(0,N-1,M-1),Cost(1,N-1,M-1),Cost(2,N-1,M-1)) << "\n";
    return 0;
}