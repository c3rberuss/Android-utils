package com.c3rberuss.androidutils

object YoutubeUtils {

    private val numericRegex = Regex("[0-9]*")

    private enum class UrlType {
        SHORT,
        LONG,
        UNKNOWN
    }

    private fun getTypeUrl(url: String): UrlType {
        return when {
            url.startsWith("https://www.youtube.com/") -> UrlType.LONG
            url.startsWith("https://youtu.be/") -> UrlType.SHORT
            else -> UrlType.UNKNOWN
        }
    }

    private fun getVideoIdFromShortUrl(url: String): String {
        var re = "(?<=[aA-zZ]/)(.*)(?=[?&])".toRegex()
        var newUrl = re.find(url)

        if (newUrl != null && newUrl.value.isNotEmpty()) {
            return newUrl.value
        }

        re = "(?<=[aA-zZ]/)(.*)".toRegex()
        newUrl = re.find(url)

        if (newUrl != null && newUrl.value.isNotEmpty()) {
            return newUrl.value
        }

        return ""
    }

    private fun getVideoIdFromLongUrl(url: String): String {
        var re = Regex("(?<=\\?v=)(.*)(?=[?&])")
        var newUrl = re.find(url)

        if (newUrl != null && newUrl.value.isNotEmpty()) {
            return newUrl.value
        }

        re = "(?<=\\?v=)(.*)".toRegex()
        newUrl = re.find(url)

        if (newUrl != null && newUrl.value.isNotEmpty()) {
            return newUrl.value
        }

        return ""
    }

    private fun getVideoStartTime(url: String): Int {
        val re = Regex("(([?&])t=[0-9]*(s)?)|([;&]start=[0-9]*(s)?)")

        val time = re.find(url)
        if (time != null) {
            val result = numericRegex.findAll(time.value)

            result.iterator().forEach loop@{
                if (it.value.isNotEmpty()) {
                    return it.value.toInt()
                }
            }
        }

        return 0
    }

    fun getVideoId(url: String): String {
        return when (getTypeUrl(url)) {
            UrlType.SHORT -> getVideoIdFromShortUrl(url)
            UrlType.LONG -> getVideoIdFromLongUrl(url)
            UrlType.UNKNOWN -> url
        }
    }

    fun getStartTime(url: String): Int {
        return getVideoStartTime(url)
    }
}