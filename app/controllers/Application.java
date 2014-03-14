package controllers;

import models.Contacts;
import models.Person;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result list() {
        Set<Person> contacts = Person.findAll();
        return ok(list.render(contacts));
    }

    private static final Form<Person> personForm = form(Person.class);
    public static Result showBlank() {
        return ok(show.render(personForm));
    }

    public static Result show(Long ean) {
        final Person person = Person.findByEan(ean);
        if (person == null) {
            return notFound(String.format("Person %s does not exist.", ean));
        }

        Form<Person> filledForm = personForm.fill(person);
        return ok(modify.render(filledForm, ean));
    }

    public static Result add() {
        Form<Person> boundForm = personForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(show.render(boundForm));
        }

        Person person = boundForm.get();
        if (person.ean == null) {
            flash("error", "Please enter in a value.");
            return badRequest(show.render(boundForm));
        }

        Person.add(person);
        flash("success", String.format("Saved contact %s", person));

        return redirect(routes.Application.list());
    }

    public static Result save(Long ean) {
        Form<Person> boundForm = personForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(modify.render(boundForm, ean));
        }

        Person person = boundForm.get();
        if (person.ean == null) {
            flash("error", "Please enter in a value.");
            return badRequest(modify.render(boundForm, ean));
        }
        Set<Person> contacts = Person.findAll();
        Person removeme = Person.findByEan(ean);
        Person.remove(removeme);
        Person.add(person);
        flash("success", String.format("Saved contact %s", person));

        return redirect(routes.Application.list());
    }

}
