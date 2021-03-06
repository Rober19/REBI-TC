/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controllers.UserController;
import Entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rober19
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/api/user"})
public class UserServlet extends HttpServlet {

    UserController userController = new UserController();

    private void addCorsHeader(HttpServletResponse response){
        //TODO: externalize the Allow-Origin
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        addCorsHeader(res);
//        PrintWriter out = res.getWriter();
//        res.setContentType("application/json");
//        res.setCharacterEncoding("UTF-8");
//
//        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
//        String productsListJson = prettyGson.toJson(new Object[]{"Ok1", 200});
//        out.print(productsListJson);
        PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        String type = "";
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new Gson();
        String ExcpMessage = "debe enviar el parametro"
                + " ?type=getall"
                + " | ?type=getbyId&id=exampleid"
                + " | ?type=login&email=a@a.com&password=###";
        try {
            type = req.getParameter("type");
            switch (type) {
                case "getall": {
                    String UserListJson = prettyGson
                            .toJson(userController.GetUsers());
                    out.print(UserListJson);
                    break;
                }
                case "getbyId": {

                    String id = req.getParameter("id");
                   String UserListJson = prettyGson
                            .toJson(userController.getUserById(id));
                    out.print(UserListJson);
                    break;
                }
                case "login": {
                    prettyGson = new GsonBuilder()
                            .setPrettyPrinting().create();

                    String email = req.getParameter("email");
                    String password = req.getParameter("password");
                    String res_1 = prettyGson.toJson(userController
                            .LoginUser(new User("", email, password, "", "")));
                    out.print(res_1);
                    if (res_1 == null) {
                        throw new Exception("Datos erroneos");
                    }
                    break;
                }
                default:
                    String UserListJson = prettyGson
                            .toJson(
                                    new Object[]{ExcpMessage,
                                        200});
                    out.print(UserListJson);
                    break;
            }
        } catch (Exception ex) {

//            String UserListJson = prettyGson
//                    .toJson(new Object[]{ExcpMessage, 200});
//            out.print(UserListJson);
            out.print(gson.toJson(jsres("err", ExcpMessage)));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
         addCorsHeader(res);
        PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        Gson gson = new Gson();
        try {

            StringBuilder sb = new StringBuilder();
            String body;
            while ((body = req.getReader().readLine()) != null) {
                sb.append(body);
            }

            User user = (User) gson.fromJson(sb.toString(), User.class);           
            userController.AddUser(user);
            out.print(sb.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
            out.print(gson.toJson(jsres("err", ex.toString())));
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PrintWriter out = res.getWriter();
         addCorsHeader(res);
        res.setContentType("application/json");
        Gson gson = new Gson();

        try {

            StringBuilder sb = new StringBuilder();
            String body;
            while ((body = req.getReader().readLine()) != null) {
                sb.append(body);
            }
            //out.print(gson.toJson(jsres("msg", "hello from put")));

            User user = (User) gson.fromJson(sb.toString(), User.class);
            userController.UpdateUser(user);
            String userJson = gson.toJson(user);
            out.print(userJson);

        } catch (Exception ex) {

            ex.printStackTrace();
            out.print(gson.toJson(jsres("err", ex.toString())));
        }

        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
         addCorsHeader(res);
        PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        Gson gson = new Gson();

        try {

            StringBuilder sb = new StringBuilder();
            String body;
            while ((body = req.getReader().readLine()) != null) {
                sb.append(body);
            }

            JsonObject jobj = new Gson()
                    .fromJson(sb.toString(), JsonObject.class);

            String result = req.getParameter("id");

            userController.DeleteUser(result);

            out.print(gson.toJson(jsres("data", "OK")));
            // Product product = (Product) gson.fromJson(sb.toString(), Product.class);
            //productController.DeleteProduct(body);
            //String productJson = gson.toJson(product);
        } catch (Exception ex) {
            ex.printStackTrace();
            out.print(gson.toJson(jsres("err", ex.toString())));
        }

    }

    public JsonObject jsres(String key, String value) {
        JsonObject obj = new JsonObject();
        obj.addProperty(key, value);
        return obj;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
