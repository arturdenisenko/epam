/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletCommand {
    //helper to execute commands
    String execute(HttpServletRequest request, HttpServletResponse response);
}
