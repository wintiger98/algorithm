import copy
n = int(input())
nums = list(map(int, input().split()))
operations = list(map(int, input().split()))
answer = []

def dfs(idx, result, used):
    if idx == n-1:
        answer.append(result)
        return
    else:
        idx += 1
        if operations[0] > used[0]:
            tmp_used = copy.deepcopy(used)
            tmp_used[0] += 1
            dfs(idx, result+nums[idx], tmp_used)
            
        if operations[1] > used[1]:
            tmp_used = copy.deepcopy(used)
            tmp_used[1] += 1
            dfs(idx, result-nums[idx], tmp_used)

        if operations[2] > used[2]:
            tmp_used = copy.deepcopy(used)
            tmp_used[2] += 1
            dfs(idx, result*nums[idx], tmp_used)

        if operations[3] > used[3]:
            tmp_used = copy.deepcopy(used)
            tmp_used[3] += 1
            if result < 0:
                dfs(idx, (-1)*(((-1) * (result)) // nums[idx]), tmp_used)
            else:
                dfs(idx, result//nums[idx], tmp_used)

dfs(0, nums[0], [0,0,0,0])
print(max(answer))
print(min(answer))