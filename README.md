# SnackbarKMM

Step 1
Add the dependency in the `commonMain` section of your `build.gradle.kts` file

    implementation("io.github.konradjurkowski:snackbarkmm:0.0.2")

Step 2
Define the `LocalSnackbarState` to store the snackbar state within your app.


    val LocalSnackbarState = compositionLocalOf<SnackBarState> { error("No SnackbarState provided") }

Step 3
In the main component of your KMM app, typically in the `App()` composable, you need to initialize the snackbar state and wrap your app's content with the `ContentWithSnackBar` composable to provide access to the snackbar state throughout the app.

    val snackbarState = rememberSnackBarState()
    ContentWithSnackBar(snackBarState = snackbarState) {
        CompositionLocalProvider(LocalSnackbarState provides snackbarState) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                // App content
            }
        }
    }

Example Usage of Snackbar

    val snackBarState = LocalSnackbarState.current
    Button(onClick = { snackBarState.showSuccess("Success Snackbar") }) {
        Text("Click me")
    }

