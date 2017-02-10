package com.hadoop.cleanUp;

import java.util.*;

public class ThreadTest {
	 public static final int N=50000;   
	  
     public static List values;   
  
     static{   
         Integer[] vals=new Integer[N];   
  
         Random r=new Random();   
  
         for(int i=0,currval=0;i<N;i++){   
             //vals=new Integer(currval);   
             currval+=r.nextInt(100)+1;   
         }   
  
         values=Arrays.asList(vals);   
     } 
     static long timeList(List lst){   
         long start=System.currentTimeMillis();   
         for(int i=0;i<N;i++){   
             int index=Collections.binarySearch(lst, values.get(i));   
             if(index!=i)   
                 System.out.println("***´íÎó***");   
         }   
         return System.currentTimeMillis()-start;   
     }   
	
public static void main(String[] args) {
	Map map=new HashMap();
	map.put(null, "2323");
	List<String> testlist=new ArrayList<String>();
	List<String> testlist2=new LinkedList<String>();
	Set set=new HashSet();
	set.add("1");
	set.add("2");
	set.add("3");
	set.add("4");
	Iterator<String> it=set.iterator();
	while(it.hasNext()){
		String str=it.next();
		System.out.println(str);
	}
	//System.out.println(map.get(null));
}
}
