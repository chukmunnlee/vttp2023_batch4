package vttp.batch4.paf.day29bgg.models;

import java.util.List;

public record Game(int id, String name, int ranking, List<Comment> comments) { }
