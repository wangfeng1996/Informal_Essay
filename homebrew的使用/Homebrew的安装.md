#  Homebrew

[TOC]

###  一、Homebrew是什么

官网：[https://brew.sh/index_zh-cn](https://brew.sh/index_zh-cn)

![](https://tva1.sinaimg.cn/large/008i3skNgy1gtyvzwg7vcj61am0ioabc02.jpg)

​																		homebrew

---

Homebrew是一款Mac OS平台下的软件包管理工具，拥有安装、卸载、更新、查看、搜索等很多实用的功能。简单的一条指令，就可以实现包管理，而不用你关心各种依赖和文件路径的情况，十分方便快捷。



引用[官方](https://brew.sh)的一句话：Homebrew —— OS X 不可或缺的套件管理器。



### 二、Homebrew的安装

#### 1、要求

+ 64-bit Intel CPU 或者 Apple Silicon CPU(苹果自己研发的CPU)
+ macOS版本为Mojave（10.14）或者以上
+ Xcode命令行工具（安装Command Line Tools）

```shell
xcode-select --install    
```

+ 支持shell (zsh或者bash) 在macOS 10.15 之后 默认shell是zsh

```shell
# 查看当前使用的shell
echo $SHELL
# 查看所有的shell
cat /etc/shells
# 修改默认shell为zsh
chsh -s /bin/bash
```

+ homebrew安装目录

Homebrew 会将软件包安装到独立目录，并将其文件软链接至 `/usr/local` 

#### 2、安装和卸载

+ 安装

```shell
# 拉脚本
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
# 检查brew是否安装成功
brew help
# 替换brew.git
cd "$(brew --repo)"
git remote set-url origin https://mirrors.ustc.edu.cn/brew.git
# homebrew-core.git
cd "$(brew --repo)/Library/Taps/homebrew/homebrew-core"
git remote set-url origin https://mirrors.ustc.edu.cn/homebrew-core.git
# 替换Bottles源

# zsh
echo 'export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.ustc.edu.cn/homebrew-bottles' >> ~/.zshrc
# 全局配置
source ~/.zshrc

# bash
echo 'export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.ustc.edu.cn/homebrew-bottles' >> ~/.bash_profile
source ~/.bash_profile

# 更新brew
brew update
```

+ 卸载

```shell
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/uninstall.sh)"

```

### 3、安装出现的问题

#### 问题一

下面是下载brew之后的一个报错问题

```shell
error:
curl: (7) Failed to connect to raw.githubusercontent.com port 443: Connection refused
   查了好多治疗，发现都是copy的，根本解决不了，然后，找到了一个算是
脚本的东西，我将她上传至github上面了，直接clone就可以。
https://github.com/jackzhaoyu/ceshi.git
```

下载下来的rb文件，你可以随便放，但是你能找到就可以。首先你先执行curl 看是不是有这句话

```shell
curl
```

执行完毕后会提示

```shell
curl: try 'curl --help' or 'curl --manual' for more information
```

没有问题的话，然后cd到你保存rb文件的那个目录，然后执行下面的命令

```shell
ruby ***********.rb
```

执行完毕后，回报的结果

![](https://tva1.sinaimg.cn/large/008i3skNgy1gu205ulqzaj60u00lq43u02.jpg)

然后执行brew，就可以了

![](https://tva1.sinaimg.cn/large/008i3skNgy1gu20k49v6qj60vq0jotaj02.jpg)

