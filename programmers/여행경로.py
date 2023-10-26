def solution(tickets):
    answer = []
    graph = {}
    for a, b in sorted(tickets, reverse=True):
        if a in graph:
            graph[a].append(b)
        else:
            graph[a] = [b]

    def dfs(a):
        while a in graph and graph[a]:
            dfs(graph[a].pop())
        answer.append(a)

    dfs("ICN")
    return answer[::-1]
