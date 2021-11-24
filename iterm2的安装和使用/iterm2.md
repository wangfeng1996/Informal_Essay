

## iterm2

### 1、iterm2介绍

`Mac OS`自带的终端，用起来虽然有些不太方便，界面也不够友好,`iTerm2`是一款相对比较好用的终端工具.`iTerm2`常用操作包括主题选择、声明高亮、自动填充建议、隐藏用户名和主机名、分屏效果等，当然还有许多的插件和字体提供用户使用。

效果图如下：

<img src="https://tva1.sinaimg.cn/large/008i3skNgy1gu99ugwvoyj60wy0sy7a202.jpg" style="zoom: 50%;" />



### 2、下载及其安装

+ 使用Homebrew进行下载安装

```shell
brew install --cask iterm2
```

+ 去官网下载
  + 地址：https://iterm2.com/downloads.html
  + 下载的是压缩文件，解压后直接双击执行程序文件，或者直接将它拖到 Applications 目录下。

### 3、配置iterm2 主题

+ 主题地址：https://draculatheme.com  需要翻墙才能打开

+ GitHub地址 ：https://github.com/dracula/iterm.git

  #### 3.1 下载主题

  ```shell
  # 将主题下载到本地
  git clone https://github.com/dracula/iterm.git
  ```

  #### 3.2 配置主题

  打开 iTerm2，按 `Command` +` ，`键，打开打开 Preferences 配置界面，然后Profiles -> Colors -> Color Presets,在下拉列表中选择 Import，选择刚才clone下的文件夹里面的Dracula.itermcolors文件，导入成功后,在 Color Presets下选择 Dracula 主题，就可以了。

  <img src="https://tva1.sinaimg.cn/large/008i3skNgy1gu9aeiubt1j61vq0u0gpv02.jpg" style="zoom:50%;" />



<img src="https://tva1.sinaimg.cn/large/008i3skNgy1gu9agkanc6j61310u0q6o02.jpg" style="zoom: 40%;" />

### 4、设置iterm2背景图片

打开 iTerm2，按`Command` +`,`键，打开 Preferences 配置界面Profiles -> Window->Background mage,选择一张自己喜欢的背景图.（我选择的事默认的）

<img src="https://tva1.sinaimg.cn/large/008i3skNgy1gu9asgpcsij618q0u0wi802.jpg" style="zoom:40%;" />

### 5、配置 Oh My Zsh

#### 5.1 安装oh-my-zsh

#### 步骤一

> 如果步骤一不存在，则执行步骤二的内容

+ 查看shell

```shell
s

# 输出

/bin/bash
/bin/csh
/bin/dash
/bin/ksh
/bin/sh
/bin/tcsh
/bin/zsh
```

+ 查看当前窗口使用的shell版本

```shell
echo $SHELL

# 输出
/bin/zsh
```

#### 步骤二

+ 安装并设置zsh

```shell
brew install zsh
```

+ 要将zsh设置为默认shell

```shell
chsh -s /usr/local/bin/zsh
```

+ 安装Oh My Zsh:
  + GitHub地址：https://github.com/ohmyzsh/ohmyzsh/wiki
  + 安装方法

  | **方式** | **命令**                                                     |
  | -------- | ------------------------------------------------------------ |
  | `curl`   | sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)" |
  | `wget`   | sh -c "$(wget -O- https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)" |
  | `fetch`  | sh -c "$(fetch -o - https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)" |

  + 本地安装方法

	```shell
	git clone https://github.com/ohmyzsh/ohmyzsh.git ~/.oh-my-zsh
	```

#### 5.2 配置oh-my-zsh

+ 备份您现有的`~/.zshrc`文件

```shell
cp ~/.zshrc ~/.zshrc.orig
```

+  创建一个新的zsh配置文件

可以通过复制我们包含的模板来创建一个新的zsh配置文件。

```shell
cp ~/.oh-my-zsh/templates/zshrc.zsh-template ~/.zshrc
```

+ 初始化新zsh配置

> 一旦您打开一个新的终端窗口，它应该使用Oh My Zsh的配置加载zsh。

#### 5.3 卸载oh-my-zsh

如果您想卸载`oh-my-zsh`，只需从命令行运行`uninstall_oh_my_zsh`。它将删除自己，并恢复您之前的`bash`或`zsh`配置。

#### 5.4 插件

插件下载目录：`.oh-my-zsh/custom/plugins`

5.4.1 高亮显示

+ 方式一

  + 使用homebrew进行安装

  ```shell
  brew install zsh-syntax-highlighting
  ```

  + 安装成功之后，编辑vim ~/.zshrc文件，在最后一行增加下面配置：

  ```shell
  source /usr/local/share/zsh-syntax-highlighting/zsh-syntax-highlighting.zsh
  ```

  + 重新启动zsh（例如打开终端模拟器的新实例）

+ 方式二

  + 执行下面指令自动安装

  ```shell
  git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting
  ```

  + 然后编辑vim ~/.zshrc文件， 找到plugins=(git)这一行，如果没有添加。更改为如下

	```shell
	plugins=(git zsh-syntax-highlighting)
	```

  + 重新启动zsh（例如打开终端模拟器的新实例）

5.4.2 自动补全插件

  + 下载插件

  ```shell
  git clone git://github.com/zsh-users/zsh-autosuggestions $ZSH_CUSTOM/plugins/zsh-autosuggestions
  ```

  + 编辑.zshrc文件
    找到plugins=(git)这一行，如果没有添加。更改为如下
  ```shell
  plugins=(git zsh-autosuggestions)
  ```
  + 重新启动zsh（例如打开终端模拟器的新实例）。

<img src="https://tva1.sinaimg.cn/large/008i3skNgy1guac26zw8yj61950u0q8r02.jpg" style="zoom:40%;" />

### 6、设置vim可配色

终端输入 `vim .vimrc`
设置如下

```shell
 syntax on
 set number
 set ruler
 
```

### 7、iterm2 快捷键


```shell

command + enter 进入与返回全屏模式
command + t 新建标签
command + w 关闭标签
command + 数字 command + 左右方向键    切换标签
command + enter 切换全屏
command + f 查找
command + d 水平分屏
command + shift + d 垂直分屏
command + option + 方向键 command + [ 或 command + ]    切换屏幕
command + ; 查看历史命令
command + shift + h 查看剪贴板历史
ctrl + u    清除当前行
ctrl + l    清屏
ctrl + a    到行首
ctrl + e    到行尾
ctrl + f/b  前进后退
ctrl + p    上一条命令
ctrl + r    搜索命令历史
```

