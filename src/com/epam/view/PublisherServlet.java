package com.epam.view;

import com.epam.dao.PublisherDao;
import com.epam.dao.impl.PublisherDaoImpl;
import com.epam.model.periodical.Publisher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PublisherServlet", urlPatterns = "/")
public class PublisherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PublisherDao publisherDao;

    public void init() {
        publisherDao = new PublisherDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/new":
                showNewForm(req, resp);
                break;
            case "/insert":
                insertNewPublisher(req, resp);
                break;
            case "/edit":
                showEditForm(req, resp);
                break;
            case "/delete":
                deletePublisher(req, resp);
                break;
            case "/update":
                updatePublisher(req, resp);
                break;
            default:
                publishersList(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    // publishers list for view layer
    private void publishersList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Publisher> publishersList;
        publishersList = publisherDao.selectAll();
        request.setAttribute("publishersList", publishersList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/publishers.jsp");
        requestDispatcher.forward(request, response);
    }

    // display publisher form here
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/publisher-form.jsp");
        requestDispatcher.forward(request, response);
    }

    // insert new Publisher
    private void insertNewPublisher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //int id = Integer.parseInt(req.getParameter("id"));
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        publisherDao.insert(new Publisher(name));
        resp.sendRedirect("publishersList");
    }

    //delete publisher
    private void deletePublisher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        publisherDao.delete(id);
        resp.sendRedirect("publishersList");

    }

    //publisher edit form
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Publisher exitingPublisher = publisherDao.select(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/publisher-form.jsp");
        req.setAttribute("publisher", exitingPublisher);
        requestDispatcher.forward(req, resp);
    }

    //Update publisher
    private void updatePublisher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        publisherDao.update(new Publisher(id, name));
        resp.sendRedirect("publishersList");
    }

}
