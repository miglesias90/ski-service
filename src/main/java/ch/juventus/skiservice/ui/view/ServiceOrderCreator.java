package ch.juventus.skiservice.ui.view;

import ch.juventus.skiservice.data.ServiceOrder;
import ch.juventus.skiservice.data.ServiceOrderRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A simple example to introduce building forms. As your real application is probably much
 * more complicated than this example, you could re-use this form in multiple places. This
 * example component is only used in MainView.
 * <p>
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX.
 */
@SpringComponent
@UIScope
public class ServiceOrderCreator extends VerticalLayout implements KeyNotifier {

	private final ServiceOrderRepository repository;

	/**
	 * The currently edited serviceOrder
	 */
	private ServiceOrder serviceOrder;

	/* Fields to edit properties in ServiceOrder entity */
	TextField customerName = new TextField("Name");
	TextField customerEmail = new TextField("Email");
	TextField customerPhone = new TextField("Telefon");
	ComboBox<String> serviceType = new ComboBox<String>("Dienstleistung");
    ComboBox<String> servicePriority = new ComboBox<String>("Priorität");


	/* Action buttons */
	// TODO why more code?
	Button save = new Button("Save", VaadinIcon.CHECK.create());
	Button cancel = new Button("Cancel");
	HorizontalLayout actions = new HorizontalLayout(save, cancel);

	Binder<ServiceOrder> binder = new Binder<>(ServiceOrder.class);
	private ChangeHandler changeHandler;

	@Autowired
	public ServiceOrderCreator(ServiceOrderRepository repository) {
		this.repository = repository;

        serviceType.setItems("Kleiner Service", "Grosser Service","Rennski-Service","Bindung montieren und einstellen","Fell zuschneiden","Heisswachsen");
        servicePriority.setItems("Tief", "Standard","Express");
		add(customerName, customerEmail, customerPhone, serviceType, servicePriority, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);

		save.getElement().getThemeList().add("primary");

		addKeyPressListener(Key.ENTER, e -> save());

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> save());
		cancel.addClickListener(e -> cancel());
		setVisible(false);
	}


	void save() {
		//repository.save(serviceOrder);
        createServiceOrder(serviceOrder);
		changeHandler.onChange();
	}


    void cancel() {
        setVisible(false);
    }

	public interface ChangeHandler {
		void onChange();
	}


	public final void showCreateEditor(ServiceOrder s) {
	    if (s != null) {
            setVisible(true);
            binder.setBean(s);
            this.serviceOrder=binder.getBean();
        }

    }


	public final void createServiceOrder(ServiceOrder s) {
		if (s != null) {
            repository.insertService(s.getCustomername(), s.getCustomeremail(), s.getCustomerphone(), s.getServicetype(), s.getServicepriority());

            setVisible(false);
		}
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		changeHandler = h;
	}

}
