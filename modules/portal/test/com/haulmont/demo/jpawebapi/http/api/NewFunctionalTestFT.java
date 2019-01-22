package com.haulmont.demo.jpawebapi.http.api;

import com.haulmont.cuba.core.sys.persistence.PostgresUUID;
import com.haulmont.demo.jpawebapi.core.app.PortalTestService;
import com.meterware.httpunit.*;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NewFunctionalTestFT {

    private static final String DB_URL = "jdbc:postgresql://localhost/refapp_6";
    private static final String URI_BASE = "http://localhost:8080/";
    private static final String apiPath = "refapp-portal";

    private static final String userLogin = "admin";
    private static final String userPassword = "admin";

    private WebConversation conv;
    private String sessionId;
    private Connection conn;
//    private DataSet dirtyData = new DataSet();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        HttpUnitOptions.setExceptionsThrownOnErrorStatus(true);
        HttpUnitOptions.setScriptingEnabled(false);
        HttpUnitOptions.setExceptionsThrownOnScriptError(false);

        conv = new WebConversation();
        sessionId = login(userLogin, userPassword);

        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(DB_URL, "root", "root");

        prepareDb();
    }

    @After
    public void tearDown() throws Exception {
//        logout();
//        dirtyData.cleanup(conn);

        if (conn != null) {
            conn.close();
        }
    }

    public void prepareDb() throws SQLException {

        executePrepared("truncate REFAPP_TEST_ENTITY");

        executePrepared("insert into REFAPP_TEST_ENTITY(id) values (?)",
                new PostgresUUID(UUID.fromString("b3a5b887-d595-466c-98e1-40629acc2c3e"))
        );

    }

//    @Test
//    public void find_JSON() throws Exception {
//        String testEnttyIdString = "b3a5b887-d595-466c-98e1-40629acc2c3e";
//        WebResponse response = GET(apiPath + "/api/find.json?e=refapp_TestEntity-" + testEnttyIdString + "&s=" + sessionId,
//                "charset=UTF-8");
//        JSONObject entity = new JSONObject(response.getText());
//        assertEquals("refapp_TestEntity-" + testEnttyIdString, entity.getString("id"));
//    }

//    @Test
//    public void commit_insertInstance_autogenerateUUID_JSON() throws Exception {
//        String json = prepareFile("new_test_entity.json", MapUtils.asMap(
//                "$ENTITY-TO_BE_REPLACED_ID$", "NEW-refapp_TestEntity")
//        );
//        WebResponse response = POST("refapp-portal/api/commit?" + "s=" + sessionId, json,
//                "application/json;charset=UTF-8");
//        JSONArray res = new JSONArray(response.getText());
//        String id = res.getJSONObject(0).getString("id");
//        assertTrue(id.startsWith("refapp_TestEntity-"));
//
//        //delete created car
//        deleteCar(id.substring("refapp_TestEntity-".length()));
//    }


    @Test
    public void testReturnEntityListJSON() throws IOException, SAXException, JSONException {
        WebResponse response = invokeServiceMethodGet("json", "finAllEntities");

        JSONObject responseObject = new JSONObject(response.getText());
        JSONArray resultArray = responseObject.getJSONArray("result");
        assertNotNull(resultArray);
        System.out.println("Service result : " + response.getText());
        System.out.println("Service size : " + resultArray.length());
//        assertEquals(2, resultArray.length());

//        JSONObject carObject = resultArray.getJSONObject(0);

//        String colourId = carObject.getJSONObject("colour").getString("id");
//        assertEquals("ref$Colour-" + colourUuidString, colourId);
    }

    private String login(String login, String password) throws JSONException, IOException, SAXException {
        JSONObject loginJSON = new JSONObject();
        loginJSON.put("username", login);
        loginJSON.put("password", password);
        loginJSON.put("locale", "ru");

        WebResponse response = POST("refapp-portal/api/login",
                loginJSON.toString(), "application/json;charset=UTF-8");
        return response.getText();
    }

    private void logout() throws JSONException {
        if (sessionId == null)
            return;
        try {
            GET("refapp-portal/api/logout?session=" + sessionId, "charset=UTF-8");
        } catch (Exception e) {
            System.out.println("Error on logout: " + e);
        }
    }

    private WebResponse POST(String uri, String s, String contentType) throws IOException, SAXException {
        ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
        return conv.sendRequest(new PostMethodWebRequest(URI_BASE + uri, is, contentType));
    }

    private WebResponse GET(String uri, String acceptedFormat) throws IOException, SAXException {
        GetMethodWebRequest request = new GetMethodWebRequest(URI_BASE + uri);
        request.setHeaderField("Accept", acceptedFormat);
        return conv.sendRequest(request);
    }


    private void deleteCar(String id) throws SQLException {
        executePrepared("delete from REFAPP_TEST_ENTITY where id = ?",
                new PostgresUUID(UUID.fromString(id)));
    }

    private void executePrepared(String sql, Object... params) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            int count = stmt.executeUpdate();
        }
    }

    protected String prepareFile(String fileName, Map<String, String> replacements) throws IOException {
        InputStream is = getClass().getResourceAsStream(fileName);
        String fileContent = IOUtils.toString(is);
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            fileContent = fileContent.replace(entry.getKey(), entry.getValue());
        }
        return fileContent;
    }


    private WebResponse invokeServiceMethodGet(String type, String methodName, String... params) throws IOException, SAXException {
        String serviceName = PortalTestService.NAME;
        StringBuilder sb = new StringBuilder();
        sb.append("refapp-portal/api/service.").append(type);
        sb.append("?s=").append(sessionId)
                .append("&service=").append(serviceName)
                .append("&method=").append(methodName);
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            sb.append("&param").append(i).append("=").append(param);
        }
        return GET(sb.toString(), "text/html;charset=UTF-8");
    }
}
