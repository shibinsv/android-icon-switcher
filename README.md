# Android Icon Switcher

A personal Android project exploring **dynamic launcher icon switching** using `activity-alias` and modern Android architecture.

The goal of this project is to learn, experiment, and eventually evolve it into a reusable Android library/SDK.

---
<img width="1774" height="887" alt="icon-switcher-l" src="https://github.com/user-attachments/assets/ba315097-4967-4aaa-b857-793ec86bb9a4" />


## About

Android supports multiple launcher icons through `activity-alias`, but switching them dynamically requires working directly with `PackageManager`.

This project explores building a clean architecture around that feature while keeping the implementation modular and extensible.

The application currently uses **Firebase Firestore** as the remote source for changing launcher icons in real time.

---

## Features

- Dynamic launcher icon switching
- Activity Alias management
- Firebase Firestore integration
- Builder Pattern
- Repository Pattern
- StateFlow for UI updates
- Preferences for storing current icon
- Validation layer
- Listener callbacks
- Jetpack Compose sample dashboard

---

## Project Structure

```
app/
│
├── Demo application
├── Dashboard UI
└── Firestore integration

icon-switcher/
│
├── builder/
├── listener/
├── manager/
├── model/
├── preferences/
├── provider/
├── repository/
├── result/
├── validator/
└── IconSwitcher.kt
```

---

## Tech Stack

- Kotlin
- Android SDK
- Jetpack Compose
- Kotlin Coroutines
- StateFlow
- Firebase Firestore
- Material 3

---

## Current Architecture

```
Firestore
      │
      ▼
Remote Provider
      │
      ▼
Repository
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

## Current Progress

- [x] Dynamic icon switching
- [x] Firestore provider
- [x] Builder Pattern
- [x] Repository layer
- [x] Validation layer
- [x] Preferences
- [x] Listener API
- [x] Dashboard State (StateFlow)
- [x] Compose demo UI

---

## Planned Improvements

- Lifecycle-aware provider
- REST API provider
- Firebase Remote Config provider
- Kotlin DSL
- Better dashboard
- Unit tests
- Documentation
- Publish as a reusable library

---

## Motivation

This project started as an experiment to understand how launcher icons can be changed without publishing a new APK.

Along the way, it became an opportunity to practice modern Android development concepts such as modularization, clean architecture, reactive UI, and extensible API design.

---
