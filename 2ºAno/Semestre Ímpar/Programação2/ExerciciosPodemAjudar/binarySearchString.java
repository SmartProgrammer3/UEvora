package ExerciciosPodemAjudar;
public class binarySearchString {
    int binarySearchString3(String[] a, String s){
        int low = 0;
        int high = a.length - 1;
        int mid = (low + high) / 2;
        
        while(low <= high && !(a[mid].equals(s))){
            if(a[mid].compareTo(s) < 0){
                low = mid + 1;
            } else{
                high = mid - 1;
            }
            mid = (low + high) / 2;
        }
        
        if(low > high){
            return -1;
        }
        return mid;
    }
}