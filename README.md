# Milestone 1 - Munch

## Table of Contents

1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Progress GIFs](#Progress-GIFs)

## Overview

### Description

Munch is a mobile Android application that allows users to input various ingredients in a search bar that saves and queries for recipes online that match the listed ingredients.

### App Evaluation

[Evaluation of your app across the following attributes]

- **Category: Food & Drink**
- **Mobile: Android app, uses phone camera**
- **Story: Allows users to share & discover recipes using ingredients they want to use**
- **Market: people who are interested in cooking and want to easily find recipes based on the ingredients they have on hand. This could include home cooks, students, busy professionals, or anyone looking to simplify their meal planning and preparation.**
- **Habit: Builds a habit for the user by providing a quick and convenient solution for finding recipes based on available ingredients, which can save time and reduce decision fatigue associated with meal planning. Over time, users may develop a habit of using the app as a go-to resource for meal ideas and planning.**
- **Scope: Includes designing and developing a mobile application that allows users to input various ingredients in a search bar, which saves and queries for recipes online that match the listed ingredients. The app also includes features such as user accounts, saving favorite recipes, recipe ratings and reviews, and social sharing.**

## Product Spec

### 1. User Features (Required and Optional)

**Required Features**

- [x] Search bar that allows users to input various ingredients and search for matching recipes online 
- Recipe display page that shows the recipe title, ingredients, cooking instructions, and images
- Save recipe function that allows users to save favorite recipes for later use
- User account system that allows users to save preferences and access saved recipes across devices
- [x] Integration with third-party recipe APIs or development of proprietary algorithms for recipe matching
- Responsive and user-friendly UI that is optimized for mobile devices
- Take and save pictures of user's attempt at the recipes
- Post pictures to a social feed
- Rating and review system that allows users to rate and review recipes and see other user reviews

**Stretch Features**

- Autocompleted ingredients searching
- "I'm feeling lucky" button that returns a random recipe
- Share recipes to 3rd party messaging and social apps
- A step-by-step guide that takes users through each step of a recipe

### 2. Screen Archetypes

- Recipe Search Bar
  - Enter ingredients in search bar
  - Perform search to find list of recipes with the ingredients
  - Scroll through list of recipes and choose recipes to view in detail or save
- Login Screen
  - Allow user to enter username and password
  - Allow user to create an account for first time use
  - Authenticate user and load data associated with that user's account in various feeds
- Recipe Feed Screen
  - Scrollable list of Saved Recipes
  - Allow User to access details about recipes
  - Allow User to removed saved recipes from list
  - Allow User to rate recipes in the list
- Social Feed Screen
  - Scrollable list of posted pictures of recipes
  - Allow users to post pictures of recipes they attempted
  - Allow users to see others posts
- Recipe Detail Screen
  - Detail Screen containing recipe title, ingredients, instructions and images
  - Allow user to view step by step instructions to make recipe

### 3. Navigation

**Tab Navigation** (Tab to Screen)

- Recipe Search
- Recipe Feed
- Social Feed

**Flow Navigation** (Screen to Screen)

- Login Screen
  - User logs in or signs up to navigate to other screens
- Recipe Search Screen
  - Searched recipes can be clicked to navigate to Detail Screen
  - Nav tab to navigate to Recipe Feed Screen
  - Nav tab to navigate to Social Feed Screen
- Recipe Feed Screen
  - Searched recipes can be clicked to navigate to Detail Screen
  - Nav tab to navigate to Recipe Feed Screen
  - Nav tab to navigate to Social Feed Screen
- Social Feed Screen
  - Nav tab to navigate to Recipe Feed Screen
  - Nav tab to navigate to Social Feed Screen
- Recipe Detail Screen
  - If accessed from Recipe Search Screen, back button navigates back to Recipe Search Screen
  - If accessed from Recipe Feed Screen, back button navigates back to Recipe Feed Screen

## Wireframes

<img width="533" alt="Screenshot 2023-03-23 at 5 16 12 PM" src="https://user-images.githubusercontent.com/26809090/227365279-d9d75663-0cb1-4c4c-b593-16d58a6d8dca.png">


## Progress GIFs

<img src='https://i.imgur.com/V0qYo6J.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
Milestone 1
This gif is to show that the recipe search bar layout has been made, some functionality is missing but the task to create the layout for Milestone 1 has been completed. A Recyclerview is also in this layout but is not being properly populated yet as the recycler adapter has not been implemented yet.

![Sprint1](https://user-images.githubusercontent.com/56842734/229668604-f6cff429-4b19-4879-9f81-1540100242f6.gif)
This gif is to show the recipe recycler view layouts that will populated with the saved recipe screen, inside the recycler view their will be the recipe name, photo, and rating. A removal button is also in place. Functionality of these layouts will be done in the sprints to come.


<img src='https://i.imgur.com/HaYKQCz.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
Milestone 2
This gif contains the data model files that are going to be used to access the JSON data returned from the API calls necessary for our different features. The first is for our Recipe Search function and contains data classes to model the return structure of the JSON. The second contains the data classes for the Recipe Instructions which will be used on the detailed view of the saved recipes and will contain instructions to actually create the desired recipe the user has selected.


<img src='https://i.imgur.com/nFpodSa.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
Milestone 3
This is the gif of the current progress for Milestone 3. Currently JSON data is being retrieved from the spoonacular API once users input a list of ingredients into the search bar and then hit search. Using the spoonacular API it finds recipes the contain some if not all of the ingredients users enter into the search bar. Save functionality and populating the user's recipe feed are not complete and will require database read and writes that are not fully functional yet.

(https://i.imgur.com/hPsmkQw.gif)](https://i.imgur.com/hPsmkQw.gifv)
Milestone 4
This gif shows the progress of Milestone 4. Currently 5 screens are working with the remaining saved recipes screen and saved detail screen to be finished. Users can now login or sign up for an account on the sign in page. They are then directed to the search screen where recipe searches can be made. If a recipe is clicked on the list of ingredients are displayed and the instructions, along with the image. There is also a save recipe button that currently does save the recipe to our database and can be retrieved depending on the user who is signed in. THere are also the Post screen which allows users to take pictures of what recipes they have made, and add a title. Once this is complete users can post the image and it will show up on the Feed Screen. This screen contains the posts made by all users of the app and is updated in realtime as soon as a user posts.

