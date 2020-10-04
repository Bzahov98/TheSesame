package com.paysafe.hackathon.thesesame.ui.fragments.portfolio

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
import com.anychart.APIlib
import com.anychart.AnyChartView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.paysafe.hackathon.thesesame.R
import com.paysafe.hackathon.thesesame.data.model.ArtItemData
import com.paysafe.hackathon.thesesame.internal.utils.gone
import com.paysafe.hackathon.thesesame.internal.utils.show
import com.paysafe.hackathon.thesesame.ui.anychartGraphs.PortfolioChartUtils.Companion.createPriceChart
import com.paysafe.hackathon.thesesame.ui.fragments.myArtItems.recyclerview.ArtListItem
import com.paysafe.hackathon.thesesame.ui.fragments.portfolio.recyclerview.PortfolioItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_art_list_my.*
import kotlinx.android.synthetic.main.fragment_art_list_my.myItemsListRecyclerView
import kotlinx.android.synthetic.main.fragment_portfolio.*
import kotlinx.android.synthetic.main.item_portfolio.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.util.function.Consumer

class PortfolioFragment : Fragment(), KodeinAware {
    private var showAddMore: Boolean = true
    private val TAG = "PortfolioFragment"
    override val kodein by closestKodein()
    private lateinit var viewModel: PortfolioViewModel
    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    private lateinit var recyclerView: RecyclerView
    private lateinit var customAlertDialogView: View
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    private val factory: PortfolioViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(PortfolioViewModel::class.java)
        //        val textView: TextView = root.findViewById(R.id.text_notifications)
//        uploadOfferViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return inflater.inflate(R.layout.fragment_portfolio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
//        myItemsListAddNewFAB.setOnClickListener{
//            // Inflate Custom alert dialog view
//            // launchCustomAlertDialog()
//            //showDialog()
//        }
        updateReceivedData()
        showChart()
        initTabLayout()
    }

    private fun showChart() {
        val chartView = anyChartView
        APIlib.getInstance().setActiveAnyChartView(chartView)
        chartView.setDebug(true)
        chartView.setChart(createPriceChart(null))
    }

    private fun initTabLayout() {

        portfolioTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab){
                    portfolioTabMyInv ->{
                        showAddMore = true
                    }
                    portfolioTabListed ->{
                        showAddMore = false
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }

    private fun initRecyclerView() {
        groupAdapter = GroupAdapter<ViewHolder>()
        recyclerView = myItemsListRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PortfolioFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            val itemDetail = (item as PortfolioItem).data
            Toast.makeText(
                this@PortfolioFragment.context,
                "Clicked: ${itemDetail.title}",
                Toast.LENGTH_SHORT
            ).show()
            //startPlaceDetailActivity(requireContext(), itemDetail.id)

            Log.e(TAG, "\n\nGroupAdapter.setOnItemClickListener ${itemDetail}\n")
            //showWeatherDetail(itemDetail.dtTxt, view)
        }
    }

    private fun updateReceivedData() {

        val listItem = HashSet<PortfolioItem>()
        viewModel.listData.observe(
            viewLifecycleOwner,
            Observer { t ->
                run {
                    listItem.clear()
                    t.forEach(Consumer { place ->
                        if( place == null) return@Consumer
                        listItem.add(PortfolioItem(place,false,requireActivity()))
                    })
                    if(showAddMore) {
                        listItem.add(PortfolioItem(ArtItemData(), true,requireActivity()))
                    }
                    clearUpdateRecyclerViewData(listItem)
                }
            })
    }

    //    private fun showDialog() {
//        val dialogFragment = AddNewOfferDialogFragment(this)
//        dialogFragment.show(requireActivity().supportFragmentManager, "signature")
//    }
    private fun clearUpdateRecyclerViewData(items: Set<PortfolioItem>?) { // null only to clear data
        groupAdapter.clear()
        if (!items.isNullOrEmpty()) {
            groupAdapter.apply { addAll(items) }
            //myItemsListNoResults.gone()
        } else {
            //myItemsListNoResults.show()
        }
        groupAdapter.notifyDataSetChanged()
    }
}
