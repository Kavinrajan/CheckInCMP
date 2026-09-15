# 📱 CheckInCMP

### Kotlin Multiplatform • Compose Multiplatform • Android & iOS

**CheckInCMP** is a modern **Kotlin Multiplatform** mobile application designed to share business logic and UI across **Android and iOS** using **Compose Multiplatform**.

The project follows a clean, layered architecture to keep **data, business logic, and presentation** separated and maintainable.

---

## ✨ Highlights

* 📱 Android & iOS support
* 🔄 Shared Kotlin Multiplatform code
* 🎨 Compose Multiplatform UI
* 🏗️ Clean layered architecture
* ♻️ Reusable business logic
* 🧩 Data, Domain & Presentation separation
* ⚙️ Gradle-based project configuration
* 🚀 GitHub Actions workflow

---

## 🏗️ Architecture

```text
                    CheckInCMP
                        │
        ┌───────────────┴───────────────┐
        │                               │
     Android                            iOS
        │                               │
        └───────────────┬───────────────┘
                        │
                Compose Multiplatform
                        │
        ┌───────────────┼───────────────┐
        │               │               │
      Data            Domain       Presentation
        │               │               │
        └───────────────┴───────────────┘
                        │
                Shared Kotlin Code
```

### Project Layers

| Layer            | Responsibility                             |
| ---------------- | ------------------------------------------ |
| **Presentation** | UI and presentation logic                  |
| **Domain**       | Business rules and application logic       |
| **Data**         | Data sources and repository implementation |
| **ComposeApp**   | Shared Compose application code            |
| **iOS App**      | iOS application entry point                |

The repository structure includes dedicated `composeApp`, `data`, `domain`, and `presentation` modules.

---

## 🛠️ Tech Stack

```text
Kotlin
Kotlin Multiplatform
Compose Multiplatform
Android
iOS
Gradle
Clean Architecture
GitHub Actions
```

---

## 📂 Project Structure

```text
CheckInCMP/
│
├── composeApp/          # Shared Compose application
│
├── data/                # Data layer
│
├── domain/              # Business logic
│
├── presentation/        # UI / presentation layer
│
├── iosApp/              # iOS application
│
├── .github/
│   └── workflows/       # CI/CD workflows
│
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

The repository currently uses `composeApp` for shared Compose code and an `iosApp` entry point for the iOS application.

---

## 🚀 Getting Started

### Clone

```bash
git clone https://github.com/Kavinrajan/CheckInCMP.git
cd CheckInCMP
```

### Android

Build the Android application with:

```bash
./gradlew :composeApp:assembleDebug
```

On Windows:

```powershell
.\gradlew.bat :composeApp:assembleDebug
```

These are based on the build commands documented in the repository.

### iOS

Open the `iosApp` directory in **Xcode** and run the application on an iOS simulator or device.

---

## 🎯 What This Project Demonstrates

**CheckInCMP** demonstrates practical experience with:

* Kotlin Multiplatform development
* Shared Android & iOS code
* Compose Multiplatform
* Layered application architecture
* Cross-platform UI development
* Gradle Kotlin DSL
* GitHub Actions
* Scalable project organization

---

## 🔮 Future Improvements

* [ ] Add offline data support
* [ ] Add persistent local storage
* [ ] Add automated unit tests
* [ ] Improve CI/CD pipeline
* [ ] Add authentication
* [ ] Add API integration
* [ ] Add application screenshots
* [ ] Add release builds for Android & iOS

---

## 👨‍💻 Author

**Kavinrajan S M**

**Senior Android Developer**

> Building modern mobile applications with Kotlin, Android, Kotlin Multiplatform, AI/ML and backend technologies.

---

⭐ **If you find this project useful, consider giving it a star.**
