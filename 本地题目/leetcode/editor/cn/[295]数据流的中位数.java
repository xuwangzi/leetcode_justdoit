//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。 
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例： 
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2 
//
// 进阶: 
//
// 
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 
//
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 762 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {

    // 构造 两个优先级序列：
    //  大顶堆： 1,2,3,4,5 <- 堆顶
    //  小顶堆：10,9,8,7,6 <- 堆顶
    //  中位数可以通过它们的堆顶元素算出来
    private PriorityQueue<Integer> small;// 大顶堆 中，元素较小
    private PriorityQueue<Integer> large;// 小顶堆 中，元素较大

    public MedianFinder() {
        // 大顶堆
        small = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        // 小顶堆
        large = new PriorityQueue<>((a, b) -> {
            return a - b;
        });

    }


    // 添加一个数字
    public void addNum(int num) {

        if (small.size()>=large.size()){
            small.offer(num);
            large.offer(small.poll());
        }else {
            large.offer(num);
            small.offer(large.poll());
        }

    }


    // 计算当前添加的所有数字的中位数
    public double findMedian() {

        if (small.size() < large.size()) {
            return large.peek();
        } else if (small.size() > large.size()) {
            return small.peek();
        } else {
            return (small.peek() + large.peek()) / 2.0;
        }

    }


}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
