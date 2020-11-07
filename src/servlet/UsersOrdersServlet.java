package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import controller.Controller;
import entity.Discount;
import entity.OrderInvoice;
import entity.StoreProduct;
import servlet.pojo.ConsumerOrderDTO;
import servlet.pojo.DiscountDTO;
import servlet.pojo.ProductInOrderDTO;
import servlet.pojo.StoreOrderDTO;
import servlet.util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "UsersOrdersServlet", urlPatterns = {"/api/users/orders"})
public class UsersOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        Gson gson = new Gson();
        Controller controller = Controller.getInstance();
        List<ConsumerOrderDTO> consumerOrderDTOs = controller.getOrdersByCustomerId(Integer.parseInt(uuid)).stream()
                .map(ConsumerOrderDTO::new)
                .collect(Collectors.toList());
        JsonObject replyJSON = new JsonObject();
        JsonElement temp = gson.toJsonTree(consumerOrderDTOs);
        JsonArray ordersJSON = temp.getAsJsonArray();
        replyJSON.add("allOrders", ordersJSON);
        String reply = String.valueOf(replyJSON.getAsJsonObject());
        response.getWriter().write(reply);
        response.getWriter().close();
    }
}
