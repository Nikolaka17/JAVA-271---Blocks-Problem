import java.util.Scanner;

public class BlocksDriver {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int size = stdin.nextInt();
        BlocksProblem problem = new BlocksProblem(size);
        String command = stdin.nextLine().toLowerCase();
        while(!command.equals("quit")){
            try{
                String[] keywords = command.split(" ");
                if(keywords[0].equals("move")){
                    if(keywords[2].equals("onto")){
                        problem.moveOnto(Integer.parseInt(keywords[1]), Integer.parseInt(keywords[3]));
                    }else if(keywords[2].equals("over")){
                        problem.moveOver(Integer.parseInt(keywords[1]), Integer.parseInt(keywords[3]));
                    }
                }else if(keywords[0].equals("pile")){
                    if(keywords[2].equals("onto")){
                        problem.pileOnto(Integer.parseInt(keywords[1]), Integer.parseInt(keywords[3]));
                    }else if(keywords[2].equals("over")){
                        problem.pileOver(Integer.parseInt(keywords[1]), Integer.parseInt(keywords[3]));
                    }
                }
            }catch(Exception e){
                
            }
            command = stdin.nextLine().toLowerCase();
        }
        System.out.print(problem);
        stdin.close();
        System.exit(0);
    }
}
