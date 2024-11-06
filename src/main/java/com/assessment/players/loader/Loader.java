package com.assessment.players.loader;

import java.util.List;

public interface Loader<T> {
    List<T> loadData(String filePath) throws Exception;
}