public class SelectionSort {
    public void selectionSort( int[] number){
        int startIndex, minIndex, length, temp;
        length = number.length;

        for (startIndex = 0; startIndex <= length-2; startIndex++){
            //each iteration of the for loop is one pass
            minIndex = startIndex;
            //find the smallest in this pass at position minIndex
            for (int i = startIndex+1; i <= length-1; i++) {
                if (number[i] < number[minIndex]) minIndex = i;
            }
                
            temp = number[startIndex];
            number[startIndex] = number[minIndex];
            number[minIndex] = temp;
        }
    }
}
