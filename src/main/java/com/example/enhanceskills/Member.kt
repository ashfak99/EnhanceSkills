package com.example.enhanceskills

data class Member(
    var title: String? = null,
    var url: String? = null
) {
    // Returns the title with a default value if null
    fun getTitleOrDefault(): String {
        return title ?: "No Title"  // Default value if title is null
    }

    // Returns the URL with a default value if null
    fun getUrlOrDefault(): String {
        return url ?: ""  // Default value if URL is null
    }
}
