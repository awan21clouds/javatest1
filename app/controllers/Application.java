package controllers;

import api.Client;
import models.Party;
import models.list.Parties;
import models.list.Tags;
import play.db.DB;
import play.mvc.Controller;
import retrofit2.Response;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {
    public static void add() throws IOException, SQLException {
        String[] tags = params.getAll("tags[]");

        Response<Parties> response = Client.getParties(tags).execute();

        if (response.isSuccessful()) {
            List<Party> parties = response.body().items;

            for (Party party : parties) {
                Connection connection = DB.getConnection();

                PreparedStatement ps = connection.prepareStatement("INSERT INTO parties (first_name, last_name) VALUES (?, ?)");
                ps.setString(1, party.firstName);
                ps.setString(2, party.lastName);
                ps.execute();
            }

            redirect("/");
        } else {
            error();
        }
    }

    public static void index() {
        render();
    }

    public static void parties() throws SQLException {
        Connection connection = DB.getConnection();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM parties");

        ResultSet rs = ps.executeQuery();

        ArrayList<Party> parties = new ArrayList<>();

        while (rs.next()) {
            parties.add(new Party(rs.getString("first_name"), rs.getString("last_name")));
        }

        renderJSON(parties);
    }

    public static void tags() throws IOException {
        Response<Tags> response = Client.getTags().execute();

        if (response.isSuccessful()) {
            renderJSON(response.body().items);
        } else {
            error();
        }
    }
}
