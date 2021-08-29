package model.observer;

public interface ISubject {
	void addSubscriber(IObserver sub);
	void removeSubscriber(IObserver sub);
	void notifySubscribers(Object notification);
}
