package com.interview.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.careercup.com/question?id=4859932243394560
 */
public class CombinationWithReptition {

    public void combination(char input[],int count[]){
        int len =0;
        for(int i=0; i < count.length; i++){
            len += count[i];
        }
        char result[] = new char[len];
        combination(input,count,0,0,result,0);
    }
    
    private void combination(char input[],int count[],int pos, int countPos, char result[],int len){
        
        if(pos == input.length){
            return;
        }
        print(result,len);
        for(int i=pos; i < input.length; i++){
            if(countPos < count[i]){
                result[len] = input[i];
                combination(input,count,i,countPos+1,result,len+1);
            }
            countPos = 0;
        }
    }
    
    public void combination(char input[]){
        Arrays.sort(input);
        char result[] = new char[input.length];
        combination(input,0,result,0);
    }
    
    private void combination(char input[],int pos,char result[],int len){
        print(result,len);
        for(int i=pos; i < input.length;)
        {
            //idea is to find first non repeated char position j.
            // then keep adding one element from repetition and find combination for 
            //rest of the array
            //e.g aaabbc i = 0 j = 3 we do a{bbc} then aa{bbc} aaa{bbc} 
            int j= i;
            while(j < input.length && input[i] == input[j]){
                j++;
            }
            int tempLen = len;
            for(int k=i; k < j; k++){
                result[len] = input[i];
                combination(input,j,result,len+1);
                len++;
            }
            len = tempLen;
            i = j;
        }
    }
    
    private void print(char result[],int pos){
        for(int i=0; i < pos; i++){
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    public void combinationEasiest(char[] input) {
        List<Character> r = new ArrayList<>();
        Arrays.sort(input);
        combinationEasiest(input, 0, r);
    }

    private void combinationEasiest(char[] input, int pos, List<Character> r) {

        r.forEach(r1 -> System.out.print(r1 + " "));
        System.out.println();
        for (int i = pos; i < input.length; i++) {
            if (i != pos && input[i] == input[i-1]) {
                continue;
            }
            r.add(input[i]);
            combinationEasiest(input, i + 1, r);
            r.remove(r.size() - 1);
        }
    }
    
    public static void main(String args[]){
        CombinationWithReptition cwr = new CombinationWithReptition();
        int count[] = {3,2,3};
        cwr.combination("abc".toCharArray(), count);
        System.out.println();
        cwr.combination("aaabbccc".toCharArray());
        System.out.println();
        cwr.combinationEasiest("aaabbccc".toCharArray());
    }
}
