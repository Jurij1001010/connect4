import AI.Learn;
import Connect_Four.Stats;
import User_Input.Input;

public class Main {
    public static void main(String[] args){

        Stats stats = new Stats();
        Learn learn = new Learn(stats);
        Input input = new Input(learn);

        input.start();
        learn.start();

        while (input.live){

            if(input.command == 1) {
                stats.print();
                learn.board.printBoard();
                //System.out.println(learn.board_print.isWin(1));
                System.out.println("----------------");
                input.command =0;
            }
            if(input.command ==2){
                learn.board.printBoard();
                System.out.println("----------");
                input.command =0;
            }

        }
        stats.print();

    }


}