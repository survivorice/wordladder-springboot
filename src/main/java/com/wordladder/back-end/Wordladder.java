
package com.wordladder.back-end;
import java.io.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

public class Wordladder
{
    public static boolean initiate(List<String> d) throws IOException
    {
        System.out.println("Dictionary file name:");
        String filename,s;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        filename =br.readLine();
        File file = new File(filename);
        if(file == null)
        {
            return false;
        }
        BufferedReader fr = new BufferedReader(new FileReader(file));
        while((s = fr.readLine()) != null)
        {
            d.add(s);
        }
        return true;
    }


    public static int createLadder(List<String> dict, final String begin, final String end)
    {
        int l = begin.length();
        int count = 0;
        List<String>dictionary = new ArrayList<String>();
        for(String a : dict)
        {
            if(l == a.length())dictionary.add(a);
        }
        Queue<Stack<String>>ladders = new LinkedList<Stack<String>>();
        Stack<String>firstLadder = new Stack<String>();
        firstLadder.push(begin);
        ladders.offer(firstLadder);
        while(!ladders.isEmpty())
        {
            Stack<String>currentLadder = ladders.poll();
            final String currentWord = currentLadder.peek();
            for(int i = 0; i < l; ++i)
            {
                for(char j = 'a'; j <= 'z'; ++j)
                {
                    char[] arr = currentWord.toCharArray();
                    arr[i] = j;
                    String neighborWord = new String(arr);
                    if(dictionary.contains(neighborWord))
                    {
                        if(neighborWord.equals(end))
                        {
                            System.out.print("A ladder from ");
                            System.out.print(end);
                            System.out.print(" back to ");
                            System.out.print(begin);
                            System.out.print(": \n");
                            currentLadder.push(end);
                            String ans;
                            while(!currentLadder.isEmpty())
                            {
                                ans = currentLadder.pop();
                                System.out.print(ans);
                                System.out.print(' ');
                                count++;
                            }
                            System.out.print("\nThe length of the ladder is:");
                            System.out.print(count);
                            System.out.print('\n');
                            return count;
                        }
                        else
                        {
                            Stack<String>temp = (Stack<String>)currentLadder.clone();
                            temp.push(neighborWord);
                            ladders.offer(temp);
                            dictionary.remove(neighborWord);
                        }
                    }
                }
            }
        }
        System.out.print("No word ladder found from ");
        System.out.print(end);
        System.out.print(" back to ");
        System.out.print(begin);
        System.out.print(".\n");
        return 0;
    }

    public static boolean isValid(final String s)
    {
        char[] arr = s.toCharArray();
        for(char c : arr)
        {
            if(!((c >= 'a' && c <= 'z') || (c > 'A' && c < 'Z')))return false;
        }
        return true;
    }

    public static void main( String[] args ) throws IOException
    {
        List<String> dict = new ArrayList<String>();
        if(initiate(dict))
        {
            while(true)
            {
                String begin,end;
                System.out.print("Word #1 (or enter to quit):");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                begin = br.readLine();
                if(begin.equals(""))
                {
                    System.out.print("Have a nice day!");
                    return;
                }
                if(!isValid(begin))
                {
                    System.out.print("Invalid input!");
                    continue;
                }
                System.out.print("Word #2 (or enter to quit):");
                end = br.readLine();
                if(end.equals(""))
                {
                    System.out.print("Have a nice day!");
                    return;
                }
                if(!isValid(end))
                {
                    System.out.print("Invalid input!\n");
                    continue;
                }
                if(begin.equals(end))
                {
                    System.out.print("The two words must be different\n");
                    continue;
                }
                if(begin.length() != end.length())
                {
                    System.out.print("The two words must be of the same length\n");
                    continue;
                }
                begin = begin.toLowerCase();
                end = end.toLowerCase();
                if(!(dict.contains(begin) && dict.contains(end)))
                {
                    System.out.print("The two words must be in the dictionary.\n");
                    continue;
                }
                createLadder(dict, begin, end);
            }
        }
        else
        {
            System.out.print("Have a nice day!");
        }
    }
}
