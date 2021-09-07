# git的使用

### 在git中设置用户名

Git 使用用户名将提交与身份关联。 Git 用户名与您的 GitHub 用户名不同。

#### 为计算机的每个仓库设置git的用户名

+ 打开 Terminal（终端）
+ 设置git的用户名：

```shell
git config --global user.name "wangfeng"
```

+ 确认您正确设置的git用户名

```shell
git config --global user.name
> wangfeng
```

