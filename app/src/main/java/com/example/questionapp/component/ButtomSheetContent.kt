package com.example.questionapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.questionapp.Module.Article
import java.security.AllPermission

@Composable
fun BottomSheetContent(
    article: Article,
    onReedFullStoryButtonClicked: () -> Unit
){
    Surface (modifier = Modifier.padding(16.dp)){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = article.title,
                style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article.description ?: "",
                style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article.content ?: "",
                style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =Arrangement.SpaceBetween){
                Text(text = article.author ?: "",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold)

                Text(text = article.source.name ?: "",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold)




            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onReedFullStoryButtonClicked,
                modifier = Modifier.fillMaxWidth()
                    .padding()) {
                Text(text = "Red Full Story")

            }

            ImageHolder(imageUrl = article.urlToImage)
            Spacer(modifier = Modifier.height(8.dp))






        }


    }
}