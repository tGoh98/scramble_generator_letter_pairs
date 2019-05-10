/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scramble_generator;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author family
 */
public class Cube {
    int[] co = { 0, 0, 0, 0, 0, 0, 0, 0 }; //1 is cw, 2 is ccw, relative to U D, from solved position
    int[] cp = { 0, 1, 2, 3, 4, 5, 6, 7 }; //U F R B L D, A-W and Z
    int buffer = 0;
    String memo = "";
    String scram = "";
    String[] lScheme;
    
    public Cube() {
        letterScheme ls = new letterScheme();
        lScheme = new String[21];
        for (int i = 0; i<21; i++) {
            lScheme[i] = ls.scheme[i];
        }
    }
    
    void genScramAndMemo() {       
        scram = Cube.genScram();
        //System.out.println(scram);
        scrambleCube(scram);
        solveCube();  
        insertSpace();
        
        //System.out.println(memo);
    }
    
    void insertSpace() {
        String tempMemo = "";
        for (int i = 0; i<memo.length(); i++) {
            tempMemo += memo.charAt(i);
            if (i%2 != 0 && i != 0){
                tempMemo += " ";
            }
        }
        memo = tempMemo;
    }
    boolean solved() {
        for (int i = 0; i<8; i++){
            if (cp[i] != i) {
                return false;
            }
        }
        return true;
    }
    
    void showcp() {
        System.out.print("\ncp: ");
        for (int piece : cp) {
            System.out.print(piece + " ");
        }
        System.out.print("\nco: ");
        for (int piece : co) {
            System.out.print(piece + " ");
        }
    }
    void solveCube() {
        //while (!solved()) {
        while (cp[0] != 0) {
            //showcp();        
            buffer = cp[0];
            /*if (buffer == 0) {
                int i = 1;
                boolean done = false;
                while (!done) {
                    if (cp[i]!= i) {
                        //System.out.println("i: " + i);
                        addToMemo(co[0], i);
                        swapThese(i);
                        done = true;
                    }
                    i++;
                }
            } else {*/
                addToMemo(co[0], cp[0]);
                swapThese(buffer);
            //}
        }
    }
    
    void addToMemo(int curOrient, int tarPerm) {
        //int curOrient = co[0];
        //int tarPerm = cp[0];
        
        //System.out.println("curOrient: " + curOrient);
        //System.out.println("tarPerm: " + tarPerm);
        
        //MY SCHEME
        switch (tarPerm) { 
            case 0:
                break;
            case 1:
                if (curOrient == 0) {
                    //UBR
                    memo += lScheme[0];
                } else if (curOrient == 1) {
                    //RUB
                    memo += lScheme[1];
                } else {
                    //BUR
                    memo += lScheme[2];
                }
                break;
            case 2:
                if (curOrient == 0) {
                    //UFR
                    memo += lScheme[3];
                } else if (curOrient == 1) {
                    //FUR
                    memo += lScheme[4];
                } else {
                    //BUR
                    memo += lScheme[5];
                }
                break;
            case 3:
                if (curOrient == 0) {
                    //UFR
                    memo += lScheme[6];
                } else if (curOrient == 1) {
                    //LUF
                    memo += lScheme[7];
                } else {
                    //FUL
                    memo += lScheme[8];
                }
                break;
            case 4:
                if (curOrient == 0) {
                    //DFL
                    memo += lScheme[9];
                } else if (curOrient == 1) {
                    //FDL
                    memo += lScheme[10];
                } else {
                    //LFD
                    memo += lScheme[11];
                }
                break;
            case 5:
                if (curOrient == 0) {
                    //DFR
                    memo += lScheme[12];
                } else if (curOrient == 1) {
                    //RFD
                    memo += lScheme[13];
                } else {
                    //FRD
                    memo += lScheme[14];
                }
                break;
            case 6:
                if (curOrient == 0) {
                    //DBR
                    memo += lScheme[15];
                } else if (curOrient == 1) {
                    //BDR
                    memo += lScheme[16];
                } else {
                    //RDB
                    memo += lScheme[17];
                }
                break;
            case 7:
                if (curOrient == 0) {
                    //DBL
                    memo += lScheme[18];
                } else if (curOrient == 1) {
                    //LDB
                    memo += lScheme[19];
                } else {
                    //BLD
                    memo += lScheme[20];
                }
                break;
            default:
                System.out.println("Error in addToMemo(), tarPerm: " + tarPerm);
                break;
        }
        /* ISHAAN SCHEME
        switch (tarPerm) {
            case 0:
                break;
            case 1:
                if (curOrient == 0) {
                    //UBR
                    memo += "B";
                } else if (curOrient == 1) {
                    //RUB
                    memo += "J";
                } else {
                    //BUR
                    memo += "M";
                }
                break;
            case 2:
                if (curOrient == 0) {
                    //UFR
                    memo += "C";
                } else if (curOrient == 1) {
                    //FUR
                    memo += "F";
                } else {
                    //RUF
                    memo += "I";
                }
                break;
            case 3:
                if (curOrient == 0) {
                    //UFL
                    memo += "D";
                } else if (curOrient == 1) {
                    //LUF
                    memo += "R";
                } else {
                    //FUL
                    memo += "E";
                }
                break;
            case 4:
                if (curOrient == 0) {
                    //DFL
                    memo += "U";
                } else if (curOrient == 1) {
                    //FDL
                    memo += "H";
                } else {
                    //LFD
                    memo += "S";
                }
                break;
            case 5:
                if (curOrient == 0) {
                    //DFR
                    memo += "V";
                } else if (curOrient == 1) {
                    //RFD
                    memo += "L";
                } else {
                    //FRD
                    memo += "G";
                }
                break;
            case 6:
                if (curOrient == 0) {
                    //DBR
                    memo += "W";
                } else if (curOrient == 1) {
                    //BDR
                    memo += "P";
                } else {
                    //RDB
                    memo += "K";
                }
                break;
            case 7:
                if (curOrient == 0) {
                    //DBL
                    memo += "Z";
                } else if (curOrient == 1) {
                    //LDB
                    memo += "T";
                } else {
                    //BLD
                    memo += "O";
                }
                break;
            default:
                System.out.println("Error in addToMemo(), tarPerm: " + tarPerm);
                break;
        }
        */
        //System.out.println("memo: " + memo);
    }
    
    void swapThese(int target) {
             
        cp[0] = cp[target];
        cp[target] = buffer;
        
        switch (co[0]) {
            case 0:
                //System.out.println("in case 0");
                co[0] = co[target];
                break;
            case 1:
                //System.out.println("in case 1");
                if (co[target] == 0) {
                    co[0] = 1;
                } else if (co[target] == 1) {
                    co[0] = 2;
                } else { //1 and 2
                    co[0] = 0;
                }
                break;
            case 2:
                //System.out.println("in case 2");
                if (co[target] == 0) {
                    co[0] = 2;
                } else if (co[target] == 1) {
                    co[0] = 0;
                } else { //2 and 2
                    co[0] = 1;
                }
                break;
        }
        co[target] = 0;
    }
    void reset() {
        for (int i = 0; i<8; i++){
            cp[i] = 0;
            co[i] = 0;
        }
        memo = "";
        scram = "";
    }
    
    void dispState() {
        System.out.println("CP:       CO: ");
        for (int i = 0; i<8; i++){
            System.out.print(cp[i] + "          " + co[i] + "\n");
        }
    }
    
    void scrambleCube(String scramble) {
        String[] sArr = scramble.split(" ");
        for (String move : sArr){
            switch (move) {
                    case "U":
                        U();
                        break;
                    case "U\'":
                        U();
                        U();
                        U();
                        break;
                    case "U2":
                        U();
                        U();
                        break;
                    case "F":
                        F();
                        break;
                    case "F\'":
                        F();
                        F();
                        F();
                        break;
                    case "F2":
                        F();
                        F();
                        break;
                    case "L":
                        L();
                        break;
                    case "L\'":
                        L();
                        L();
                        L();
                        break;
                    case "L2":
                        L();
                        L();
                        break;
                    case "B":
                        B();
                        break;
                    case "B\'":
                        B();
                        B();
                        B();
                        break;
                    case "B2":
                        B();
                        B();
                        break;
                    case "R":	
                        R();	
                        break;
                    case "R\'":	
                        R();
                        R();
                        R();
                        break;
                    case "R2":	
                        R();
                        R();
                        break;
                    case "D":	
                        D();	
                        break;
                    case "D\'":	
                        D();
                        D();
                        D();
                        break;
                    case "D2":	
                        D();
                        D();
                        break;
                    default:
                        System.out.println("error in scrambleCube(), move: " + move);
                        break;
            }
        }
    }  
    
    void twistcw(int id) {
        if (co[id] == 0) {
            co[id] = 1;
        } else if (co[id] == 1) {
            co[id] = 2;
        } else {
            co[id] = 0;
        }
    }
    void twistccw(int id) {
        if (co[id] == 0) {
            co[id] = 2;
        } else if (co[id] == 1) {
            co[id] = 0;
        } else {
            co[id] = 1;
        }
    }
    int temp = 0;
    void U() {
        temp = cp[0];
        cp[0] = cp[3];
        cp[3] = cp[2];
        cp[2] = cp[1];
        cp[1] = temp;
        
        temp = co[0];
        co[0] = co[3];
        co[3] = co[2];
        co[2] = co[1];
        co[1] = temp;

        //does not affect co
    }
    void F() {
        temp = cp[2];
        cp[2] = cp[3];
        cp[3] = cp[4];
        cp[4] = cp[5];
        cp[5] = temp;
        
        temp = co[2];
        co[2] = co[3];
        co[3] = co[4];
        co[4] = co[5];
        co[5] = temp;
        
        twistcw(2);
        twistcw(4);
        twistccw(3);
        twistccw(5);
    }
    void L() {     
        temp = cp[0];
        cp[0] = cp[7];
        cp[7] = cp[4];
        cp[4] = cp[3];
        cp[3] = temp;
        
        temp = co[0];
        co[0] = co[7];
        co[7] = co[4];
        co[4] = co[3];
        co[3] = temp;

        twistcw(3);
        twistcw(7);
        twistccw(0);
        twistccw(4);
    }
    void B() {
        temp = cp[0];
        cp[0] = cp[1];
        cp[1] = cp[6];
        cp[6] = cp[7];
        cp[7] = temp;
        
        temp = co[0];
        co[0] = co[1];
        co[1] = co[6];
        co[6] = co[7];
        co[7] = temp;

        twistcw(0);
        twistcw(6);
        twistccw(1);
        twistccw(7);
    }
    void R() {
        temp = cp[1];
        cp[1] = cp[2];
        cp[2] = cp[5];
        cp[5] = cp[6];
        cp[6] = temp;
        
        temp = co[1];
        co[1] = co[2];
        co[2] = co[5];
        co[5] = co[6];
        co[6] = temp;

        twistcw(1);
        twistcw(5);
        twistccw(2);
        twistccw(6);
    }
    void D() {
        temp = cp[4];
        cp[4] = cp[7];
        cp[7] = cp[6];
        cp[6] = cp[5];
        cp[5] = temp;
        
        temp = co[4];
        co[4] = co[7];
        co[7] = co[6];
        co[6] = co[5];
        co[5] = temp;

        //does not affect co
    }

    public static String genScram(){
        String scramble = "";
        int min = 0;
        int max = 5;
        int randNum = 0; 
        int prev2Num = ThreadLocalRandom.current().nextInt(min, max + 1);
	int prevNum = 0;
	String move;
        int parity = 0;

	for (int i = 0; i < 20; i++) {
		if (i==0) {
                    randNum = prev2Num;
                } else if (i==1) {
                    do {
                            prevNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                    } while (prevNum == prev2Num);
                    randNum = prevNum;
                } else {
                    prev2Num = prevNum;
                    prevNum = randNum;
                    max = 5;
                    do {
                            randNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                    } while (prevNum == randNum || prev2Num == randNum);
                }

		move = "";
		if (randNum == 0) {
			move = "U";
		}
		else if (randNum == 1) {
			move = "F";
		}
		else if (randNum == 2) {
			move = "L";
		}
		else if (randNum == 3) {
			move = "B";
		}
		else if (randNum == 4) {
			move = "R";
		}
		else {
			move = "D";
		}
                    
                max = 2;
                if (i!=19) {
                    int randNum2 = ThreadLocalRandom.current().nextInt(min, max + 1);
                    if (randNum2 == 0) {
                        move += "2";
                    }
                    else {
                            int randNum3 = ThreadLocalRandom.current().nextInt(min, max + 1);
                            if (randNum3 == 0) {
                                    move += "\'";
                                    parity -= 1;
                            } else {
                                parity += 1;
                            }
                    }
                }
                if (i == 19) {
                    if (parity%2==0) { //is no parity before last move
                        //System.out.println("Scramble before: " + scramble);
                        String[] moveArr = move.split("");
                        if (moveArr.length == 2){
                            if (moveArr[1] == "2") {
                                move = moveArr[0];
                                //System.out.println("subtracting 2");
                            } else { //is prime, need to change to 2
                                //moveArr[1] = "2";
                                //System.out.println("changing to 2");
                                move = moveArr[0];
                                move += "2";
                            }
                        } else {
                            //System.out.println("adding 2");
                            move += "2";
                        }
                    }
                }
		move += " ";
		scramble += move;
	}

	return scramble;
    }
}
