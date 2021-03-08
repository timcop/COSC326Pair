import sys

problems = [] # Problems array, store tuples (word1, word2, path) where path = 0 means no path specified
dictionary = [] # Array of words to be used for chains

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
                dictionary.append(word)
        

## Now construct graph of dictionary words

# Initialise adjacency matrix
adj = [[0 for i in range(len(dictionary))] for j in range(len(dictionary))]

for i, v in enumerate(dictionary):
    v_length = len(v)
    for j, u in enumerate(dictionary):
        u_length = len(u)
        if v_length == u_length:
            count = 0
            for k in range(len(u)):
                if v[k] == u[k]:
                    count += 1
            if count == v_length-1:
                adj[i][j] = 1
                adj[j][i] = 1
print(adj)
