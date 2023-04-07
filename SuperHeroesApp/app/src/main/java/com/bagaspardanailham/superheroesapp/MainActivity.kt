package com.bagaspardanailham.superheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagaspardanailham.superheroesapp.model.Hero
import com.bagaspardanailham.superheroesapp.model.HeroesRepository
import com.bagaspardanailham.superheroesapp.model.HeroesRepository.heroes
import com.bagaspardanailham.superheroesapp.ui.theme.SuperHeroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SuperHeroScreen(HeroesRepository.heroes)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SuperHeroScreen(heroes: List<Hero>) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    Scaffold(
        topBar = {
            SuperHeroTopBar()
        }
    ) {
        AnimatedVisibility(
            visibleState = visibleState,
            enter = fadeIn(
                animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
            ),
            exit = fadeOut()
        ) {
            LazyColumn(modifier = Modifier
                .background(MaterialTheme.colors.background)
            ) {
                itemsIndexed(heroes) { index, hero ->
                    SuperHeroListItem(
                        hero = hero,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .animateEnterExit(
                                enter = slideInHorizontally(
                                    animationSpec = spring(
                                        stiffness = StiffnessVeryLow,
                                        dampingRatio = DampingRatioLowBouncy
                                    ),
                                    initialOffsetX = { it * (index + 1)  }
                                )
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun SuperHeroListItem(modifier: Modifier = Modifier, hero: Hero) {
    Card(
        modifier = modifier,
        elevation = 2.dp
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(hero.name),
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = stringResource(hero.desc),
                    style = MaterialTheme.typography.body1
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))

            ) {
                Image(
                    painter = painterResource(hero.img),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Composable
fun SuperHeroTopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperHeroesAppTheme {
        SuperHeroScreen(HeroesRepository.heroes)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDark() {
    SuperHeroesAppTheme(darkTheme = true) {
        SuperHeroScreen(HeroesRepository.heroes)
    }
}