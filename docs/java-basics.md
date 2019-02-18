## java 基础知识

### 在 Java 中定义一个不做事且没有参数的构造方法的作用
Java 程序在执行子类的构造方法之前，如果没有用 super() 来调用父类特定的构造方法，则会调用父类中“没有参数的构造方法”。因此，如果父类中只定义了有参数的构造方法，而在子类的构造方法中又没有用 super() 来调用父类中特定的构造方法，则编译时将发生错误，因为 Java 程序在父类中找不到没有参数的构造方法可供执行。解决办法是在父类里加上一个不做事且没有参数的构造方法。 　
> 见 demo-explore 模块中 com.zmdj.explore.basic.parent中的例子

### InputStream使用mark(), reset()来实现重复读
#### InputSteam中几个重要字段
* pos 
int类型,当前字节读取的位置
* count
int类型,当前流的字节数
* markpos
int类型,最后一次mark()方法调用的位置
* marklimit
int类型,调用reset()回退的最大字节数
#### markSupported()
是否支持流标记和回退
```java
if (inputstream.markSupported()) {
    inputstream.mark();
    // ...
    inputstream.reset();
}
```

#### mark()
```java
        // 调用reset()方法最大回退的字节数
        marklimit = readlimit;
        // 保存mark位置
        markpos = pos;
```
#### reset()
```java
        // 检查流是否关闭
        getBufIfOpen(); // Cause exception if closed
        if (markpos < 0)
            throw new IOException("Resetting to invalid mark");
        // 当前字节读取位置回到mark()的位置
        pos = markpos;
```
#### 使用范例
看demo-boot模块 com.zmdj.demo.controller.FileUploadController中的使用