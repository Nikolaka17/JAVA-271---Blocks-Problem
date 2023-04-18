import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;

public class BlocksProblem {
    private ArrayList<String> commands = new ArrayList<String>();
    private int size;
    private int[] pos;
    private Stack<Integer>[] piles;
    
    public BlocksProblem(int n){
        size = n;
        pos = new int[n];
        for(int i = 0; i < n; i++){
            pos[i] = -1;
        }
        piles = new Stack[n];
    }
    
    public void moveOnto(int a, int b){
        
    }
    
    public void moveOver(int a, int b){
        
    }
    
    public void pileOnto(int a, int b){
        
    }
    
    public void pileOver(int a, int b){
        
    }
    
    public int getSize(){
        return size;
    }
    
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
    
    @Override
    public String toString(){
        StringBuilder concation = new StringBuilder();
        for(int i = 0; i < size; i++){
            concation = concation.append(i + ": ");
            while(!piles[i].empty()){
                concation = concation.append(piles[i].pop().toString()).append(" ");
            }
            concation = concation.append("\b\n");
        }
        return concation.append("\b").toString();
    }
    
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
