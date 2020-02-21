package com.example.httpexample.repository;

import com.example.httpexample.domain.Address;
import com.example.httpexample.domain.Company;
import com.example.httpexample.domain.Geo;
import com.example.httpexample.domain.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class JsonPlaceholderParser {
    JsonPlaceholderRepository repository = new JsonPlaceholderRepository();

    public ArrayList<User> getUsers() throws IOException, JSONException {
        JSONArray arrayUsers = new JSONArray(repository.getRawJson("/users")) ;

        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < arrayUsers.length(); i++) {
            JSONObject userRootJson = arrayUsers.getJSONObject(i);
            JSONObject addressJson = userRootJson.getJSONObject("address");
            JSONObject geoJson = addressJson.getJSONObject("geo");
            JSONObject companyJson = userRootJson.getJSONObject("company");

            int id = userRootJson.getInt("id");
            String name = userRootJson.getString("name");
            String userName = userRootJson.getString("username");
            String email = userRootJson.getString("email");
            String phone = userRootJson.getString("phone");
            String website = userRootJson.getString("website");

            String addressStreet = addressJson.getString("street");
            String addressSuite = addressJson.getString("suite");
            String addressCity = addressJson.getString("city");
            String addressZipcode = addressJson.getString("zipcode");

            double geoLat = geoJson.getDouble("lat");
            double geoLng = geoJson.getDouble("lng");

            String companyName = companyJson.getString("name");
            String companyCatchPhrase = companyJson.getString("catchPhrase");
            String companyBs = companyJson.getString("bs");

            Geo geo = new Geo(geoLat, geoLng);
            Address address = new Address(addressStreet, addressSuite, addressCity, addressZipcode, geo);
            Company company = new Company(companyName, companyCatchPhrase, companyBs);
            User user = new User(id, name, userName, email, address, phone, website, company);

            users.add(user);
        }


        return users;
    }

}