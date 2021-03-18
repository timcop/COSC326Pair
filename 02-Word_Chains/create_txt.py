import sys

f = open("verylong.txt", "w+")

for line in sys.stdin:
    line = line.rstrip().split()
    for word in line:
        f.write(word + "\n")