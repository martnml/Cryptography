

public class AES_crypto {


     private  static class AES{

         // function Print Matrix
         public static void Matrix_Printer(String [][] a){

                 for(int i=0;i<4;i++) {
                     for (int j = 0; j < 4; j++) {
                         System.out.print(" "+ a[i][j].toUpperCase());
                     }
                     System.out.println();
                 }
             System.out.println();
             }

             // Multipication (AND) method

         public static String StringToBinary(String s) {
             String c = "";
             for (int i = 0; i < s.length(); i++)
                 c = String.valueOf(c) + Integer.toBinaryString(s.charAt(i));
             return c;
         }

         public static String And(String a, String b)
         {
             String s = "";
             for(int i=0;i<a.length();i++)
                 if((a.charAt(i)=='1')&&(b.charAt(i)=='1')) s+="1";
                 else s+=0;

             return s;
         }

         public String hextoBin(String input)
         {
             int n = input.length() * 4;
             input = Long.toBinaryString( Long.parseUnsignedLong(input, 16));
             while (input.length() < n)
                 input = "0" + input;

             return input;
         }

             // Xor method
         public static String xor(String a, String b)
         {
             // hexadecimal to decimal(base 10)
             long t_a = Long.parseUnsignedLong(a, 16);
             // hexadecimal to decimal(base 10)
             long t_b = Long.parseUnsignedLong(b, 16);
             // xor
             t_a = t_a ^ t_b;
             // decimal to hexadecimal
             a = Long.toHexString(t_a);
             // prepend 0's to maintain length
             while (a.length() < b.length())
                 a = "0" + a;
             return a;
         }


         public static String binToHex(String input)
         {
             int n = (int)input.length() / 4;
             input = Long.toHexString(
                     Long.parseUnsignedLong(input, 2));
             while (input.length() < n)
                 input = "0" + input;
             return input;
         }
     }


     //--------------------------Main Methode---------------------
    public static void main(String[] args) {
         String [] sbox = { "00","04","12","14","12","04","12","00","0C","00","13","11","08","08","23","19","19"};


        AES cipher = new AES();

//--------- Converting the vector to matrix

       String [][]sbox2 = new String[4][4];
       int k =0;
        for(int j=0; j < 4 ; j++ )
           for(int i=0; i < 4 ; i++,k++)
              sbox2[i][j]=sbox[k];

        System.out.println("the Original matrix :");
         cipher.Matrix_Printer(sbox2);


//--------------- Round Key  ---------------------------------------------------------------
     String [][]Rkey={ {"57","40","2C","A1"},
                      {"1B","38","52","CB"},
                      {"FF","59","B2","99"},
                      {"62","D1","E2","A2"}};

        for ( int i = 0 ; i<4 ; i++)
            for( int j=0 ; j < 4 ; j++ )
               //hexadecimal 1st matrix & Roundkey
                sbox2[i][j]=cipher.xor(sbox2[i][j],Rkey[i][j]);


        System.out.println(" Round key matrix : ");
        cipher.Matrix_Printer(sbox2);

 //---------------Process subByte  ----------------------------------------------------------------------

//sub byte matrix
   String[][] SubByte = {{"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
                        {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
                        {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
                        {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
                        {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
                        {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
                        {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
                        {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
                        {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
                        {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
                        {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
                        {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
                        {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
                        {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
                        {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
                        {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}};


        int i1=0, i2=0;
        for ( int i = 0 ; i< 4 ; i++)
            for( int j=0 ; j < 4 ; j++ ) {
                String hex1 =String.valueOf(sbox2[i][j].charAt(0));
                String hex2 =String.valueOf(sbox2[i][j].charAt(1));
                i1=Integer.parseInt(hex1,16);
                i2=Integer.parseInt(hex2,16);

                sbox2[i][j]=SubByte[i1][i2];
            }
        System.out.println("SUB BYTE");
        cipher.Matrix_Printer(sbox2);

//-----------------------   Reverse ----------------------------------------------------------------
//Reverse To right
        String [] RevToRight = new String[4];
        String [][]sbox3 = new String[4][4];

        for( int i=0 ;  i < 4 ; i++)
            sbox3[0][i]=sbox2[0][i];


         // starting the rersing the the matrix
        for ( int i = 1 ; i < 4; i++){

            // filling the reversed line to an array
             for( int j=0 ; j < i ; j++ )
               RevToRight[j]=sbox2[i][j];

            // reverse the rest of the line
             for( int j=0  ; j+i < 4; j++)
                sbox3[i][j]=sbox2[i][j+i];


            // refilling the rest of the line with reversed variables
             int j;
             for(k=0,j=3; k<3 && j>3-i; k++,j--)
                sbox3[i][j]=RevToRight[k];

        }

        System.out.println("Reversed matrix ");
        cipher.Matrix_Printer(sbox3);


        //Xor
        String [][]sbox4 = new String[4][4];
        String str , str2 ="",str3="", b2 ="";

//--------------------------- Mix Columns -------------------------------------------------------------
        String [][]MixColumn={ {"02","03","01","01"},
                               {"01","02","03","01"},
                               {"01","01","02","03"},
                               {"03","01","01","02"}};

        for ( int i = 0 ; i< 4 ; i++)
            for( int j=0 ; j < 4 ; j++ ) {

              //----- case of value from matrix is 01
               if( MixColumn[i][j].equals("01") )  sbox4[i][j] = sbox3[i][j];

              //------ case of value from matrix is 02
              else if(MixColumn[i][j].equals("02")) {
                  // Value from hexadecimal to Binary
                  str = cipher.hextoBin(sbox3[i][j]);

                  //Reverse the first  to last position
                  if(str.charAt(0)=='0') {
                      str = str.replaceFirst("0", "") + "0";
                      //the AND operation with 02
                      str = cipher.And(str, "00000010"); }

                  else if (str.charAt(0)=='1'){
                      str = str.replaceFirst("1", "") + "0";
                      //the XOR operation with B2
                      str = cipher.xor(str, "10110010"); }

                  //  Binary to String
                  sbox4[i][j]=cipher.binToHex(str);  }

           //------ case of value from matrix is 03
           else {
                  // VAlue from hexadecimal to Binary
                  str = cipher.hextoBin(sbox3[i][j]);

                  //Reverse the first  to last position
                  if(str.charAt(0)=='0') {
                      str = str.replaceFirst("0", "") + "0";
                      str2 =str;
                      //the AND operation with 02
                      str = cipher.And(str, "00000010"); }

                  else if (str.charAt(0)=='1'){
                      str = str.replaceFirst("1", "") + "0";
                      str2 =str;
                      //the XOR operation with B2
                      str = cipher.xor(str, "10110010"); }

                  //the old variable And function with 01
                  str3=cipher.And(str2,"00000001");

                  //the And operation with the
                  str=cipher.And(str,str3);

                  //  Binary to String
                  sbox4[i][j]=cipher.binToHex(str);

                   }


            }
        System.out.println("Multiplication Process");
        cipher.Matrix_Printer(sbox4);


    }
}
