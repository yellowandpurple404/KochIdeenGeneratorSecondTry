package com.example.kochideengenerator.ui

sealed class IdeasEvent{
    class SwipeRight :IdeasEvent()
    class SwipeLeft :IdeasEvent()
    class SwipeUp :IdeasEvent()
    class SwipeDown :IdeasEvent()
    class DoubleClick :IdeasEvent()
}
