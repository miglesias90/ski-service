package hello;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

@Route
public class MainView extends VerticalLayout {

	private final CustomerRepository repo;

	private final CustomerEditor customerEditor;

	private final CustomerCreator customerCreator;

	final Grid<ServiceOrder> grid;

	final TextField filter;

	private final Button addNewBtn;

	public MainView(CustomerRepository repo, CustomerEditor customerEditor, CustomerCreator customerCreator) {
		this.repo = repo;
		this.customerEditor = customerEditor;
		this.customerCreator = customerCreator;
		this.grid = new Grid<>(ServiceOrder.class);
		this.filter = new TextField();
		this.addNewBtn = new Button("Neuer Auftrag", VaadinIcon.PLUS.create());

		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		add(actions, grid, customerEditor, customerCreator);

		grid.setHeight("300px");
		grid.setColumns("id", "customername", "customeremail", "customerphone", "servicepriority", "servicestate", "servicetype", "startdate", "enddate");

		grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

		filter.setPlaceholder("Name filtern");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listCustomers(e.getValue()));

		// Connect selected ServiceOrder to customerEditor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			customerEditor.editCustomer(e.getValue());
		});

		// Instantiate and edit new ServiceOrder the new button is clicked
		addNewBtn.addClickListener(e -> customerCreator.createCustomer(new ServiceOrder()));

		// Listen changes made by the customerEditor and customerCreator, refresh data from backend
		customerEditor.setChangeHandler(() -> {
			customerEditor.setVisible(false);
			listCustomers(filter.getValue());
		});


		customerCreator.setChangeHandler(() -> {
			customerEditor.setVisible(false);
			listCustomers(filter.getValue());
		});

		// Initialize listing
		listCustomers(null);
	}

	// tag::listCustomers[]
	void listCustomers(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repo.findAll());
		}
		else {
			//grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
		}
	}
	// end::listCustomers[]

}
