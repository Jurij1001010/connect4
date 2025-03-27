package Connect_Four;

import AI.Player;

import java.util.Arrays;


public class Game {
    public boolean randomOrder = false;//random start first or player 1 always first
    public int pos_count = -1;
    public Player[] players = new Player[2];
    public Board board = new Board();
    public Stats stats;

    int f;//first player 0 or 1 -> depends on random or not
    int s;//second player

    public Game(Stats stats){
        this.stats = stats;
        players[0] = new Player(0, true);
        players[1] = new Player(1, false);
    }


    public void startNewGame(){
        pos_count = -1;
        players[0].startNewGame();
        players[1].startNewGame();
        board = new Board();

        int[] first_second = setFirstPlayer();
        f = first_second[0];
        s = first_second[1];
        board.started = f;

        stats.players_game_played[0] = players[0].games_played;
        stats.players_game_played[1] = players[1].games_played;
    }


    public boolean move(){
        pos_count++;
        players[f].place(board, pos_count, 0);
        if(!checkWin(f, s))return false;
        players[s].place(board, pos_count, 1);
        return checkWin(f, s);

    }
    public int[] setFirstPlayer(){
        int f = 0;
        int s = 1;
        if(randomOrder) {
            f = (int) Math.round(Math.random());//first player
            s = f == 1 ? 0 : 1;//second player
        }
        return new int[]{f, s};
    }
    public boolean checkWin(int f, int  s){
        if(players[s].tried_to_place_to_filled_col || players[f].tried_to_place_to_filled_col){
            stats.tie_count++;
            return false;
        }

        if (board.isWin(0)) {
            stats.playerWin(players[f].place);

            players[1].learn(players[f].history_results, players[f].history_board, pos_count);
            return false;

        }if(board.isWin(1)){
            stats.playerWin(players[s].place);

            players[1].learn(players[s].history_results, players[s].history_board, pos_count);

            //players[0].learn(players[1].history_results, players[1].history_board, pos_count);
            //players[0].resetNetwork();
            return false;

        }
        return !board.is_field;
    }

    public void setPlayerOne(Player p){
        players[0] = p;
    }
    public void setPlayerTwo(Player p){
        players[1] = p;
    }
}
