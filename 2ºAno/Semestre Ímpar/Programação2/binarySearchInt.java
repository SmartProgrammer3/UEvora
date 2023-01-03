public class binarySearchInt{
    int binarySearch(int[] xs, int x) {
        int low = 0; // begin of interval
        int high = xs.length - 1; // end of interval
        int mid = (low + high) / 2; // middle of interval
        while (low <= high && xs[mid] != x) {
            if (xs[mid] < x) {
                low = mid + 1; // raise low
            } else {
                high = mid - 1; // lower high                           
            }
            mid = (low + high) / 2 ; // update mid
        }
        if (low > high) 
            mid = -1; // not found;
        return mid;
    }
}
