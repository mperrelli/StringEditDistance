package distance;

import java.util.Scanner;

public class Distance {
    static String s1, s2;
    static int cache[][];
    static int changes;
    
	public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        s1 = s.nextLine();
        s2 = s.nextLine();
        
        cache = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++)
            for (int j = 0; j < s2.length(); j++)
                cache[i][j] = -1;
        
        opspath(s1, s2);
        
        String temp = s1;
        s1 = s2;
        s2 = temp;
        
        cache = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++)
            for (int j = 0; j < s2.length(); j++)
                cache[i][j] = -1;
        
        opspath(s1, s2);
        
	}
    
	static int edist(int i, int j) {
        int n = s1.length();
        int m = s2.length();
        
        if (i >= n) {
            return m - j;
        } else if (j >= m){
            return n - i;
        } else if (s1.charAt(i) == s2.charAt(j)) {
            if (cache[i][j] == -1)
                cache[i][j] = min(edist(i+1, j+1),
                                  1 + edist(i+1, j),
                                  1 + edist(i, j+1));
            return cache[i][j];
        } else {
            if (cache[i][j] == -1)
                cache[i][j] = min(1 + edist(i+1, j+1),
                                  1 + edist(i+1, j),
                                  1 + edist(i, j+1));
            return cache[i][j];
        }
    }
	
    private static void opspath(String s1, String s2){
    	String temp = "";
    	int i = 0;
    	int j = 0;
    	while(i < s1.length() || j < s2.length()){
    		int smallest = edist(i,j);
    		if(1 + edist(i+1,j) == smallest){
    			temp += "_";
    			i++;
    		}
    		else if(1 + edist(i, j+1) == smallest){
    			temp += "_";
    			j++;
    		}
    		else{
    			temp += s1.charAt(i);
    			i++; j++;
    		}
    		
    	}
    	
    	System.out.println(temp);
    	
    }
       
    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b,c));
    }

}
