from collections import defaultdict

import sys

problems = [] # Problems array, store tuples (word1, word2, path) where path = 0 means no path specified
words_arr = [] # Array of words to be used for chains

for line in sys.stdin:
    if not(line.isspace()):
        try:
            word1, word2 = line.split()
            prob = (word1, word2, 0)
            problems.append(prob)
        except:
            try:
                word1, word2, path = line.split()
                prob = (word1, word2, path)
                problems.append(prob)

            except:
                word = line.strip().lower()
                words_arr.append(word)
        

graph = defaultdict(list)
def addEdge(graph, u, v):
    graph[u].append(v)

def generate_edges(graph):
    edges = []

    for node in graph:

        for neighbour in graph[node]:
            edges.append((node, neighbour))

    return edges

def find_all_paths(graph, start, end, path =[]): 
    path = path + [start] 
    if start == end: 
        return [path] 
    paths = [] 
    for node in graph[start]: 
        if node not in path: 
            newpaths = find_all_paths(graph, node, end, path) 
            for newpath in newpaths: 
                paths.append(newpath) 
    return paths 

def find_shortest_path(graph, start, end, path =[]): 
        path = path + [start] 
        if start == end: 
            return path 
        shortest = None
        for node in graph[start]: 
            if node not in path: 
                newpath = find_shortest_path(graph, node, end, path) 
                if newpath: 
                    if not shortest or len(newpath) < len(shortest): 
                        shortest = newpath 
        return shortest 


for i, v in enumerate(words_arr):
    v_length = len(v)
    for j, u in enumerate(words_arr):
        u_length = len(u)
        if v_length == u_length:
            count = 0
            for k in range(len(u)):
                if v[k] == u[k]:
                    count += 1
                
            if count == v_length-1:
                addEdge(graph, v, u)


for prob in problems:
    if prob[2] == 0:
        path = find_shortest_path(graph, prob[0], prob[1])
        if path != None:
            for node in path:
                print(node, end =" ")
            print()
        else:
            print(prob[0] + " " + prob[1] + " " + "impossible")
    else:
        paths = find_all_paths(graph, prob[0], prob[1])
        if paths == None:
            print(prob[0] + " " + prob[1] + " " + str(prob[2]) + " " + "impossible")
        else:
            for i, path in enumerate(paths):
                if len(path) == int(prob[2]):
                    for node in path:
                        print(node, end=" ")
                    print()
                elif i == len(path)-1:
                    print(prob[0] + " " + prob[1] + " " + str(prob[2]) + " " + "impossible")
