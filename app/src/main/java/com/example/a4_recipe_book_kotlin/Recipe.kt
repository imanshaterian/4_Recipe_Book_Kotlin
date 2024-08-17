package com.example.a4_recipe_book_kotlin

data class Recipe (
    val name: String,
    val imageURL: String,
    val cookingTime: String,
    val type: RecipeType
)

enum class RecipeType{
    Vegan, Healthy, Normal
}