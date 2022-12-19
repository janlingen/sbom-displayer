package com.azakamu.sbomdisplayer.domain.entities;

import java.util.List;

/**
 * @author janlingen
 */
public record Project(Integer id, String name, List<Dependency> dependencies) {

}
