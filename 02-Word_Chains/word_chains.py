from collections import defaultdict, deque
import sys
from time import time


problems = [] # Problems array, store tuples (word1, word2, path) where path = 0 means no path specified
words_set = set() # Set of words to be used for chains

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
            line = "Invalid input: " + line
            problems.append(line.strip())
        elif len(split) == 3:
            try:
                length = int(split[2])
                if length > 0:
                    prob = (split[0], split[1], length)
                    problems.append(prob)
                else:
                    line = "Invalid input: " + line
                    problems.append(line.strip())
            except:
                line = "Invalid input: " + line
                problems.append(line.strip())
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
                line = "Invalid input: " + line
                problems.append(line.strip())
            except:
                # Don't want duplicates
                if word not in words_set:
                    words_set.add(split[0])

## Now construct graph of dictionary words
graph = defaultdict(list)

# Creates an edge between node u and v (undirected)
def addEdge(graph, u, v):
    graph[u].append(v)

# A BFS graph search method which returns the shortest path from start to end.
def BFS_shortest(graph, start, end):

    if start == end:
        return [start, end]
    queue = deque([[start]])
    visited = set()
    
    while queue:
        path = queue.popleft()
        vertex = path[-1]
            
        # For all adjacent vortices to our current vertex, if it hasn't been visited
        # then make a new path appending the adj vertex and add it to the queue
        for curr_adj in graph[vertex]:
            if curr_adj not in visited:
                if curr_adj == end:
                    path.append(curr_adj)
                    return path
                
                new_path = list(path)
                new_path.append(curr_adj)
                queue.append(new_path)
        visited.add(vertex)
    return None

## DEPTH 
def DFS_length(graph, start, end, length):
    
    stack = deque([[start]])

    while stack:
        path = stack.pop()
        # When paths are longer than the desired length we get rid of them
        if len(path) <= length:
            vertex = path[-1]

            if vertex == end:
                if length == len(path):
                    return path

            for curr_adj in graph[vertex]:
                # Make sure it's not in the current path, we don't want cycles.
                if curr_adj not in path:
                    new_path = list(path)
                    new_path.append(curr_adj)
                    stack.append(new_path)
    return None

# Loop through the word set, compute possible word chain words and see if they're in the word set. 
# If they are, we create edges in the graph. 
while words_set:
    word = words_set.pop()    #ASCII a = 97, z = 122
    new_word = list(word)
    for i, letter in enumerate(new_word):
        #ASCII a = 97, z = 122
        for j in range(97, 123):
            new_letter = chr(j)
            if new_letter != letter:
                new_word[i] = new_letter
                new_word = ''.join(new_word)
                if new_word in words_set:
                    addEdge(graph, word, new_word)
                    addEdge(graph,new_word, word)
            new_word = list(word)



# For each of the problems, print the path if found, if not we print the problem saying it's impossible
for prob in problems:
    if isinstance(prob, str):
        print(prob)
    else:
        if prob[2] == 0:
            path = BFS_shortest(graph, prob[0], prob[1])
        else:
            path = DFS_length(graph, prob[0], prob[1], prob[2])
        
        if path != None:
            for node in path:
                print(node, end = ' ')
            print()
        else:
            if prob[2] == 0:
                print(prob[0] + " " + prob[1] + " " + "impossible")
            else:
                print(prob[0] + " " + prob[1] + " " + str(prob[2]) + " " + "impossible")
