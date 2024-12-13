public interface Engine {
    void update();
}

// Can an interface be instantiated?
// No, an interface cannot be instantiated directly.To use an interface, we must firstly create a class that implements it, and only after that we can instantiate that class.

// Under what condition can a class implement the Engine interface?
// A class can implement the Engine interface only if they also implement the update() method.

// Can a class implement multiple interfaces?
// Yes, a class can implement multiple interfaces as long as it provides implementations for all the methods in each interface.