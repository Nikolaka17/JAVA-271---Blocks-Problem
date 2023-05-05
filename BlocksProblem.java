import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * A class that represents a block world for the blocks problem
 */
@SuppressWarnings("unchecked")
public class BlocksProblem {
    private int size;
    private int[] pos;
    private Stack<Integer>[] piles;
    
    /**
     * Creates a block world of blocks [0,n)
     * @param n The number of blocks in the block world
     */
    public BlocksProblem(int n){
        size = n;
        pos = new int[n];
        piles = new Stack[n];
        for(int i = 0; i < n; i++){
            pos[i] = i;
            piles[i] = new Stack<Integer>();
            piles[i].add(i);
        }
    }
    
    /**
     * Removes all elements from both a and b and puts a onto b
     * @param a The block to move
     * @param b The block to add to
     * @throws IllegalArgumentException Thrown if a or b aren't valid blocks or a and b are on the same pile
     */
    public void moveOnto(int a, int b)throws IllegalArgumentException{
        if(isInvalid(a, b)){
            throw new IllegalArgumentException();
        }
        int temp = piles[pos[a]].pop();
        while(temp != a){
            piles[temp].push(temp);
            pos[temp] = temp;
            temp = piles[pos[a]].pop();
        }
        pos[a] = pos[b];
        while(!piles[pos[b]].peek().equals(b)){
            temp = piles[pos[b]].pop();
            piles[temp].push(temp);
            pos[temp] = temp;
        }
        piles[pos[b]].push(a);
    }
    
    /**
     * Removes all elements from a and places a onto the stack containing b
     * @param a The block to move
     * @param b The block to stack onto
     * @throws IllegalArgumentException Thrown if a or b aren't valid blocks or a and b are on the same pile
     */
    public void moveOver(int a, int b)throws IllegalArgumentException{
        if(isInvalid(a, b)){
            throw new IllegalArgumentException();
        }
        int temp = piles[pos[a]].pop();
        while(temp != a){
            piles[temp].push(temp);
            pos[temp] = temp;
            temp = piles[pos[a]].pop();
        }
        pos[a] = pos[b];
        piles[pos[b]].push(a);
    }
    
    /**
     * Removes all elements from b and places a and all elements on top of it onto b
     * @param a The stack to move
     * @param b The block to add to
     * @throws IllegalArgumentException Thrown if a or b aren't valid blocks or a and b are on the same pile
     */
    public void pileOnto(int a, int b)throws IllegalArgumentException{
        if(isInvalid(a, b)){
            throw new IllegalArgumentException();
        }
        Stack<Integer> temp = new Stack<Integer>();
        temp.push(piles[pos[a]].pop());
        while(!temp.peek().equals(a)){
            pos[temp.peek()] = pos[b];
            temp.push(piles[pos[a]].pop());
        }
        pos[a] = pos[b];
        while(!piles[pos[b]].peek().equals(b)){
            int temp2 = piles[pos[b]].pop();
            piles[temp2].push(temp2);
            pos[temp2] = temp2;
        }
        while(!temp.empty()){
            piles[pos[b]].push(temp.pop());
        }
    }
    
    /**
     * Places a and all elements on top of the stack of b
     * @param a The stack to move
     * @param b The block to stack onto
     * @throws IllegalArgumentException Thrown if a or b aren't valid blocks or a and b are on the same pile
     */
    public void pileOver(int a, int b)throws IllegalArgumentException{
        if(isInvalid(a, b)){
            throw new IllegalArgumentException();
        }
        Stack<Integer> temp = new Stack<Integer>();
        temp.push(piles[pos[a]].pop());
        while(!temp.peek().equals(a)){
            pos[temp.peek()] = pos[b];
            temp.push(piles[pos[a]].pop());
        }
        pos[a] = pos[b];
        while(!temp.empty()){
            piles[pos[b]].push(temp.pop());
        }
    }
    
    /**
     * Gets the size of the block world
     * @return The size of the block world
     */
    public int getSize(){
        return size;
    }

    /**
     * Checks if the two blocks are valid for moving
     * @param a The first block
     * @param b The second block
     * @return A boolean representing if the two blocks are valid to use in a command
     */
    public boolean isInvalid(int a, int b){
        return a < 0 || b < 0 || a >= size || b >= size || a == b || pos[a] == pos[b];
    }
    
    /**
     * Gives an array representation of the block world
     * @return An 2D array representing the current state of the block world
     */
    public int[][] toArray(){
        int[][] result = new int[size][size];
        for(int i = 0; i < size; i++){
            int j = 0;
            while(!piles[i].empty()){
                result[i][j] = piles[i].pop().intValue();
                j++;
            }
        }
        return result;
    }
    
    /**
     * Converts the block world into a string for printing
     * @return The string representation of the block world following n: [blocks] \n for each stack
     */
    @Override
    public String toString(){
        StringBuilder concation = new StringBuilder();
        for(int i = 0; i < size; i++){
            ArrayList<Integer> reverse = new ArrayList<Integer>();
            concation = concation.append(i + ":");
            while(!piles[i].empty()){
                reverse.add(piles[i].pop());
            }
            for(int j = reverse.size()-1; j >= 0; j--){
                concation = concation.append(" ").append(reverse.get(j).toString());
            }
            concation = concation.append("\n");
        }
        return concation.toString();
    }
    
    /**
     * Checks if two block worlds are in the same layout
     * @param o The block world to compare to
     * @return A boolean representing if each block world has the same layout
     */
    @Override
    public boolean equals(Object o){
        if(((BlocksProblem)o).getSize() == size){
            return false;
        }
        int[][] self = toArray();
        int[][] other = ((BlocksProblem)o).toArray();
        for(int i = 0; i < size; i++){
            if(!Arrays.equals(self[i], other[i])){
                return false;
            }
        }
        return true;
    }
}
