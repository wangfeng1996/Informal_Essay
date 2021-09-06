# IntelliJ idea 安装与配置

## 开发工具的下载和安装

### 下载

+ 下载链接地址
  <u>https://www.jetbrains.com/idea/download/#section=mac</u>

+ 版本

  + 旗舰版（Ultimate）：

    > 旗舰版功能齐全，但是收费（30天试用期）有学生或者老师的邮箱可以免费申请一年，到期可以续

  + 社区版本（Community）

    > 社区版免费，功能不全。

+ 旗舰版安装的教程：

![](https://tva1.sinaimg.cn/large/008i3skNgy1gu6pil3l9zj61fj0u0n0m02.jpg)

​	下载好之后，直接安装即可，上面我是安装最新的版本，如果想要安装其他的版本，则选中其他的版本链接选择你想要的版本即可。

---

###  安装

+ 双击下载好的安装包

  ![](https://tva1.sinaimg.cn/large/008i3skNgy1gu6pozewxej607i07wmx702.jpg)

![](https://tva1.sinaimg.cn/large/008i3skNgy1gu6ppl3822j60km074glo02.jpg)

+ 将开发软件拖拽至"**Applications**"中

![](https://tva1.sinaimg.cn/large/008i3skNgy1gu6pqceplqj611g0pwmyt02.jpg)

+ 选择是否：导入IntelliJ IDEA设置（默认选择不导入设置）

![](https://tva1.sinaimg.cn/large/008i3skNgy1gu6prfuchnj611c0du3z902.jpg)

+ 设置主题

根据个人喜好选择、黑色显得比较炫酷一点，下方有两个选择（默认是选择的是黑色）

Skip Remaining and Set Defaults：跳过剩余部分，设置默认值

Next: Launcher Script：下一个:启动脚本

点击【Next: Launcher Script】- 下一页:启动脚本

![](https://tva1.sinaimg.cn/large/008i3skNgy1gu6pscaoacj60zc0u0wiw02.jpg)

+ 设置IEDA插件

设置IEDA中的各种插件，可以进行自定义设置、支持不同的开发或快速开发的一些插件。Preferences —> Plugins

```shell

1 Codota AI  代码智能提示插件
2 Key Promoter X 快捷键提示插件
3 CodeGlance 显示代码缩略图插件
4 lombok
5 Alibaba Java Coding Guidelines 阿里代码规范检查插件
6 CamelCase 驼峰命名和下划线命名转换
7 SonarLint 代码质量检查插件 
8 Save Actions 格式化代码插件
9 Grep Console 自定义控制台输出格式插件
10 MetricsReloaded 代码复杂度检查插件
11 Statistic 代码统计插件
12 Translation 翻译
13 Rainbow Brackets 彩虹括号插件
```

## 配置

### 全局配置

+ 优化导包配置（Editor--General--Auto+Import）

```shell
# 勾选下面两个
Add unambiguous imports on the fly (自动导包) 
Optimize imports on the fly (自动删除无用的包)
```

+ 取消tab页单行显示（Editor--General--Editor+Tabs）

```shell
Show tabs in one row (不勾)
multiple rows （钩上）多行显示更多的文件,方便查看

```

+ 双斜杠注释改成紧跟代码头(Editor--Code+Style--Java--code Generation)

```shell
Line Comment at first column(取消)
```

+ 5取消大小写匹配 (Preferences -- Editor -- General --Code Completion)

```sehll
Match case First letter only(勾选) --输入小写s也能提示String
```

+ 优化版本控制的目录颜色展示 (Preferences | Version Control)

```shell
Show directories with changed descendants (勾选)  代码改变时,目录颜色也跟着变化
```

+ 创建文件时自动生成作者和时间信息（Preferences | Editor | File and Code Templates｜includes）

```shell
/**
 * @author  wf
 * @date  ${DATE} ${TIME}
 */
```

+ 显示行号和方法分割线 （Preferences | Editor | General | Appearance）

```shell
Show line numbers(勾选显示行号) 
Show menthod separators(勾选显示方法分割线)
```



