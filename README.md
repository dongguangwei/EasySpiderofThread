# EasySpiderofThread
第一个小型多线程爬虫

#介绍

        实现了多线程爬取爬取新浪 深度为两层（可以实现更多 只需要在DEMO.java中修改layer的值），运用了多线程 httpclient。

        整个项目分成了四个包

        第一个包 Deal包 该包只有dealWeb类用于处理整个网页的信息 将网页中的url通过正则表达式提取出来

        第二个包 Download包 该包只有httpdownload类通过httpclient下载网页将得到的网页传给Deal包中dealWeb类进行处理

        第三个包 MenUrl包 该包有三个类(NewUrl类 OldUrl类 UrlKey类)

                NewUrl类通过队列将没有爬取的url存储在这里(存储形式为数据类型 url-key)
                OldUrl类通过set将爬取过的url存储在这里
                UrlKey类中有两个数据类型 一个为String 另一个为int存储url int存储当前url的深度

        第四个包 MainClass包 该包有两个类(DEMO类 Main类)

                Main类 该类实现了整个爬虫的配置包括爬虫深度 NewUrl对象 OldUrl对象 多线程
                DEMO类 该类为测试类
      

#事项过程

        www.sina.com为爬虫入口 先将该url添加到NewUrl中进行管理 开启多线程 线程先对NewUrl进行判断是否用url-key 
        如果有则将url跟key进行提取进而运行httpdownload进行网页的爬取，如果NewUrl为空则将该线程置为等待直到在一
        次唤醒。
        在httpdownload中设置使用get方法并且将爬虫伪装成流浪其对网页进行下载，如果得到则将网页内容提交给dealWeb处
        理并放入OldUrl，如果连接超时 返回404等问题则直接结束对这一url的内容下载。
        在dealWeb中将得到网页内容通过正则表达式提取出url并放入NewUrl中，进行一次判断
                如果等待的线程大于0则唤醒线
        整个程序的最后进行一次判断
                如果等到的线程数等于最大运行线程数则退出整个程序 或者 NewUrl为空并且Thread.activeCount()等于1则堆出程序
	
	
