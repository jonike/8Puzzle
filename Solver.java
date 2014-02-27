/*
 *
 */

/**
 *
 * @author Webber Huang
 */

public class Solver {
    private final SearchNode initialSN; 
    private SearchNode goalSN;
    
    // Search Node implementation
    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode prev;
        private final int move, priority;
        
        public SearchNode(Board board, SearchNode prev, int move) {
            this.board = board;
            this.prev = prev;
            this.move = move;
            priority = board.manhattan() + move;
        }
        
        public int compareTo(SearchNode that) {
            return this.priority - that.priority;
        }
        
        public boolean equals(Object y) {
            if (y == this) return true;
            if (y == null) return false;
            if (y.getClass() != this.getClass())
                return false;
        
            SearchNode that = (SearchNode) y;
            return this.board.equals(that.board);
        }
        
        public boolean isGoal() {
            return board.isGoal();
        }
        
        public SearchNode twin() {
            return new SearchNode(board.twin(), prev, move);
        }
        
        public Iterable<SearchNode> neighbors() {  
            Queue<SearchNode> queue = new Queue<>();
            for (Board b: board.neighbors()) {
                SearchNode curr = new SearchNode(b, this, move+1);
                if (!curr.equals(this.prev)) queue.enqueue(curr); 
            }
            return queue;
        }
    }
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        initialSN = new SearchNode(initial, null, 0);
        
        // run solver
        solveIt();
    }    
    
    private void solveIt() {        
        SearchNode currSN = initialSN;
        MinPQ<SearchNode> snQueue = new MinPQ<>();
        snQueue.insert(currSN);
        
        SearchNode twinSN = initialSN.twin();
        MinPQ<SearchNode> twinQueue = new MinPQ<>();
        twinQueue.insert(twinSN);
        
        while (!currSN.isGoal() && !twinSN.isGoal()) {            
            for (SearchNode node : currSN.neighbors())
                snQueue.insert(node);
            currSN = snQueue.delMin();
            
            // solve twin search node            
            for (SearchNode node : twinSN.neighbors())
                twinQueue.insert(node);
            twinSN = twinQueue.delMin();
        }
        goalSN = currSN;
    }
    
    // is the initial board solvable?
    public boolean isSolvable() {        
        return goalSN.isGoal();
    }  
    
    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        if (isSolvable()) return goalSN.move;
        return -1;
    }  
    
    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        
        SearchNode currSN = goalSN;
        Stack<Board> stepQueue = new Stack<>();
        while (currSN.prev != null) {
            stepQueue.push(currSN.board);
            currSN = currSN.prev;
        }
        stepQueue.push(initialSN.board);
        return stepQueue;
    }
    
    // solve a slider puzzle (given below)   
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        //String path = "E:/Coursera/Algorithms, Part I/Week 4/Assignment/8puzzle/src/puzzle30.txt";
        //In in = new In(path);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

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
    }
            
}
