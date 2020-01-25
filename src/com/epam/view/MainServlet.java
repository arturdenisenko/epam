/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.view;

import com.epam.command.CommandManager;
import com.epam.command.ServletCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlet")
@MultipartConfig
public class MainServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServlet.class);

    private CommandManager commandManager;

    public void init(ServletConfig config) throws ServletException {
        LOGGER.info("SERVLET INITIALIZING...");
        commandManager = new CommandManager();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("GET REQUEST PROCESSING");

        ServletCommand command = commandManager.getGetCommand(request);
        String page = command.execute(request, response);
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("POST REQUEST PROCESSING");

        ServletCommand command = commandManager.getPostCommand(request);
        String page = command.execute(request, response);
        request.getRequestDispatcher(page).forward(request, response);
    }
}