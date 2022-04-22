package com.example.treespotter_firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.treespotter_firebase.Tree
import com.example.treespotter_firebase.TreeRecyclerViewAdapter
import com.example.treespotter_firebase.TreeViewModel
import java.lang.RuntimeException

private const val TAG = "TREE_LIST_FRAGMENT"

class TreeListFragment : Fragment() {

    private val treeViewModel: TreeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(TreeViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val recyclerView = inflater.inflate(R.layout.fragment_tree_list, container, false)
        if (recyclerView !is RecyclerView) { throw RuntimeException("TreeListFragment View must be a RecyclerView") }

        val trees = listOf<Tree>()
        val adapter = TreeRecyclerViewAdapter(trees) { tree, isFavorite ->
            treeViewModel.setIsFavorite(tree, isFavorite)
        }

        treeViewModel.latestTrees.observe(requireActivity()) { treeList ->
            adapter.trees = treeList
            // To avoid updating everything, we can identify what has changed
            // https://firebase.google.com/docs/firestore/query-data/listen#view_changes_between_snapshots
            adapter.notifyDataSetChanged()  // better to update more specifically, this re runs the
            // whole list on any change
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        return recyclerView
    }


    companion object {
        @JvmStatic
        fun newInstance() = TreeListFragment()
    }
}