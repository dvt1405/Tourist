package com.kt.apps.media.taipeitour.utils

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.executor.GlideExecutor
import com.bumptech.glide.module.AppGlideModule


@GlideModule
class GlideModule : AppGlideModule() {
    override fun isManifestParsingEnabled(): Boolean {
        return true
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val service = GlideExecutor.newSourceBuilder()
            .setThreadCount(threadPoolSize)
            .setName("GlideExecutor")
            .setUncaughtThrowableStrategy(GlideExecutor.UncaughtThrowableStrategy.DEFAULT)
            .build()
        builder.setSourceExecutor(service)
            .setAnimationExecutor(
                GlideExecutor.newSourceBuilder()
                    .setThreadCount(threadPoolSize)
                    .setName("AnimationExecutor")
                    .setUncaughtThrowableStrategy(GlideExecutor.UncaughtThrowableStrategy.DEFAULT)
                    .build()
            )
            .setDiskCacheExecutor(
                GlideExecutor.newSourceBuilder()
                    .setThreadCount(threadPoolSize)
                    .setName("DiskCacheExecutor")
                    .setUncaughtThrowableStrategy(GlideExecutor.UncaughtThrowableStrategy.DEFAULT)
                    .build()
            )
    }

    companion object {
        private const val threadPoolSize = 4
    }
}