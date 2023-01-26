package com.tryden.simplenfl

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.epoxy.controllers.team.player.PlayerEpoxyController

class PlayerFragment : Fragment() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerPlayer = PlayerEpoxyController()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyPlayerRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_player_RecyclerView)

        viewModel.playerByIdLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerPlayer.playerResponse = response
        }

        viewModel.refreshPlayer("14876")
        epoxyPlayerRecyclerView.setControllerAndBuildModels(epoxyControllerPlayer)

    }
}