package com.example.sqldatabse

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqldatabse.util.DBHelper
import com.example.sqldatabse.util.ModelClass

class DataAdapter(val mainActivity: MainActivity, val list: ArrayList<ModelClass>) :
    RecyclerView.Adapter<DataAdapter.Viewdata>() {

    class Viewdata(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var id_txt = itemView.findViewById<TextView>(R.id.id_txt)
        var name_txt = itemView.findViewById<TextView>(R.id.name_txt)
        var mobile_txt = itemView.findViewById<TextView>(R.id.mobile_txt)
        var std_txt = itemView.findViewById<TextView>(R.id.std_txt)
        var edit_img = itemView.findViewById<ImageView>(R.id.edit_img)
        var delete_img = itemView.findViewById<ImageView>(R.id.delete_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewdata {
        var view = LayoutInflater.from(mainActivity).inflate(R.layout.itemfile, parent, false)
        return Viewdata(view)
    }

    override fun onBindViewHolder(holder: Viewdata, position: Int) {

        holder.id_txt.setText(list[position].id)
        holder.name_txt.setText(list[position].name)
        holder.mobile_txt.setText(list[position].mobile)
        holder.std_txt.setText(list[position].std)

        holder.delete_img.setOnClickListener {
            DBHelper(mainActivity).deleteData(list[position].id)

            val l1 = DBHelper(mainActivity).readData();

            mainActivity.RvView(l1)
        }
        holder.edit_img.setOnClickListener {
            Dailog(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

//    dailog

    fun Dailog(position: Int) {
        var dailog = Dialog(mainActivity)
        dailog.setContentView(R.layout.dailog_item)
        dailog.show()

        val name_update_edt = dailog.findViewById<EditText>(R.id.name_update_edt)
        val mobile_update_edt = dailog.findViewById<EditText>(R.id.mobile_update_edt)
        val sdt_update_edt = dailog.findViewById<EditText>(R.id.sdt_update_edt)
        val update_btn = dailog.findViewById<Button>(R.id.update_btn)

        update_btn.setOnClickListener {
            DBHelper(mainActivity).updateData(list[position].id,
                name_update_edt.text.toString(),
                mobile_update_edt.text.toString(),
                sdt_update_edt.text.toString())
            var l1 = DBHelper(mainActivity).readData();
            mainActivity.RvView(l1)

            dailog.dismiss()
        }
    }
}

