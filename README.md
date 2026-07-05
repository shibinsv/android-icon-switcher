# 🚀 Android Icon Switcher SDK

A lightweight, extensible Android SDK that enables **dynamic launcher icon switching** without publishing a new APK.

The SDK observes a remote data source (currently Firestore) and automatically updates the launcher icon using Android Activity Aliases.

> ⚡ Change your app icon remotely. No app update required.

---

## ✨ Features

- 🎨 Dynamic launcher icon switching
- 🔥 Live Firestore integration
- 🏗 Builder Pattern API
- 📡 Observable SDK state using StateFlow
- 💾 Current icon persistence
- ✅ Icon validation
- 🎯 Activity Alias management
- 🪵 Event listener callbacks
- 🧩 Modular architecture
- 🛠 Easily extendable with custom providers

---

## Demo

| Dashboard | Launcher Icon |
|-----------|---------------|
| SDK Dashboard | Dynamic App Icon |
| Firestore Connected | Auto Updates |

*(Screenshots coming soon)*

---

# Installation

### Step 1

Add the dependency

```gradle
implementation("io.github.shibin:icon-switcher:<version>")
```

---

### Step 2

Configure Activity Aliases

```xml
<activity
    android:name=".MainActivity"
    android:exported="true"/>

<activity-alias
    android:name=".DefaultIcon"
    android:enabled="true"
    android:targetActivity=".MainActivity"
    android:icon="@mipmap/ic_launcher">

    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>

</activity-alias>

<activity-alias
    android:name=".BlueIcon"
    android:enabled="false"
    android:targetActivity=".MainActivity"
    android:icon="@mipmap/ic_launcher_blue">

    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>

</activity-alias>
```

---

# Usage

```kotlin
val config = IconConfig(
    defaultIcon = "default",
    icons = listOf(
        IconInfo(
            name = "default",
            alias = ".DefaultIcon"
        ),
        IconInfo(
            name = "blue",
            alias = ".BlueIcon"
        ),
        IconInfo(
            name = "red",
            alias = ".RedIcon"
        )
    )
)

IconSwitcher.builder(applicationContext)
    .config(config)
    .provider(
        FirestoreProvider(
            FirebaseFirestore.getInstance()
        )
    )
    .listener(object : IconSwitcherListener {

        override fun onIconChanged(icon: IconInfo) {
            Log.d("SDK", "Changed to ${icon.name}")
        }

        override fun onError(error: Throwable) {
            Log.e("SDK", "Error", error)
        }

    })
    .enableLogging(true)
    .enableCaching(true)
    .build()
```

---

# Firestore Structure

Collection

```
configuration
```

Document

```
icon
```

Fields

```
activeIcon : "blue"
```

Changing

```
default
```

to

```
red
```

automatically updates the launcher icon on every connected device.

---

# SDK Architecture

```
                App

                 │

                 ▼

        IconSwitcher Builder

                 │

                 ▼

          IconSwitcher SDK

                 │

      ┌──────────┼──────────┐
      │          │          │
      ▼          ▼          ▼

 Repository   Validator   Preferences

      │

      ▼

 Alias Manager

      │

      ▼

 PackageManager

      │

      ▼

 Launcher Icon
```

---

# Module Structure

```
icon-switcher
│
├── builder
├── listener
├── manager
├── model
├── preferences
├── provider
├── repository
├── result
├── validator
└── IconSwitcher.kt
```

---

# Current Features

- Dynamic launcher icon switching
- Firestore provider
- Builder Pattern
- StateFlow support
- Repository architecture
- Preferences storage
- Icon validation
- Result API
- Listener API

---

# Planned Features

- REST API Provider
- Firebase Remote Config Provider
- Lifecycle Awareness
- Kotlin DSL
- Compose Extensions
- Analytics
- Scheduler
- Custom Providers
- Dashboard UI
- Unit Tests
- GitHub Actions
- Maven Central Publishing

---

# Example Dashboard

```
Android Icon Switcher SDK

🟢 Connected

Current Icon

Blue

Available Icons

Default
Blue
Red

Firestore

Current Value

blue

SDK Logs

Initialized

Connected

Icon Changed
```

---

# Why this SDK?

Instead of shipping multiple APKs or waiting for Play Store updates, this SDK allows you to remotely control your application's launcher icon.

Perfect for

- Seasonal branding
- Holiday themes
- Promotions
- Feature launches
- A/B testing
- White-label applications
- Enterprise deployments

---

# Requirements

- Android API 24+
- Kotlin
- AndroidX
- Activity Alias support

---

# Roadmap

- [x] Core Engine
- [x] Alias Manager
- [x] Repository Pattern
- [x] Preferences
- [x] Firestore Provider
- [x] Builder Pattern
- [x] Listener API
- [x] Result API
- [x] Dashboard State
- [ ] Lifecycle Support
- [ ] Kotlin DSL
- [ ] REST Provider
- [ ] Remote Config Provider
- [ ] Compose SDK
- [ ] Testing Module
- [ ] Maven Central

---


## Author

**Shibin**

Senior Android Developer

GitHub: https://github.com/<your-github>

---

⭐ If you found this project useful, consider giving it a star!
