import numpy as np
import random
from matplotlib import pyplot as plt

cards = [0,0,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,14,15,15]
cards = np.array(cards)
N = 2
num_sim = 100000
std_all = []
for i in range(num_sim):
    sample = random.sample(range(len(cards)), N)
    sample_cards = []
    for ind in sample:
        sample_cards.append(cards[ind])

    std = np.std(sample_cards)
    std_all.append(std)


plt.hist(std_all, bins=40)
plt.show()
