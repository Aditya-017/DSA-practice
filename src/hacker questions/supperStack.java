import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
      static void superStack(String[] operations) {
        // System.out.println(Arrays.toString(operations));
        List<Long> st = new ArrayList<>();
        List<Long> ar = new ArrayList<>();
        for(String op:operations){
            String[] o = op.trim().split(" ");

            if(o[0].equals("push")){
                long e = Long.parseLong(o[1]);
                st.add(e);
                ar.add(0l);
                System.out.println(e);
            }else if(o[0].equals("pop")){
                int stSize = st.size();
                if(st.size()==0){
                    System.out.println("EMPTY");
                    continue;
                }
                long ans = st.remove(stSize-1);
                long sum = ar.remove(ar.size()-1);
                if(ar.size()==0){
                    System.out.println("EMPTY");
                    continue;
                }
                long last = ar.get(ar.size()-1);
                ar.set(ar.size()-1,last+sum);
                
                System.out.println(ar.get(ar.size()-1)+st.get(st.size()-1));
                
            }else if(o[0].equals("inc")){
                int i=Integer.parseInt(o[1]); 
                long v=Long.parseLong(o[2]);
                ar.set(i-1,ar.get(i-1)+v);
                
                System.out.println(ar.get(ar.size()-1)+st.get(st.size()-1));
            }
        }
    }
    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);
        int operations_size = 0;
        operations_size = Integer.parseInt(in.nextLine().trim());

        String[] operations = new String[operations_size];
        for(int i = 0; i < operations_size; i++) {
            String operations_item;
            try {
                operations_item = in.nextLine();
            } catch (Exception e) {
                operations_item = null;
            }
            operations[i] = operations_item;
        }

        superStack(operations);
        
    }
}
