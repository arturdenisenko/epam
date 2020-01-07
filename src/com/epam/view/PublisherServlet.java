/*
 * @Denisenko Artur
 */

package com.epam.view;

import com.epam.dao.impl.PublisherDaoImpl;
import com.epam.exception.ServiceException;
import com.epam.model.periodical.Publisher;
import com.epam.service.PublisherService;
import com.epam.service.impl.PublisherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PublisherServlet", urlPatterns = "/admin/publishers")
public class PublisherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherServlet.class);
    private PublisherService publisherService;

    public void init() {
        publisherService = new PublisherServiceImpl(new PublisherDaoImpl());
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
        LOGGER.info("publisherList view");
        List<Publisher> publishersList;
        try {
            publishersList = publisherService.selectAll();
            request.setAttribute("publishersList", publishersList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/publishers.jsp");
        requestDispatcher.forward(request, response);
    }

    // display publisher form here
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("show new form");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/publisher-form.jsp");
        requestDispatcher.forward(request, response);
    }

    // insert new Publisher
    private void insertNewPublisher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        LOGGER.info("insert new");
        String name = req.getParameter("name");
        try {
            publisherService.insert(new Publisher(name));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("publishersList");
    }

    //delete publisher
    private void deletePublisher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("delete publisher");
        Long id = Long.valueOf((req.getParameter("id")));
        try {
            publisherService.delete(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("publishersList");
    }

    //publisher edit form
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("show edit form");
        Long id = Long.valueOf((req.getParameter("id")));
        Publisher exitingPublisher = null;
        try {
            exitingPublisher = publisherService.select(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/publisher-form.jsp");
        req.setAttribute("publisher", exitingPublisher);
        requestDispatcher.forward(req, resp);
    }

    //Update publisher
    private void updatePublisher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        LOGGER.info("update publisher");
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        try {
            publisherService.update(new Publisher(id, name));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("publishersList");
    }

}
