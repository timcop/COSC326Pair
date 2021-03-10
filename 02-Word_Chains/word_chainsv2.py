from collections import defaultdict

import sys

problems = [] # Problems array, store tuples (word1, word2, path) where path = 0 means no path specified
words_arr = [] # Array of words to be used for chains

# Read in and interpret input from stdin
for line in sys.stdin:
    if not(line.isspace()):
        # First try the case where we have two words on a line
        try:
            word1, word2 = line.split()
            prob = (word1, word2, 0)
            problems.append(prob)
        except:
            # Next try the case where we have 2 words and a path distance
            try:
                word1, word2, path = line.split()
                prob = (word1, word2, path)
                problems.append(prob)

            except:
                # Finally, we must have single words which we store in our words_arr
                word = line.strip().lower()
                words_arr.append(word)
        

graph = defaultdict(list) # Create a graph

# Adds edge from u -> v
def addEdge(graph, u, v):
    graph[u].append(v)

# Finds all paths between start and end vortices, storing in an array paths = [path1, path2, ...] where path1 = [start, node1, ..., end] etc
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

# Finds shortest path between start and end vortices, return path = [start, node1, ..., end]
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

# Create edges between word vortices. We count how many characters two nodes have in common,
# if the count is equal to length(word) - 1 then we know they differ by one character and 
# thus we create and edge joining them.
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

# Loop through the problems we have to solve in our problems array.
# Remember prob = (start, end, distance), so if prob[2] = 0 we want to find the shortest path,
# else we want to find a path of a given length. If no path is found, we print the problem
# and say "impossible"
for prob in problems:
    #print(prob)
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
            count = 0
            for i, path in enumerate(paths):
                if len(path) == int(prob[2]):
                    for node in path:
                        print(node, end=" ")
                    print()
                    count = 1
                    break
            if count == 0:
                print(prob[0] + " " + prob[1] + " " + str(prob[2]) + " " + "impossible")
