package ch.juventus.hf.skiservice.ui;

import ch.juventus.hf.skiservice.entity.Priority;
import ch.juventus.hf.skiservice.entity.Service;
import ch.juventus.hf.skiservice.entity.Serviceorder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.ArrayList;
import java.util.Collection;

@Route
public class MainView extends VerticalLayout {

    //TODO Repo angeben

    private final ServiceorderEditor editor;

    final Grid<Serviceorder> grid;

    private final Button addNewBtn;


    public MainView(ServiceorderEditor editor) {
        this.editor = editor;
        this.grid = new Grid<>(Serviceorder.class);
        this.addNewBtn = new Button("New service order", VaadinIcon.PLUS.create());

        //build layout
        HorizontalLayout actions = new HorizontalLayout(addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "customername");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editServiceOrder(e.getValue());
        });


        // Instantiate and edit new Customer the new button is clicked
        //Contstructor
        addNewBtn.addClickListener(e -> editor.editServiceOrder(new Serviceorder("")));


        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listServiceorder();
        });
    }


    void listServiceorder() {

        //MOCK
        Serviceorder serviceorder = new Serviceorder("Miguel Iglesias");

        //MOCK
        Collection<Serviceorder> collection = new ArrayList<>();
        collection.add(serviceorder);

        grid.setItems(collection);
    }
}
