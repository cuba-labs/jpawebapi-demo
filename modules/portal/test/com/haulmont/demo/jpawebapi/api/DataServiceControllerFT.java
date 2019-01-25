package com.haulmont.demo.jpawebapi.api;

import com.haulmont.addon.jpawebapi.restapi.XMLConverter;
import com.haulmont.bali.util.Dom4j;
import com.haulmont.cuba.core.sys.persistence.PostgresUUID;
import com.haulmont.demo.jpawebapi.core.app.PortalTestService;
import com.meterware.httpunit.*;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataServiceControllerFT {

    private static final String DB_URL = "jdbc:postgresql://localhost/jpademo";
    private static final String URI_BASE = "http://localhost:8080/";
    private static final String apiPath = "app-portal/jpawebapi";

    private static final String userLogin = "admin";
    private static final String userPassword = "admin";

    private WebConversation conv;
    private String sessionId;
    private Connection conn;
    private DataSet dirtyData = new DataSet();

    private String firstId;
    private String secondId;
    private String thirdId;
    private String forthId;

    private String toBeDeletedByJson;
    private String toBeDeletedByXml;

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
        logout();
        dirtyData.cleanup(conn);

        if (conn != null) {
            conn.close();
        }
    }

    public void prepareDb() throws SQLException {

        executePrepared("truncate JPADEMO_TEST_ENTITY");

        // Prepare list of jsons com.haulmont.demo.jpawebapi.api.DataServiceControllerFT.testReturnEntityListJSON
        firstId = dirtyData.createEntityId().toString();
        secondId = dirtyData.createEntityId().toString();
        executePrepared("insert into JPADEMO_TEST_ENTITY(id, version) values (?,?)",
                new PostgresUUID(UUID.fromString(firstId)),
                1
        );

        executePrepared("insert into JPADEMO_TEST_ENTITY(id, version) values (?,?)",
                new PostgresUUID(UUID.fromString(secondId)),
                1
        );

        // Prepare Entity update com.haulmont.demo.jpawebapi.api.DataServiceControllerFT.testPostWithEntityParamJSON
        thirdId = dirtyData.createEntityId().toString();
        executePrepared("insert into JPADEMO_TEST_ENTITY(id, F_NAME, version) values (?,?,?)",
                new PostgresUUID(UUID.fromString(thirdId)),
                "TestFName",
                1
        );

        forthId = dirtyData.createEntityId().toString();
        executePrepared("insert into JPADEMO_TEST_ENTITY(id, F_NAME, version) values (?,?,?)",
                new PostgresUUID(UUID.fromString(forthId)),
                "TestFName",
                1
        );

        toBeDeletedByJson = dirtyData.createEntityId().toString();
        executePrepared("insert into JPADEMO_TEST_ENTITY(id, F_NAME, version) values (?,?,?)",
                new PostgresUUID(UUID.fromString(toBeDeletedByJson)),
                "TestFName",
                1
        );
        toBeDeletedByXml = dirtyData.createEntityId().toString();
        executePrepared("insert into JPADEMO_TEST_ENTITY(id, F_NAME, version) values (?,?,?)",
                new PostgresUUID(UUID.fromString(toBeDeletedByXml)),
                "TestFName",
                1
        );

    }

    /********************************************************************************8
     *
     * /api/commit SECTION
     *
     */

    @Test
    public void test_commit_new_instance_JSON() throws Exception {
        UUID newUuid = dirtyData.createEntityId();
        String json = prepareFile("new_test_entity.json", MapUtils.asMap(
                "$ENTITY-TO_BE_REPLACED_ID$", "NEW-jpademo_TestEntity-" + newUuid)
        );
        WebResponse response = POST(apiPath + "/api/commit?" + "s=" + sessionId, json,
                "application/json;charset=UTF-8");
        JSONArray res = new JSONArray(response.getText());
        assertEquals("jpademo_TestEntity-" + newUuid.toString(), res.getJSONObject(0).getString("id"));

        response = GET(apiPath + "/api/find.json?e=jpademo_TestEntity-" + newUuid + "&s=" + sessionId,
                "charset=UTF-8");
        JSONObject entity = new JSONObject(response.getText());
        assertEquals("Test_First_Name", entity.getString("fName"));
    }

//    @Test
//    public void Acommit_insertInstance_JSON() throws Exception {
//        UUID newUuid = dirtyData.createEntityId();
//        String json = prepareFile("new_test_entity.json", MapUtils.asMap(
//                "$ENTITY-TO_BE_REPLACED_ID$", "NEW-jpademo_TestEntity-" + newUuid)
//        );
//        WebResponse response = POST(apiPath + "/api/commit?" + "s=" + sessionId, json,
//                "application/json;charset=UTF-8");
//        JSONArray res = new JSONArray(response.getText());
//        System.out.println(response.getText());
//        assertEquals("jpademo_TestEntity-" + newUuid, res.getJSONObject(0).getString("id"));
//
//        response = GET(apiPath + "/api/find.json?e=jpademo_TestEntity-" + newUuid + "&s=" + sessionId,
//                "charset=UTF-8");
//        JSONObject entity = new JSONObject(response.getText());
//        assertEquals("jpademo_TestEntity-" + newUuid, entity.getString("id"));
//
////        deleteEntity(newUuid.toString());
//    }

    @Test
    public void test_commit_new_instance_XML() throws Exception {
        UUID newUuid = dirtyData.createEntityId();
        String xml = prepareFile("new_test_entity.xml", MapUtils.asMap(
                "$ENTITY-TO_BE_REPLACED_ID$", "NEW-jpademo_TestEntity",
                "$TO_BE_REPLACED_ID$", newUuid.toString())
        );

        WebResponse response = POST(apiPath + "/api/commit?" + "s=" + sessionId, xml,
                "text/xml;charset=UTF-8");
        Document document = Dom4j.readDocument(response.getText());
        List instanceNodes = document.selectNodes("/instances/instance");
        assertEquals(1, instanceNodes.size());
        Element instanceEl = (Element) instanceNodes.get(0);
        assertFieldValueEquals("Test_First_Name", instanceEl, "fName");
        assertFieldValueEquals("Test_Last_Name", instanceEl, "lName");
        assertFieldValueEquals("12", instanceEl, "age");

        response = GET(apiPath + "/api/find.xml?e=jpademo_TestEntity-" + newUuid + "&s=" + sessionId,
                "charset=UTF-8");
        document = Dom4j.readDocument(response.getText());
        instanceNodes = document.selectNodes("/instances/instance");
        assertEquals(1, instanceNodes.size());
        instanceEl = (Element) instanceNodes.get(0);
        assertFieldValueEquals("Test_First_Name", instanceEl, "fName");
        assertFieldValueEquals("Test_Last_Name", instanceEl, "lName");

//        deleteEntity(newUuid.toString());
    }

    @Test
    public void test_commit_remove_instance_JSON() throws Exception {
        WebResponse response = GET(apiPath + "/api/find.json?e=jpademo_TestEntity-" + toBeDeletedByJson + "&s=" + sessionId,
                "charset=UTF-8");
        assertNotNull(response.getText());
        assertFalse(response.getText().isEmpty());

        String json = prepareFile("remove_entity.json",
                MapUtils.asMap(
                        "$ENTITY-TO_BE_REPLACED_ID$",
                        "jpademo_TestEntity-" + toBeDeletedByJson,
                        "$TO_BE_REPLACED_ID$",
                        toBeDeletedByJson
                )
        );

        response = POST(apiPath + "/api/commit?" + "s=" + sessionId, json,
                "application/json;charset=UTF-8");
        JSONArray res = new JSONArray(response.getText());
        assertEquals("jpademo_TestEntity-" + toBeDeletedByJson, res.getJSONObject(0).getString("id"));
        assertFalse(res.getJSONObject(0).isNull("version"));

        try {
            GET(apiPath + "/api/find.json?e=jpademo_TestEntity-" + toBeDeletedByJson + "&s=" + sessionId,
                    "text/xml;charset=UTF-8");
            fail();
        } catch (HttpNotFoundException e) {
        }
    }

    @Test
    public void test_commit_remove_instance_XML() throws Exception {
        WebResponse response = GET(apiPath + "/api/find.xml?e=jpademo_TestEntity-" + toBeDeletedByXml + "&s=" + sessionId,
                "charset=UTF-8");
        assertNotNull(response.getText());

        String xml = prepareFile("remove_entity.xml",
                MapUtils.asMap(
                        "$ENTITY-TO_BE_REPLACED_ID$",
                        "jpademo_TestEntity-" + toBeDeletedByXml,
                        "$TO_BE_REPLACED_ID$",
                        toBeDeletedByXml
                )
        );
        response = POST(apiPath + "/api/commit?" + "s=" + sessionId, xml,
                "text/xml;charset=UTF-8");
        Document document = Dom4j.readDocument(response.getText());
        List instanceElements = document.selectNodes("/instances/instance");
        assertEquals(1, instanceElements.size());
        Element instanceEl = (Element) instanceElements.get(0);
        assertFieldValueEquals("2", instanceEl, "version");
        assertFieldValueEquals("TestFName", instanceEl, "fName");
        assertFieldValueEquals("admin", instanceEl, "deletedBy");

        exception.expect(HttpNotFoundException.class);
        GET(apiPath + "/api/find.xml?e=jpademo_TestEntity-" + toBeDeletedByXml + "&s=" + sessionId,
                "charset=UTF-8");

        int count = selectCount("jpademo_TestEntity", UUID.fromString(toBeDeletedByXml));
        assertEquals(1, count);
    }

    /********************************************************************************8
     *
     * /api/find SECTION
     *
     */

    @Test
    public void test_find_entity_JSON() throws Exception {
        WebResponse response = GET(apiPath + "/api/find.json?e=jpademo_TestEntity-" + firstId + "&s=" + sessionId,
                "charset=UTF-8");
        JSONObject entity = new JSONObject(response.getText());
        assertEquals("jpademo_TestEntity-" + firstId, entity.getString("id"));
    }

    @Test
    public void test_find_entity_XML() throws Exception {
        WebResponse response = GET(apiPath + "/api/find.xml?e=jpademo_TestEntity-" + firstId + "&s=" + sessionId,
                "charset=UTF-8");
        Document document = Dom4j.readDocument(response.getText());
        List instanceNodes = document.selectNodes("/instances/instance");
        assertEquals(1, instanceNodes.size());
        org.dom4j.Element instanceEl = (org.dom4j.Element) instanceNodes.get(0);
        assertEquals("jpademo_TestEntity-" + firstId, instanceEl.attributeValue("id"));
    }

    /********************************************************************************8
     *
     * /api/service GET SECTION
     *
     */

    @Test
    public void test_get_entity_JSON() throws IOException, SAXException, JSONException {
        WebResponse response = invokeServiceMethodGet("json", "findEntityById", firstId);

        JSONObject responseObject = new JSONObject(response.getText());
        JSONObject resultObject = responseObject.getJSONObject("result");
        assertNotNull(resultObject);
        assertEquals("jpademo_TestEntity-" + firstId, resultObject.getString("id"));
    }

    @Test
    public void test_get_entity_XML() throws IOException, SAXException, JSONException {
        WebResponse response = invokeServiceMethodGet("xml", "findEntityById", firstId);
        Document document = Dom4j.readDocument(response.getText());
        Element rootElement = document.getRootElement();
        Element instances = rootElement.element(XMLConverter.ROOT_ELEMENT_INSTANCE);
        List<Element> instanceList = Dom4j.elements(instances, XMLConverter.ELEMENT_INSTANCE);
        assertEquals(1, instanceList.size());
        Element instance = instanceList.get(0);
        assertEquals("jpademo_TestEntity-" + firstId, instance.attributeValue("id"));
    }

    @Test
    public void test_get_entity_list_JSON() throws IOException, SAXException, JSONException {
        WebResponse response = invokeServiceMethodGet("json", "finAllEntities");

        JSONObject responseObject = new JSONObject(response.getText());
        JSONArray resultArray = responseObject.getJSONArray("result");
        assertNotNull(resultArray);
        assertEquals(6, resultArray.length());

        JSONObject entityObject = resultArray.getJSONObject(0);

        assertEquals("jpademo_TestEntity-" + firstId, resultArray.getJSONObject(0).getString("id"));
        assertEquals("jpademo_TestEntity-" + secondId, resultArray.getJSONObject(1).getString("id"));
    }

    @Test
    public void test_get_entity_list_XML() throws IOException, SAXException, JSONException {
        WebResponse response = invokeServiceMethodGet("xml", "finAllEntities");

        Document document = Dom4j.readDocument(response.getText());
        Element rootElement = document.getRootElement();
        Element instancesEl = rootElement.element(XMLConverter.ROOT_ELEMENT_INSTANCE);
        List<Element> instanceList = Dom4j.elements(instancesEl, XMLConverter.ELEMENT_INSTANCE);
        assertEquals(6, instanceList.size());

        Node firstEntityElement = instancesEl.selectSingleNode("instance[@id='jpademo_TestEntity-" + firstId + "']");
        assertNotNull(firstEntityElement);

        Node secondEntityElement = instancesEl.selectSingleNode("instance[@id='jpademo_TestEntity-" + secondId + "']");
        assertNotNull(secondEntityElement);
    }

    /********************************************************************************8
     *
     * /api/service POST SECTION
     *
     */

    @Test
    public void test_service_post_JSON() throws IOException, SAXException, JSONException {
        Map<String, String> replacements = new HashMap<>();
        String newFirstName = "NewTestFName";
        replacements.put("$ENTITY-TO_BE_REPLACED_ID$", thirdId);
        replacements.put("$NEW_FIRST_NAME$", newFirstName);

        WebResponse response = invokeServiceMethodPost("update_entity_name_service_post.json", replacements, "application/json;charset=UTF-8");
        JSONObject responseObject = new JSONObject(response.getText());
        JSONObject resultObject = responseObject.getJSONObject("result");
        assertNotNull(resultObject);
        assertEquals("jpademo_TestEntity-" + thirdId, resultObject.getString("id"));
        assertEquals(newFirstName, resultObject.getString("fName"));
    }

    @Test
    public void test_service_post_XML() throws Exception {
        Map<String, String> replacements = new HashMap<>();
        String newFirstName = "NewTestFName";
        replacements.put("$ENTITY-TO_BE_REPLACED_ID$", forthId);
        replacements.put("$NEW_FIRST_NAME$", newFirstName);

        WebResponse response = invokeServiceMethodPost("update_entity_name_service_post.xml", replacements, "text/xml;charset=UTF-8");

        Document document = Dom4j.readDocument(response.getText());
        Element instanceEl = (Element) document.selectSingleNode("result/instances/instance");
        assertNotNull(instanceEl);

        String newFName = fieldValue(instanceEl, "fName");
        assertEquals("jpademo_TestEntity-" + forthId, instanceEl.attributeValue("id"));
        assertEquals(newFirstName, newFName);


    }

    @Test
    public void test_service_collection_post_JSON() throws Exception {
        Map<String, String> replacements = new HashMap<>();
        String newFName = "NewTestFNames";
        replacements.put("$ENTITY_ID_1$", thirdId);
        replacements.put("$ENTITY_ID_2$", forthId);
        replacements.put("$NEW_FIRST_NAME$", newFName);

        WebResponse response = invokeServiceMethodPost("update_entity_names_service_post.json", replacements, "application/json;charset=UTF-8");
        JSONObject responseObject = new JSONObject(response.getText());
        JSONArray resultArray = responseObject.getJSONArray("result");
        assertEquals(2, resultArray.length());

        JSONObject entity1 = resultArray.getJSONObject(0);
        assertEquals("jpademo_TestEntity-" + thirdId, entity1.getString("id"));
        assertEquals(newFName, entity1.getString("fName"));

        JSONObject entity2 = resultArray.getJSONObject(1);
        assertEquals("jpademo_TestEntity-" + forthId, entity2.getString("id"));
        assertEquals(newFName, entity2.getString("fName"));
    }

    @Test
    public void test_service_collection_post_XML() throws Exception {
        Map<String, String> replacements = new HashMap<>();
        String newFName = "NewTestFNames";
        replacements.put("$ENTITY_ID_1$", firstId);
        replacements.put("$ENTITY_ID_2$", secondId);
        replacements.put("$NEW_FIRST_NAME$", newFName);

        WebResponse response = invokeServiceMethodPost("update_entity_names_service_post.xml", replacements, "text/xml;charset=UTF-8");
        Document document = Dom4j.readDocument(response.getText());
        List instances = document.selectNodes("result/instances/instance");
        assertEquals(2, instances.size());

        Element entityEl1 = (Element) instances.get(0);
        String elName1 = fieldValue(entityEl1, "fName");
        assertEquals("jpademo_TestEntity-" + firstId, entityEl1.attributeValue("id"));
        assertEquals(newFName, elName1);

        Element entityEl2 = (Element) instances.get(1);
        String elName2 = fieldValue(entityEl2, "fName");
        assertEquals("jpademo_TestEntity-" + secondId, entityEl2.attributeValue("id"));
        assertEquals(newFName, elName2);
    }

    /********************************************************************************8
     *
     * /api/query SECTION
     *
     */

    @Test
    public void test_get_query_JSON() throws Exception {
        String url = apiPath + "/api/query.json?e=jpademo_TestEntity&q=select c from jpademo_TestEntity c where c.id = :id&id=" + firstId + "&s=" + sessionId;
        WebResponse response = GET(url, "charset=UTF-8");
        JSONArray entities = new JSONArray(response.getText());
        assertEquals(1, entities.length());
        assertEquals("jpademo_TestEntity-" + firstId, ((JSONObject) entities.get(0)).getString("id"));
    }

    @Test
    public void test_post_query_JSON() throws Exception {
        String url = apiPath + "/api/query?s=" + sessionId;

        Map<String, Object> content = new HashMap<>();
        content.put("entity", "jpademo_TestEntity");
        content.put("query", "select c from jpademo_TestEntity c where c.id = :id");

        Map<String, Object> params = new HashMap<>();
        params.put("name", "id");
        params.put("value", firstId);
        content.put("params", Collections.singletonList(params));

        JSONObject jsonObject = new JSONObject(content);
        WebResponse response = POST(url, jsonObject.toString(), "application/json");
        JSONArray entities = new JSONArray(response.getText());
        assertEquals(1, entities.length());
        assertEquals("jpademo_TestEntity-" + firstId, ((JSONObject) entities.get(0)).getString("id"));
    }


    @Test
    public void test_get_query_XML() throws IOException, SAXException {
        String url = apiPath + "/api/query.xml?e=jpademo_TestEntity&q=select c from jpademo_TestEntity c where c.id = :id&id=" + firstId + "&s=" + sessionId;
        WebResponse response = GET(url, "charset=UTF-8");
        Document document = Dom4j.readDocument(response.getText());
        List instanceNodes = document.selectNodes("/instances/instance");
        assertEquals(1, instanceNodes.size());
        Element instanceEl = (Element) instanceNodes.get(0);
        assertEquals("jpademo_TestEntity-" + firstId, instanceEl.attributeValue("id"));
    }

    @Test
    public void test_post_query_XML() throws IOException, SAXException {
        String url = apiPath + "/api/query?s=" + sessionId;
        String xml = prepareFile("query-entity.xml",
                MapUtils.asMap(
                        "$ENTITY-TO_BE_REPLACED_ID$", "jpademo_TestEntity",
                        "$QUERY-TO_BE_REPLACED_ID$", "select c from jpademo_TestEntity c where c.id = :id",
                        "$PARAM_NAME-TO_BE_REPLACED$", "id",
                        "$PARAM_VALUE-TO_BE_REPLACED$", firstId
                )
        );

        WebResponse response = POST(url, xml, "text/xml;charset=UTF-8");
        Document document = Dom4j.readDocument(response.getText());
        List instanceNodes = document.selectNodes("/instances/instance");
        assertEquals(1, instanceNodes.size());
        Element instanceEl = (Element) instanceNodes.get(0);
        assertEquals("jpademo_TestEntity-" + firstId, instanceEl.attributeValue("id"));
    }

    /********************************************************************************8
     *
     * /api/deployViews SECTION
     *
     */

    @Test
    public void test_deployViews() throws Exception {
        String xml = prepareFile("new_views.xml", Collections.EMPTY_MAP);
        POST(apiPath + "/api/deployViews?" + "s=" + sessionId, xml,
                "text/xml;charset=UTF-8");

        WebResponse response = GET(apiPath + "/api/find.xml?e=jpademo_TestEntity-" + thirdId + "&s=" + sessionId,
                "charset=UTF-8");
        assertNotNull(response.getText());

        Document document = Dom4j.readDocument(response.getText());
        Element entity = (Element) document.selectSingleNode("instances/instance");
        assertFieldValueEquals("TestFName", entity, "fName");

        response = GET(apiPath + "/api/find.xml?e=jpademo_TestEntity-" + thirdId + "-test.minimal&s=" + sessionId,
                "charset=UTF-8");
        assertNotNull(response.getText());
        document = Dom4j.readDocument(response.getText());
        entity = (Element) document.selectSingleNode("instances/instance");
        assertNull(entity.selectSingleNode("field[@name='fName']"));
    }


    /********************************************************************************8
     *
     * /api/printDomain SECTION
     *
     */

    @Test
    public void test_printDomain() throws IOException, SAXException {
        WebResponse response = GET(apiPath + "/api/printDomain?" + "s=" + sessionId,
                "text/html;charset=UTF-8");
        String txt = response.getText();
        assertTrue(txt.contains("<h1>Domain model description</h1>"));
    }

    private String login(String login, String password) throws JSONException, IOException, SAXException {
        JSONObject loginJSON = new JSONObject();
        loginJSON.put("username", login);
        loginJSON.put("password", password);
        loginJSON.put("locale", "ru");

        WebResponse response = POST(apiPath + "/api/login",
                loginJSON.toString(), "application/json;charset=UTF-8");
        return response.getText();
    }

    private void logout() throws JSONException {
        if (sessionId == null)
            return;
        try {
            GET(apiPath + "/api/logout?session=" + sessionId, "charset=UTF-8");
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

    private void deleteEntity(String id) throws SQLException {
        executePrepared("delete from JPADEMO_TEST_ENTITY where id = ?",
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

    private WebResponse invokeServiceMethodGet(String type, String methodName, String... params) throws IOException, SAXException {
        String serviceName = PortalTestService.NAME;
        StringBuilder sb = new StringBuilder();
        sb.append(apiPath);
        sb.append("/api/service.").append(type);
        sb.append("?s=").append(sessionId)
                .append("&service=").append(serviceName)
                .append("&method=").append(methodName);
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            sb.append("&param").append(i).append("=").append(param);
        }
        return GET(sb.toString(), "text/html;charset=UTF-8");
    }

    private WebResponse invokeServiceMethodPost(String fileName, Map<String, String> replacements, String type) throws IOException, SAXException {
        StringBuilder sb = new StringBuilder();
        sb.append(apiPath);
        sb.append("/api/service")
                .append("?s=").append(sessionId);
        String fileContent = getFileContent(fileName, replacements);
        return POST(sb.toString(), fileContent, type);
    }

    private String prepareFile(String fileName, Map<String, String> replacements) throws IOException {
        InputStream is = getClass().getResourceAsStream(fileName);
        String fileContent = IOUtils.toString(is);
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            fileContent = fileContent.replace(entry.getKey(), entry.getValue());
        }
        return fileContent;
    }

    private String getFileContent(String fileName, Map<String, String> replacements) throws IOException {
        InputStream is = getClass().getResourceAsStream(fileName);
        String fileContent = IOUtils.toString(is);
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            fileContent = fileContent.replace(entry.getKey(), entry.getValue());
        }
        return fileContent;
    }

    private String fieldValue(Element instanceEl, String propertyName) {
        return instanceEl.selectSingleNode("field[@name = '" + propertyName + "']").getText();
    }

    private void assertFieldValueEquals(String value, Element instanceEl, String fieldName) {
        Element fieldEl = (Element) instanceEl.selectSingleNode("field[@name='" + fieldName + "']");
        assertNotNull(fieldEl);
        assertEquals(value, fieldEl.getText());
    }

    private int selectCount(String table, UUID uuid) throws SQLException {
        String sql = "select count(*) from " + table + "where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        Object param = new PostgresUUID(uuid);
        stmt.setObject(1, param);
        ResultSet rs = stmt.executeQuery();
        return rs.getInt(0);
    }

}
