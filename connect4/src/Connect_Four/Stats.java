package Connect_Four;


import java.util.Arrays;
import java.util.Objects;

public class Stats {
    public int game_count = 0;
    public int[] players_game_played=new int[2];
    public int[] players_win= new int[]{0, 0};
    public int[] players_win_rate = new int[]{0, 0};

    public int session_reset = 100;
    public int game_count_session = 0;
    public int[] players_win_session= new int[]{0, 0};
    public int[] players_win_rate_session = new int[]{0, 0};
    int[] session_history = new int[3];

    int tie_count = 0;
    int tie_count_session = 0;
    public int new_generation=0;

    public void print(){
        System.out.println("Games played: "+game_count);
        System.out.println("Player 1 played: "+players_game_played[0]+" won: "+players_win[0]+" times ("+ players_win_rate[0]+"%)");
        System.out.println("Player 2 played: "+players_game_played[1]+" won: "+players_win[1]+" times ("+ players_win_rate[1]+"%)");
        //System.out.println("                             p1    p2    tie      p1    p2    tie");
        System.out.println("In first "+session_reset+" games win rate: "+session_history[0]+"% - "+session_history[1]+"% - "+tie_count_session+"% now (p1-p2-tie): "+players_win_rate_session[0]+"% - "+players_win_rate_session[1]+"% - "+tie_count_session+"%");
        System.out.println("New generation on every "+new_generation+" game");

    }
    public void update(){
        game_count++;
        game_count_session++;
        players_win_rate[0] = (int)((double)players_win[0]/game_count*100);
        players_win_rate[1] = (int)((double)players_win[1]/game_count*100);
        if(game_count_session==session_reset){
            players_win_rate_session[0] = (int)((double)players_win_session[0]/game_count_session*100);
            players_win_rate_session[1] = (int)((double)players_win_session[1]/game_count_session*100);
            if(Arrays.equals(session_history, new int[3])) session_history = new int[]{players_win_rate_session[0], players_win_rate_session[1], tie_count_session};
            players_win_session  = new int[]{0, 0};
            game_count_session = 0;
            tie_count_session = tie_count;
            tie_count = 0;
        }
    }

    public void playerWin(int i){
        players_win[i]++;
        players_win_session[i]++;
    }
}
