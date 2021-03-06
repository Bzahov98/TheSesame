package com.paysafe.hackathon.thesesame.ui.fragments.myArtItems

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.paysafe.hackathon.thesesame.R
import com.paysafe.hackathon.thesesame.data.model.ArtItemData
import com.paysafe.hackathon.thesesame.internal.utils.gone
import com.paysafe.hackathon.thesesame.internal.utils.show
import com.paysafe.hackathon.thesesame.ui.fragments.addNew.AddNewOfferDialogFragment
import com.paysafe.hackathon.thesesame.ui.fragments.addNew.CallbackListener
import com.paysafe.hackathon.thesesame.ui.fragments.home.HomeViewModelFactory
import com.paysafe.hackathon.thesesame.ui.fragments.myArtItems.recyclerview.ArtListItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.dialog_fullscreen_add_new.*
import kotlinx.android.synthetic.main.fragment_art_list_my.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.util.function.Consumer

class MyArtItemsFragment : Fragment(),KodeinAware, CallbackListener {
    private val TAG  = "UploadOfferFragment"
    override val kodein by closestKodein()
    private lateinit var viewModel: MyArtItemsViewModel
    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    private lateinit var recyclerView: RecyclerView
    private lateinit var customAlertDialogView : View
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    private val factory: MyArtItemsViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(MyArtItemsViewModel::class.java)
        //        val textView: TextView = root.findViewById(R.id.text_notifications)
//        uploadOfferViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return inflater.inflate(R.layout.fragment_art_list_my, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        myItemsListAddNewFAB.setOnClickListener{
            // Inflate Custom alert dialog view


           showDialog()
        }
        updateReceivedData()
    }

    private fun initRecyclerView() {
        groupAdapter = GroupAdapter<ViewHolder>()
        recyclerView = myItemsListRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MyArtItemsFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            val itemDetail = (item as ArtListItem).data
            Toast.makeText(
                this@MyArtItemsFragment.context,
                "Clicked: ${itemDetail.title}",
                Toast.LENGTH_SHORT
            ).show()
            //startPlaceDetailActivity(requireContext(), itemDetail.id)

            Log.e(TAG, "\n\nGroupAdapter.setOnItemClickListener ${itemDetail}\n")
            //showWeatherDetail(itemDetail.dtTxt, view)
        }
    }
    private fun updateReceivedData() {

        val listItem = HashSet<ArtListItem>()
        viewModel.listData.observe(
            viewLifecycleOwner,
            Observer { t ->
                run {
                    listItem.clear()
                    t.forEach(Consumer { place ->
                        if( place == null) return@Consumer
                        listItem.add(ArtListItem(place))
                    })
                    clearUpdateRecyclerViewData(listItem)
                }
            })
    }
    private fun showDialog() {
        val dialogFragment = AddNewOfferDialogFragment(this)
        dialogFragment.show(requireActivity().supportFragmentManager, "signature")
    }
    private fun clearUpdateRecyclerViewData(items: Set<ArtListItem>?) { // null only to clear data
        groupAdapter.clear()
        if (!items.isNullOrEmpty()) {
            groupAdapter.apply { addAll(items) }
            myItemsListNoResults.gone()
        }else{
            myItemsListNoResults.show()
        }
        groupAdapter.notifyDataSetChanged()
    }

    override fun onDataReceived(data: ArtItemData) {
        Log.d(TAG, "onDataReceived: ${data.title}\n$data")
        viewModel.createNewItem(data)
    }


}