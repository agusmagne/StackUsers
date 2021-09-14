# StackUsers
Test app displays users from StackExchange


### Architecture
Simple MVVM architecture. I've implemented a centralized event system using the classes Event, VoidEvent, and BaseActivity. Event and VoidEvent classes may also be used for any other single one time events one may want to trigger. To do so simply create a new MutableLiveData(Event(T)) and observe its changes with liveData.observeEvent(owner, observer).
All Retrofit calls should be called within the "safeCall" method which the ViewModel should inherit from BaseViewModel. This method allows for safe HTTP calls and a centralized way of catching different exceptions. More details on the implementation of different type of errors can be made.

### Structure
The folder structure separates most things regarding concern, with the exception of ViewModels.
ViewModels are kept inside the same folder as their view counterpart so that its easier to access each part. By experience I find this detail important and comfortable.

### Regarding interface
I didn't put too much work into the interface. To me personally structure and architecture is more important, and I have dedicated from 5 to 6 hours to this small project already. I think my most proud work on interface and design is this and it's worth checking out: https://youtu.be/llLyX5L2YGw

### No unit tests were implemented
Even though I do have knowledge of basic unit testing with JUnit and Mockito for back-end servers, the specific Android implementation changes quite a bit, and the UI testing with Espresso is a whole other world.
