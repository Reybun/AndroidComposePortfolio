package com.yannsimajchel.portfolio.ui

import android.annotation.SuppressLint
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yannsimajchel.portfolio.R
import com.yannsimajchel.portfolio.domain.model.AudioTrack
import com.yannsimajchel.portfolio.ui.tools.noRippleClickable


class BonusScreen() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainScreen() {
        val selectedTrack = remember { mutableStateOf(0) }
        val isMediaPlaying = remember { mutableStateOf(false) }
        val sliderPosition = remember { mutableStateOf(0f) }


        val uriStrings = listOf(
            "android.resource://com.yannsimajchel.portfolio/raw/bloom",
            "android.resource://com.yannsimajchel.portfolio/raw/time"
        )
        val raws = listOf(
            R.raw.bloom,
            R.raw.time
        )

        val data = MediaMetadataRetriever()
        val context = LocalContext.current
        val mediaPlayer = MediaPlayer.create(context, raws[selectedTrack.value])
        data.setDataSource(LocalContext.current, Uri.parse(uriStrings[selectedTrack.value]))

        val track = AudioTrack(data, LocalContext.current.resources)

        sliderPosition.value =
            ((mediaPlayer.currentPosition * 100) / mediaPlayer.duration).toFloat()

        val timer = object : CountDownTimer(mediaPlayer.duration.toLong(), 100) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("TEST", "TEST ${mediaPlayer.currentPosition.toFloat()}")
                sliderPosition.value = mediaPlayer.currentPosition.toFloat()
                if (mediaPlayer.currentPosition == mediaPlayer.duration) {
                    isMediaPlaying.value = false
                }
            }

            override fun onFinish() {}
        }.start()

        DisposableEffect(LocalContext.current) {
            onDispose {
                mediaPlayer.stop()
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Music Player",
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.onPrimary,
                        )
                    },
                    backgroundColor = MaterialTheme.colors.primary
                )
            },
            content = {
                Image(
                    bitmap = track.albumCover.asImageBitmap(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .background(MaterialTheme.colors.surface)
                        .alpha(0.3f)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black,
                                    MaterialTheme.colors.background,
                                    Color.Black,
                                )
                            )
                        )
                        .fillMaxSize()

                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(36.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier.border(
                                4.dp,
                                Brush.verticalGradient(
                                    colors = track.themeColors.map { Color(it) }
                                ),
                                RectangleShape
                            ),
                            bitmap = track.squareAlbumCover.asImageBitmap(),
                            contentDescription = "audio track image"
                        )
                        Text(
                            text = track.title,
                            color = MaterialTheme.colors.onSurface,
                            textAlign = TextAlign.Left,
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(
                            text = track.artist,
                            color = MaterialTheme.colors.onSurface,
                            textAlign = TextAlign.Left,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(4.dp)
                        )
                        Slider(
                            value = sliderPosition.value,
                            onValueChange = {
                                sliderPosition.value = it
                                mediaPlayer.seekTo(it.toInt())
                            },
                            valueRange = 0f..mediaPlayer.duration.toFloat(),
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = Icons.Filled.SkipPrevious,
                                tint = MaterialTheme.colors.onSurface,
                                contentDescription = "Play Pause",
                                modifier = Modifier
                                    .size(50.dp)
                                    .noRippleClickable {
                                        mediaPlayer.stop()
                                        mediaPlayer.reset()
                                        data.close()
                                        timer.cancel()
                                        if (selectedTrack.value == 0) {
                                            selectedTrack.value = uriStrings.size - 1
                                        } else {
                                            selectedTrack.value--
                                        }
                                        isMediaPlaying.value = false
                                    }
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Icon(
                                imageVector =
                                if (isMediaPlaying.value) Icons.Filled.Pause
                                else Icons.Filled.PlayArrow,
                                tint = MaterialTheme.colors.onSurface,
                                contentDescription = "Play Pause",
                                modifier = Modifier
                                    .size(70.dp)
                                    .noRippleClickable {
                                        if (mediaPlayer.isPlaying) {
                                            mediaPlayer.pause()
                                            isMediaPlaying.value = false
                                        } else {
                                            isMediaPlaying.value = true
                                            mediaPlayer.start()
                                        }
                                    }
                                    .border(
                                        4.dp,
                                        Brush.verticalGradient(
                                            colors = track.themeColors.map { Color(it) }
                                        ),
                                        CircleShape
                                    ),
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Icon(
                                imageVector = Icons.Filled.SkipNext,
                                tint = MaterialTheme.colors.onSurface,
                                contentDescription = "Play Pause",
                                modifier = Modifier
                                    .size(50.dp)
                                    .noRippleClickable {
                                        mediaPlayer.stop()
                                        mediaPlayer.reset()
                                        data.close()
                                        timer.cancel()
                                        if (selectedTrack.value == uriStrings.size - 1) {
                                            selectedTrack.value = 0
                                        } else {
                                            selectedTrack.value++
                                        }
                                        isMediaPlaying.value = false
                                    }
                            )
                        }

                    }

                }
            }
        )
    }
}


