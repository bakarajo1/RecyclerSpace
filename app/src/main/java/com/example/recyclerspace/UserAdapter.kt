package com.example.recyclerspace

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerspace.databinding.ItemRecyclerWithBothImagesBinding
import com.example.recyclerspace.databinding.ItemRecyclerWithoutLeftImageBinding
import com.example.recyclerspace.databinding.ItemRecyclerWithoutRightImageBinding
import com.example.recyclerspace.models.CarModel

class UserAdapter(private val userList:MutableList<CarModel>, val longClick: LongClick): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class UserWithoutRightImageVH(private val binding:ItemRecyclerWithoutRightImageBinding):RecyclerView.ViewHolder(binding.root) {

        fun onBind(carModel: CarModel, longClick: LongClick) {
            with(binding) {

                Glide.with(leftImage.context).load(carModel.imageUrl1).into(leftImage)
                cardView.setOnClickListener {
                    Toast.makeText(binding.root.context, "Manufacturer:${carModel.manufacturer}, Model:${carModel.model}, Year:${carModel.year}", Toast.LENGTH_SHORT).show()
                }
                cardView.setOnLongClickListener {


                    longClick.deleteCard(adapterPosition)
                    true

                }

            }

        }
    }

    class UserWithBothImageVH(private val binding:ItemRecyclerWithBothImagesBinding):RecyclerView.ViewHolder(binding.root) {

        fun onBind(carModel: CarModel, longClick: LongClick) {
            with(binding) {
                Glide.with(leftImage.context).load(carModel.imageUrl1).into(leftImage)
                Glide.with(rightImage.context).load(carModel.imageUrl2).into(rightImage)
                cardView.setOnClickListener {
                    Toast.makeText(binding.root.context, "Manufacturer:${carModel.manufacturer}, Model:${carModel.model}, Year:${carModel.year}", Toast.LENGTH_SHORT).show()
                }
                cardView.setOnLongClickListener {


                    longClick.deleteCard(adapterPosition)
                    true

                }
                            }
        }
    }
    class UserWithoutLeftImageVH(private val binding:ItemRecyclerWithoutLeftImageBinding):RecyclerView.ViewHolder(binding.root) {

        fun onBind(carModel: CarModel, longClick: LongClick) {
            with(binding) {
                Glide.with(rightImage.context).load(carModel.imageUrl2).into(rightImage)
                cardView.setOnClickListener {
                    Toast.makeText(binding.root.context, "Manufacturer:${carModel.manufacturer}, Model:${carModel.model}, Year:${carModel.year}", Toast.LENGTH_SHORT).show()
                }
                cardView.setOnLongClickListener {


                    longClick.deleteCard(adapterPosition)
                    true

                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType== USER_WITHOUT_LEFT_IMAGE){

            UserWithoutLeftImageVH(ItemRecyclerWithoutLeftImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }else if (viewType== USER_WITH_BOTH_IMAGES){

            UserWithBothImageVH(ItemRecyclerWithBothImagesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }else{
            UserAdapter.UserWithoutRightImageVH(
                ItemRecyclerWithoutRightImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            //is UserViewHolder -> holder.onBind(userList[position])
            is UserWithoutLeftImageVH -> holder.onBind(userList[position],longClick)
            is UserAdapter.UserWithoutRightImageVH -> holder.onBind(userList[position],longClick)
            is UserWithBothImageVH -> holder.onBind(userList[position],longClick)

        }
    }

    override fun getItemCount(): Int =userList.size

    override fun getItemViewType(position: Int): Int {
        val car=userList[position]
        return if (car.imageUrl1=="null"){
            USER_WITHOUT_LEFT_IMAGE
        }else if(car.imageUrl2 =="null"){
            USER_WITHOUT_RIGHT_IMAGE
        }else{
            USER_WITH_BOTH_IMAGES
        }
    }
    fun addSingleUser(carModel: CarModel){
        this.userList.add(carModel)
        notifyItemInserted(userList.size-1)
    }
    fun deleteView(position: Int){
        userList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, userList.size)

    }

    companion object{


        private const val USER_WITH_BOTH_IMAGES=0
        private const val USER_WITHOUT_LEFT_IMAGE=1
        private const val USER_WITHOUT_RIGHT_IMAGE=2

    }


}
