/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author DELL
 */
@DatabaseIdentityStoreDefinition(
    dataSourceLookup = "mysql/project",
    callerQuery = "SELECT password FROM users WHERE username = ?",
    groupsQuery = "SELECT groupName FROM users u JOIN groupmaster g ON u.groupmasterId = g.groupmasterId WHERE u.username = ?",
    hashAlgorithm = Pbkdf2PasswordHash.class,
    priority = 30
)

@ApplicationScoped
public class Config {

}
