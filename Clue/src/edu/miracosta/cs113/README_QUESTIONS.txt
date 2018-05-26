
Prompt Answers (Clue Assignment)
********************************

Author: Reesha Rajen
Professor: Nery Chapeton-Lamas
Date: 30th January, 2018


1. How often do you get a solution in fewer than 20 guesses? Does this change depending
   on the theory you test (1, 2, 3)?

    A solution is found in as little as one guess to as many as 20 guesses, exactly. However,
the number of guesses will never exceed 20. This fluctuates depending on the theory
tested.
    For example, testing the first theory (1) results in only using one guess, because
my algorithm initializes all weapon, location, and murderer guesses as the first of
each, respectively.
    Testing the second theory (2) results in using exactly twenty guesses, due to the
fact that my algorithm begins at the first of each weapon, location, and murderer,
and must increment each guess until the last of each weapon, location, and
murderer is reached. This takes exactly twenty guesses, no more, no less.
Testing the third theory (3) gives an average result of ten guesses. Mathematically,
this makes sense, as ten is the immediate median of the range being dealt with.

2. What is good or bad about this strategy?

    The good news about this algorithm is that it logically makes sense to initialize
all guesses by starting at (1, 1, 1) and ending at (6, 10, 6). Therefore, if theory
one is the true theory, the algorithm will always reach it immediately. However, this
may make more sense to a human than to a computer.
    The bad news about this algorithm is that it will always take 20 tries to reach a theory
like (6, 10, 6), if theory three is the true theory. This is not entirely efficient.
If more weapons, locations, and murderers are added to the list (such as newer Clue
games), then the algorithm will always reach (last, last, last) in the most amount
of guesses.
