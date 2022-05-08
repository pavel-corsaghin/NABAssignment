package io.hungnguyen.nab.assignment.extension

class StringExtension {

    fun String.capitalizeFirstChar(): String {
        return replaceFirstChar {
            it.uppercaseChar()
        }
    }
}