package AI;

import Connect_Four.Board;
import Neural_Network.Network;


public class Player {
    public Network n = new Network(new int[] {42, 42, 42, 21, 7});
    public double[][] history_results;//all decisions where to put coin
    public double[][] history_board;//list of all board info's (positions os coins)
    public int place;
    public int games_played = 0;
    public boolean tried_to_place_to_filled_col = false;
    public boolean reset;//reset player after every game

    public Player(int place, boolean reset){
        this.place = place;
        this.reset = reset;
    }

    public void startNewGame(){
        games_played++;
        tried_to_place_to_filled_col = false;
        //saves positions and moves for later learning
        history_results = new double[22][7];
        history_board = new double[22][42];
        if(reset) {resetNetwork();games_played=0;}
    }
    public void place(Board board, int pos_count, int m) {

        //System.out.println(pos_count);

        history_board[pos_count] = board.makeInput(m).clone();//makes input for neural network to input and saves it



        history_results[pos_count] = n.feedNetwork(board.makeInput(m)).clone();//gets the network output
        int col_to_place_0 = biggestValue(history_results[pos_count]);//position of placement

        //System.out.println("player "+place+": "+ Arrays.toString(history_board[pos_count]));
        //System.out.println("           "+Arrays.toString(history_results[pos_count])+" "+col_to_place_0);
        /*
        if(placed_to_filled_col>3){
            col_to_place_0= (int) (Math.random() * 7);
        }*/
        board.makeMove(col_to_place_0);

        //if player cant place in col the game ends and learns he cant place to that col

        if (board.cant_place) {
            //if(place==1) System.out.println("c");
            /*if (!reset) {
                history_results[pos_count][col_to_place_0] = 0.0;
                n.learn(history_board[pos_count], history_results[pos_count]);//learns that cant place to filled column
                //System.out.println(Arrays.toString());
            }

             */

            tried_to_place_to_filled_col = true;


        }
    }

    public void learn(double[][] history_results, double[][] history_board, int pos_count){
        for (int i = 0; i <= pos_count; i++) {
            double[] output = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            output[biggestValue(history_results[i])] = 1;
            n.learnNetwork(history_board[i], output);
        }
    }

    public void resetNetwork(){
        n.resetLayers();
    }
    public void setNetwork(Network n){
        this.n = n;
    }

    /*
    //position where to place a coin
    //
    //
     */
    public static int biggestValue (double[] values){
        double max_ = values[0];
        int j = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max_) {
                max_ = values[i];
                j = i;
            }
        }
        return j;
    }

}
