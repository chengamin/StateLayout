# StateLayout 
> StateLayout属于Android多状态管理布局,比如某一个页面,我们需要对不同状态下的页面进行更改,比如:加载,空数据,数据加载错误以及数据加载成功等等.
但是我们不能在一个xml页面中,对每个状态进行管理,那样不仅费时、费力,更属于重复不必要的操作,因为我们将代码进行向上抽取,抽取为一个自定义ViewGroup.
另外,对于每个不同的加载样式,我们向外进行了暴露,可以加载用户高度自定义的样式.


## 依赖引入
### Step1. Add it in your root build.gradle at the end of repositories:
  ```Project Gradle代码
    allprojects {
	repositories {
	     ...
	     maven { url 'https://jitpack.io' }
	}
   }
  ```
### Step2. Add the Module dependency
  ```Module Gradle代码
    dependencies {
	 implementation 'com.github.chengamin:StateLayout:1.0'
    }
  ```
## 代码调用
