package com.example.recyclerspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerspace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),LongClick {

    private lateinit var binding: ActivityMainBinding
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    fun init() {
       val userList = mutableListOf<CarModel>(CarModel(
           "nissan",
            "teana",
             1999,
            "https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png",
            "https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png",

        ))

        userAdapter = UserAdapter(userList,this)
        initRecyclerView()


        binding.addUserButton.setOnClickListener {
            addUser()
        }
    }

    private fun addUser() {
        val userImageList = mutableListOf<String>(
            "https://www.pngall.com/wp-content/uploads/5/User-Profile-PNG-High-Quality-Image.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/1024px-User_icon_2.svg.png",
            "https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png",
            "null"
        )
//        with(binding){
//
//            if (ageEditText.text?.isNotBlank()==true && usernameEditText.text?.isNotBlank()==true){
//                val userModel=UserModel(
//                    usernameEditText.text.toString(),
//                    ageEditText.text.toString().toInt(),
//                    //
//                    userImageList.random()
//                )
//                userAdapter.addSingleUser(userModel)
//            }else{
//                Toast.makeText(this@MainActivity,"Fill all the fields",Toast.LENGTH_SHORT).show()
//            }
//        }

        with(binding){

            if (yearEditText.text?.isNotBlank()==true && manufacturerEditText.text?.isNotBlank()==true && modelEditText.text?.isNotBlank()==true){
                val userModel=CarModel(
                    manufacturerEditText.text.toString(),
                    modelEditText.text.toString(),
                    yearEditText.text.toString().toInt(),
                    userImageList.random(),
                    userImageList.random()
                )
                userAdapter.addSingleUser(userModel)
            }else{
                Toast.makeText(this@MainActivity,"Fill all the fields",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun initRecyclerView() {

        with(binding.recyclerMain) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }

    override fun deleteCard(position: Int) {
        userAdapter.deleteView(position)
    }


}