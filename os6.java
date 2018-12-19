import java.io.*;
import java.util.*;
class os6
{
 	public static void main(String args[])throws Exception
 	{
 		int a[]=new int[50];
 		int la,pn,offset,p,t,i=0,fn=0,j,pa;
 		String str=new String();
 		FileReader f=new FileReader("addresses.txt");			
 		BufferedReader b=new BufferedReader(f);
 		p=(int)Math.pow(2,32);
 		t=(int)Math.pow(2,16);
 		while((str=b.readLine())!=null)				//Read logical address from file
 		{
 			la=Integer.parseInt(str);
 			/*If address is negative*/
 			if(la<0)
 			{
 				System.out.println("Invalid address "+la);
 				continue;
 			}
 			/*if address is greater then 32 bit*/
 			if(la>(p-1))
 			{
 				System.out.println("greater then 32 bit");
 				continue;
 			}
 			/*if address is between 16-32 bit*/
 			if(la>(t-1)&&la<p)
 			{
 				la=la%t;					//mod logical address with 65536					
 			}
 			pn=la/256;						//To get page no.
 			offset=la%256;					//To get offset.
			/*To check whether page already exist in table*/
 			for(j=0;j<i;j++)
 			{
 				if(pn==a[j])
 				{
 					fn=j;			//if yes allocate same frame no.
 					break;
 				}
 			}
 			/*if not in page table*/
 			if(j==i)
 			{
 				a[i]=pn;			//Store new page no. in page table
 				fn=i;				//allocate new frame
 				i++;
 			}
 			pa=((fn*256)+offset);		//calculate Physical address
 			System.out.println("Logical address="+la+" Physical address="+pa);
 		}
 		/*Display Page Table*/
 		System.out.println("Page table");
 		System.out.println("frame no.    Page no.");
 		for(j=0;j<i;j++)
 		{
 			System.out.println(j+"\t\t"+a[j]);
 		}
 	}
}
 
/*output
tejal@ubuntu:~/Desktop$ javac os6.java
tejal@ubuntu:~/Desktop$ java os6
Logical address=16916 Physical address=20
Logical address=62493 Physical address=285
Invalid address -10456
Logical address=3 Physical address=515
Logical address=16980 Physical address=84
greater then 32 bit
Invalid address -456789
Logical address=17890 Physical address=994
Logical address=45678 Physical address=1134
Logical address=23456 Physical address=1440
Logical address=22108 Physical address=1628
Logical address=56789 Physical address=2005
Logical address=34567 Physical address=2055
Logical address=1923 Physical address=2435
Logical address=29876 Physical address=2740
Logical address=37289 Physical address=2985
Logical address=45123 Physical address=3139
Logical address=7920 Physical address=3568
Logical address=38765 Physical address=3693
Logical address=64789 Physical address=3861
Page table
frame no.    Page no.
0		66
1		244
2		0
3		69
4		178
5		91
6		86
7		221
8		135
9		7
10		116
11		145
12		176
13		30
14		151
15		253
tejal@ubuntu:~/Desktop$ 
*/
