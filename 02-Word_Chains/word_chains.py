from collections import defaultdict
import sys

problems = [] # Problems array, store tuples (word1, word2, path) where path = 0 means no path specified
words_arr = [] # Array of words to be used for chains

blank_count = 0 # Keep track of the white space break for dictionary words

# Read in and interpret input from stdin. Convert all input to lowercase
# treating a capitalised letter/word as the same.
for line in sys.stdin:
    if line.isspace():
        blank_count += 1
    # First section of input
    elif blank_count == 0: 
        
        split = line.lower().split()

        # Valid input is either "word1 word2" or "word1 word2 int"
        if ((len(split) != 2) and (len(split) != 3)): 
            print("Invalid input: ", line)
        elif len(split) == 3:
            try:
                length = int(split[2])
                prob = (split[0], split[1], length)
                problems.append(prob)
            except:
                print("Invalid input: ", line)
        elif len(split) == 2:
            prob = (split[0], split[1], 0)
            problems.append(prob)
    else:
        split = line.lower().split()
        # Valid input is single string "word"
        if len(split) == 1:
            word = split[0]
            try:
                length = int(word)
                print("Invalid input: ", line)
            except:
                # Don't want duplicates
                if word not in words_arr:
                    words_arr.append(split[0])


## Now construct graph of dictionary words
graph = defaultdict(list)

# Creates an edge between node u and v (undirected)
def addEdge(graph, u, v):
    graph[u].append(v)

# A BFS graph search method which returns the shortest path from start to end if length = 0,
# or returns a path of the specified length (length != 0)
def BFS(graph, start, end, length):
    # If length = 0, we want shortest path (i.e first path found)
    # If length != 0, we want the first path found of the given length
    paths = []
    queue= [[start]]
    
    while queue:
        path = queue.pop(0) # Current path at start of queue
        vertex = path[-1] # Last vertex in the path

        # Path found
        if vertex == end:
            if path not in paths:
                if length == 0 or length == len(path):
                    return path
            
   

        # For all adjacent vortices to our current vertex, if it's not in the current path
        # then append to current path and add to queue
        for curr_adj in graph[vertex]:
            if curr_adj not in path:
                new_path = list(path)
                new_path.append(curr_adj)
                queue.append(new_path)

    
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

# For each of the problems, print the path if found, if not we print the problem saying it's impossible
for prob in problems:
    path = BFS(graph, prob[0], prob[1], prob[2])
    if path != None:
        for node in path:
            print(node, end = ' ')
        print()
    else:
        if prob[2] == 0:
            print(prob[0] + " " + prob[1] + " " + "impossible")
        else:
            print(prob[0] + " " + prob[1] + " " + str(prob[2]) + " " + "impossible")
