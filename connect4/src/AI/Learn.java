package AI;

import Connect_Four.Board;
import Connect_Four.Game;
import Connect_Four.Stats;
import Neural_Network.Network;

import java.util.Arrays;

public class Learn extends Thread{
    public volatile boolean learn = false;
    public volatile boolean live = true;

    public Stats stats;
    public Board board = new Board();// board for print
    public Game game;
    public Network old_n;
    int generation = 0;
    int new_generation = -1;//on how many games not learning player is updated

    public Learn(Stats stats){
        this.stats = stats;
        this.stats.new_generation = new_generation;
        game = new Game(stats);
    }


    public void run() {
        while (live) {
            if (!learn) continue;


            game.startNewGame();


            boolean play = true;
            while (play) {
                play = game.move();
            }

            /*
            System.out.println(Arrays.toString(game.players[1].n.input_layer.getNeuron_values_a()));
            System.out.println(Arrays.toString(game.players[1].n.output_layer.getNeuron_values()));
            System.out.println(Arrays.toString(game.players[1].n.output_layer.getNeuron_values_a()));
            System.out.println();

             */


            if(new_generation!= -1 && stats.game_count%new_generation ==0&&old_n!=null){
                generation++;
                game.players[0].n.setLayers(old_n);
                old_n.setLayers(game.players[1].n);
                System.out.println("new gen");
            }

            stats.update();
            //if((stats.game_count+1)%100 == 0) learn =false;
            board = game.board;
        }
    }




}
