# Etude 5 - Elevators

### Output
A report for a client with various strategies on how to operate their elevators efficiently (reduce waiting and travel times).

### Task
Strategies for directing the elevator such that the waiting and travel times for the users are minimised.

**Properties of the building**
- 10 floors in the building

**Properties of the elevator**
- Can hold <=4 people
- 5 seconds to travel from one floor to next
- 10 seconds for doors to open and close

### Helpful Links
*I'm currently reading through these, so some may or may not be relevant*
- https://www.geeksforgeeks.org/schedule-elevator-to-reduce-the-total-time-taken/
- https://whyy.org/segments/science-of-waiting-elevators/
- https://www.diva-portal.org/smash/get/diva2:811136/FULLTEXT01.pdf
- https://www.popularmechanics.com/technology/infrastructure/a20986/the-hidden-science-of-elevators/
- https://web.eecs.umich.edu/~baveja/RLMasses/node2.html
- https://softwareengineering.stackexchange.com/questions/96278/elevator-algorithm-and-implementation
- https://www.i-programmer.info/programmer-puzzles/203-sharpen-your-coding-skills/4561-sharpen-your-coding-skills-elevator-puzzle.html?start=1
- https://thinksoftware.medium.com/elevator-system-design-a-tricky-technical-interview-question-116f396f2b1c
- https://abhayavachat.blogspot.com/2016/04/elevator-algorithm-perfect-interview.html

### Ideas
- `floors` = an array of passengers corresponding to their desired floor number
- `time` = time taken for passenger to reach destination
- Create a simulation with a timer

**Strategy 1**: Sort `floors` in descending order and arrange in groups of 4
**Strategy 2**: Same as above but sort `floors` in ascending order instead
