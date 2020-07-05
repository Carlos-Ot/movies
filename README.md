# Serial
Serial is an app built to show TV Shows related info from [TMDB API](https://www.themoviedb.org/)

# Goals
- Study Architecture Advanced Structures, Hilt Dependency Injection and Modern Android Development.

# Architecture
    .
    ├── ...
    ├── data                    # Data layer.
    │   ├── source              
    │   │   ├── local           # Database, Cache.
    │   │   ├── remote          # Network layer.
    │   │   └── ...
    │   └── ...
    ├── domain                  # Business logic layer: repositories, models, dispatchers.
    ├── features                # View layer: Activity, Fragments, components
    │   ├── featureA
    │   │   ├── subFeatureA
    │   │   └── ...
    │   └── ...
    └── ...

# Stack
  - MVVM Architecture with DataBinding
  - Coroutines
  - Kotlin
  - Room
  - Hilt
  - Retrofit
  - Paging
  
 # Testing
  - AndroidX Test
  - Unit Test
  - MockK

# Contact:
c.ottobonijr@gmail.com

# TODO
 - [x] Add Unit Tests to Factories
 - [x] Add Unit Tests for DataSources and Repositories
 - [ ] Add Unit Tests for ViewModels
 - [ ] Add UI Testing after UI Refactor
 - [ ] Improve UI Feedback to User
 - [ ] Improve UI for Main Screen
 - [ ] Improve Show Details to show Seasons for Show
 - [ ] Add Android Navigation to achieve Single Activity 
 - [ ] Add Option to save a Show as Favorite
 - [ ] Migrate Paging to latest version
 
