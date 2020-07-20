# JUCdemo_SimpleCache
学习java并发包的相关内容，以及迭代一个简单微型的缓存。
## juc包相关内容
编写了demo实例，并在描述中进行解释，要结合源码。
* 线程池
* ThreadLocal
* 各种类型的锁->读写锁 公平锁与非公平锁 互斥锁与共享锁 等等
* 安全的java并发容器->ConcurrentHashMap BlockingQueue CopyOnWrite等等
* atomic类 -> AtomicLong AtomicInteger FiledUpdater以及1.8新增的LongAdder、Accumulator
* 流程控制类->Condition CountDownLatch Semaphore CyclicBarrier
* 理解cas思想，使用java编写一个等价代码
* 理解aqs类，并编写一个类CountDownLatch的自定义门闩

## 自定义一个cache
从最简单的hashmap一步步迭代成一个可以异常处理、并发安全、延时过期、错误重试的缓存。
使用juc包的相关内容以及线程池实现。
以及使用CountDownLatch进行简单粗暴的测压。


