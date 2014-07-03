class Combination {
	 public static int [][]array=new int [100][2];
	 public static int aIndex=0;
    // Driver method to test below methods
    public static void main(String[] args) {   
    	Combination demo =new Combination();
    	int[] a = {0,1,2,3,4};  
        int n = 2;  
       
    	demo.combine(a, n);
    	for(int i=0;i<aIndex;i++){
    		for(int j=0;j<2;j++){
    			 System.out.print(array[i][j]+" ");
    		}
    		System.out.println();
    	}
    }    
    public void combine(int[] a, int n) {  
        
        if(null == a || a.length == 0 || n <= 0 || n > a.length)  
            return;  
              
        int[] b = new int[n];//辅助空间，保存待输出组合数  
        getCombination(a, n , 0, b, 0);  
    }  
  
    private void getCombination(int[] a, int n, int begin, int[] b, int index) {  
          
        if(n == 0){//如果够n个数了，输出b数组  
            for(int i = 0; i < index; i++){  
               // System.out.print(b[i] + " ");  
                array[aIndex][i]=b[i];
            }  
            System.out.println();  
            aIndex++;
            return;  
        }  
              
        for(int i = begin; i < a.length; i++){  
              
            b[index] = a[i];  
            getCombination(a, n-1, i+1, b, index+1);  
        }  
  
    }  
    
 
    
}