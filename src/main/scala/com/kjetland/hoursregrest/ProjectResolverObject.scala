package com.kjetland.hoursregrest

import client.Client
import client.model.Project

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 27.feb.2010
 * Time: 23:15:43
 * To change this template use File | Settings | File Templates.
 */

trait ProjectResolver{
  def resolveProject(project: String ) : Project
}

object ProjectResolverObject{

  def resolveProject(project: String, client: Client): Project = {
    return new ProjectResolverImpl( client ).resolveProject( project )
  }

}

class ProjectResolverImpl(client : Client) extends Object with ProjectResolver{

   def resolveProject(project: String): Project = {

    var _project = project.toLowerCase
    var selectedProject: Project = null
    client.projects.foreach {
      p =>
        var _p: Project = null
        if (p.id.toString == _project) {
          _p = p
        } else if (p.name.toLowerCase.contains(_project)) {
          _p = p
        }

        if (_p != null) {
          if (selectedProject != null) {
            throw new ArgException("project '" + project + "' is not unique");
          }
          selectedProject = _p
        }

    }

    if (selectedProject != null) return selectedProject

    throw new ArgException("Unable to resolve projectId from: " + project)

  }
}

