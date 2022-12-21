package com.azakamu.sbomdisplayer.domain;

/**
 * @author janlingen
 */
public record Dependency(Long id,
                         String name,
                         String publisher,
                         String group,
                         String description) {

}
