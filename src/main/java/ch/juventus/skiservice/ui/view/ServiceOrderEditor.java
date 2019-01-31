package ch.juventus.skiservice.ui.view;

import ch.juventus.skiservice.data.ServiceOrder;
import ch.juventus.skiservice.data.ServiceOrderRepository;
import ch.juventus.skiservice.data.ServiceState;
import ch.juventus.skiservice.data.ServiceStateRepository;
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

import java.util.ArrayList;
import java.util.Iterator;

@SpringComponent
@UIScope
public class ServiceOrderEditor extends VerticalLayout implements KeyNotifier {

	private final ServiceOrderRepository repository;
	private final ServiceStateRepository stateRepository;

	/**
	 * The currently edited serviceOrder
	 */
	private ServiceOrder serviceOrder;

	/* Fields to edit properties in ServiceOrder entity */
	TextField customerName = new TextField("Name");
	TextField customerEmail = new TextField("Email");
	TextField customerPhone = new TextField("Telefon");
	ComboBox<String> status = new ComboBox<String>("Status");

	/* Action buttons */
	Button save = new Button("Save", VaadinIcon.CHECK.create());
	Button cancel = new Button("Cancel");
	HorizontalLayout actions = new HorizontalLayout(save, cancel);

	Binder<ServiceOrder> binder = new Binder<>(ServiceOrder.class);
	private ChangeHandler changeHandler;

	@Autowired
	public ServiceOrderEditor(ServiceOrderRepository repository, ServiceStateRepository stateRepository) {
		this.repository = repository;
		this.stateRepository = stateRepository;

		ArrayList<String> states= new ArrayList<>();
		Iterator stateIt = this.stateRepository.findAll().iterator();
		while (stateIt.hasNext()) {
			states.add(((ServiceState)stateIt.next()).getServicestate());
		}

		status.setItems(states);
		add(customerName, customerEmail, customerPhone, status, actions);

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
        updateServiceOrder(serviceOrder);
        changeHandler.onChange();
	}

	void cancel() {
		setVisible(false);
	}

	public interface ChangeHandler {
		void onChange();
	}


    public final void showUpdateEditor(ServiceOrder s) {
        if (s != null) {
            setVisible(true);
            binder.setBean(s);
            this.serviceOrder=binder.getBean();
            status.setValue(s.getServicestate());
        }

    }

    public final void updateServiceOrder(ServiceOrder s) {
        if (s != null) {
            repository.updateService(s.getId(), s.getCustomername(), s.getCustomeremail(), s.getCustomerphone(), status.getValue());
            setVisible(false);
        }
    }


	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		changeHandler = h;
	}

}
