package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String logout = request.getParameter("action");

        //check if the logout button was clicked
        if (logout != null) {
            //if it was clicked, then invalidate the session and load the register JSP        
            session.invalidate();
            //load register JSP        
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;

            //if the logout button was not clicked check if there is a running session 
        } else if (userName != null) {
            //if there is a running session, then load the shoppingList JSP               
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        } else {
            //load the register JSP
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //store session
        HttpSession session = request.getSession();

        //retrive action parameter to tell what must be done
        String action = request.getParameter("action");

        //retrieve itemlist, if it is null, then make one
        ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("itemlist");
        if (itemList == null) {
            itemList = new ArrayList<String>();
        }

        switch (action) {
            case "register":
                //store username in a session attribute
                session.setAttribute("username", request.getParameter("user_name"));

                //load shoppingList JSP
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            case "add":
                //retrieve and check if item is null
                String item = request.getParameter("item_to_add");

                //add item to arrayList if it is not null
                if (item != null) {
                    itemList.add(item);
                }

                //save arraylist into itemlist session attribute
                session.setAttribute("itemlist", itemList);

                //reload the jsp with the new information
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            case "delete":
                //get item value from radio button
                String item_to_delete = request.getParameter("single_item");

                //check if item was selected and delete if it was
                if (item_to_delete != null) {
                    itemList.remove(item_to_delete);
                }
                //resave the updated arraylist to the same session attribute name
                session.setAttribute("itemlist", itemList);
                //reload the jsp
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
        }

    }

}
