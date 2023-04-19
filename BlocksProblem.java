import java.util.Stack;
import java.util.Arrays;

public class BlocksProblem {
    private int size;
    private int[] pos;
    private Stack<Integer>[] piles;
    
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
    
    public int getSize(){
        return size;
    }

    public boolean isInvalid(int a, int b){
        return a < 0 || b < 0 || a >= size || b >= size || a == b || pos[a] == pos[b];
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
