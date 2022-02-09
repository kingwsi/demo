#### 类加载器：
- 启动类加载器Application ClassLoader
- 扩展类加载器Extension ClassLoader
- 应用类加载器Application ClassLoader

#### 双亲委派机制：
- Application ClassLoader加载类的时先委托Extension ClassLoader加载，
- Extension ClassLoader收到加载请求时，同样也是委托上级类加载器Bootstrap ClassLoader加载
- 然后由Bootstrap ClassLoader在\jre\lib核心类目录中寻找类去加载
- 找不到就让下级Extension ClassLoader去\lib\ext中寻找，
- 找不到就去classpath中寻找



#### jvm 运行空间
方法区 堆 虚拟机栈 本地方法栈 寄存器（程序计数器）
#### jvm 内存比例
- Eden0: form: to = 8:1:1
- 新生代：老年代 = 1：2


#### 方法区：

```
public static void main(String[] args){
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.changevaluel(age);
        System.out.println(tage);
        // 复制一份值，源值不变 还是20
        Person person = new Person("abc");
        test.changevalue2(person);
        System.out.println("personName----" + person.getPersonName());
        // 引用的对象地址 
        String str = "abc";
        test.changevalue3(str);
        System.out.println("string-"+str);
        // 常量池中 值不变 abc
    }
```

#### 栈：
队列-先进先出
栈-先进后出

#### 堆：
Eden form to(可交换) young GC
老年代     Full GC
元空间 使用物理内存(java8 记录)
- 在metaspace中，类和其元数据的生命周期与其对应的类加载器相同，只要类的类加载器是存活的，在Metaspace中的类元数据也是存活的，不能被回收。
- 每个加载器有单独的存储空间。
- 省掉了GC扫描及压缩的时间。
- 当GC发现某个类加载器不再存活了，会把对应的空间整个回收。

#### 垃圾回收
###### 判断对象是否存活
- 引用计数法 不用了
- 可达性分析
###### 四大垃圾回收算法
`收集频率：次数上较频繁收集young区，较少收集Old区， 基本不动元空间`
`不同代用不同算法，年轻代 死亡对象多，用复制算法，老年代，存活对象较多，使用标记清除 + 压缩`
- 引用计数法：
- 复制算法：年轻代中使用Minor GC，这种算法就是复制算法，s0，s1， 不会产生内存碎片/耗空间
- 标记-清除：老年代一般由标记清除和标记整理混合实现
        内存碎片问题/扫描两遍，先标记，再清除
- 标记压缩