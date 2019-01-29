package hello;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
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
public class CustomerCreator extends VerticalLayout implements KeyNotifier {

	private final CustomerRepository repository;

	/**
	 * The currently edited serviceOrder
	 */
	private ServiceOrder serviceOrder;

	/* Fields to edit properties in ServiceOrder entity */
	TextField customerName = new TextField("Name");
	TextField customerEmail = new TextField("Email");
	TextField customerPhone = new TextField("Telefon");
	TextField priority = new TextField("Priotität");
	TextField status = new TextField("Status");
	TextField serviceType = new TextField("Diensleitung");
	TextField startDate = new TextField("Startdatum");
    TextField endtDate = new TextField("Enddatum");


	/* Action buttons */
	// TODO why more code?
	Button save = new Button("Save", VaadinIcon.CHECK.create());
	Button cancel = new Button("Cancel");
	HorizontalLayout actions = new HorizontalLayout(save, cancel);

	Binder<ServiceOrder> binder = new Binder<>(ServiceOrder.class);
	private ChangeHandler changeHandler;

	@Autowired
	public CustomerCreator(CustomerRepository repository) {
		this.repository = repository;



		add(customerName, customerEmail, customerPhone, priority, status, serviceType, startDate, endtDate, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);

		save.getElement().getThemeList().add("primary");

		addKeyPressListener(Key.ENTER, e -> save());

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> save());
		cancel.addClickListener(e -> createCustomer(serviceOrder));
		setVisible(false);
	}


	void save() {
		repository.save(serviceOrder);
		changeHandler.onChange();
	}

	public interface ChangeHandler {
		void onChange();
	}

	public final void createCustomer(ServiceOrder c) {
		if (c == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = c.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			serviceOrder = repository.findById(c.getId()).get();
		}
		else {
			serviceOrder = c;
		}
		cancel.setVisible(persisted);

		// Bind serviceOrder properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(serviceOrder);

		setVisible(true);

		// Focus first name initially
		customerName.focus();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		changeHandler = h;
	}

}
