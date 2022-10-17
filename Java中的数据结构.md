## Integer

```java
// 自带属性：最大整数
// 将len设为最大整数
int len = Integer.MAX_VALUE;// len = 0x3f3f3f3f

// 比较大小：
a.compareTo(b);// 源码：return (x < y) ? -1 : ((x == y) ? 0 : 1);
// 是否相等：
a.equals(b);// Integer使用equals()
int n = Integer.intValue(a);// 或者Integer.intValue()得到int值再进行比较
```

## Character

```java
Character.isDigit(c);
```

## 字符串

```java
charAt(i);
length();
substring(a,b);// [a,b)
```

## 数组

```java
// 构造
int[] nums = new int[3];
int[] nums = new int[]{1, 2, 3};
// 自带属性：长度
int len = nums.length;

// 二维数组
int[][] matrix = new int[m][n];
// m * n
int m = matrix.length, n = matrix[0].length;

// 升序（直接重新排序）
Arrays.sort(nums);
```

## HashMap

```java
// 构造（键值对）
// You can't use primitive types as generic arguments in Java.
// 所以 值 必须是 Integer 而非 int
HashMap<Character,Integer> hm = new HashMap<Character,Integer>();
// 自带方法：
put(Object key, Object value) // 添加键值对（会覆盖原来的键值对）
putIfAbsent(Object key, Object value)
2.putAll(Collection c) //添加指定的映射关系到目标映射关系
get(Object key) // 根据键来获取对应的值
getOrDefault(Object key, V defaultValue) //map中存在key则使用对应的value，否则使用defaultValue
containsKey(Object key) //是否有指定key的映射
6.containsValue(Object value) //是否有指定value的映射
7.remove(Object key) //删除该键值对
8.values() //返回所有值，返回形式为Collection
9.isEmpty() //测试映射是否为空
size() //返回大小
```

## List

```java
// 创建
List<Integer> res = new ArrayList<>();

// 自带方法
// list中添加，获取，删除元素：
add(index, e);
get(index);
remove(index);
    
list.size();

2、list中是否包含某个元素；（并不是equals）（是List中另外一个方法contains，用法与equals大差不离）

3、list中根据索引将元素数值改变(替换)；（list.set(index,e)）

4、list中查看（判断）元素的索引；(indexOf(Object o))

5、根据元素索引位置进行的判断；

6、利用list中索引位置重新生成一个新的list（截取集合）；（list.subList(fromindex,toindex)）

7、判断list是否为空；(List.isEmpty())

8、返回Iterator集合对象；(使用Iterator迭代器方式需要创建对象，且这种迭代器方式多用于遍历)

9、将集合转换为字符串；List.toString();
```

## PriorityQueue

```java
// 构造 两个优先级序列：
private PriorityQueue<Integer> pq1;// 大顶堆
private PriorityQueue<Integer> pq2;// 小顶堆
//  大顶堆： 1,2,3,4,5 <- 堆顶
pq1 = new PriorityQueue<>((a, b) -> {
    return b - a;
});
//  小顶堆： 5,4,3,2,1 <- 堆顶
pq2 = new PriorityQueue<>((a, b) -> {
    return a - b;
});

// 自带方法
public boolean add(E e); //在队尾插入元素，插入失败时抛出异常，并调整堆结构
public boolean offer(E e); // 在队尾插入元素，插入失败时抛出false，并调整堆结构

public E remove(); //获取队头元素并删除，并返回，失败时前者抛出异常，再调整堆结构
public E poll(); // 获取队头元素并删除，并返回，失败时前者抛出null，再调整堆结构

public E element(); //返回队头元素（不删除），失败时前者抛出异常
public E peek()；//返回队头元素（不删除），失败时前者抛出null

public boolean isEmpty(); //判断队列是否为空
public int size(); //获取队列中元素个数
public void clear(); //清空队列
public boolean contains(Object o); //判断队列中是否包含指定元素（从队头到队尾遍历）
public Iterator<E> iterator(); //迭代器
```

## Stack

```java
// 创建
Stack<Character> stk = new Stack<>();

// 自带方法
1	boolean empty() 测试堆栈是否为空。
2	Object peek( ) 查看堆栈顶部的对象，但不从堆栈中移除它。
3	Object pop( ) 移除堆栈顶部的对象，并作为此函数的值返回该对象。
4	Object push(Object element) 把项压入堆栈顶部。
5	int search(Object element) 返回对象在堆栈中的位置，以 1 为基数。
```

## StringBuilder

```java
// 创建
StringBuilder sb = new StringBuilder();

sb.append('c');

sb.reverse().toString();
```

## LinkedHashMap（Java内置）

```java
// 创建
// Java内置LinkedHashMap：哈希链表（双向链表和哈希表的结合体）
LinkedHashMap<Integer,Integer> cache = new LinkedHashMap<>();

// 自带方法
containsKey(key);
get(key);
put(key,value);
size();
// 删除 链表头部的 key
int oldestKey = cache.keySet().iterator().next();
remove(oldestKey);
```

## LinkedHashSet

```java
/**
* LinkedHashSet：链表和哈希集合的结合体
* 链表不能快速访问链表节点，但是插入元素具有时序；
* 哈希集合中的元素无序，但是可以对元素进行快速的访问和删除。
*/
LinkedHashSet<Integer>>;

add(key);
remove(key);
add(key);
isEmpty();
```

## Random

```java
/**
* 从集合中等概率地随机获得⼀个元素
*/
public int getRandom() {
    Random r = new Random();
    
    // nextInt()返回一个伪随机数，[0,n)之间
    return nums.get(r.nextInt(nums.size()));
}
```

