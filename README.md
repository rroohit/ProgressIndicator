# ProgressIndicator

Android Horizontal Progress Indicator

https://user-images.githubusercontent.com/36406595/207393203-666fa372-16c4-4bd7-95f6-cef6bdcef473.mp4

## Download Using Gradle

[![](https://jitpack.io/v/rroohit/ProgressIndicator.svg)](https://jitpack.io/#rroohit/ProgressIndicator)

Add this in your root `settings.gradle` at `dependencyResolutionManagement` in `repositories`
section:

```groovy
repositories {
    google()
    //...
    maven { url 'https://jitpack.io' }

}
```

Then add this dependency to your **module-level** `build.gradle` in `dependencies` section:

```groovy
implementation 'com.github.rroohit:ProgressIndicator:1.1.0'
```

## Usage

### Xml

First add following widget in xml with customized attributes:

```xml

<com.roh.indicator.ProgressIndicator 
    android:layout_width="match_parent"
    android:layout_height="50dp" 
    app:noOfIndicators="5" 
    app:selectedIndicator="1"
    app:indicatorRadius="40" 
    app:trackThickNess="20" 
    app:trackerColor="@color/tracker_color"
    app:trackerProgressColor="@color/progress_tracker_color" />
```

### Kotlin

We can customize the ProgressIndicator using the below methods from base `activity` or `fragment`:

```kotlin
    val indicator = findViewById<ProgressIndicator>(R.id.indicator)

    indicator.noOfIndicators = 5        //Number of indicator you want show
    indicator.selectedIndicator = 2     //Number of indicator completed position
    indicator.indicatorRadius = 40      //Indicator radius
    indicator.trackThickness = 20       //Track thickness of horizontal progress bar
```

#