package com.txt.javatest;

/**
 * Created by tongxt on 2017/5/16.
 */

public class SortTest {
    static int[] arg = {2,5,8,1,3,6,9,7,4};
    //选择排序，时间复杂度为O（N^2)
    public static void SelectionSort(int[] arg){
        int len = arg.length;
        for(int i =0,k=0; i< len; i++,k=i ){
            //找最小的值
            for(int j=i+1;j<len;j++){//剩下的未排序的，找出最小的下标
                if(arg[k] > arg[j]) k = j;//每一次循环都去判断当前K下标的值是不是比剩下的值大，大说明这个值不是最小的值，交换下标。
                //比如第一执行K=0，那么arg[0]=2 和剩下的元素比较，找到arg[3]=1，
                //此时交换下标，k变为3。
            }
            if(i!=k){//第一次的时候i和K不相等（K已经被赋值为3了，i还是0)
                int temp = arg[i];//把i下标对应的值先保存起来
                arg[i] = arg[k];//把k下标对应的值赋值给i下标对应的值，此时i下标对应的值是最小的了
                arg[k] = temp;//把i下标对应的值赋值给k下标对应。
                //也就第一次循环，交换了一下第0个元素和选择出的第K个元素的位置，
                // 每次找出的K始终是最小的
            }
        }
    }

    /** 另一种写法
     * 选择排序算法
     * 在未排序序列中找到最小元素，存放到排序序列的起始位置
     * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。
     * 以此类推，直到所有元素均排序完毕。
     * @param numbers
     */
    public static void selectSort(int[] numbers)
    {
        int size = numbers.length; //数组长度
        int temp = 0 ; //中间变量

        for(int i = 0 ; i < size ; i++)
        {
            int k = i;   //待确定的位置，k用来存放待交换的元素的下标
            //选择出应该在第i个位置的数
            for(int j = size -1 ; j > i ; j--)
            {
                if(numbers[j] < numbers[k])//其实类似于冒泡排序，只不过是减少了交换的次数
                {
                    k = j;
                }
            }
            //交换两个数
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }

    /**
     * 冒泡排序是一种用时间换空间的排序方法，最坏情况是把顺序的排列变成逆序，或者把逆序的数列变成顺序。
     * 在这种情况下，每一次比较都需要进行交换运算。
     * 举个例子来说，一个数列 5 4 3 2 1 进行冒泡升序排列，
     * 第一次大循环从第一个数（5）开始到倒数第二个数（2）结束，比较过程：
     * 先比较5和4，4比5小，交换位置变成4 5 3 2 1；比较5和3，3比5小，
     * 交换位置变成4 3 5 2 1……最后比较5和1，1比5小，交换位置变成4 3 2 1 5。
     * 这时候共进行了4次比较交换运算，最后1个数变成了数列最大数。
     第二次大循环从第一个数（4）开始到倒数第三个数（2）结束。进行3次比较交换运算。
     ……
     所以总的比较次数为 4 + 3 + 2 + 1 = 10次
     对于n位的数列则有比较次数为 (n-1) + (n-2) + ... + 1 = n * (n - 1) / 2，
     这就得到了最大的比较次数
     而O(N^2)表示的是复杂度的数量级。
     举个例子来说，如果n = 10000，那么 n(n-1)/2 = (n^2 - n) / 2 = (100000000 - 10000) / 2，
     相对10^8来说，10000小的可以忽略不计了，
     所以总计算次数约为0.5 * N^2。用O(N^2)就表示了其数量级（忽略前面系数0.5）。
     * @param arg
     */
    public static void bubbleSort(int[] arg){
        int len = arg.length;
        for(int i=len-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(arg[j]>arg[j+1]){//比较两个相邻的数，
                    // 比如第一次arg[0]=2,arg[1]=5,不符合条件，继续循环，
                    //第二次arg[1]=5,arg[2]=8,不符合条件，继续循环，
                    //第三次arg[2]=8,arg[3]=1,符合条件，交换元素
                    //变成arg[2]=1,arg[3]=8;
                    //第四次arg[3]=8,arg[4]=3,比较符合条件，继续交换元素
                    int temp = arg[j];
                    arg[j] = arg[j+1];
                    arg[j+1] = temp;
                }
            }//循环相邻的元素两两比较交换后
            // 里面的循环完成后，最大的元素排在了数组的最后，接下来继续循环剩下的。
        }

    }

    /**
     * 冒泡排序优化，利用boolean变量，减少循环次数
     * @param arg
     */
    public static void bubbleSort2(int[] arg){
        int len = arg.length;
        boolean isSorted;
        for(int i=len-1;i>0;i--){
            isSorted = true; // 每次进入大循环都设置标记
            for(int j=0;j<i;j++){
                if(arg[j]>arg[j+1]){//比较两个相邻的数，

                    int temp = arg[j];
                    arg[j] = arg[j+1];
                    arg[j+1] = temp;
                    isSorted = false; // 如果存在交换则表示还没有排好序
                }
            }
            if(isSorted){ // 否则，说明内循环已经是排好序的了
                break;//直接退出外循环
            }
        }
    }

    /**
     * 冒泡优化，记录有序无序区域的边界，避免有序区域进行没必要的数据交换
     * @param arg
     */
    public static void bubbleSort3(int[] arg){
        int len = arg.length;
        boolean isSorted;
        int lastExchangeIndex = 0;// 记录最后一次交换（内循环）的位置
        int sortBorder = len -1; // 无序数列的边界，每次只要比较到这里就好了
        for(int i = 0; i < len; i++){
            isSorted = true; // 每次进入大循环都设置标记
            for(int j=0;j < sortBorder;j++){// 下一次内循环到无序边界就可以了
                if(arg[j]>arg[j+1]){//比较两个相邻的数，
                    int temp = arg[j];
                    arg[j] = arg[j+1];
                    arg[j+1] = temp;
                    isSorted = false; // 如果存在交换则表示还没有排好序
                    lastExchangeIndex = j; // 每次都更新交换的位置
                }
            }
            sortBorder = lastExchangeIndex; // 每次内循环完成后更新一下边界
            if(isSorted){ // 否则，说明内循环已经是排好序的了
                break;//直接退出外循环
            }
        }
    }

    public void insertSort(int arg[]){
        int len = arg.length;
        //假设初始数组是[2,6,3,8,5],要进行排序，先假定一个插入点，一般是假设第0个元素为插入点
        int j ;
        int temp;
        for(int i=0;i<len;i++){
            temp = arg[i]; // 记录当前循环待插入的点（第一次为arg[0])
            for(j=i; j > 0 && temp < arg[j-1] ;j--){// 假如temp这个插入点比前面的值小，则将前面的值一个个的往后移，可以看出第一次不会执行
                arg[j] = arg[j-1];
            }
            arg[j] = temp;
        }
    }
}
