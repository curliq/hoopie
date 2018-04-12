# Hoopie

### Purpose
A single page native Android app to display activities for kids from http://files.hoop.co.uk/test.json.

### Run
- Built with Android Studio 3.1.1 and tested on Android 8.0 (26), compatible up to Android 5.0 (21).
- [APK file](https://cdn.discordapp.com/attachments/420735220593983508/434126286764507156/app-debug.apk) for convinience

### Structure
This being a small app, it follows a simple structure.

`MainActivity` - Acitvities list view
`MainModel` - Class to handle RESTful services
`EventDetailActivity` - Avtivity details view

`MainActivity` calls a function in `MainModel` when it first starts, and listens for a response through an event using EventBus. On a successful response, the list of activities is saved locally using SharedPreferences and retrieved when the app is opened to show the activities instantly.

### Libraries used
- **EventBus** - Dispatch and listen for events across the app.
- **Butterknife** - Simplify binding views between the `xml` layouts and `java` objects.
- **Retrofit** - Perform HTTP requests.
- **Picasso** - Load venue's images and cache them.
- **JodaTime** - Convert ISO datetime strings to timezone aware date objects


*Made for Hoop :)*
