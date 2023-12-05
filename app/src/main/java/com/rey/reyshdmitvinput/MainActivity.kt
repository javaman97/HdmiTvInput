package com.rey.reyshdmitvinput

import android.content.Intent
import android.media.tv.TvContract
import android.media.tv.TvInputInfo.TYPE_HDMI
import android.media.tv.TvInputManager
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import kotlin.toString


class MainActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.videoView)
        button= findViewById(R.id.button)

        button.setOnClickListener {
            buttonSetHDMIInput()
        }
    }

    private fun buttonSetHDMIInput() {

        val tvInputManager = getSystemService(TV_INPUT_SERVICE) as TvInputManager
        val tvInputInfo = tvInputManager.getTvInputInfo(TYPE_HDMI.toString())
        val uriTVInput = TvContract.buildChannelUriForPassthroughInput(tvInputInfo.toString())
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.data = uriTVInput
        startActivity(intent)
        videoView.setVideoURI(uriTVInput)
    }

}