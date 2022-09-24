package myapp.service;

import myapp.model.File;

import java.util.List;

public interface FileService {

    File getById(Long id);

    void save(File file);

    void deleteById(Long id);

    List<File> getAll();
}
