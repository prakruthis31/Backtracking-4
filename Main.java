// "static void main" must be defined in a public class.
public class Main {
    public static class BuildingPlacement {
        int min, H, W, n;
        int [][] grid;
        public BuildingPlacement(int H, int W, int n){
            this.H = H;
            this.W = W;
            this.n = n;
            this.min = Integer.MAX_VALUE;
            this.grid = new int[this.H][this.W];
        }
        public int findMinDistance(){
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    grid[i][j] = -1;
                }
            }
            
            backtrack(0,0,n);
            return min;
        }
        private void getDistance(){
            boolean[][] visited = new boolean[H][W];
            Queue<int []> q = new LinkedList<>();
            int[][] dirs = { {-1,0}, {1,0}, {0, -1}, {0, 1}};
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(grid[i][j] == 0){
                        q.add(new int[] {i,j});
                        visited[i][j] = true;
                    }
                }
            }
            
            int distance = 0;
            while(!q.isEmpty()){
                int size = q.size();
                for(int i = 0 ; i < size; i++){
                    int[] curr = q.poll();
                    for(int[] dir : dirs){
                        int nr = curr[0] + dir[0];
                        int nc = curr[1] + dir[1];
                        if(nr >= 0 && nr < H && nc >= 0 && nc < W && visited[nr][nc] == false){
                            q.add(new int[] {nr, nc});
                            visited[nr][nc] = true;
                        }
                    }
                }
                distance++;
            }
            min = Math.min(min, distance - 1);
        }
        private void backtrack(int row, int col, int n){        
            //base
            if( n == 0 ){
                getDistance();
                return;
            }
            if( col == W ){ //idx ++ to next row
                col = 0; 
                row++;
            }
            // logic
            for(int i = row; i < H; i++){
                for(int j = col; j < W; j++){
                    // action
                    grid[i][j] = 0;
                    // recurse
                    backtrack(row, col + 1, n - 1);
                    // backtrack
                    grid[i][j] = -1 ;    
                }
                col = 0;
            }
        }
    }
    public static void main(String[] args) {
        BuildingPlacement bp = new BuildingPlacement(4,4,3);
        System.out.println(bp.findMinDistance());
    }
}