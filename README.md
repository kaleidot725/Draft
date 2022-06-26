# Draft

This repository contains a note-taking application that is implemented MVI architecture using Koin, Jetpack Compose, Navigation Compose, Orbit MVI.

<img src="docs/title.png">

## ✨Features

This application has next features.

- Create notebook and memo.
- Delete notebook and memo.

## 🏢Architecture

This application is implemented by MVI architecture based on MVVM + Repository Pattern.

<img src="docs/architecture.png" width="800">

## 💽 DataFlow

This application is implemented MVI data flow by Orbit MVI

<img src="docs/dataflow.png" width="500">

## 📦Module

This application has multi modules. For details of each module, please refer to the figure below.

| Name   | Details                                                      |
| ------ | ------------------------------------------------------------ |
| app    | Implement application user interface and navigation management logic. |
| domain | Implement business logic for EmoMemo               |
| data   | Define persistence data structure and implement persistence logic. |

## 📚Library

This application uses the libraries below.

| Name                  | Link                                                         |
| --------------------- | ------------------------------------------------------------ |
| Koin                  | https://insert-koin.io                                       |
| Jetpack Compose       | https://developer.android.com/jetpack/compose                |
| Navigation Compose    | https://developer.android.com/jetpack/compose/navigation     |
| Orbit MVI             | https://orbit-mvi.org                                        |
| Room                  | https://developer.android.com/training/data-storage/room?hl=ja |

## ⭐Reference

| Name                                                         | Link                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Koin \| Jetpack Compose                                      | https://insert-koin.io/docs/reference/koin-android/compose   |
| Navigation Compose \| Navigating With Compose                | https://developer.android.com/jetpack/compose/navigation     |
| Orbit MVI \| Android ViewModel module                        | https://orbit-mvi.org/Android-ViewModel/overview             |
| MVI Architecture - Android Tutorial for Beginners - Step By Step Guide | https://blog.mindorks.com/mvi-architecture-android-tutorial-for-beginners-step-by-step-guide |
 
## 💡License

```
Copyright (c) 2022 Yusuke Katsuragawa

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
