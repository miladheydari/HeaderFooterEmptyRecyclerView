# android RecyclerView support Header Footer and Empty list


***
simple and useful
***
Install
=====

Add to the dependency

```groovy
	dependencies {
            implementation 'com.android.support:recyclerview-v7:27.1.1'            //required

            implementation 'com.miladheydari:headerfooteremptyrecyclerview:1.1.0'
	}
```

# usage
first add HFERecyclerView and empty view to your layout
```xml
<com.miladheydari.hferecyclerview.HFERecyclerView
        android:id="@+id/recycler"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <include
        layout="@layout/empty"
        android:visibility="gone" />
```
**your adapter must extend HFEAdapter<T>**  
T is type of your data list and must use getItem(position) for geting item of list data
```kotlin
class Adapter(_data: List<String>?) : HFEAdapter<String>(_data) {
    override fun getItemView(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {

        return ViewHolder(inflater.inflate(R.layout.row, parent,false))

    }

    override fun onBindViewHolder(holder: android.support.v7.widget.RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.tv.text = getItem(position) //required

            }
            else -> {
            }

        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tv: TextView = view.findViewById(R.id.tv)

    }
}
``` 
initial HFERecyclerView and add header footer and attach emptyView

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var hfeRecyclerView: HFERecyclerView
    private val listString: MutableList<String> = ArrayList()

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hfeRecyclerView = findViewById(R.id.recycler)

        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        hfeRecyclerView.layoutManager = mLayoutManager
        hfeRecyclerView.itemAnimator = DefaultItemAnimator()

        hfeRecyclerView.emptyView = findViewById(R.id.empty_view)
        for (i in 1..20)
            listString.add("hii $i")

        adapter = Adapter(listString)
        hfeRecyclerView.adapter = adapter

        hfeRecyclerView.setFooter(LayoutInflater.from(this).inflate(R.layout.footer, null))
        hfeRecyclerView.setHeader(LayoutInflater.from(this).inflate(R.layout.header, null))

    }
}
```
```kotlin
hfeRecyclerView.setFooter(view)
```
set footer for HFERecyclerView and remove footer with pass null

```kotlin
hfeRecyclerView.setHeader(view)
```
set header for HFERecyclerView and remove header with pass null

```kotlin
hfeRecyclerView.emtyView = view
```
set emptyView to recycler view **optional
