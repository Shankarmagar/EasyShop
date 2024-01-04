package org.yearup.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.Profile;
import org.yearup.models.User;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("profile")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class ProfileController {
    private ProfileDao profileDao;
    private UserDao userDao;

    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao)
    {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Profile getProfile(Principal principal, HttpServletResponse response)
    {
        if(principal == null || principal.getName() == null)
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        try
        {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();
            return profileDao.getProfile(userId);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Profile editProfile(Principal principal, @RequestBody Profile profile)
    {
        try
        {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();
            return profileDao.editProfile(userId, profile);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

}
