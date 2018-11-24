package view;

import domain.User;
import domain.UserType;
import service.Service;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class UserServletController extends BaseController {

    private Service<User> service;

    public void init() {
        service = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException{
        try {
            String action = extractAction(request);

            switch (action){
                case "/new":
                    showNewForm(request, response);
                    break;
//                case "/insert":
//                    insertUser(request, response);
                case "/delete":
                    deleteUser(request, response);
                case "/edit":
                    showEditForm(request, response);
//                case "/update":
//                    updateUser(request, response);
                default:
                    listUser(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new ServletException(ex);
        }
    }

    private String extractAction(HttpServletRequest request){
        String pathInfo = request.getPathInfo();
        if(pathInfo == null){
            return "list";
        } else {
            return pathInfo;
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = service.findAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/UserList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/UserForm.jsp");
        request.setAttribute("types", UserType.values());
        request.setAttribute("isNew", true);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User existingUser = service.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/UserForm.jsp");
        request.setAttribute("user", existingUser);
        request.setAttribute("types", UserType.values());
        request.setAttribute("isEdit", true);
        dispatcher.forward(request, response);

    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            service.remove(id);
            request.setAttribute("message", "User deleted successfully");
            listUser(request, response);
        } catch (Exception e){
            request.setAttribute("message", processException(e));
            listUser(request, response);
        }
    }
}