# 实验1


## 一、实验内容

1、安装Android Studio
2、创建一个Android工程并同步至Github

## 二、实验步骤

### 1、安装Android Studio

Android Studio官网 https://developer.android.google.cn/studio 下载即可
在安装Android Studio之前，确保已经安装好了Java JDK
JDK安装可查看Java 开发环境配置 https://www.runoob.com/java/java-environment-setup.html （转载）
需要注意的是，在Android Studio安装过程中，不能出现空格或中文路径名

### 2、创建一个Android工程并同步至Github

（1）、创建一个Android工程

1）、通过File->New->New Project新建项目

[![InBmEF.png](https://z3.ax1x.com/2021/11/05/InBmEF.png)](https://imgtu.com/i/InBmEF)

2）、选择Empty Activity创建空白项目

[![InBZHU.png](https://z3.ax1x.com/2021/11/05/InBZHU.png)](https://imgtu.com/i/InBZHU)

3）、命名完成后即可打开项目（需下载手机虚拟机或外连手机）

[![InBBvt.png](https://z3.ax1x.com/2021/11/05/InBBvt.png)](https://imgtu.com/i/InBBvt)

（2）将工程上传至Github
1）创建github账户并新建仓库
2）安装下载好Git 详细可查看 https://blog.csdn.net/qq_35206244/article/details/97698815 （转载）
3）选择本地仓库，右键选择Git Bash Here
4) 依次输入命令git add . / git commit -m "log"(其中log为注释信息) / git push -u origin main
[![InDCVO.png](https://z3.ax1x.com/2021/11/05/InDCVO.png)](https://imgtu.com/i/InDCVO)
[![InDpqK.png](https://z3.ax1x.com/2021/11/05/InDpqK.png)](https://imgtu.com/i/InDpqK)
[![InDiIe.png](https://z3.ax1x.com/2021/11/05/InDiIe.png)](https://imgtu.com/i/InDiIe)
[![InDPaD.png](https://z3.ax1x.com/2021/11/05/InDPaD.png)](https://imgtu.com/i/InDPaD)
5) 上传成功后即可在github查看

## 三、实验反思
其中在Git上传时出现报错error: src refspec master does not match any error: failed to push some refs to ‘github.com:github
具体解决方法可查看 https://blog.csdn.net/sfgsdfg2516/article/details/120579313?spm=1001.2014.3001.5501 （原创）