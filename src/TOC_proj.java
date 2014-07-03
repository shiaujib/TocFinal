import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;

import org.json.*;

										//資訊103 F74009054 楊于進
public class TOC_proj {

	/**
	 * @param args
	 */
	private static char[] buf;
	private static StringBuffer sBuf = new StringBuffer();
	private static JSONObject myObject;
	private static String []columnName=new String[20];
	private static String []valueTable=new String[10000];
	private static int totalColumnNum;
	private static int [][]combinationList=new int [50][4];
	private static int combinationListNum=0;
	private static int [] appearList=new int [10000];
	private static int [][]appearTable=new int [300][10000];
	private static int []appearTableNum=new int [10000];
	private static int []haveData=null;
	private static String [][][] dataList=new String[100][100000][10];
	private static String [][] nArray=new String[100000][10]; //不重複data表
	private static String [][] rArray=new String[100000][10]; //重複data表
	private static int []setAppearTime=new int[100000]; 
	private static int index=0;
	private static int rindex=0;

	private LinkedHashMap map;
	private static String URL;
	public TOC_proj() throws Exception{
		sBuf=download(URL);
		//System.out.println(sBuf);
	}
	
	private static StringBuffer download(String urlString) throws Exception{
		URL url=new URL(urlString);
		int read;
		buf=new char[1024];
		StringBuffer strBuf=new StringBuffer();		
		try {
			BufferedReader bf =new BufferedReader(new InputStreamReader(url.openStream(),"UTF8"));
			while((read=bf.read(buf))!=-1){
				strBuf.append(buf,0,read);
							
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strBuf;
			
	}
	public static void setURL(String url){
		URL=url;
		
	}
	public static int dectectHaveValue(JSONObject ob,int index){
		int num=0;
		int n=0;
		int []tmp=new int [10];
		for(int i=0;i<ob.names().length();i++){
			if(!ob.isNull(columnName[i])){
				tmp[n++]=i;
				
				if(valueTable[index]==null){
					valueTable[index]=Integer.toString(i);
					
				}
				else{
					//System.out.println(i);
					valueTable[index]=valueTable[index].concat(Integer.toString(i));
				}
			}
		}
		haveData=new int [n];
		for(int i=0;i<n;i++){
			haveData[i]=tmp[i];
			
			
		}
		return n;
		
		
		
	}
	public static void convertStringArray(JSONArray ja){
		for(int i=0;i<ja.length();i++){
			columnName[i]=ja.optString(i);
		}
		
	}
	public static int[] initialArray(int n,int array[]){
		for(int i=0;i<n;i++){
			array[i]=i;
			
		}
		return array;
		
	}
	  public static void combine(int[] a, int n) {  
	        
	        if(null == a || a.length == 0 || n <= 0 || n > a.length)  
	            return;  
	              
	        int[] b = new int[n];  
	        getCombination(a, n , 0, b, 0);  
	    }  
	  
	    private static void getCombination(int[] a, int n, int begin, int[] b, int index) {  
	          
	        if(n == 0){
	            for(int i = 0; i < index; i++){  
	                //System.out.print(b[i] + " ");
	                combinationList[combinationListNum][i]=b[i];
	            }  
	            //System.out.println();  
	            combinationListNum++;
	            return;  
	        }  
	              
	        for(int i = begin; i < a.length; i++){  
	              
	            b[index] = a[i];  
	            getCombination(a, n-1, i+1, b, index+1);  
	        }  
	          
	    } 
	private static int appearDetect(int n,String []valueTable,int [][]combinationList,int combinationListNum,int l){
		int []tmp=new int[10];
		int index=0;
		int tableIndex=0;
		int appearN=0;
		
		for(int k=0;k<combinationListNum;k++){
			for(int i=0;i<n;i++){
				for(int j=0;j<valueTable[i].length();j++){
			//System.out.println("h "+valueTable[0].charAt(2));
					if(combinationList[k][index]==Character.getNumericValue(valueTable[i].charAt(j))){
						index++;
						appearN++;
					}
					//System.out.println("a  "+valueTable[i].charAt(j));
				}
				if(appearN==l){
					appearTable[k][tableIndex++]=i;
					//System.out.println("b  "+appearTable[k][tableIndex-1]);
				
				}
				//System.out.println("next");
				index=0;
				appearN=0;
				//tableIndex=0;
				//System.out.println("next");
				}
			appearTableNum[k]=tableIndex;
			//System.out.println("num "+appearTableNum[k]);		
			tableIndex=0;
			//System.out.println("next");			
		}
		
		return 0;
		
	}
	public static void clearRepeat(int l){   //清除重複出現 data set
		String tmp;
		
		
		for(int i=0;i<combinationListNum;i++){
			for(int j=0;j<appearTableNum[i];j++){
				for(int k=0;k<l;k++){
					nArray[index][k]=dataList[i][j][k];
	
					
					}
				index++;
				for(int h=0;h<index-1;h++){
					if(l==2&&nArray[index-1][0].equals(nArray[h][0])&&nArray[index-1][1].equals(nArray[h][1])){
						index--;
						break;
					}
					if(l==3&&nArray[index-1][0].equals(nArray[h][0])&&nArray[index-1][1].equals(nArray[h][1])&&nArray[index-1][2].equals(nArray[h][2])){
						index--;
						break;
					}
					if(l==4&&nArray[index-1][0].equals(nArray[h][0])&&nArray[index-1][1].equals(nArray[h][1])&&nArray[index-1][2].equals(nArray[h][2])&&nArray[index-1][3].equals(nArray[h][3])){
						index--;
						break;
					}
					
				}
			}
		}
		for(int i=0;i<combinationListNum;i++){
			for(int j=0;j<appearTableNum[i];j++){
				for(int k=0;k<l;k++){
					rArray[rindex][k]=dataList[i][j][k];
	
					
					}
				rindex++;
			}
		}
		
	
			/*for(int j=0;j<index;j++){
				for(int k=0;k<3;k++){
					System.out.print(nArray[j][k]+" ");
				}
				System.out.println();
				}*/
		
		
	}
	private static void checkSetAppear(int l){
		for(int i=0;i<index;i++){
			for(int j=0;j<rindex;j++){
				if(l==3&&nArray[i][0].equals(rArray[j][0])&&nArray[i][1].equals(rArray[j][1])&&nArray[i][2].equals(nArray[j][2])){
					setAppearTime[i]++;
					
				}
			}
			setAppearTime[i]++;
			}
		
		for(int i=0;i<index;i++){
			System.out.println(setAppearTime[i]);
			
		}
		
	}
	private static void printTop(int top,int l){
		int tmp;
		int num=0;
		int []tmpArray=new int [100000];
		for(int i=0;i<index;i++){
			tmpArray[i]=setAppearTime[i];
		}
		for(int i=0;i<index;i++)
			for(int j=0;j<index-1;j++){
				if(tmpArray[j]<tmpArray[j+1]){
					tmp=tmpArray[j];
					tmpArray[j]=tmpArray[j+1];
					tmpArray[j+1]=tmp;
				}
				
			
		}
		/*while(tmpArray[num]==tmpArray[num+1]){
			tmpArray[num+1]=tmpArray[num+2];
		}*/
		
		for(int i=0;i<top;i++){
			for(int j=0;j<index;j++){
				if(tmpArray[i]==setAppearTime[j]&&l==3){
					
					System.out.println(nArray[j][0]+" "+nArray[j][1]+" "+nArray[j][2]+" 出現次數"+tmpArray[i]);
					
				}
				if(tmpArray[i]==setAppearTime[j]&&l==2){
					
					System.out.println(nArray[j][0]+" "+nArray[j][1]+" 出現次數"+tmpArray[i]);
					
				}
				if(tmpArray[i]==setAppearTime[j]&&l==4){
					
					System.out.println(nArray[j][0]+" "+nArray[j][1]+" "+nArray[j][2]+" "+nArray[j][3]+" 出現次數"+tmpArray[i]);
					
				}
			}
			
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		int [] numList = null;
		int	 l,top;
		setURL(args[0]);
		l=Integer.parseInt(args[2]);
		top=Integer.parseInt(args[1]);
		TOC_proj demo=new TOC_proj();
		
		//System.out.println(sBuf);
		JSONArray ja=new JSONArray(new JSONTokener(sBuf.toString()));
		for(int i=0;i<ja.length();i++){
			//System.out.println(ja);
			myObject=ja.getJSONObject(i);
			//System.out.println(myObject);
			
			//System.out.println(myObject.keys());myObject.get(columnName[combinationList[n][k]])myObject.get(columnName[combinationList[n][k]])myObject.get(columnName[combinationList[n][k]])myObject.get(columnName[combinationList[n][k]])myObject.get(columnName[combinationList[n][k]])myObject.get(columnName[combinationList[n][k]])
			if(i==0){
				convertStringArray(myObject.names()); //獲得欄位名稱
				totalColumnNum=myObject.names().length();
				numList=new int [totalColumnNum];
				numList=initialArray(totalColumnNum,numList);
				combine(numList,l);
				//System.out.println(combinationList[0][3]);
					}
			
			dectectHaveValue(myObject,i);
			/*System.out.println(haveData[0]+" "+haveData[1]+" "+haveData[2]);
			combine(haveData,2);
			System.out.println("a "+combinationListNum);*/
			//combinationListNum=0;
			/*for(int n=0;n<totalColumnNum;n++){
				myObject
				
			}*/
		
			//System.out.println(columnName[i]);
			
		}
			appearDetect(ja.length(),valueTable,combinationList,combinationListNum,l);//建立資料集表
		
			for(int i=0;i<combinationListNum;i++){
				for(int j=0;j<appearTableNum[i];j++){
				myObject=ja.getJSONObject(appearTable[i][j]);
				for(int k=0;k<l;k++){
				myObject.get(columnName[combinationList[i][k]]);
				dataList[i][j][k]=myObject.get(columnName[combinationList[i][k]]).toString();
				System.out.print(" "+dataList[i][j][k]);
				}
				
				System.out.println();
		
		}
				
	}
			clearRepeat(l);
			checkSetAppear(l);
			printTop(top,l);


			
	}

}
