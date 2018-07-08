package com.wordladder.back-end;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


public class WordladderTest
    extends TestCase
{
    List<String> testDict = new ArrayList<String>();
   
    public WordladderTest(String testName )
    {
        super( testName );
    }

    public void testCreateLadder() throws IOException
    {
        String s;
        File file = new File("dictionary.txt");
        BufferedReader fr = new BufferedReader(new FileReader(file));
        while((s = fr.readLine()) != null)
        {
            testDict.add(s);
        }
        assertEquals(5, Wordladder.createLadder(testDict, "hit", "cog"));
        assertEquals(0, Wordladder.createLadder(testDict, "test", "test"));
    }


    public void testApp()
    {
        assertTrue( true );
    }
}
