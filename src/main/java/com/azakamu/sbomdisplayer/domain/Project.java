package com.azakamu.sbomdisplayer.domain;

import java.util.List;

/**
 * @author janlingen
 */
public record Project(Long id, String name, List<Version> dependencies) {

}
