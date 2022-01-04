<img src="https://tva1.sinaimg.cn/large/008i3skNgy1gu881rdku0j60ji06474902.jpg" style="zoom:50%;" />

---

# git笔记

## 1、版本管理工具概念

>版本控制工具：能够记录我们对文档的所有版本，记录所有修改内容的一系列软件

+ 版本管理工具一般具有如下特性:

  ```shell
  1) 能够记录历史版本,回退历史版本
  2) 团队开发,方便代码合并
  ```


## 2、版本管理工具的介绍

> 现在比较流行的版本管理工具是git ,但是实际上git 是近几年才发展起来的,可能有一些老的项目,还在用一些老的软件,比如svn
>

### 2.1 SVN

+ 工作流程

```
SVN是集中式版本控制系统，版本库是集中放在中央服务器的.
工作流程如下:
	1.从中央服务器远程仓库下载代码
	2.修改后将代码提交到中央服务器远程仓库
```

+ 优缺点:

```
 优点: 简单,易操作
 缺点:所有代码必须放在中央服务器  
  	   1.服务器一旦宕机无法提交代码,即容错性较差
       2.离线无法提交代码,无法及时记录我们的提交行为
```

svn流程图 

![](https://tva1.sinaimg.cn/large/008i3skNgy1gu88ebeyibj60bf08974902.jpg)

### 2.2 Git

+ 工作流程

Git是分布式版本控制系统（Distributed Version Control System，简称 DVCS），分为两种类型的仓库：
本地仓库和远程仓库
工作流程如下
    1．从远程仓库中克隆或拉取代码到本地仓库(clone/pull)
    2．从本地进行代码修改
    3．在提交前先将代码提交到暂存区
    4．提交到本地仓库。本地仓库中保存修改的各个历史版本
    5．修改完成后，需要和团队成员共享代码时，将代码push到远程仓库

![](https://tva1.sinaimg.cn/large/008i3skNgy1gu88fwqgxvj60d90d2dgd02.jpg)

`tip`

+ svn 是集中式版本控制工具,git 是分布式版本控制工具
+  svn 不支持离线提交,git 支持离线提交代码



## git安装（使用Homebrew安装）

+ 下载地址：https://git-scm.com/download
+ 安装命令
```shell
brew install git
```
+ 查看git版本号

```shell
git --verison

# 结果
git version 2.33.0
```

+ 查看是否安装成功  直接输入git即可

## 常用远程仓库托管服务

除了自己搭建服务器,其实我们可以使用一些免费的远程仓库,远程仓库有很多,常见的免费互联网远程仓库托管服务如下:

```
www.github.com
www.gitee.com
www.gitlab.com

github  是一个基于git实现在线代码托管的仓库，向互联网开放，企业版要收钱。
gitee    即码云，是 oschina 免费给企业用的，不用自己搭建环境。
gitlab   类似 github，一般用于在企业内搭建git私服，要自己搭环境。

GitHub(gitee)、GitLab 不同点：
1、GitHub如果使用私有仓库是需要付费的，(2019年开始私有仓库也是免费的但是只能3个人协同开发,想要更多需要收费)，GitLab可以在上面搭建私人的免费仓库。
2、GitLab让开发团队对他们的代码仓库拥有更多的控制，相对于GitHub，它有不少的特色：
    (1)允许免费设置仓库权限
    (2)允许用户选择分享一个project的部分代码
    (3)允许用户设置project的获取权限，进一步提升安全性
    (4)可以设置获取到团队整体的改进进度
    (5)通过innersourcing让不在权限范围内的人访问不到该资源

```

鉴于国内用户可能网络不好,这里我们使用gitee(码云) 来讲解我们的课程,其他可自行找资料学习非常类似

## ssh 连接概述

`实际上git 不仅仅支持用户名密码方式的配置,可以有另外一种相对更加安全的配置即ssh 方式配置`

+  ssh 方式的底层原理

```sell
ssh连接地城是RAS加密算法,又称非对称加密,是一种现在公认的最安全的加密方式
https://www.cnblogs.com/cjm123/p/8243424.html

公钥私钥加密可以看作古代 的"虎符" , 我们本地电脑有一份,远程服务器有一份, 只要 "虎符" 核对通过 表示身份无误,可以执行提交等操作,无需输入用户名密码
```

+  ssh 密钥的生成

```she
#生成公钥私钥
 ssh-keygen -t rsa
 一直回车即可
 会默认用户目录 .ssh 目录生成一个默认的id_rsa文件 和id_rsa.pub
```

+ 密钥配置

将`id_rsa.pub`文件中的哪用放到gitee或者github的`设置--ssh公钥`中

# # 命令行-- git基本操作

#### 环境配置

当安装Git后首先要做的事情是设置用户名称和email地址。这是非常重要的，因为每次Git提交都会使用该用户信息

#### 为计算机的每个仓库设置git的用户名

+ 打开 Terminal（终端）
+ 设置git的用户名：

```shell
git config --global user.name "xxxxxxxxxx"
git config --global user.email “xxxxxxxxxx”
```

+ 确认您正确设置的git用户名

```shell
git config --global user.name
> wangfeng
```

+ 查看配置信息

```shell
 git config --list
 
 #通过上面的命令设置的信息会保存在~/.gitconfig文件中
```

## 命令行 

### 初始化本地仓库 init

```shell
# 初始化仓库带工作区
git init
# 初始化仓库不带工作区
git init --bare  
```

### 克隆 clone

```shell
# 从远程仓库克隆
git clone 远程Git仓库地址 
例如: git clone https://gitee.com/xxxx/xxx.git
```
### 查看状态 status

```shell
# 查看状态
git status 
#查看状态 使输出信息更加简洁
git status –s 
```
### add

```shell
# 将未跟踪的文件加入暂存区
git add  <文件名> 
# 将暂存区的文件取消暂存 (取消 add )
git reset  <文件名> 

```
### 查看commit的节点
```shell
git log
```


### 本地回滚命令
```shell
git reset --hard commit节点
```

### commit

```shell
# git commit 将暂存区的文件修改提交到本地仓库
git commit -m "日志信息"  <文件名>  
```
### 删除 rm

```shell
# 从本地工作区 删除文件
git rm <文件名>  
# 如果本工作区库误删, 想要回退
git checkout head <文件名>  
```

## 常用命令

![](https://tva1.sinaimg.cn/large/008i3skNgy1gu87mxirk6j619l0u0tgq02.jpg)

