<div align="center">

# 🚀 Android Icon Switcher

### Dynamic Launcher Icon Switching for Android

Change your Android application's launcher icon **remotely** without publishing a new APK.

Built with Kotlin, Jetpack Compose, Builder Pattern, StateFlow and a provider-based architecture.

> **Work in Progress** – Designed to evolve into a production-ready Android SDK.

---

</div>

## ✨ Why Android Icon Switcher?

Android supports multiple launcher icons using **Activity Aliases**, but managing them dynamically can quickly become complex.

Android Icon Switcher abstracts all that complexity behind a clean, extensible API.

Instead of writing PackageManager code yourself, simply provide a remote provider (Firestore today, REST tomorrow), and let the SDK handle the rest.

---

# Features

### Current

- ✅ Dynamic launcher icon switching
- ✅ Activity Alias management
- ✅ Builder Pattern API
- ✅ Live Firestore provider
- ✅ Repository architecture
- ✅ Icon validation
- ✅ StateFlow dashboard state
- ✅ Event listener callbacks
- ✅ SharedPreferences persistence
- ✅ Jetpack Compose sample application

### Planned

- 🔄 REST API Provider
- 🔄 Firebase Remote Config Provider
- 🔄 Lifecycle-aware SDK
- 🔄 Kotlin DSL
- 🔄 Analytics
- 🔄 Compose SDK module
- 🔄 Maven Central publishing

---

# Demo

## Dashboard

- Current active launcher icon
- Available icons
- Firestore status
- SDK event logs
- Live updates using StateFlow

*(Screenshots / GIF coming soon)*

---

# Installation

```gradle
implementation("io.github.shibin:icon-switcher:<latest-version>")
```

---

# Quick Start

## Configure your icons

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
```

---

## Initialize the SDK

```kotlin
IconSwitcher.builder(applicationContext)
    .config(config)
    .provider(
        FirestoreProvider(
            FirebaseFirestore.getInstance()
        )
    )
    .listener(
        object : IconSwitcherListener {

            override fun onIconChanged(icon: IconInfo) {
                Log.d("SDK", "Changed to ${icon.name}")
            }

            override fun onError(error: Throwable) {
                Log.e("SDK", "Error", error)
            }
        }
    )
    .build()
```

That's it.

The SDK automatically observes the provider and updates the launcher icon whenever the remote value changes.

---

# Firestore Configuration

Collection

```
configuration
```

Document

```
icon
```

Field

```
activeIcon
```

Example

```json
{
  "activeIcon": "blue"
}
```

Updating the field immediately changes the launcher icon.

---

# Architecture

```
                     Application

                           │

                           ▼

                 IconSwitcher Builder

                           │

                           ▼

                    IconSwitcher SDK

                           │

             ┌─────────────┼─────────────┐
             │             │             │

             ▼             ▼             ▼

      Repository      Validator     Preferences

             │

             ▼

       Alias Manager

             │

             ▼

      Android PackageManager

             │

             ▼

      Activity Alias Switching
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

sample-app
│
├── dashboard
├── firestore
└── demo data
```

---

# Public API

Current public entry point

```kotlin
IconSwitcher.builder(context)
```

Builder

```kotlin
.config(...)
.provider(...)
.listener(...)
.enableLogging(...)
.enableCaching(...)
.build()
```

---

# SDK State

The SDK exposes a live `StateFlow` that can be observed directly from Jetpack Compose.

```kotlin
val state by IconSwitcher.dashboardState.collectAsStateWithLifecycle()
```

Useful for building dashboards, debug screens, or analytics without additional wiring.

---

# Event Listener

Receive SDK events through a listener.

```kotlin
override fun onIconChanged(icon: IconInfo)

override fun onAlreadyApplied(icon: IconInfo)

override fun onInvalidIcon(requested: String)

override fun onSwitchFailed(requested: String)

override fun onError(error: Throwable)
```

---

# Providers

Current

- Firestore Provider

Upcoming

- REST Provider
- Firebase Remote Config Provider
- Custom Providers

The provider architecture allows the SDK to support multiple remote configuration sources without changing the core engine.

---

# Roadmap

## Phase 1

- [x] Core Engine
- [x] Alias Manager
- [x] Preferences

## Phase 2

- [x] Repository
- [x] Validator
- [x] Result API

## Phase 3

- [x] Listener API
- [x] Builder Pattern
- [x] Dashboard State

## Phase 4

- [ ] Lifecycle Awareness
- [ ] Kotlin DSL
- [ ] Logging Improvements
- [ ] Analytics

## Phase 5

- [ ] REST Provider
- [ ] Remote Config Provider
- [ ] Compose SDK

## Phase 6

- [ ] Unit Tests
- [ ] CI/CD
- [ ] Maven Central

---

# Motivation

Typical launcher icon switching implementations require developers to:

- Manage multiple Activity Aliases
- Work directly with PackageManager
- Handle persistence
- Validate icon names
- Listen for remote updates

Android Icon Switcher brings these concerns together in a reusable SDK with a clean API.

---

# Technologies

- Kotlin
- Android SDK
- Jetpack Compose
- Kotlin Coroutines
- StateFlow
- Firebase Firestore
- Builder Pattern
- Repository Pattern

---
