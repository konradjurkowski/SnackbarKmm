# SnackbarKmm

Step 1  
Add the dependency in the `commonMain` section of your `build.gradle.kts` file

    implementation("io.github.konradjurkowski:snackbarkmm:0.0.7")  

Step 2  
In the main component of your KMM app, typically in the App() composable, you need to wrap your app's content with the `ContentWithSnackBar` composable to provide access to the snackbar state throughout the app. You can also change the look of your snackbar by passing composable to the `snackbar` field in `ContetnWithSnackbar`.


     ContentWithSnackBar { 
    	 / App content } 
     }

Example Usage of Snackbar


    val snackBarState = LocalSnackbarState.current
    snackBarState.showSuccess("SUCCESS")  
    snackBarState.showError("ERROR")  
    snackBarState.showInfo("INFO")  
      
    snackBarState.showSuccess(Res.string.success)  
    snackBarState.showError(Res.string.error)  
    snackBarState.showInfo(Res.string.info)

In addition, you can specify duration and position.

    enum class SnackBarDuration(val durationTime: Long) {  
        SHORT(2000L),  
        MEDIUM(3500L),  
        LONG(5000L),  
        EXTRA_LONG(8000L),  
    }
    
    enum class SnackBarPosition {  
        TOP,  
        BOTTOM;  
    }
