package com.kjetland.hoursregrest.client.model

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 09.feb.2010
 * Time: 22:56:06
 * To change this template use File | Settings | File Templates.
 */

class Project(
  val id : Int,
  val name : String
        ){

  override def toString() : String={
    "Project(id="+id+", name="+name+")"
  }

  override def equals( other : Any) = {
    other match{
      case o : Project => this.id == o.id && this.name == o.name
      case _ => false
    }

  }

}

