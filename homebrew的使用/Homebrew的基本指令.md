# homebrew几个常用命令

+ 查看homebrew版本
```
brew -v
```
+ 查看已安装的包

```
brew list
```
+ 安装包
```
brew install packageName
```
+  卸载包
```
brew uninstall packageName
```
+  查找包
```
brew searck packageName
```
+  查看包信息
```
brew info packageName
```
+  更新homebrew
```
brew update
```
+ 诊断homebrew

```
brew doctor
```
+ 查看帮助信息
```shell
brew -h
```

+ 清理旧版本

```shell
brew cleanup
```

+ 查看包的信息

```shell
brew info 包名
```

+ ##### 服务管理

  - 查看服务列表
    `brew services list`
  - 启动服务
    `brew services start 服务名`
  - 停止服务
    `brew services stop 服务名`
  - 重启服务
    `brew services restart 服务名`
  - 清除已卸载应用无用的配置
    `brew services cleanup`

+ 更新包
```
  brew upgrade 			全部更新
  brew upgrade 包名 指定包
```
+ 查询可更新的包 

```
brew outdated
```

+ 安装 macOS 应用程序、字体和插件以及其他非开源软件。

```shell
brew install --cask 软件包
```

+ 卸载 macOS 应用程序、字体和插件以及其他非开源软件。

```shell
brew uninstall --cask 软件包
```

+ 列出当前的存储库

```shell
brew tap
```

+ 更新新的brew 仓库源

```shell
brew tap <gihhub_user/repo>
# 例如
brew tap homebrew/cask-versions
```
