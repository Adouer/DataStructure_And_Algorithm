package binarysearch;

/**
 * 二分查抄非递归
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 8);
        System.out.println("index=" + index);
    }

    /**
     * 二分查找算法  O(lgn)
     *
     * @param arr    被检索的数组
     * @param target 目标
     * @return
     */
    public static int binarySearch(int[] arr, int target) {
        //左下标
        int left = 0;
        //右下标
        int right = arr.length - 1;
        //说明继续查找
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

}
