package ch.juventus.skiservice.ui;

import ch.juventus.skiservice.data.*;
import ch.juventus.skiservice.ui.view.ServiceOrderCreator;
import ch.juventus.skiservice.ui.view.ServiceOrderEditor;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;


@Route
public class MainView extends VerticalLayout {

	private final ServiceOrderRepository repo;
	private final ServicePriorityRepository priorityRepo;
	private final ServiceStateRepository stateRepo;
	private final ServiceTypeRepository typeRepo;

	private final ServiceOrderEditor serviceOrderEditor;

	private final ServiceOrderCreator serviceOrderCreator;

	final Grid<ServiceOrder> grid;

	private final Button addNewBtn;

	public MainView(ServiceOrderRepository repo, ServicePriorityRepository priorityRepo, ServiceStateRepository stateRepo, ServiceTypeRepository typeRepo, ServiceOrderEditor serviceOrderEditor, ServiceOrderCreator serviceOrderCreator) {
		this.repo = repo;
		this.priorityRepo = priorityRepo;
		this.stateRepo = stateRepo;
		this.typeRepo = typeRepo;
		this.serviceOrderEditor = serviceOrderEditor;
		this.serviceOrderCreator = serviceOrderCreator;
		this.grid = new Grid<>();
		this.addNewBtn = new Button("Neuer Auftrag", VaadinIcon.PLUS.create());

		// build layout
		HorizontalLayout actions = new HorizontalLayout(addNewBtn);
		add(actions, grid, serviceOrderEditor, serviceOrderCreator);

		grid.setHeight("300px");
		grid.addColumn(ServiceOrder::getId).setHeader("ID").setWidth("50px").setFlexGrow(0).setSortable(true);
		grid.addColumn(ServiceOrder::getCustomername).setHeader("Name");
		grid.addColumn(ServiceOrder::getCustomeremail).setHeader("Email");
		grid.addColumn(ServiceOrder::getCustomerphone).setHeader("Telefon");
		grid.addColumn(ServiceOrder::getServicepriority).setHeader("Priorität").setSortable(true);
		grid.addColumn(ServiceOrder::getServicestate).setHeader("Status");
		grid.addColumn(ServiceOrder::getServicetype).setHeader("Dienstleistung");
		grid.addColumn(ServiceOrder::getStartdate).setHeader("Begin");
		grid.addColumn(ServiceOrder::getEnddate).setHeader("Ende");


		// Hook logic to components

		// Connect selected ServiceOrder to customerEditor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			serviceOrderEditor.showUpdateEditor(e.getValue());
		});

		// Instantiate and edit new ServiceOrder the new button is clicked
		addNewBtn.addClickListener(e -> serviceOrderCreator.showCreateEditor(new ServiceOrder()));

		// Listen changes made by the customerEditor and customerCreator, refresh data from backend
		serviceOrderEditor.setChangeHandler(() -> {
			serviceOrderEditor.setVisible(false);
			listServiceOrders();
		});


		serviceOrderCreator.setChangeHandler(() -> {
			serviceOrderEditor.setVisible(false);
			listServiceOrders();
		});

		// Initialize listing
		listServiceOrders();
	}


	void listServiceOrders() {
		grid.setItems(repo.findAll());
	}
	// end::listCustomers[]

}
