# 实验三

## 实验内容
一、Android ListView的用法
二、创建自定义布局的AlertDialog
三、使用XML定义菜单
四、创建上下文操作模式（ActionMode）的上下文菜单

## 实验步骤

### 一、Android ListView的用法

列表中的列表项采用的是线性布局（本次使用，其他布局也可）item：

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="70dp"
    android:orientation="horizontal">
    <TextView
        android:id="@+id/name"
        android:layout_width="wrap_content"
        android:text="Lion"
        android:textColor="@color/black"
        android:gravity="center_vertical"
        android:layout_weight="1"
        android:textSize="20dp"
        android:textFontWeight="600"
        android:layout_marginLeft="20dp"
        android:layout_height="70dp"></TextView>

    <ImageView
        android:id="@+id/img"
        android:layout_gravity="center_vertical"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:layout_marginRight="20dp"></ImageView>
</LinearLayout>
```

MainActivity中采用SimpleAdapter（简单适配器）来提供列表项，其中点击onItemClick点击方法使用Toast弹窗提示：

```
package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView lv = findViewById(R.id.lv);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","Lion");
        map.put("img",R.drawable.lion);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","Tiger");
        map.put("img",R.drawable.tiger);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","Monkey");
        map.put("img",R.drawable.monkey);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","Dog");
        map.put("img",R.drawable.dog);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","Cat");
        map.put("img",R.drawable.cat);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","Elephant");
        map.put("img",R.drawable.elephant);
        list.add(map);

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.item,
                new String[]{"name","img"},
                new int[]{R.id.name,R.id.img}
        );

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,"点击"+list.get(i).get("name"),Toast.LENGTH_SHORT).show();
    }
}
```

其中点击item项变色，实现采用在drawable文件夹中新建item_select.xml选择器，当点击时颜色变换：


```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@color/transparent" android:state_pressed="false"/>
    <item android:drawable="@color/choose" android:state_pressed="true"/>
</selector>
```

实验结果截图：

[![IuidBQ.png](https://z3.ax1x.com/2021/11/05/IuidBQ.png)](https://imgtu.com/i/IuidBQ)
[![Iuiw7j.png](https://z3.ax1x.com/2021/11/05/Iuiw7j.png)](https://imgtu.com/i/Iuiw7j)

### 二、创建自定义布局的AlertDialog

对于类似登录界面，个人习惯使用约束布局，方便调整：

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/title"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:text="ANDROID APP"
        tools:ignore="MissingConstraints"
        android:background="#febb32"
        android:textColor="@color/white"
        android:textSize="25dp"
        android:textFontWeight="1000"
        android:gravity="center"></TextView>

    <EditText
        android:layout_marginTop="10dp"
        android:id="@+id/username"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:hint="Username"
        android:gravity="center_vertical"
        app:layout_constraintTop_toBottomOf="@+id/title"
        tools:ignore="MissingConstraints" />

    <EditText
        android:id="@+id/password"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:hint="Password"
        android:gravity="center_vertical"
        app:layout_constraintTop_toBottomOf="@+id/username"
        tools:ignore="MissingConstraints"></EditText>

    <androidx.appcompat.widget.LinearLayoutCompat
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        tools:ignore="MissingConstraints"
        android:orientation="horizontal"
        app:layout_constraintTop_toBottomOf="@id/password">
        <Button
            android:layout_weight="1"
            android:id="@+id/btn_sign_in"
            android:layout_width="150dp"
            android:layout_height="50dp"
            android:text="Sign in"
            android:gravity="center"
            android:textColor="@color/black"
            android:backgroundTint="@color/white"
            tools:ignore="MissingConstraints"></Button>
        <Button
            android:layout_weight="1"
            android:layout_marginLeft="5dp"
            android:id="@+id/btn_cancel"
            android:layout_width="150dp"
            android:layout_height="50dp"
            android:text="Cancel"
            android:gravity="center"
            android:textColor="@color/black"
            android:backgroundTint="@color/white"
            tools:ignore="MissingConstraints"></Button>

    </androidx.appcompat.widget.LinearLayoutCompat>
</androidx.constraintlayout.widget.ConstraintLayout>
```

在MainActivity中直接setView展示界面：

```
package com.example.test3_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 获取布局
        View view2 = View.inflate(MainActivity.this, R.layout.login, null);
        // 获取布局中的控件
        final EditText username = (EditText) view2.findViewById(R.id.username);
        final EditText password = (EditText) view2.findViewById(R.id.password);
        final Button button1 = (Button) view2.findViewById(R.id.btn_cancel);
        final Button button2 = (Button) view2.findViewById(R.id.btn_sign_in);
        // 设置参数
        builder.setIcon(R.drawable.ic_launcher)
                .setView(view2);
        // 创建对话框
        final AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}
```

实验结果截图：

[![Iuiang.png](https://z3.ax1x.com/2021/11/05/Iuiang.png)](https://imgtu.com/i/Iuiang)

### 三、使用XML定义菜单

 一般推荐使用XML资源文件来定义菜单，这种方式可以提高更好的解耦。
 菜单资源文件通常应该放在/res/menu目录下，菜单资源的根元素通常是<menu.../>元素，<menu.../>元素无须指定任何属性。<menu.../>元素内可包含如下子元素。
一旦在程序中定义了菜单资源后，接下来还是重写onCreateOptionsMenu（用于创建选项菜单）、onCreateContextMenu（用于创建上下文菜单）方法，在这些方法中调用MenuInflater对象的inflate方法装载指定资源对应的菜单即可。

```
package com.example.test3_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3_3.R;

public class MainActivity extends AppCompatActivity {
    //    字体大小
    private static final int FONT_S = 0x111;
    private static final int FONT_M = 0x112;
    private static final int FONT_L = 0x113;
    //    字体颜色
    private static final int FONT_RED = 0x114;
    private static final int FONT_BLACK = 0x115;
    //    普通菜单项
    private static final int PLAIN_ITEM = 0x116;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.txt);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        SubMenu fontMenu =menu.addSubMenu("字体大小");
        fontMenu.add(0,FONT_S,0,"小号字体");
        fontMenu.add(0,FONT_M,0,"中号字体");
        fontMenu.add(0,FONT_L,0,"大号字体");
        menu.add(0,PLAIN_ITEM,0,"普通菜单项");
        SubMenu colorMenu =menu.addSubMenu("字体颜色");
        colorMenu.add(0,FONT_RED,0,"红色");
        colorMenu.add(0,FONT_BLACK,0,"黑色");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()){
            case FONT_S:text.setTextSize(25); break;
            case FONT_M:text.setTextSize(40); break;
            case FONT_L:text.setTextSize(55); break;
            case PLAIN_ITEM:
                Toast.makeText(MainActivity.this,"普通菜单项",Toast.LENGTH_SHORT).show(); break;
            case FONT_RED:text.setTextColor(getResources().getColor(R.color.red)); break;
            case FONT_BLACK:text.setTextColor(getResources().getColor(R.color.black)); break;
        }
        return true;
    }
}

```

实验结果截图：

[![IuiBAs.png](https://z3.ax1x.com/2021/11/05/IuiBAs.png)](https://imgtu.com/i/IuiBAs)
[![IuiNjS.png](https://z3.ax1x.com/2021/11/05/IuiNjS.png)](https://imgtu.com/i/IuiNjS)
[![Iuitc8.png](https://z3.ax1x.com/2021/11/05/Iuitc8.png)](https://imgtu.com/i/Iuitc8)

### 四、创建上下文操作模式（ActionMode）的上下文菜单

所谓“上下文”是指操作与某个列表项相关的，而非整个屏幕相关联的。
上下文操作主要是通过上下文操作栏呈现的，它位于activity的Toolbar之上，上下文操作栏为用户提供了各种操作
先使用ListView创建List，再为ListItem创建ActionMode形式的上下文菜单，设置响应即刻实现
上方选项菜单的有关设置：

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/menu_delete"
        app:showAsAction="always"
        android:icon="@drawable/dustbin"
        android:title="@string/delete" />
</menu>
```

自定义适配器Adapter：

```
public class Adapter extends BaseAdapter {

    List<Item> list;//item的list对象
    Context context;//上下文对象

    //初始化
    public Adapter(List<Item> list, Context context) {
        this.context = context;
        this.list = list;
        //列表同步方法
        notifyDataSetChanged();
    }

    //得到当前列表的选项数量
    public int getCount() {
        return list.size();
    }

    //根据下标得到列表项
    public Item getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        //如果还没加载
        if(convertView==null){
            //加载布局文件，并将各个选项以及每个选项中的内容一一对应
            convertView=View.inflate(context, R.layout.simple_item, null);
            viewHolder=new ViewHolder();
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.picture);
            viewHolder.textView=(TextView) convertView.findViewById(R.id.item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }

        //得到十六进制的颜色的int值
        int blue = Color.parseColor("#33B5E5");
        int white = Color.parseColor("#FFFFFF");
        viewHolder.textView.setText(list.get(position).getName());
        //如果被选中，那么改变选中颜色
        if(list.get(position).isBo() == true){
            viewHolder.textView.setBackgroundColor(blue);
            viewHolder.imageView.setBackgroundColor(blue);
        }
        else {
            viewHolder.textView.setBackgroundColor(white);
            viewHolder.imageView.setBackgroundColor(white);
        }
        return convertView;

    }

    //创建内部类，定义每一个列表项所包含的东西，这里是每个列表项都有一个imageView和textView。
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
```

在MainActivity.java中，采用onItemCheckedStateChanged()方法
以及ActionMode.Callback接口中的各类方法实现要求的功能：

```
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Item> list;

    private BaseAdapter adapter;
    private String [] name = {"One","Two","Three","Four","Five"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.mylist);
        list = new ArrayList<Item>();
        //定义item并且加入list中
        for(int i = 0; i < name.length; i++){
            list.add(new Item(name[i], false));
        }
        //对listview进行适配器适配
        adapter = new Adapter(list, MainActivity.this);
        listView.setAdapter(adapter);

        //设置listview允许多选模式
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
            //选中数量
            int num = 0;
            /*
             * 参数：ActionMode是长按后出现的标题栏
             *        positon是当前选中的item的序号
             *    id 是当前选中的item的id
             *    checked 如果是选中事件则为true，如果是取消事件则为false
             */
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                // 调整选定条目
                if (checked == true) {
                    list.get(position).setBo(true);
                    //实时刷新
                    adapter.notifyDataSetChanged();
                    num++;
                } else {
                    list.get(position).setBo(false);
                    //实时刷新
                    adapter.notifyDataSetChanged();
                    num--;
                }
                // 用TextView显示
                mode.setTitle("  " + num + " Selected");
            }

            /*
             * 参数：ActionMode是长按后出现的标题栏
             *        Menu是标题栏的菜单内容
             */
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // 设置长按后所要显示的标题栏的内容
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu, menu);
                num = 0;
                adapter.notifyDataSetChanged();
                return true;
            }
            /*
             * 可在此方法中进行标题栏UI的创建和更新
             */
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

                adapter.notifyDataSetChanged();
                return false;
            }
            public void refresh(){
                for(int i = 0; i < name.length; i++){
                    list.get(i).setBo(false);
                }
            }
            /*
             * 可在此方法中监听标题栏Menu的监听，从而进行相应操作
             * 设置actionMode菜单每个按钮的点击事件
             * 这里我只设置了删除
             */
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    //删除
                    case R.id.menu_delete:
                        adapter.notifyDataSetChanged();
                        num = 0;
                        refresh();
                        mode.finish();// 由于题目的要求主要是上下文菜单关联模式的实现，没有继续扩展相应的方法，将菜单按钮设置为返回，结束多选模式
                        return true;
                    default:
                        refresh();
                        adapter.notifyDataSetChanged();
                        num = 0;
                        return false;
                }
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                refresh();
                adapter.notifyDataSetChanged();
            }
        });
    }
}
```

实验截图：

[![Iuirhq.png](https://z3.ax1x.com/2021/11/05/Iuirhq.png)](https://imgtu.com/i/Iuirhq)
[![IuiDNn.png](https://z3.ax1x.com/2021/11/05/IuiDNn.png)](https://imgtu.com/i/IuiDNn)