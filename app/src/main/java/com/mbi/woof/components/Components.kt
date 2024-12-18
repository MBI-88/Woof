package com.mbi.woof.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mbi.woof.R
import com.mbi.woof.data.DataSource
import com.mbi.woof.data.Model



@Composable
fun WoofIcon(@DrawableRes woofIco: Int, modifier: Modifier = Modifier){
    Image(
        modifier= modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small)
        ,
        contentScale = ContentScale.Crop,
        painter =  painterResource(woofIco),
        contentDescription = "Image"
    )
}

@Composable
fun WoofInfo(@StringRes woofInfo:Int, wooAge: Int, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = stringResource(woofInfo),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text =  stringResource(R.string.years_old, wooAge ),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun WoofButton(expanded:Boolean, modifier: Modifier, onClick: () -> Unit) {
    IconButton(
        onClick,
        modifier = modifier
    ){
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun WoofHobby(@StringRes hobby: Int, modifier: Modifier = Modifier) {
    Column(
        modifier
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(hobby),
            style = MaterialTheme.typography.labelSmall
        )
    }
}


@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }


@Composable
fun WoofCard(model: DataSource, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.shadow(2.dp, shape = Shapes().small, ambientColor = Color.DarkGray)
    ) {
        Column(
            modifier = Modifier.animateContentSize(animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessMedium
            )
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))

            ) {
                WoofIcon(model.imageResourceId)
                WoofInfo(model.stringResourceId,model.age)
                Spacer(Modifier.weight(1f))
                WoofButton(expanded, modifier, onClick = {expanded = !expanded})
            }
            if (expanded) {
                WoofHobby(model.stringResourceId,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    )

                )
            }
        }
    }
}



@Composable
fun WoofApp() {
    val models = Model().LoadResources()
    Scaffold(
        topBar = {
            WoofTopAppBar()
        }
    ) {
            it ->
        LazyColumn(contentPadding = it) {
            items(models) {
                WoofCard(
                    model = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}