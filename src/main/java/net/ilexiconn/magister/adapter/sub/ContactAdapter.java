/*
 * Copyright (c) 2015 iLexiconn
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package net.ilexiconn.magister.adapter.sub;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import net.ilexiconn.magister.Magister;
import net.ilexiconn.magister.cache.ContainerCache;
import net.ilexiconn.magister.container.Contact;
import net.ilexiconn.magister.container.sub.Link;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends TypeAdapter<Contact[]> {
    public TypeAdapter<JsonElement> jsonElementTypeAdapter;
    public TypeAdapter<Link[]> linkTypeAdapter;
    public Magister magister;

    public ContactAdapter(Magister m) {
        magister = m;
    }

    public void write(JsonWriter jsonWriter, Contact[] value) throws IOException {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Contact[] read(JsonReader jsonReader) throws IOException {
        if (jsonElementTypeAdapter == null) {
            jsonElementTypeAdapter = magister.gson.getAdapter(JsonElement.class);
        }
        if (linkTypeAdapter == null) {
            linkTypeAdapter = magister.gson.getAdapter(Link[].class);
        }
        List<Contact> contactList = new ArrayList<>();
        JsonArray items;
        if (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            JsonObject contactObject = jsonElementTypeAdapter.read(jsonReader).getAsJsonObject();
            items = contactObject.getAsJsonArray("Items");
        } else {
            items = jsonElementTypeAdapter.read(jsonReader).getAsJsonArray();
        }
        for (JsonElement item : items) {
            JsonObject contact = item.getAsJsonObject();
            if (!contact.has("Links")) {
                int id = contact.get("Id").getAsInt();
                Contact c = ContainerCache.get(id + "", Contact.class);
                if (c != null) {
                    contactList.add(c);
                } else {
                    String code = contact.get("Docentcode").getAsString();
                    Contact[] contacts = magister.getTeacherInfo(code);
                    for (Contact s : contacts) {
                        System.out.println("Found teacher with id " + s.getId());
                        if (s.id == id) {
                            System.out.println("Done");
                            contactList.add(s);
                        }
                    }
                }
            } else {
                int id = contact.get("Id").getAsInt();
                Contact c = ContainerCache.get(id + "", Contact.class);
                if (c != null) {
                    contactList.add(c);
                    continue;
                }
                Link[] links = contact.get("Links") instanceof JsonNull ? null : linkTypeAdapter.fromJsonTree(contact.getAsJsonArray("Links"));
                String surname = contact.get("Achternaam").getAsString();
                String firstName = contact.get("Voornaam").getAsString();
                String surnamePrefix = contact.get("Tussenvoegsel") instanceof JsonNull ? null : contact.get("Tussenvoegsel").getAsString();
                String fullName = contact.get("Naam").getAsString();
                int type = contact.get("Type").getAsInt();
                contactList.add(new Contact(id, links, surname, firstName, surnamePrefix, fullName, type));
            }
        }
        return contactList.toArray(new Contact[contactList.size()]);
    }
}