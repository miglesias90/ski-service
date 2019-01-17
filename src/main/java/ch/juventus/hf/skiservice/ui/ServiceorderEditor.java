package ch.juventus.hf.skiservice.ui;

import ch.juventus.hf.skiservice.entity.Serviceorder;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;


@SpringComponent
@UIScope
public class ServiceorderEditor extends VerticalLayout implements KeyNotifier {


    private Serviceorder serviceorder;
    /* Fields to edit properties in Customer entity */
    TextField firstName = new TextField("Name");
    TextField lastName = new TextField("Email");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Serviceorder> binder = new Binder<>(Serviceorder.class);
    private ServiceorderEditor.ChangeHandler changeHandler;

    @Autowired
    private ServiceorderEditor() {
        add(firstName, lastName, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");


        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editServiceOrder(serviceorder));
        setVisible(false);
    }


    void delete() {
        //repository.delete(customer);
        changeHandler.onChange();
    }

    void save() {
        //repository.save(customer);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }


    public final void editServiceOrder(Serviceorder s) {
        if (s == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = s.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            //TODO customer = repository.findById(c.getId()).get();
        }
        else {
            serviceorder = s;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(serviceorder);

        setVisible(true);

        // Focus first name initially
        firstName.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

}
