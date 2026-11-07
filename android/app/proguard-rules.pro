# ProGuard and R8 Rules for Centre Youth

# Keep Kotlinx serialization & Gson models
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
-keep,allowobfuscation,allowshrinking class org.marthoma.centreyouth.data.model.** { *; }

# Media3 ExoPlayer & Session rules
-keep class androidx.media3.** { *; }
-dontwarn androidx.media3.**

# WorkManager Worker keep
-keep class * extends androidx.work.Worker {
    public <init>(android.content.Context, androidx.work.WorkerParameters);
}
-keep class * extends androidx.work.CoroutineWorker {
    public <init>(android.content.Context, androidx.work.WorkerParameters);
}

# AndroidX Core & Compose
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>();
}
