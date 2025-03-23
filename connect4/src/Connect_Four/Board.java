package Connect_Four;

public class Board {

    public int[] height = new int[]{0, 7, 14, 21, 28, 35, 42};
    public int[] height_limit = new int[]{6, 13, 20, 27, 34, 41, 48};
    public int[][] A = {{5, 12, 19, 26, 33, 40, 47},
                        {4, 11, 18, 25, 32, 39, 46},
                        {3, 10, 17, 24, 31, 38, 45},
                        {2,  9, 16, 23, 30, 37, 44},
                        {1,  8, 15, 22, 29, 36, 43},
                        {0,  7, 14, 21, 28, 35, 42}};
    public int counter = 0;
    public boolean is_field = false;

    public long[] bitboard = new long[] {0, 0};
    public boolean cant_place = false;
    public int started;

    public void makeMove(int col) {
        //System.out.println(height[col]);
        if(height[col]==height_limit[col]){
            //System.out.println("cant place");
            cant_place = true;
            return;
        }
        long move = 1L << height[col]++; // (1)
        bitboard[counter & 1] ^= move;  // (2)
        counter++;
        if (counter == 42) is_field = true;
            //System.out.println("isfield");}
        cant_place = false;
    }
    public void B(){
        long l = bitboard[0];
        for(int i = 0; i < Long.numberOfLeadingZeros((long)l); i++) {
            System.out.print('0');
        }
        System.out.println(Long.toBinaryString((long)l));
    }
    public void printBoard(){
        for(int[] a_ : A){
            for(int a : a_) {
                long bitboard1;
                long bitboard2;
                bitboard1 = bitboard[0];
                bitboard2 = bitboard[1];
                if (((bitboard1 >> a) & 1) == 1) {
                    System.out.print((started==0?"X":"0"));
                }
                else if (((bitboard2 >> a) & 1) == 1) {
                    System.out.print((started==1?"X":"0"));
                } else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println("Started: "+(started==0?"X (not AI)":"0 (AI)"));
        System.out.print("Player won: ");
        if(isWin(started)) System.out.println("X");
        else if(isWin(started==0?1:0)) System.out.println("0");
        else System.out.println("no one");
    }
    public double[] makeInput(int p){
        //         ____
        //me: 1    \  /
        //you: 2    \/    VEDNO
        //          0
        double[] output = new double[42];
        int i = 0;
        for(int[] a_ : A){
            for(int a : a_) {
                long bitboard1;
                long bitboard2;
                if (p == 0){
                    bitboard1 = bitboard[0];
                    bitboard2 = bitboard[1];
                }else{
                    bitboard1 = bitboard[1];
                    bitboard2 = bitboard[0];
                }
                if (((bitboard1 >> a) & 1) == 1) {
                    output[i] = 1;
                }
                else if (((bitboard2 >> a) & 1) == 1) {
                    output[i] = 2;
                } else {
                    output[i] = 0;

                }
                i++;

            }

        }
        return output;
    }
    public boolean isWin(int p) {

        int[] directions = {1, 7, 6, 8};
        long bb;
        for(int direction : directions) {
            bb = bitboard[p] & (bitboard[p] >> direction);
            if ((bb & (bb >> (2 * direction))) != 0) return true;
        }
        return false;
    }

}
