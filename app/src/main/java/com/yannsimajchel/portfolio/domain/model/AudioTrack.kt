package com.yannsimajchel.portfolio.domain.model

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.MediaMetadataRetriever
import androidx.palette.graphics.Palette
import com.yannsimajchel.portfolio.R

data class AudioTrack(
    private val mediaMetadata: MediaMetadataRetriever,
    private val resources: Resources
) {
    var title : String = mediaMetadata.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) ?: "TITLE"
    var album: String = mediaMetadata.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM) ?: ""
    var albumCover: Bitmap
    var squareAlbumCover: Bitmap
    var artist: String =
        mediaMetadata.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST) ?: "ARTIST"
    var themeColors: List<Int> = listOf(Color.RED, Color.GREEN, Color.BLUE)

    init {
        val trackArt = mediaMetadata.embeddedPicture
        albumCover = if (trackArt != null) {
            BitmapFactory.decodeByteArray(trackArt, 0, trackArt.size)
        } else {
            BitmapFactory.decodeResource(resources, R.drawable.audio_placeholder)
        }
        squareAlbumCover = createSquaredBitmap(albumCover)

        val palette = Palette.generate(albumCover)
        val mainColor = palette.getVibrantColor(Color.WHITE)
        val darkColor = palette.getDarkVibrantColor(Color.WHITE)
        val lightColor = palette.getLightMutedColor(Color.WHITE)

        themeColors = listOf(mainColor, darkColor, lightColor)
    }


    private fun createSquaredBitmap(srcBmp: Bitmap): Bitmap {
        if (srcBmp.width >= srcBmp.height){
            return Bitmap.createBitmap(
                srcBmp,
                srcBmp.width /2 - srcBmp.height /2,
                0,
                srcBmp.height,
                srcBmp.height
            );

        }else{
            return Bitmap.createBitmap(
                srcBmp,
                0,
                srcBmp.height /2 - srcBmp.width /2,
                srcBmp.width,
                srcBmp.width
            );
        }
    }
}