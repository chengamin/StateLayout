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
## 代码使用
### Step1. 在对应布局的xml文件中进行引用
  ```对应布局XML代码
    <com.jh.statelayoutlibrary.StateLayout
        android:id="@+id/stateLayout"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/lin_top"
        >

        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:text="Hello World!"
            android:gravity="center"
            />

    </com.jh.statelayoutlibrary.StateLayout>
  ```
### Step2. java文件中引用
  ```
  	private StateLayout stateLayout = null;
	
	stateLayout = findViewById(R.id.stateLayout);
	// 使用作者自己定义的布局管理器
	stateLayout.setStateLayoutManager();
	// 自定义自己的布局管理器,继承自StateLayoutManager
        // stateLayout.setStateLayoutManager(new CustomLayoutManager(this));
  	// 切换状态
	stateLayout.setState(State.LOADING,null);
  ```
### Step3. 自定义StateLayoutMnager
 ```
 public class CustomLayoutManager extends StateLayoutManager {

    public CustomLayoutManager(Context mContext) {
        super(mContext);
    }

    @Override
    protected int loadingLayout() {
        return R.layout.layout_loading;
    }

    @Override
    protected void showLoadingAfter(View parentView) {

    }

    @Override
    protected int emptyLayout() {
        return R.layout.layout_empty;
    }

    @Override
    protected void showEmptyAfter(View parentView) {

    }

    @Override
    protected View.OnClickListener clickEmptyListener() {
        return null;
    }

    @Override
    protected int errorLayout() {
        return R.layout.layout_error;
    }

    @Override
    protected void showErrorAfter(View parentView, String errorMsg) {

    }

    @Override
    protected View.OnClickListener clickErrorListener() {
        return null;
    }

    @Override
    protected void showSuccessAfter(View parentView) {

    }
}
 
 ```

