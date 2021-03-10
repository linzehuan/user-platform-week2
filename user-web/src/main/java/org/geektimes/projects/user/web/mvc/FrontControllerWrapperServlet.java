package org.geektimes.projects.user.web.mvc;

import org.geektimes.context.ComponentContext;
import org.geektimes.web.mvc.FrontControllerServlet;
import org.geektimes.web.mvc.controller.Controller;

import java.util.ServiceLoader;

/**
 * @author Lin Zehuan
 */
public class FrontControllerWrapperServlet extends FrontControllerServlet {


    @Override
    public ServiceLoader<Controller> wrapController() {
        ServiceLoader<Controller> controllers = super.wrapController();
        for (Controller c : controllers) {
            ComponentContext instance = ComponentContext.getInstance();
            instance.injectComponents(c, c.getClass());
        }
        return controllers;


    }
}
