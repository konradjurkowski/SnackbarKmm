# SnackbarKMM

Step 1
Add the dependency in the `commonMain` section of your `build.gradle.kts` file

    implementation("io.github.konradjurkowski:snackbarkmm:0.0.2")

Step 2
In the main component of your KMM app, typically in the `App()` composable, you need to wrap your app's content with the `ContentWithSnackBar` composable to provide access to the snackbar state throughout the app.

    ContentWithSnackBar {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            // App content
        }
    }

Example Usage of Snackbar

    val snackBarState = LocalSnackbarState.current
    Button(onClick = { snackBarState.showSuccess("Success Snackbar") }) {
        Text("Click me")
    }

