package exceptions;

import java.util.ArrayList;
import java.util.List;

import model.observer.IObserver;
import model.observer.ISubject;

public class ErrorHandler implements ISubject, IErrorHandler {

	List<IObserver> observeri;
	
	public ErrorHandler() {
		observeri = new ArrayList<>();
	}
	
	@Override
	public void addSubscriber(IObserver obs) {
		if(obs == null)
			return;
		if(this.observeri == null)
			this.observeri = new ArrayList<>();
		if(this.observeri.contains(obs))
			return;
		this.observeri.add(obs);
	}

	@Override
	public void removeSubscriber(IObserver obs) {
		if(obs == null || this.observeri == null || !this.observeri.contains(obs))
			return;
		this.observeri.remove(obs);
	}

	@Override
	public void notifySubscribers(Object notification) {
		for (IObserver obs : observeri) {
			obs.update((MyError)notification);
		}
	}

	@Override
	public void generateError(ErrorType errorType) {
		if(errorType == ErrorType.node_cannot_be_deleted) {
			notifySubscribers(new MyError(1, "Greska prilikom brisanja", "Nemoguce je obrisati izabrani cvor"));
		} else if(errorType == ErrorType.node_nothing_selected) {
			notifySubscribers(new MyError(2, "Greska prilikom selekcije", "Nijedan cvor nije selektovan"));
		} else if(errorType == ErrorType.element_not_selected) {
			notifySubscribers(new MyError(2, "Greska prilikom selekcije", "Nijedan element nije selektovan"));
		}
	}
}
