/*
 * Copyright (C) 2005-2015 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */

package org.alfresco.share;

import org.alfresco.po.share.LoginPage;
import org.alfresco.webdrone.WebDrone;
/**
 * Utility steps that is commonly used across steps.
 * 
 * @author Michael Suzuki
 *
 */
public class StepsUtil
{

    private StepsUtil(){}
    /**
     * Logs user into alfresco share.
     * @param drone {@link WebDrone} instance
     * @param username String user identifier
     * @param password String user password
     */
    public static void login(WebDrone drone, String username, String password)
    {
        String url = "http://localhost:8080/share";
        drone.navigateTo(url);
        LoginPage login = drone.getCurrentPage().render();
        login.loginAs(username, password);
    }
}
