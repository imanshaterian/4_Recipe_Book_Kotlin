package com.example.a4_recipe_book_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.a4_recipe_book_kotlin.ui.theme._4_Recipe_Book_KotlinTheme

enum class RecipeCategory {
    BREAKFAST, LUNCH_DINNER, DESSERT
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _4_Recipe_Book_KotlinTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { FootNavigation(onCategorySelected = { navController.navigate(it.name) }) }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "BREAKFAST",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("BREAKFAST") {
                            RecipeList(category = RecipeCategory.BREAKFAST,
                                onRecipeSelected = { navController.navigate("details") })
                        }
                        composable("LUNCH_DINNER") {
                            RecipeList(category = RecipeCategory.LUNCH_DINNER,
                                onRecipeSelected = { navController.navigate("details") })
                        }
                        composable("DESSERT") {
                            RecipeList(category = RecipeCategory.DESSERT,
                                onRecipeSelected = { navController.navigate("details") })
                        }
                        composable("details") {
                            RecipeDetail(
                                recipe = Recipe(
                                    "Sample Recipe",
                                    "https://via.placeholder.com/150",
                                    "30 min",
                                    RecipeType.Normal
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeList(
    category: RecipeCategory, onRecipeSelected: () -> Unit, modifier: Modifier = Modifier
) {
    val sampleRecipes = when (category) {
        RecipeCategory.BREAKFAST -> listOf(
            Recipe(
                "Vegan Pancakes", "https://via.placeholder.com/150", "20 min", RecipeType.Vegan
            ), Recipe(
                "Fruit Salad", "https://via.placeholder.com/150", "10 min", RecipeType.Healthy
            )
        )

        RecipeCategory.LUNCH_DINNER -> listOf(
            Recipe(
                "Grilled Chicken", "https://via.placeholder.com/150", "40 min", RecipeType.Normal
            ), Recipe(
                "Pasta", "https://via.placeholder.com/150", "30 min", RecipeType.Normal
            )
        )

        RecipeCategory.DESSERT -> listOf(
            Recipe(
                "Chocolate Cake", "https://via.placeholder.com/150", "50 min", RecipeType.Normal
            ), Recipe(
                "Vegan Cookies", "https://via.placeholder.com/150", "25 min", RecipeType.Vegan
            )
        )
    }

    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(sampleRecipes.size) { index ->
            RecipeCard(recipe = sampleRecipes[index], onClick = onRecipeSelected)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(recipe.imageURL),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Row(
                modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = recipe.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = recipe.cookingTime, style = MaterialTheme.typography.bodyMedium)
                }
                // Placeholder for recipe type (Vegan, Healthy, Normal)
                Text(text = recipe.type.name, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun RecipeDetail(recipe: Recipe, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Image(
            painter = rememberAsyncImagePainter(recipe.imageURL),
            contentDescription = recipe.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = recipe.name, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Cooking Time: ${recipe.cookingTime}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Ingredients:", style = MaterialTheme.typography.headlineSmall)
        Text(
            text = "• Ingredient 1\n• Ingredient 2\n• Ingredient 3",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Instructions:", style = MaterialTheme.typography.headlineSmall)
        Text(
            text = "1. Step one.\n2. Step two.\n3. Step three.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun FootNavigation(onCategorySelected: (RecipeCategory) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = { onCategorySelected(RecipeCategory.BREAKFAST) }) { Text("Breakfast") }
        Button(onClick = { onCategorySelected(RecipeCategory.LUNCH_DINNER) }) { Text("Lunch/Dinner") }
        Button(onClick = { onCategorySelected(RecipeCategory.DESSERT) }) { Text("Dessert") }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    _4_Recipe_Book_KotlinTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = { FootNavigation(onCategorySelected = {}) }) {
            RecipeList(category = RecipeCategory.BREAKFAST, onRecipeSelected = {})
        }
    }
}

// Sample Recipe and RecipeType classes
data class Recipe(
    val name: String, val imageURL: String, val cookingTime: String, val type: RecipeType
)

enum class RecipeType {
    Vegan, Healthy, Normal
}