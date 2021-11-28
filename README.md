# 实验三

## 实验内容
阅读NotePad的源代码并做如下扩展：
• 基本要求：每个人必须完成
• NoteList中显示条目增加时间戳显示
• 添加笔记查询功能（根据标题查询）
• 附加功能：根据自身实际情况进行扩充（至少两项），以下是建议的扩展功能
• UI美化
• 更改记事本的背景
• 导出笔记，笔记的云备份和恢复
• 添加代办功能
• 记事本的设置的功能
• 笔记分类
• 支持多种资源，如保存图片、语音、视频等（参考印象笔记）
• 语音搜索？
• 笔记便签
• 偏好设置
• 。。。。。。

## 实验步骤

### 一、最终功能展示

1、新建日记功能


[![onD2Us.png](https://z3.ax1x.com/2021/11/28/onD2Us.png)](https://imgtu.com/i/onD2Us)


2、插入新日记功能


[![onDR5n.png](https://z3.ax1x.com/2021/11/28/onDR5n.png)](https://imgtu.com/i/onDR5n)


3、编辑日记（包括删除日记）


[![oVMPnH.jpg](https://z3.ax1x.com/2021/11/26/oVMPnH.jpg)](https://imgtu.com/i/oVMPnH)


5、修改标题


[![onDgEj.png](https://z3.ax1x.com/2021/11/28/onDgEj.png)](https://imgtu.com/i/onDgEj)


6、时间戳添加


[![oVMS1O.jpg](https://z3.ax1x.com/2021/11/26/oVMS1O.jpg)](https://imgtu.com/i/oVMS1O)


7、搜索功能


[![oVKz9K.jpg](https://z3.ax1x.com/2021/11/26/oVKz9K.jpg)](https://imgtu.com/i/oVKz9K)


8、UI美化


[![oVMS1O.jpg](https://z3.ax1x.com/2021/11/26/oVMS1O.jpg)](https://imgtu.com/i/oVMS1O)


9、背景更换


[![oVMpcD.jpg](https://z3.ax1x.com/2021/11/26/oVMpcD.jpg)](https://imgtu.com/i/oVMpcD)

##具体代码：
### 一、记录时间戳添加

首先在每个item中添加时间戳的文本位置，顺便优化外观
（标题占左上方，时间戳在标题下，将点击整个item相应事件绑定到右边的“编辑”图标中）：

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:id="@+id/ll_notesItems"
    android:layout_marginBottom="5dp"
    >

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:layout_marginTop="10dp"
        android:background="#50ffffff"
        >

    </LinearLayout>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:background="#50ffffff"
        android:padding="5dp"
        >
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:orientation="vertical"
            >
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Title"
                android:textColor="#2197ef"
                android:textSize="22sp"
                android:layout_marginTop="5dp"
                android:layout_marginLeft="10dp"
                android:id="@+id/tv_title"
                />
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="10dp"
                android:id="@+id/tv_data"
                android:textColor="#000000"
                android:textSize="12sp"
                />
        </LinearLayout>

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:layout_marginTop="10dp"
            >
            <ImageView
                android:layout_width="30dp"
                android:layout_height="30dp"
                android:background="@drawable/ic_menu_edit"
                android:layout_marginRight="10dp"
                android:id="@+id/iv_editNote"
                />
            <View
                android:layout_width="20dp"
                android:layout_height="40dp"
                />
            <ImageView
                android:layout_width="0dp"
                android:layout_height="40dp"
                android:id="@+id/iv_deleteNote"
                />
        </LinearLayout>

    </LinearLayout>
</LinearLayout>
```
效果如图
[![oVQeaR.png](https://z3.ax1x.com/2021/11/26/oVQeaR.png)](https://imgtu.com/i/oVQeaR)

修改READ_NOTE_PROJECTION：

```
    private static final String[] READ_NOTE_PROJECTION = new String[] {
            NotePad.Notes._ID,               // id
            NotePad.Notes.COLUMN_NAME_NOTE,  // 内容
            NotePad.Notes.COLUMN_NAME_TITLE, // 标题
            NotePad.Notes.COLUMN_NAME_CREATE_DATE, // 创建时间
    };
```

添加Note类：

```
public class Note {
    private String Title;//笔记的标题
    private String createTime;//笔记的创建时间
    private String Cursor_id;//所属的游标的位置

    public Note(String title, String createTime) {
        Title = title;
        this.createTime = createTime;
    }

    public Note(String title, String createTime, String cursor_id) {
        Title = title;
        this.createTime = createTime;
        Cursor_id = cursor_id;
    }

    public Note(String title, String createTime, String cursor_id, Cursor cursor) {
        Title = title;
        this.createTime = createTime;
        Cursor_id = cursor_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCursor_id() {
        return Cursor_id;
    }

    public void setCursor_id(String cursor_id) {
        Cursor_id = cursor_id;
    }
}

```
编写NotesAdapter适配器（部分功能）:
```
public View getView(int position, View convertView, ViewGroup parent) {

        ViewHold viewHold=null;
        if(convertView==null){
            viewHold=new ViewHold();
            convertView=inflater.inflate(R.layout.noteslistitem,null);
            viewHold.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            viewHold.tv_data= (TextView) convertView.findViewById(R.id.tv_data);
            viewHold.iv_editNote= (ImageView) convertView.findViewById(R.id.iv_editNote);
            viewHold.iv_deleteNote= (ImageView) convertView.findViewById(R.id.iv_deleteNote);
            viewHold.ll_notesItems= (LinearLayout) convertView.findViewById(R.id.ll_notesItems);
            convertView.setTag(viewHold);
        }else{
            viewHold= (ViewHold) convertView.getTag();
        }
        if(mDate.get(position)!=null){

            viewHold.tv_title.setText(mDate.get(position).getTitle());
            viewHold.tv_data.setText(DateUtil.StringToDate(mDate.get(position).getCreateTime()+""));
        }
        viewHold.ll_notesItems.setBackgroundColor(Color.parseColor(background));
        setClick(viewHold,position);
        return convertView;
    }

    //为删除控件设置监听
        viewHold.iv_deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteNote(Uri.parse(mUri+"/"+mDate.get(position).getCursor_id()));
                mDate.remove(position);
                notifyDataSetChanged();
            }
        });

        
```
如图


[![oV10bQ.png](https://z3.ax1x.com/2021/11/26/oV10bQ.png)](https://imgtu.com/i/oV10bQ)

### 二、添加搜索功能
在layout文件添加EditText和Buttom
```
<LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        >
        <EditText
            android:id="@+id/et_Search"
            android:layout_width="match_parent"
            android:layout_height="40dp"
            android:layout_marginLeft="10dp"
            android:layout_weight="1"
            android:hint="输入标题关键字查找"
            android:textColor="#000000"
            android:textCursorDrawable="@drawable/contact_edit_edittext_normal" />

        <ImageView
            android:layout_width="30dp"
            android:layout_height="30dp"
            android:background="@drawable/search1"
            android:layout_marginRight="10dp"
            android:layout_marginTop="5dp"
            android:layout_marginLeft="10dp"
            android:id="@+id/iv_searchnotes"
            />

    </LinearLayout>
```



之后绑定按钮，添加搜索功能：

```
private void initView() {
        ll_noteList= (LinearLayout) findViewById(R.id.noteList_layout);
        iv_addnotes= (ImageView) findViewById(R.id.fab);
        lv_notesList= (ListView) findViewById(android.R.id.list);//绑定listView;
        et_Search= (EditText) findViewById(R.id.et_Search);
        iv_searchnotes= (ImageView) findViewById(R.id.iv_searchnotes);

        iv_addnotes.setOnClickListener(this);
        iv_searchnotes.setOnClickListener(this);
        ll_noteList.setBackgroundColor(Color.parseColor(MyApplication.getBackground()));
        lv_notesList.setBackgroundColor(Color.parseColor(MyApplication.getBackground()));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                startActivity(new Intent(Intent.ACTION_INSERT, getIntent().getData()));
                break;
            case R.id.iv_searchnotes:
                showOrhide();
                if(et_Search.getText().toString().equals("")){
                    Cursor cursor1 = managedQuery(
                            getIntent().getData(),            // Use the default content URI for the provider.
                            PROJECTION,                       // Return the note ID and title for each note.
                            null,                             // No where clause, return all records.
                            null,                             // No where clause, therefore no where column values.
                            NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
                    );
                    adapter.readDate(cursor1);
                    adapter.notifyDataSetChanged();
                }else{
                    adapter.Search(et_Search.getText().toString());
                }

                break;
        }
    }
```


[![oVKz9K.jpg](https://z3.ax1x.com/2021/11/26/oVKz9K.jpg)](https://imgtu.com/i/oVKz9K)

### 三、UI美化

通过下载icon，图片通过photosho等软件进行编辑元素，最终UI为简洁风格
layout文件
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:fab="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFFFCC"
    android:id="@+id/noteList_layout">


    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"

        >

        <EditText
            android:id="@+id/et_Search"
            android:layout_width="match_parent"
            android:layout_height="40dp"
            android:layout_marginLeft="10dp"
            android:layout_weight="1"
            android:hint="输入标题关键字查找"
            android:textColor="#000000"
            android:textCursorDrawable="@drawable/contact_edit_edittext_normal" />

        <ImageView
            android:layout_width="30dp"
            android:layout_height="30dp"
            android:background="@drawable/search1"
            android:layout_marginRight="10dp"
            android:layout_marginTop="5dp"
            android:layout_marginLeft="10dp"
            android:id="@+id/iv_searchnotes"
            />

    </LinearLayout>

    <FrameLayout
    android:layout_width="match_parent"
    android:layout_weight="1"
    android:orientation="vertical"
    android:layout_height="wrap_content">
    <ListView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:cacheColorHint="#00000000"
        android:divider="#FFFFCC"
        android:dividerHeight="0dp"
        android:id="@id/android:list"

        >

    </ListView>
        <ImageButton
            android:id="@+id/fab"
            android:layout_width="60dp"
            android:layout_height="60dp"
            android:layout_gravity="bottom|center"
            android:layout_margin="45dp"
            android:background="@drawable/tianjia"
            android:backgroundTintMode="add"/>


</FrameLayout>


        <!--fab:fab_colorNormal="@color/primary"-->
        <!--fab:fab_colorPressed="@color/primary_pressed"-->
        <!--fab:fab_colorRipple="@color/ripple" />-->
    <!--<ImageView-->
        <!--android:layout_marginTop="20dp"-->
        <!--android:layout_width="80dp"-->
        <!--android:layout_height="80dp"-->
        <!--android:background="@drawable/tianjia"-->
        <!--android:layout_gravity="center_horizontal"-->
        <!--android:layout_marginBottom="20dp"-->
        <!--android:id="@+id/iv_addnotes"-->
        <!--/>-->
</LinearLayout>
```
初始界面


[![oVMS1O.jpg](https://z3.ax1x.com/2021/11/26/oVMS1O.jpg)](https://imgtu.com/i/oVMS1O)


编译页面


[![oVMPnH.jpg](https://z3.ax1x.com/2021/11/26/oVMPnH.jpg)](https://imgtu.com/i/oVMPnH)

### 四、实现背景更换功能

添加背景选择view，绑定切换背景onclick方法
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:weightSum="0.7">

    <ImageView
        android:layout_width="40dp"
        android:layout_height="60dp"
        android:id="@+id/background1"
        android:background="@drawable/background1"
        android:layout_weight="0.1"
        android:onClick="ColorSelect"
        />
    <ImageView
        android:layout_width="40dp"
        android:layout_height="60dp"
        android:layout_weight="0.1"
        android:id="@+id/background2"
        android:background="@drawable/background2"
        android:onClick="ColorSelect"
        />
    <ImageView
        android:layout_width="40dp"
        android:layout_height="60dp"
        android:id="@+id/background3"
        android:layout_weight="0.1"
        android:background="@drawable/background3"
        android:onClick="ColorSelect"
        />
    <ImageView
        android:layout_width="40dp"
        android:layout_height="60dp"
        android:layout_weight="0.1"
        android:id="@+id/background4"
        android:background="@drawable/background4"
        android:onClick="ColorSelect"
        />
    <ImageView
        android:layout_width="40dp"
        android:layout_height="60dp"
        android:layout_weight="0.1"
        android:id="@+id/background5"
        android:background="@drawable/background5"
        android:onClick="ColorSelect"
        />
    <ImageView
        android:layout_width="40dp"
        android:layout_height="60dp"
        android:layout_weight="0.1"
        android:id="@+id/background6"
        android:background="@drawable/background6"
        android:onClick="ColorSelect"
        />
    <ImageView
        android:layout_width="40dp"
        android:layout_height="60dp"
        android:layout_weight="0.1"
        android:id="@+id/background7"
        android:background="@drawable/background7"
        android:onClick="ColorSelect"
        />

</LinearLayout>

public void ColorSelect(View view){
        String color;
        switch(view.getId()){
            case R.id.background1:
                /*此处进行背景颜色修改,后改为主题效果*/
//                color="#FFC0CB";
//                ll_noteList.setBackgroundColor(Color.parseColor(color));
//                lv_notesList.setBackgroundColor(Color.parseColor(color));
//                adapter.setBackground(color);
//                adapter.notifyDataSetChanged();
//                MyApplication.setBackground(color);
//                MyApplication.saveBackground();
                Drawable btnDrawable1 = getResources().getDrawable(R.drawable.background1);
                ll_noteList.setBackgroundDrawable(btnDrawable1);
                lv_notesList.setBackgroundDrawable(btnDrawable1);

                break;
            case R.id.background2:
                Drawable btnDrawable2 = getResources().getDrawable(R.drawable.background2);
                ll_noteList.setBackgroundDrawable(btnDrawable2);
                lv_notesList.setBackgroundDrawable(btnDrawable2);
                break;
            case R.id.background3:
                Drawable btnDrawable3 = getResources().getDrawable(R.drawable.background3);
                ll_noteList.setBackgroundDrawable(btnDrawable3);
                lv_notesList.setBackgroundDrawable(btnDrawable3);
                break;
            case R.id.background4:
                Drawable btnDrawable4 = getResources().getDrawable(R.drawable.background4);
                ll_noteList.setBackgroundDrawable(btnDrawable4);
                lv_notesList.setBackgroundDrawable(btnDrawable4);
                break;
            case R.id.background5:
                Drawable btnDrawable5 = getResources().getDrawable(R.drawable.background5);
                ll_noteList.setBackgroundDrawable(btnDrawable5);
                lv_notesList.setBackgroundDrawable(btnDrawable5);
                break;
            case R.id.background6:
                Drawable btnDrawable6 = getResources().getDrawable(R.drawable.background6);
                ll_noteList.setBackgroundDrawable(btnDrawable6);
                lv_notesList.setBackgroundDrawable(btnDrawable6);
                break;
            case R.id.background7:
                Drawable btnDrawable7 = getResources().getDrawable(R.drawable.background7);
                ll_noteList.setBackgroundDrawable(btnDrawable7);
                lv_notesList.setBackgroundDrawable(btnDrawable7);
                break;
        }

    }
```
最终效果展示


[![oVMpcD.jpg](https://z3.ax1x.com/2021/11/26/oVMpcD.jpg)](https://imgtu.com/i/oVMpcD)


[![oVKvh6.jpg](https://z3.ax1x.com/2021/11/26/oVKvh6.jpg)](https://imgtu.com/i/oVKvh6)


[![oVMiBd.jpg](https://z3.ax1x.com/2021/11/26/oVMiBd.jpg)](https://imgtu.com/i/oVMiBd)
