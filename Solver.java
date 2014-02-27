/*
 *
 */

/**
 *
 * @author Webber Huang
 */

public class Solver {
    private SearchNode result;
    
    // Search Node implementation
    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode prev;
        private final int move, priority;
        
        public SearchNode(Board board, SearchNode prev) {
            this.board = board;
            this.prev = prev;
            if (prev == null) move = 0;
            else move = prev.move + 1;
            priority = board.manhattan() + move;
        }
        
        public int compareTo(SearchNode that) {
            return this.priority - that.priority;
        }
        
        public boolean isGoal() {
            return board.isGoal();
        }
        
        public SearchNode twin() {
            return new SearchNode(board.twin(), prev);
        }
    }
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        // run solver
        result = solveIt(new SearchNode(initial, null));
    }    
    
    private SearchNode calculateOneStep(MinPQ<SearchNode> pq) {
        SearchNode least = pq.delMin();
        for (Board neighbor : least.board.neighbors()) {
            if (least.prev == null || !neighbor.equals(least.prev.board))
                pq.insert(new SearchNode(neighbor, least));
        }
        return least;
    }
    
    private SearchNode solveIt(SearchNode initial) {        
        SearchNode last = initial;
        MinPQ<SearchNode> snQueue = new MinPQ<>();
        snQueue.insert(last);
        
        MinPQ<SearchNode> twinQueue = new MinPQ<>();
        twinQueue.insert(initial.twin());        
        
        while (true) {            
            last = calculateOneStep(snQueue);
            if (last.isGoal()) return last;
            
            // solve twin search node            
            if (calculateOneStep(twinQueue).isGoal()) return null;
        }
    }
    
    // is the initial board solvable?
    public boolean isSolvable() {        
        return result != null;
    }  
    
    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        if (isSolvable()) return result.move;
        return -1;
    }  
    
    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;        
        Stack<Board> s = new Stack<>();
        for (SearchNode n = result; n != null; n = n.prev) {
            s.push(n.board);
        }
        return s;
    }
    
    // solve a slider puzzle (given below)   
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        //String path = "E:/Coursera/Algorithms, Part I/Week 4/Assignment/8puzzle/src/puzzle34.txt";
        //In in = new In(path);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        Stopwatch time1 = new Stopwatch();

        // solve the puzzle
        Solver solver = new Solver(initial);        

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        StdOut.println("Time escape:   " + time1.elapsedTime());
    }
            
}
