package app.suhasdissa.mellowmusic.utils

import app.suhasdissa.mellowmusic.backend.models.PipedInstance

object Pref {
    const val pipedInstanceKey = "PipedInstanceKey"

    val pipedInstances = listOf(
        PipedInstance(
            "lunar.icu",
            "https://piped-api.lunar.icu/",
            "https://piped-proxy.lunar.icu/"
        ),
        PipedInstance(
            "whatever.social",
            "https://watchapi.whatever.social/",
            "https://watchproxy-nl.whatever.social"
        ),
        PipedInstance(
            "tokhmi.xyz",
            "https://pipedapi.tokhmi.xyz/"
        ),
        PipedInstance(
            "mha.fi",
            "https://api-piped.mha.fi/"
        ),
        PipedInstance(
            "garudalinux.org",
            "https://piped-api.garudalinux.org/"
        ),
        PipedInstance(
            "piped.yt",
            "https://api.piped.yt/"
        ),
        PipedInstance(
            "kavin.rocks",
            "https://pipedapi.kavin.rocks/"
        )
    )

    var pipedUrl: Int? = null
}