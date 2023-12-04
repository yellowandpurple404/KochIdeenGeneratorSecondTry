package com.example.kochideengenerator.ui

import com.example.kochideengenerator.domain.model.Idea

data class MainScreenState(
    val displayedIdea: Idea = Idea("dd", "", "", 0),
)
