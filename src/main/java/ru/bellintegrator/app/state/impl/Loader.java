package ru.bellintegrator.app.state.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.data.DataManager;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.state.ILoadFromStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by neste_000 on 13.07.2017.
 */
public class Loader implements ILoadFromStore {

    private static final Logger log = LoggerFactory.getLogger(Loader.class);

    @Override
    public void load() {

        List<Contact> contacts;
        List<Group> groups;
        DataManager dataManager = DataManager.getInstance();

        try (FileInputStream fileInputStream = new FileInputStream("state");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            contacts = (List<Contact>) objectInputStream.readObject();
            groups = (List<Group>) objectInputStream.readObject();

            dataManager.getContactObservableList().addAll(contacts);
            dataManager.getGroupObservableList().addAll(groups);

            objectInputStream.close();

            log.debug("deserialization:" + contacts);
            log.debug("deserialization:" + groups);

        } catch (FileNotFoundException e) {
            log.debug(e.getMessage());

        } catch (IOException e) {
            log.debug(e.getMessage());

        } catch (ClassNotFoundException e) {
            log.debug(e.getMessage());
        }
    }

}
