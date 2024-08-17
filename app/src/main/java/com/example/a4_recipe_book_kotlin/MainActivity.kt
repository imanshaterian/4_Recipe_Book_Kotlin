package com.example.a4_recipe_book_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import coil.compose.rememberAsyncImagePainter
import com.example.a4_recipe_book_kotlin.ui.theme._4_Recipe_Book_KotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _4_Recipe_Book_KotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { FootNavigation() } // footer
                ) { innerPadding ->
                    RecipeList(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun RecipeList(modifier: Modifier = Modifier) {
    val sampleRecipes = listOf(
        Recipe("Vegan Salad", "https://via.placeholder.com/150", "15 min", RecipeType.Vegan),
        Recipe("Healthy Smoothie", "https://via.placeholder.com/150", "10 min", RecipeType.Healthy),
        Recipe("Pasta", "https://via.placeholder.com/150", "30 min", RecipeType.Normal)
    )

    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(sampleRecipes.size) { index ->
            RecipeCard(recipe = sampleRecipes[index])
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(recipe.imageURL),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = recipe.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = recipe.cookingTime, style = MaterialTheme.typography.bodyMedium)
                }
                //food type icon
                Text(text = recipe.type.name, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun FootNavigation(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = { /*TODO*/ }) { Text("Breakfast") }
        Button(onClick = { /*TODO*/ }) { Text("Lunch/Dinner") }
        Button(onClick = { /*TODO*/ }) { Text("Dessert") }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    _4_Recipe_Book_KotlinTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { FootNavigation() }) { RecipeList() }
    }
}