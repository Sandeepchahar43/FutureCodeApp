📱 FutureCode Android Practical App

This project is developed as part of an Android practical test provided by My FutureCode IT Solutions Pvt Ltd.

🔗 API

https://myfuturecode.com/task/user.json

🎯 Features
📋 Display user list using RecyclerView + ConstraintLayout
✨ Shimmer loading animation
🌐 API integration using Retrofit
⚡ Asynchronous operations using Kotlin Coroutines
🧠 Clean architecture using MVVM
🔄 Data Binding for UI updates
🔍 Search functionality (by name & email)
💾 Room Database for local caching
📴 Offline support (shows cached data when no internet)
🖼️ Image loading using Glide (with caching)
🧱 Architecture
UI (Activity)
   ↓
ViewModel
   ↓
Repository
  ↙     ↘
API     Room DB
Repository acts as a single source of truth
Fetches data from API and caches it in Room
If API fails → loads data from local database
🛠️ Tech Stack
Kotlin
MVVM Architecture
Retrofit
Room Database
Coroutines
Data Binding
Glide
Shimmer (Facebook)
📸 Screens
User list with card UI
Search functionality
Shimmer loading state
Offline data display
⚙️ How it works
App fetches data from API
Stores data in Room database
Displays data in RecyclerView
If internet is not available:
Loads data from Room
Search works on local list (both online/offline)
🚀 Setup
Clone the repository
Open in Android Studio
Run the app
👨‍💻 Developer

Sandeep Chahar

⭐ Key Learning
MVVM architecture implementation
Offline-first app development
API + Room integration
Clean UI design and UX handling
