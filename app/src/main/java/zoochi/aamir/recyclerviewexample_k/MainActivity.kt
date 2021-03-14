package zoochi.aamir.recyclerviewexample_k

import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zoochi.aamir.recyclerviewexample_k.ExampleAdapter.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    private val exampleList = generateDummyList(500)
   // private val adapter  = ExampleAdapter(exampleList, this)
   private lateinit var adapter: ExampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //amir

        val recycler_view: RecyclerView =findViewById(R.id.recycler_view)
        //recycler_view.adapter = adapter
        adapter=ExampleAdapter(
            exampleList = exampleList,
            listener = object : OnItemClickListener{
                override fun onItemClick(position: Int) =
                    Toast.makeText(this@MainActivity, "position "+position, Toast.LENGTH_SHORT).show()
            }
        )
        recycler_view.adapter=adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }


    fun insertItem(view: View) {
        val index = Random.nextInt(8);
        val newItem = ExampleItem(
            R.drawable.ic_person,
            "New item at position $index",
            "Line 2"
        )
        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }

    fun removeItem(view: View) {
        val index = Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    /*override fun onItemClick(position: Int) {
        Toast.makeText(this, "clicked $position", Toast.LENGTH_SHORT).show()
        exampleList[position].text1 = "clicked"
        adapter.notifyItemChanged(position);
    }*/

    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_goggle
                1 -> R.drawable.ic_facebook
                else -> R.drawable.ic_black_disk
            }
            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}