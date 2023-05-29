package com.example.mymusic

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private var totalTime : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this,R.raw.music)
        mediaPlayer.setVolume(1f,1f)
        totalTime = mediaPlayer.duration

        val bStart = findViewById<ImageView>(R.id.start)
        val bPause = findViewById<ImageView>(R.id.pause)
        val bStop = findViewById<ImageView>(R.id.stop)
        val seekBar = findViewById<SeekBar>(R.id.seekbar)

        bStart.setOnClickListener {
            mediaPlayer.start()
        }

        bPause.setOnClickListener {
            mediaPlayer.pause()
        }

        bStop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }

        seekBar.max = totalTime

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBars : SeekBar?, progress : Int, fromUser : Boolean) {
                if(fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBars : SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBars : SeekBar?) {
            }

        })

        val handler = Handler()

        handler.postDelayed(object : Runnable{
            override fun run() {
                try {
                    seekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this,1000)
                }catch(exception :java.lang.Exception){
                    seekBar?.progress = 0
                }
            }
        },0)

    }
}