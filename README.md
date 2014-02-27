8Puzzle
=======

Algorithms, Part I - Assignment 4: 8 Puzzle

Performance statistics:

================================================================

******************************************************************************
*  memory usage
******************************************************************************

Computing memory of Board
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of an N-by-N board

              N       student (bytes)    reference (bytes)
----------------------------------------------------------
=> passed     4            88                  248
=> passed     8           184                  568
=> passed    12           344                 1016
=> passed    16           568                 1592
=> passed    20           856                 2296
=> passed    36          2648                 6392
=> passed    72         10424                23096
=> passed   120         28856                61496
==> 8/8 tests passed

Total: 8/8 tests passed!

Student   memory = 2.00 N^2 + 0.00 N + 56.00  (R^2 = 1.000)
Reference memory = 4.00 N^2 + 32.00 N + 56.00  (R^2 = 1.000)

================================================================



******************************************************************************
*  timing
******************************************************************************

Timing Solver
*-----------------------------------------------------------
Running 17 total tests.

Timing tests use your implementation of Board.java and Solver.java. Maximum allowed time per puzzle is 15 seconds.

                                                              delMin()
               filename   N    seconds    insert()          + delMax()         max PQ size
---------------------------------------------------------------------------------------------
=> passed  puzzle20.txt   3     0.07       1522                 897                 625         
=> passed  puzzle21.txt   3     0.06       2751                1615                1136         
=> passed  puzzle22.txt   3     0.07       3084                1833                1251         
=> passed  puzzle23.txt   3     0.11       5412                3189                2223         
=> passed  puzzle24.txt   3     0.06       6500                3941                2559         
=> passed  puzzle25.txt   3     0.04      11097                6579                4518         
=> passed  puzzle26.txt   3     0.02       5345                3189                2156         
=> passed  puzzle27.txt   3     0.03      10067                6043                4024         
=> passed  puzzle28.txt   3     0.08      24033               14375                9658         
=> passed  puzzle29.txt   3     0.05      24525               14817                9708         
=> passed  puzzle30.txt   3     0.09      41680               25163               16517         
=> passed  puzzle31.txt   3     0.12      53554               32505               21049         
=> passed  puzzle34.txt   4     0.84     254936              121659              133277         
=> passed  puzzle37.txt   4     0.49     158105               76215               81890         
=> passed  puzzle39.txt   4     0.47     151048               74157               76891         
=> passed  puzzle41.txt   5     0.37      77031               32947               44084         
=> passed  puzzle44.txt   5     1.13     311230              138177              173053         
==> 17/17 tests passed

Total: 17/17 tests passed!

================================================================
