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

## ProgressIndicator Attributes

Here are the attributes of `ProgressIndicator` for `xml` and `kotlin`:

| Attributes   | Type   | Default  | Description   |
|--------------|--------|----------|---------------|


### License

```
Copyright 2022 rohit

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

```