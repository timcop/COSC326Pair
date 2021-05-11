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

### Topic Research
- https://www.geeksforgeeks.org/schedule-elevator-to-reduce-the-total-time-taken/
- ~~https://whyy.org/segments/science-of-waiting-elevators/~~
- __https://www.diva-portal.org/smash/get/diva2:811136/FULLTEXT01.pdf__
- ~~https://www.popularmechanics.com/technology/infrastructure/a20986/the-hidden-science-of-elevators/~~
- ~~https://web.eecs.umich.edu/~baveja/RLMasses/node2.html~~
- https://softwareengineering.stackexchange.com/questions/96278/elevator-algorithm-and-implementation
- https://www.i-programmer.info/programmer-puzzles/203-sharpen-your-coding-skills/4561-sharpen-your-coding-skills-elevator-puzzle.html?start=1
- https://thinksoftware.medium.com/elevator-system-design-a-tricky-technical-interview-question-116f396f2b1c
- https://abhayavachat.blogspot.com/2016/04/elevator-algorithm-perfect-interview.html

### Object-Oriented Design
- Simulation https://github.com/magwo/elevatorsaga
- https://stackoverflow.com/questions/493276/modelling-an-elevator-using-object-oriented-analysis-and-design
- __https://www.angelfire.com/trek/software/elevator.html__
- https://youtu.be/DhB8la7kGxc

### Strategies
**UPDATE**
Actually I'm going to try process scheduling methods now

**Collective Control Elevator Algorithm**
1. As long as there’s someone inside or ahead of the elevator who wants to go in the current direction, keep heading in that direction.
2. Once the elevator has exhausted the requests in its current direction, switch directions if there’s a request in the other direction.  Otherwise, stop and wait for a call.

**First Come First Served (FCFS)**
- The elevator would respond to the first request button, go to that floor, drop the person to the requested floor and then move on to the next person.
- At first glance, it’s clear that this will generate excessive up and down movement of the elevator. It also will not be the most time efficient.
- But it’s not all bad. It’s very fair in terms of serving, if fairness means relative wait time. Choosing the next request would be very fast, based on a simple queue data structure.

**Shortest Seek Time First (SSTF)**
- Whenever the elevator drops a person to the desired floor, it can look at all the requests, and pick the request that’s originating at the nearest floor. It will minimize the distance that elevator has to travel to start serving another request.
- There is a possibility that requests keep coming from a region of nearby floors, and the elevator doesn’t get a chance to serve older requests from floors that are far away. This can lead to starvation for some requests, as there is no upper bound on the wait time.
- Note that a slightly more complex structure than a queue, would be needed to store the requests, so that they can be searched quickly to find out the next best request. That would be another side question.

**SCAN**

**LOOK**

