# git

## git更新项目、推送项目

+ 进入你要推送的文件夹，在此处进行初始化的操作

```shell
1| git init
```

+ git 下载自己的项目到本地

```shell
1|  git pull https://github.com/xxxx/xxxx （远程仓库url）
```

+ 这个时候第一次push需要添加远程仓库

```shell
1| git remote add <默认origin> <远程仓库url>     本地默认是origin 
```

+ 然后就可以添加、提交、推送文件

```shell
1|git add --all
2|git commit -m "提交信息" 
3|git push -u <本地仓库name> <远程分支名>   # 本地仓库名默认：origin  远程分支名默认：master
```

+ 然后下一次就不用这么麻烦了，直接下面的操作

```shell
1|git add 文件名
2｜git commit -m "描述的信息"
3｜git push
```



