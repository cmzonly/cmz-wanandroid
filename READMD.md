#flow操作符
1.map 用于将一个值映射成另一个值
2.filter 用来过滤掉一些数据的
3.onEach 遍历
4.debounce(防止误动作)   用来确保flow的各项数据之间存在一定的时间间隔
想象一下我们正在Edge浏览器的地址栏中输入搜索关键字，浏览器的地址栏下方通常都会给出一些搜索建议。
这些搜索建议是根据用户当前输入的内容通过发送网络请求去服务器端实时获取的。
但如果用户每输入一个字符就立刻发起一条网络请求，这是非常不合理的设计。
原因很简单，网络请求是不可能做到无延时响应的，而用户的打字速度通常都比较快。
如果用户每输入一个字符都立刻发起一条网络请求，那么很有可能用户输完了第3个字符之后，
对应第1个字符的网络请求结果才刚刚返回回来，而此时的数据早已是无效数据了。
正确的做法是，我们不应该在用户每输入一个字符时就立刻发起一条网络请求，而是应该在一定时间的停顿之后再发起网络请求。
如果用户停顿了一段时间，很有可能说明用户已经结束了快速输入，开始期待一些搜索建议了。这样就能有效地避免发起无效网络请求的情况。

5.sample(采样)   sample操作符函数和debounce稍微有点类似，它们的用法也比较接近，同样都是接收一个时间参数。

        sample是采样的意思，也就是说，它可以从flow的数据流当中按照一定的时间间隔来采样某一条数据。

        这个函数在某些源数据量很大，但我们又只需展示少量数据的时候比较有用。

        比如说视频网站的弹幕功能，理论上来说每个时间点用户发送的弹幕数量可以是无限多的，但是视频网站又不可能把每条弹幕都展示出来，不然的话弹幕和视频都没法看了。

        因此，这个时候就需要对数据进行采样。

        我们来模拟一下这种场景，假设某个视频的播放量很大，每时每刻都有无数人在发送弹幕，但是我们每秒钟最多只允许显示1条弹幕，代码就可以这样写：
        fun main() {
            runBlocking {
                flow {
                    while (true) {
                    emit("发送一条弹幕")
                    }
                }
                .sample(1000)
                .flowOn(Dispatchers.IO)
                .collect {
                    println(it)
                 }
            }
        }
        这里我们在flow构建函数中写了一个死循环，不断地在发送弹幕，那么这个弹幕的发送量无疑是巨大的。

        而接下来我们借助sample函数进行数据采集，每秒钟只取一条弹幕，这样就轻松满足了前面说的弹幕采样率的要求。

        另外还有一点需要注意，由于flow是通过死循环不断发送的，我们必须调用flowOn函数，让它在IO线程里去执行，否则我们的主线程就一直被卡死了。

6.reduce 归纳,简化
其中acc是累积值的意思，value则是当前值的意思。

    也就是说，reduce函数会通过参数给我们一个Flow的累积值和一个Flow的当前值，

    我们可以在函数体中对它们进行一定的运算，运算的结果会作为下一个累积值继续传递到reduce函数当中。

    举一个更加具体点的例子，我们上学时学等差数列都会讲这个故事，高斯的老师让全班同学计算从1加到100的结果。

7.fold 合拢
fold函数和reduce函数基本上是完全类似的，它也是一个终端操作符函数。

    主要的区别在于，fold函数需要传入一个初始值，这个初始值会作为首个累积值被传递到fold的函数体当中，它的基本公式如下：
    flow.fold(initial) { acc, value -> acc + value }
    总体区别就这么多，所以我感觉fold函数并没有什么好讲的，它和reduce函数具体用谁只取决于你编程时的业务逻辑需求。
    但是其实reduce函数和fold函数并不是只能用作数值计算，相反它们可以作用于任何类型的数据。因此，这里我就用fold函数来演示一个字符串拼接的功能吧：
    fun main() {
        runBlocking {
            val result = flow {
                for (i in ('A'..'Z')) {
                emit(i.toString())
             }
         }.fold("Alphabet: ") { acc, value -> acc + value}
         println(result)
        }
    }
    这里我们将字母A-Z进行了拼接，另外fold函数要求传入一个初始值，那么我们就再添加一个Alphabet的头部，打印结果如下所示：

8.flatMapConcat   : 平面地图连接
flatMap的核心，就是将两个flow中的数据进行映射、合并、压平成一个flow，最后再进行输出。

    在实际的业务中，这个函数又有什么具体的应用场景呢？
    不知道你有没有遇到过这样的情况，请求一个网络资源时需要依赖于先去请求另外一个网络资源。
    比如说我们想要获取用户的数据，但是获取用户数据必须要有token授权信息才行，因此我们得先发起一个请求去获取token信息，然后再发起另一个请求去获取用户数据。

9.flatMapMerge :平面地图合并

    flatMapConcat与flatMapMerge函数最主要的区别其实就在字面上。concat是连接的意思，merge是合并的意思。
    连接一定会保证数据是按照原有的顺序连接起来的，而合并则只保证将数据合并到一起，并不会保证顺序。
    因此，flatMapMerge函数的内部是启用并发来进行数据处理的，它不会保证最终结果的顺序。

    fun main() {
        runBlocking {
            flowOf(300, 200, 100)
            .flatMapMerge {
                flow {
                delay(it.toLong())
                emit("a$it")
                emit("b$it")
                }
            }
            .collect {
                 println(it)
             }
        }
    }
    输出结果:a100,b100,a200,b200,c300,b300

10.flatMapLatest   :平面地图最新

    flatMapLatest函数也是类似的，flow1中的数据传递到flow2中会立刻进行处理，
    但如果flow1中的下一个数据要发送了，而flow2中上一个数据还没处理完，则会直接将剩余逻辑取消掉，开始处理最新的数据。

11.zip

    使用zip连接的两个flow，它们之间是并行的运行关系。最终的总耗时取决于运行耗时更久的那个flow。
    那么zip函数具体又有什么应用场景呢？
    想象一下如下需求，我们正在开发一个天气预报应用，需要去一个接口请求当前实时的天气信息，还需要去另一个接口请求未来7天的天气信息。
    这两个接口之间并没有先后依赖关系，但是却需要两个接口同时返回数据之后再将天气信息展示给用户。
    如果我们先去请求当前实时的天气信息，等得到数据响应之后再去请求未来7天的天气信息，那效率就会比较低了，因为这两件事情很明显可以同时去做。

12.buffer   :缓冲 解决Flow流速不均匀的问题。所谓流速不均匀问题，指的就是Flow上游发送数据的速度和Flow下游处理数据的速度不匹配，从而可能引发的一系列问题。
    
    fun main() {
        runBlocking {
            flow {
                emit(1);
                delay(1000);
                emit(2);
                delay(1000);
                emit(3);
            }.onEach {
                println("$it is ready")
            }.collect {
                delay(1000)
                println("$it is handled")
            }
        }
    }
    这里每条数据都是要耗费2秒时长才能处理完
    因为默认情况下，collect函数和flow函数会运行在同一个协程当中，因此collect函数中的代码没有执行完，flow函数中的代码也会被挂起等待。
    也就是说，我们在collect函数中处理数据需要花费1秒，flow函数同样就要等待1秒。
    collect函数处理完成数据之后，flow函数恢复运行，发现又要等待1秒，这样2秒钟就过去了才能发送下一条数据。

    buffer函数会让flow函数和collect函数运行在不同的协程当中，这样flow中的数据发送就不会受collect函数的影响了。
    buffer函数其实就是一种背压的处理策略，它提供了一份缓存区，当Flow数据流速不均匀的时候，使用这份缓存区来保证程序运行效率。
    flow函数只管发送自己的数据，它不需要关心数据有没有被处理，反正都缓存在buffer当中。
    而collect函数只需要一直从buffer中获取数据来进行处理就可以了。
    但是，如果流速不均匀问题持续放大，缓存区的内容越来越多时又该怎么办呢？
    这个时候，我们又需要引入一种新的策略了，来适当地丢弃一些数据。
    
    那么进入到本篇文章的最后一个操作符函数：conflate
    fun main() {
        runBlocking {
            flow {
                emit(1);
                delay(1000);
                emit(2);
                delay(1000);
                emit(3);
            }
            .onEach {
                println("$it is ready")
            }
            .buffer()
            .collect {
                delay(1000)
                println("$it is handled")
            }
        }
    }
13.conflate  : 合并

    buffer函数最大的问题在于，不管怎样调整它缓冲区的大小（buffer函数可以通过传入参数来指定缓冲区大小），
    都无法完全地保障程度的运行效果。究其原因，主要还是因为buffer函数不会丢弃数据
    buffer函数最大的问题在于，不管怎样调整它缓冲区的大小（buffer函数可以通过传入参数来指定缓冲区大小），都无法完全地保障程度的运行效果。究其原因，主要还是因为buffer函数不会丢弃数据。
    而在某些场景下，我们可能并不需要保留所有的数据。
    比如拿股票软件举例，服务器端会将股票价格的实时数据源源不断地发送到客户端这边，而客户端这边只需要永远显示最新的股票价格即可，将过时的数据展示给用户是没有意义的。
    因此，这种场景下使用buffer函数来提升运行效率就完全不合理，它会缓存太多完全没有必要保留的数据。
    那么针对这种场景，其中一个可选的方案就是借助我们在上篇文章中学习的collectLatest函数。
    它的特性是，只接收处理最新的数据，如果有新数据到来了而前一个数据还没有处理完，则会将前一个数据剩余的处理逻辑全部取消。

    fun main() {
        runBlocking {
            flow {
                var count = 0
                while (true) {
                    emit(count)
                    delay(1000)
                    count++
                }
            }.collectLatest {
                println("start handle $it")
                delay(2000)
                println("finish handle $it")
            }
        }
    }

    通过日志打印我们发现，每条数据都是有输出的，但是每条数据都只输出了start部分，而finish部分则都没有输出。
    这就充分说明了collectLatest函数的特性，当有新数据到来时而前一个数据还没有处理完，则会将前一个数据剩余的处理逻辑全部取消。
    所以，finish部分的日志是永远得不到输出的。

    对于这种行为结果，我个人认为是有点反直觉的。
    我的第一直觉是，当前正在处理的数据无论如何都应该处理完，然后准备去处理下一条数据时，直接处理最新的数据即可，中间的数据就都可以丢弃掉了。
    如果这也正是你所期望的行为的话，那么恭喜你，conflate函数就是用来做这件事的。

    fun main() {
        runBlocking {
            flow {
                var count = 0
                while (true) {
                    emit(count)
                    delay(1000)
                    count++
                }
            }
            .conflate()
            .collect {
                println("start handle $it")
                delay(2000)
                println("finish handle $it")
            }
        }
    }
    可以看到，现在start日志和finish日志就会结对输出了。
    但是，有些数据则被完全丢弃掉了，比如说0、2、4都没有输出。因为当上一条数据处理完时，又有更新的数据发送过来了，那么这些过期的数据就会被直接舍弃

            --------------------------StateFlow和SharedFlow-----------------------------------
1.Flow的生命周期管理
       
    <activity代码> 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.text_view)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.timeFlow.collect { time ->
                    textView.text = time.toString()
                    Log.d("FlowTest", "Update time $time in UI.")
                }
            }
        }
    }
    
    <viewmodel代码>
     val timeFlow = flow {
        var time = 0
        while (true){
            emit(time)   //emit函数可以理解为一个数据发送器，它会把传入的参数发送到水管当中。
            delay(1000)
            time++
        }
    }
    
    运行程序
    一开始的时候，界面上计时器每更新一次，同时控制台也会打印一行日志，这还算是正常。
    可接下来，当我们按下Home键回到桌面后，控制台的日志依然会持续打印。好家伙，这还得了？
    这说明，即使我们的程序已经不在前台了，UI更新依然在持续进行当中。这是非常危险的事情，因为在非前台的情况下更新UI，某些场景下是会导致程序崩溃的。
    也就是说，我们并没有很好地管理Flow的生命周期，它没有与Activity的生命周期同步，而是始终在接收着Flow上游发送过来的数据。
    
    那这个问题要怎么解决呢？lifecycleScope除了launch函数可以用于启动一个协程之外，还有几个与Activity生命周期关联的launch函数可以使用。
    比如说，launchWhenStarted函数就是用于保证只有在Activity处于Started状态的情况下，协程中的代码才会执行。
    修改代码:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.text_view)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            lifecycleScope.launchWhenStarted {      //修改的地方
                mainViewModel.timeFlow.collect { time ->
                    textView.text = time.toString()
                    Log.d("FlowTest", "Update time $time in UI.")
                }
            }
        }
    }
    
    上面代码问题
    可以看到，这次当我们将程序切到后台的时候，日志就会停止打印，说明刚才的改动生效了。而当我们将程序重新切回到前台时，计时器会接着刚才切出去的时间继续计时。
    那么现在程序终于一切正常了吗？
    很遗憾，还没有。
    还有什么问题呢？上图其实已经将问题显现出来了。
    现在的主要问题在于，当我们将程序从后台切回到前台时，计时器会接着之前切出去的时间继续计时。
    这说明了什么？说明程序在后台的时候，Flow的管道中一直会暂存着一些的旧数据，这些数据不仅可能已经失去了时效性，而且还会造成一些内存上的问题。
    要知道，我们使用flow构建函数构建出的Flow是属于冷流，也就是在没有任何接受端的情况下，Flow是不会工作的。但是上述例子当中，即使程序切到了后台，
    Flow依然没有中止，还是为它保留了过期数据，这就是一种内存上的浪费。
    当然，我们这个例子非常简单，在实际项目中一个Flow可能又是由多个上游Flow合并而成的。
    在这种情况下，如果程序进入了后台，却仍有大量Flow依然处于活跃的状态，那么内存问题会变得更加严重。
    为此，Google推荐我们使用repeatOnLifecycle函数来解决这个问题，写法如下：
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.text_view)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {   //修改此处
                    mainViewModel.timeFlow.collect { time ->
                        textView.text = time.toString()
                        Log.d("FlowTest", "Update time $time in UI.")
                    }
                }
            }
        }
    }
    repeatOnLifecycle函数接受一个Lifecycle.State参数，
    这里我们传入Lifecycle.State.STARTED，同样表示只有在Activity处于Started状态的情况下，协程中的代码才会执行。
    可以看到，当我们将程序切到后台之后，日志打印就停止了。当我们将程序重新切回前台时，计时器会从零开始重新计时。  
    这说明什么？说明Flow在程序进入后台之后就完全停止了，不会保留任何数据。程序回到前台之后Flow又从头开始工作，所以才会从零开始计时。
    正确使用repeatOnLifecycle函数，这样才能让我们的程序在使用Flow的时候更加安全。


    


    
    
