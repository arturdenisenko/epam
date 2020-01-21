/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * helper to execute commands
 */
public interface ServletCommand {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
