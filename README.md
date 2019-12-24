# DeltaDrive
[![](https://jitpack.io/v/DeltaRobotics-9351/DeltaDrive.svg)](https://jitpack.io/#DeltaRobotics-9351/DeltaDrive)

Easy to use library containing many functions to control your FTC robot movements

## Install (Credit to EasyOpenCV & DogeCV)
1. Pull up Android Studio, with the FTC application SDK open
2. Go to the root `build.gradle`
3. To the repositories section, add the lines 
```groovy
allprojects {
  repositories {
    maven { url 'https://jitpack.io' } // this line!
  }
}
```
3. Add the line `implementation 'com.github.DeltaRobotics-9351:DeltaDrive:v1.0-beta'` to TeamCode's `build.release.gradle`, inside the dependencies block
7. Press the `Sync Now` button that should appear in the top right

(Video tutorial coming soon)

## Features
 
**Mecanum wheels:**
   - IMU sensor turns and strafing
   - Time based drive
   - Encoder based drive
   
## TO-DO
   - Wiki and sample classes
   - More chassis types:
      - 4-wheel omnidirectional
      - H-Drive 
      - X-Drive 
