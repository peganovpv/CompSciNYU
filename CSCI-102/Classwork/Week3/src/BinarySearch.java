public class BinarySearch{

    public static boolean binarySearch(int[] data, int low, int high){
        if(low > high){
            return false;
        }
        else{
            int mid = (low + high) / 2;
            if(data[mid] == 0){
                return true;
            }
            else if(data[mid] > 0){
                return binarySearch(data, low, mid - 1);
            }
            else{
                return binarySearch(data, mid + 1, high);
            }
        }
    }

    public static void main(String[] args){

        int[] data = {1, 2, 3, 4, 5};

        System.out.println("binarySearch(data, 0, 4) = " + binarySearch(data, 0, 4));
    }

}